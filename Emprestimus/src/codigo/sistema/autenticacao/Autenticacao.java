package codigo.sistema.autenticacao;

import java.util.Iterator;
import java.util.TreeSet;


import excecoes.IDMalFormadoCAException;
import excecoes.LoginMalFormadoCAException;

public class Autenticacao {
	
	// armazena os cartoes de acesso dos usuarios
	private TreeSet<CartaoDeAcesso> autenticacao = new TreeSet<CartaoDeAcesso>();
	
	/**
	 * Adiciona cartao de acesso ao usuario.
	 * @param login 
	 *     O login do novo usuario.
	 * @param senha 
	 *     A senha do novo usuario.
	 * @param id
	 *     O id do novo usuario.
	 * @return
	 *     True - Se o usuario for cadastrado.
	 *     False -  Caso o usuario nao seja cadastrado por algum erro.
	 * @throws LoginMalFormadoCAException
	 * @throws IDMalFormadoCAException
	 */
	public boolean adicUsuarioCA(String login, String senha, Integer id) throws LoginMalFormadoCAException, IDMalFormadoCAException {
		Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
		
		while(iterador.hasNext()){
			CartaoDeAcesso ca = iterador.next();
			if( ca.getLogin().equals(login) || ca.getID().equals(id) 
					|| id < 0 )
				return false;
		}
		autenticacao.add(new CartaoDeAcesso(login, senha, id));
		return true;
	}
	
	public boolean deletarUsuarioCAPorLogin(String login){
        if(autenticacao.contains(new CartaoDeAcesso(login, "qualquerSenha", 0))){
        	autenticacao.remove(new CartaoDeAcesso(login, "qualquerSenha", 0));
        	return true;
        }
        
        return false;
	}
	
	public boolean deletarUsuarioCAPorID(Integer id) throws IDMalFormadoCAException{
        Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
        while(iterador.hasNext()){
        	CartaoDeAcesso ca = iterador.next();
        	if(ca.getID() == id){
        		autenticacao.remove(ca);
        		return true;
        	}
        }
        
        return false;
	}
	
	public boolean desativarUsuarioCAPorLogin(String login) throws LoginMalFormadoCAException{
        Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
        
		while(iterador.hasNext()){
			CartaoDeAcesso ca = iterador.next();
			if( ca.getLogin().equals(login) ){
				ca.desativar();
				return true;
			}	
		}
		return false;
	}
	
	private CartaoDeAcesso getUsuarioCA(String login) throws LoginMalFormadoCAException{
		Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
		while(iterador.hasNext()){
			CartaoDeAcesso ca = iterador.next();
			if(ca.getLogin().equals(login))
				return ca;
		}
		return null;
	}
	
	private CartaoDeAcesso getUsuarioCA(Integer id) throws LoginMalFormadoCAException, IDMalFormadoCAException{
		Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
		while(iterador.hasNext()){
			CartaoDeAcesso ca = iterador.next();
			if(ca.getID() == id)
				return ca;
		}
		return null;
	}
	
	public boolean desativarUsuarioCA(String login) throws LoginMalFormadoCAException{
		Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
		while(iterador.hasNext()){
			CartaoDeAcesso ca = iterador.next();
			if(ca.getLogin().equals(login)){
				ca.desativar();
				return true;
			}
				
		}
		return false;
	}
	
	public boolean desativarUsuarioCA(Integer id) throws IDMalFormadoCAException {
		Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
		while(iterador.hasNext()){
			CartaoDeAcesso ca = iterador.next();
			if(ca.getID() == id ){
				ca.desativar();
				return true;
			}		
		}
		return false;
	}
	
	public boolean ativarUsuarioCA(String login) throws LoginMalFormadoCAException{
		Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
		while(iterador.hasNext()){
			CartaoDeAcesso ca = iterador.next();
			if(ca.getLogin().equals(login)){
				ca.ativar();
				return true;
			}
				
		}
		return false;
	}
	
	public boolean ativarUsuarioCA(Integer id) throws IDMalFormadoCAException {
		Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
		while(iterador.hasNext()){
			CartaoDeAcesso ca = iterador.next();
			if(ca.getID() == id ){
				ca.ativar();
				return true;
			}		
		}
		return false;
	}
	
	/**
	 * Verifica se o usuarioCA estah ativo.
	 * @param login
	 *     O login do usuario.
	 * @return
	 *     True - Se o usuarioCA estiver ativo.
	 *     False - Se o usuario nao existir ou se ele estiver inativo.
	 * @throws LoginMalFormadoCAException
	 */
	public boolean estahAtivoUsuarioCA(String login) throws LoginMalFormadoCAException{
		Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
		while(iterador.hasNext()){
			CartaoDeAcesso ca = iterador.next();
			if(ca.getLogin().equals(login) ){
				return ca.estaAtivo();
			}		
		}
		return false;
	}
	
	/**
	 * Verifica se o usuarioCA estah ativo.
	 * @param id
	 *     O id do usuario.
	 * @return
	 *     True - Se o usuarioCA estiver ativo.
	 *     False - Se o usuarioCA nao existir ou se ele estiver inativo.
	 * @throws IDMalFormadoCAException 
	 */
	public boolean estahAtivoUsuarioCA(int id) throws IDMalFormadoCAException  {
		Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
		while(iterador.hasNext()){
			CartaoDeAcesso ca = iterador.next();
			if(ca.getID() == id ){
				return ca.estaAtivo();
			}		
		}
		return false;
	}
	
	public Integer logarUsuarioCA(String login, String senha) throws LoginMalFormadoCAException, IDMalFormadoCAException{
		//flag para logar sem senha.
		// Em vez de true compara a senha.
		Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
		while(iterador.hasNext()){
			CartaoDeAcesso ca = iterador.next();
			if(ca.getLogin().equals(login) && true){
				ca.logar();
				return ca.getID();
			}		
		}
		return Integer.MIN_VALUE;
	}
	
	public boolean logoffUsuarioCA(String login) throws LoginMalFormadoCAException{

		Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
		while(iterador.hasNext()){
			CartaoDeAcesso ca = iterador.next();
			if(ca.getLogin().equals(login)){
				ca.logoff();
				return true;
			}		
		}
		return false;
	}
	
	public boolean logoffUsuarioCA(Integer id) throws IDMalFormadoCAException{

		Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
		while(iterador.hasNext()){
			CartaoDeAcesso ca = iterador.next();
			if(ca.getID() == id){
				ca.logoff();
				return true;
			}		
		}
		return false;
	}
	
	/**
	 * Verifica se o usuarioCA estah logado.
	 * @param login
	 *     O login do usuario.
	 * @return
	 *     True - Se o usuarioCA estiver logado.
	 *     False - Se o usuario nao existir ou se ele estiver logado.
	 * @throws LoginMalFormadoCAException
	 */
	public boolean estahLogadoUsuarioCA(String login) throws LoginMalFormadoCAException{
		Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
		while(iterador.hasNext()){
			CartaoDeAcesso ca = iterador.next();
			if(ca.getLogin().equals(login) ){
				return ca.estaLogado();
			}		
		}
		return false;
	}
	
	/**
	 * Verifica se o usuarioCA estah logado.
	 * @param id
	 *     O id do usuario.
	 * @return
	 *     True - Se o usuarioCA estiver logado.
	 *     False - Se o usuarioCA nao existir ou se ele estiver logado.
	 * @throws IDMalFormadoCAException 
	 */
	public boolean estahLogadoUsuarioCA(int id) throws IDMalFormadoCAException  {
		Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
		while(iterador.hasNext()){
			CartaoDeAcesso ca = iterador.next();
			if(ca.getID() == id ){
				return ca.estaLogado();
			}		
		}
		return false;
	}
	
	/**
	 * Calcula a quantidade de usuariosCA cadastrados.
	 * @return
	 *     A quantidade de usuariosCA cadastrados.
	 */
	public int qntUsuarioCA(){
		return autenticacao.size();
	}
	
	public boolean alterarSenhaUsuarioCA(String login, String senhaAnterior, String novaSenha) throws LoginMalFormadoCAException{
		Iterator<CartaoDeAcesso> iterador = autenticacao.iterator();
		while(iterador.hasNext()){
			CartaoDeAcesso ca = iterador.next();
			if(ca.getLogin().equals(login) ){
				return ca.estaLogado();
			}		
		}
		return false;
	}
	
	public static void main(String[] args){
		
		TreeSet<CartaoDeAcesso> autenticacao = new TreeSet<CartaoDeAcesso>();
		
		autenticacao.add(new CartaoDeAcesso("Samir", "umaSenhaBasica", 4));
		
		Iterator<CartaoDeAcesso> it = autenticacao.iterator();
		
		CartaoDeAcesso ca = it.next();
		try {
			System.out.println(ca.getLogin());
		} catch (LoginMalFormadoCAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ca.setLogin("Maculele");
		
		Iterator<CartaoDeAcesso> at = autenticacao.iterator();
		CartaoDeAcesso ca2 = at.next();
		try {
			System.out.println(ca2.getLogin());
		} catch (LoginMalFormadoCAException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

}
