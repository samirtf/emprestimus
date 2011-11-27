package iu.web.server.sistema.dao;

import iu.web.server.sistema.mensagem.ChatIF;
import iu.web.server.sistema.persistencia.ChatRepositorio;

public class ChatFileDAO implements ChatDAO{

	@Override
	public synchronized String geraIdProxConversa() {
		return ChatRepositorio.getInstance().geraIdProxConversa();
	}

	@Override
	public synchronized String registrarConversa(ChatIF mensagem) throws Exception {
		return ChatRepositorio.getInstance().registrarConversa(mensagem);
	}

	@Override
	public synchronized ChatIF recuperarConversa(String idConversa) throws Exception {
		return ChatRepositorio.getInstance().recuperarConversa(idConversa);
	}

	@Override
	public synchronized String getAtributoConversa(String idConversa, String atributo)
			throws Exception {
		return ChatRepositorio.getInstance().getAtributoConversa(idConversa, atributo);
	}

	@Override
	public synchronized int qntMensagens() {
		return ChatRepositorio.getInstance().qntMensagens();
	}

	@Override
	public synchronized boolean existeConversa(String idConversa) {
		return ChatRepositorio.getInstance().existeConversa(idConversa);
	}

	@Override
	public synchronized ChatIF existeConversaEntreAsPessoasSobreMesmoAssuntoETipo(
			String remetente, String destinatario, String assunto,
			boolean ehOffTopic) {
		return ChatRepositorio.getInstance().existeConversaEntreAsPessoasSobreMesmoAssuntoETipo(remetente, destinatario, assunto, ehOffTopic);
	}

	@Override
	public synchronized void zerarRepositorio() {
		ChatRepositorio.getInstance().zerarRepositorio();	
	}

	@Override
	public void notificaPersistenciaDoSistema() {
		ChatRepositorio.getInstance().salvarEmArquivo();
	}

	@Override
	public void iniciarDAO() {
		ChatRepositorio.getInstance();
	}
	
	@Override
	public void iniciarListener() {
		iniciarDAO();
	}

}
