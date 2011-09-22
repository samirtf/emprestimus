package sistema.mensagem;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sistema.excecoes.ArgumentoInvalidoException;
import sistema.persistencia.EmprestimoRepositorio;
import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;
import static sistema.utilitarios.Validador.*;

public class Chat implements ChatIF{
	
	String idMensagem;
	UsuarioIF remetente;
	UsuarioIF destinatario;
	String assunto, idRequisicaoEmprestimo;
	List<MensagemChatIF> conversa;
	MensagemTipo tipo;
	
	public Chat(UsuarioIF remetente, UsuarioIF destinatario, String assunto, 
			String mensagem, String idRequisicaoEmprestimo ) throws ArgumentoInvalidoException{
		
		setRemetente(remetente);
		setDestinatario(destinatario);
		setAssunto(assunto);
		this.conversa = new LinkedList<MensagemChatIF>();
		adicionaMensagem(mensagem);
		setIdRequisicaEmprestimo(idRequisicaoEmprestimo);
		this.tipo = MensagemTipo.NEGOCIACAO;
		
	}

	public Chat(UsuarioIF remetente, UsuarioIF destinatario, String assunto,
			String mensagem ) throws ArgumentoInvalidoException {
		
		setRemetente(remetente);
		setDestinatario(destinatario);
		setAssunto(assunto);
		adicionaMensagem(mensagem);
		setTipoOffTopicMsg();
		
	}

	@Override
	public void setTipoOffTopicMsg() {
		this.tipo = MensagemTipo.OFF_TOPIC;
	}
	
	@Override
	public void adicionaMensagem(String mensagem) throws ArgumentoInvalidoException {
		assertNaoNulo(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem());
		assertStringNaoVazia(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem());
		this.conversa.add( new MensagemChat(mensagem) );
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
		this.idRequisicaoEmprestimo = idRequisicaoEmprestimo;
	}

	@Override
	public void setTipoNegociacaoMsg() {
		tipo = MensagemTipo.NEGOCIACAO;
	}

	@Override
	public String getMensagem() {
		return this.conversa.toString();
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

	@Override
	public String getConversa() throws Exception {
		Iterator<MensagemChatIF> iterador = this.conversa.iterator();
		StringBuffer conversa = new StringBuffer();
		
		while(iterador.hasNext()){
			conversa.append(iterador.next().getMensagem()+"; ");
		}
		
		if(conversa.toString().equals(""))
			throw new Exception("Conversa sem mensagem vazia");
		return conversa.toString().trim();
	}
	
	public static void main(String[] args) throws ArgumentoInvalidoException, Exception{
		
		ChatIF conversa = new Chat(new Usuario("samir1", "Samir1", "endereco1"), new Usuario("samir2", "Samir2", "endereco2"),
				"Uma conversa espretensiosa", "Uma mensagem nada a ver", "id");
		
		conversa.adicionaMensagem("Opaaa");
		conversa.adicionaMensagem("Suruba");
		
		System.out.println(conversa.getConversa());
		
	}

	@Override
	public boolean ehConversaOfftopic() {
		return this.tipo == MensagemTipo.OFF_TOPIC;
	}
	
	
}
