package sistema.item;

public interface ItemIF {
	
	/**
	 * Recupera o idItem do item.
	 * 
	 * @return
	 * 		O idItem do objeto.
	 */
	public String getIdItem();
	
	/**
	 * Recupera o nome do item.
	 * @return
	 * 		O nome do item.
	 */
	public String getNome();
	
	/**
	 * Recupera a categoria do item.
	 * @return
	 * 		A categoria do item.
	 */
	public String getCategoria();
	
	/**
	 * Recupera o tipo categoria do item.
	 * @return
	 * 		O tipo da categoria do item.
	 */
	public ItemCategoria getCategoriaType();
	
	/**
	 * Recupera a descricao do item.
	 * 
	 * @return
	 * 		A descricao do item.
	 */
	public String getDescricao();
	
	/**
	 * Configura o nome do item.
	 * 
	 * @param nome
	 * 		O nome do item.
	 */
	public void setNome( String nome );
	
	/**
	 * Configura a categoria do item.
	 * 
	 * @param categoria
	 * 		A categoria do item.
	 * @throws Exception
	 * 		Se a categoria nao existir em ItemCategoria.
	 */
	public void setCategoria( String categoria ) throws Exception;
	
	/**
	 * Configura a categoria do item.
	 * 
	 * @param categoria
	 * 		A categoria do item (ItemCategoria).
	 */
	public void setCategoria( ItemCategoria categoria );
	
	/**
	 * Configura a descricao do item.
	 * 
	 * @param descricao
	 * 		A descricao do item.
	 */
	public void setDescricao( String descricao );
	
	/**
	 * Verifica se item estah disponivel.
	 * 
	 * @return
	 * 		True - Se estiver disponivel.
	 * 		False - Caso esteja emprestado.
	 */
	public boolean estahDisponivel();
	
	/**
	 * Verifica se a igualdade dos item.
	 * 
	 * @param outroItem
	 * 		O outro item a ser comparado.
	 * @return
	 * 		True - Se os itens forem iguais. Neste caso, a comparacao eh
	 *   realizada pelo idItem.
	 *   	False - Se forem de instancias diferentes ou nao forem iguais.
	 */
	public boolean equals( Object outroItem );

	
}
