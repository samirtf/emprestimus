package sistema.dao;

import java.util.List;
import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.Bauh;
import sistema.item.ItemIF;


/**
 * @author Mobile
 * 
 */
public interface AcervoDeItensDAO {
	
	/**
	 * Adiciona um Bauh a um determinado usuario.
	 * 
	 * @param usuario - String
	 * 		Usuario desejado
	 * 
	 * @throws Exception
	 */
	public void adicionaBauhAoUsuario(String usuario) throws Exception;

	/**
	 * Remove conta do usuario
	 * 
	 * @param usuario - String
	 * 		Usuario desejado
	 * 
	 * @throws Exception
	 */
	public void removeContaDoUsuario(String usuario) throws Exception;

	/**
	 * Retorna o Bauh de um determinado usuario
	 * 
	 * @param login - String
	 * 		Login do usuario desejado
	 * 
	 * @return Bauh
	 * 		Bauh do usuario desejado
	 * 
	 * @throws Exception
	 */
	public Bauh getBauh(String login) throws Exception;

	/**
	 * Cadastra um item.
	 * 
	 * @param login - String
	 * 		Login do usuario
	 * @param nome - String
	 * 		Nome do item a ser cadastrado
	 * @param descricao - String
	 * 		Descrição do item a ser cadastrado
	 * @param categoria - String
	 * 		Categoria(s) do item a ser cadastrado
	 * 
	 * @return String
	 * 		ID do item cadastrado
	 * 
	 * @throws Exception
	 */
	public String cadastrarItem(String login, String nome, String descricao,
			String categoria) throws Exception;

	/**
	 * Remove um item previamente cadastrado.
	 * 
	 * @param login - String
	 * 		Login do usuario
	 * @param idItem - String
	 * 		ID do item a ser removido
	 * 
	 * @return boolean
	 * 		true caso o item seja removido com sucesso.
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public boolean removerItem(String login, String idItem) throws ArgumentoInvalidoException;

	/**
	 * Retorna a lista completa de itens de um determinado usuario
	 * 
	 * @param login - String
	 * 		Loguin do usuario desejado
	 * 
	 * @return String
	 * 		Uma String compilada a partir da lista de itens de um determinado usuario
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public String getListaIdItens(String login) throws ArgumentoInvalidoException;

	/**
	 * Retorna uma lista de itens de um determinado usuario.
	 * 
	 * @param login - String
	 * 		Login do usuario desejado.
	 * 
	 * @return List<ItemIF>
	 * 		Lista de itens do usuario
	 */
	public List<ItemIF> getItens(String login);

	/**
	 * Retorna um determinado item de um determinado usuario.
	 * @param login - String
	 * 		Login do usuario desejado.
	 * @param idItem - String
	 * 		ID do item desejado.
	 * 
	 * @return ItemIF
	 * 		Objeto referente ao item desejado
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public ItemIF getItem(String login, String idItem) throws ArgumentoInvalidoException;

	/**
	 * Retorna quantidade de itens de um determinado usuario
	 * @param login - String
	 * 		Login do usuario desejado.
	 * 
	 * @return int
	 * 		Numero de itens do usuario
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public int qntItens(String login) throws ArgumentoInvalidoException;

	/**
	 * @param login - String
	 * 		Login do usuario desejado.
	 * 
	 * @return int
	 * 		Quantidade de itens emprestados.
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public int qntItensEmprestados(String login) throws ArgumentoInvalidoException;

	/**
	 * @param login - String
	 * 		Login do usuario desejado.
	 * 
	 * @return String
	 * 		String compilada a partir da lista de itens emprestados do usuario.
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public String getListaIdItensEmprestados(String login) throws ArgumentoInvalidoException;

	/**
	 * Testa se um determinado item existe dentro da lista do usuario
	 * 
	 * @param login - String
	 * 		Login do usuario desejado.
	 * @param idItem - String
	 * 		ID do item a ser testado
	 * 
	 * @return boolean
	 * 		True caso o item exista
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public boolean existeItemID(String login, String idItem) throws ArgumentoInvalidoException;

	/**
	 * @param login - String
	 * 		Login do usuario desejado.
	 * 
	 * @return String
	 * 		String compilada a partir da lista de itens do usuario
	 * 
	 * @throws Exception
	 */
	public String getListaItens(String login) throws Exception;

	/**
	 * Verifica se o item pertence ao usuário.
	 * @param login
	 * 		Login do usuário.
	 * @param idItem
	 * 		Id do item.
	 * @return
	 * 		<code>True</code> - Se o item pertencer ao usuário.
	 * 		<code>False</code> - Caso contrário.. 
	 * @throws Exception
	 */
	public boolean esteItemMePertence(String login, String idItem) throws Exception;

	/**
	 * Apaga um determinado item
	 * 
	 * @param login - String
	 * 		Login do usuario desejado.
	 * @param idItem - String
	 * 		ID do item a ser apagado
	 * 
	 * @throws Exception
	 */
	public void apagarItem(String login, String idItem) throws Exception;

	/**
	 * Registra interesse por um determinado item
	 * 
	 * @param seuLogin - String
	 * 		Login do usuario.
	 * @param idItem - String
	 * 		ID do item a ser registrado
	 * 
	 * @throws Exception
	 */
	public void registrarInteressePorItem(String seuLogin, String idItem) throws Exception;

	/**
	 * Oferece um Item
	 * 
	 * @param  - String
	 * 		Login do usuario.
	 * @param idPublicacaoPedido - String
	 * 		ID da publicacao do pedido
	 * @param idItem - String
	 * 		ID do item pedido.
	 * 
	 * @throws Exception
	 */
	public void oferecerItem(String login, String idPublicacaoPedido, String idItem) throws Exception;
	
	/**
	 * Retorna o sistema a suas configurações iniciais.
	 */
	public void zerarSistema();

}
