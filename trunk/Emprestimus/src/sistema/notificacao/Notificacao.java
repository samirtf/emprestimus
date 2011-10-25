package sistema.notificacao;

import java.util.Date;
import sistema.usuario.UsuarioIF;

/**
 * 
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 * @since 19/11/2011
 * @version 1.0
 *
 */
public interface Notificacao extends Comparable<Notificacao>{

	/**
	 * Devolve a data de criacao da notificação
	 * 
	 * @return data
	 */
	public Date getData();
	
	/**
	 * Atribue uma nova data de atualizacao da notificacao
	 * 
	 * @return A notifação com a nova data
	 * @throws Exception
	 *             Caso a data não seja válida
	 */
	public Notificacao setNovaData() throws Exception;

	/**
	 * Devolve a mensagem formatada ponta para para ser mostrada na timeline do
	 * usuario
	 * 
	 * @param usuario
	 *            Usuario que vai receber a mensagem para mostrar a notificação
	 *            na sua timeline
	 * @return notificação
	 */
	public String getMensagem(UsuarioIF usuario);

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
	
	public int compareTo(Notificacao notificacao);
	
	
	
}
