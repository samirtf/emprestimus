package sistema.item;

/**
 * Esta interface eh utilizada para representar itens que podem ser adicionados pelo usuario de um sistema.
 * 
 * @author Joeffison Silverio de Andrade, 21011853, joeffisonsa@gmail.com
 * @version 1.2
 * @since 1.0
 */
public interface ItemIF {
	
	/**
	 * Recupera o id do item.
	 * 
	 * @return
	 * 		O id do objeto.
	 */
	public String getId();
	
	/**
	 * Recupera o nome do item.
	 * 
	 * @return
	 * 		O nome do item.
	 */
	public String getNome();
	
	/**
	 * Recupera a categoria do item.
	 * 
	 * @return
	 * 		A categoria do item.
	 */
	public String getCategoria();
	
	/**
	 * Recupera o tipo da categoria do item.
	 * 
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
	 * Altera o nome do item.
	 * 
	 * @param nome
	 * 		O nome do item.
	 * @throws Exception
	 * 		Caso o nome nao seja uma string valida. 
	 */
	public void setNome( String nome ) throws Exception;
	
	/**
	 * Altera a categoria do item.
	 * 
	 * @param categoria
	 * 		A categoria do item.
	 * @throws Exception
	 * 		Se a categoria nao existir em ItemCategoria.
	 */
	public void setCategoria( String categoria ) throws Exception;
	
	/**
	 * Altera a categoria do item.
	 * 
	 * @param categoria
	 * 		A categoria do item (ItemCategoria).
	 * @throws Exception
	 * 		Caso o parametro passado seja nulo, ele lanca excecao. 
	 */
	public void setCategoria( ItemCategoria categoria ) throws Exception;
	
	/**
	 * Altera a descricao do item.
	 * 
	 * @param descricao
	 * 		A descricao do item.
	 * @throws Exception 
	 * 		Lanca excecao, no caso de passar uma string invalida
	 */
	public void setDescricao( String descricao ) throws Exception;
	
	/**
	 * Verifica se item estah disponivel.
	 * 
	 * @return
	 * 		True - Se estiver disponivel.
	 * 		False - Caso esteja emprestado.
	 */
	public boolean estahDisponivel();
	
	/**
	 * Altera a disponibilidade do item.
	 * 
	 * @param disponibilidade
	 * 		Nova condicao de disponibilidade para o item.
	 */
	public void setDisponibilidade(boolean disponibilidade);
	
	/**
	 * Verifica-se a igualdade dos item.
	 * 
	 * @param outroItem
	 * 		O outro item a ser comparado.
	 * @return
	 * 		True - Se os itens forem iguais. Neste caso, a comparacao eh
	 *   realizada pelo id dos mesmos.
	 *   	False - Se forem de instancias diferentes ou nao forem iguais.
	 */
	public boolean equals( Object outroItem );

	
}
