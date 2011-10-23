package sistema.notificacao;

import java.util.Date;

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
	 */
	public NotificacaoRegistroInteresse(UsuarioIF interessado, UsuarioIF dono,
			ItemIF item) {
		this.interessado = interessado;
		this.dono = dono;
		this.item = item;
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
		return interessado.getNome() + " tem interesse pelo item " + item.getNome() + " de " + dono.getNome();
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

}
