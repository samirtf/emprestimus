package iu.web.server.sistema.dao;

import iu.web.server.sistema.emprestimo.Conta;
import iu.web.server.sistema.emprestimo.EmprestimoIF;
import iu.web.server.sistema.persistencia.PersistenciaListener;
import iu.web.server.sistema.usuario.UsuarioIF;

/**
 * @author Mobile
 */
public interface BancoDeEmprestimosDAO extends PersistenciaListener {

	/**
	 * Inicia construção do DAO.
	 */
	public void iniciarDAO();
	
	/**
	 * Cadastra um usuario no banco de emprestimos
	 * 
	 * @param usuario - String
	 * 		Usuario a ser cadastrado
	 * 
	 * @throws Exception
	 * 		Caso o usuario já esteja cadastrado
	 */
	public void adicionaContaAoUsuario(String usuario) throws Exception;

	/**
	 * Remove um usuario no banco de emprestimos
	 * 
	 * @param usuario - String
	 * 		Usuario a ser cadastrado
	 * 
	 * @throws Exception
	 * 		Caso o usuario já esteja cadastrado
	 */
	public void removeContaDoUsuario(String usuario) throws Exception;

	/**
	 * @param login - String
	 * 		Login do usuario desejado
	 * 
	 * @return Conta
	 * 		Conta do usuario desejado
	 * 
	 * @throws Exception
	 */
	public Conta getConta(String login) throws Exception;
	
	/**
	 * Adiciona uma requisição de emprestimo de um amigo
	 * 
	 * @param login - String
	 * 		amigo desejado
	 * @param emp - EmprestimoIF
	 * 		emprestimo a ser cadastrado na requisição
	 * 
	 * @throws Exception
	 * 		Caso a conta fornecida seja inexistente
	 */
	public void adicionarRequisicaoEmprestimoEmEsperaDeAmigo(String login,
			EmprestimoIF emp) throws Exception;

	/**
	 * Requisita um emprestimo
	 * 
	 * @param login - String
	 * 		Loguin do usuario
	 * @param idItem - String
	 * 		ID do item desejado
	 * @param duracao - String
	 * 		Duração do emprestimo
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 * 		caso o seja fornecido algum parametro invalido ou o usuario não tenha permissao para requisitar um
	 * 			emprestimo do item em questão.
	 */
	public String requisitarEmprestimo(String login, String idItem,
			int duracao) throws Exception;

	public String getEmprestimos(String login, String tipo) throws Exception;

	/**
	 * Aproma um determinado emprestimo
	 * 
	 * @param login - String
	 * 		Loguin do usuario
	 * @param idRequisicaoEmprestimo String
	 * 		ID da requisição a ser aprovada
	 * 
	 * @return String
	 * 		ID do emprestimo
	 * 
	 * @throws Exception
	 */
	public String aprovarEmprestimo(String login,
			String idRequisicaoEmprestimo) throws Exception;

	/**
	 * Adiciona ao banco de emprestimos um emprestimo aceito por um amigo
	 * 
	 * @param login - String
	 * 		Loguin do usuario
	 * @param emp - EmprestimoIF
	 * 		Emprestimo a ser adicionado
	 * 
	 * @throws Exception
	 */
	public void emprestimoAceitoPorAmigo(String login, EmprestimoIF emp) throws Exception;
	
	/**
	 * Remove emprestimos requeridos por um amigo
	 * 
	 * @param login - String
	 * 		Loguin do usuario
	 * @param amigo - UsuarioIF
	 * 		Objeto referente ao amigo
	 */
	public void removerEmprestimosRequeridosPorAmigo(String login, UsuarioIF amigo);

	/**
	 * Remove emprestimos requeridos pelo proprio usuario
	 * 
	 * @param login - String
	 * 		Loguin do usuario
	 * @param amigo - UsuarioIF
	 * 		Objeto referente ao amigo
	 */
	public void removerEmprestimosRequeridosPorMim(String login, UsuarioIF amigo);

	/**
	 * Marca um determinado item como requesitado
	 * 
	 * @param login - String
	 * 		Loguin do usuario
	 * @param idItem - String
	 * 
	 * @return boolean
	 * 
	 * @throws Exception
	 */
	public boolean requisiteiEsteItem(String login, String idItem) throws Exception;

	/**
	 * Remove uma solicitação de emprestimo
	 * 
	 * @param login - String
	 * 		Loguin do usuario
	 * @param emprestimo - EmprestimoIF
	 * 		Emprestimo a ser removido
	 */
	public void removerMinhaSolicitacaoEmprestimo(String login, EmprestimoIF emprestimo);

	/**
	 * Reseta o sistema para as configurações iniciais
	 */
	public void zerarSistema();

}
