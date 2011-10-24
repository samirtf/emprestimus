/**
 * 
 */
package sistema.notificacao;

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

	public String getProprietario() {
		return proprietario;
	}

	public List<Notificacao> getHistorico() {
		return historico; // FIXME: Não gostei disso, quem deve fazer as
							// operções sobre os dados é quem tem os dados. Isto
							// é, aqui deveria ter métodos para mexer com o
							// histórico e não somente mandar ele pra outra
							// pessoa mexer. adicionaNotificacao(),
							// removeNotificacao(), getIterador(), etc.
	}

}
