package sistema.persistencia;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

import sistema.item.ItemIF;
import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;

public class ItemRepositorio {

	private static long contadorID = 0;

	private static Map<Long, ItemIF> itensCadastrados = new TreeMap<Long, ItemIF>();

	/**
	 * Calcula o id do proximo item a ser cadastrado.
	 * 
	 * @return O id do proximo item a ser cadastrado.
	 */
	public static String geraIdProxItem() {
		return String.valueOf(contadorID + 1);
	}

	
	public static String cadastrarItem(ItemIF item) throws Exception {
		item.setId(ItemRepositorio.geraIdProxItem());
		itensCadastrados.put(++contadorID, item);
		return String.valueOf(contadorID);
	}

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

	public static String getAtributoItem(String idItem, String atributo)
			throws Exception {
		ItemIF item = recuperarItem(idItem);

		String valor = null;
		for (Field f : item.getClass().getDeclaredFields()) {
			if (f.getName().equals(atributo)) {
				f.setAccessible(true);
				valor = (f.get((ItemIF) item)).toString();
			}
		}
		if (valor == null)
			throw new Exception(Mensagem.ATRIBUTO_INEXISTENTE.getMensagem());

		return valor;
	}
	
	/**
	 * Calcula a quantidade de itens cadastrados.
	 * @return
	 * 		A quantidade de itens cadastrados.
	 */
	public static int qntItens(){
		return itensCadastrados.size();
	}
	
	
	/**
	 * Verifica se um determinado item existe no repositorio.
	 * 
	 * @param idItem
	 * 		Um idItem.
	 * @return
	 * 		True - Se o item procurado existir.
	 * 		False - Se o item não existir.
	 */
	public static boolean existeItem( String idItem ){
		Long id;
		try{
			id = Long.valueOf(idItem);
		}catch(Exception e){
			return false;
		}
		return itensCadastrados.containsKey(Long.valueOf(idItem));
	}

	public static void removerItem( String idItem ){
		Long id;
		try{
			id = Long.valueOf(idItem);
			if(existeItem((idItem))){
				itensCadastrados.remove(id);
			}
		}catch(Exception e){
		
		}
		
	}
	
}
