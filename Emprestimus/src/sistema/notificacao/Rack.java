/**
 * 
 */
package sistema.notificacao;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author Mobile
 * 
 */
public class Rack {

	private String proprietario;
	private List<Notificacao> historico;

	public Rack(String proprietario) {
		this.proprietario = proprietario;
		historico = new ArrayList<Notificacao>();
	}

	public String getProprietario() {
		return proprietario;
	}

	// public List<Notificacao> getHistorico(){
	// return historico; // FIXME: Não gostei disso, quem deve fazer as
	// // operções sobre os dados é quem tem os dados. Isto
	// // é, aqui deveria ter métodos para mexer com o
	// // histórico e não somente mandar ele pra outra
	// // pessoa mexer. adicionaNotificacao(),
	// // removeNotificacao(), getIterador(), etc.
	// }

	public void addNotificacao(Notificacao notificacao) {
		historico.add(0, notificacao);
	}

	public void republicarPedido(Notificacao notificacao) throws Exception {
		Notificacao notificacaoAtulizada = notificacao;
		historico.remove(notificacao);
		notificacaoAtulizada.setNovaData();
		historico.add(0, notificacaoAtulizada);
	}

	public void zerarHistorico() {
		historico.clear();
	}

	public Iterator<Notificacao> iterador() {
		return historico.iterator();
	}

	// public List<Notificacao> getHistoricoOrdenadoPorDataDecrescente(){
	// Collections.sort(historico, new HistoricoDataDecrescenteComparator());
	// return getHistorico();
	// }
	//
	// public List<Notificacao> getHistoricoOrdenadoPorDataCrescente(){
	// Collections.sort(historico, new HistoricoDataCrescenteComparator());
	// return getHistorico();
	// }

	class HistoricoDataDecrescenteComparator implements Comparator<Notificacao> {

		@Override
		public int compare(Notificacao o1, Notificacao o2) {
			return -(o1.getData().compareTo(o2.getData()));
		}
	}

	class HistoricoDataCrescenteComparator implements Comparator<Notificacao> {

		@Override
		public int compare(Notificacao o1, Notificacao o2) {
			return o1.getData().compareTo(o2.getData());
		}
	}

}
