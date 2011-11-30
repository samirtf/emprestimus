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

	void getMensagens(String idSessao, AsyncCallback<String> asyncCallback);

	void getItens(String idSessao, AsyncCallback<String> asyncCallback);

	void getEndereco(String idSessao, AsyncCallback<String> asyncCallback);

	void getEmprestimosTodos(String idSessao, AsyncCallback<String> asyncCallback);

	void getEmprestimosBeneficiador(String idSessao,
			AsyncCallback<String> callback);

	void getEmprestimosEmprestador(String idSessao,
			AsyncCallback<String> asyncCallback);

	void localizarUsuario(String idSessao, AsyncCallback<String> callback);

	void localizarUsuario(String idSessao, String chave, String atributo,
			AsyncCallback<String> callback);

	void getNomeAmigo(String loginAmigo, AsyncCallback<String> callback);

	void getImagemAmigo(String loginAmigo, AsyncCallback<String> callback);

	void getHistorico(String idSessao, String loginAmigo,
			AsyncCallback<String> callback);

	void getAmigosDoAmigo(String idSessao, String loginAmigo,
			AsyncCallback<String> callback);
}
