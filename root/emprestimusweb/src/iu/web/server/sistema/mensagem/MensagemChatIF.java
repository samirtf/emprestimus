package iu.web.server.sistema.mensagem;

import java.io.Serializable;
import java.util.Date;

public interface MensagemChatIF extends Serializable {

	/**
	 * @return String
	 * 		Mensagem do chat
	 */
	public String getMensagem();

	/**
	 * Modifica a mensagem
	 * 
	 * @param mensagem - String
	 */
	public void setMensagem(String mensagem);

	/**
	 * data da mensagem
	 * 
	 * @return Date
	 */
	public Date getData();

	/**
	 * Modifica data da mensagem
	 * 
	 * @param data - Date
	 */
	public void setData(Date data);

	@Override
	public String toString();

}
