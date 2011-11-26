package sistema.autenticacao;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import maps.ComparaDistancia;
import maps.RefCoordenadas;
import sistema.dao.AcervoDeItensDAO;
import sistema.dao.AcervoDeItensFileDAO;
import sistema.dao.BancoDeEmprestimosDAO;
import sistema.dao.BancoDeEmprestimosFileDAO;
import sistema.dao.CorreioDAO;
import sistema.dao.CorreioFileDAO;
import sistema.dao.GerenciadorDeNotificacoesDAO;
import sistema.dao.GerenciadorDeNotificacoesFileDAO;
import sistema.dao.RelacionamentosUsuariosDAO;
import sistema.dao.RelacionamentosUsuariosFileDAO;
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
	//private final int qntMaxSessoes = Integer.MAX_VALUE - 1024; // Quantidade
																// maxima de
																// sessoes.

	private Autenticacao() {
		usuariosCadastrados = new TreeMap<String, UsuarioIF>();
		sessoes = new TreeMap<String, UsuarioIF>();
		File file = new File("./teste.txt");
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("eh CILADA BINO");
		}
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
	}

	@Override
	public void encerrarSistema() {
		//System.exit(0);
	}

	@Override
	public ItemIF getItemComID(String id) throws Exception {
		assertStringNaoVazia(id, Mensagem.ID_ITEM_INVALIDO.getMensagem(),
				Mensagem.ID_ITEM_INVALIDO.getMensagem());

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
	public void criarUsuario(String login, String nome, String endereco) throws Exception {
		UsuarioIF novoUsuario = new Usuario(login, nome, endereco);
		if (usuariosCadastrados.containsKey(login.trim())) {
			throw new Exception(Mensagem.LOGIN_JAH_CADASTRADO.getMensagem());
		}

		usuariosCadastrados.put(login, novoUsuario);
		// adicionando caixa postal ao usuario
		((CorreioDAO) new CorreioFileDAO()).adicionaCaixaPostalAoUsuario(login);
		((BancoDeEmprestimosDAO) new BancoDeEmprestimosFileDAO()).adicionaContaAoUsuario(login);
		((AcervoDeItensDAO) new AcervoDeItensFileDAO()).adicionaBauhAoUsuario(login);
		((RelacionamentosUsuariosDAO) new RelacionamentosUsuariosFileDAO()).adicionaCicloDeAmizadeAoUsuario(login);
		((GerenciadorDeNotificacoesDAO) new GerenciadorDeNotificacoesFileDAO()).adicionaRackAoUsuario(login);
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
	public String getAtributoUsuario(String login, String atributo) throws Exception {
		assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		assertStringNaoVazia(atributo, Mensagem.ATRIBUTO_INVALIDO.getMensagem(),
				Mensagem.ATRIBUTO_INVALIDO.getMensagem());
		asserteTrue(existeUsuario(login), Mensagem.USUARIO_INEXISTENTE.getMensagem());

		UsuarioIF usuario = getUsuario(login);
		String valor = null;
		for (Field f : usuario.getClass().getDeclaredFields()) {
			if (f.getName().equals(atributo)) {
				f.setAccessible(true);
				valor = (f.get(usuario)).toString();
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
	 * @param String - login
	 *            O login do usuario.
	 * @return boolean
	 * 		True - Se o usuario ja estiver cadastrado.
	 */
	public static boolean existeUsuario(String login) {
		if (usuariosCadastrados == null)
			return false;
		return usuariosCadastrados.containsKey(login);
	}

	public static UsuarioIF getUsuarioPorLogin(String remetente) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(remetente, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.asserteTrue(Autenticacao.existeUsuario(remetente),
				Mensagem.LOGIN_INEXISTENTE.getMensagem());
		return usuariosCadastrados.get(remetente);
	}

	private UsuarioIF getUsuario(String login) throws Exception {
		return usuariosCadastrados.get(login);
	}

	@Override
	public UsuarioIF getUsuarioPeloIDSessao(String idSessao) throws Exception {
		assertStringNaoVazia(idSessao, Mensagem.SESSAO_INVALIDA.getMensagem(),
				Mensagem.SESSAO_INVALIDA.getMensagem());
		asserteTrue(existeIdSessao(idSessao), Mensagem.SESSAO_INEXISTENTE.getMensagem());

		return sessoes.get(idSessao);
	}

	/**
	 * Gera um idSessao.
	 * 
	 * @return String
	 * 		Um idSessao.
	 */
	private String gerarIdSessao() {
		Random rd = new Random();
		return String.valueOf((rd.nextInt(Integer.MAX_VALUE - 1024 - 1) + 1));
	}

	/**
	 * Verifica se um idSessao ja existe.
	 * 
	 * @param String idSessao
	 *            Um idSessao.
	 * @return boolean
	 * 		True - Se existir o idSessao.
	 */
	@Override
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
		return Autenticacao.usuariosCadastrados.values();
	}

	public List<UsuarioIF> getUsuarios(UsuarioIF userCorrente) {
		List<UsuarioIF> usuarios = new ArrayList<UsuarioIF>(usuariosCadastrados.values());
		usuarios.remove(userCorrente);
		Collections.sort(usuarios, new ComparaDistancia(new RefCoordenadas(userCorrente
				.getLatitude(), userCorrente.getLongitude())));
		return usuarios;
	}

	@Override
	public String abrirSessao(String login, String senha) throws Exception {
		assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(), 
				Mensagem.LOGIN_INVALIDO.getMensagem());
		if (!existeUsuario(login))
			throw new Exception(Mensagem.USUARIO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(senha, Mensagem.SENHA_INVALIDA.getMensagem(), 
				Mensagem.SENHA_INVALIDA.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(login);
		if(!usuario.logar(senha)){
			throw new Exception("Login ou senha incorreto(s)");
		}
		String idSessao = gerarIdSessao();
		while (existeIdSessao(idSessao)) {
			idSessao = gerarIdSessao();
		}
		sessoes.put(idSessao, getUsuario(login));

		return idSessao;
	}

	public void criarUsuario(String login, String senha, String nome,
			String endereco) throws Exception {
		UsuarioIF novoUsuario = new Usuario(login, senha, nome, endereco);
		if (usuariosCadastrados.containsKey(login.trim())) {
			throw new Exception(Mensagem.LOGIN_JAH_CADASTRADO.getMensagem());
		}

		usuariosCadastrados.put(login, novoUsuario);
		// adicionando caixa postal ao usuario
		((CorreioDAO) new CorreioFileDAO()).adicionaCaixaPostalAoUsuario(login);
		((BancoDeEmprestimosDAO) new BancoDeEmprestimosFileDAO()).adicionaContaAoUsuario(login);
		((AcervoDeItensDAO) new AcervoDeItensFileDAO()).adicionaBauhAoUsuario(login);
		((RelacionamentosUsuariosDAO) new RelacionamentosUsuariosFileDAO()).adicionaCicloDeAmizadeAoUsuario(login);
		((GerenciadorDeNotificacoesDAO) new GerenciadorDeNotificacoesFileDAO()).adicionaRackAoUsuario(login);

	}

	public void encerrarSessao(String idSessao) throws Exception {
		
		if(autenticacao.sessoes.remove(idSessao) == null){
			throw new Exception(Mensagem.SESSAO_JAH_ENCERRADA.getMensagem());
		}
	}

	@Override
	public void notificaPersistenciaDoSistema() {
		// TODO Auto-generated method stub
		
	}
}
