package iu.web.server.sistema.usuario;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Emprestimus Projeto de Sistemas de Informação I Universidade Federal de
 * Campina Grande
 */

public class ReputacaoUsuarioComparator implements Comparator<UsuarioIF>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public int compare(UsuarioIF usuario1, UsuarioIF usuario2) {
		int reputacaoUsuario1 = usuario1.getReputacao();
		int reputacaoUsuario2 = usuario2.getReputacao();
		return reputacaoUsuario1 - reputacaoUsuario2;
	}
}
