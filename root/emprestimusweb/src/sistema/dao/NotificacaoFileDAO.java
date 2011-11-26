package sistema.dao;

import sistema.notificacao.Notificacao;
import sistema.persistencia.NotificacaoRepositorio;

public class NotificacaoFileDAO implements NotificacaoDAO {

	@Override
	public String geraIdProxNotificacao() {
		return NotificacaoRepositorio.geraIdProxNotificacao();
	}

	@Override
	public String novaNotificacao(Notificacao notif) throws Exception {
		return NotificacaoRepositorio.getInstance().novaNotificacao(notif);
	}

	@Override
	public Notificacao recuperarNotificacao(String idNotificacao)
			throws Exception {
		return null;
	}

	@Override
	public int qntNotificacoes() {
		return 0;
	}

	@Override
	public boolean existeNotificacao(String idNotificacao) {
		return false;
	}

	@Override
	public void removerNotificacao(String idNotificacao) {
		
	}

	@Override
	public void zerarRepositorio() {
		
	}

}
