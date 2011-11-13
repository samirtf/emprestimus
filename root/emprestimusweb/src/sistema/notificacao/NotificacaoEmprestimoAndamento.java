package sistema.notificacao;

import java.util.Date;
import java.util.GregorianCalendar;

import sistema.item.ItemIF;
import sistema.usuario.UsuarioIF;

/**
 * 
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 * @since 23/11/2011
 * @version 1.0
 * 
 */
public class NotificacaoEmprestimoAndamento implements Notificacao {
	private UsuarioIF dono;
	private UsuarioIF beneficiado;
	private ItemIF item;

	private Date data;
	private String id;

	/**
	 * @param dono
	 * @param beneficiado
	 * @param item
	 * @throws Exception
	 */
	public NotificacaoEmprestimoAndamento(UsuarioIF dono, UsuarioIF beneficiado,
			ItemIF item) throws Exception {
		this.dono = dono;
		this.beneficiado = beneficiado;
		this.item = item;
		Thread.sleep(2);
		data = new GregorianCalendar().getTime();
	}

	@Override
	public Date getData() {
		return data;
	}

	@Override
	public Notificacao setNovaData() throws Exception {
		Thread.sleep(2);
		data = new GregorianCalendar().getTime();
		return this;
	}

	@Override
	public String getMensagem(UsuarioIF usuario) {
		return dono.getNome() + " emprestou " + item.getNome() + " a "
				+ beneficiado.getNome();
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

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NotificacaoEmprestimoAndamento) {
			NotificacaoEmprestimoAndamento notificacao = (NotificacaoEmprestimoAndamento) obj;
			return id.equals(notificacao.getId());
		}
		return false;
	}

	@Override
	public int compareTo(Notificacao o) {
		return getData().compareTo(o.getData());
	}

}
