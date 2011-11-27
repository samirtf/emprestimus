package iu.web.server.sistema.notificacao;

import iu.web.server.sistema.usuario.UsuarioIF;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 * @since 19/11/2011
 * @version 1.0
 * 
 */
public interface Notificacao extends Comparable<Notificacao>, Serializable {

	/**
	 * Devolve a data de criacao da notificação
	 * 
	 * @return Date
	 * 		data
	 */
	public Date getData();

	/**
	 * Atribue uma nova data de atualizacao da notificacao
	 * 
	 * @return Notificacao
	 * 		A notifação com a nova data
	 * @throws Exception
	 * 		Caso a data não seja válida
	 */
	public Notificacao setNovaData() throws Exception;

	/**
	 * Devolve a mensagem formatada ponta para para ser mostrada na timeline do
	 * usuario
	 * 
	 * @param UsuarioIF
	 * 		Usuario que vai receber a mensagem para mostrar a notificação
	 *            na sua timeline
	 * @return String
	 * 		notificação
	 */
	public String getMensagem(UsuarioIF usuario);

	/**
	 * Devolve o ID da notificação
	 * 
	 * @return String
	 * 		ID
	 */
	public String getId();

	/**
	 * Atribue um novo id a esta notificacao
	 * 
	 * @param String
	 * 		nova ID
	 * @return Notificacao
	 * 		A notificação com o novo ID
	 * @throws Exception
	 * 		Caso o ID não seja válido
	 */
	public Notificacao setId(String novoId) throws Exception;

	@Override
	public int compareTo(Notificacao notificacao);

}
