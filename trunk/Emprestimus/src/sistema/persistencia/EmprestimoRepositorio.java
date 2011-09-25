package sistema.persistencia;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

import sistema.emprestimo.EmprestimoIF;
import sistema.utilitarios.Mensagem;

public class EmprestimoRepositorio {
	
	private static long contadorID = 0;

	private static Map<Long, EmprestimoIF> emprestimosRealizados = new TreeMap<Long, EmprestimoIF>();

	/**
	 * Calcula o id do proximo emprestimo a ser cadastrado.
	 * 
	 * @return O id do proximo emprestimo a ser cadastrado.
	 */
	public static String geraIdProxEmprestimo() {
		return String.valueOf(contadorID + 1);
	}

	
	public static String requisitarEmprestimo(EmprestimoIF emp) throws Exception {
		emp.setId(EmprestimoRepositorio.geraIdProxEmprestimo());
		emprestimosRealizados.put(++contadorID, emp);
		return String.valueOf(contadorID);
	}

	public static EmprestimoIF recuperarEmprestimo(String idEmprestimo) throws Exception {
		Long idLong = null;
		try {
			idLong = Long.parseLong(idEmprestimo);
		} catch (Exception e) {
			throw new Exception(Mensagem.EMPRESTIMO_INEXISTENTE.getMensagem());
		}
		EmprestimoIF emp = emprestimosRealizados.get(Long.parseLong(idEmprestimo));
		if (emp == null)
			throw new Exception(Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		
		return emp;
	}

	public static String getAtributoItem(String idEmprestimo, String atributo)
			throws Exception {
		EmprestimoIF emp = recuperarEmprestimo(idEmprestimo);

		String valor = null;
		for (Field f : emp.getClass().getDeclaredFields()) {
			if (f.getName().equals(atributo)) {
				f.setAccessible(true);
				valor = (f.get((EmprestimoIF) emp)).toString();
			}
		}
		if (valor == null)
			throw new Exception(Mensagem.ATRIBUTO_INEXISTENTE.getMensagem());

		return valor;
	}
	
	/**
	 * Calcula a quantidade de emprestimos cadastrados.
	 * @return
	 * 		A quantidade de emprestimos cadastrados.
	 */
	public static int qntEmprestimos(){
		return emprestimosRealizados.size();
	}
	
	
	/**
	 * Verifica se um determinado emprestimos existe no repositorio.
	 * 
	 * @param idEmprestimo
	 * 		Um idEmprestimo.
	 * @return
	 * 		True - Se o emprestimo procurado existir.
	 * 		False - Se o emprestimo não existir.
	 */
	public static boolean existeEmprestimo( String idEmprestimo ){
		Long id;
		try{
			id = Long.valueOf(idEmprestimo);
		}catch(Exception e){
			return false;
		}
		return emprestimosRealizados.containsKey(Long.valueOf(idEmprestimo));
	}
	
	public static void removerEmprestimo( String idEmprestimo ){
		Long id;
		try{
			id = Long.valueOf(idEmprestimo);
			if(existeEmprestimo(idEmprestimo)){
				emprestimosRealizados.remove(id);
			}
		}catch(Exception e){
		
		}
		
	}
}
