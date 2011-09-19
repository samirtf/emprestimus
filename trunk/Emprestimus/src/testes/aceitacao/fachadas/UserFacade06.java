package testes.aceitacao.fachadas;

public class UserFacade06 extends UserFacade05{
	
	public String requisitarEmprestimo (String idSessao, String idItem, int duracao) throws Exception{
		return sistema.requisitarEmprestimo(idSessao, idItem, duracao);
	}

}
