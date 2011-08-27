package codigo.logica.itens;


/**
 * Esta interface eh utilizada para representar itens que podem ser adicionados pelo usuario de um sistema
 * 
 * @author Nathaniel
 * @author Joeffison Silverio de Andrade, joeffisonsa@gmail.com
 * @since 25/08/2011
 * @version 1.1
 */
public interface ItemIF {
	
	public String getNome();
	public String getDescricao();
	public ItemCategoria getCategoria();
	
	public void setNome(String nome);
	public void setDescricao(String descricao);
	public void setCategoria(ItemCategoria categoria);
	
}
