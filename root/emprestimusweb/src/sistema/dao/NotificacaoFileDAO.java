package sistema.dao;

import sistema.notificacao.Notificacao;
import sistema.persistencia.NotificacaoRepositorio;

public class NotificacaoFileDAO implements NotificacaoDAO {

	@Override
	public synchronized String geraIdProxNotificacao() {
		return NotificacaoRepositorio.geraIdProxNotificacao();
	}

	@Override
	public synchronized String novaNotificacao(Notificacao notif) throws Exception {
		return NotificacaoRepositorio.getInstance().novaNotificacao(notif);
	}

	@Override
	public synchronized Notificacao recuperarNotificacao(String idNotificacao)
			throws Exception {
		return null;
	}

	@Override
	public synchronized int qntNotificacoes() {
		return 0;
	}

	@Override
	public synchronized boolean existeNotificacao(String idNotificacao) {
		return false;
	}

	@Override
	public synchronized void removerNotificacao(String idNotificacao) {
		
	}

	@Override
	public synchronized void zerarRepositorio() {
		
	}

}
