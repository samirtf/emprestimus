package iu.web.server.sistema.dao;

import java.io.Serializable;

import iu.web.server.sistema.mensagem.ChatIF;
import iu.web.server.sistema.mensagem.Correio;

public class CorreioFileDAO implements CorreioDAO, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1720105466378499225L;

	@Override
	public synchronized void adicionaCaixaPostalAoUsuario(String usuario) throws Exception {
		Correio.getInstance().adicionaCaixaPostalAoUsuario(usuario);
	}

	@Override
	public synchronized void removeCaixaPostalDoUsuario(String usuario) throws Exception {
		Correio.getInstance().removeCaixaPostalDoUsuario(usuario);
	}

	@Override
	public synchronized void adicionaConversaOfftopicNaLista(String usuario, ChatIF conversa)
			throws Exception {
		Correio.getInstance().adicionaConversaOfftopicNaLista(usuario, conversa);
	}

	@Override
	public synchronized void adicionaConversaNegociacaoNaLista(String usuario,
			ChatIF conversa) throws Exception {
		Correio.getInstance().adicionaConversaNegociacaoNaLista(usuario, conversa);
	}

	@Override
	public String enviarMensagemOffTopic(String remetente, String destinatario,
			String assunto, String mensagem) throws Exception {
		return Correio.getInstance().enviarMensagemOffTopic(remetente, destinatario, assunto, mensagem);
	}

	@Override
	public String enviarMensagemEmprestimo(String remetente,
			String destinatario, String assunto, String mensagem,
			String idRequisicaoEmprestimo) throws Exception {
		return Correio.getInstance().enviarMensagemEmprestimo(remetente, destinatario, assunto, mensagem, idRequisicaoEmprestimo);
	}

	@Override
	public String lerTopicos(String proprietario, String tipo) throws Exception {
		return Correio.getInstance().lerTopicos(proprietario, tipo);
	}

	@Override
	public String enviarMensagemOferecimentoItemOffTopic(String remetente,
			String destinatario, String assunto, String mensagem)
			throws Exception {
		return Correio.getInstance().enviarMensagemOferecimentoItemOffTopic(remetente, destinatario, assunto, mensagem);
	}

	@Override
	public synchronized void zerarSistema() {
		Correio.getInstance().zerarSistema();		
	}

	@Override
	public synchronized void notificaPersistenciaDoSistema() {
		Correio.getInstance().salvarEmArquivo();
	}

	@Override
	public synchronized void iniciarDAO() {
		Correio.getInstance();
	}
	
	@Override
	public synchronized void iniciarListener() {
		iniciarDAO();
	}

}
