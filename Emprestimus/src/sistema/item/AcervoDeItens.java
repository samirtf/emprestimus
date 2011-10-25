/**
 * 
 */
package sistema.item;

import static sistema.utilitarios.Validador.assertNaoNulo;
import static sistema.utilitarios.Validador.assertStringNaoVazia;
import static sistema.utilitarios.Validador.asserteTrue;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import sistema.autenticacao.Autenticacao;
import sistema.emprestimo.BancoDeEmprestimos;
import sistema.emprestimo.EmprestimoIF;
import sistema.excecoes.ArgumentoInvalidoException;
import sistema.notificacao.GerenciadorDeNotificacoes;
import sistema.notificacao.NotificacaoPublicarPedido;
import sistema.persistencia.EmprestimoRepositorio;
import sistema.persistencia.ItemRepositorio;
import sistema.persistencia.NotificacaoRepositorio;
import sistema.usuario.RelacionamentosUsuarios;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;

/**
 * @author Mobile
 * 
 */
public class AcervoDeItens {
	private static AcervoDeItens acervoDeItens;
	private static Map<String, Bauh> bauhs;

	private AcervoDeItens() {
		bauhs = new TreeMap<String, Bauh>();
	}

	public static AcervoDeItens getInstance() {
		if (acervoDeItens == null) {
			acervoDeItens = new AcervoDeItens();

			return acervoDeItens;
		}
		return acervoDeItens;
	}

	public void adicionaBauhAoUsuario(String usuario) throws Exception {
		if (bauhs.containsKey(usuario))
			throw new Exception(Mensagem.PROPRIETARIO_BAUH_JAH_CADASTRADO.getMensagem());
		bauhs.put(usuario, new Bauh(usuario));

	}

	public void removeContaDoUsuario(String usuario) throws Exception {
		bauhs.remove(usuario);
	}

	public Bauh getBauh(String login) throws Exception {
		return bauhs.get(login);
	}

	public String cadastrarItem(String login, String nome, String descricao,
			String categoria) throws Exception {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());

		ItemIF item = new Item(nome, descricao, categoria, Autenticacao
				.getUsuarioPorLogin(login));
		ItemRepositorio.cadastrarItem(item);
		bauhs.get(login).getItens().add(item);// o item eh modificado pelo
												// repositorio possuindo agora
		// um id valido
		// addHistoricoCadastrarItem(item);
		return item.getId();
	}

	public boolean removerItem(String login, String idItem) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		List<ItemIF> itens = bauhs.get(login).getItens();
		for (ItemIF item : itens) {
			if (idItem.equals(item.getId())) {
				itens.remove(item);
				return true;
			}
		}
		return false;
	}

	public String getListaIdItens(String login) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		List<ItemIF> itens = bauhs.get(login).getItens();
		StringBuilder listaIdItensString = new StringBuilder();
		Collections.sort(itens);
		for (ItemIF item : itens) {
			listaIdItensString.append(item.getId() + " ");
		}

		return listaIdItensString.toString().trim();
	}

	public List<ItemIF> getItens(String login) {
		return bauhs.get(login).getItens();
	}

	public ItemIF getItem(String login, String idItem) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		List<ItemIF> itens = bauhs.get(login).getItens();
		for (ItemIF item : itens) {
			if (item.getId().equals(idItem)) {
				return item;
			}
		}

		return null;
	}

	public int qntItens(String login) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		List<ItemIF> itens = bauhs.get(login).getItens();
		return itens.size();
	}

	public int qntItensEmprestados(String login) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		return bauhs.get(login).getItensEmprestados().size();
	}

	public String getListaIdItensEmprestados(String login) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		List<ItemIF> itensEmprestados = bauhs.get(login).getItensEmprestados();
		StringBuilder listaIdItensEmprestadosString = new StringBuilder();

		for (ItemIF itemEmprestado : itensEmprestados) {
			listaIdItensEmprestadosString.append(itemEmprestado.getId() + " ");
		}

		return listaIdItensEmprestadosString.toString().trim();
	}

	public boolean existeItemID(String login, String idItem) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		List<ItemIF> itens = bauhs.get(login).getItens();
		try {
			return itens.contains(new Item("placebo", "placebo", "FILME").setId(idItem));
		} catch (Exception e) {
		}// nao lanca excecao.
		return false;

	}

	public String getListaItens(String login) throws Exception {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		Iterator<ItemIF> iterador = bauhs.get(login).getItens().iterator();
		StringBuffer str = new StringBuffer();
		while (iterador.hasNext()) {
			str.append(iterador.next().getNome() + "; ");
		}
		if (str.toString().trim().equals(""))
			return Mensagem.USUARIO_SEM_ITENS_CADASTRADOS.getMensagem();
		return str.toString().substring(0, str.toString().length() - 2);
	}

	public boolean esteItemMePertence(String login, String idItem) throws Exception {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(),
				Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(ItemRepositorio.existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE
				.getMensagem());

		Iterator<ItemIF> iteradorItens = bauhs.get(login).getItens().iterator();
		while (iteradorItens.hasNext()) {
			if (iteradorItens.next().getId().trim().equalsIgnoreCase(idItem.trim()))
				return true;
		}
		return false;
	}

	public void apagarItem(String login, String idItem) throws Exception {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		BancoDeEmprestimos banco = BancoDeEmprestimos.getInstance();
		Iterator<EmprestimoIF> iteradorEmprestimosRequeridosPorAmigos = banco.getConta(
				login).getEmprestimosRequeridosPorAmigosEmEspera().iterator();
		while (iteradorEmprestimosRequeridosPorAmigos.hasNext()) {
			EmprestimoIF emprestimo = iteradorEmprestimosRequeridosPorAmigos.next();
			if (emprestimo.getItem().getId().equalsIgnoreCase(idItem.trim())) {

				UsuarioIF amigoQueSolicitou = emprestimo.getBeneficiado();
				amigoQueSolicitou.removerMinhaSolicitacaoEmprestimo(emprestimo);
				EmprestimoRepositorio.removerEmprestimo(emprestimo.getIdEmprestimo());
				iteradorEmprestimosRequeridosPorAmigos.remove();
			}
		}

		Iterator<ItemIF> iteradorMeusItens = bauhs.get(login).getItens().iterator();
		while (iteradorMeusItens.hasNext()) {
			if (iteradorMeusItens.next().getId().equalsIgnoreCase(idItem.trim())) {
				iteradorMeusItens.remove();
			}
		}
	}

	public void registrarInteressePorItem(String seuLogin, String idItem) throws Exception {

		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(),
				Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(ItemRepositorio.existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE
				.getMensagem());

		ItemIF item = ItemRepositorio.recuperarItem(idItem);

		// FIXME usar as mensagens constantes do enum Mensagem

		asserteTrue(!item.getInteresasados().contains(
				Autenticacao.getUsuarioPorLogin(seuLogin)),
				"O usuário já registrou interesse neste item");
		asserteTrue(!esteItemMePertence(seuLogin, idItem),
				"O usuário não pode registrar interesse no próprio item");
		UsuarioIF amigo = RelacionamentosUsuarios.getInstance().ehItemDoMeuAmigo(
				seuLogin, idItem);
		assertNaoNulo(amigo,
				"O usuário não tem permissão para registrar interesse neste item");
		item.adicionaInteressado(Autenticacao.getUsuarioPorLogin(seuLogin));
		GerenciadorDeNotificacoes.getInstance().addHistoricoInteressePorItem(seuLogin,
				amigo, item);
	}

	public void oferecerItem(String login, String idPublicacaoPedido, String idItem) throws Exception {

		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		assertStringNaoVazia(idPublicacaoPedido, Mensagem.PUBLICACAO_ID_INVALIDO
				.getMensagem(), Mensagem.PUBLICACAO_ID_INVALIDO.getMensagem());
		NotificacaoPublicarPedido notificacao = null;
		try {
			notificacao = (NotificacaoPublicarPedido) NotificacaoRepositorio
					.getInstance().recuperarNotificacao(idPublicacaoPedido);
		} catch (Exception e) {
			throw new Exception(Mensagem.PUBLICACAO_ID_INEXISTENTE.getMensagem());
		}
		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(),
				Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(ItemRepositorio.existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE
				.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(login);
		if (!usuario.esteItemMePertence(idItem)) {
			throw new Exception(Mensagem.ITEM_NAO_ME_PERTENCE.getMensagem());
		}
		ItemIF item = ItemRepositorio.recuperarItem(idItem);
		usuario.enviarMensagemOferecimentoItemOffTopic(notificacao
				.getOriginadorMensagem().getLogin(), String.format(
				"O usuário %s ofereceu o item %s", usuario.getNome(), item.getNome()),
				String.format("Item oferecido: %s - %s", item.getNome(), item
						.getDescricao()));
	}

	public void zerarSistema() {
		AcervoDeItens.bauhs.clear();
	}

}
