package iu;

/**
 * Interface geral do sistema. Para os �ntimos, chama-se Fachada =D.
 * @author Radix
 *
 */
public interface EmprestimusIF {

	//US01
	public void criarUsuario( String login, String nome, String endereco ) throws Exception;
	
	public String abrirSessao( String login ) throws Exception;
	
	public String getAtributoUsuario(String login, String atributo ) throws Exception;
	
	//US02
	public String cadastrarItem( String idSessao, String nome, String descricao, String categoria ) throws Exception;
	
	public String getAtributoItem( String idItem, String atributo ) throws Exception;
	
	//US03
	public String localizarUsuario( String idSessao, String chave, String atributo ) throws Exception;
	
	//US04
	public void requisitarAmizade( String idSessao, String login ) throws Exception;
	
	public String getRequisicoesDeAmizade( String idSessao ) throws Exception;
	
	public void aprovarAmizade( String idSessao, String login ) throws Exception;
	
	public String ehAmigo (String idSessao, String login ) throws Exception;
	
	//US05
	public String getAmigos( String idSessao ) throws Exception;
	
	public String getAmigos( String idSessao, String login ) throws Exception;
	
	public String getItens( String idSessao ) throws Exception;
	
	public String getItens( String idSessao, String login ) throws Exception;
	
	//US06
	public String requisitarEmprestimo( String idSessao, String idItem, int duracao );
	
	public void aprovarEmprestimo( String idSessao, String idRequisicaoEmprestimo );
	
	public String getEmprestimos( String idSessao, String tipo );
	
	//Utils
	public void zerarSistema();
	
	public void encerrarSistema();
	
	
}
