package sistema.utilitarios;

import sistema.excecoes.ArgumentoInvalidoException;

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
	 * Testa se um dado objeto é nullo, passando-se uma mensagem de erro, que
	 * será acoplada a uma ArgumentoInvalidoException
	 * 
	 * @param obj
	 * @param mensagem
	 * @throws ArgumentoInvalidoException
	 */
	public static void assertNaoNulo(Object obj, String mensagem)
			throws ArgumentoInvalidoException {
		if (obj == null)
			throw new ArgumentoInvalidoException(mensagem);
	}

	/**
	 * Testa se uma dada String é vazia ou se é formada apenas por espaços,
	 * passando-se uma mensagem de erro, que será acoplada a uma
	 * ArgumentoInvalidoException
	 * 
	 * @param str
	 * @param mensagem
	 * @throws ArgumentoInvalidoException
	 */
	public static void assertStringNaoVazia(String str, String mensagem)
			throws ArgumentoInvalidoException {
		if (str.trim().equals(""))
			throw new ArgumentoInvalidoException(mensagem);
	}

	/**
	 * Testa se um valor é verdadeiro. Caso seja falso lança uma
	 * ArgumentoInvalidoException.
	 * 
	 * @param bool
	 * @param mensagem
	 * @throws ArgumentoInvalidoException
	 */
	public static void asserteTrue(boolean bool, String mensagem)
			throws ArgumentoInvalidoException {
		if (!bool)
			throw new ArgumentoInvalidoException(mensagem);
	}

}
