package sistema.persistencia;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

import java.util.List;

import sistema.item.ItemIF;
import sistema.utilitarios.Mensagem;

public class ItemRepositorio {

	private static long contadorID = 0;

	private static Map<Long, ItemIF> itensCadastrados = new TreeMap<Long, ItemIF>();

	/**
	 * Calcula o id do proximo item a ser cadastrado.
	 * 
	 * @return String
	 * 		O id do proximo item a ser cadastrado.
	 */
	public static String geraIdProxItem() {
		return String.valueOf(contadorID + 1);
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
	public static String cadastrarItem(ItemIF item) throws Exception {
		item.setId(ItemRepositorio.geraIdProxItem());
		itensCadastrados.put(++contadorID, item);
		return String.valueOf(contadorID);
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
	public static ItemIF recuperarItem(String idItem) throws Exception {
		Long idLong = null;
		try {
			idLong = Long.parseLong(idItem);
		} catch (Exception e) {
			throw new Exception(Mensagem.ID_ITEM_INVALIDO.getMensagem());
		}
		ItemIF item = itensCadastrados.get(idLong);
		if (item == null)
			throw new Exception(Mensagem.ID_ITEM_INEXISTENTE.getMensagem());

		return item;
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
	public static String getAtributoItem(String idItem, String atributo) throws Exception {
		ItemIF item = recuperarItem(idItem);
		String atrib = ((atributo.equals("categoria"))? "categorias":atributo);
		String valor = null;
		for (Field f : item.getClass().getDeclaredFields()) {
			if (f.getName().equals(atrib)) {
				f.setAccessible(true);				
				valor = (f.get(item)).toString();
			}
		}

		if (valor == null)
			throw new Exception(Mensagem.ATRIBUTO_INEXISTENTE.getMensagem());

		return valor.replace("[", "").replace("]", "");
	}

	/**
	 * Calcula a quantidade de itens cadastrados.
	 * 
	 * @return int
	 * 		A quantidade de itens cadastrados.
	 */
	public static int qntItens() {
		return itensCadastrados.size();
	}

	/**
	 * Verifica se um determinado item existe no repositorio.
	 * 
	 * @param String - idItem
	 *  		Um idItem.
	 * @return boolean
	 * 		True - Se o item procurado existir.
	 */
	public static boolean existeItem(String idItem) {
		Long id;
		try {
			id = Long.valueOf(idItem);
		} catch (Exception e) {
			return false;
		}
		return itensCadastrados.containsKey(Long.valueOf(idItem));
	}

	/**
	 * Remove um Item do repositorio
	 * 
	 * @param String
	 * 		idItem
	 */
	public static void removerItem(String idItem) {
		Long id;
		try {
			id = Long.valueOf(idItem);
			if (existeItem((idItem))) {
				itensCadastrados.remove(id);
			}
		} catch (Exception e) {

		}

	}

	/**
	 * zera o repositorio.
	 */
	public static void zerarRepositorio() {
		itensCadastrados = new TreeMap<Long, ItemIF>();
		contadorID = 0;
	}
	
//	public static void main(String [] args){
//		List<String> lista =  new ArrayList<String>();
//		lista.add("samir");
////		lista.add("trajano");
////		lista.add("feitosa");
//		System.out.println(lista);
//	}

}
