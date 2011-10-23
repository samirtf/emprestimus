package sistema.notificacao;

import java.util.Iterator;

public interface HistoricoIF extends UsuarioIFListenner {
	
	public Iterator<Notificacao> getIteratorHistorico();
	
	//public void addAtividade(Notificacao notificacao);
	/* Metodo removido pois o Historico extends o Listener,
	 * o que resolve o problema do método. */
}
