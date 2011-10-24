package sistema.notificacao;

import java.util.Date;
import java.util.GregorianCalendar;

import sistema.item.ItemIF;
import sistema.usuario.UsuarioIF;

/**
 * @author Mobile
 *
 */
public class NotificacaoTerminoEmprestimo implements Notificacao {
	private UsuarioIF usuario;
	private ItemIF item;
	private Date data;
	private Long id;

	public NotificacaoTerminoEmprestimo(UsuarioIF usuario, ItemIF item) {
		this.usuario = usuario;
		this.item = item;
		this.data = new GregorianCalendar().getTime();
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Notificacao o) {
		// TODO Auto-generated method stub
		return 0;
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
		// Mark Zuckerberg confirmou o término do empréstimo do item The Social Network
		return String.format("%s confirmou o término do empréstimo do item %s", usuario.getNome(), item.getNome());
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
		// TODO Auto-generated method stub
		return null;
	}


}
