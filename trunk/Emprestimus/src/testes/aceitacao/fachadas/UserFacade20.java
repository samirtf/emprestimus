package testes.aceitacao.fachadas;

public class UserFacade20 extends UserFacade19 {
	
	public String localizarUsuario(String idSessao, String chave, String atributo) throws Exception {
		return sistema.localizarUsuario(idSessao, chave, atributo);
	}
	public String localizarUsuario(String idSessao) throws Exception {
		return sistema.localizarUsuario(idSessao);
	}

}
