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
	
	private String nomeDoInteressado;
	private String nomeDoAmigo;
	private ItemIF item;
	private Date data;
	private Long id;
	
	private NotificacaoRegistrarInteresseItem(){}
	
	public NotificacaoRegistrarInteresseItem(String nomeDoInteressado, String nomeDoAmigo, ItemIF item) throws Exception {
		this.nomeDoInteressado = nomeDoInteressado;
		this.nomeDoAmigo = nomeDoAmigo;
		this.item = item;
		inicializarData();
	}

	@Override
	public int compareTo(Notificacao o) {
		// TODO Auto-generated method stub
		return 0;
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
	public void inicializarData() throws Exception {
		data = new GregorianCalendar().getTime();
	}

	@Override
	public String getMensagem(UsuarioIF usuario) {
		//William Henry Gates III tem interesse pelo item The Social Network de Mark Zuckerberg
		return String.format("%s tem interesse pelo item %s de %s", getNomeDoInteressado(), getNomeItem(), getNomeDoAmigo());
	}
	
	private String getNomeDoAmigo() {
		return this.nomeDoAmigo;
	}

	private String getNomeDoInteressado() {
		return this.nomeDoInteressado;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notificacao setId(String novoId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
