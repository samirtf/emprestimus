package codigo.logica.itens;


import codigo.utilitarios.ValidadorString;


/**
 * Esta classe representa os itens que podem ser cadastrados pelo usuario
 * 
 * @author Joeffison Silverio de Andrade, joeffisonsa@gmail.com
 * @version 1.0
 */
public class Item implements ItemIF{
	
	private String nome, descricao;
	private ItemCategoria categoria;
	
	public Item(String nome, String descricao, ItemCategoria categoria) throws IllegalArgumentException {
		setDescricao(descricao);
		setNome(nome);
		setCategoria(categoria);
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public ItemCategoria getCategoria() {
		return this.categoria;
	}
	
	public void setNome(String nome) throws IllegalArgumentException {
		this.nome = ValidadorString.pegaString(nome);
	}
	
	public void setDescricao(String descricao) throws IllegalArgumentException {
		this.descricao = ValidadorString.pegaString(descricao);
	}
	
	public void setCategoria(ItemCategoria categoria) {
		this.categoria = categoria;
	}
	
}
