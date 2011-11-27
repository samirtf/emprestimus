package testes.aceitacao.fachadas;

import iu.web.server.Emprestimus;
import iu.web.server.EmprestimusIF;



/**
 * UFCG - CEEI - DSC- SI1 Projeto da Disciplina Sistema de Informa��o 1 - 2011.2
 * 
 * Professor: Nazareno
 * 
 * Alunos: Samir Trajano Feitosa - samircc20092@gmail.com nome completo - email
 * 
 */

public class UserFacade01 {

	public EmprestimusIF sistema = Emprestimus.getInstance();

	/**
	 * Cria usuario a partir de um login, um nome e um endereco.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param nome
	 *            O nome do usuario.
	 * @param endereco
	 *            O endereco do usuario.
	 */
	public void criarUsuario(String login, String nome, String endereco) throws Exception {
		sistema.criarUsuario(login, nome, endereco);
	}

	/**
	 * Abre uma sessao para o usuario.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @return O id de sessao para o usuario.
	 */
	public String abrirSessao(String login) throws Exception {
		return sistema.abrirSessao(login);
	}

	/**
	 * Recupera um atributo do usuario.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param atributo
	 *            O atributo do usuario.
	 * @return Uma representacao de um atributo do usuario.
	 */
	public String getAtributoUsuario(String login, String atributo) throws Exception {
		return sistema.getAtributoUsuario(login, atributo);
	}

	/**
	 * Metodo que zera o Sistema.
	 * 
	 */
	public void zerarSistema() {
		sistema.zerarSistema();
	}

	/**
	 * Metodo que encerra o Sistema.
	 * 
	 */
	public void encerrarSistema() {
		sistema.encerrarSistema();
	}

}
