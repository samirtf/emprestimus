package testes.aceitacao.fachadas;

public class UserFacade07 extends UserFacade06 {

	public void devolverItem(String idSessao, String idEmprestimo) throws Exception {
		sistema.devolverItem(idSessao, idEmprestimo);
	}

	public void confirmarTerminoEmprestimo(String idSessao, String idEmprestimo) throws Exception {
		sistema.confirmarTerminoEmprestimo(idSessao, idEmprestimo);
	}

}
