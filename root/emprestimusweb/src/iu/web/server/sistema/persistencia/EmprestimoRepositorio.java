package iu.web.server.sistema.persistencia;

import iu.web.server.sistema.emprestimo.EmprestimoIF;
import iu.web.server.sistema.utilitarios.Mensagem;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;


public class EmprestimoRepositorio {
	
	private static EmprestimoRepositorio repositorio;

	private long contadorID = 0;

	private Map<Long, EmprestimoIF> emprestimosRealizados = new TreeMap<Long, EmprestimoIF>();
	
	private EmprestimoRepositorio() {
	}

	public static EmprestimoRepositorio getInstance() {
		if (repositorio == null) {
			repositorio = new EmprestimoRepositorio();
		}
		return repositorio;
	}

	/**
	 * Calcula o id do proximo emprestimo a ser cadastrado.
	 * 
	 * @return String
	 * 		O id do proximo emprestimo a ser cadastrado.
	 */
	public String geraIdProxNotificacao() {
		return String.valueOf(contadorID + 1);
	}

	/**
	 * Requisita um emprestimo
	 * @param EmprestimoIF
	 * 		Emprestimo a ser requisitado
	 * 
	 * @return String
	 * 		ID
	 * 
	 * @throws Exception
	 */
	public String requisitarEmprestimo(EmprestimoIF emp) throws Exception {
		emp.setId(geraIdProxNotificacao());
		emprestimosRealizados.put(++contadorID, emp);
		return String.valueOf(contadorID);
	}

	/**
	 * Recupera um Emprestimo
	 * 
	 * @param String
	 * 		idEmprestimo
	 * 
	 * @return EmprestimoIF
	 * 		Emprestimo encontrado
	 * 
	 * @throws Exception
	 */
	public EmprestimoIF recuperarEmprestimo(String idEmprestimo) throws Exception {
		Long idLong = null;
		try {
			idLong = Long.parseLong(idEmprestimo);
		} catch (Exception e) {
			throw new Exception(Mensagem.EMPRESTIMO_INEXISTENTE.getMensagem());
		}
		EmprestimoIF emp = emprestimosRealizados.get(idLong);
		if (emp == null)
			throw new Exception(Mensagem.ID_ITEM_INEXISTENTE.getMensagem());

		return emp;
	}

	/**
	 * Retorna um atributo de um item
	 * 
	 * @param String
	 * 		idEmprestimo
	 * @param String
	 * 		atributo
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	public String getAtributoItem(String idEmprestimo, String atributo) throws Exception {
		EmprestimoIF emp = recuperarEmprestimo(idEmprestimo);

		String valor = null;
		for (Field f : emp.getClass().getDeclaredFields()) {
			if (f.getName().equals(atributo)) {
				f.setAccessible(true);
				valor = (f.get(emp)).toString();
			}
		}
		if (valor == null)
			throw new Exception(Mensagem.ATRIBUTO_INEXISTENTE.getMensagem());

		return valor;
	}

	/**
	 * Calcula a quantidade de emprestimos cadastrados.
	 * 
	 * @return int
	 * 		A quantidade de emprestimos cadastrados.
	 */
	public int qntEmprestimos() {
		return emprestimosRealizados.size();
	}

	/**
	 * Verifica se um determinado emprestimos existe no repositorio.
	 * 
	 * @param String - idEmprestimo
	 *            Um idEmprestimo.
	 * @return boolean
	 * 		True - Se o emprestimo procurado existir.
	 */
	public boolean existeEmprestimo(String idEmprestimo) {
		Long idLong;
		try {
			idLong = Long.valueOf(idEmprestimo);
		} catch (Exception e) {
			return false;
		}
		return emprestimosRealizados.containsKey(idLong);
	}

	/**
	 * Remove um Emprestimo
	 * @param String
	 * 		idEmprestimo
	 */
	public void removerEmprestimo(String idEmprestimo) {
		Long id;
		try {
			id = Long.valueOf(idEmprestimo);
			if (existeEmprestimo(idEmprestimo)) {
				emprestimosRealizados.remove(id);
			}
		} catch (Exception e) {

		}

	}

	/**
	 * Zera o repositorio
	 */
	public void zerarRepositorio() {
		emprestimosRealizados = new TreeMap<Long, EmprestimoIF>();
		contadorID = 0;
	}

	public void salvarEmArquivo() {
		// TODO Auto-generated method stub
		
	}
}
