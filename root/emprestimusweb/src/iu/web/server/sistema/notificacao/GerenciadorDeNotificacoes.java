/**
 * 
 */
package iu.web.server.sistema.notificacao;

import static iu.web.server.sistema.utilitarios.Validador.assertStringNaoVazia;

import iu.web.server.sistema.autenticacao.Autenticacao;
import iu.web.server.sistema.autenticacao.Configuracao;
import iu.web.server.sistema.excecoes.ArgumentoInvalidoException;
import iu.web.server.sistema.item.ItemIF;
import iu.web.server.sistema.persistencia.NotificacaoRepositorio;
import iu.web.server.sistema.usuario.UsuarioIF;
import iu.web.server.sistema.utilitarios.Mensagem;
import iu.web.server.sistema.utilitarios.Validador;

/*import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;*/
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


/**
 * @author Mobile
 * 
 */
public class GerenciadorDeNotificacoes implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4185149564223983280L;
	private static GerenciadorDeNotificacoes gerenciadorDeNotificacoes;
	private static Map<String, Rack> historicos;

	private GerenciadorDeNotificacoes() {
		historicos = new TreeMap<String, Rack>();
		
		Configuracao conf = Configuracao.getInstance();
		/*File arquivo = new File("./"+conf.getDiretorioBD()+"gerenciadorNotificacoes.bd");
		File diretorio = new File("./"+conf.getDiretorioBD());
		if(!diretorio.exists() || !arquivo.exists()){
			diretorio.mkdir();
			try {
				arquivo.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			ObjectOutputStream objectOut = null;
			try {
				Object[] vetor = new Object[1];
				vetor[0] =  new TreeMap<String, Rack>();
				historicos = new TreeMap<String, Rack>();
				objectOut = new ObjectOutputStream(
	                    new BufferedOutputStream(new FileOutputStream("./"+conf.getDiretorioBD()+"gerenciadorNotificacoes.bd")));
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
		}*/
		try {
			inicializarDados();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public synchronized static GerenciadorDeNotificacoes getInstance() {
		if (gerenciadorDeNotificacoes == null)
			gerenciadorDeNotificacoes = new GerenciadorDeNotificacoes();
		return gerenciadorDeNotificacoes;
	}
	
	private synchronized static void inicializarDados() throws Exception {
		Configuracao conf = Configuracao.getInstance();
        
       /* ObjectInputStream objectIn = null;
        try{
        	objectIn = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream("./"+conf.getDiretorioBD()+"gerenciadorNotificacoes.bd")));
            Object[] vetor = ((Object[])objectIn.readObject());
            historicos = ((TreeMap<String, Rack>) vetor[0]);
        
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            objectIn.close();
        }*/
        historicos = new TreeMap<String, Rack>();
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
	public synchronized void adicionaRackAoUsuario(String usuario) throws Exception {
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
	public synchronized void removeRackDoUsuario(String usuario) throws Exception {
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
	public synchronized Rack getRack(String login) throws Exception {
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
	public synchronized void addHistoricoNovoItem(String seuLogin, ItemIF item) throws Exception {
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
	public synchronized void addHistoricoAmizadeAprovada(String seuLogin, UsuarioIF amigo) throws Exception {
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
	public synchronized void addHistoricoEmprestimoEmAndamento(String seuLogin, UsuarioIF amigo,
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
	public synchronized void zerarHistorico(String seuLogin) {
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
	public synchronized String getHistoricoToString(String seuLogin) throws ArgumentoInvalidoException {
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
	public synchronized void addHistoricoInteressePorItem(String seuLogin, UsuarioIF amigo, ItemIF item) throws Exception {
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
	public synchronized void addHistoricoTerminoEmprestimo(String seuLogin, ItemIF item) throws Exception {
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
	public synchronized String addHistoricoPublicarPedido(String seuLogin, String nomeItem,
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
	public synchronized void republicarPedido(UsuarioIF usuario, String idPublicacaoPedido) throws Exception {
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
	public synchronized void zerarSistema() {
		GerenciadorDeNotificacoes.historicos.clear();
	}

	public synchronized void salvarEmArquivo() {
		Configuracao conf = Configuracao.getInstance();
		/*File arquivo = new File("./"+conf.getDiretorioBD()+"gerenciadorNotificacoes.bd");
		ObjectOutputStream objectOut = null;
		try {
			Object[] vetor = new Object[1];
			historicos = ((TreeMap<String, Rack>) vetor[0]);
			objectOut = new ObjectOutputStream(
	            new BufferedOutputStream(new FileOutputStream("./"+conf.getDiretorioBD()+"gerenciadorNotificacoes.bd")));
			objectOut.reset();
	           objectOut.writeObject(vetor);
	                
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				objectOut.close();
			} catch (IOException e) {}
		}*/

	}

}
