package sistema.notificacao;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import sistema.item.Item;
import sistema.item.ItemIF;
import sistema.usuario.Usuario;
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

	public NotificacaoTerminoEmprestimo(UsuarioIF usuario, ItemIF item) throws InterruptedException {
		this.usuario = usuario;
		this.item = item;
		Thread.sleep(1);
		this.data = new GregorianCalendar().getTime();
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Notificacao o) {
		return getId().compareTo(o.getId());
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
		// usuario não será usado.
		return String.format("%s confirmou o término do empréstimo do item %s", this.usuario.getNome(), item.getNome());
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
		this.id = Long.valueOf(novoId);
		return this;
	}
	
	public static void main(String[] args) throws Exception{
		List<Notificacao> nots = new ArrayList<Notificacao>();
		UsuarioIF usu = new Usuario("aaa", "nome", "endereco");
		ItemIF item = new Item("aaa", "descricao", "Filme");
		Notificacao not1 = new NotificacaoTerminoEmprestimo(usu, item);
		Notificacao not2 = new NotificacaoNovoItem(usu, item);
		nots.add(not1);
		nots.add(not1);
		nots.add(not2);
		Iterator<Notificacao> notificacoes = nots.iterator();
		while(notificacoes.hasNext()){
			System.out.println(notificacoes.next().getId());
		}
		System.out.println(nots.size());
	}


}
