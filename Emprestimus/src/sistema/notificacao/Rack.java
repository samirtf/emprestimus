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
	
	public String getProprietario(){
		return proprietario;
	}
	
	public List<Notificacao> getHistorico(){
		return historico;
	}
	
	

}
