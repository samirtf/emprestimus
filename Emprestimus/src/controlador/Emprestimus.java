package controlador;

public interface Emprestimus {
	
	
	public void criarUsuario( String login, String nome, String endereco );
	
	public int abrirSessao( String login );
	
	public String getAtributoUsuario( String login, String atributo );

}
