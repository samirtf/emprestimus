package iu.web.client;

import iu.web.shared.UsuarioSimples;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void login(String nick, String senha, AsyncCallback<String> callback) throws IllegalArgumentException;

	void cadastra(String nome, String login, String endereco, String senha,
			AsyncCallback<String> callback) throws IllegalArgumentException;

	void getUsuarioSimples(String idSessao, AsyncCallback<UsuarioSimples> callback) throws Exception;
	
	void encerraSessao(String idSessao, AsyncCallback<String> callback);
}
