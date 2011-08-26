package codigo.sistema.autenticacao;

import excecoes.CartaoDeAcessoCorrompidoCAException;
import excecoes.IDMalFormadoCAException;
import excecoes.LoginMalFormadoCAException;
import excecoes.SenhaMalFormadaCAException;

/**
 * Esta classe representa o cartao de acesso do usuario
 * 
 * @author Joeffison Silverio de Andrade, joeffisonsa@gmail.com
 * @version 1.02
 */
public class CartaoDeAcesso implements Comparable{
	
	private String login = null;
	private String senha = null;
	private Integer id = null;
	private boolean estaLogado = false;
	private boolean ativo = true;
	
	private CartaoDeAcesso(){}

	public CartaoDeAcesso(String login, String senha, Integer id) {
		setLogin(login);
		setSenha(senha);
		setID(id);
	}

	/**
	 * Recupera o login do cartao
	 * 
	 * @return
	 * 		Login do {@link CartaoDeAcesso}
	 * @throws LoginMalFormadoCAException
	 * 		Lanca uma excecao caso o login nao tenha sido formado devidamente.
	 */
	public String getLogin() throws LoginMalFormadoCAException {
		if(login == null) throw new LoginMalFormadoCAException();
		return login;
	}
	
	
	/**
	 * Recupera a senha do usuarioCA.
	 * 
	 * @return
	 * 		A senha do {@link CartaoDeAcesso}
	 * @throws SenhaMalFormadaCAException
	 * 		Lanca uma excecao caso a senha nao tenha sido formada devidamente.
	 */
	public String getSenha() throws SenhaMalFormadaCAException {
		if(senha == null) throw new SenhaMalFormadaCAException();
		return senha;
	}
	
	/**
	 * Recupera o ID do cartao
	 * 
	 * @return
	 * 		O ID do {@link CartaoDeAcesso}
	 * @throws IDMalFormadoCAException
	 * 		Lanca uma excecao caso ID nao tenha sido formado devidamente.
	 */
	public Integer getID() throws IDMalFormadoCAException {
		if(id == null) throw new IDMalFormadoCAException();
		return id;
	}
	
	
	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public void setID(Integer id) {
		this.id = id;
	}
	
	public void logar(){
		this.estaLogado = true;
	}
	
	public void logoff(){
		this.estaLogado = false;
	}
	
	public void ativar(){
		this.ativo = true;
	}
	
	public void desativar(){
		this.ativo = false;
	}
	
	public boolean estaAtivo(){
		return this.ativo;
	}
	
	public boolean estaLogado(){
		return this.estaLogado;
	}
	
	@Override
	public boolean equals(Object obj){
		if( !(obj instanceof CartaoDeAcesso) ) return false;
		try {
			return this.login.equals( ((CartaoDeAcesso) obj).getLogin());
		} catch (LoginMalFormadoCAException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public int compareTo(Object obj) {
		CartaoDeAcesso ca = (CartaoDeAcesso) obj;
		int resultado = 0;
		try {
			resultado = this.login.compareTo(ca.getLogin());
		} catch (LoginMalFormadoCAException e) {
			e.printStackTrace();
			resultado = Integer.MIN_VALUE;
		}
		return resultado;
	}

}