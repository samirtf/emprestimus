/**
 * 
 */
package iu.web.server.sistema.notificacao;

import iu.web.server.sistema.item.ItemIF;
import iu.web.server.sistema.usuario.UsuarioIF;

import java.util.Date;
import java.util.GregorianCalendar;


/**
 * @author Mobile
 * 
 */
public class NotificacaoRegistrarInteresseItem implements Notificacao {

	/**
	 * 
	 */
	private static final long serialVersionUID = 690904877279637914L;
	private UsuarioIF interessado;
	private UsuarioIF amigo;
	private ItemIF item;
	private Date data;
	private String id;

	private NotificacaoRegistrarInteresseItem() {}

	/**
	 * Cria uma notificação de Registro de interesse em Item
	 * 
	 * @param UsuarioIF
	 * 		interessado
	 * @param UsuarioIF 
	 * 		amigo
	 * @param ItemIF
	 * 		item
	 * 
	 * @throws Exception
	 */
	public NotificacaoRegistrarInteresseItem(UsuarioIF interessado, UsuarioIF amigo,
			ItemIF item) throws Exception {
		this.interessado = interessado;
		this.amigo = amigo;
		this.item = item;
		Thread.sleep(1);
		data = new GregorianCalendar().getTime();
	}

	@Override
	public int compareTo(Notificacao o) {
		return getData().compareTo(o.getData());
	}

	@Override
	public Date getData() {
		return data;
	}

	@Override
	public Notificacao setNovaData() throws Exception {
		Thread.sleep(1);
		data = new GregorianCalendar().getTime();
		return this;
	}

	@Override
	public String getMensagem(UsuarioIF usuario) {
		// William Henry Gates III tem interesse pelo item The Social Network de
		// Mark Zuckerberg
		return String.format("%s tem interesse pelo item %s de %s",
				getNomeDoInteressado(), getNomeItem(), getNomeDoAmigo());
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
	public String getId() {
		return id;
	}

	@Override
	public Notificacao setId(String novoId) throws Exception {
		this.id = novoId;
		return this;
	}

}
