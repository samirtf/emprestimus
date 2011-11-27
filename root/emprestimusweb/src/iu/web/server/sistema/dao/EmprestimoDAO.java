package iu.web.server.sistema.dao;

import java.io.Serializable;

import iu.web.server.sistema.emprestimo.EmprestimoIF;
import iu.web.server.sistema.persistencia.PersistenciaListener;

public interface EmprestimoDAO extends PersistenciaListener, Serializable{
	
	/**
	 * Inicia construção do DAO.
	 */
	public void iniciarDAO();

	/**
	 * Calcula o id do proximo emprestimo a ser cadastrado.
	 * 
	 * @return String
	 * 		O id do proximo emprestimo a ser cadastrado.
	 */
	public String geraIdProxNotificacao();

	/**
	 * Requisita um emprestimo
	 * @param EmprestimoIF
	 * 		Emprestimo a ser requisitado
	 * 
	 * @return String
	 * 		ID
	 * 
	 * @throws Exception
	 */
	public String requisitarEmprestimo(EmprestimoIF emp) throws Exception;

	/**
	 * Recupera um Emprestimo
	 * 
	 * @param String
	 * 		idEmprestimo
	 * 
	 * @return EmprestimoIF
	 * 		Emprestimo encontrado
	 * 
	 * @throws Exception
	 */
	public EmprestimoIF recuperarEmprestimo(String idEmprestimo) throws Exception;

	/**
	 * Retorna um atributo de um item
	 * 
	 * @param String
	 * 		idEmprestimo
	 * @param String
	 * 		atributo
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	public String getAtributoItem(String idEmprestimo, String atributo) throws Exception;

	/**
	 * Calcula a quantidade de emprestimos cadastrados.
	 * 
	 * @return int
	 * 		A quantidade de emprestimos cadastrados.
	 */
	public int qntEmprestimos();

	/**
	 * Verifica se um determinado emprestimos existe no repositorio.
	 * 
	 * @param String - idEmprestimo
	 *            Um idEmprestimo.
	 * @return boolean
	 * 		True - Se o emprestimo procurado existir.
	 */
	public boolean existeEmprestimo(String idEmprestimo);

	/**
	 * Remove um Emprestimo
	 * @param String
	 * 		idEmprestimo
	 */
	public void removerEmprestimo(String idEmprestimo);

	/**
	 * Zera o repositorio
	 */
	public void zerarRepositorio();
}
