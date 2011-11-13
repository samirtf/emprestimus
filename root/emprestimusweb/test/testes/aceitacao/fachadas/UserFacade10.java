package testes.aceitacao.fachadas;

public class UserFacade10 extends UserFacade09 {
	public void registrarInteresse(String idSessao, String idItem) throws Exception {
		sistema.registraInteresse(idSessao, idItem);
	}

}
