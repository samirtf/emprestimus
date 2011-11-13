package testes.aceitacao.fachadas;

public class UserFacade06 extends UserFacade05 {

	public String requisitarEmprestimo(String idSessao, String idItem, int duracao) throws Exception {
		return sistema.requisitarEmprestimo(idSessao, idItem, duracao);
	}

	public String getEmprestimos(String idSessao, String tipo) throws Exception {
		return sistema.getEmprestimos(idSessao, tipo);
	}

	public String aprovarEmprestimo(String idSessao, String idRequisicaoEmprestimo) throws Exception {
		return sistema.aprovarEmprestimo(idSessao, idRequisicaoEmprestimo);
	}

}
