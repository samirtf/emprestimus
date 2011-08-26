package testes.sistema.autenticacao;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import codigo.sistema.autenticacao.CartaoDeAcesso;

import excecoes.IDMalFormadoCAException;
import excecoes.LoginMalFormadoCAException;
import excecoes.SenhaMalFormadaCAException;

public class TestCartaoDeAcesso {
	
	private CartaoDeAcesso ca = new CartaoDeAcesso("umLogin", "umaSenha", 1);
	
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testaCartaoDeAcesso() throws LoginMalFormadoCAException, SenhaMalFormadaCAException, IDMalFormadoCAException{
		
		Assert.assertTrue(ca.getLogin() == "umLogin");
		Assert.assertTrue(ca.getSenha() == "umaSenha");
		Assert.assertTrue(ca.getID() == 1);
		
		ca.setLogin("outroLogin");
		ca.setSenha("outraSenha");
		ca.setID(2);
		
		Assert.assertTrue(ca.getLogin() == "outroLogin");
		Assert.assertTrue(ca.getSenha() == "outraSenha");
		Assert.assertTrue(ca.getID() == 2);
		
		CartaoDeAcesso ca2 = new CartaoDeAcesso("meuLogin", "umaSenhaDiferente", 3);
		CartaoDeAcesso ca3 = new CartaoDeAcesso("outroLogin", "umaSenhaDiferente", 4);
		
		Assert.assertFalse( ca.equals(ca2));
		Assert.assertTrue( ca.equals(ca3));
		
		// logar usuario
		Assert.assertFalse( ca.estaLogado() );
		ca.logar();
		Assert.assertTrue( ca.estaLogado() );
		
		// logoff usuario
		ca.logoff();
		Assert.assertFalse( ca.estaLogado() );
		
		
		// desativar usuario
		Assert.assertTrue( ca.estaAtivo() );
		ca.desativar();
		Assert.assertFalse( ca.estaAtivo() );
		
		// ativar usuario
		ca.ativar();
		Assert.assertTrue( ca.estaAtivo() );
		
	}
	

}
