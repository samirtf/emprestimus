package testes.aceitacao.fachadas;

public class UserFacade09 extends UserFacade08 {
	
	public void requisitarDevolucao(String idSessao, String idEmprestimo) throws Exception {
		sistema.requisitarDevolucao(idSessao, idEmprestimo);
	}
	
	public void adicionarDias(String dias) throws Exception {
		sistema.adicionarDias(dias);
		//TODO implemente isto!
	}
	
	

}
