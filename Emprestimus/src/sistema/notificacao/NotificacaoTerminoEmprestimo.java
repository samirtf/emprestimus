package sistema.notificacao;

import java.util.Date;

import sistema.item.ItemIF;
import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;

/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public class NotificacaoTerminoEmprestimo implements Notificacao {
	private UsuarioIF usuario;
	private ItemIF item;
	
	private Date data;
	private Long id;

	/**
	 * @param usuario
	 * @param item
	 */
	public NotificacaoTerminoEmprestimo(UsuarioIF usuario, ItemIF item) {
		this.usuario = usuario;
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
		return usuario.getNome() + " confirmou o término no empréstimo do item " + item.getNome();
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
