package iu.web.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	String login(String login, String senha) throws Exception;

	String cadastra(String nome, String login, String endereco, String senha) throws Exception;
	
	String encerraSessao(String idSessao) throws Exception;
	
	String trocaSenha(String idSessao, String senha) throws Exception;
	
	String getNome(String idSessao) throws Exception;
	
	String getImagem(String idSessao) throws Exception;
	
	String getHistoricoConjunto(String idSessao) throws Exception;
	
	String getAmigos(String idSessao) throws Exception;
	
	String getMensagens(String idSessao) throws Exception;
	
	String getItens(String idSessao) throws Exception;

	String getEndereco(String idSessao) throws Exception;

	String getEmprestimosTodos(String idSessao) throws Exception;

	String getEmprestimosBeneficiador(String idSessao) throws Exception;

	String getEmprestimosEmprestador(String idSessao) throws Exception;

	String localizarUsuario(String idSessao) throws Exception;

	String localizarUsuario(String idSessao, String chave, String atributo)
			throws Exception;

	String getNomeAmigo(String loginAmigo) throws Exception;

	String getImagemAmigo(String loginAmigo) throws Exception;

	String getHistorico(String idSessao, String loginAmigo) throws Exception;

	String getAmigosDoAmigo(String idSessao, String loginAmigo)
			throws Exception;
	
	
}
