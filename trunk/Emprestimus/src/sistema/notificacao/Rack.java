/**
 * 
 */
package sistema.notificacao;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
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
		historico = new LinkedList<Notificacao>();
	}
	
	public String getProprietario(){
		return proprietario;
	}
	
	public List<Notificacao> getHistorico(){
		return historico; // FIXME: Não gostei disso, quem deve fazer as
		// operções sobre os dados é quem tem os dados. Isto
		// é, aqui deveria ter métodos para mexer com o
		// histórico e não somente mandar ele pra outra
		// pessoa mexer. adicionaNotificacao(),
		// removeNotificacao(), getIterador(), etc.
	}
	
	public List<Notificacao> getHistoricoOrdenadoPorDataDecrescente(){
		Collections.sort(historico, new HistoricoDataDecrescenteComparator());
		return getHistorico();
	}
	
	public List<Notificacao> getHistoricoOrdenadoPorDataCrescente(){
		Collections.sort(historico, new HistoricoDataCrescenteComparator());
		return getHistorico();
	}

	class HistoricoDataDecrescenteComparator implements Comparator<Notificacao>{
		
		@Override
		public int compare(Notificacao o1, Notificacao o2) {
			return o2.getData().compareTo(o1.getData());
		}
	}
	
	class HistoricoDataCrescenteComparator implements Comparator<Notificacao>{
		
		@Override
		public int compare(Notificacao o1, Notificacao o2) {
			return o1.getData().compareTo(o2.getData());
		}
	}

}




