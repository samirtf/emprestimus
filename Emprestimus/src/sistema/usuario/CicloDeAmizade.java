/**
 * 
 */
package sistema.usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mobile
 *
 */
public class CicloDeAmizade {

	private String proprietario;
	private List<UsuarioIF> amigos; // Grupo de amigos
	private List<UsuarioIF> queremSerMeusAmigos; // solicitacoes de amizade
	private List<UsuarioIF> queroSerAmigoDeles; // solicitacoes de amizade

	public CicloDeAmizade(String proprietario) {
		this.proprietario = proprietario;
		amigos = new ArrayList<UsuarioIF>();
		queremSerMeusAmigos = new ArrayList<UsuarioIF>();
		queroSerAmigoDeles = new ArrayList<UsuarioIF>();
	}
	
	public String getProprietario(){
		return proprietario;
	}

	/**
	 * @return the amigos
	 */
	public List<UsuarioIF> getAmigos() {
		return amigos;
	}

	/**
	 * @return the amigos
	 */
	public List<UsuarioIF> getQueremSerMeusAmigos() {
		return queremSerMeusAmigos;
	}
	
	/**
	 * @return the amigos
	 */
	public List<UsuarioIF> getQueroSerAmigoDeles() {
		return queroSerAmigoDeles;
	}
	
	

}
