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
	
	/**
	 * Testa construtor de UsuarioIF.
	 * @throws Exception
	 */
	public void testaConstrutor() throws Exception {
		System.out.println("Testando construtor...");
		try{
			UsuarioIF usuario = new Usuario(null, "nome", "endereco");
		}catch(Exception e){
			Assert.assertEquals("Login inválido", e.getMessage() );
		}
		try{
			UsuarioIF usuario = new Usuario(" ", "nome", "endereco");
		}catch(Exception e){
			Assert.assertEquals("Login inválido", e.getMessage());
		}
		
		
		try{
			UsuarioIF usuario = new Usuario("Login", null, "endereco");
		}catch(Exception e){
			Assert.assertEquals("Nome inválido", e.getMessage() );
		}
		try{
			UsuarioIF usuario = new Usuario("Login", " ", "endereco");
		}catch(Exception e){
			Assert.assertEquals("Nome inválido", e.getMessage());
		}
		
		try{
			UsuarioIF usuario = new Usuario("Login", "nome", null);
		}catch(Exception e){
			Assert.assertEquals("", e.getMessage() );
		}
		try{
			UsuarioIF usuario = new Usuario("Login", "nome", " ");
		}catch(Exception e){
			Assert.assertEquals("", e.getMessage());
		}
		
		UsuarioIF us1 = new Usuario(" meuLogin ", " Nome ", " endereco ");
		Assert.assertEquals("meuLogin", us1.getLogin());
		Assert.assertEquals("Nome", us1.getNome());
		Assert.assertEquals("endereco", us1.getEndereco());
		
		UsuarioIF us2 = new Usuario(" meuLogin ", " Nome ", null);
		Assert.assertEquals("meuLogin", us1.getLogin());
		Assert.assertEquals("Nome", us1.getNome());
		Assert.assertEquals("", us1.getEndereco());
		
	}
	
	public void testaSettersEGetters(){
		
	}
	
	

	protected void tearDown() {
		System.out.println("Finalizando...");
	}
}