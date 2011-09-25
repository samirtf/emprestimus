package testes.aceitacao.fachadas;

public class UserFacade11 extends UserFacade10{
	
	public String pesquisarItem( String idSessao, String chave, String atributo, 
			String tipoOrdenacao, String criterioOrdenacao ) throws Exception{
		return sistema.pesquisarItem(idSessao, chave, atributo, tipoOrdenacao, criterioOrdenacao);
	}

}
