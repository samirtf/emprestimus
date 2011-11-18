package iu.web.server;

import java.util.regex.Pattern;

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
		String nome = null;
		String senha = null;
		try {
			String[] array = input.split(Pattern.quote("|"));
			nome = array[0];
			senha = array[1];
		} catch (Exception e) {
			throw new IllegalArgumentException("Entrada inconsistente");			
		}
		// Verify that the input is valid. 
		if (!VerificadorDeCampos.ehNickValido(nome)) {
			// If the nome is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(MensagensWeb.NOME_CURTO.getMensagem());
		} else if (!VerificadorDeCampos.ehSenhaValida(senha)) {
			// If the senha is not valid, throw an IllegalArgumentException back to
			// the client.
			throw new IllegalArgumentException(MensagensWeb.SENHA_CURTA.getMensagem());
		}


//		// Escape data from the client to avoid cross-site script vulnerabilities.
//		nome = escapeHtml(nome);
//		senha = escapeHtml(senha);

		return nome;
	}

	/**
	 * Escape an html string. Escaping data received from the client helps to
	 * prevent cross-site script vulnerabilities.
	 * 
	 * @param html the html string to escape
	 * @return the escaped string
	 */
	private String escapeHtml(String html) {
		if (html == null) {
			return null;
		}
		return html.replaceAll("&", "&amp;").replaceAll("<", "&lt;").replaceAll(">",
				"&gt;");
	}
}
