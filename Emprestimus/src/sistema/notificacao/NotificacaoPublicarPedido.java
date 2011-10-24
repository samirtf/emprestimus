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
	
	private ItemIF item;
	private UsuarioIF usuario;
	private Date data;
	private Long id;
	
	public NotificacaoPublicarPedido(UsuarioIF usuario, ItemIF item) {
		this.item = item;
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
	public Notificacao setNovaData(Date novaData) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see sistema.notificacao.Notificacao#getMensagem(sistema.usuario.UsuarioIF)
	 */
	@Override
	public String getMensagem(UsuarioIF usuario) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see sistema.notificacao.Notificacao#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see sistema.notificacao.Notificacao#setId(java.lang.String)
	 */
	@Override
	public Notificacao setId(String novoId) throws Exception {
		id = Long.valueOf(novoId);
		return this;
	}

	/* (non-Javadoc)
	 * @see sistema.notificacao.Notificacao#compareTo(sistema.notificacao.Notificacao)
	 */
	@Override
	public int compareTo(Notificacao notificacao) {
		return getId().compareTo(notificacao.getId());
	}

}
