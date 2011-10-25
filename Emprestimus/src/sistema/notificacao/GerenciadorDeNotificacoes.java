/**
 * 
 */
package sistema.notificacao;

import static sistema.utilitarios.Validador.assertStringNaoVazia;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import sistema.autenticacao.Autenticacao;
import sistema.excecoes.ArgumentoInvalidoException;
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
		if (historicos.containsKey(usuario))
			throw new Exception(
					Mensagem.PROPRIETARIO_RACK_JAH_CADASTRADO.getMensagem());
		historicos.put(usuario, new Rack(usuario));

	}

	public void removeRackDoUsuario(String usuario) throws Exception {
		historicos.remove(usuario);
	}

	public Rack getRack(String login) throws Exception {
		return historicos.get(login);
	}

	public void addHistoricoNovoItem(String seuLogin, ItemIF item)
			throws Exception {
		Validador.assertStringNaoVazia(seuLogin,
				Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoNovoItem(usuario, item);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);
		// addNotificacao(notif);
	}

	public void addHistoricoAmizadeAprovada(String seuLogin, UsuarioIF amigo)
			throws Exception {
		Validador.assertStringNaoVazia(seuLogin,
				Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoNovoAmigo(usuario, amigo);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);
		historicos.get(amigo.getLogin()).addNotificacao(notif);
		// this.addNotificacao(notif);
		// amigo.addNotificacao(notif);
	}

	public void addHistoricoEmprestimoEmAndamento(String seuLogin,
			UsuarioIF amigo, ItemIF item) throws Exception {
		Validador.assertStringNaoVazia(seuLogin,
				Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoEmprestimoAndamento(usuario, amigo,
				item);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);
		historicos.get(amigo.getLogin()).addNotificacao(notif);
		// this.addNotificacao(notif);
		// amigo.addNotificacao(notif);

	}

	public void zerarHistorico(String seuLogin) {
		historicos.get(seuLogin).zerarHistorico();
	}

	public String getHistoricoToString(String seuLogin)
			throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(seuLogin,
				Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);

		StringBuffer sb = new StringBuffer();
		Iterator<Notificacao> iterador = historicos.get(seuLogin).iterador();
		while (iterador.hasNext()) {
			sb.append(iterador.next().getMensagem(usuario));
			sb.append("; ");
		}
		if (sb.toString().equals(""))
			return Mensagem.HISTORICO_VAZIO.getMensagem();
		return sb.toString().substring(0, sb.length() - 2);
	}

	public void addHistoricoInteressePorItem(String seuLogin, UsuarioIF amigo,
			ItemIF item) throws Exception {
		Validador.assertStringNaoVazia(seuLogin,
				Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoRegistrarInteresseItem(usuario,
				amigo, item);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);

	}

	public void addHistoricoTerminoEmprestimo(String seuLogin, ItemIF item)
			throws Exception {
		Validador.assertStringNaoVazia(seuLogin,
				Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoTerminoEmprestimo(usuario, item);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);

	}

	public String getHistoricoAtividadesConjunto(UsuarioIF usuario)
			throws Exception {
		List<Notificacao> historico = new ArrayList<Notificacao>();
		List<UsuarioIF> amigos = usuario.getListaAmigos();
		Iterator<UsuarioIF> iteradorAmigos = amigos.iterator();
		while (iteradorAmigos.hasNext()) {
			String loginAmigo = iteradorAmigos.next().getLogin();
			Iterator<Notificacao> iteradorNotificacoes = historicos.get(
					loginAmigo).iterador();

			while (iteradorNotificacoes.hasNext()) {
				Notificacao not = iteradorNotificacoes.next();
				if (!historico.contains(not)) {
					historico.add(not);
				}
			}
		}
		Iterator<Notificacao> iteradorMinhasNotificacoes = historicos.get(
				usuario.getLogin()).iterador();

		while (iteradorMinhasNotificacoes.hasNext()) {
			Notificacao not = iteradorMinhasNotificacoes.next();
			if (!historico.contains(not)) {
				historico.add(not);
			}

		}
		Collections.sort(historico);
		Collections.reverse(historico);
		StringBuffer sb = new StringBuffer();
		Iterator<Notificacao> iterador = historico.iterator();
		while (iterador.hasNext()) {
			Notificacao not = iterador.next();
			sb.append(not.getMensagem(usuario));
			sb.append("; ");
		}

		if (sb.toString().equals(""))
			return Mensagem.HISTORICO_VAZIO.getMensagem();
		return sb.toString().substring(0, sb.length() - 2);
	}

	public String addHistoricoPublicarPedido(String seuLogin, String nomeItem,
			String descricaoItem) throws Exception {
		Validador.assertStringNaoVazia(seuLogin,
				Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoPublicarPedido(usuario, nomeItem,
				descricaoItem);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);
		return notif.getId();
	}

	public void republicarPedido(UsuarioIF usuario, String idPublicacaoPedido)
			throws Exception {
		assertStringNaoVazia(idPublicacaoPedido,
				Mensagem.PUBLICACAO_ID_INVALIDO.getMensagem(),
				Mensagem.PUBLICACAO_ID_INVALIDO.getMensagem());
		if (!NotificacaoRepositorio.getInstance().existeNotificacao(
				idPublicacaoPedido))
			throw new Exception(
					Mensagem.PUBLICACAO_ID_INEXISTENTE.getMensagem());
		Notificacao notificacao = NotificacaoRepositorio.getInstance()
				.recuperarNotificacao(idPublicacaoPedido);
		historicos.get(usuario.getLogin()).republicarPedido(notificacao);

	}

	public void zerarSistema() {
		GerenciadorDeNotificacoes.historicos.clear();
	}

}
