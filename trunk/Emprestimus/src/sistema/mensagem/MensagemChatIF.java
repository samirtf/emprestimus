package sistema.mensagem;

import static sistema.utilitarios.Validador.assertNaoNulo;
import static sistema.utilitarios.Validador.assertStringNaoVazia;
import sistema.excecoes.ArgumentoInvalidoException;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;

public interface MensagemChatIF {
		
	public void setTipoOffTopicMsg();
	
	public void setTipoNegociacaoMsg();

	public void setMensagem(String mensagem) throws ArgumentoInvalidoException;
	
	public String getMensagem();
	
	public void setAssunto(String assunto) throws ArgumentoInvalidoException;
	
	public String getAssunto();
	
	public void setDestinatario(UsuarioIF destinatario) throws ArgumentoInvalidoException;
	
	public UsuarioIF getDestinatario();

	public void setRemetente(UsuarioIF remetente) throws ArgumentoInvalidoException;
	
	public UsuarioIF getRemetente();

	public void setIdMensagem(String idMensagem);
	
	public String getIdMensagem();

	public void setIdRequisicaEmprestimo(String idRequisicaoEmprestimo) throws ArgumentoInvalidoException;
	
	public String getIdRequisicaoEmprestimo();


}
