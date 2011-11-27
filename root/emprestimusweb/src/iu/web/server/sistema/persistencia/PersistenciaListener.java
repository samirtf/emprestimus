package iu.web.server.sistema.persistencia;

import java.io.Serializable;

public interface PersistenciaListener extends Serializable {

	/**
	 * Notifica ao objeto a ordem de persistir seus dados.
	 */
	void notificaPersistenciaDoSistema();
	
	/**
	 * Inicia o listener.
	 */
	void iniciarListener();
	
}
