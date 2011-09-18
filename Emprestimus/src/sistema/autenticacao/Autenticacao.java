package sistema.autenticacao;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Logger;

import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.ItemIF;
import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Log;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;
import static sistema.utilitarios.Validador.*;
import sistema.utilitarios.ValidadorString;

public class Autenticacao implements AutenticacaoIF {

	Logger logger = Log.getLogger();
	private static Autenticacao autenticacao;

	// Mapa dos usuarios cadastrados no sistema
	private static Map<String, UsuarioIF> usuariosCadastrados = new TreeMap<String, UsuarioIF>();

	// Mapa das sessoes de usuarios logados no sistema
	private Map<String, UsuarioIF> sessoes = new TreeMap<String, UsuarioIF>();
	private final int qntMaxSessoes = Integer.MAX_VALUE - 1024; // Quantidade
																// maxima de
																// sessoes.

	private Autenticacao() {
		logger.info("Criando nova autenticação...");

		usuariosCadastrados = new TreeMap<String, UsuarioIF>();
		sessoes = new TreeMap<String, UsuarioIF>();
	}

	public static Autenticacao getInstance() {
		if (autenticacao == null) {
			autenticacao = new Autenticacao();

			return autenticacao;
		}
		return autenticacao;
	}

	@Override
	public void zerarSistema() {
		logger.info("Zerando o sistema...");
		// FIXME implemente isto;
		// usuariosCadastrados = new TreeMap<String, UsuarioIF>();
		// sessoes = new TreeMap<String, UsuarioIF>();
	}

	@Override
	public void encerrarSistema() {
		logger.info("Encerrando o sistema...");
		// System.exit(0);
	}

	@Override
	public ItemIF getItemComID(String id) throws Exception {
		logger.info("Procurando item com a ID " + id);
		ValidadorString.pegaCampoSemEspacos(
				Mensagem.ID_ITEM_INVALIDO.getMensagem(), id);
		for (UsuarioIF usuario : usuariosCadastrados.values()) {
			logger.info(" => Usuario: " + usuario.getLogin());
			try {
				for (ItemIF item : usuario.getItens()) {
					logger.info("Item: " + item.getNome() + "ID: "
							+ item.getId());
					if (item.getId().equals(id)) {
						return item;
					}
				}
			} catch (Exception e) {
				logger.severe(e.getMessage());
			} // Descartando possiveis excecoes que nao precisam ser capturadas.
		}
		throw new Exception(Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
	}

	@Override
	public void criarUsuario(String login, String nome, String endereco)
			throws Exception {
		logger.info(String
				.format("Criando usuario com login = \"%s\", nome = \"%s\" e endereço = \"%s\"",
						login, nome, endereco));

		UsuarioIF novoUsuario = new Usuario(login, nome, endereco);
		if (usuariosCadastrados.containsKey(login.trim())) {
			logger.warning(Mensagem.LOGIN_JAH_CADASTRADO.getMensagem() + "\n"
					+ novoUsuario + "\n"
					+ usuariosCadastrados.get(login.trim()).toString());
			throw new Exception(Mensagem.LOGIN_JAH_CADASTRADO.getMensagem());
		}
		usuariosCadastrados.put(login, novoUsuario);
	}

	@Override
	public String abrirSessao(String login) throws Exception {
		logger.info("Abrindo nova seção com o login: " + login);

		if (login == null || login.trim().equals(""))
			throw new Exception("Login inválido");
		if (!existeUsuario(login))
			throw new Exception("Usuário inexistente");
		String idSessao = gerarIdSessao();
		while (existeIdSessao(idSessao)) {
			idSessao = gerarIdSessao();
		}
		sessoes.put(idSessao, getUsuario(login));
		
		logger.info("Seção aberta com sucesso. idSeção: " + idSessao);
		return idSessao;

	}

	@Override
	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
		logger.info("Pegando atributo do usuário. login: " + login + " atributo: " + atributo);

		assertNaoNulo(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		assertNaoNulo(atributo, Mensagem.ATRIBUTO_INVALIDO.getMensagem());
		assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		assertStringNaoVazia(atributo, Mensagem.ATRIBUTO_INVALIDO.getMensagem());
		asserteTrue(existeUsuario(login), Mensagem.USUARIO_INEXISTENTE.getMensagem());

		UsuarioIF usuario = getUsuario(login);
		String valor = null;
		for (Field f : usuario.getClass().getDeclaredFields()) {
			logger.info("Field: " + f + " nome: " + f.getName());
			if (f.getName().equals(atributo)) {
				f.setAccessible(true);
				valor = (f.get((Usuario) usuario)).toString();
			}
		}
		if (valor == null) {
			logger.warning(Mensagem.ATRIBUTO_INEXISTENTE.getMensagem());
			throw new Exception(Mensagem.ATRIBUTO_INEXISTENTE.getMensagem());
		}

		return valor;
	}

	/**
	 * Verifica se existe usuario cadastrado.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @return True - Se o usuario ja estiver cadastrado. False - Caso
	 *         contrario.
	 */
	public static boolean existeUsuario(String login) {
		if(usuariosCadastrados == null) return false;
		return usuariosCadastrados.containsKey(login);
	}
	
	public static UsuarioIF getUsuarioPorLogin( String login ) throws ArgumentoInvalidoException{
		Validador.assertNaoNulo(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(login.trim(), Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.asserteTrue(Autenticacao.existeUsuario(login), Mensagem.LOGIN_INEXISTENTE.getMensagem());
		return usuariosCadastrados.get(login);
	}

	private UsuarioIF getUsuario(String login) throws Exception {
		return usuariosCadastrados.get(login);
	}

	@Override
	public UsuarioIF getUsuarioPeloIDSessao(String idSessao) throws Exception {
		logger.info("Pegando usuario pela idSessão. idSessão: " + idSessao);
		
		assertNaoNulo(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		
		logger.info("Usuario: " + sessoes.get(idSessao));
		return sessoes.get(idSessao);
	}

	/**
	 * Gera um idSessao.
	 * 
	 * @return Um idSessao.
	 */
	private String gerarIdSessao() {
		logger.info("Gerando uma idSessão");
		
		Random rd = new Random();
		return String.valueOf((rd.nextInt(Integer.MAX_VALUE - 1024 - 1) + 1));
	}

	/**
	 * Verifica se um idSessao ja existe.
	 * 
	 * @param idSessao
	 *            Um idSessao.
	 * @return True - Se existir o idSessao. False - Se nao existir.
	 */
	public boolean existeIdSessao(String idSessao) {
		return sessoes.containsKey(idSessao);
	}

	@Override
	public List<UsuarioIF> getUsuarioNome(String nome) {
		logger.info("Pegando usuario pelo nome. Nome: " + nome);
		
		List<UsuarioIF> usuarios = new LinkedList<UsuarioIF>();
		for (UsuarioIF user : usuariosCadastrados.values()) {
			logger.info(" -Usuario: " + user.getNome());
			if (user.getNome().contains(nome)) {
				usuarios.add(user);
			}
		}
		return usuarios;
	}

	@Override
	public List<UsuarioIF> getUsuarioEndereco(String endereco) {
		logger.info("Pegando usuario pelo endereço. Endereço: " + endereco);
		List<UsuarioIF> usuarios = new LinkedList<UsuarioIF>();
		for (UsuarioIF user : usuariosCadastrados.values()) {
			logger.info(" -Endereço: " + user.getEndereco());
			if (user.getEndereco().toLowerCase().contains(endereco.toLowerCase())) {
				usuarios.add(user);
			}
		}
		return usuarios;
	}
	
	

}
