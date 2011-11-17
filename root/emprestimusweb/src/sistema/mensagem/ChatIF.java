package sistema.mensagem;

import java.util.Date;

import sistema.excecoes.ArgumentoInvalidoException;
import sistema.usuario.UsuarioIF;

public interface ChatIF extends Comparable<ChatIF> {

	/**
	 * Set o tipo da mensagem para OFF TOPIC
	 */
	public void setTipoOffTopicMsg();

	/**
	 *  Set o tipo da mensagem para OFF TOPIC
	 */
	public void setTipoNegociacaoMsg();

	/**
	 * Adiciona um texto ao chat
	 * 
	 * @param mensagem - String
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public void adicionaMensagem(String mensagem) throws ArgumentoInvalidoException;

	/**
	 * Adiciona um assunto ao Chat
	 * 
	 * @param assunto - String
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public void setAssunto(String assunto) throws ArgumentoInvalidoException;

	/**
	 * modifica o destinatario do chat
	 * 
	 * @param destinatario - UsuarioIF
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public void setDestinatario(UsuarioIF destinatario) throws ArgumentoInvalidoException;

	/**
	 * modifica o remetente ao chat
	 * 
	 * @param remetente - UsuarioIF
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public void setRemetente(UsuarioIF remetente) throws ArgumentoInvalidoException;

	/**
	 * modifica o id de mensagem
	 * 
	 * @param idMensagem - String
	 */
	public void setIdMensagem(String idMensagem);

	/**
	 * Modifica o id de requisicao de Emprestimo
	 * 
	 * @param idRequisicaoEmprestimo - String
	 * 
	 * @throws ArgumentoInvalidoException
	 * 
	 * FIXME: O nome deste metodo está faltando um "o"? pq no get getIdRequisicaoEmprestimo tem o.O
	 */
	public void setIdRequisicaEmprestimo(String idRequisicaoEmprestimo) throws ArgumentoInvalidoException;

	/**
	 * retorna a mensagem do chat
	 * 
	 * @return String
	 */
	public String getMensagem();

	/**
	 * Retorna o assunto do chat
	 * 
	 * @return String
	 */
	public String getAssunto();

	/**
	 * Retorna o destinatario do chat
	 * 
	 * @return UsuarioIF
	 */
	public UsuarioIF getDestinatario();

	/**
	 * Retorna o remetente do chat
	 * 
	 * @return UsuarioIF
	 */
	public UsuarioIF getRemetente();

	/**
	 * Retorna id de mensagem do chat
	 * 
	 * @return String
	 */
	public String getIdMensagem();

	/**
	 * Retorna o ID de requisicao de Emprestimo
	 * @return
	 */
	public String getIdRequisicaoEmprestimo();

	/**
	 * retorna a conversa completa do chat
	 * 
	 * @return String
	 * 		Compilação das mensagens do chat
	 * 
	 * @throws Exception
	 */
	public String getConversa() throws Exception;

	/**
	 * @return boolean
	 * 		true caso a conversa seja OFF topic
	 */
	public boolean ehConversaOfftopic();

	/**
	 * retorna a data do chat
	 * 
	 * @return Date
	 */
	public Date getData();

}
