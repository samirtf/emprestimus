package testes.logica.pessoas;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import codigo.logica.pessoas.Usuario;

public class TestUsuario {
	private Usuario usuario;
	private final int id = 10;

	@Before
	public void setUp() throws Exception {
		usuario = new Usuario("nome", "login", "endereco", id);
	}

	@After
	public void tearDown() throws Exception {
		usuario = null;
	}

	@Test
	public void testEquals_HashCode() {
		Usuario usuario2 = new Usuario("nome_diferente", "login_diferente", "endereco_diferente", id);
		assertFalse(usuario.equals(usuario2));
		assertEquals(usuario.hashCode(), usuario2.hashCode());
		
		usuario2 = new Usuario("nome_diferente", "login", "endereco_diferente", id);
		assertTrue(usuario.equals(usuario2));
		assertEquals(usuario.hashCode(), usuario2.hashCode());
		
		usuario2 = new Usuario("nome_diferente", "login", "endereco_diferente", id+1);
		assertFalse(usuario.equals(usuario2));
		assertFalse(usuario.hashCode() == usuario2.hashCode());
		
	}

	@Test
	public void testUsuario() {
		usuario = null;
		try { //nome = null
			usuario = new Usuario(null, "login", "endereco", id);
			fail("Deveria ter lancado uma excecao de argumentos ilegais");
		} catch (IllegalArgumentException e) {
			usuario = null;
		}
		try { //login = null
			usuario = new Usuario("nome", null, "endereco", id);
			fail("Deveria ter lancado uma excecao de argumentos ilegais");
		} catch (IllegalArgumentException e) {
			usuario = null;
		}
		try { //endereco = null
			usuario = new Usuario("nome", "login", null, id);
			fail("Deveria ter lancado uma excecao de argumentos ilegais");
		} catch (IllegalArgumentException e) {
			usuario = null;
		}
		try { //Strings vazias e ID zero [roda]
			usuario = new Usuario("", "", "", 0);
		} catch (IllegalArgumentException e) {
			fail("Nao deveria ter lancado excecao alguma");
		}
		try { //Strings com simbolos e/ou espacos e ID negativa [roda]
			usuario = new Usuario(" ", " .   ", "*@# $%¨&()!/? | ", -29);
		} catch (IllegalArgumentException e) {
			fail("Nao deveria ter lancado excecao alguma");
		}
	}

	@Test
	public void testSolicitarAmizade() {
		Usuario amigo = new Usuario("nomeAmigo", "loginAmigo", "enderecoAmigo", id+1);
		
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
		
	}

	@Test
	public void testAceitarAmizade() {
		Usuario amigo1 = new Usuario("nome1", "login1", "endereco1", id+1);
		Usuario amigo2 = new Usuario("nome2", "login2", "endereco2", id+2);

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
		assertFalse(usuario.aceitarAmizade(new Usuario("nomeNovo", "loginNovo", "enderecoNovo", 1)));
		
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
		
	}

	@Test
	public void testRejeitarAmizade() {
		Usuario amigo1 = new Usuario("nome1", "login1", "endereco1", id+1);
		Usuario amigo2 = new Usuario("nome2", "login2", "endereco2", id+2);

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
		assertFalse(usuario.rejeitarAmizade(new Usuario("nomeNovo", "loginNovo", "enderecoNovo", 1)));

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
	}

	@Test
	public void testGetId() {
		assertEquals(id, usuario.getId());
	}


}
