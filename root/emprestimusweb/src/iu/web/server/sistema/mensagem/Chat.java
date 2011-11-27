package iu.web.server.sistema.mensagem;

import iu.web.server.sistema.excecoes.ArgumentoInvalidoException;
import iu.web.server.sistema.usuario.Usuario;
import iu.web.server.sistema.usuario.UsuarioIF;
import iu.web.server.sistema.utilitarios.Mensagem;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import static iu.web.server.sistema.utilitarios.Validador.*;

public class Chat implements ChatIF {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8256573020953616277L;
	String idMensagem;
	UsuarioIF remetente;
	UsuarioIF destinatario;
	String assunto, idRequisicaoEmprestimo;
	List<MensagemChatIF> conversa;
	MensagemTipo tipo;
	Date dataUltimaAtualizacao;

	public Chat(UsuarioIF remetente, UsuarioIF destinatario, String assunto,
			String mensagem, String idRequisicaoEmprestimo) throws ArgumentoInvalidoException {

		setRemetente(remetente);
		setDestinatario(destinatario);
		setAssunto(assunto);
		this.conversa = new LinkedList<MensagemChatIF>();
		adicionaMensagem(mensagem);
		setIdRequisicaEmprestimo(idRequisicaoEmprestimo);
		setTipoNegociacaoMsg();
		setDataUltimaAtualizacao();

	}

	public Chat(UsuarioIF remetente, UsuarioIF destinatario, String assunto,
			String mensagem) throws ArgumentoInvalidoException {

		setRemetente(remetente);
		setDestinatario(destinatario);
		setAssunto(assunto);
		this.conversa = new LinkedList<MensagemChatIF>();
		adicionaMensagem(mensagem);
		setTipoOffTopicMsg();
		setDataUltimaAtualizacao();
	}

	public synchronized void setDataUltimaAtualizacao() {
		this.dataUltimaAtualizacao = new GregorianCalendar().getTime();
	}

	@Override
	public void setTipoOffTopicMsg() {
		this.tipo = MensagemTipo.OFF_TOPIC;
	}

	@Override
	public void adicionaMensagem(String mensagem) throws ArgumentoInvalidoException {
		assertStringNaoVazia(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem(),
				Mensagem.MENSAGEM_INVALIDA.getMensagem());
		MensagemChat mensagemChat = new MensagemChat(mensagem);
		this.conversa.add(mensagemChat);
		this.dataUltimaAtualizacao = mensagemChat.getData();
	}

	@Override
	public void setAssunto(String assunto) throws ArgumentoInvalidoException {
		assertStringNaoVazia(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem(),
				Mensagem.ASSUNTO_INVALIDO.getMensagem());
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
		assertStringNaoVazia(idRequisicaoEmprestimo,
				Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem(),
				Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		this.idRequisicaoEmprestimo = idRequisicaoEmprestimo;
	}

	@Override
	public void setTipoNegociacaoMsg() {
		tipo = MensagemTipo.NEGOCIACAO;
	}

	@Override
	public String getMensagem() {
		StringBuffer saida = new StringBuffer();
		Iterator<MensagemChatIF> iterador = conversa.iterator();
		while (iterador.hasNext()) {
			saida.append(iterador.next().getMensagem());
		}

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

		while (iterador.hasNext()) {
			conversa.append(iterador.next().getMensagem() + "; ");
		}

		if (conversa.toString().equals(""))
			throw new Exception("Conversa sem mensagem");
		return conversa.toString().trim().substring(0,
				conversa.toString().trim().length() - 1);
	}

	public static void main(String[] args) throws ArgumentoInvalidoException, Exception {

		ChatIF conversa = new Chat(new Usuario("samir1", "Samir1", "endereco1"),
				new Usuario("samir2", "Samir2", "endereco2"),
				"Uma conversa espretensiosa", "Uma mensagem nada a ver", "id");

		conversa.adicionaMensagem("Opaaa");
		conversa.adicionaMensagem("Suco de uva");
	}

	@Override
	public boolean ehConversaOfftopic() {
		return this.tipo == MensagemTipo.OFF_TOPIC;
	}

	@Override
	public int compareTo(ChatIF outro) {
		return this.dataUltimaAtualizacao.compareTo(outro.getData());
	}

	@Override
	public Date getData() {
		return this.dataUltimaAtualizacao;
	}

}
