/**
 * 
 */
package sistema.item;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mobile
 *
 */
public class Bauh {

	private String proprietario;
	private List<ItemIF> itens;
	private List<ItemIF> itensEmprestados;

	public Bauh(String proprietario) {
		this.proprietario = proprietario;
		this.itens = new ArrayList<ItemIF>();
		itensEmprestados = new ArrayList<ItemIF>();
	}
	
	public String getProprietario(){
		return proprietario;
	}
	
	public List<ItemIF> getItens(){
		return itens;
	}
	
	public List<ItemIF> getItensEmprestados(){
		return itensEmprestados;
	}

}
