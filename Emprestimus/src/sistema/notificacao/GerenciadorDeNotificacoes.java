/**
 * 
 */
package sistema.notificacao;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import sistema.autenticacao.Autenticacao;
import sistema.emprestimo.BancoDeEmprestimos;
import sistema.emprestimo.Conta;
import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.ItemIF;
import sistema.persistencia.NotificacaoRepositorio;
import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;

/**
 * @author Mobile
 *
 */
public class GerenciadorDeNotificacoes {
	
	private static GerenciadorDeNotificacoes gerenciadorDeNotificacoes;
	private static Map<String, Rack> historicos;

	private GerenciadorDeNotificacoes() {
		historicos = new TreeMap<String, Rack>();
	}

	public static GerenciadorDeNotificacoes getInstance() {
		if (gerenciadorDeNotificacoes == null) {
			gerenciadorDeNotificacoes = new GerenciadorDeNotificacoes();

			return gerenciadorDeNotificacoes;
		}
		return gerenciadorDeNotificacoes;
	}

	public void adicionaRackAoUsuario(String usuario) throws Exception {
		if(historicos.containsKey(usuario)) throw new Exception(Mensagem.PROPRIETARIO_RACK_JAH_CADASTRADO.getMensagem());
		historicos.put(usuario, new Rack(usuario));
		
	}
	
	public void removeRackDoUsuario(String usuario) throws Exception {
		historicos.remove(usuario);
	}
	
	public Rack getRack(String login) throws Exception {
		return historicos.get(login);
	}
	
	
	public void addHistoricoNovoItem(String seuLogin, ItemIF item) throws Exception {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoNovoItem(usuario, item);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);
		//addNotificacao(notif);
	}
	
	public void addHistoricoAmizadeAprovada(String seuLogin, UsuarioIF amigo) throws Exception {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoNovoAmigo(usuario, amigo);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);
		historicos.get(amigo.getLogin()).addNotificacao(notif);
//		this.addNotificacao(notif);
//		amigo.addNotificacao(notif);
	}
	
	public void addHistoricoEmprestimoEmAndamento(String seuLogin, UsuarioIF amigo, ItemIF item) throws Exception {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoEmprestimoAndamento(usuario, amigo, item);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);
		historicos.get(amigo.getLogin()).addNotificacao(notif);
//		this.addNotificacao(notif);
//		amigo.addNotificacao(notif);
		
	}

	
	public void zerarHistorico(String seuLogin) {
		historicos.get(seuLogin).zerarHistorico();
	}
	

	public String getHistoricoToString(String seuLogin) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		
		StringBuffer sb = new StringBuffer();
		Iterator<Notificacao> iterador = historicos.get(seuLogin).iterador();
		while (iterador.hasNext()) {
			sb.append(iterador.next().getMensagem(usuario));
			sb.append("; ");
		}
		if(sb.toString().equals("")) 
			return Mensagem.HISTORICO_VAZIO.getMensagem();
		return sb.toString().substring(0, sb.length()-2);
	}
	
	public void addHistoricoInteressePorItem(String seuLogin, UsuarioIF amigo, ItemIF item) throws Exception {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoRegistrarInteresseItem(usuario, amigo, item);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);
		
	}

	public void addHistoricoTerminoEmprestimo(String seuLogin, ItemIF item) throws Exception {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoTerminoEmprestimo(usuario, item);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);
		
	}

}
