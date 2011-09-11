package testes.unidade.sistema.usuario;


import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;

/**
 * Classe EmailTeste - Responsavel em realizar teste unitarios na classe E-mail.
 ** 
 * @author Samir Trajano Feitosa  - samircc20092@gmail.com
 *         outraPessoa1           - outroEmail1
 *         outraPessoa2           - outroEmail2
 *         outraPessoa3           - outroEmail3
 * @since 17/01/2007
 */
public class UsuarioIFTeste extends TestCase implements Test {
	public UsuarioIFTeste() {
		super();
	}

	protected void setUp() {
		System.out.println("Iniciando...");
	}

	/**
	 * Metodo testEnvia - Metodo responsavel em realizar testes.
	 * Metodos DEVEM POSSUIR DATA, COMO MENCIONADO ABAIXO.
	 *
	 * @since 17/01/2007
	 */
	public void testEnvia() {
		assertEquals(true,true);
	}
	
	public void testaConstrutor() {
		
		try{
			UsuarioIF usuario = new Usuario(null, "nome", "endereco");
		}catch(Exception e){
			Assert.assertEquals(e.getMessage(), "Usuário inválido");
		}
	}

	protected void tearDown() {
		System.out.println("Finalizando...");
	}
}