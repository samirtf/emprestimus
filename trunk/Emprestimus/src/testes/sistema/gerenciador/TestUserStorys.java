package testes.sistema.gerenciador;


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
	public void testUserStory01() {
		this.autenticacao.cadastraUsuario("nome", "login", "endereco", 5);
		
		
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
