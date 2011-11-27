/**
 * 
 */
package iu.web.server.sistema.item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mobile
 * 
 */
public class Bauh implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -2988391625202458717L;
	
	private String proprietario;
	private List<ItemIF> itens;
	private List<ItemIF> itensEmprestados;

	public Bauh(String proprietario) {
		this.proprietario = proprietario;
		this.itens = new ArrayList<ItemIF>();
		itensEmprestados = new ArrayList<ItemIF>();
	}

	public String getProprietario() {
		return proprietario;
	}

	public List<ItemIF> getItens() {
		return itens;
	}

	public List<ItemIF> getItensEmprestados() {
		return itensEmprestados;
	}

}
