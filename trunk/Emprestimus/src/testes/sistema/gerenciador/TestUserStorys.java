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
		String [] entrada_invalida = {null, "", " ", "    ", "            "};
		String [] logins_invalidos = {"login com espaco", "tem espaco", "J a v a", "We're can!",
									  null, "", " ", "    ", "            "};
		
		
		Assert.assertTrue(this.autenticacao.cadastraUsuario("nome", "login", "endereco"));
		
		
		try {
			this.autenticacao.cadastraUsuario("nome", "login", "endereco");
		} catch (Exception e) {
			Assert.assertEquals("Já existe um usuário com este login", e.getMessage());
		}
		
		for (String nome : entrada_invalida) {
			try {
				this.autenticacao.cadastraUsuario(nome, "login", "endereco");
			} catch (IllegalArgumentException e) {
				Assert.assertEquals("Nome inválido", e.getMessage());
			}
		}
		
		for (String login : logins_invalidos) {
			try {
				this.autenticacao.cadastraUsuario("nome", login, "endereco");
			} catch (IllegalArgumentException e) {
				Assert.assertEquals("Login inválido", e.getMessage());
			}
		}
		
		for (String endereco : entrada_invalida) {
			try {
				this.autenticacao.cadastraUsuario("nome", "login", endereco);
			} catch (IllegalArgumentException e) {
				Assert.assertEquals("Endereco inválido", e.getMessage());
			}
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
