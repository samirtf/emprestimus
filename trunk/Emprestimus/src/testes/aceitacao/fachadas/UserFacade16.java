package testes.aceitacao.fachadas;

public class UserFacade16 extends UserFacade15 {
	
	public String historicoAtividadesConjunto(String idSessao) throws Exception {
		return sistema.historicoAtividades(idSessao);
	}
	
}
