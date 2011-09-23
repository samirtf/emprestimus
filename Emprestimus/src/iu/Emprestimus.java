package iu;

import static sistema.utilitarios.Validador.assertNaoNulo;
import static sistema.utilitarios.Validador.assertStringNaoVazia;
import static sistema.utilitarios.Validador.asserteTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import sistema.autenticacao.Autenticacao;
import sistema.emprestimo.EmprestimoEstado;
import sistema.emprestimo.EmprestimoIF;
import sistema.excecoes.ArgumentoInvalidoException;
import sistema.mensagem.Chat;
import sistema.mensagem.ChatIF;
import sistema.persistencia.ChatRepositorio;
import sistema.persistencia.EmprestimoRepositorio;
import sistema.persistencia.ItemRepositorio;
import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;
import sistema.utilitarios.ValidadorString;

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
	 * Metodo que faz parte do padrao Singleton e serve para retornar uma
	 * instancia única do gerenciador de sala
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

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#criarUsuario(java.lang.String, java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void criarUsuario(String login, String nome, String endereco) throws Exception{
		
        autenticacao.criarUsuario(login, nome, endereco);
        
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#abrirSessao(java.lang.String)
	 */
	@Override
	public String abrirSessao(String login) throws Exception{
		
	    return autenticacao.abrirSessao(login);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getAtributo(java.lang.String, java.lang.String)
	 */
	@Override
	public String getAtributoUsuario(String login, String atributo) throws Exception{
		String valor = null;
		
			valor = autenticacao.getAtributoUsuario(login, atributo);
		
		return valor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#cadastrarItem(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public String cadastrarItem(String idSessao, String nome, String descricao,
			String categoria) throws Exception {
		
		assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		assertNaoNulo(nome, Mensagem.NOME_INVALIDO.getMensagem());
		assertNaoNulo(categoria, Mensagem.CATEGORIA_INVALIDA.getMensagem());
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		assertStringNaoVazia(nome, Mensagem.NOME_INVALIDO.getMensagem());
		assertStringNaoVazia(categoria, Mensagem.CATEGORIA_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		
		return usuario.cadastrarItem(nome, descricao, categoria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getAtributoItem(java.lang.String, java.lang.String)
	 */
	@Override
	public String getAtributoItem( String idItem, String atributo ) throws Exception {
		
		assertNaoNulo(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem());
		assertStringNaoVazia(idItem.trim(), Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(ItemRepositorio.existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		
		assertNaoNulo(atributo, Mensagem.ATRIBUTO_INVALIDO.getMensagem());
		assertStringNaoVazia(atributo.trim(), Mensagem.ATRIBUTO_INVALIDO.getMensagem());
		
		atributo = atributo.toLowerCase().trim();
		String str = ItemRepositorio.getAtributoItem(idItem, atributo);
		return str;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#localizarUsuario(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public String localizarUsuario(String idSessao, String chave,
			String atributo) throws Exception {
		autenticacao.getUsuarioPeloIDSessao(idSessao);
		ValidadorString.pegaString(Mensagem.PALAVRA_CHAVE_INVALIDA.getMensagem(), chave);
		ValidadorString.pegaString(Mensagem.ATRIBUTO_INVALIDO.getMensagem(), atributo);
		atributo = atributo.toLowerCase();
		List<UsuarioIF> users;
		UsuarioIF eu = autenticacao.getUsuarioPeloIDSessao(idSessao);
		if(atributo.equals("nome")) {
//			if( autenticacao.getUsuarioPeloIDSessao(idSessao).getNome().toLowerCase().contains(chave.toLowerCase()) ){
//				return Mensagem.PALAVRA_CHAVE_INEXISTENTE.getMensagem();
//			}
			users = autenticacao.getUsuarioNome(chave);
			if(users.contains(eu)) users.remove(eu);
		} else if(atributo.equals("endereco")) {
			users = autenticacao.getUsuarioEndereco(chave);
			if(users.contains(eu)) users.remove(eu);
		} else {
			throw new Exception(Mensagem.ATRIBUTO_INEXISTENTE.getMensagem());
		}
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#requisitarAmizade(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void requisitarAmizade(String idSessao, String login) throws Exception {
		Validador.assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.assertStringNaoVazia(idSessao.trim(), Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.assertNaoNulo(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(login.trim(), Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		Validador.asserteTrue(Autenticacao.existeUsuario(login), Mensagem.USUARIO_INEXISTENTE.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		usuario.requisitarAmizade(login.trim());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getRequisicoesDeAmizade(java.lang.String)
	 */
	@Override
	public String getRequisicoesDeAmizade(String idSessao) throws Exception {
		Validador.assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.assertStringNaoVazia(idSessao.trim(), Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		List<UsuarioIF> usuarios = usuario.getQueremSerMeusAmigos();
		String saida ="";
		for (int i = 0; i<usuarios.size(); i++) {
			saida += usuarios.get(i).getLogin();
			if(i != usuarios.size() -1) {
				saida += "; ";
			}
		}
		if (saida.trim().equals(""))
			saida = Mensagem.NAO_HA_REQUISICOES.getMensagem();
		return saida;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#aprovarAmizade(java.lang.String, java.lang.String)
	 */
	@Override
	public void aprovarAmizade(String idSessao, String login) throws Exception {
		Validador.assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.assertStringNaoVazia(idSessao.trim(), Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.asserteTrue(autenticacao.existeIdSessao(idSessao.trim()), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		Validador.assertNaoNulo(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(login.trim(), Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.asserteTrue(Autenticacao.existeUsuario(login.trim()), Mensagem.USUARIO_INEXISTENTE.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		usuario.aprovarAmizade(login);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#ehAmigo(java.lang.String, java.lang.String)
	 */
	@Override
	public String ehAmigo(String idSessao, String login) throws Exception {
		Validador.assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.assertStringNaoVazia(idSessao.trim(), Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.assertNaoNulo(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(login.trim(), Mensagem.LOGIN_INVALIDO.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		asserteTrue(Autenticacao.existeUsuario(login.trim()), Mensagem.USUARIO_INEXISTENTE.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		if(usuario.ehAmigo(login)){
			return "true";
		}return "false";
				
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getAmigos(java.lang.String)
	 */
	@Override
	public String getAmigos(String idSessao) throws Exception {
		Validador.assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		
		return autenticacao.getUsuarioPeloIDSessao(idSessao).getAmigos();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getAmigos(java.lang.String, java.lang.String)
	 */
	@Override
	public String getAmigos(String idSessao, String login) throws Exception {
		Validador.assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		Validador.assertNaoNulo(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.asserteTrue(autenticacao.existeUsuario(login), Mensagem.USUARIO_INEXISTENTE.getMensagem());
		
		return Autenticacao.getUsuarioPorLogin(login).getAmigos();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getItens(java.lang.String)
	 */
	@Override
	public String getItens(String idSessao) throws Exception{
		Validador.assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		
		return autenticacao.getUsuarioPeloIDSessao(idSessao).getListaItens();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getItens(java.lang.String, java.lang.String)
	 */
	@Override
	public String getItens(String idSessao, String login) throws Exception {
		Validador.assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		Validador.assertNaoNulo(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.asserteTrue(autenticacao.existeUsuario(login), Mensagem.USUARIO_INEXISTENTE.getMensagem());
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
		Validador.assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.assertStringNaoVazia(idSessao.trim(), Mensagem.SESSAO_INVALIDA.getMensagem());
		Validador.asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		Validador.assertNaoNulo(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(idItem.trim(), Mensagem.ID_ITEM_INVALIDO.getMensagem());
		Validador.asserteTrue(ItemRepositorio.existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		Validador.asserteTrue(duracao > 0, Mensagem.EMPRESTIMO_DURACAO_INVALIDA.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		return usuario.requisitarEmprestimo(idItem, duracao);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#aprovarEmprestimo(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public String aprovarEmprestimo(String idSessao, String idRequisicaoEmprestimo) throws Exception {
		assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao.trim()), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		assertNaoNulo(idRequisicaoEmprestimo, Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		assertStringNaoVazia(idRequisicaoEmprestimo, Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		asserteTrue(EmprestimoRepositorio.existeEmprestimo(idRequisicaoEmprestimo.trim()), Mensagem.ID_REQUISICAO_EMP_INEXISTENTE.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao.trim());
		String saida = usuario.aprovarEmprestimo(idRequisicaoEmprestimo);
		return saida;
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getEmprestimos(java.lang.String, java.lang.String)
	 */
	@Override
	public String getEmprestimos(String idSessao, String tipo) throws Exception {
		assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao.trim()), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		assertNaoNulo(tipo, Mensagem.TIPO_INVALIDO.getMensagem());
		assertStringNaoVazia(tipo, Mensagem.TIPO_INVALIDO.getMensagem());
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao.trim());
		return usuario.getEmprestimos(tipo);
		
	}

	

	@Override
	public synchronized void devolverItem(String idSessao, String idEmprestimo)
			throws Exception {
		assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		
		assertNaoNulo(idEmprestimo, Mensagem.ID_EMPRESTIMO_INVALIDO.getMensagem());
		assertStringNaoVazia(idEmprestimo, Mensagem.ID_EMPRESTIMO_INVALIDO.getMensagem());
		asserteTrue(EmprestimoRepositorio.existeEmprestimo(idEmprestimo), Mensagem.EMPRESTIMO_INEXISTENTE.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		EmprestimoIF emp = EmprestimoRepositorio.recuperarEmprestimo(idEmprestimo);
		asserteTrue(emp.getBeneficiado().equals(usuario),Mensagem.EMPRESTIMO_DEVOLUCAO_APENAS_BENEFICIADO.getMensagem());
		asserteTrue(!emp.getTipoEstado().equals(EmprestimoEstado.AGUARDANDO_CONFIRMACAO_DEVOLUCAO), Mensagem.ITEM_JA_DEVOLVIDO.getMensagem());
		asserteTrue(!emp.getTipoEstado().equals(EmprestimoEstado.CONFIRMADO), Mensagem.ITEM_JA_DEVOLVIDO.getMensagem());
		
		
		emp.setEstadoAguardandoConfirmacaoDevolucao();
		
	}

	@Override
	public synchronized void requisitarDevolucao(String idSessao, String idEmprestimo)
			throws Exception {
		assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		assertNaoNulo(idEmprestimo, Mensagem.ID_EMPRESTIMO_INVALIDO.getMensagem());
		assertStringNaoVazia(idEmprestimo, Mensagem.ID_EMPRESTIMO_INVALIDO.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		EmprestimoIF emprestimo = EmprestimoRepositorio.recuperarEmprestimo(idEmprestimo);
		
		asserteTrue(emprestimo.getEmprestador().equals(usuario),"O usuário não tem permissão para requisitar a devolução deste item");
		asserteTrue(emprestimo.getEstado().equalsIgnoreCase(EmprestimoEstado.ANDAMENTO.getNome())
				|| emprestimo.getEstadoEnum().equals(EmprestimoEstado.REQUISITADO_PARA_DEVOLUCAO)
						|| emprestimo.getEstadoEnum().equals(EmprestimoEstado.ACEITO), Mensagem.TERMINO_EMPRESTIMO_JA_CONFIRMADO.getMensagem());
		asserteTrue(!emprestimo.getEstadoEnum().equals(EmprestimoEstado.ANDAMENTO), Mensagem.DEVOLUCAO_JA_REQUISITADA.getMensagem());
		asserteTrue(!emprestimo.getEstadoEnum().equals(EmprestimoEstado.REQUISITADO_PARA_DEVOLUCAO), Mensagem.DEVOLUCAO_JA_REQUISITADA.getMensagem());
		asserteTrue(!emprestimo.getEstadoEnum().equals(EmprestimoEstado.AGUARDANDO_CONFIRMACAO_DEVOLUCAO), Mensagem.ITEM_JA_DEVOLVIDO.getMensagem());
		
		dataCorrente = new GregorianCalendar();
		dataCorrente.add(GregorianCalendar.DATE, diasExtras);
		dataCorrente.add(GregorianCalendar.MILLISECOND, -3);
		
		if (dataCorrente.compareTo(emprestimo.getDataDeDevolucao()) <= 0) {
			emprestimo.setEstadoRequisitadoParaDevolucao();
		} else {
			emprestimo.setEstadoAndamento();
		}
	}
	
	@Override
	public synchronized void adicionarDias(String dias) throws Exception {
		assertNaoNulo(dias, Mensagem.ATRIBUTO_INVALIDO.getMensagem());
		int numDias = Integer.valueOf(dias);
		diasExtras += numDias;
		
		
		
	}

	@Override
	public void confirmarTerminoEmprestimo(String idSessao, String idEmprestimo)
			throws Exception {
		
		assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		
		assertNaoNulo(idEmprestimo, Mensagem.ID_EMPRESTIMO_INVALIDO.getMensagem());
		assertStringNaoVazia(idEmprestimo, Mensagem.ID_EMPRESTIMO_INVALIDO.getMensagem());
		asserteTrue(EmprestimoRepositorio.existeEmprestimo(idEmprestimo), Mensagem.EMPRESTIMO_INEXISTENTE.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		EmprestimoIF emp = EmprestimoRepositorio.recuperarEmprestimo(idEmprestimo);
		asserteTrue(emp.getEmprestador().equals(usuario),Mensagem.EMPRESTIMO_DEVOLUCAO_CONFIRMADA_APENAS_EMPRESTADOR.getMensagem());
		asserteTrue(!emp.getEstado().equalsIgnoreCase(EmprestimoEstado.CONFIRMADO.getNome()), Mensagem.TERMINO_EMPRESTIMO_JA_CONFIRMADO.getMensagem());
		
		emp.setEstadoConfirmado();
		
	}

	@Override
	public String enviarMensagem(String idSessao, String destinatario,
			String assunto, String mensagem) throws Exception {
		assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		
		assertNaoNulo(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		assertStringNaoVazia(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		UsuarioIF amigo = usuario.possuoAmigoComEsteLogin(destinatario);
		asserteTrue( amigo != null, Mensagem.DESTINATARIO_INEXISTENTE.getMensagem());
		
		assertNaoNulo(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem());
		assertStringNaoVazia(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem());
		
		assertNaoNulo(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem());
		assertStringNaoVazia(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem());
		
		return usuario.enviarMensagemOffTopic(destinatario, assunto, mensagem);
		
	}

	@Override
	public String enviarMensagem(String idSessao, String destinatario,
			String assunto, String mensagem, String idRequisicaoEmprestimo)
			throws Exception {
		assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		
		assertNaoNulo(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		assertStringNaoVazia(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		UsuarioIF amigo = usuario.possuoAmigoComEsteLogin(destinatario);
		asserteTrue( amigo != null, Mensagem.DESTINATARIO_INEXISTENTE.getMensagem());
		
		assertNaoNulo(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem());
		assertStringNaoVazia(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem());
		
		assertNaoNulo(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem());
		assertStringNaoVazia(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem());
		
		assertNaoNulo(idRequisicaoEmprestimo, Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		assertStringNaoVazia(idRequisicaoEmprestimo, Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		asserteTrue(EmprestimoRepositorio.existeEmprestimo(idRequisicaoEmprestimo.trim()), 
				Mensagem.ID_REQUISICAO_EMP_INEXISTENTE.getMensagem());
		
		return usuario.enviarMensagemEmprestimo(destinatario, assunto, mensagem, idRequisicaoEmprestimo);
	}

	@Override
	public String lerTopicos(String idSessao, String tipo) throws Exception {
		assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		
		assertNaoNulo(tipo, Mensagem.TIPO_INVALIDO.getMensagem());
		assertStringNaoVazia(tipo, Mensagem.TIPO_INVALIDO.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		return usuario.lerTopicos(tipo);
		
	}

	@Override
	public String lerMensagens(String idSessao, String idTopico)
			throws Exception {
		assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(autenticacao.existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		assertNaoNulo(idTopico, Mensagem.TOPICO_ID_INVALIDO.getMensagem());
		assertStringNaoVazia(idTopico, Mensagem.TOPICO_ID_INVALIDO.getMensagem());
		asserteTrue(ChatRepositorio.existeConversa(idTopico), Mensagem.TOPICO_ID_INEXISTENTE.getMensagem());
		
		ChatIF conversa = ChatRepositorio.recuperarConversa(idTopico);
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		if(!usuario.equals(conversa.getRemetente()) && !usuario.equals(conversa.getDestinatario()))
			throw new Exception(Mensagem.USUARIO_SEM_PERMISSAO_LEITURA_TOPICO.getMensagem());
		
		return conversa.getConversa();
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#zerarSistema()
	 */
	@Override
	public void zerarSistema() {
		//Salva os dados em persistencia
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#encerrarSistema()
	 */
	@Override
	public void encerrarSistema() {
		//Salva dados em persistencia

	}

}
