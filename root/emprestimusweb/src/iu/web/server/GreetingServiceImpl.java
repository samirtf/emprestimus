package iu.web.server;

import java.rmi.UnexpectedException;
import java.util.regex.Pattern;

import iu.Emprestimus;
import iu.web.client.GreetingService;
import iu.web.shared.VerificadorDeCampos;
import iu.web.shared.MensagensWeb;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	public String greetServer(String input) throws IllegalArgumentException {
		try {
			String[] array = input.split(Pattern.quote("|"));
			
			if (array[0].equalsIgnoreCase("login")) {
				return realizaLogin(array[1], array[2]);
			} else if (array[0].equalsIgnoreCase("cadastro")) {
				return cadastraUsuario(array[1], array[2], array[3], array[4]);
			}
		} catch (UnexpectedException e){
			throw new IllegalArgumentException("Entrada inconsistente");
		} catch (Exception e) {
			throw new IllegalArgumentException(e);
		}
		return "Opçao inválida";
	}

	/**
	 * 
	 * @param nick
	 * @param senha
	 * @throws Exception
	 */
	private String realizaLogin(String nick, String senha) throws Exception {
		if (!VerificadorDeCampos.ehNickValido(nick)) {
			throw new Exception(MensagensWeb.NOME_CURTO.getMensagem());
		} else if (!VerificadorDeCampos.ehSenhaValida(senha)) {
			throw new Exception(MensagensWeb.SENHA_CURTA.getMensagem());
		}
		return Emprestimus.getInstance().abrirSessao(nick, senha);
		
	}

	/**
	 * 
	 * @param nome
	 * @param nick
	 * @param endereco
	 * @param senha
	 * @return 
	 * @throws Exception
	 */
	private String cadastraUsuario(String nome, String nick, String endereco,
			String senha) throws Exception {
		Emprestimus.getInstance().criarUsuario(nick, senha, nome, endereco);
		return realizaLogin(nick, senha);
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
//	private String escapeHtml(String html) {
//		if (html == null) {
//			return null;
//		}
//		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">",
//				"&gt;");
//	}
}
