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
	private String id;

	/**
	 * Cria Notificação de termino de Emprestimo
	 * 
	 * @param UsuarioIF
	 * 		usuario
	 * @param ItemIF
	 * 		item
	 * 
	 * @throws InterruptedException
	 */
	public NotificacaoTerminoEmprestimo(UsuarioIF usuario, ItemIF item) throws InterruptedException {
		this.usuario = usuario;
		this.item = item;
		Thread.sleep(1);
		this.data = new GregorianCalendar().getTime();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Notificacao o) {
		return getData().compareTo(o.getData());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sistema.notificacao.Notificacao#getData()
	 */
	@Override
	public Date getData() {
		return data;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sistema.notificacao.Notificacao#setNovaData(java.util.Date)
	 */
	@Override
	public Notificacao setNovaData() throws Exception {
		Thread.sleep(1);
		data = new GregorianCalendar().getTime();
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * sistema.notificacao.Notificacao#getMensagem(sistema.usuario.UsuarioIF)
	 */
	@Override
	public String getMensagem(UsuarioIF usuario) {
		// usuario não será usado.
		return String.format("%s confirmou o término do empréstimo do item %s",
				this.usuario.getNome(), item.getNome());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sistema.notificacao.Notificacao#getId()
	 */
	@Override
	public String getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sistema.notificacao.Notificacao#setId(java.lang.String)
	 */
	@Override
	public Notificacao setId(String novoId) throws Exception {
		this.id = novoId;
		return this;
	}

}
