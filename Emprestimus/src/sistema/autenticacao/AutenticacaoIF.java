package sistema.autenticacao;

public interface AutenticacaoIF {
	
	public void zerarSistema();
	
	public void encerrarSistema();
	
	public boolean criarUsuario( String login, String nome, String endereco );
	
	public String abrirSessao( String login );
	
	public String getAtributoUsuario( String login,  String aributo );
	

}
