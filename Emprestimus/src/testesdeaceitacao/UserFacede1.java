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

}




//package testes;
//
//import controller.GerenciadorDeSalas;
//
//public class UserFacede1 {
//	public GerenciadorDeSalas gerenciadorSala =  GerenciadorDeSalas.getInstance();
//
//	/**
//	 * Metodo adiciona a patir de dados fornecidos uma sala ao sistema.
//	 * 
//	 * @param id
//	 * @param capacidade
//	 * @param finalidade
//	 * @param tipo
//	 * @throws Exception
//	 *             Caso algum parametro esteja incorreto.
//	 */
//	public void adicionarSala(String id, int capacidade, String finalidade,
//			String tipo) throws Exception {
//		gerenciadorSala.adicionarSala(id, capacidade, finalidade, tipo);
//	}
//
//	/**
//	 * Metodo adiciona a partir de dados fornecidos uma sala ao sistema.
//	 * 
//	 * @param id
//	 * @param capacidade
//	 * @param finalidade
//	 * @param tipo
//	 * @param apelido
//	 * @throws Exception
//	 *             Caso algum parametro esteja incorreto
//	 */
//	public void adicionarSala(String id, int capacidade, String finalidade,
//			String tipo, String apelido) throws Exception {
//		gerenciadorSala
//				.adicionarSala(id, capacidade, finalidade, tipo, apelido);
//	}
//
//	/**
//	 * Metodo adiciona a partir de dados fornecidos uma sala ao sistema.
//	 * 
//	 * @param id
//	 * @param capacidade
//	 * @param finalidade
//	 * @param tipo
//	 * @param apelido
//	 * @param aberto
//	 * @throws Exception
//	 *             Caso algum parametro esteja incorreto
//	 */
//	public void adicionarSala(String id, int capacidade, String finalidade,
//			String tipo, String apelido, boolean aberto) throws Exception {
//		gerenciadorSala.adicionarSala(id, capacidade, finalidade, tipo,
//				apelido, aberto);
//	}
//
//	/**
//	 * Metodo que retorna o valor do atributo de uma sala apartir do seu id.
//	 * 
//	 * @param idSala
//	 * @param atributo
//	 * @return String valor do atributo
//	 * @throws Exception
//	 *             Caso algum parametro esteja incorreto ou nao existe sala
//	 *             cadastrada com o id fornecido
//	 */
//	public String getAtributoSala(String idSala, String atributo)
//			throws Exception {
//		return gerenciadorSala.getAtributoSala(idSala, atributo);
//	}
//
//	/**
//	 * Metodo que zera o Sistema.
//	 * 
//	 */
//	public void zerarSistema() {
//		gerenciadorSala.zerarSistema();
//	}
//
//	/**
//	 * Metodo que encerra o Sistema.
//	 * 
//	 */
//	public void encerrarSistema() {
//		gerenciadorSala.encerrarSistema();
//	}
//
//}
