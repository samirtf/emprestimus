package testes.sistema.gerenciador;


import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import codigo.sistema.autenticacao.Autenticacao;

public class TestUserStorys {
	
	public Autenticacao autenticacao;
	
	@Before
	public void setUp() throws Exception {
		autenticacao = Autenticacao.getInstance();
	}
	
	@Test
	public void testUserStory01(){
		//Entradas validas
		String [] nomes_validos = {"Mark Zuckerberg"};
		String [] logins_validos = {"mark"};
		String [] enderecos_validos = {"Palo Alto, California"}; 
		String [] atributos_validos = {"nome", "endereco"};
		
		// Entradas invalidas
		String [] entrada_invalida = {null, "", " ", "    ", "            "};
		String [] logins_invalidos = {"login com espaco", "tem espaco", "J a v a", "We're can!",
									  null, "", " ", "    ", "            "};
		String [] atributos_invalidos = logins_invalidos;
		String [] atributos_inexistentes = {"nom", "name", "enderec", "endreco", "friz"};
		
		// Zerar sistema
		Assert.assertTrue(autenticacao.zerarSistema());
		
		// Criacao de usuarios
		for (int i = 0; i < logins_validos.length; i++) {
			Assert.assertTrue(this.autenticacao.criarUsuario(nomes_validos[i], logins_validos[i], enderecos_validos[i]));
		}
		
		try {
			this.autenticacao.criarUsuario("Mark", "mark", "Palo Alto, in California");
		} catch (Exception e) {
			Assert.assertEquals("Já existe um usuário com este login", e.getMessage());
		}
		
		for (String nome : entrada_invalida) {
			try {
				this.autenticacao.criarUsuario(nome, "login", "endereco");
			} catch (IllegalArgumentException e) {
				Assert.assertEquals("Nome inválido", e.getMessage());
			}
		}
		
		for (String login : logins_invalidos) {
			try {
				this.autenticacao.criarUsuario("nome", login, "endereco");
			} catch (IllegalArgumentException e) {
				Assert.assertEquals("Login inválido", e.getMessage());
			}
		}
				
		for (String endereco : entrada_invalida) {
			try {
				this.autenticacao.criarUsuario("nome", "login", endereco);
			} catch (IllegalArgumentException e) {
				Assert.assertEquals("Endereco inválido", e.getMessage());
			}
		}
		
		// Loggin
		Assert.assertTrue(autenticacao.abrirSessao("mark"));
		
		for (String login : logins_invalidos) {
			try {
				this.autenticacao.abrirSessao(login);
			} catch (IllegalArgumentException e) {
				Assert.assertEquals("Login inválido", e.getMessage());
			}
		}
		
		try {
			this.autenticacao.abrirSessao("xpto²");
			Assert.fail();
		} catch (IllegalArgumentException e) {
			Assert.assertEquals("Usuário inexistente", e.getMessage());
		}
		
		// GetAtributo
		for (int i = 0; i<logins_validos.length; i++) {
			Assert.assertEquals(nomes_validos[i], autenticacao.getAtributo(logins_validos[i], "nome"));
			Assert.assertEquals(enderecos_validos[i], autenticacao.getAtributo(logins_validos[i], "endereco"));
		}
		
		for (String login : logins_invalidos) {
			for (String atributo : atributos_validos) {
				try {
					this.autenticacao.getAtributo(login, atributo);
				} catch (IllegalArgumentException e) {
					Assert.assertEquals("Login inválido", e.getMessage());
				}
			}
		}
		
		for (String login : logins_validos) {
			for (String atributo : atributos_invalidos) {
				try {
					this.autenticacao.getAtributo(login, atributo);
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
					Assert.assertEquals("Atributo inválido", e.getMessage());
				}
			}
		}
		
		for (String login : logins_validos) {
			for (String atributo : atributos_inexistentes) {
				try {
					this.autenticacao.getAtributo(login, atributo);
				} catch (IllegalArgumentException e) {
					Assert.assertEquals("Atributo inexistente", e.getMessage());
				}
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
