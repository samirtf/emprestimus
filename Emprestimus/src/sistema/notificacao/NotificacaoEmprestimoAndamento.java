package sistema.notificacao;

import java.util.Date;

import sistema.item.ItemIF;
import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;

/**
 * 
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 * @since 23/11/2011
 * @version 1.0
 *
 */
public class NotificacaoEmprestimoAndamento implements Notificacao {
	private UsuarioIF dono;
	private UsuarioIF beneficiado;
	private ItemIF item;

	private Date data;
	private Long id;

	/**
	 * @param dono
	 * @param beneficiado
	 * @param item
	 */
	public NotificacaoEmprestimoAndamento(UsuarioIF dono,
			UsuarioIF beneficiado, ItemIF item) {
		this.dono = dono;
		this.beneficiado = beneficiado;
		this.item = item;
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
		return dono.getNome() + " emprestou " + item.getNome() + " a "
				+ beneficiado.getNome();
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public Notificacao setId(String novoId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof NotificacaoEmprestimoAndamento) {
			NotificacaoEmprestimoAndamento notificacao = (NotificacaoEmprestimoAndamento) obj;
			return id.equals(notificacao.getId());
		}
		return false;
	}

}
