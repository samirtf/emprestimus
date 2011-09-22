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
	public String requisitarEmprestimo( String idSessao, String idItem, int duracao ) throws Exception;
	
	public String aprovarEmprestimo( String idSessao, String idRequisicaoEmprestimo ) throws Exception;
	
	public String getEmprestimos( String idSessao, String tipo ) throws Exception;
	
	//US07
    public void devolverItem( String idSessao, String idEmprestimo ) throws Exception;
    
    public void confirmarTerminoEmprestimo( String idSessao, String idEmprestimo ) throws Exception;
    
    //US08
    //mensagm off-topic
    public String enviarMensagem( String idSessao, String destinatario, String assunto, String mensagem ) throws Exception;
    
    //relativa a negociacao
    public String enviarMensagem( String idSessao, String destinatario, String assunto, String mensagem, String idRequisicaoEmprestimo ) throws Exception;
    
    //Recupera a lista de tópicos do usuário, informando o tipo dos tópicos recuperados (negociação, off-topic ou todos). 
    //Esta lista deve ser ordenada pela data de criação, do tópico mais recente ao mais antigo.
    public String lerTopicos( String idSessao, String tipo ) throws Exception;
    
    //Recupera a lista de mensagens relativa a um tópico. 
    //Esta lista deve ser ordenada pela data de criação, da mensagem antiga à mais recente.
    public String lerMensagens( String idSessao, String idTopico ) throws Exception;
    
    //US09
	
	//Utils
	public void zerarSistema();
	
	public void encerrarSistema();
	
	
}
