package sistema.notificacao;

import java.util.Date;
import java.util.GregorianCalendar;

import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;

/**
 * 
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 * @since 20/11/2011
 * @version 1.0
 *
 */
public class NotificacaoNovoAmigo implements Notificacao {
	private UsuarioIF usuario1;
	private UsuarioIF usuario2;
	private Date data;
	private Long id;

	public NotificacaoNovoAmigo(UsuarioIF usuario1, UsuarioIF usuario2) {
		this.usuario1 = usuario1;
		this.usuario2 = usuario2;
		this.data = new GregorianCalendar().getTime();
	}

	@Override
	public Date getData() {
		return data;
	}

	@Override
	public Notificacao setData(Date novaData) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMensagem(Usuario usuario) {
		if (usuario1.equals(usuario)) {
			return usuario1.getNome() + " e " + usuario2.getNome()
					+ " são amigos agora";
		}
		return usuario2.getNome() + " e " + usuario1.getNome()
				+ " são amigos agora";
	}

	@Override
	public Long getId() {
		return Long.valueOf(id);
	}

	@Override
	public Notificacao setId(String novoId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NotificacaoNovoAmigo) {
			NotificacaoNovoAmigo notificacao = (NotificacaoNovoAmigo) obj;
			return id.equals(notificacao.getId());
		}
		return false;
	}

}
