package sistema.utilitarios;

import sistema.excecoes.ArgumentoInvalidoException;

/**
 * Emprestimus Projeto de Sistemas de Informação I Universidade Federal de
 * Campina Grande
 * 
 * Classe usada para lançar exceção em alguns casos, quando se deseja testar
 * algo.
 */

public class Validador {

	/**
	 * Testa se um dado objeto é nulo, passando-se uma mensagem de erro, que
	 * será acoplada a uma ArgumentoInvalidoException
	 * 
	 * @param obj
	 *            Objeto a ser verificado.
	 * @param mensagem
	 *            Mensagem da exceção gerada em caso do objeto ser nulo.
	 * @throws ArgumentoInvalidoException
	 *             Exceção gerada em caso do objeto ser nulo.
	 */
	public static void assertNaoNulo(Object obj, String mensagem) throws ArgumentoInvalidoException {
		if (obj == null)
			throw new ArgumentoInvalidoException(mensagem);
	}

	/**
	 * Testa se uma dada {@link String} é vazia ou se é formada apenas por
	 * espaços, passando-se uma mensagem de erro, que será acoplada a uma
	 * ArgumentoInvalidoException
	 * 
	 * @param str
	 *            String a ser testada.
	 * @param mensagem
	 *            Mensagem da exceção gerada em caso da String for inválida
	 *            segundo os requisitos acima.
	 * @throws ArgumentoInvalidoException
	 *             Exceção gerada em caso da String for inválida segundo os
	 *             requisitos acima.
	 */
	public static void assertStringNaoVazia(String str, String mensagem1, String mensagem2) throws ArgumentoInvalidoException {
		assertNaoNulo(str, mensagem1);
		if (str.trim().equals(""))
			throw new ArgumentoInvalidoException(mensagem2);
	}

	/**
	 * Testa se um valor é verdadeiro. Caso seja falso lança uma
	 * ArgumentoInvalidoException.
	 * 
	 * @param bool
	 *            Boolean a ser verificado.
	 * @param mensagem
	 *            Mensagem para a exceção gerada, se for o caso.
	 * @throws ArgumentoInvalidoException
	 *             Exceção gerada em caso do boolean ser false.
	 */
	public static void asserteTrue(boolean bool, String mensagem) throws ArgumentoInvalidoException {
		if (!bool)
			throw new ArgumentoInvalidoException(mensagem);
	}

}
