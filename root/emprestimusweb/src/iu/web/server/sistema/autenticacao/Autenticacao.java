package iu.web.server.sistema.autenticacao;

import iu.web.server.sistema.dao.AcervoDeItensDAO;
import iu.web.server.sistema.dao.AcervoDeItensFileDAO;
import iu.web.server.sistema.dao.BancoDeEmprestimosDAO;
import iu.web.server.sistema.dao.BancoDeEmprestimosFileDAO;
import iu.web.server.sistema.dao.CorreioDAO;
import iu.web.server.sistema.dao.CorreioFileDAO;
import iu.web.server.sistema.dao.GerenciadorDeNotificacoesDAO;
import iu.web.server.sistema.dao.GerenciadorDeNotificacoesFileDAO;
import iu.web.server.sistema.dao.RelacionamentosUsuariosDAO;
import iu.web.server.sistema.dao.RelacionamentosUsuariosFileDAO;
import iu.web.server.sistema.excecoes.ArgumentoInvalidoException;
import iu.web.server.sistema.item.ItemIF;
import iu.web.server.sistema.usuario.Usuario;
import iu.web.server.sistema.usuario.UsuarioIF;
import iu.web.server.sistema.utilitarios.Mensagem;
import iu.web.server.sistema.utilitarios.Validador;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
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
import static iu.web.server.sistema.utilitarios.Validador.*;

public class Autenticacao implements AutenticacaoIF {
	private static Autenticacao autenticacao;

	// Mapa dos usuarios cadastrados no sistema
	private static Map<String, UsuarioIF> usuariosCadastrados = new TreeMap<String, UsuarioIF>();

	// Mapa das sessoes de usuarios logados no sistema
	private static Map<String, UsuarioIF> sessoes = new TreeMap<String, UsuarioIF>();
	//private final int qntMaxSessoes = Integer.MAX_VALUE - 1024; // Quantidade
																// maxima de
																// sessoes.

	private Autenticacao() {
		usuariosCadastrados = new TreeMap<String, UsuarioIF>();
		sessoes = new TreeMap<String, UsuarioIF>();
		
		Configuracao conf = Configuracao.getInstance();
		File arquivo = new File("./"+conf.getDiretorioBD()+"autenticacao.bd");
		File diretorio = new File("./"+conf.getDiretorioBD());
		if(!diretorio.exists()){
			diretorio.mkdir();
			ObjectOutputStream objectOut = null;
			try {
				arquivo.createNewFile();
				Object[] vetor = new Object[2];
				vetor[0] =  new TreeMap<String, UsuarioIF>();
				vetor[1] =  new TreeMap<String, UsuarioIF>();
				objectOut = new ObjectOutputStream(
	                    new BufferedOutputStream(new FileOutputStream("./"+conf.getDiretorioBD()+"autenticacao.bd")));
	                objectOut.writeObject(vetor);
	                
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					objectOut.close();
				} catch (IOException e) {}
			}
			
		}else{
			try {
				inicializarDados();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public static Autenticacao getInstance() {
		if (autenticacao == null) {
			autenticacao = new Autenticacao();
			
			return autenticacao;
		}
		return autenticacao;
	}
	
	private static void inicializarDados() throws Exception {
		Configuracao conf = Configuracao.getInstance();
        
        ObjectInputStream objectIn = null;
        try{
        	objectIn = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream("./"+conf.getDiretorioBD()+"autenticacao.bd")));
            Object[] vetor = ((Object[])objectIn.readObject());
            usuariosCadastrados = ((TreeMap<String, UsuarioIF>) vetor[0]);
    		sessoes = ((TreeMap<String, UsuarioIF>) vetor[1]);
        
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            objectIn.close();
        }
        
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
		
		if(sessoes.remove(idSessao) == null){
			throw new Exception(Mensagem.SESSAO_JAH_ENCERRADA.getMensagem());
		}
	}

	@Override
	public void notificaPersistenciaDoSistema() {

		Configuracao conf = Configuracao.getInstance();
		File arquivo = new File("./"+conf.getDiretorioBD()+"autenticacao.bd");
		File diretorio = new File("./"+conf.getDiretorioBD());
		if(!diretorio.exists()){
			diretorio.mkdir();
			ObjectOutputStream objectOut = null;
			try {
				arquivo.createNewFile();
				Object[] vetor = new Object[2];
				vetor[0] =  usuariosCadastrados;
				vetor[1] =  sessoes;
				objectOut = new ObjectOutputStream(
	                    new BufferedOutputStream(new FileOutputStream("./"+conf.getDiretorioBD()+"autenticacao.bd")));
				objectOut.reset();
	            objectOut.writeObject(vetor);
	                
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					objectOut.close();
				} catch (IOException e) {}
			}
			
		}else{
			try {
				inicializarDados();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
}
