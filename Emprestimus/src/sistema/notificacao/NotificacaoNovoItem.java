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
	private ItemIF item;
	private Date data;
	private Long id;

	
	public NotificacaoNovoItem(UsuarioIF usuario, ItemIF item) {
		this.item = item;
		this.data = new GregorianCalendar().getTime();
	}

	@Override
	public Date getData() {
		return data;
	}

	@Override
	public Notificacao setData(Date novaData) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMensagem(Usuario usuario) {
		return usuario.getNome() + " cadastrou " + item.getNome();
	}

	@Override
	public Long getId() {
		return Long.valueOf(id);
	}

	@Override
	public Notificacao setId(String novoId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NotificacaoNovoItem) {
			NotificacaoNovoItem notificacao = (NotificacaoNovoItem) obj;
			return id.equals(notificacao.getId());
		}
		return false;
	}

}
