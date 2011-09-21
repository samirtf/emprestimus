package iu;

import static sistema.utilitarios.Validador.assertNaoNulo;
import static sistema.utilitarios.Validador.assertStringNaoVazia;
import static sistema.utilitarios.Validador.asserteTrue;

import java.util.List;

import sistema.autenticacao.Autenticacao;
import sistema.excecoes.ArgumentoInvalidoException;
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
	public String requisitarEmprestimo(String idSessao, String idItem,
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
	public void aprovarEmprestimo(String idSessao, String idRequisicaoEmprestimo) throws Exception {
		assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		assertNaoNulo(idRequisicaoEmprestimo, Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		assertStringNaoVazia(idRequisicaoEmprestimo, Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		usuario.aprovarEmprestimo(idRequisicaoEmprestimo);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getEmprestimos(java.lang.String, java.lang.String)
	 */
	@Override
	public String getEmprestimos(String idSessao, String tipo) throws Exception {
		assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		assertNaoNulo(tipo, Mensagem.TIPO_INVALIDO.getMensagem());
		assertStringNaoVazia(idSessao,
				Mensagem.SESSAO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(tipo, Mensagem.TIPO_INVALIDO.getMensagem());
		
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		return usuario.getEmprestimo(tipo);
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
