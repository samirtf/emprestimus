package iu.web.server.sistema.dao;

import iu.web.server.sistema.notificacao.Notificacao;
import iu.web.server.sistema.persistencia.PersistenciaListener;

/**
 * Singleton
 * 
 * @author Nathaniel
 * 
 */
public interface NotificacaoDAO extends PersistenciaListener{
	
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
	 * Cria uma nova notificação
	 * 
	 * @param Notificacao - notif
	 * 		Nova notificação
	 * 
	 * @return String
	 * 		ID
	 * 
	 * @throws Exception
	 */
	public String novaNotificacao(Notificacao notif) throws Exception;

	/**
	 * Recupera uma notificação
	 * 
	 * @param String idNotificacao
	 * 
	 * @return Notificacao
	 * 		Notificação encontrada
	 * 
	 * @throws Exception
	 */
	public Notificacao recuperarNotificacao(String idNotificacao) throws Exception;

	/**
	 * Calcula a quantidade de notificações cadastradas.
	 * 
	 * @return int
	 * 		A quantidade de notificações cadastradas.
	 */
	public int qntNotificacoes();

	/**
	 * Verifica se um determinada notificação existe no repositorio.
	 * 
	 * @param String - idNotificacao
	 * 		Um idEmprestimo.
	 * 
	 * @return boolean
	 * 		True - Se a notificação procurado existir.
	 */
	public boolean existeNotificacao(String idNotificacao);

	/**
	 * Remove uma notificação
	 * 
	 * @param String
	 * 		idNotificacao
	 */
	public void removerNotificacao(String idNotificacao);

	/**
	 * Zera o repositorio
	 */
	public void zerarRepositorio();
	
}
