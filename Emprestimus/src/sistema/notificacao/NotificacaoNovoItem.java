package sistema.notificacao;

import java.util.Date;
import java.util.GregorianCalendar;

import sistema.item.ItemIF;
import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;

/**
 * 
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 * @since 20/11/2011
 * @version 1.0
 *
 */
public class NotificacaoNovoItem implements Notificacao {
	private UsuarioIF usuario;
	private ItemIF item;
	private Date data;
	private Long id;

	
	public NotificacaoNovoItem(UsuarioIF usuario, ItemIF item) throws Exception {
		this.usuario = usuario;
		this.item = item;
		Thread.sleep(1);
		data = new GregorianCalendar().getTime();
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
		//O usuário passado como argumento não será usado.
		return this.usuario.getNome() + " cadastrou " + item.getNome();
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
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NotificacaoNovoItem) {
			NotificacaoNovoItem notificacao = (NotificacaoNovoItem) obj;
			return id.equals(notificacao.getId());
		}
		return false;
	}

	@Override
	public int compareTo(Notificacao o) {
		return getId().compareTo(o.getId());
	}

}
