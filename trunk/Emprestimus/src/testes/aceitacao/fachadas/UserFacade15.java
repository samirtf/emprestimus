package testes.aceitacao.fachadas;

public class UserFacade15 extends UserFacade14 {
	public String historicoAtividades(String idSessao) throws Exception {
		return sistema.historicoAtividades(idSessao);
	}

}
