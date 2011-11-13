package testes.aceitacao.fachadas;

public class UserFacade05 extends UserFacade04 {

	public String getAmigos(String idSessao) throws Exception {
		return sistema.getAmigos(idSessao);
	}

	public String getAmigos(String idSessao, String login) throws Exception {
		return sistema.getAmigos(idSessao, login);
	}

	public String getItens(String idSessao) throws Exception {
		return sistema.getItens(idSessao);
	}

	public String getItens(String idSessao, String login) throws Exception {
		return sistema.getItens(idSessao, login);
	}

}
