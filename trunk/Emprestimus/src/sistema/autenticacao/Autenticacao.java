package sistema.autenticacao;

import java.util.Map;
import java.util.TreeMap;

import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;

public class Autenticacao implements AutenticacaoIF{
	
	// Mapa dos usuarios cadastrados no sistema
	private Map<String, UsuarioIF> usuariosCadastrados = new TreeMap<String, UsuarioIF>();
	
	// Mapa das sessoes de usuarios logados no sistema
	private Map<String, UsuarioIF> sessoes = new TreeMap<String, UsuarioIF>();
	
	

	@Override
	public void zerarSistema() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void encerrarSistema() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean criarUsuario(String login, String nome, String endereco) throws Exception {
		if( login != null && !existeUsuario(login) ){
			usuariosCadastrados.put(login, new Usuario(login, nome, endereco));
			return true;
		}
		return false;
	}

	@Override
	public String abrirSessao(String login) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAtributoUsuario(String login, String aributo) {
		// TODO Auto-generated method stub
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

}
