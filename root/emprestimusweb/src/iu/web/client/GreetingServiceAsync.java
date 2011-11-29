package iu.web.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	void login(String nick, String senha, AsyncCallback<String> callback) throws IllegalArgumentException;

	void cadastra(String nome, String login, String endereco, String senha,
			AsyncCallback<String> callback) throws IllegalArgumentException;

	void encerraSessao(String idSessao, AsyncCallback<String> callback);

	void trocaSenha(String idSessao, String senha, AsyncCallback<String> callback);

	void getNome(String idSessao, AsyncCallback<String> callback);

	void getImagem(String idSessao, AsyncCallback<String> callback);

	void getHistoricoConjunto(String idSessao, AsyncCallback<String> callback);

	void getAmigos(String idSessao, AsyncCallback<String> callback);
}
