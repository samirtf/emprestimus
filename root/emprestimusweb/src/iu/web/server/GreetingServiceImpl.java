package iu.web.server;

import iu.Emprestimus;
import iu.web.client.GreetingService;
import iu.web.shared.MensagensWeb;
import iu.web.shared.UsuarioSimples;
import iu.web.shared.VerificadorDeCampos;
import sistema.autenticacao.Autenticacao;
import sistema.usuario.UsuarioIF;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

	@Override
	public String login(String login, String senha) throws Exception {
		if (!VerificadorDeCampos.ehNickValido(login)) {
			throw new IllegalArgumentException(MensagensWeb.NOME_CURTO.getMensagem());
		} else if (!VerificadorDeCampos.ehSenhaValida(senha)) {
			throw new IllegalArgumentException(MensagensWeb.SENHA_CURTA.getMensagem());
		}
		return Emprestimus.getInstance().abrirSessao(login, senha);
	}

	@Override
	public String cadastra(String nome, String login, String endereco, String senha) throws Exception {
		Emprestimus.getInstance().criarUsuario(login, senha, nome, endereco);
		return login(login, senha);
	}

	@Override
	public UsuarioSimples getUsuarioSimples(String idSessao) throws Exception {
		UsuarioIF usuario = Autenticacao.getInstance().getUsuarioPeloIDSessao(idSessao);
		
		UsuarioSimples usuarioSimples = new UsuarioSimples();
		usuarioSimples.setNome(usuario.getNome());
		usuarioSimples.setFoto(usuario.getCaminhaImagemPerfil());
		usuarioSimples.setHistorico(usuario.getHistoricoToString());
		
		return usuarioSimples;
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
