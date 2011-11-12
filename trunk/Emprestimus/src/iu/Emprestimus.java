package iu;

import static sistema.utilitarios.Validador.assertNaoNulo;
import static sistema.utilitarios.Validador.assertStringNaoVazia;
import static sistema.utilitarios.Validador.asserteTrue;

import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import maps.ComparaDistancia;
import maps.RefCoordenadas;

import sistema.autenticacao.Autenticacao;
import sistema.emprestimo.BancoDeEmprestimos;
import sistema.emprestimo.EmprestimoIF;
import sistema.item.AcervoDeItens;
import sistema.item.ItemIF;
import sistema.mensagem.ChatIF;
import sistema.mensagem.Correio;
import sistema.notificacao.GerenciadorDeNotificacoes;
import sistema.persistencia.ChatRepositorio;
import sistema.persistencia.EmprestimoRepositorio;
import sistema.persistencia.ItemRepositorio;
import sistema.usuario.RelacionamentosUsuarios;
import sistema.usuario.ReputacaoUsuarioComparator;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;

/**
 * Classe que implementa a fachada, usada para fazer a interação entre a
 * interface com o usuário e o sistema.
 * 
 * @author José Nathaniel L. de Abrante - nathaniel.una@gmail.com
 * @since 05/09/2011
 * @version 1.0
 */
public class Emprestimus implements EmprestimusIF {
	private static Emprestimus emprestimus;
	private Autenticacao autenticacao;
	private Calendar dataCorrente;
	private int diasExtras = 0;
	
	private Emprestimus(){
		autenticacao = Autenticacao.getInstance();
	}
	
	/**
	 * Metodo que faz parte do padrão Singleton e serve para retornar uma
	 * instancia da classe que sera unica para toda a execução.
	 * 
	 * @return um objeto do tipo GerenciadorDeSalas
	 */
	public synchronized static Emprestimus getInstance() {

		if (emprestimus == null) {
			emprestimus = new Emprestimus();
			
			return emprestimus;
		}
		
		return emprestimus;
	}

	@Override
	public void criarUsuario(String login, String nome, String endereco) throws Exception{
        autenticacao.criarUsuario(login, nome, endereco);
	}

	@Override
	public String abrirSessao(String login) throws Exception{
	    return autenticacao.abrirSessao(login);
	}
	
	@Override
	public String abrirSessao(String login, String senha) throws Exception {
		return autenticacao.abrirSessao(login, senha);
	}

	@Override
	public String getAtributoUsuario(String login, String atributo) throws Exception{
		String valor = null;
		
			valor = autenticacao.getAtributoUsuario(login, atributo);
		
		return valor;
	}

	@Override
	public String cadastrarItem(String idSessao, String nome, String descricao,
			String categoria) throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		assertStringNaoVazia(nome, Mensagem.NOME_INVALIDO.getMensagem(), Mensagem.NOME_INVALIDO.getMensagem());
		assertStringNaoVazia(categoria, Mensagem.CATEGORIA_INVALIDA.getMensagem(), Mensagem.CATEGORIA_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		
		String id = usuario.cadastrarItem(nome, descricao, categoria);
		
		return id;
	}

	@Override
	public String getAtributoItem( String idItem, String atributo ) throws Exception {
		
		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(), Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(ItemRepositorio.existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		assertStringNaoVazia(atributo, Mensagem.ATRIBUTO_INVALIDO.getMensagem(),Mensagem.ATRIBUTO_INVALIDO.getMensagem());
		
		atributo = atributo.toLowerCase().trim();
		String str = ItemRepositorio.getAtributoItem(idItem, atributo);
		return str;
		
	}

	@Override
	public String localizarUsuario(String idSessao, String chave,
			String atributo) throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(chave, Mensagem.PALAVRA_CHAVE_INVALIDA.getMensagem(), Mensagem.PALAVRA_CHAVE_INVALIDA.getMensagem());
		assertStringNaoVazia(idSessao, Mensagem.PALAVRA_CHAVE_INVALIDA.getMensagem(), Mensagem.PALAVRA_CHAVE_INVALIDA.getMensagem());
		assertStringNaoVazia(atributo, Mensagem.ATRIBUTO_INVALIDO.getMensagem(), Mensagem.ATRIBUTO_INVALIDO.getMensagem());

		atributo = atributo.toLowerCase();
		List<UsuarioIF> users;
		UsuarioIF eu = autenticacao.getUsuarioPeloIDSessao(idSessao);
		if(atributo.equals("nome")) {
			users = autenticacao.getUsuarioNome(chave);
			if(users.contains(eu)) users.remove(eu);
		} else if(atributo.equals("endereco")) {
			users = autenticacao.getUsuarioEndereco(chave);
			if(users.contains(eu)) users.remove(eu);
		} else {
			throw new Exception(Mensagem.ATRIBUTO_INEXISTENTE.getMensagem());
		}
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		RefCoordenadas coordenadas = new RefCoordenadas(usuario.getLatitude(), usuario.getLongitude());
		Collections.sort(users, new ComparaDistancia(coordenadas));
		String saida ="";
		for (int i = 0; i<users.size(); i++) {
			saida += users.get(i).getNome() + " - " + users.get(i).getEndereco();
			if(i != users.size() -1) {
				saida += "; ";
			}
		}
		if (saida.trim().equals(""))
			saida = Mensagem.PALAVRA_CHAVE_INEXISTENTE.getMensagem();
		return saida;
	}

	@Override
	public void requisitarAmizade(String idSessao, String login) throws Exception {
		Validador.assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		Validador.asserteTrue(Autenticacao.existeUsuario(login), Mensagem.USUARIO_INEXISTENTE.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		usuario.requisitarAmizade(login.trim());

	}

	@Override
	public String getRequisicoesDeAmizade(String idSessao) throws Exception {
		Validador.assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		List<UsuarioIF> usuarios = usuario.getQueremSerMeusAmigos();
		String saida = "";
		for (int i = 0; i < usuarios.size(); i++) {
			saida += usuarios.get(i).getLogin();
			if (i != usuarios.size() - 1) {
				saida += "; ";
			}
		}
		if (saida.trim().equals(""))
			saida = Mensagem.NAO_HA_REQUISICOES.getMensagem();
		return saida;
		
	}

	@Override
	public void aprovarAmizade(String idSessao, String login) throws Exception {
		Validador.assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.asserteTrue(autenticacao.existeIdSessao(idSessao.trim()), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.asserteTrue(Autenticacao.existeUsuario(login.trim()), Mensagem.USUARIO_INEXISTENTE.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		usuario.aprovarAmizade(login);
		
	}

	@Override
	public String ehAmigo(String idSessao, String login) throws Exception {
		Validador.assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		asserteTrue(Autenticacao.existeUsuario(login.trim()), Mensagem.USUARIO_INEXISTENTE.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		if (usuario.ehAmigo(login)) {
			return "true";
		}
		return "false";

	}

	@Override
	public String getAmigos(String idSessao) throws Exception {
		Validador.assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		
		return autenticacao.getUsuarioPeloIDSessao(idSessao).getAmigos();
	}

	@Override
	public String getAmigos(String idSessao, String login) throws Exception {
		Validador.assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.asserteTrue(Autenticacao.existeUsuario(login), Mensagem.USUARIO_INEXISTENTE.getMensagem());
		
		return Autenticacao.getUsuarioPorLogin(login).getAmigos();
	}

	@Override
	public String getItens(String idSessao) throws Exception{
		Validador.assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		
		return autenticacao.getUsuarioPeloIDSessao(idSessao).getListaItens();
	}

	@Override
	public String getItens(String idSessao, String login) throws Exception {
		Validador.assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.asserteTrue(Autenticacao.existeUsuario(login), Mensagem.USUARIO_INEXISTENTE.getMensagem());
		Validador.asserteTrue(autenticacao.getUsuarioPeloIDSessao(idSessao).ehAmigo(login), 
				Mensagem.USUARIO_NAO_TEM_PEMISSAO_VER_ITENS.getMensagem());
		
		return Autenticacao.getUsuarioPorLogin(login).getListaItens();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#requisitarEmprestimo(java.lang.String,
	 * java.lang.String, int)
	 */
	@Override
	public synchronized String requisitarEmprestimo(String idSessao, String idItem,
			int duracao) throws Exception{
		Validador.assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		Validador.assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(), Mensagem.ID_ITEM_INVALIDO.getMensagem());
		Validador.asserteTrue(ItemRepositorio.existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		Validador.asserteTrue(duracao > 0, Mensagem.EMPRESTIMO_DURACAO_INVALIDA.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		return usuario.requisitarEmprestimo(idItem, duracao);
		
	}

	@Override
	public String aprovarEmprestimo(String idSessao, String idRequisicaoEmprestimo) throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao.trim()), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(idRequisicaoEmprestimo, Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem(), Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		asserteTrue(EmprestimoRepositorio.existeEmprestimo(idRequisicaoEmprestimo.trim()), Mensagem.ID_REQUISICAO_EMP_INEXISTENTE.getMensagem());
		
		EmprestimoIF emp = EmprestimoRepositorio.recuperarEmprestimo(idRequisicaoEmprestimo); 
		
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao.trim());
		String saida = usuario.aprovarEmprestimo(idRequisicaoEmprestimo);
		return saida;
		
	}

	@Override
	public String getEmprestimos(String idSessao, String tipo) throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao.trim()), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(tipo, Mensagem.TIPO_INVALIDO.getMensagem(), Mensagem.TIPO_INVALIDO.getMensagem());
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao.trim());
		String result = null;
		return usuario.getEmprestimos(tipo); 
		
	}


	@Override
	public synchronized void devolverItem(String idSessao, String idEmprestimo)
			throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(idEmprestimo, Mensagem.ID_EMPRESTIMO_INVALIDO.getMensagem(), Mensagem.ID_EMPRESTIMO_INVALIDO.getMensagem());
		asserteTrue(EmprestimoRepositorio.existeEmprestimo(idEmprestimo), Mensagem.EMPRESTIMO_INEXISTENTE.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		EmprestimoIF emp = EmprestimoRepositorio.recuperarEmprestimo(idEmprestimo);
		asserteTrue(emp.getBeneficiado().equals(usuario),Mensagem.EMPRESTIMO_DEVOLUCAO_APENAS_BENEFICIADO.getMensagem());
		
		emp.devolverEmprestimo();
			
	}

	@Override
	public synchronized void requisitarDevolucao(String idSessao, String idEmprestimo)
			throws Exception {
		
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		assertStringNaoVazia(idEmprestimo, Mensagem.ID_EMPRESTIMO_INVALIDO.getMensagem(), Mensagem.ID_EMPRESTIMO_INVALIDO.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		EmprestimoIF emprestimo = EmprestimoRepositorio.recuperarEmprestimo(idEmprestimo);
		asserteTrue(emprestimo.getEmprestador().equals(usuario),
				"O usuário não tem permissão para requisitar a devolução deste item");

		dataCorrente = new GregorianCalendar();
		dataCorrente.add(Calendar.DAY_OF_YEAR, diasExtras);
		dataCorrente.add(Calendar.MILLISECOND, 0);
		emprestimo.getDataDeDevolucao();

		try {
			dataCorrente.compareTo(emprestimo.getDataDeDevolucao());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (dataCorrente.compareTo(emprestimo.getDataDeDevolucao()) < 0) {
			emprestimo.requisitarDevolucaoEmprestimo(true);
		} else {
			emprestimo.requisitarDevolucaoEmprestimo(false);
		}
		
		UsuarioIF amigo = emprestimo.getBeneficiado();
		String assunto = "Empréstimo do item " + emprestimo.getItem().getNome() + " a "
				+ amigo.getNome() + "";
		String mensagem = usuario.getNome() + " solicitou a devolução do item "
				+ emprestimo.getItem().getNome();
		usuario.enviarMensagemEmprestimo(amigo.getLogin(), assunto, mensagem, emprestimo
				.getIdEmprestimo());

	}
	
	@Override
	public synchronized void adicionarDias(String dias) throws Exception {
		assertNaoNulo(dias, Mensagem.ATRIBUTO_INVALIDO.getMensagem());
		int numDias = Integer.valueOf(dias);
		diasExtras += numDias;

	}

	@Override
	public void confirmarTerminoEmprestimo(String idSessao, String idEmprestimo) throws Exception {

		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(),
				Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE
				.getMensagem());
		assertStringNaoVazia(idEmprestimo, Mensagem.ID_EMPRESTIMO_INVALIDO.getMensagem(),
				Mensagem.ID_EMPRESTIMO_INVALIDO.getMensagem());
		asserteTrue(EmprestimoRepositorio.existeEmprestimo(idEmprestimo),
				Mensagem.EMPRESTIMO_INEXISTENTE.getMensagem());

		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		EmprestimoIF emp = EmprestimoRepositorio.recuperarEmprestimo(idEmprestimo);
		asserteTrue(emp.getEmprestador().equals(usuario),
				Mensagem.EMPRESTIMO_DEVOLUCAO_CONFIRMADA_APENAS_EMPRESTADOR.getMensagem());
		// asserteTrue(!emp.getEstadoEnum().equals(EmprestimoEstado.CONFIRMADO),
		// Mensagem.TERMINO_EMPRESTIMO_JA_CONFIRMADO.getMensagem());

		emp.confirmarTerminoEmprestimo();
		liberaItem(idSessao, idEmprestimo);
		// verificar a situacao do emprestimo
		if (emp.getSituacao().equals("Completado")) {
			usuario.incrementaReputacao();
		} else if (emp.getSituacao().equals("Cancelado")) {
			usuario.decrementaReputacao();
		}

	}

	/**
	 * Seta um item como disponivel e envia mensagens privadas para todos os
	 * interessados nele.
	 * 
	 * @param idSessao 
	 * @param idEmprestimo 
	 * @throws Exception 
	 */
	private void liberaItem(String idSessao, String idEmprestimo) throws Exception {
		EmprestimoIF emprestimo = EmprestimoRepositorio.recuperarEmprestimo(idEmprestimo);
		UsuarioIF dono = emprestimo.getEmprestador();
		ItemIF item = emprestimo.getItem();
		
		for (UsuarioIF interessado : item.getInteresasados()) {
			enviarMensagem(idSessao, interessado.getLogin(), "O item " + item.getNome()
					+ " do usuário " + dono.getNome() + " está disponível",
					"Agora você pode requisitar o empréstimo do " + item.getNome());
		}
		item.removeTodosInteressados();
		item.setDisponibilidade(true);
		
	}

	@Override
	public String enviarMensagem(String idSessao, String destinatario, String assunto,
			String mensagem) throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(),
				Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE
				.getMensagem());
		assertStringNaoVazia(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem(),
				Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		UsuarioIF amigo = usuario.possuoAmigoComEsteLogin(destinatario);
		asserteTrue( amigo != null, Mensagem.DESTINATARIO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem(), Mensagem.ASSUNTO_INVALIDO.getMensagem());
		assertStringNaoVazia(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem(), Mensagem.MENSAGEM_INVALIDA.getMensagem());
		
		return usuario.enviarMensagemOffTopic(destinatario, assunto, mensagem);
		
	}

	@Override
	public String enviarMensagem(String idSessao, String destinatario,
			String assunto, String mensagem, String idRequisicaoEmprestimo)
			throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem(), Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		UsuarioIF amigo = usuario.possuoAmigoComEsteLogin(destinatario);

		asserteTrue(amigo != null, Mensagem.DESTINATARIO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem(),
				Mensagem.ASSUNTO_INVALIDO.getMensagem());
		assertStringNaoVazia(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem(),
				Mensagem.MENSAGEM_INVALIDA.getMensagem());
		assertStringNaoVazia(idRequisicaoEmprestimo,
				Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem(),
				Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		asserteTrue(
				EmprestimoRepositorio.existeEmprestimo(idRequisicaoEmprestimo.trim()),
				Mensagem.ID_REQUISICAO_EMP_INEXISTENTE.getMensagem());
		return usuario.enviarMensagemEmprestimo(destinatario, assunto, mensagem,
				idRequisicaoEmprestimo);
	}

	@Override
	public synchronized String lerTopicos(String idSessao, String tipo) throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(),
				Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE
				.getMensagem());
		assertStringNaoVazia(tipo, Mensagem.TIPO_INVALIDO.getMensagem(),
				Mensagem.TIPO_INVALIDO.getMensagem());

		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		return usuario.lerTopicos(tipo);
		
	}

	@Override
	public String lerMensagens(String idSessao, String idTopico) throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(),
				Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE
				.getMensagem());
		assertStringNaoVazia(idTopico, Mensagem.TOPICO_ID_INVALIDO.getMensagem(),
				Mensagem.TOPICO_ID_INVALIDO.getMensagem());
		asserteTrue(ChatRepositorio.existeConversa(idTopico),
				Mensagem.TOPICO_ID_INEXISTENTE.getMensagem());

		ChatIF conversa = ChatRepositorio.recuperarConversa(idTopico);
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		if(!usuario.equals(conversa.getRemetente()) && !usuario.equals(conversa.getDestinatario()))
			throw new Exception(Mensagem.USUARIO_SEM_PERMISSAO_LEITURA_TOPICO.getMensagem());
		
		return conversa.getConversa();
	}

	@Override
	public void registraInteresse(String idSessao, String idItem)
			throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(), Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(ItemRepositorio.existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		usuario.registrarInteressePorItem(idItem);
		
	}
	
	@Override
	public void zerarSistema() {
		//Zerar o BD
		autenticacao.zerarSistema();
		
		ChatRepositorio.zerarRepositorio();
		EmprestimoRepositorio.zerarRepositorio();
		ItemRepositorio.zerarRepositorio();
		// Limpar Gerenciamentos
		RelacionamentosUsuarios.getInstance().zerarSistema();
		BancoDeEmprestimos.getInstance().zerarSistema();
		AcervoDeItens.getInstance().zerarSistema();
		Correio.getInstance().zerarSistema();
		GerenciadorDeNotificacoes.getInstance().zerarSistema();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#encerrarSistema()
	 */
	@Override
	public void encerrarSistema() {
		// Salva dados em persistencia e encerra.
		autenticacao.encerrarSistema();
		// System.exit(0);
	}

	@Override
	public String pesquisarItem(String idSessao, String chave, String atributo,
			String tipoOrdenacao, String criterioOrdenacao) throws Exception {

		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(),
				Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE
				.getMensagem());
		assertStringNaoVazia(chave, Mensagem.CHAVE_INVALIDA.getMensagem(),
				Mensagem.CHAVE_INVALIDA.getMensagem());
		assertStringNaoVazia(atributo, Mensagem.ATRIBUTO_INVALIDO.getMensagem(),
				Mensagem.ATRIBUTO_INVALIDO.getMensagem());

		if (!atributo.trim().equalsIgnoreCase("nome")
				&& !atributo.trim().equalsIgnoreCase("descricao")
				&& !atributo.trim().equalsIgnoreCase("categoria")
				&& !atributo.trim().equalsIgnoreCase("descrição")) {
			throw new Exception(Mensagem.ATRIBUTO_INEXISTENTE.getMensagem());
		}

		assertStringNaoVazia(tipoOrdenacao, Mensagem.ORDENACAO_TIPO_INVALIDO
				.getMensagem(), Mensagem.ORDENACAO_TIPO_INVALIDO.getMensagem());
		if (!tipoOrdenacao.trim().equalsIgnoreCase("crescente")
				&& !tipoOrdenacao.trim().equalsIgnoreCase("decrescente")) {
			throw new Exception(Mensagem.ORDENACAO_TIPO_INEXISTENTE.getMensagem());
			
		}
		
		assertStringNaoVazia(criterioOrdenacao, Mensagem.ORDENACAO_CRITERIO_INVALIDO.getMensagem(), Mensagem.ORDENACAO_CRITERIO_INVALIDO.getMensagem());
		if( !criterioOrdenacao.trim().equalsIgnoreCase("dataCriacao") &&
				!criterioOrdenacao.trim().equalsIgnoreCase("reputacao")){
			throw new Exception(Mensagem.ORDENACAO_CRITERIO_INEXISTENTE.getMensagem());
			
		}
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		
		return usuario.pesquisarItem(chave, atributo, tipoOrdenacao, criterioOrdenacao);
	}

	@Override
	public void desfazerAmizade(String idSessao, String amigo) throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(),
				Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE
				.getMensagem());
		assertStringNaoVazia(amigo, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		asserteTrue(Autenticacao.existeUsuario(amigo), Mensagem.USUARIO_INEXISTENTE
				.getMensagem());

		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		if(!usuario.ehAmigo(amigo)){
			throw new Exception(Mensagem.AMIZADE_INEXISTENTE.getMensagem());
		}
		
		usuario.desfazerAmizade(amigo);
		
	}

	@Override
	public void apagarItem(String idSessao, String idItem) throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(),
				Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE
				.getMensagem());
		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(),
				Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(ItemRepositorio.existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE
				.getMensagem());

		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		asserteTrue(usuario.esteItemMePertence(idItem),
				Mensagem.SEM_PERMISSAO_APAGAR_ITEM.getMensagem());
		ItemIF item = ItemRepositorio.recuperarItem(idItem.trim());
		asserteTrue(item.estahDisponivel(), Mensagem.NAO_PODE_EMPRESTAR_ITEM_EMPRESTADO.getMensagem());
		
		usuario.apagarItem( idItem.trim() );
		ItemRepositorio.removerItem(idItem);
		
	}

	@Override
	public String getRanking(String idSessao, String categoria) throws Exception {

		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(),
				Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE
				.getMensagem());
		assertStringNaoVazia(categoria, Mensagem.CATEGORIA_INVALIDA.getMensagem(),
				Mensagem.CATEGORIA_INVALIDA.getMensagem());
		if (!categoria.trim().equalsIgnoreCase("global")
				&& !categoria.trim().equalsIgnoreCase("amigos")) {
			throw new Exception(Mensagem.CATEGORIA_INEXISTENTE.getMensagem());
		}
		
		StringBuffer saidaRanking = new StringBuffer();
		List<UsuarioIF> listaUsuarios = new LinkedList<UsuarioIF>();

		if (categoria.trim().equalsIgnoreCase("global")) {
			Iterator<UsuarioIF> iteradorColecaoUsuarios = autenticacao.getListaUsuarios()
					.iterator();
			while (iteradorColecaoUsuarios.hasNext()) {
				listaUsuarios.add(iteradorColecaoUsuarios.next());
			}
			Collections.sort(listaUsuarios, new ReputacaoUsuarioComparator());
			Collections.reverse(listaUsuarios);
			
			Iterator<UsuarioIF> iteraListUsuarios = listaUsuarios.iterator();
			while(iteraListUsuarios.hasNext()){
				saidaRanking.append(iteraListUsuarios.next().getLogin()+"; ");
			}

		} else if (categoria.trim().equalsIgnoreCase("amigos")) {
			UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao.trim());
			listaUsuarios = usuario.getListaAmigos();
			// Tenho de ser adicionado na lista para ser comparado com eles
			listaUsuarios.add(usuario);
			Collections.sort(listaUsuarios, new ReputacaoUsuarioComparator());
			Collections.reverse(listaUsuarios);
			
			Iterator<UsuarioIF> iteraListUsuarios = listaUsuarios.iterator();
			while (iteraListUsuarios.hasNext()) {
				saidaRanking.append(iteraListUsuarios.next().getLogin() + "; ");
			}
		}
		if (saidaRanking.toString().trim().equals(""))
			return "";
		return saidaRanking.toString().trim().substring(0,
				saidaRanking.toString().trim().length() - 1);
	}

	@Override
	public String historicoAtividades(String idSessao) throws Exception {
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		String result = null;
		try {
			result = usuario.getHistoricoToString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public String historicoAtividadesConjunto(String idSessao) throws Exception {
		return GerenciadorDeNotificacoes.getInstance().getHistoricoAtividadesConjunto(
				autenticacao.getUsuarioPeloIDSessao(idSessao));
	}

	@Override
	public String publicarPedido(String idSessao, String nomeItem,
			String descricaoItem) throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		//PUBLICACAO_ID_INVALIDO
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(nomeItem, Mensagem.NOME_INVALIDO.getMensagem(), Mensagem.NOME_INVALIDO.getMensagem());
		assertStringNaoVazia(descricaoItem, Mensagem.DESCRICAO_INVALIDA.getMensagem(), Mensagem.DESCRICAO_INVALIDA.getMensagem());
		return autenticacao.getUsuarioPeloIDSessao(idSessao).publicarPedido(nomeItem, descricaoItem);
	}

	@Override
	public void oferecerItem(String idSessao, String idPublicacaoPedido,
			String idItem) throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(idPublicacaoPedido, Mensagem.PUBLICACAO_ID_INVALIDO.getMensagem(), Mensagem.PUBLICACAO_ID_INVALIDO.getMensagem());
		
		autenticacao.getUsuarioPeloIDSessao(idSessao).oferecerItem(idPublicacaoPedido, idItem);
		
	}

	@Override
	public void republicarPedido(String idSessao, String idPublicacaoPedido) throws Exception{
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		autenticacao.getUsuarioPeloIDSessao(idSessao).republicarPedido(idPublicacaoPedido);
		
	}

	@Override
	public String localizarUsuario(String idSessao) throws Exception {
		List<UsuarioIF> usuarios = autenticacao.getUsuarios(autenticacao
				.getUsuarioPeloIDSessao(idSessao));
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		RefCoordenadas coordenadas = new RefCoordenadas(usuario.getLatitude(), usuario.getLongitude());
		Collections.sort(usuarios, new ComparaDistancia(coordenadas));
		String saida = "";
		for (int i = 0; i < usuarios.size(); i++) {
			saida += usuarios.get(i).getNome() + " - " + usuarios.get(i).getEndereco();
			if (i != usuarios.size() - 1) {
				saida += "; ";
			}
		}
		if (saida.trim().equals(""))
			saida = Mensagem.PALAVRA_CHAVE_INEXISTENTE.getMensagem();
		return saida;
	}

	@Override
	public void criarUsuario(String login, String senha, String nome,
			String endereco) throws Exception {
		autenticacao.criarUsuario(login, senha, nome, endereco);
		
	}

	@Override
	public void cadastrarEmailRedefinicaoSenha(String idSessao, String email)
			throws Exception {
		
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(email, Mensagem.EMAIL_INVALIDO.getMensagem(), Mensagem.EMAIL_INVALIDO.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		usuario.cadastrarEmailRedefinicaoSenha(email);
		
	}

	@Override
	public void alterarSenha(String idSessao, String senhaAtual,
			String senhaNova) throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(senhaAtual, Mensagem.SENHA_ATUAL_INVALIDA.getMensagem(), Mensagem.SENHA_ATUAL_INVALIDA.getMensagem());
		assertStringNaoVazia(senhaNova, Mensagem.SENHA_NOVA_INVALIDA.getMensagem(), Mensagem.SENHA_NOVA_INVALIDA.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		usuario.alterarSenha(senhaAtual, senhaNova);
		
	}

}
