package testesdeaceitacao;

import controlador.Emprestimus;
import controlador.EmprestimusIF;

/**
 * UFCG - CEEI - DSC- SI1
 * Projeto da Disciplina Sistema de Informação 1 - 2011.2
 * 
 * Professor: Nazareno 
 * 
 * Alunos: Samir Trajano Feitosa - samircc20092@gmail.com
 *                 nome completo - email
 * 
 */

public class UserFacede1 {
	
	private EmprestimusIF sistema = new Emprestimus();
	
	/**
	 * Cria usuario a partir de um login, um nome e um endereco.
	 * @param login
	 *     O login do usuario.
	 * @param nome
	 *     O nome do usuario.
	 * @param endereco
	 *     O endereco do usuario.
	 */
	public void criarUsuario(String login, String nome, String endereco) {
		sistema.criarUsuario(login, nome, endereco);
	}

	/**
	 * Abre uma sessao para o usuario.
	 * @param login
	 *     O login do usuario.
	 * @return
	 *     O id de sessao para o usuario.
	 */
	public int abrirSessao(String login) {
		return sistema.abrirSessao(login);
	}

	/**
	 * Recupera um atributo do usuario.
	 * @param login
	 *     O login do usuario.
	 * @param atributo
	 * 	   O atributo do usuario.
	 * @return
	 *     Uma representacao de um atributo do usuario.
	 */
	public String getAtributoUsuario(String login, String atributo) {
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

