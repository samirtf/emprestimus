package sistema.mensagem;

import sistema.excecoes.ArgumentoInvalidoException;
import sistema.persistencia.EmprestimoRepositorio;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;
import static sistema.utilitarios.Validador.*;

public class MensagemChat implements MensagemChatIF{
	String idMensagem;
	UsuarioIF remetente;
	UsuarioIF destinatario;
	String assunto, mensagem, idRequisicaoEmprestimo;
	MensagemTipo tipo;
	
	public MensagemChat(UsuarioIF remetente, UsuarioIF destinatario, String assunto, 
			String mensagem, String idRequisicaoEmprestimo, MensagemTipo tipo ) throws ArgumentoInvalidoException{
		
		setRemetente(remetente);
		setDestinatario(destinatario);
		setAssunto(assunto);
		setMensagem(mensagem);
		this.tipo = tipo;
		
	}

	public MensagemChat(UsuarioIF remetente, UsuarioIF destinatario, String assunto,
			String mensagem ) throws ArgumentoInvalidoException {
		
		setRemetente(remetente);
		setDestinatario(destinatario);
		setAssunto(assunto);
		setMensagem(mensagem);
		setTipoOffTopicMsg();
		
	}

	@Override
	public void setTipoOffTopicMsg() {
		this.tipo = MensagemTipo.OFF_TOPIC;
	}
	
	

	@Override
	public void setMensagem(String mensagem) throws ArgumentoInvalidoException {
		assertNaoNulo(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem());
		assertStringNaoVazia(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem());
		this.mensagem = mensagem;
	}

	@Override
	public void setAssunto(String assunto) throws ArgumentoInvalidoException {
		assertNaoNulo(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem());
		assertStringNaoVazia(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem());
		this.assunto = assunto;
	}

	@Override
	public void setDestinatario(UsuarioIF destinatario) throws ArgumentoInvalidoException {
		assertNaoNulo(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		this.destinatario = destinatario;
	}

	@Override
	public void setRemetente(UsuarioIF remetente) throws ArgumentoInvalidoException {
		assertNaoNulo(remetente, Mensagem.REMETENTE_INVALIDO.getMensagem());
		this.remetente = remetente;
	}

	@Override
	public void setIdMensagem(String idMensagem) {
		this.idMensagem = idMensagem;
	}
	
	@Override
	public void setIdRequisicaEmprestimo(String idRequisicaoEmprestimo) throws ArgumentoInvalidoException {
		assertNaoNulo(idRequisicaoEmprestimo, Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		assertStringNaoVazia(idRequisicaoEmprestimo, Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		asserteTrue(EmprestimoRepositorio.existeEmprestimo(idRequisicaoEmprestimo.trim()), 
				Mensagem.ID_REQUISICAO_EMP_INEXISTENTE.getMensagem());
		this.idRequisicaoEmprestimo = idRequisicaoEmprestimo;
	}

	@Override
	public void setTipoNegociacaoMsg() {
		tipo = MensagemTipo.NEGOCIACAO;
	}

	@Override
	public String getMensagem() {
		return this.mensagem;
	}

	@Override
	public String getAssunto() {
		return this.assunto;
	}

	@Override
	public UsuarioIF getDestinatario() {
		return this.destinatario;
	}

	@Override
	public UsuarioIF getRemetente() {
		return this.remetente;
	}

	@Override
	public String getIdMensagem() {
		return this.idMensagem;
	}

	@Override
	public String getIdRequisicaoEmprestimo() {
		return this.idRequisicaoEmprestimo;
	}
	
	

}
