package testes.unidade.sistema.item;

import junit.framework.Test;
import junit.framework.TestCase;

/**
 * Emprestimus Projeto de Sistemas de Informação I Universidade Federal de
 * Campina Grande
 * 
 * 
 * Classe EmailTeste - Respons�vel em realizar teste unit�rios na classe E-mail.
 */

public class ItemIFTeste extends TestCase implements Test {
	public ItemIFTeste() {
		super();
	}

	@Override
	protected void setUp() {
		System.out.println("Iniciando...");
	}

	public void testaConstrutor() {

	}

	/**
	 * Metodo testEnvia - Metodo responsavel em realizar testes. Metodos DEVEM
	 * POSSUIR DATA, COMO MENCIONADO ABAIXO.
	 * 
	 * @since 17/01/2007
	 */
	public void testEnvia() {
		assertEquals(true, true);
	}

	@Override
	protected void tearDown() {
		System.out.println("Finalizando...");
	}
}