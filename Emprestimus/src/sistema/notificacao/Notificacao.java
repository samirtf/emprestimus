package sistema.notificacao;

import java.util.Date;

import sistema.usuario.Usuario;

public interface Notificacao {

	/**
	 * Devolve a data de criacao da notificação
	 * 
	 * @return data
	 */
	public Date getData();

	/**
	 * Atribue uma nova data de atualizacao da notificacao
	 * 
	 * @param novaData
	 *            Data da última atualização na notificação
	 * @return A notifação com a nova data
	 * @throws Exception
	 *             Caso a data não seja válida
	 */
	public Notificacao setData(Date novaData) throws Exception;

	/**
	 * Devolve a mensagem formatada ponta para para ser mostrada na timeline do
	 * usuario
	 * 
	 * @param usuario
	 *            Usuario que vai receber a mensagem para mostrar a notificação
	 *            na sua timeline
	 * @return notificação
	 */
	public String getMensagem(Usuario usuario);

	/**
	 * Devolve o ID da notificação
	 * 
	 * @return
	 */
	public String getId();

	/**
	 * Atribue um novo id a esta notificacao
	 * 
	 * @param id
	 * @return A notificação com o novo ID
	 * @throws Exception
	 *             Caso o ID não seja válido
	 */
	public Notificacao setId(String novoId) throws Exception;

}
