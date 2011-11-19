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

	/**
	 * Adiciona um Rack a um determinado usuario
	 * 
	 * @param String
	 * 		usuario
	 * 
	 * @throws Exception
	 * 		Caso o rack já tenha sido cadastrado
	 */
	public void adicionaRackAoUsuario(String usuario) throws Exception {
		if (historicos.containsKey(usuario))
			throw new Exception(Mensagem.PROPRIETARIO_RACK_JAH_CADASTRADO.getMensagem());
		historicos.put(usuario, new Rack(usuario));

	}

	/**
	 * Remove o Rack de um usuario
	 * 
	 * @param String
	 * 		usuario
	 * 
	 * @throws Exception
	 */
	public void removeRackDoUsuario(String usuario) throws Exception {
		historicos.remove(usuario);
	}

	/**
	 * @param String login
	 * 		Usuario cujo rack sera buscado
	 * 
	 * @return Rack
	 * 		Rack do usuario
	 * 
	 * @throws Exception
	 */
	public Rack getRack(String login) throws Exception {
		return historicos.get(login);
	}

	/**
	 * Adiciona no historico um novo Item
	 * 
	 * @param String
	 * 		seuLogin
	 * @param ItemIF
	 * 		item
	 * 
	 * @throws Exception
	 */
	public void addHistoricoNovoItem(String seuLogin, ItemIF item) throws Exception {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoNovoItem(usuario, item);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);
		// addNotificacao(notif);
	}

	/**
	 * Adiciona ao historico uma amizade aprovada
	 * 
	 * @param String
	 * 		seuLogin
	 * @param UsuarioIF
	 * 		amigo
	 * 
	 * @throws Exception
	 */
	public void addHistoricoAmizadeAprovada(String seuLogin, UsuarioIF amigo) throws Exception {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoNovoAmigo(usuario, amigo);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);
		historicos.get(amigo.getLogin()).addNotificacao(notif);
		// this.addNotificacao(notif);
		// amigo.addNotificacao(notif);
	}

	/**
	 * Adiciona ao historico um emprestimo em andamento
	 * 
	 * @param String
	 * 		seuLogin
	 * @param UsuarioIF
	 * 		amigo
	 * @param ItemIF
	 * 		item
	 * 
	 * @throws Exception
	 */
	public void addHistoricoEmprestimoEmAndamento(String seuLogin, UsuarioIF amigo,
			ItemIF item) throws Exception {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoEmprestimoAndamento(usuario, amigo, item);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);
		historicos.get(amigo.getLogin()).addNotificacao(notif);
		// this.addNotificacao(notif);
		// amigo.addNotificacao(notif);

	}

	/**
	 * Zera o historico de um usuario
	 * 
	 * @param String
	 * 		seuLogin
	 */
	public void zerarHistorico(String seuLogin) {
		historicos.get(seuLogin).zerarHistorico();
	}

	/**
	 * Retorna o historico em forma de String
	 * 
	 * @param String
	 * 		seuLogin
	 * 
	 * @return String
	 * 		Copilação do historico
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public String getHistoricoToString(String seuLogin) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(),
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

	/**
	 * Adiciona no historico um interesse por item
	 * 
	 * @param String
	 * 		seuLogin
	 * @param UsuarioIF
	 * 		amigo
	 * @param ItemIF
	 * 		item
	 * 
	 * @throws Exception
	 */
	public void addHistoricoInteressePorItem(String seuLogin, UsuarioIF amigo, ItemIF item) throws Exception {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoRegistrarInteresseItem(usuario, amigo, item);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);

	}

	/**
	 * Adiciona ao historico um Termino de emprestimo
	 * @param String
	 * 		seuLogin
	 * @param ItemIF
	 * 		item
	 * @throws Exception
	 */
	public void addHistoricoTerminoEmprestimo(String seuLogin, ItemIF item) throws Exception {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoTerminoEmprestimo(usuario, item);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);

	}

	/**
	 * Retorna do historico uma compilação de atividades em conjunto
	 * @param UsuarioIF
	 * 		usuario
	 * 
	 * @return String
	 * 		Compilação das atividades em conjunto
	 * 
	 * @throws Exception
	 */
	public synchronized String getHistoricoAtividadesConjunto(UsuarioIF usuario) throws Exception {
		List<Notificacao> historico = new ArrayList<Notificacao>();
		List<UsuarioIF> amigos = usuario.getListaAmigos();
		Iterator<UsuarioIF> iteradorAmigos = amigos.iterator();
		Iterator<Notificacao> iteradorMinhasNotificacoes = historicos.get(
				usuario.getLogin()).iterador();

		while (iteradorMinhasNotificacoes.hasNext()) {
			Notificacao not = iteradorMinhasNotificacoes.next();
			if (!historico.contains(not)) {
				historico.add(not);
			}

		}
		while (iteradorAmigos.hasNext()) {
			String loginAmigo = iteradorAmigos.next().getLogin();
			Iterator<Notificacao> iteradorNotificacoes = historicos.get(loginAmigo)
					.iterador();

			while (iteradorNotificacoes.hasNext()) {
				Notificacao not = iteradorNotificacoes.next();
				if (!historico.contains(not)) {
					historico.add(not);
				}
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

	/**
	 * Adiciona ao historico uma publicação de pedido
	 * 
	 * @param String
	 * 		seuLogin
	 * @param String 
	 * 		nomeItem
	 * @param String 
	 * 		descricaoItem
	 * 
	 * @return String
	 * 		ID da notificação
	 * 
	 * @throws Exception
	 */
	public String addHistoricoPublicarPedido(String seuLogin, String nomeItem,
			String descricaoItem) throws Exception {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		Notificacao notif = new NotificacaoPublicarPedido(usuario, nomeItem,
				descricaoItem);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		historicos.get(seuLogin).addNotificacao(notif);
		return notif.getId();
	}

	/**
	 * Republica um pedido
	 * 
	 * @param UsuarioIF
	 * 		usuario
	 * @param String
	 * 		idPublicacaoPedido
	 * 
	 * @throws Exception
	 */
	public void republicarPedido(UsuarioIF usuario, String idPublicacaoPedido) throws Exception {
		assertStringNaoVazia(idPublicacaoPedido, Mensagem.PUBLICACAO_ID_INVALIDO
				.getMensagem(), Mensagem.PUBLICACAO_ID_INVALIDO.getMensagem());
		if (!NotificacaoRepositorio.getInstance().existeNotificacao(idPublicacaoPedido))
			throw new Exception(Mensagem.PUBLICACAO_ID_INEXISTENTE.getMensagem());
		Notificacao notificacao = NotificacaoRepositorio.getInstance()
				.recuperarNotificacao(idPublicacaoPedido);
		historicos.get(usuario.getLogin()).republicarPedido(notificacao);

	}

	/**
	 * Retorna o sistema para suas configurações iniciais.
	 */
	public void zerarSistema() {
		GerenciadorDeNotificacoes.historicos.clear();
	}

}
