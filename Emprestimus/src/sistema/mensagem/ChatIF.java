package sistema.mensagem;

import static sistema.utilitarios.Validador.assertNaoNulo;
import static sistema.utilitarios.Validador.assertStringNaoVazia;

import java.util.Date;

import sistema.excecoes.ArgumentoInvalidoException;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;

public interface ChatIF extends Comparable<ChatIF>{
		
	public void setTipoOffTopicMsg();
	
	public void setTipoNegociacaoMsg();

	public void adicionaMensagem(String mensagem) throws ArgumentoInvalidoException;
	
	public void setAssunto(String assunto) throws ArgumentoInvalidoException;
	
	public void setDestinatario(UsuarioIF destinatario) throws ArgumentoInvalidoException;
		
	public void setRemetente(UsuarioIF remetente) throws ArgumentoInvalidoException;
		
	public void setIdMensagem(String idMensagem);
	    
	public void setIdRequisicaEmprestimo(String idRequisicaoEmprestimo) throws ArgumentoInvalidoException;
	
	public String getMensagem();
	
	public String getAssunto();
	
	public UsuarioIF getDestinatario();
	
	public UsuarioIF getRemetente();
		
	public String getIdMensagem();
	
	public String getIdRequisicaoEmprestimo();
	
	public String getConversa() throws Exception;
	
	public boolean ehConversaOfftopic();
	
	public Date getData();
	

}
