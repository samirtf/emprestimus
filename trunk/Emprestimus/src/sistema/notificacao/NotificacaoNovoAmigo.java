package sistema.notificacao;

import java.util.Date;

import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;

public class NotificacaoNovoAmigo implements Notificacao {
	UsuarioIF usuario1;
	UsuarioIF usuario2;
	Date data;
	Long id;

	public NotificacaoNovoAmigo(UsuarioIF usuario1, UsuarioIF usuario2) {
		this.usuario1 = usuario1;
		this.usuario2 = usuario2;
	}

	@Override
	public Date getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notificacao setData(Date novaData) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMensagem(Usuario usuario) {
		if (usuario1.equals(usuario)) {
			return usuario1.getNome() + " e " + usuario2.getNome() + " são amigos agora";
		}
		return usuario2.getNome() + " e " + usuario1.getNome() + " são amigos agora";
	}

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Notificacao setId(String novoId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
