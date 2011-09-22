package sistema.mensagem;

import sistema.excecoes.ArgumentoInvalidoException;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;
import static sistema.utilitarios.Validador.*;

public class MensagemChat implements MensagemChatIF{
	
	UsuarioIF remetente;
	UsuarioIF destinatario;
	String assunto, mensagem, idRequisicaoEmprestimo;
	MensagemTipo tipo;
	
	public MensagemChat(UsuarioIF remetente, UsuarioIF destinatario, String assunto, 
			String mensagem, String idRequisicaoEmprestimo, MensagemTipo tipo ) throws ArgumentoInvalidoException{
		
		assertNaoNulo(remetente, Mensagem.REMETENTE_INVALIDO.getMensagem());
		assertNaoNulo(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		asserteTrue(true, Mensagem.DESTINATARIO_INEXISTENTE.getMensagem());
		assertNaoNulo(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem());
		assertStringNaoVazia(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem());
		assertNaoNulo(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem());
		assertStringNaoVazia(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem());
		
	}

	@Override
	public void setId(String geraIdProxEmprestimo) {
		// TODO Auto-generated method stub
		
	}

}
