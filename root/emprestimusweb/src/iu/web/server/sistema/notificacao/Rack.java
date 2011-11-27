/**
 * 
 */
package iu.web.server.sistema.notificacao;

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

	/**
	 * retorna proprietario do Rack
	 * 
	 * @return String
	 * 		proprietario
	 */
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

	/**
	 * Adiciona Notificação
	 * 
	 * @param Notificacao
	 * 		nova notificação
	 */
	public void addNotificacao(Notificacao notificacao) {
		historico.add(0, notificacao);
	}

	/**
	 * Republica um pedido
	 * 
	 * @param Notificacao
	 * 		notificacao
	 * 
	 * @throws Exception
	 */
	public void republicarPedido(Notificacao notificacao) throws Exception {
		Notificacao notificacaoAtulizada = notificacao;
		historico.remove(notificacao);
		notificacaoAtulizada.setNovaData();
		historico.add(0, notificacaoAtulizada);
	}

	/**
	 * Zera o historico do rack
	 */
	public void zerarHistorico() {
		historico.clear();
	}

	/**
	 * retorna o historico na forma de um Iterator
	 * 
	 * @return Iterator<Notificacao>
	 */
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