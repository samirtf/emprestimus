package iu.web.server.sistema.dao;

import java.io.Serializable;

import iu.web.server.sistema.mensagem.ChatIF;
import iu.web.server.sistema.persistencia.PersistenciaListener;

/**
 * @author Mobile
 * 
 */
public interface CorreioDAO extends PersistenciaListener, Serializable{
	
	/**
	 * Inicia construção do DAO.
	 */
	public void iniciarDAO();

	public void adicionaCaixaPostalAoUsuario(String usuario) throws Exception;

	public void removeCaixaPostalDoUsuario(String usuario) throws Exception;

	/**
	 * Adiciona uma conversa off-topic à lista de conversas do usuário.
	 * 
	 * @param String - usuario
	 * 		O login do usuario.
	 *            
	 * @param ChatIF - conversa
	 * 		Conversa a ser adicionada.
	 * 
	 * @throws Exception
	 */
	public void adicionaConversaOfftopicNaLista(String usuario, ChatIF conversa) throws Exception;

	/**
	 * Adiciona uma conversa sobre uma negociação à lista de conversas do
	 * usuário.
	 * 
	 * @param String - usuario
	 * 		O login do usuario.
	 *            
	 * @param ChatIF - conversa
	 * 		Conversa a ser adicionada.
	 * 
	 * @throws Exception
	 */
	public void adicionaConversaNegociacaoNaLista(String usuario, ChatIF conversa) throws Exception;

	/**
	 * Envia uma mensagem off-topic.
	 * 
	 * @param String - usuario
	 *  	O login do usuario.
	 * @param String - destinatario
	 * 		Destino da mensagem.
	 * @param String - assunto
	 * 		Assunto da mensagem.
	 * @param String - mensagem
	 * 		Mensagem a ser enviada.
	 *            
	 * @return String
	 * 		ID do tópico.
	 * 
	 * @throws Exception
	 * 		Caso os parâmetros sejam inválidos.
	 */
	public String enviarMensagemOffTopic(String remetente,
			String destinatario, String assunto, String mensagem) throws Exception;

	/**
	 * Envia mensagem de emprestimo
	 * 
	 * @param String - usuario
	 * 		O login do usuario.
	 * @param String - destinatario
	 * 		Destino da mensagem.
	 * @param String - assunto
	 * 		Assunto da mensagem.
	 * @param String - mensagem
	 * 		Mensagem a ser enviada.
	 * @param String - idRequisicaoEmprestimo
	 * 		Id da requisicao de emprestimo.
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	public String enviarMensagemEmprestimo(String remetente,
			String destinatario, String assunto, String mensagem,
			String idRequisicaoEmprestimo) throws Exception;

	/**
	 * Lê topicos
	 * 
	 * @param String - usuario
	 * 		O login do usuario.
	 * @param String - tipo
	 * 		Tipo do topico.
	 * 
	 * @return String
	 * 		compilação dos textos.
	 * 
	 * @throws Exception
	 */
	public String lerTopicos(String proprietario, String tipo) throws Exception;

	/**
	 * Envia uma mensagem de oferecimento de item Off Topic
	 * 
	 * @param String - remetente
	 * 		remetente da mensagem.
	 * @param String - destinatario
	 * 		Destinatario da mensagem
	 * @param String - assunto
	 * 		Assunto da mensagem
	 * @param String - mensagem
	 * 		texto da mensagem
	 * 
	 * @return String
	 * 		id da mensagem de oferencimento de item off topic
	 * 
	 * @throws Exception
	 */
	public String enviarMensagemOferecimentoItemOffTopic(String remetente,
			String destinatario, String assunto, String mensagem) throws Exception;

	/**
	 * retorna o correio a sua configuração inicial
	 */
	public void zerarSistema();

}
