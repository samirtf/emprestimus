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
			Assert.assertEquals("", usuario.getEndereco() );
		}catch(Exception e){
			Assert.fail("Nao devia ter lancado excecao");
		}
		try{
			UsuarioIF usuario = new Usuario("Login", "nome", " ");
			Assert.assertEquals("", usuario.getEndereco() );
		}catch(Exception e){
			Assert.fail("Nao devia ter lancado excecao");
		}
		
		UsuarioIF us1 = new Usuario(" meuLogin ", " Nome ", " endereco ");
		Assert.assertEquals("meuLogin", us1.getLogin());
		Assert.assertEquals("Nome", us1.getNome());
		Assert.assertEquals("endereco", us1.getEndereco());
		
		UsuarioIF us2 = new Usuario(" meuLogin ", " Nome ", null);
		Assert.assertEquals("meuLogin", us2.getLogin());
		Assert.assertEquals("Nome", us2.getNome());
		Assert.assertEquals("", us2.getEndereco());
		
	}
	
	public void testaSettersEGetters(){
		
		try {
			UsuarioIF us1 = new Usuario(" umLogin ", " umNome ", null);
			Assert.assertEquals("umLogin", us1.getLogin());
			Assert.assertEquals("umNome", us1.getNome());
			Assert.assertEquals("", us1.getEndereco());
		} catch (Exception e) {
			Assert.fail("Nao devia ter lancado excecao");
		}
		
		try {
			UsuarioIF us1 = new Usuario(" umLogin   ", "   umNome   ", "   ");
			Assert.assertEquals("umLogin", us1.getLogin());
			Assert.assertEquals("umNome", us1.getNome());
			Assert.assertEquals("", us1.getEndereco());
		} catch (Exception e) {
			Assert.fail("Nao devia ter lancado excecao");
		}
		
		try {
			UsuarioIF us1 = new Usuario(" umLogin   ", "   umNome   ", "  umEndereco   ");
			Assert.assertEquals("umLogin", us1.getLogin());
			Assert.assertEquals("umNome", us1.getNome());
			Assert.assertEquals("umEndereco", us1.getEndereco());
		} catch (Exception e) {
			Assert.fail("Nao devia ter lancado excecao");
		}
		
		UsuarioIF us1 = null;
		
		try{
			us1 = new Usuario(" umLogin ", " umNome ", " umEndereco ");
		}catch(Exception e){
			Assert.fail("Não devia ter lançado excecao");
		}
		
		try{
			us1.setLogin(null);
		}catch(Exception e){
			Assert.assertEquals("Login inválido", e.getMessage());
		}
		
		try{
			us1.setLogin("    ");
		}catch(Exception e){
			Assert.assertEquals("Login inválido", e.getMessage());
		}
		
		try{
			us1.setLogin("  outroLogin  ");
			Assert.assertEquals("outroLogin", us1.getLogin());
		}catch(Exception e){
			Assert.fail("Não deveria ter lancado excecao");
		}
		
		try{
			us1.setNome(null);
		}catch(Exception e){
			Assert.assertEquals("Nome inválido", e.getMessage());
		}
		
		try{
			us1.setNome("    ");
		}catch(Exception e){
			Assert.assertEquals("Nome inválido", e.getMessage());
		}
		
		try{
			us1.setNome("  outroNome  ");
			Assert.assertEquals("outroNome", us1.getNome());
		}catch(Exception e){
			Assert.fail("Não deveria ter lancado excecao");
		}
		
		try{
			us1.setEndereco(null);
			Assert.assertEquals("", us1.getEndereco());
		}catch(Exception e){
			Assert.fail("Não deveria ter lancado excecao");
		}
		
		try{
			us1.setEndereco("    ");
			Assert.assertEquals("", us1.getEndereco());
		}catch(Exception e){
			Assert.fail("Não deveria ter lancado excecao");
		}
		
		try{
			us1.setEndereco("  outroEndereco  ");
			Assert.assertEquals("outroEndereco", us1.getEndereco());
		}catch(Exception e){
			Assert.fail("Não deveria ter lancado excecao");
		}
		
	}
	
	/**
	 * A igualdade entre dois usuarios eh feita pelo LOGIN.
	 */
	public void testaIgualdadeUsuarios(){
		
		UsuarioIF us1, us2, us3;
		
		try{
			us1 = new Usuario(" umLogin ", " umNome ", " umEndereco ");
			us2 = new Usuario(" umLogin ", " umNome ", " umEndereco ");
			us3 = new Usuario(" outroLogin ", " umNome ", " umEndereco ");
			
			Assert.assertEquals(true, us1.equals(us2));
			Assert.assertEquals(false, us1.equals(us3));
			
			try{
				us2.setNome(" outroNome ");
				us2.setEndereco(" um Outro Endereco ");
				Assert.assertEquals(true, us1.equals(us2));
				
				us2.setLogin(" outroLogin ");
				Assert.assertEquals(false, us1.equals(us2));
				Assert.assertEquals(false, us1.equals(us3));
				Assert.assertEquals(true, us2.equals(us3));
				
				us3.setLogin( " OuTrOlOgIn " );
				Assert.assertEquals(false, us2.equals(us3));
				
			}catch(Exception e){
				Assert.fail("Nao devia ter lancado excacao");
			}
				
		}catch(Exception e){
			Assert.fail("Nao devia ter lancado excecao");
		}
		
	}
	
	public void testarCadastrarItem(){
		
		UsuarioIF us1,us2, us3;
		
		try{
			us1 = new Usuario(" samirtf ", " Samir Trajano Feitosa ", " um endereco X");
			us2 = new Usuario(" Terrenus ", " Samir Trajano Feitosa ", " um endereco X");
			us3 = new Usuario(" Terry ", " Samir Trajano Feitosa ", " um endereco X");
			
			try{
				us1.cadastrarItem(null, " uma descricao ", " FILME ");
				Assert.fail("Era para ter lancado excecao");
			}catch(Exception ex){
				Assert.assertEquals("Nome inválido", ex.getMessage());
			}
			
			try{
				us1.cadastrarItem("    ", " uma descricao ", " FILME ");
				Assert.fail("Era para ter lancado excecao");
			}catch(Exception ex){
				Assert.assertEquals("Nome inválido", ex.getMessage());
			}
			
			try{
				Assert.assertEquals(0, us1.qntItens());
				String idItem01 = us1.cadastrarItem("  nomeItem  ", null, " FILME ");
				System.out.println(idItem01);
				Assert.assertEquals(1, us1.qntItens());
				Assert.assertTrue("O item deveria estar cadastrado", us1.existeItemID(idItem01));
				Assert.assertEquals("nomeItem", us1.getItem(idItem01).getNome());
				
			}catch(Exception ex){
				ex.printStackTrace();
				Assert.fail();
			}
			
			
			
		}catch(Exception e){
			Assert.fail("Nao devia ter lancado excecao na criacao do usuario");
		}
		
		
	}
	

	protected void tearDown() {
		System.out.println("Finalizando...");
	}
}