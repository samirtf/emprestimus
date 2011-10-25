/**
 * 
 */
package sistema.emprestimo;

import static sistema.utilitarios.Validador.assertStringNaoVazia;
import static sistema.utilitarios.Validador.asserteTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import sistema.autenticacao.Autenticacao;
import sistema.item.ItemIF;
import sistema.persistencia.EmprestimoRepositorio;
import sistema.persistencia.ItemRepositorio;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;

/**
 * @author Mobile
 * 
 */
public class BancoDeEmprestimos {

	private static BancoDeEmprestimos bancoDeEmprestimos;
	private static Map<String, Conta> contas;

	private BancoDeEmprestimos() {
		contas = new TreeMap<String, Conta>();
	}

	public static BancoDeEmprestimos getInstance() {
		if (bancoDeEmprestimos == null) {
			bancoDeEmprestimos = new BancoDeEmprestimos();

			return bancoDeEmprestimos;
		}
		return bancoDeEmprestimos;
	}

	public void adicionaContaAoUsuario(String usuario) throws Exception {
		if (contas.containsKey(usuario))
			throw new Exception(
					"Mensagem.PROPRIETARIO_CONTA_JAH_CADASTRADO.getMensagem()");
		contas.put(usuario, new Conta(usuario));
	}

	public void removeContaDoUsuario(String usuario) throws Exception {
		contas.remove(usuario);
	}

	public Conta getConta(String login) throws Exception {
		return contas.get(login);
	}

	public void adicionarRequisicaoEmprestimoEmEsperaDeAmigo(String login,
			EmprestimoIF emp) throws Exception {
		if (!contas.containsKey(login))
			throw new Exception(
					Mensagem.PROPRIETARIO_CONTA_INEXISTENTE.getMensagem());
		contas.get(login).getEmprestimosRequeridosPorAmigosEmEspera().add(emp);
		// this.emprestimosRequeridosPorAmigosEmEspera.add(emp);
	}

	public synchronized String requisitarEmprestimo(String login,
			String idItem, int duracao) throws Exception {
		Validador.assertStringNaoVazia(login,
				Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(idItem,
				Mensagem.ID_ITEM_INVALIDO.getMensagem(),
				Mensagem.ID_ITEM_INVALIDO.getMensagem());
		Validador.asserteTrue(ItemRepositorio.existeItem(idItem.trim()),
				Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		Validador.asserteTrue(duracao > 0,
				Mensagem.EMPRESTIMO_DURACAO_INVALIDA.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(login);
		UsuarioIF amigo = usuario.ehItemDoMeuAmigo(idItem);
		Validador.asserteTrue(amigo != null,
				Mensagem.USUARIO_NAO_TEM_PERMISSAO_REQUISITAR_EMPREST_ITEM
						.getMensagem());

		ItemIF item = ItemRepositorio.recuperarItem(idItem);

		// verifica se jah fiz o pedido do item
		Iterator<EmprestimoIF> iterador = contas.get(login)
				.getEmprestimosRequeridosPorMimEmEspera().iterator();
		while (iterador.hasNext()) {
			if (iterador.next().getItem().equals(item))
				throw new Exception(
						Mensagem.REQUISICAO_EMPRESTIMO_JA_SOLICITADA
								.getMensagem());
		}

		EmprestimoIF emp = new Emprestimo(amigo, usuario, item, "beneficiado",
				duracao);

		// vou atualizar estado do emprestimo
		EmprestimoRepositorio.requisitarEmprestimo(emp);
		contas.get(login).getEmprestimosRequeridosPorMimEmEspera().add(emp);// o
																			// emprestimo
																			// eh
																			// modificdo
																			// pelo
																			// repositorio
																			// possuindo
																			// agora
		// um id valido
		amigo.adicionarRequisicaoEmprestimoEmEsperaDeAmigo(emp);
		String assunto = "Empréstimo do item " + item.getNome() + " a "
				+ usuario.getNome() + "";
		String mensagem = usuario.getNome()
				+ " solicitou o empréstimo do item " + item.getNome();
		usuario.enviarMensagemEmprestimo(amigo.getLogin(), assunto, mensagem,
				emp.getIdEmprestimo());
		return emp.getIdEmprestimo();

	}

	public synchronized String getEmprestimos(String login, String tipo)
			throws Exception {

		// FIXME: este método está muito grande e precisa ser dividido!!!
		Validador.assertStringNaoVazia(login,
				Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(tipo,
				Mensagem.EMPRESTIMO_TIPO_INVALIDO.getMensagem(),
				Mensagem.EMPRESTIMO_TIPO_INVALIDO.getMensagem());
		// "mark-steve:The Social Network:Andamento; steve-mark:Guia do mochileiro das galáxias:Andamento"
		StringBuffer str = new StringBuffer();
		List<String> listaSaida = new ArrayList<String>();
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(login);
		Iterator<EmprestimoIF> iterador = contas.get(login).getEmprestimos()
				.iterator();

		while (iterador.hasNext()) {
			EmprestimoIF emp = iterador.next();
			if (tipo.trim().equalsIgnoreCase("emprestador")) {

				if (usuario.equals(emp.getEmprestador())) {

					listaSaida.add(emp.getEmprestador().getLogin() + "-"
							+ emp.getBeneficiado().getLogin() + ":"
							+ emp.getItem().getNome() + ":" + emp.getSituacao()
							+ "; ");
					Collections.sort(listaSaida);
				}
			} else if (tipo.trim().equalsIgnoreCase("beneficiado")) {
				if (usuario.equals(emp.getBeneficiado())) {

					listaSaida.add(emp.getEmprestador().getLogin() + "-"
							+ emp.getBeneficiado().getLogin() + ":"
							+ emp.getItem().getNome() + ":" + emp.getSituacao()
							+ "; ");
					Collections.sort(listaSaida);
				}
			} else if (tipo.trim().equalsIgnoreCase("todos")) {

				if (usuario.equals(emp.getEmprestador())) {
					listaSaida.add(0, emp.getEmprestador().getLogin() + "-"
							+ emp.getBeneficiado().getLogin() + ":"
							+ emp.getItem().getNome() + ":" + emp.getSituacao()
							+ "; ");
				}

				if (usuario.equals(emp.getBeneficiado())) {
					listaSaida.add(emp.getEmprestador().getLogin() + "-"
							+ emp.getBeneficiado().getLogin() + ":"
							+ emp.getItem().getNome() + ":" + emp.getSituacao()
							+ "; ");
				}

			} else {
				throw new Exception(
						Mensagem.EMPRESTIMO_TIPO_INXISTENTE.getMensagem());
			}
		}

		for (String s : listaSaida) {
			str.append(s);
		}

		if (str.toString().trim().equals(""))
			return Mensagem.NAO_HA_EMPRESTIMOS_DESTE_TIPO.getMensagem();
		return str.toString().trim().substring(0, str.toString().length() - 2);
	}

	public synchronized String aprovarEmprestimo(String login,
			String idRequisicaoEmprestimo) throws Exception {
		Validador.assertStringNaoVazia(login,
				Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		assertStringNaoVazia(idRequisicaoEmprestimo,
				Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem(),
				Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		asserteTrue(
				EmprestimoRepositorio.existeEmprestimo(idRequisicaoEmprestimo
						.trim()),
				Mensagem.ID_REQUISICAO_EMP_INEXISTENTE.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(login);
		EmprestimoIF emp = EmprestimoRepositorio
				.recuperarEmprestimo(idRequisicaoEmprestimo.trim());
		asserteTrue(usuario.equals(emp.getEmprestador()),
				Mensagem.EMPRESTIMO_SEM_PERMISSAO_APROVAR.getMensagem());

		if (!emp.estaAprovado())
			throw new Exception(Mensagem.EMPRESTIMO_JA_APROVADO.getMensagem());
		emp.aprovarEmprestimo();

		contas.get(login).getEmprestimosRequeridosPorAmigosEmEspera()
				.remove(emp);
		contas.get(login).getEmprestimos().add(emp);
		UsuarioIF amigo = emp.getBeneficiado();
		amigo.emprestimoAceitoPorAmigo(emp);
		emp.getItem().setDisponibilidade(false);

		Autenticacao.getUsuarioPorLogin(login)
				.addHistoricoEmprestimoEmAndamento(amigo, emp.getItem());

		return emp.getIdEmprestimo();

	}

	public void emprestimoAceitoPorAmigo(String login, EmprestimoIF emp)
			throws Exception {
		Validador.assertStringNaoVazia(login,
				Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		Conta conta = contas.get(login);
		conta.getEmprestimosRequeridosPorMimEmEspera().remove(emp);
		conta.getEmprestimos().add(emp);
	}

	public void removerEmprestimosRequeridosPorAmigo(String login,
			UsuarioIF amigo) {
		Iterator<EmprestimoIF> iteradorListaEmprestimosRequeridosPorAmigo = contas
				.get(login).getEmprestimosRequeridosPorAmigosEmEspera()
				.iterator();

		while (iteradorListaEmprestimosRequeridosPorAmigo.hasNext()) {
			EmprestimoIF emprestimo = iteradorListaEmprestimosRequeridosPorAmigo
					.next();
			if (emprestimo.getBeneficiado().equals(amigo)) {
				EmprestimoRepositorio.removerEmprestimo(emprestimo
						.getIdEmprestimo());
				iteradorListaEmprestimosRequeridosPorAmigo.remove();
			}
		}

	}

	public void removerEmprestimosRequeridosPorMim(String login, UsuarioIF amigo) {
		Iterator<EmprestimoIF> iteradorListaEmprestimosRequeridosPorMim = contas
				.get(login).getEmprestimosRequeridosPorMimEmEspera().iterator();

		while (iteradorListaEmprestimosRequeridosPorMim.hasNext()) {
			EmprestimoIF emprestimo = iteradorListaEmprestimosRequeridosPorMim
					.next();
			if (emprestimo.getEmprestador().equals(amigo)) {
				EmprestimoRepositorio.removerEmprestimo(emprestimo
						.getIdEmprestimo());
				iteradorListaEmprestimosRequeridosPorMim.remove();
			}
		}

	}

	public synchronized boolean requisiteiEsteItem(String login, String idItem)
			throws Exception {
		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(),
				Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(ItemRepositorio.existeItem(idItem),
				Mensagem.ID_ITEM_INEXISTENTE.getMensagem());

		Iterator<EmprestimoIF> iteradorEmprestimos = contas.get(login)
				.getEmprestimosRequeridosPorMimEmEspera().iterator();
		while (iteradorEmprestimos.hasNext()) {

			if (iteradorEmprestimos.next().getItem().getId().trim()
					.equalsIgnoreCase(idItem.trim())) {
				return true;
			}
		}
		return false;
	}

	public void removerMinhaSolicitacaoEmprestimo(String login,
			EmprestimoIF emprestimo) {
		contas.get(login).getEmprestimosRequeridosPorMimEmEspera()
				.remove(emprestimo);
	}

	public void zerarSistema() {
		contas.clear();
	}

}
