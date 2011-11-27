package iu.web.client;

import iu.web.shared.UsuarioSimples;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String login(String login, String senha) throws Exception;

	String cadastra(String nome, String login, String endereco, String senha) throws Exception;
	
	UsuarioSimples getUsuarioSimples(String idSessao) throws Exception;
	
	String encerraSessao(String idSessao) throws Exception;
}
