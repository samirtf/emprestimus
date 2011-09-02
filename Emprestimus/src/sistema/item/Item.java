package sistema.item;

public class Item implements ItemIF{
	
	private String idItem, nome, descricao;
	private ItemCategoria categoria;
	
	/**
	 * COntrutor padrao eh privado e nao oferece implementacao.
	 */
	private Item(){}
	
	public Item( String idItem, String nome, String descricao, String categoria)throws Exception{
		//TODO fazer com que a verificacao da string categoria valide o tipo fornecido.
	}
	
    public Item( String idItem, String nome, String descricao, ItemCategoria categoria)throws Exception{
		//TODO verifica aqui um monte de coisa e depois seta atributos se nao lancar excecao.
    	this.idItem = idItem;
    	this.nome = nome;
    	this.descricao = descricao;
    	this.categoria = categoria;
	}

	@Override
	public String getIdItem() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCategoria() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemCategoria getCategoriaType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDescricao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setNome(String nome) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCategoria(String categoria) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCategoria(ItemCategoria categoria) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDescricao(String descricao) {
		// TODO Auto-generated method stub
		
	}
	
	
}
