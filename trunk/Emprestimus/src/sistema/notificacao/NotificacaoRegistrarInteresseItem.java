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
public class NotificacaoRegistrarInteresseItem implements Notificacao {
	
	private UsuarioIF interessado;
	private UsuarioIF amigo;
	private ItemIF item;
	private Date data;
	private Long id;
	
	private NotificacaoRegistrarInteresseItem(){}
	
	public NotificacaoRegistrarInteresseItem(UsuarioIF interessado, UsuarioIF amigo, ItemIF item) throws Exception {
		this.interessado = interessado;
		this.amigo = amigo;
		this.item = item;
		Thread.sleep(1);
		data = new GregorianCalendar().getTime();
	}

	@Override
	public int compareTo(Notificacao o) {
		return getId().compareTo(o.getId());
	}

	@Override
	public Date getData() {
		return data;
	}

	@Override
	public Notificacao setNovaData(Date novaData) throws Exception {
		data = novaData;
		return this;
	}


	@Override
	public String getMensagem(UsuarioIF usuario) {
		//William Henry Gates III tem interesse pelo item The Social Network de Mark Zuckerberg
		return String.format("%s tem interesse pelo item %s de %s", getNomeDoInteressado(), getNomeItem(), getNomeDoAmigo());
	}
	
	private String getNomeDoAmigo() {
		return this.amigo.getNome();
	}

	private String getNomeDoInteressado() {
		return this.interessado.getNome();
	}

	private String getNomeItem() {
		return this.item.getNome();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NotificacaoRegistrarInteresseItem) {
			NotificacaoRegistrarInteresseItem notificacao = (NotificacaoRegistrarInteresseItem) obj;
			return id.equals(notificacao.getId());
		}
		return false;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Notificacao setId(String novoId) throws Exception {
		this.id = Long.valueOf(novoId);
		return this;
	}

}
