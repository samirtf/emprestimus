package testes.sistema.autenticacao;


import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import codigo.sistema.autenticacao.Autenticacao;
import codigo.sistema.autenticacao.CartaoDeAcesso;

public class TestAutenticacao {
	
	private Autenticacao aut = null;
	
	@Before
	public void setUp() throws Exception {
		aut = new Autenticacao();
	}

	@After
	public void tearDown() throws Exception {
		aut = null;
	}
	
	@Test
	public void testaAdicUsuarioCA() throws Exception{
		setUp();
		
		Assert.assertTrue(aut.qntUsuarioCA() == 0);
		aut.adicUsuarioCA("Samir", "samir", 1);
		Assert.assertTrue(aut.qntUsuarioCA() == 1);
		
		aut.adicUsuarioCA("Samir", "samirtrajano", 1);
		Assert.assertTrue(aut.qntUsuarioCA() == 1);
		
		/* Observe que mesmo os logins sendo diferentes, se
		 * os objetos possuirem o mesmo id, o cadastro nao
		 * sera realizado.
		 */
		aut.adicUsuarioCA("Samara", "samaraTrajano", 1);
		Assert.assertTrue(aut.qntUsuarioCA() == 1);
		
		aut.adicUsuarioCA("Samara", "samirtrajano", 2);
		Assert.assertTrue(aut.qntUsuarioCA() == 2);
		
		aut.adicUsuarioCA("Gabriela", "samirtrajano", -1);
		Assert.assertTrue(aut.qntUsuarioCA() == 2);
		
		aut.adicUsuarioCA("Gabriela", "samirtrajano", 5);
		Assert.assertTrue(aut.qntUsuarioCA() == 3);

		tearDown();
	}
	
	@Test
	public void testarDeletarUsuarioCAPorLogin() throws Exception {
		setUp();
		
		Assert.assertTrue(aut.qntUsuarioCA() == 0);
		
		for( int i = 1; i <= 10; i++ ){
			aut.adicUsuarioCA("usuario"+i, "senha", i);
			Assert.assertTrue(aut.qntUsuarioCA() == i);
		}
		
		for( int i = 10; i > 0; i-- ){
			aut.deletarUsuarioCAPorLogin("usuario"+i);
			Assert.assertTrue(aut.qntUsuarioCA() == i-1);
		}
		
		tearDown();
	}
	
	@Test
	public void testarDeletarUsuarioCAPorID() throws Exception {
        setUp();
		
		Assert.assertTrue(aut.qntUsuarioCA() == 0);
		
		for( int i = 1; i <= 10; i++ ){
			aut.adicUsuarioCA("usuario"+i, "senha", i);
			Assert.assertTrue(aut.qntUsuarioCA() == i);
		}
		
		for( int i = 10; i > 0; i-- ){
			aut.deletarUsuarioCAPorID(i);
			Assert.assertTrue(aut.qntUsuarioCA() == i-1);
		}
		
		tearDown();
	}
	
	@Test
	public void testarDesativarUsuarioCAPorLogin() throws Exception {
		setUp();
		
		Assert.assertTrue(aut.qntUsuarioCA() == 0);
		
		for( int i = 1; i <= 10; i++ ){
			aut.adicUsuarioCA("usuario"+i, "senha", i);
			Assert.assertTrue(aut.qntUsuarioCA() == i);
			Assert.assertTrue(aut.qntUsuarioCA() == i);
		}
		
		for( int i = 10; i > 0; i-- ){
			aut.desativarUsuarioCAPorLogin("usuario"+i);
			aut.estahAtivoUsuarioCA(i);
		}
		
		tearDown();
	}
	
	@Test
	public void testarDesativarUsuarioCAPorID() throws Exception {
        setUp();
		
		Assert.assertTrue(aut.qntUsuarioCA() == 0);
		
		for( int i = 1; i <= 10; i++ ){
			aut.adicUsuarioCA("usuario"+i, "senha", i);
			Assert.assertTrue(aut.qntUsuarioCA() == i);
		}
		
		for( int i = 10; i > 0; i-- ){
			aut.desativarUsuarioCA(i);
			aut.estahAtivoUsuarioCA(i);
		}
		
		tearDown();
	}
	
	@Test
	public void testarLogonELogoffUsuarioCA() throws Exception{
		setUp();
		
		aut.adicUsuarioCA("samir", "umaSenha", 0);
		aut.adicUsuarioCA("samara", "umaSenha", 1);
		aut.adicUsuarioCA("yonara", "umaSenha", 2);
		
		Assert.assertFalse(aut.estahLogadoUsuarioCA("samir"));
		Assert.assertFalse(aut.estahLogadoUsuarioCA("samara"));
		Assert.assertFalse(aut.estahLogadoUsuarioCA("yonara"));
		
		Integer idUsuario1 = aut.logarUsuarioCA("samir", "naoVerificaSenhaAinda");
		Assert.assertTrue(idUsuario1 == 0);
		Assert.assertTrue(aut.estahLogadoUsuarioCA("samir"));
		Assert.assertFalse(aut.estahLogadoUsuarioCA("samara"));
		Assert.assertFalse(aut.estahLogadoUsuarioCA("yonara"));
		
		Integer idUsuario2 = aut.logarUsuarioCA("samara", "naoVerificaSenhaAinda");
		Assert.assertTrue(idUsuario2 == 1);
		Assert.assertTrue(aut.estahLogadoUsuarioCA("samir"));
		Assert.assertTrue(aut.estahLogadoUsuarioCA("samara"));
		Assert.assertFalse(aut.estahLogadoUsuarioCA("yonara"));
		
		Integer idUsuario3 = aut.logarUsuarioCA("yonara", "naoVerificaSenhaAinda");
		Assert.assertTrue(idUsuario3 == 2);
		Assert.assertTrue(aut.estahLogadoUsuarioCA("samir"));
		Assert.assertTrue(aut.estahLogadoUsuarioCA("samara"));
		Assert.assertTrue(aut.estahLogadoUsuarioCA("yonara"));
		
		aut.logoffUsuarioCA("samir");
		aut.logoffUsuarioCA(1);
		aut.logoffUsuarioCA("yonara");
		Assert.assertFalse(aut.estahLogadoUsuarioCA("samir"));
		Assert.assertFalse(aut.estahLogadoUsuarioCA("samara"));
		Assert.assertFalse(aut.estahLogadoUsuarioCA("yonara"));
		
		tearDown();
	}

}
