package testes.unidade.sistema.usuario;
import sistema.persistencia.ItemRepositorio;
import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;
import junit.framework.Assert;
import junit.framework.Test;
import junit.framework.TestCase;


/**
 * Emprestimus
 * Projeto de Sistemas de Informação I
 * Universidade Federal de Campina Grande
 * 
 * Classe EmailTeste - Responsavel em realizar teste unitarios na classe E-mail.
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
				//cadastrando item para Usuario1
				Assert.assertEquals(0, ItemRepositorio.qntItens());
				Assert.assertEquals(0, us1.qntItens());
				String idItem01 = us1.cadastrarItem("  nomeItem  ", null, " FILME ");
				System.out.println(idItem01);
				Assert.assertEquals("1", idItem01);
				Assert.assertEquals(1, us1.qntItens());
				Assert.assertTrue("O item deveria estar cadastrado", us1.existeItemID(idItem01));
				Assert.assertEquals("nomeItem", us1.getItem(idItem01).getNome());
				Assert.assertEquals("", us1.getItem(idItem01).getDescricao());
				Assert.assertEquals("Filme", us1.getItem(idItem01).getCategoria());
				Assert.assertEquals("1", us1.getListaIdItens());
				Assert.assertEquals(1, ItemRepositorio.qntItens());
				
				//cadastrando item para Usuario2
				Assert.assertEquals(0, us2.qntItens());
				String idItem02 = us2.cadastrarItem("  nome Item  ", "  ", " FiLmE ");
				Assert.assertEquals("2", idItem02);
				System.out.println(idItem02);
				Assert.assertEquals(1, us2.qntItens());
				Assert.assertTrue("O item deveria estar cadastrado", us2.existeItemID(idItem02));
				Assert.assertEquals("nome Item", us2.getItem(idItem02).getNome());
				Assert.assertEquals("", us2.getItem(idItem02).getDescricao());
				Assert.assertEquals("Filme", us2.getItem(idItem02).getCategoria());
				Assert.assertEquals("2", us2.getListaIdItens());
				Assert.assertEquals(2, ItemRepositorio.qntItens());
				
				//cadastrando item para Usuario3
				Assert.assertEquals(0, us3.qntItens());
				String idItem03 = us3.cadastrarItem("  nome Item  ", " um jogo legal ", " jogo ");
				Assert.assertEquals("3", idItem03);
				System.out.println(idItem03);
				Assert.assertEquals(1, us3.qntItens());
				Assert.assertTrue("O item deveria estar cadastrado", us3.existeItemID(idItem03));
				Assert.assertEquals("nome Item", us3.getItem(idItem03).getNome());
				Assert.assertEquals("um jogo legal", us3.getItem(idItem03).getDescricao());
				Assert.assertEquals("Jogo", us3.getItem(idItem03).getCategoria());
				Assert.assertEquals("3", us3.getListaIdItens());
				Assert.assertEquals(3, ItemRepositorio.qntItens());
				
				// adicionando mais itens aos usuarios
				
				//cadastrando outro item 4 para Usuario1
				Assert.assertEquals(1, us1.qntItens());
				String idItem04 = us1.cadastrarItem("  nomeI4  ", "descricaoI4", " JOGO ");
				System.out.println(idItem04);
				Assert.assertEquals("4", idItem04);
				Assert.assertEquals(2, us1.qntItens());
				Assert.assertTrue("O item deveria estar cadastrado", us1.existeItemID(idItem04));
				Assert.assertEquals("nomeI4", us1.getItem(idItem04).getNome());
				Assert.assertEquals("descricaoI4", us1.getItem(idItem04).getDescricao());
				Assert.assertEquals("Jogo", us1.getItem(idItem04).getCategoria());
				Assert.assertEquals("1 4", us1.getListaIdItens());
				Assert.assertEquals(4, ItemRepositorio.qntItens());
				
				// cadastrando item 5 ao usuario 1
				Assert.assertEquals(2, us1.qntItens());
				String idItem05 = us1.cadastrarItem("  nomeI5  ", "descricaoI5", " JOGO ");
				System.out.println(idItem05);
				Assert.assertEquals("5", idItem05);
				Assert.assertEquals(3, us1.qntItens());
				Assert.assertTrue("O item deveria estar cadastrado", us1.existeItemID(idItem05));
				Assert.assertEquals("nomeI5", us1.getItem(idItem05).getNome());
				Assert.assertEquals("descricaoI5", us1.getItem(idItem05).getDescricao());
				Assert.assertEquals("Jogo", us1.getItem(idItem05).getCategoria());
				Assert.assertEquals("1 4 5", us1.getListaIdItens());
				Assert.assertEquals(5, ItemRepositorio.qntItens());
				
				// cadastrando item 6 ao usuario 2
				Assert.assertEquals(1, us2.qntItens());
				String idItem06 = us2.cadastrarItem("  nomeI6  ", "descricaoI6", " JOGO ");
				System.out.println(idItem06);
				Assert.assertEquals("6", idItem06);
				Assert.assertEquals(2, us2.qntItens());
				Assert.assertTrue("O item deveria estar cadastrado", us2.existeItemID(idItem06));
				Assert.assertEquals("nomeI6", us2.getItem(idItem06).getNome());
				Assert.assertEquals("descricaoI6", us2.getItem(idItem06).getDescricao());
				Assert.assertEquals("Jogo", us2.getItem(idItem06).getCategoria());
				Assert.assertEquals("2 6", us2.getListaIdItens());
				Assert.assertEquals(6, ItemRepositorio.qntItens());
				
				// testes de carga
				
				Assert.assertTrue("Usuario 1 deve possuir ate entao 3 itens cadastrados",
						us1.qntItens() == 3);
				Assert.assertTrue("Usuario 2 deve possuir ate entao 2 itens cadastrados",
						us2.qntItens() == 2);
				Assert.assertTrue("Usuario 1 deve possuir ate entao 1 itens cadastrados",
						us3.qntItens() == 1);
				
				
				// iniciando testes de carga para usuario 1
				for( int i = 0; i < 30; i++ ){
					us1.cadastrarItem("nome"+i, "descricao"+i, "JoGo" );
					us2.cadastrarItem("nome"+i, "descricao"+i, "LiVrO" );
					us3.cadastrarItem("nome"+i, "descricao"+i, "FiLmE" );
				}
				System.out.println(us3.getListaIdItens());
				Assert.assertEquals("1 4 5 7 10 13 16 19 22 25 28 31 34 37 40 43 46 49 52 55 58 61 64 67 70 73 76 79 82 85 88 91 94", us1.getListaIdItens());
				Assert.assertEquals("2 6 8 11 14 17 20 23 26 29 32 35 38 41 44 47 50 53 56 59 62 65 68 71 74 77 80 83 86 89 92 95", us2.getListaIdItens());
				Assert.assertEquals("3 9 12 15 18 21 24 27 30 33 36 39 42 45 48 51 54 57 60 63 66 69 72 75 78 81 84 87 90 93 96", us3.getListaIdItens());
				
				Assert.assertEquals(96, ItemRepositorio.qntItens()); // ha no total 96 itens
				
				
			}catch(Exception ex){
				Assert.fail("Nao era para ter lancado excecao");
			}
			
			
			
		}catch(Exception e){
			Assert.fail("Nao devia ter lancado excecao na criacao do usuario");
		}
		
		
	}
	

	protected void tearDown() {
		System.out.println("Finalizando...");
	}
}