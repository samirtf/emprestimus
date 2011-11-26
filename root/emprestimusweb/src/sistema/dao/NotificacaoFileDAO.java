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
		return NotificacaoRepositorio.getInstance().recuperarNotificacao(idNotificacao);
	}

	@Override
	public synchronized int qntNotificacoes() {
		return NotificacaoRepositorio.getInstance().qntNotificacoes();
	}

	@Override
	public synchronized boolean existeNotificacao(String idNotificacao) {
		return NotificacaoRepositorio.getInstance().existeNotificacao(idNotificacao);
	}

	@Override
	public synchronized void removerNotificacao(String idNotificacao) {
		NotificacaoRepositorio.getInstance().removerNotificacao(idNotificacao);
	}

	@Override
	public synchronized void zerarRepositorio() {
		NotificacaoRepositorio.zerarRepositorio();
	}

	@Override
	public void notificaPersistenciaDoSistema() {
		NotificacaoRepositorio.getInstance().salvarEmArquivo();
	}

}
