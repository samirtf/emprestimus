package teste.unidade.sistema.item;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 * Classe EmailTeste - Responsável em realizar teste unitários na classe E-mail.
 ** 
 * @author Samir Trajano Feitosa  - samircc20092@gmail.com
 *         outraPessoa1           - outroEmail1
 *         outraPessoa2           - outroEmail2
 *         outraPessoa3           - outroEmail3
 * @since 17/01/2007
 */
public class ItemIFTeste extends TestCase implements Test {
	public ItemIFTeste() {
		super();
	}

	protected void setUp() {
		System.out.println("Iniciando...");
	}

	/**
	 * Método testEnvia - Método responsável em realizar testes.
	 * Métodos DEVEM POSSUIR DATA, COMO MENCIONADO ABAIXO.
	 *
	 * @since 17/01/2007
	 */
	public void testEnvia() {
		assertEquals(true,true);
	}

	protected void tearDown() {
		System.out.println("Finalizando...");
	}
}