package sistema.utilitarios;

/**
 * Classe usada para lançar exceção em alguns casos, quando se deseja testar
 * algo.
 * 
 * @author José Nathaniel L. de Abrante - nathaniel.una@gmail.com
 * @since 10/09/2011
 * @version 1.0
 */
public class Validador {

	/**
	 * Testa se um dado objeto é nullo, passando-se uma exceção para ser lançada
	 * 
	 * @param obj
	 * @param t
	 */
	public static void testaNaoNulo(Object obj, Throwable t) {
		if (obj == null) {
			throw new IllegalArgumentException(t);
		}
	}

	/**
	 * Testa se um dado objeto é nullo, passando-se uma mensagem de erro, que
	 * será acoplada a uma IllegalArgumentException
	 * 
	 * @param obj
	 * @param mensagem
	 */
	public static void testaNaoNulo(Object obj, String mensagem) {
		if (obj == null) {
			throw new IllegalArgumentException(mensagem);
		}
	}

	/**
	 * Testa se uma dada String é vazia, passando-se uma exceção para ser
	 * lançada.
	 * 
	 * @param s
	 * @param t
	 */
	public static void testaStringVazia(String s, Throwable t) {
		if (s.trim().equals("")) {
			throw new IllegalArgumentException(t);
		}
	}

	/**
	 * Testa se uma dada String é vazia, passando-se uma mensagem de erro, que
	 * será acoplada a uma IllegalArgumentException
	 * 
	 * @param s
	 * @param mensagem
	 */
	public static void testaStringVazia(String s, String mensagem) {
		if (s.trim().equals("")) {
			throw new IllegalArgumentException(mensagem);
		}
	}

}
