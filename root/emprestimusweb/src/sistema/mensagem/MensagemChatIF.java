package sistema.mensagem;

import java.util.Date;

public interface MensagemChatIF {

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
