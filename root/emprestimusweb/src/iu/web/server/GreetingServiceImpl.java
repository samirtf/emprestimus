package iu.web.server;

import iu.web.client.GreetingService;
import iu.web.server.sistema.autenticacao.Autenticacao;
import iu.web.shared.Emprestimus;
import iu.web.shared.MensagensWeb;
import iu.web.shared.VerificadorDeCampos;

import java.io.Serializable;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService, Serializable {

	@Override
	public String login(String login, String senha) throws Exception {
		if (!VerificadorDeCampos.ehNickValido(login)) {
			throw new IllegalArgumentException(MensagensWeb.NOME_CURTO.getMensagem());
		} else if (!VerificadorDeCampos.ehSenhaValida(senha)) {
			throw new IllegalArgumentException(MensagensWeb.SENHA_CURTA.getMensagem());
		}
		return escapeHtml(Emprestimus.getInstance().abrirSessao(login, senha));
	}

	@Override
	public String cadastra(String nome, String login, String endereco, String senha) throws Exception {
		try{
		Emprestimus.getInstance().criarUsuario(login, senha, nome, endereco);
		}catch(Exception e){
			e.printStackTrace();
		}
		return escapeHtml(login(login, senha));
	}

	@Override
	public String encerraSessao(String idSessao) throws Exception {
		Emprestimus.getInstance().encerrarSessao(idSessao);
		return "";
	}

	@Override
	public String trocaSenha(String idSessao, String senha) throws Exception {
		Autenticacao.getInstance().getUsuarioPeloIDSessao(idSessao).setSenha(senha);
		return "Senha trocada com sucesso";
	}

	@Override
	public String getNome(String idSessao) throws Exception {
		return escapeHtml(Autenticacao.getInstance().getUsuarioPeloIDSessao(idSessao).getNome());
	}

	@Override
	public String getImagem(String idSessao) throws Exception {
		return escapeHtml(Autenticacao.getInstance().getUsuarioPeloIDSessao(idSessao).getCaminhaImagemPerfil());
	}

	@Override
	public String getHistoricoConjunto(String idSessao) throws Exception {
		return escapeHtml(Emprestimus.getInstance().historicoAtividadesConjunto(idSessao));
	}

	@Override
	public String getAmigos(String idSessao) throws Exception {
		return escapeHtml(Emprestimus.getInstance().getAmigos(idSessao));
	}

	@Override
	public String getMensagens(String idSessao) throws Exception {
		return escapeHtml(Emprestimus.getInstance().lerTopicos(idSessao, "todos"));
	}

	@Override
	public String getItens(String idSessao) throws Exception {
		return escapeHtml(Emprestimus.getInstance().getItens(idSessao));
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

	@Override
	public String getEndereco(String idSessao) throws Exception {
		return escapeHtml(Autenticacao.getInstance().getUsuarioPeloIDSessao(idSessao).getEndereco());
	}

	@Override
	public String getEmprestimosTodos(String idSessao) throws Exception {
		return escapeHtml(Autenticacao.getInstance().getUsuarioPeloIDSessao(idSessao).getEmprestimos("todos"));
	}
	
	@Override
	public String getEmprestimosBeneficiador(String idSessao) throws Exception {
		return escapeHtml(Autenticacao.getInstance().getUsuarioPeloIDSessao(idSessao).getEmprestimos("beneficiador"));
	}
	
	@Override
	public String getEmprestimosEmprestador(String idSessao) throws Exception {
		return escapeHtml(Autenticacao.getInstance().getUsuarioPeloIDSessao(idSessao).getEmprestimos("emprestador"));
	}
	
}
