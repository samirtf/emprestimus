package sistema.notificacao;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sistema.emprestimo.EmprestimoIF;
import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.ItemIF;
import sistema.mensagem.ChatIF;
import sistema.usuario.UsuarioIF;

/**
 * Representa o Historico do {@link UsuarioIF}
 * 
 * @author Joeffison Silverio de Andrade, 21011853, joeffisonsa@gmail.com
 * @since 23/10/2011
 */
public class Historico implements HistoricoIF {
	
	private UsuarioIF dono_do_historico;
	private List<Notificacao> atividades;
	
	
	public Historico(UsuarioIF dono_do_historico) {
		this.dono_do_historico = dono_do_historico;
		this.atividades = new LinkedList<Notificacao>();
	}
	
	@Override
	public Iterator<Notificacao> getIteratorHistorico() {
		final Iterator<Notificacao> iterator = this.atividades.iterator();
		
		return new Iterator<Notificacao>() {
			
			@Override
			public boolean hasNext() {
				return iterator.hasNext();
			}

			@Override
			public Notificacao next() {
				return iterator.next();
			}

			@Override
			/**
			 * Como esse iterator foi criado para não se modificar a lista com as atividades,
			 * 	o método remover() foi desabilitado.
			 * 
			 */
			public void remove(){}
				
			
		};
	}

	@Override
	public void notificaNovaAmizade(Notificacao notificacao) {
		this.atividades.add(notificacao);	
	}

	@Override
	public void notificaAmizadeRemovida(Notificacao notificacao) {
		// FIXME Remover atividades.
	}


}
