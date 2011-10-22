package sistema.item;

import java.util.Date;
import java.util.List;

import sistema.usuario.UsuarioIF;

/**
 * Esta interface eh utilizada para representar itens que podem ser adicionados
 * pelo usuario de um sistema.
 * 
 * @author Joeffison Silverio de Andrade, 21011853
 * @author José Ulisses de Brito Lira Filho, 20911806
 * @version 1.2
 */
public interface ItemIF extends Comparable<ItemIF> {

	/**
	 * Adiciona um usuário que está interessado neste item.
	 * 
	 * @param interessado
	 *            Usuario interessado.
	 * @throws Exception
	 *             Caso o usuario já exista entre os interessados.
	 */
	public void adicionaInteressado(UsuarioIF interessado) throws Exception;

	/**
	 * Remove um usuario que não está mais interessado no item.
	 * 
	 * @param interessado
	 *            Usuario que não está mais interessado.
	 * @throws Exception
	 *             Caso este usuario não esteja entre os interessados.
	 */
	public void removeInteressado(UsuarioIF interessado) throws Exception;

	/**
	 * Remove todos os interessados no item.
	 */
	public void removeTodosInteressados();

	/**
	 * Devolve uma lista com todos os interessados neste item.
	 * 
	 * @return Lista de interessados.
	 */
	public List<UsuarioIF> getInteresasados();

	/**
	 * Recupera o id do item.
	 * 
	 * @return O id do objeto.
	 */
	public String getId();

	/**
	 * Recupera o nome do item.
	 * 
	 * @return O nome do item.
	 */
	public String getNome();

	/**
	 * Recupera a categoria do item.
	 * 
	 * @return A categoria do item.
	 */
	public String getCategoria();

	/**
	 * -METODO DELETADO-
	 * Recupera o tipo da categoria do item.
	 * @deprecated
	 * @return O tipo da categoria do item.
	 */
//	public ItemCategoria getCategoriaType();

	/**
	 * Recupera a descricao do item.
	 * 
	 * @return A descricao do item.
	 */
	public String getDescricao();

	/**
	 * Modifica o id do usuario.
	 * 
	 * @param id
	 *            O id do usuario.
	 * @throws Exception
	 *             Lanca uma excecao se o valor do id nao puder ser convertido
	 *             para Long.
	 */
	public ItemIF setId(String id) throws Exception;

	/**
	 * Altera o nome do item.
	 * 
	 * @param nome
	 *            O nome do item.
	 * @throws Exception
	 *             Caso o nome nao seja uma string valida.
	 */
	public void setNome(String nome) throws Exception;

	/**
	 * Altera a categoria do item.
	 * 
	 * @param categoria
	 *            A categoria do item.
	 * @throws Exception
	 *             Se a categoria nao existir em ItemCategoria.
	 */
	public void setCategoria(String categoria) throws Exception;

	/**
	 * -METODO DELETADO-
	 * Altera a categoria do item.
	 * @deprecated
	 * @param categoria
	 *            A categoria do item (ItemCategoria).
	 * @throws Exception
	 *             Caso o parametro passado seja nulo, ele lanca excecao.
	 */
//	public void setCategoria(ItemCategoria categoria) throws Exception;

	/**
	 * Altera a descricao do item.
	 * 
	 * @param descricao
	 *            A descricao do item.
	 * @throws Exception
	 *             Lanca excecao, no caso de passar uma string invalida
	 */
	public void setDescricao(String descricao) throws Exception;

	/**
	 * Verifica se item estah disponivel.
	 * 
	 * @return True - Se estiver disponivel. False - Caso esteja emprestado.
	 */
	public boolean estahDisponivel();

	/**
	 * Altera a disponibilidade do item.
	 * 
	 * @param disponibilidade
	 *            Nova condicao de disponibilidade para o item.
	 */
	public void setDisponibilidade(boolean disponibilidade);
	
	public Date getDataCriacao();

	/**
	 * Verifica-se a igualdade dos item.
	 * 
	 * @param outroItem
	 *            O outro item a ser comparado.
	 * @return True - Se os itens forem iguais. Neste caso, a comparacao eh
	 *         realizada pelo id dos mesmos. False - Se forem de instancias
	 *         diferentes ou nao forem iguais.
	 */
	public boolean equals(Object outroItem);

	/**
	 * Compara os objetos por um ID.
	 */
	@Override
	public int compareTo(ItemIF item);

}
