package testes.logica.pessoas;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import codigo.logica.pessoas.Usuario;

public class TestUsuario {
	private Usuario usuario;

	@Before
	public void setUp() throws Exception {
		usuario.ID_Prox_Usuario = 1;
		usuario = new Usuario("nome", "login", "endereco");
	}

	@After
	public void tearDown() throws Exception {
		Usuario.resetIDClasse(); //Zerar o contador estatico de ID da Classe Usuario.
		usuario = null;
	}
	
	@Test
	public void testGetId() throws Exception {
		setUp();
		System.out.println("aqui");
		System.out.println(usuario.ID_Prox_Usuario);
		//Pois o primeiro usuario ja foi cadastrado no setUp().
		assertTrue(2 == usuario.ID_Prox_Usuario);
		assertTrue(1 == usuario.getId());
		
		Usuario usuario2 = new Usuario("nome2", "login3", "endereco");
		assertTrue(2 == usuario2.getId());
		
		Usuario usuario3 = new Usuario("nome3", "login3", "endereco");
		assertTrue(3 == usuario3.getId());
		
		tearDown();
	}
	
	@Test
	public void testCompareTo() throws Exception {
		setUp();
		Usuario usuario2 = new Usuario("nome", "login", "endereco");
		System.out.println(usuario2.getId());
		System.out.println(Usuario.ID_Prox_Usuario);
		Usuario usuario3 = new Usuario("nome", "login", "endereco");
		
		assertTrue(usuario.compareTo(usuario) == 0);
		assertTrue(usuario2.compareTo(usuario2) == 0);
		assertTrue(usuario3.compareTo(usuario3) == 0);
		assertTrue(usuario.compareTo(usuario2) < 0);
		assertTrue(usuario2.compareTo(usuario) > 0);
		assertTrue(usuario.compareTo(usuario3) < 0);
		assertTrue(usuario3.compareTo(usuario) > 0);
		assertTrue(usuario2.compareTo(usuario3) < 0);
		assertTrue(usuario3.compareTo(usuario2) > 0);
		tearDown();
	}

	@Test
	public void testEquals_HashCode() throws Exception {
		setUp();
		Usuario usuario2 = new Usuario("nome", "login", "endereco");
		assertFalse(usuario.getId() == usuario2.getId());
		assertTrue(usuario.equals(usuario2));
		assertFalse(usuario.hashCode() == usuario2.hashCode());
		
		tearDown();
	}

	@Test //TODO Este teste esta quebrando, precisamos decidir se ele tem que ficar ou não! 
	public void testUsuario() throws Exception {
		setUp();
		usuario = null;
		try { //nome = null
			usuario = new Usuario(null, "login", "endereco");
			fail("Deveria ter lancado uma excecao de argumentos ilegais");
		} catch (IllegalArgumentException e) {
			usuario = null;
		}
		try { //login = null
			usuario = new Usuario("nome", null, "endereco");
			fail("Deveria ter lancado uma excecao de argumentos ilegais");
		} catch (IllegalArgumentException e) {
			usuario = null;
		}
		try { //endereco = null
			usuario = new Usuario("nome", "login", null);
			fail("Deveria ter lancado uma excecao de argumentos ilegais");
		} catch (IllegalArgumentException e) {
			usuario = null;
		}
		try { //Strings vazias [não roda]
			usuario = new Usuario("", "", "");
			
		} catch (IllegalArgumentException e) {
			usuario = null;
		}
		try { //Strings com simbolos e/ou espacos [ não roda]
			usuario = new Usuario(" ", " .   ", "*@# $%¨&()!/? | ");
			fail("Deveria ter lancado uma excecao de argumentos ilegais");
		} catch (IllegalArgumentException e) {
			usuario = null;
		}
		try { //Strings com simbolos e/ou espacos [ não roda]
			usuario = new Usuario("usuario", " ", "*@# $%¨&()!/? | ");
			fail("Deveria ter lancado uma excecao de argumentos ilegais");
		} catch (IllegalArgumentException e) {
			usuario = null;
		}
		try { //Strings com simbolos e/ou espacos [ não roda]
			usuario = new Usuario("samir", " ops ", "*@# $%¨&()!/? | ");
			
		} catch (IllegalArgumentException e) {
			usuario = null;
			fail("Nao deveria ter lancado uma excecao de argumentos ilegais");
		}
		tearDown();
	}

	@Test
	public void testSolicitarAmizade() throws Exception {
		setUp();
		Usuario amigo = new Usuario("nomeAmigo", "loginAmigo", "enderecoAmigo");
		
		assertEquals(0, usuario.getNumSolicitacoes());
		assertEquals(0, usuario.getNumAmigos());
		assertEquals(0, amigo.getNumSolicitacoes());
		assertEquals(0, amigo.getNumAmigos());

		assertFalse(usuario.temAmigo(amigo));
		assertFalse(amigo.temAmigo(usuario));
		
		try {
			assertTrue(usuario.solicitarAmizade(amigo));
			assertEquals(0, usuario.getNumSolicitacoes());
			assertEquals(0, usuario.getNumAmigos());
			assertEquals(1, amigo.getNumSolicitacoes());
			assertEquals(0, amigo.getNumAmigos());

			assertFalse(usuario.solicitarAmizade(amigo));
			assertFalse(usuario.solicitarAmizade(amigo));
			assertFalse(usuario.solicitarAmizade(amigo));
			assertEquals(1, amigo.getNumSolicitacoes());
			
			assertTrue(amigo.solicitarAmizade(usuario));
			assertEquals(0, usuario.getNumSolicitacoes());
			assertEquals(1, usuario.getNumAmigos());
			assertEquals(0, amigo.getNumSolicitacoes());
			assertEquals(1, amigo.getNumAmigos());
		} catch (Exception e) {
			fail("Nenhuma excecao esperada");
		}
		
		assertTrue(usuario.temAmigo(amigo));
		assertTrue(amigo.temAmigo(usuario));
		
		try {
			usuario.solicitarAmizade(amigo);
			fail("Esperava-se excesao de usuario existente na lista de amigos");
		} catch (Exception e) {
			// Passe
		}
		
		try {
			amigo.solicitarAmizade(usuario);
			fail("Esperava-se excesao de usuario existente na lista de amigos");
		} catch (Exception e) {
			// Passe
		}
		tearDown();
	}

	@Test
	public void testAceitarAmizade() throws Exception {
		setUp();
		Usuario amigo1 = new Usuario("nome1", "login1", "endereco1");
		Usuario amigo2 = new Usuario("nome2", "login2", "endereco2");

		assertEquals(0, usuario.getNumSolicitacoes());
		assertEquals(0, usuario.getNumAmigos());
		assertEquals(0, amigo1.getNumSolicitacoes());
		assertEquals(0, amigo1.getNumAmigos());
		assertEquals(0, amigo2.getNumSolicitacoes());
		assertEquals(0, amigo2.getNumAmigos());
		try {
			assertTrue(amigo1.solicitarAmizade(usuario));
			assertTrue(amigo2.solicitarAmizade(usuario));
		} catch (Exception e) {
			fail("Nenhuma excecao esperada");
		}
		assertEquals(2, usuario.getNumSolicitacoes());
		assertEquals(0, usuario.getNumAmigos());
		assertEquals(0, amigo1.getNumSolicitacoes());
		assertEquals(0, amigo1.getNumAmigos());
		assertEquals(0, amigo2.getNumSolicitacoes());
		assertEquals(0, amigo2.getNumAmigos());
		
		assertTrue(usuario.aceitarAmizade(amigo1));
		
		assertEquals(1, usuario.getNumSolicitacoes());
		assertEquals(1, usuario.getNumAmigos());
		assertEquals(0, amigo1.getNumSolicitacoes());
		assertEquals(1, amigo1.getNumAmigos());
		assertEquals(0, amigo2.getNumSolicitacoes());
		assertEquals(0, amigo2.getNumAmigos());

		assertTrue(usuario.aceitarAmizade(amigo2));
		assertFalse(usuario.aceitarAmizade(new Usuario("nomeNovo", "loginNovo", "enderecoNovo")));
		
		assertEquals(0, usuario.getNumSolicitacoes());
		assertEquals(2, usuario.getNumAmigos());
		assertEquals(0, amigo1.getNumSolicitacoes());
		assertEquals(1, amigo1.getNumAmigos());
		assertEquals(0, amigo2.getNumSolicitacoes());
		assertEquals(1, amigo2.getNumAmigos());
		
		assertTrue(usuario.temAmigo(amigo1));
		assertTrue(usuario.temAmigo(amigo2));
		assertTrue(amigo1.temAmigo(usuario));
		assertTrue(amigo2.temAmigo(usuario));
		assertFalse(amigo1.temAmigo(amigo2));
		assertFalse(amigo2.temAmigo(amigo1));
		
		tearDown();
	}

	@Test
	public void testRejeitarAmizade() throws Exception {
		setUp();
		Usuario amigo1 = new Usuario("nome1", "login1", "endereco1");
		Usuario amigo2 = new Usuario("nome2", "login2", "endereco2");

		assertEquals(0, usuario.getNumSolicitacoes());
		assertEquals(0, usuario.getNumAmigos());
		assertEquals(0, amigo1.getNumSolicitacoes());
		assertEquals(0, amigo1.getNumAmigos());
		assertEquals(0, amigo2.getNumSolicitacoes());
		assertEquals(0, amigo2.getNumAmigos());
		try {
			assertTrue(amigo1.solicitarAmizade(usuario));
			assertTrue(amigo2.solicitarAmizade(usuario));
		} catch (Exception e) {
			fail("Nenhuma excecao esperada");
		}
		assertEquals(2, usuario.getNumSolicitacoes());
		assertEquals(0, usuario.getNumAmigos());
		assertEquals(0, amigo1.getNumSolicitacoes());
		assertEquals(0, amigo1.getNumAmigos());
		assertEquals(0, amigo2.getNumSolicitacoes());
		assertEquals(0, amigo2.getNumAmigos());
		
		assertTrue(usuario.temSolicitacao(amigo1));
		assertTrue(usuario.temSolicitacao(amigo2));
		
		assertTrue(usuario.rejeitarAmizade(amigo1));
		assertTrue(usuario.rejeitarAmizade(amigo2));
		assertFalse(usuario.rejeitarAmizade(new Usuario("nomeNovo", "loginNovo", "enderecoNovo")));

		assertFalse(usuario.temSolicitacao(amigo1));
		assertFalse(usuario.temSolicitacao(amigo2));
		
		assertEquals(0, usuario.getNumSolicitacoes());
		assertEquals(0, usuario.getNumAmigos());
		assertEquals(0, amigo1.getNumSolicitacoes());
		assertEquals(0, amigo1.getNumAmigos());
		assertEquals(0, amigo2.getNumSolicitacoes());
		assertEquals(0, amigo2.getNumAmigos());
		
		assertFalse(usuario.temAmigo(amigo1));
		assertFalse(usuario.temAmigo(amigo2));
		assertFalse(amigo1.temAmigo(usuario));
		assertFalse(amigo2.temAmigo(usuario));
		assertFalse(amigo1.temAmigo(amigo2));
		assertFalse(amigo2.temAmigo(amigo1));
		
		tearDown();
	}


}
