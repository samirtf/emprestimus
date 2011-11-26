package sistema.dao;

import sistema.item.ItemIF;
import sistema.persistencia.ItemRepositorio;

public class ItemFileDAO implements ItemDAO {
	
	

	/**
	 * Calcula o id do proximo item a ser cadastrado.
	 * 
	 * @return String
	 * 		O id do proximo item a ser cadastrado.
	 */
	public String calculaIdProxItem() {
		return ItemRepositorio.getInstance().geraIdProxItem();
	}

	/**
	 * Cadastra um Item no repositorio
	 * 
	 * @param ItemIF
	 * 		item
	 * 
	 * @return String
	 * 		ID
	 * 
	 * @throws Exception
	 */
	public String cadastrarItem(ItemIF item) throws Exception {
		return ItemRepositorio.getInstance().cadastrarItem(item);
	}

	/**
	 * Recupera um Item
	 * 
	 * @param String
	 * 		idItem
	 * 
	 * @return ItemIF
	 * 		Item encontrado
	 * 
	 * @throws Exception
	 */
	public ItemIF recuperarItem(String idItem) throws Exception {
		return ItemRepositorio.getInstance().recuperarItem(idItem);
	}

	/**
	 * Retorna um atributo do Item
	 * 
	 * @param String
	 * 		idItem
	 * @param String
	 * 		atributo
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	public String getAtributoItem(String idItem, String atributo) throws Exception {
		return ItemRepositorio.getInstance().getAtributoItem(idItem, atributo);
	}

	/**
	 * Calcula a quantidade de itens cadastrados.
	 * 
	 * @return int
	 * 		A quantidade de itens cadastrados.
	 */
	public int qntItens() {
		return ItemRepositorio.getInstance().qntItens();
	}

	/**
	 * Verifica se um determinado item existe no repositorio.
	 * 
	 * @param String - idItem
	 *  		Um idItem.
	 * @return boolean
	 * 		True - Se o item procurado existir.
	 */
	public boolean existeItem(String idItem) {
		return ItemRepositorio.getInstance().existeItem(idItem);
	}

	/**
	 * Remove um Item do repositorio
	 * 
	 * @param String
	 * 		idItem
	 */
	public void removerItem(String idItem) {
		ItemRepositorio.getInstance().removerItem(idItem);
	}

	/**
	 * zera o repositorio.
	 */
	public void zerarRepositorio() {
		ItemRepositorio.getInstance().zerarRepositorio();
	}
	

}
