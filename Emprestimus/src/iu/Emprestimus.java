package iu;

import sistema.autenticacao.Autenticacao;
import sistema.autenticacao.AutenticacaoIF;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;
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

	AutenticacaoIF autenticacao = new Autenticacao();

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
		if(idSessao == null || idSessao.trim().equals("")) throw new Exception("Sessão inválida");
		if(!autenticacao.existeIdSessao(idSessao)) throw new Exception("Sessão inexistente");
		if(nome == null || nome.trim().equals("")) throw new Exception("Nome inválido");
		if(categoria == null || categoria.trim().equals("")) throw new Exception("Categoria inválida");
		UsuarioIF usuario = autenticacao.getUsuarioPeloIDSessao(idSessao);
		
		return usuario.cadastrarItem(nome, descricao, categoria);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getAtributoItem(java.lang.String, java.lang.String)
	 */
	@Override
	public String getAtributoItem(String idItem, String atributo) {
		if(!ValidadorString.validaCampoSemEspaco(atributo).equals(Mensagem.OK.getMensagem())) {
			// O atributo nao eh valido
		}
		atributo = atributo.toLowerCase();
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#localizarUsuario(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public String localizarUsuario(String idSessao, String chave,
			String atributo) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#requisitarAmizade(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void requisitarAmizade(String idSessao, String login) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getRequisicoesDeAmizade(java.lang.String)
	 */
	@Override
	public String getRequisicoesDeAmizade(String idSessao) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#aprovarAmizade(java.lang.String, java.lang.String)
	 */
	@Override
	public void aprovarAmizade(String idSessao, String login) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#ehAmigo(java.lang.String, java.lang.String)
	 */
	@Override
	public String ehAmigo(String idSessao, String login) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getAmigos(java.lang.String)
	 */
	@Override
	public String getAmigos(String idSessao) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getAmigos(java.lang.String, java.lang.String)
	 */
	@Override
	public String getAmigos(String idSessao, String login) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getItens(java.lang.String)
	 */
	@Override
	public String getItens(String idSessao) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getItens(java.lang.String, java.lang.String)
	 */
	@Override
	public String getItens(String idSessao, String login) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#requisitarEmprestimo(java.lang.String,
	 * java.lang.String, int)
	 */
	@Override
	public String requisitarEmprestimo(String idSessao, String idItem,
			int duracao) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#aprovarEmprestimo(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public void aprovarEmprestimo(String idSessao, String idRequisicaoEmprestimo) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getEmprestimos(java.lang.String, java.lang.String)
	 */
	@Override
	public String getEmprestimos(String idSessao, String tipo) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#zerarSistema()
	 */
	@Override
	public void zerarSistema() {
		autenticacao.zerarSistema();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#encerrarSistema()
	 */
	@Override
	public void encerrarSistema() {
		autenticacao.encerrarSistema();

	}

}
