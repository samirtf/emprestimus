package sistema.notificacao;

import java.util.Date;

import sistema.item.ItemIF;
import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;

public class NotificacaoNovoItem implements Notificacao {
	UsuarioIF usuario;
	ItemIF item;
	Date data;
	Long id;

	
	public NotificacaoNovoItem(UsuarioIF usuario, ItemIF item) {
		this.usuario = usuario;
		this.item = item;
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
		return usuario.getNome() + " cadastrou " + item.getNome();
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
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

}
