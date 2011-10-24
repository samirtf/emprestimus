package testes.aceitacao.fachadas;

public class UserFacade17_18 extends UserFacade16 {
	
	public String publicarPedido(String idSessao, String nomeItem, String descricaoItem) throws Exception{
		return sistema.publicarPedido(idSessao, nomeItem, descricaoItem);
	}
	
	public void oferecerItem(String idSessao, String idPublicacaoPedido, String idItem) throws Exception {
		sistema.oferecerItem(idSessao, idPublicacaoPedido, idItem);
	}
	
	public void rePublicarPedido(String idSessao, String idPublicacaoPedido) throws Exception {
		sistema.republicarPedido(idSessao, idPublicacaoPedido);
	}
}
