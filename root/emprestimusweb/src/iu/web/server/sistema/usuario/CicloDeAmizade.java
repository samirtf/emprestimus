package iu.web.server.sistema.usuario;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Emprestimus Projeto de Sistemas de Informação I Universidade Federal de
 * Campina Grande
 */

public class CicloDeAmizade implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5229585841504100025L;
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

	/**
	 * @return String
	 * 		Propietario deste Circulo de Amizade.
	 */
	public String getProprietario() {
		return proprietario;
	}

	/**
	 * @return List<UsuarioIF>
	 * 		lista de amigos do proprietario.
	 */
	public List<UsuarioIF> getAmigos() {
		return amigos;
	}

	/**
	 * @return List<UsuarioIF>
	 * 		pessoas que querem ser amigos do proprietario.
	 */
	public List<UsuarioIF> getQueremSerMeusAmigos() {
		return queremSerMeusAmigos;
	}

	/**
	 * @return List<UsuarioIF>
	 * 		pessoas que o proprietario quer ser amigo.
	 */
	public List<UsuarioIF> getQueroSerAmigoDeles() {
		return queroSerAmigoDeles;
	}

}
