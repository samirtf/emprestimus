package testes.aceitacao.fachadas;

public class UserFacade14 extends UserFacade13 {

	public String getRanking(String idSessao, String categoria) throws Exception {
		return sistema.getRanking(idSessao, categoria);
	}
}
