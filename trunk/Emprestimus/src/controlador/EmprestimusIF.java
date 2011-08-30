package controlador;

public interface EmprestimusIF {
	
	
	public void criarUsuario( String login, String nome, String endereco );
	
	public int abrirSessao( String login );
	
	public String getAtributoUsuario( String login, String atributo );
	
	public void zerarSistema();
	
	public void encerrarSistema();
	


}
