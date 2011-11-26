package sistema.dao;

import sistema.mensagem.ChatIF;
import sistema.mensagem.Correio;

public class CorreioFileDAO implements CorreioDAO{

	@Override
	public void adicionaCaixaPostalAoUsuario(String usuario) throws Exception {
		Correio.getInstance().adicionaCaixaPostalAoUsuario(usuario);
	}

	@Override
	public void removeCaixaPostalDoUsuario(String usuario) throws Exception {
		Correio.getInstance().removeCaixaPostalDoUsuario(usuario);
	}

	@Override
	public void adicionaConversaOfftopicNaLista(String usuario, ChatIF conversa)
			throws Exception {
		Correio.getInstance().adicionaConversaOfftopicNaLista(usuario, conversa);
	}

	@Override
	public void adicionaConversaNegociacaoNaLista(String usuario,
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
	public void zerarSistema() {
		Correio.getInstance().zerarSistema();		
	}

	@Override
	public void notificaPersistenciaDoSistema() {
		// TODO Auto-generated method stub
		
	}

}
