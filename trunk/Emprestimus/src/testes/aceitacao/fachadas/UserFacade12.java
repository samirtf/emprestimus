package testes.aceitacao.fachadas;

public class UserFacade12 extends UserFacade11 {

	public void desfazerAmizade(String idSessao, String amigo) throws Exception {
		sistema.desfazerAmizade(idSessao, amigo);
	}
}
