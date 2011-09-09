package sistema.autenticacao;

import sistema.usuario.UsuarioIF;

public interface AutenticacaoIF {
	
	public void zerarSistema();
	
	public void encerrarSistema();
	
	public void criarUsuario( String login, String nome, String endereco ) throws Exception;
	
	public String abrirSessao( String login ) throws Exception;
	
	public String getAtributoUsuario( String login,  String atributo ) throws Exception;
	
	public boolean existeIdSessao(String idSessao);
	
	public UsuarioIF getUsuarioPeloIDSessao( String idSessao ) throws Exception;

}
