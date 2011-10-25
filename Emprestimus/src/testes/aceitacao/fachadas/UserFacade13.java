package testes.aceitacao.fachadas;

public class UserFacade13 extends UserFacade12 {

	public void apagarItem(String idSessao, String idItem) throws Exception {
		sistema.apagarItem(idSessao, idItem);
	}

}
