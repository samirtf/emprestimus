/**
 * 
 */
package sistema.notificacao;

import java.util.Date;
import java.util.GregorianCalendar;

import sistema.item.ItemIF;
import sistema.usuario.UsuarioIF;

/**
 * @author Mobile
 *
 */
public class NotificacaoPublicarPedido implements Notificacao {
	
	private String nomeItem;
	private String descricaoItem;
	private UsuarioIF usuario;
	private Date data;
	private String id;
	
	public NotificacaoPublicarPedido(UsuarioIF usuario, String nomeItem, String descricaoItem) {
		this.nomeItem = nomeItem;
		this.usuario = usuario;
		this.data = new GregorianCalendar().getTime();
	}

	/* (non-Javadoc)
	 * @see sistema.notificacao.Notificacao#getData()
	 */
	@Override
	public Date getData() {
		return data;
	}

	/* (non-Javadoc)
	 * @see sistema.notificacao.Notificacao#setNovaData(java.util.Date)
	 */
	@Override
	public Notificacao setNovaData() throws Exception {
		data = new GregorianCalendar().getTime();
		return this;
	}

	/* (non-Javadoc)
	 * @see sistema.notificacao.Notificacao#getMensagem(sistema.usuario.UsuarioIF)
	 */
	@Override
	public String getMensagem(UsuarioIF usuario) {
		//Steven Paul Jobs precisa do item The Social Network
		return String.format("%s precisa do item %s", this.usuario.getNome(), nomeItem);
	}

	/* (non-Javadoc)
	 * @see sistema.notificacao.Notificacao#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see sistema.notificacao.Notificacao#setId(java.lang.String)
	 */
	@Override
	public Notificacao setId(String novoId) throws Exception {
		id = novoId;
		return this;
	}
	
	public UsuarioIF getOriginadorMensagem(){
		return this.usuario;
	}

	/* (non-Javadoc)
	 * @see sistema.notificacao.Notificacao#compareTo(sistema.notificacao.Notificacao)
	 */
	@Override
	public int compareTo(Notificacao notificacao) {
		return getData().compareTo(notificacao.getData());
	}

}
