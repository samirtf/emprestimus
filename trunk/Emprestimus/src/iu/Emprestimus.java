package iu;

import sistema.autenticacao.Autenticacao;
import sistema.autenticacao.AutenticacaoIF;

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
	public String criarUsuario(String login, String nome, String endereco) {
		try {
			autenticacao.criarUsuario(login, nome, endereco);
			return null; //FIXME: O que eu vou retornar aqui?
		} catch (Exception e) {
			// TODO Tratar as exceções que podem ser lançadas aqui
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#abrirSessao(java.lang.String)
	 */
	@Override
	public String abrirSessao(String login) {
		try {
			autenticacao.abrirSessao(login);
		} catch (Exception e) {
			// TODO Tratar as exceções que podem ser lançadas aqui
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getAtributo(java.lang.String, java.lang.String)
	 */
	@Override
	public String getAtributo(String login, String atributo) {
		try {
			autenticacao.getAtributoUsuario(login, atributo);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#cadastrarItem(java.lang.String, java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public String cadastrarItem(String idSessao, String nome, String descricao,
			String categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see iu.EmprestimusIF#getAtributoItem(java.lang.String, java.lang.String)
	 */
	@Override
	public String getAtributoItem(String idItem, String atributo) {
		// TODO Auto-generated method stub
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
