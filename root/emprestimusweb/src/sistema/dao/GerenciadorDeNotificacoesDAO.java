package sistema.dao;

import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.ItemIF;
import sistema.notificacao.Rack;
import sistema.usuario.UsuarioIF;


/**
 * @author Mobile
 * 
 */
public interface GerenciadorDeNotificacoesDAO {

	/**
	 * Adiciona um Rack a um determinado usuario
	 * 
	 * @param String
	 * 		usuario
	 * 
	 * @throws Exception
	 * 		Caso o rack já tenha sido cadastrado
	 */
	public void adicionaRackAoUsuario(String usuario) throws Exception;

	/**
	 * Remove o Rack de um usuario
	 * 
	 * @param String
	 * 		usuario
	 * 
	 * @throws Exception
	 */
	public void removeRackDoUsuario(String usuario) throws Exception;

	/**
	 * @param String login
	 * 		Usuario cujo rack sera buscado
	 * 
	 * @return Rack
	 * 		Rack do usuario
	 * 
	 * @throws Exception
	 */
	public Rack getRack(String login) throws Exception;

	/**
	 * Adiciona no historico um novo Item
	 * 
	 * @param String
	 * 		seuLogin
	 * @param ItemIF
	 * 		item
	 * 
	 * @throws Exception
	 */
	public void addHistoricoNovoItem(String seuLogin, ItemIF item) throws Exception;

	/**
	 * Adiciona ao historico uma amizade aprovada
	 * 
	 * @param String
	 * 		seuLogin
	 * @param UsuarioIF
	 * 		amigo
	 * 
	 * @throws Exception
	 */
	public void addHistoricoAmizadeAprovada(String seuLogin, UsuarioIF amigo) throws Exception;

	/**
	 * Adiciona ao historico um emprestimo em andamento
	 * 
	 * @param String
	 * 		seuLogin
	 * @param UsuarioIF
	 * 		amigo
	 * @param ItemIF
	 * 		item
	 * 
	 * @throws Exception
	 */
	public void addHistoricoEmprestimoEmAndamento(String seuLogin, UsuarioIF amigo,
			ItemIF item) throws Exception;

	/**
	 * Zera o historico de um usuario
	 * 
	 * @param String
	 * 		seuLogin
	 */
	public void zerarHistorico(String seuLogin);

	/**
	 * Retorna o historico em forma de String
	 * 
	 * @param String
	 * 		seuLogin
	 * 
	 * @return String
	 * 		Copilação do historico
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public String getHistoricoToString(String seuLogin) throws ArgumentoInvalidoException;
	
	/**
	 * Adiciona no historico um interesse por item
	 * 
	 * @param String
	 * 		seuLogin
	 * @param UsuarioIF
	 * 		amigo
	 * @param ItemIF
	 * 		item
	 * 
	 * @throws Exception
	 */
	public void addHistoricoInteressePorItem(String seuLogin, UsuarioIF amigo, ItemIF item) throws Exception;

	/**
	 * Adiciona ao historico um Termino de emprestimo
	 * @param String
	 * 		seuLogin
	 * @param ItemIF
	 * 		item
	 * @throws Exception
	 */
	public void addHistoricoTerminoEmprestimo(String seuLogin, ItemIF item) throws Exception;

	/**
	 * Retorna do historico uma compilação de atividades em conjunto
	 * @param UsuarioIF
	 * 		usuario
	 * 
	 * @return String
	 * 		Compilação das atividades em conjunto
	 * 
	 * @throws Exception
	 */
	public String getHistoricoAtividadesConjunto(UsuarioIF usuario) throws Exception;

	/**
	 * Adiciona ao historico uma publicação de pedido
	 * 
	 * @param String
	 * 		seuLogin
	 * @param String 
	 * 		nomeItem
	 * @param String 
	 * 		descricaoItem
	 * 
	 * @return String
	 * 		ID da notificação
	 * 
	 * @throws Exception
	 */
	public String addHistoricoPublicarPedido(String seuLogin, String nomeItem,
			String descricaoItem) throws Exception;

	/**
	 * Republica um pedido
	 * 
	 * @param UsuarioIF
	 * 		usuario
	 * @param String
	 * 		idPublicacaoPedido
	 * 
	 * @throws Exception
	 */
	public void republicarPedido(UsuarioIF usuario, String idPublicacaoPedido) throws Exception;
	/**
	 * Retorna o sistema para suas configurações iniciais.
	 */
	public void zerarSistema();

}
