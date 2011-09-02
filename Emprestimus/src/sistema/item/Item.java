package sistema.item;

import sistema.utilitarios.Mensagem;
import sistema.utilitarios.ValidadorString;

/**
 * Esta classe representa os itens que podem ser cadastrados pelo usuario
 * 
 * @author Joeffison Silverio de Andrade, 21011853, joeffisonsa@gmail.com
 * @version 1.5.2
 * @since 1.0
 */
public class Item implements ItemIF{
	
	private String idItem, nome, descricao;
	private ItemCategoria categoria;
	private boolean estaDisponivel;
	
	/**
	 * O construtor padrao eh privado e nao oferece implementacao.
	 */
	private Item(){}
	
	public Item( String idItem, String nome, String descricao, String categoria) throws Exception{
		this(idItem, nome, descricao, ItemCategoria.getCategoria(categoria));
	}
	
    public Item( String idItem, String nome, String descricao, ItemCategoria categoria) throws Exception{
    	this.idItem = idItem;
    	
    	setNome(nome);
    	setDescricao(descricao);
		setCategoria(categoria);
		
		this.estaDisponivel = true;
	}
    
    
	@Override
	public String getIdItem() {
		return this.nome;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getCategoria() {
		return this.categoria.getNome();
	}

	@Override
	public ItemCategoria getCategoriaType() {
		return this.categoria;
	}

	@Override
	public String getDescricao() {
		return this.descricao;
	}

	@Override
	public void setNome(String nome) throws Exception {
		this.nome = ValidadorString.pegaString(nome);
	}
	
	@Override
	public void setCategoria(ItemCategoria categoria) throws Exception {
		if (categoria == null) {
			throw new Exception(Mensagem.CATEGORIA_INVALIDA.getMensagem());
		}
		this.categoria = categoria;
	}
	
	@Override
	public void setCategoria(String categoria) throws Exception {
		this.categoria = ItemCategoria.getCategoria(categoria);
	}

	@Override
	public void setDescricao(String descricao) throws Exception {
		this.descricao = ValidadorString.pegaString(descricao);
	}

	@Override
	public boolean estahDisponivel() {
		return this.estaDisponivel;
	}
	
	@Override
	public void setDisponibilidade(boolean disponibilidade) {
		this.estaDisponivel = disponibilidade;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item) {
			Item outro = (Item) obj;
			return getNome().equals(outro.getNome()) && getCategoria().equals(outro.getCategoria()) && this.idItem == outro.getIdItem();
		}
		return false;
	}
}
