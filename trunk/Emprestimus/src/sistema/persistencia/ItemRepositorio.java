package sistema.persistencia;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

import sistema.item.ItemIF;
import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;

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
			throw new Exception("Identificador do item é inválido");
		}
		ItemIF item = itensCadastrados.get(idLong);
		if (item == null)
			throw new Exception("Item inexistente");
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
			throw new Exception("Atributo inexistente");

		return valor;
	}

}
