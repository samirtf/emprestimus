package iu.web.server.sistema.dao;

import iu.web.server.sistema.excecoes.ArgumentoInvalidoException;
import iu.web.server.sistema.item.Bauh;
import iu.web.server.sistema.item.ItemIF;
import iu.web.server.sistema.persistencia.PersistenciaListener;

import java.io.Serializable;
import java.util.List;


/**
 * @author Mobile
 * 
 */
public interface AcervoDeItensDAO extends PersistenciaListener, Serializable {
	
	/**
	 * Inicia construção do DAO.
	 */
	public void iniciarDAO();
	
	/**
	 * Adiciona um Bauh a um determinado usuario.
	 * 
	 * @param String - usuario
	 * 		Usuario desejado
	 * 
	 * @throws Exception
	 */
	public void adicionaBauhAoUsuario(String usuario) throws Exception;

	/**
	 * Remove conta do usuario
	 * 
	 * @param String - usuario
	 * 		Usuario desejado
	 * 
	 * @throws Exception
	 */
	public void removeContaDoUsuario(String usuario) throws Exception;

	/**
	 * Retorna o Bauh de um determinado usuario
	 * 
	 * @param String - login
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
	 * @param String - login
	 * 		Login do usuario
	 * @param String - nome
	 * 		Nome do item a ser cadastrado
	 * @param String - descricao
	 * 		Descrição do item a ser cadastrado
	 * @param String - categoria
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
	 * @param String - login
	 * 		Login do usuario
	 * @param String - idItem
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
	 * @param String - login
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
	 * @param String - login
	 * 		Login do usuario desejado.
	 * 
	 * @return List<ItemIF>
	 * 		Lista de itens do usuario
	 */
	public List<ItemIF> getItens(String login);

	/**
	 * Retorna um determinado item de um determinado usuario.
	 * 
	 * @param String - login
	 * 		Login do usuario desejado.
	 * @param String - idItem
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
	 * 
	 * @param String - login
	 * 		Login do usuario desejado.
	 * 
	 * @return int
	 * 		Numero de itens do usuario
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public int qntItens(String login) throws ArgumentoInvalidoException;

	/**
	 * Quantidade de itens emprestados
	 * 
	 * @param String - login
	 * 		Login do usuario desejado.
	 * 
	 * @return int
	 * 		Quantidade de itens emprestados.
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public int qntItensEmprestados(String login) throws ArgumentoInvalidoException;

	/**
	 * Lista de id de itens emprestados
	 * 
	 * @param String - login
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
	 * @param String - login
	 * 		Login do usuario desejado.
	 * @param String - idItem
	 * 		ID do item a ser testado
	 * 
	 * @return boolean
	 * 		True caso o item exista
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public boolean existeItemID(String login, String idItem) throws ArgumentoInvalidoException;

	/**
	 * Lista de itens de um usuario
	 * 
	 * @param String - login
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
	 * 
	 * @param String - login
	 * 		Login do usuário.
	 * @param String - idItem
	 * 		Id do item.
	 * 
	 * @return boolean
	 * 		<code>True</code> - Se o item pertencer ao usuário.
	 * 
	 * @throws Exception
	 */
	public boolean esteItemMePertence(String login, String idItem) throws Exception;

	/**
	 * Apaga um determinado item
	 * 
	 * @param String - login
	 * 		Login do usuario desejado.
	 * @param String - idItem
	 * 		ID do item a ser apagado
	 * 
	 * @throws Exception
	 */
	public void apagarItem(String login, String idItem) throws Exception;

	/**
	 * Registra interesse por um determinado item
	 * 
	 * @param String - seuLogin
	 * 		Login do usuario.
	 * @param String - idItem
	 * 		ID do item a ser registrado
	 * 
	 * @throws Exception
	 */
	public void registrarInteressePorItem(String seuLogin, String idItem) throws Exception;

	/**
	 * Oferece um Item
	 * 
	 * @param String - login
	 * 		Login do usuario.
	 * @param String - idPublicacaoPedido
	 * 		ID da publicacao do pedido
	 * @param String - idItem
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
