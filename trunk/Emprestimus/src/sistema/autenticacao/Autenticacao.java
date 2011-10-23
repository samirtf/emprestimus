package sistema.autenticacao;

import java.lang.reflect.Field;
import java.util.Collection;
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
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;
import static sistema.utilitarios.Validador.*;

public class Autenticacao implements AutenticacaoIF {
	private static Autenticacao autenticacao;

	// Mapa dos usuarios cadastrados no sistema
	private static Map<String, UsuarioIF> usuariosCadastrados = new TreeMap<String, UsuarioIF>();

	// Mapa das sessoes de usuarios logados no sistema
	private Map<String, UsuarioIF> sessoes = new TreeMap<String, UsuarioIF>();
	private final int qntMaxSessoes = Integer.MAX_VALUE - 1024; // Quantidade
																// maxima de
																// sessoes.

	private Autenticacao() {
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
		 usuariosCadastrados = new TreeMap<String, UsuarioIF>();
		 sessoes = new TreeMap<String, UsuarioIF>();
		 zerarHistoricoUsuario();
	}

	private void zerarHistoricoUsuario() {
		for ( UsuarioIF usuario : usuariosCadastrados.values()) {
			usuario.zerarHistorico();
			
		}
		
	}

	@Override
	public void encerrarSistema() {
		 //System.exit(0);
	}

	@Override
	public ItemIF getItemComID(String id) throws Exception {
		assertStringNaoVazia(id, Mensagem.ID_ITEM_INVALIDO.getMensagem(), Mensagem.ID_ITEM_INVALIDO.getMensagem());
		
		for (UsuarioIF usuario : usuariosCadastrados.values()) {
			try {
				for (ItemIF item : usuario.getItens()) {
					if (item.getId().equals(id.trim())) {
						return item;
					}
				}
			} catch (Exception e) {
			} // Descartando possiveis excecoes que nao precisam ser capturadas.
		}
		throw new Exception(Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
	}

	@Override
	public void criarUsuario(String login, String nome, String endereco)
			throws Exception {
		UsuarioIF novoUsuario = new Usuario(login, nome, endereco);
		if (usuariosCadastrados.containsKey(login.trim())) {
			throw new Exception(Mensagem.LOGIN_JAH_CADASTRADO.getMensagem());
		}
		usuariosCadastrados.put(login, novoUsuario);
	}

	@Override
	public String abrirSessao(String login) throws Exception {
		if (login == null || login.trim().equals(""))
			throw new Exception("Login inválido");
		if (!existeUsuario(login))
			throw new Exception("Usuário inexistente");
		String idSessao = gerarIdSessao();
		while (existeIdSessao(idSessao)) {
			idSessao = gerarIdSessao();
		}
		sessoes.put(idSessao, getUsuario(login));
		
		return idSessao;

	}

	@Override
	public String getAtributoUsuario(String login, String atributo)
			throws Exception {
		assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		assertStringNaoVazia(atributo, Mensagem.ATRIBUTO_INVALIDO.getMensagem(), Mensagem.ATRIBUTO_INVALIDO.getMensagem());
		asserteTrue(existeUsuario(login), Mensagem.USUARIO_INEXISTENTE.getMensagem());

		UsuarioIF usuario = getUsuario(login);
		String valor = null;
		for (Field f : usuario.getClass().getDeclaredFields()) {
			if (f.getName().equals(atributo)) {
				f.setAccessible(true);
				valor = (f.get((Usuario) usuario)).toString();
			}
		}
		if (valor == null) {
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
	
	public static UsuarioIF getUsuarioPorLogin(String login) throws ArgumentoInvalidoException{
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.asserteTrue(Autenticacao.existeUsuario(login), Mensagem.LOGIN_INEXISTENTE.getMensagem());
		return usuariosCadastrados.get(login);
	}

	private UsuarioIF getUsuario(String login) throws Exception {
		return usuariosCadastrados.get(login);
	}

	@Override
	public UsuarioIF getUsuarioPeloIDSessao(String idSessao) throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(), Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());
		
		return sessoes.get(idSessao);
	}

	/**
	 * Gera um idSessao.
	 * 
	 * @return Um idSessao.
	 */
	private String gerarIdSessao() {
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
		List<UsuarioIF> usuarios = new LinkedList<UsuarioIF>();
		for (UsuarioIF user : usuariosCadastrados.values()) {
			if (user.getNome().contains(nome)) {
				usuarios.add(user);
			}
		}
		return usuarios;
	}

	@Override
	public List<UsuarioIF> getUsuarioEndereco(String endereco) {
		List<UsuarioIF> usuarios = new LinkedList<UsuarioIF>();
		for (UsuarioIF user : usuariosCadastrados.values()) {
			if (user.getEndereco().toLowerCase().contains(endereco.toLowerCase())) {
				usuarios.add(user);
			}
		}
		return usuarios;
	}

	@Override
	public Collection<UsuarioIF> getListaUsuarios() {
		return this.usuariosCadastrados.values();
	}
	
	
	

}
