/**
 * 
 */
package sistema.notificacao;

import java.util.Map;
import java.util.TreeMap;

import sistema.autenticacao.Autenticacao;
import sistema.emprestimo.BancoDeEmprestimos;
import sistema.emprestimo.Conta;
import sistema.item.ItemIF;
import sistema.persistencia.NotificacaoRepositorio;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;

/**
 * @author Mobile
 *
 */
public class GerenciadorDeNotificacoes {
	
	private static GerenciadorDeNotificacoes gerenciadorDeNotificacoes;
	private static Map<String, Rack> rackDeNotificacoes;

	private GerenciadorDeNotificacoes() {
		rackDeNotificacoes = new TreeMap<String, Rack>();
	}

	public static GerenciadorDeNotificacoes getInstance() {
		if (gerenciadorDeNotificacoes == null) {
			gerenciadorDeNotificacoes = new GerenciadorDeNotificacoes();

			return gerenciadorDeNotificacoes;
		}
		return gerenciadorDeNotificacoes;
	}

	public void adicionaRackAoUsuario(String usuario) throws Exception {
		if(rackDeNotificacoes.containsKey(usuario)) throw new Exception(Mensagem.PROPRIETARIO_RACK_JAH_CADASTRADO.getMensagem());
		rackDeNotificacoes.put(usuario, new Rack(usuario));
		
	}
	
	public void removeRackDoUsuario(String usuario) throws Exception {
		rackDeNotificacoes.remove(usuario);
	}
	
	public Rack getRack(String login) throws Exception {
		return rackDeNotificacoes.get(login);
	}
	
	
	public void addHistoricoCadastrarItem(String seuLogin, ItemIF item) throws Exception {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoNovoItem(usuario, item);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		rackDeNotificacoes.get(seuLogin).getHistorico().add(notif);
		//addNotificacao(notif);
	}
	
	public void addHistoricoAmizadeAprovada(String seuLogin, UsuarioIF amigo) throws Exception {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoNovoAmigo(usuario, amigo);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		rackDeNotificacoes.get(seuLogin).getHistorico().add(notif);
		rackDeNotificacoes.get(amigo.getLogin()).getHistorico().add(notif);
//		this.addNotificacao(notif);
//		amigo.addNotificacao(notif);
	}
	
	public void addHistoricoEmprestimoEmAndamento(String seuLogin, UsuarioIF amigo, ItemIF item) throws Exception {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoEmprestimoAndamento(usuario, amigo, item);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		rackDeNotificacoes.get(seuLogin).getHistorico().add(notif);
		rackDeNotificacoes.get(amigo.getLogin()).getHistorico().add(notif);
//		this.addNotificacao(notif);
//		amigo.addNotificacao(notif);
		
	}


}
