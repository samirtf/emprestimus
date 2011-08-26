package codigo.logica.itens;


/**
 * 
 * @author Nathaniel
 * @since 25/08/2011
 * @version 1.0
 */
public interface ItemIF {
	
	void setNome(String nome);
	void setDescricao(String descricao);
	void setCategoria(ItemCategoria categoria);
	
	String getNome();
	String getDescricao();
	ItemCategoria getCategoria();
}
