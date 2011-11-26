package sistema.dao;

import sistema.emprestimo.EmprestimoIF;

public interface EmprestimoDAO {

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
