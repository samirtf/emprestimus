/**
 * 
 */
package sistema.mensagem;

import static sistema.utilitarios.Validador.assertStringNaoVazia;
import static sistema.utilitarios.Validador.asserteTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import sistema.autenticacao.Autenticacao;
import sistema.emprestimo.EmprestimoIF;
import sistema.persistencia.ChatRepositorio;
import sistema.persistencia.EmprestimoRepositorio;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;

/**
 * @author Mobile
 * 
 */
public class Correio {

	private static Correio correio;
	private static Map<String, CaixaPostal> caixasPostais = new TreeMap<String, CaixaPostal>();

	private Correio() {
		caixasPostais = new TreeMap<String, CaixaPostal>();
	}

	public static Correio getInstance() {
		if (correio == null) {
			correio = new Correio();

			return correio;
		}
		return correio;
	}

	public static void adicionaCaixaPostalAoUsuario(String usuario) throws Exception {
		if (caixasPostais.containsKey(usuario))
			throw new Exception(Mensagem.PROPRIETARIO_CAIXA_POSTAL_JAH_CADASTRADO
					.getMensagem());
		caixasPostais.put(usuario, new CaixaPostal(usuario));
	}

	public static void removeCaixaPostalDoUsuario(String usuario) throws Exception {
		caixasPostais.remove(usuario);
	}

	/**
	 * Adiciona uma conversa off-topic à lista de conversas do usuário.
	 * 
	 * @param usuario
	 *            O login do usuario.
	 * @param conversa
	 *            Conversa a ser adicionada.
	 * @throws Exception
	 */
	public static void adicionaConversaOfftopicNaLista(String usuario, ChatIF conversa) throws Exception {
		CaixaPostal caixaPostal = caixasPostais.get(usuario);
		if (caixaPostal == null)
			throw new Exception(Mensagem.PROPRIETARIO_CAIXA_POSTAL_INEXISTENTE
					.getMensagem());
		caixaPostal.getConversasOffTopic().add(conversa);
	}

	/**
	 * Adiciona uma conversa sobre uma negociação à lista de conversas do
	 * usuário.
	 * 
	 * @param usuario
	 *            O login do usuario.
	 * @param conversa
	 *            Conversa a ser adicionada.
	 * @throws Exception
	 */
	public static void adicionaConversaNegociacaoNaLista(String usuario, ChatIF conversa) throws Exception {
		CaixaPostal caixaPostal = caixasPostais.get(usuario);
		if (caixaPostal == null)
			throw new Exception(Mensagem.PROPRIETARIO_CAIXA_POSTAL_INEXISTENTE
					.getMensagem());
		caixaPostal.getConversasNegociacao().add(conversa);
	}

	/**
	 * Envia uma mensagem off-topic.
	 * 
	 * @param usuario
	 *            O login do usuario.
	 * @param destinatario
	 *            Destino da mensagem.
	 * @param assunto
	 *            Assunto da mensagem.
	 * @param mensagem
	 *            Mensagem a ser enviada.
	 * @return ID do tópico.
	 * @throws Exception
	 *             Caso os parâmetros sejam inválidos.
	 */
	public synchronized static String enviarMensagemOffTopic(String remetente,
			String destinatario, String assunto, String mensagem) throws Exception {

		assertStringNaoVazia(remetente, Mensagem.REMETENTE_INVALIDO.getMensagem(),
				Mensagem.REMETENTE_INVALIDO.getMensagem());

		assertStringNaoVazia(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem(),
				Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(remetente);
		UsuarioIF amigo = usuario.possuoAmigoComEsteLogin(destinatario);

		asserteTrue(amigo != null, Mensagem.DESTINATARIO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem(),
				Mensagem.ASSUNTO_INVALIDO.getMensagem());
		assertStringNaoVazia(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem(),
				Mensagem.MENSAGEM_INVALIDA.getMensagem());

		ChatIF conversa = ChatRepositorio
				.existeConversaEntreAsPessoasSobreMesmoAssuntoETipo(usuario.getLogin(),
						destinatario, assunto, true);

		if (conversa == null) {
			conversa = new Chat(usuario, amigo, assunto.trim(), mensagem.trim());
			conversa.setTipoOffTopicMsg();
			ChatRepositorio.registrarConversa(conversa);
			caixasPostais.get(remetente).getConversasOffTopic().add(conversa);
			// usuario.conversasOfftopic.add(conversa);
			amigo.adicionaConversaOfftopicNaLista(conversa);
		} else {
			conversa.adicionaMensagem(mensagem);
		}

		return conversa.getIdMensagem();

	}

	/**
	 * 
	 * @param usuario
	 *            O login do usuario.
	 * @param destinatario
	 * @param assunto
	 * @param mensagem
	 * @param idRequisicaoEmprestimo
	 * @return
	 * @throws Exception
	 */
	public synchronized static String enviarMensagemEmprestimo(String remetente,
			String destinatario, String assunto, String mensagem,
			String idRequisicaoEmprestimo) throws Exception {

		assertStringNaoVazia(remetente, Mensagem.REMETENTE_INVALIDO.getMensagem(),
				Mensagem.REMETENTE_INVALIDO.getMensagem());
		assertStringNaoVazia(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem(),
				Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(remetente);
		UsuarioIF amigo = usuario.possuoAmigoComEsteLogin(destinatario);
		asserteTrue(amigo != null, Mensagem.DESTINATARIO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem(),
				Mensagem.ASSUNTO_INVALIDO.getMensagem());
		assertStringNaoVazia(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem(),
				Mensagem.MENSAGEM_INVALIDA.getMensagem());
		assertStringNaoVazia(idRequisicaoEmprestimo,
				Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem(),
				Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		asserteTrue(
				EmprestimoRepositorio.existeEmprestimo(idRequisicaoEmprestimo.trim()),
				Mensagem.ID_REQUISICAO_EMP_INEXISTENTE.getMensagem());

		EmprestimoIF emprestimo = EmprestimoRepositorio
				.recuperarEmprestimo(idRequisicaoEmprestimo);
		if (!usuario.equals(emprestimo.getEmprestador())
				&& !usuario.equals(emprestimo.getBeneficiado()))
			throw new Exception(Mensagem.USUARIO_NAO_PARTICIPA_DESTE_EMPRESTIMO
					.getMensagem());

		ChatIF conversa = ChatRepositorio
				.existeConversaEntreAsPessoasSobreMesmoAssuntoETipo(usuario.getLogin(),
						destinatario, assunto, false);

		if (conversa == null) {
			conversa = new Chat(usuario, amigo, assunto.trim(), mensagem,
					idRequisicaoEmprestimo);
			conversa.setTipoNegociacaoMsg();
			ChatRepositorio.registrarConversa(conversa);
			caixasPostais.get(remetente).getConversasNegociacao().add(conversa);
			// this.conversasNegociacao.add(conversa);
			amigo.adicionaConversaNegociacaoNaLista(conversa);
		} else {
			conversa.adicionaMensagem(mensagem);
		}
		return conversa.getIdMensagem();
	}

	/**
	 * 
	 * @param usuario
	 *            O login do usuario.
	 * @param tipo
	 * @return
	 * @throws Exception
	 */
	public static String lerTopicos(String proprietario, String tipo) throws Exception {

		assertStringNaoVazia(proprietario, Mensagem.PROPRIETARIO_CAIXA_POSTAL_INEXISTENTE
				.getMensagem(), Mensagem.PROPRIETARIO_CAIXA_POSTAL_INEXISTENTE
				.getMensagem());
		assertStringNaoVazia(tipo, Mensagem.TIPO_INVALIDO.getMensagem(),
				Mensagem.TIPO_INVALIDO.getMensagem());

		List<ChatIF> listaTopicos = new ArrayList<ChatIF>();
		StringBuffer saida = new StringBuffer();
		if (tipo.trim().equalsIgnoreCase("negociacao")
				|| tipo.trim().equalsIgnoreCase("todos")) {
			Iterator<ChatIF> iterador = caixasPostais.get(proprietario)
					.getConversasNegociacao().iterator();

			while (iterador.hasNext()) {
				listaTopicos.add(iterador.next());
				// saida.append(iterador.next().getAssunto()+"; ");
			}
		}
		if (tipo.trim().equalsIgnoreCase("offtopic")
				|| tipo.trim().equalsIgnoreCase("todos")) {
			Iterator<ChatIF> iterador = caixasPostais.get(proprietario)
					.getConversasOffTopic().iterator();

			while (iterador.hasNext()) {
				listaTopicos.add(iterador.next());
				// saida.append(iterador.next().getAssunto()+"; ");
			}
		}
		if (!tipo.trim().equalsIgnoreCase("negociacao")
				&& !tipo.trim().equalsIgnoreCase("offtopic")
				&& !tipo.trim().equalsIgnoreCase("todos")) {
			throw new Exception(Mensagem.TIPO_INEXISTENTE.getMensagem());
		}

		if (listaTopicos.isEmpty())
			return Mensagem.NAO_HA_TOPICOS_CRIADOS.getMensagem();
		Collections.sort(listaTopicos);
		Collections.reverse(listaTopicos);
		for (ChatIF c : listaTopicos) {
			saida.append(c.getAssunto() + "; ");
		}
		return saida.toString().trim().substring(0, saida.toString().trim().length() - 1);
	}

	public static String enviarMensagemOferecimentoItemOffTopic(String remetente,
			String destinatario, String assunto, String mensagem) throws Exception {

		assertStringNaoVazia(remetente, Mensagem.REMETENTE_INVALIDO.getMensagem(),
				Mensagem.REMETENTE_INVALIDO.getMensagem());

		assertStringNaoVazia(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem(),
				Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(remetente);
		UsuarioIF amigoDeAmigo = Autenticacao.getUsuarioPorLogin(destinatario);

		asserteTrue(amigoDeAmigo != null, Mensagem.DESTINATARIO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem(),
				Mensagem.ASSUNTO_INVALIDO.getMensagem());
		assertStringNaoVazia(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem(),
				Mensagem.MENSAGEM_INVALIDA.getMensagem());

		ChatIF conversa = ChatRepositorio
				.existeConversaEntreAsPessoasSobreMesmoAssuntoETipo(usuario.getLogin(),
						destinatario, assunto, true);

		if (conversa == null) {
			conversa = new Chat(usuario, amigoDeAmigo, assunto.trim(), mensagem.trim());
			conversa.setTipoOffTopicMsg();
			ChatRepositorio.registrarConversa(conversa);
			caixasPostais.get(remetente).getConversasOffTopic().add(conversa);
			// usuario.conversasOfftopic.add(conversa);
			amigoDeAmigo.adicionaConversaOfftopicNaLista(conversa);
		} else {
			conversa.adicionaMensagem(mensagem);
		}

		return conversa.getIdMensagem();

	}

	public void zerarSistema() {
		Correio.caixasPostais.clear();
	}

}
