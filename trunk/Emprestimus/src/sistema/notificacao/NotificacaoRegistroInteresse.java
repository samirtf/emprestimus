package sistema.notificacao;

import java.util.Date;
import java.util.GregorianCalendar;

import sistema.item.ItemIF;
import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;

/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public class NotificacaoRegistroInteresse implements Notificacao {
	private UsuarioIF interessado;
	private UsuarioIF dono;
	private ItemIF item;
	
	private Date data;
	private Long id;

	/**
	 * @param interessado
	 * @param dono
	 * @param item
	 * @throws InterruptedException 
	 */
	public NotificacaoRegistroInteresse(UsuarioIF interessado, UsuarioIF dono,
			ItemIF item) throws InterruptedException {
		this.interessado = interessado;
		this.dono = dono;
		this.item = item;
		Thread.sleep(1);
		this.data = new GregorianCalendar().getTime();
	}

	@Override
	public Date getData() {
		return data;
	}

	@Override
	public Notificacao setNovaData() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMensagem(UsuarioIF usuario) {
		return interessado.getNome() + " tem interesse pelo item " + item.getNome() + " de " + dono.getNome();
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
	public int compareTo(Notificacao o) {
		return getId().compareTo(o.getId());
	}

}
