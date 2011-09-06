package sistema.autenticacao;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import sistema.excecoes.LoginJahExisteException;
import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;

public class Autenticacao implements AutenticacaoIF{
	
	// Mapa dos usuarios cadastrados no sistema
	private Map<String, UsuarioIF> usuariosCadastrados = new TreeMap<String, UsuarioIF>();
	
	// Mapa das sessoes de usuarios logados no sistema
	private Map<String, UsuarioIF> sessoes = new TreeMap<String, UsuarioIF>();
	private final int qntMaxSessoes = Integer.MAX_VALUE - 1024; // Quantidade maxima de sessoes.
	
	
	

	@Override
	public void zerarSistema() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encerrarSistema() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void criarUsuario(String login, String nome, String endereco) throws Exception {
		
		if( login == null || login.trim().equals("")) throw new Exception("Login inválido");
		if( nome == null || nome.trim().equals("")) throw new Exception("Nome inválido");
		if(endereco == null) endereco = "";
		if( existeUsuario(login) ) throw new LoginJahExisteException(login);
		// adiciona novo usuario no sistema
		usuariosCadastrados.put(login, new Usuario(login, nome, endereco));
		
	}

	
	@Override
	public String abrirSessao(String login) throws Exception {

		if( login == null || login.trim().equals("")) throw new Exception("Login inválido");
		if(!existeUsuario(login)) throw new Exception("Usuário inexistente");
		String idSessao = gerarIdSessao();
		while(existeIdSessao(idSessao)){
			idSessao = gerarIdSessao();
		}
		sessoes.put(idSessao, getUsuario(login));
		return idSessao;
		
	}

	@Override
	public String getAtributoUsuario(String login, String atributo) throws Exception{
		
		if( login == null || login.trim().equals("")) throw new Exception("Login inválido");
		if(!existeUsuario(login)) throw new Exception("Usuário inexistente");//Atributo inexistente
		if(atributo == null || atributo.trim().equals("")) throw new Exception("Atributo inválido");
		UsuarioIF usuario = getUsuario(login);
		Class cls = usuario.getClass();
		Field[] campos = cls.getDeclaredFields();
		for( Field f : campos){
			//if(f.toString().endsWith("."+atributo)) return usuario.
			System.out.println(f.getName());
			
		}
		
		
		
		//if() throw new Exception("Atributo inexistente");//Atributo inexistente
		return null;
	}
	
	/**
	 * Verifica se existe usuario cadastrado.
	 * @param login
	 * 		O login do usuario.
	 * @return
	 * 		True - Se o usuario ja estiver cadastrado.
	 * 		False - Caso contrario.
	 */
	private boolean existeUsuario( String login ){
		return usuariosCadastrados.containsKey(login);
	}
	
	private UsuarioIF getUsuario( String login ) throws Exception{
		return usuariosCadastrados.get(login);
	}
	
	/**
	 * Gera um idSessao.
	 * @return
	 * 		Um idSessao.
	 */
	private String gerarIdSessao(){
		Random rd = new Random();
		return String.valueOf((rd.nextInt(Integer.MAX_VALUE - 1024 - 1) + 1));
	}
	
	/**
	 * Verifica se um idSessao ja existe.
	 * @param idSessao
	 * 		Um idSessao.
	 * @return
	 * 		True - Se existir o idSessao.
	 * 		False - Se nao existir.
	 */
	private boolean existeIdSessao(String idSessao){
		return sessoes.containsKey(idSessao);
	}
	
	
	public static void main( String[] args) throws Exception{
		Autenticacao aut = new Autenticacao();
		aut.criarUsuario("samirtf", "Samir", "meuEndereco");
		
		aut.getAtributoUsuario("samirtf", "login");
	}
	

}
