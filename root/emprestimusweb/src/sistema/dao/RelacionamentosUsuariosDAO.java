package sistema.dao;


import java.util.List;
import sistema.excecoes.ArgumentoInvalidoException;
import sistema.persistencia.PersistenciaListener;
import sistema.usuario.CicloDeAmizade;
import sistema.usuario.UsuarioIF;

/**
 * Emprestimus Projeto de Sistemas de Informação I Universidade Federal de
 * Campina Grande
 */

public interface RelacionamentosUsuariosDAO extends PersistenciaListener{

	
	/**
	 * Adiciona um ciclo de amizade a um usuario
	 * 
	 * @param String
	 * 		usuario
	 * 
	 * @throws Exception
	 */
	public void adicionaCicloDeAmizadeAoUsuario(String usuario) throws Exception;

	/**
	 * Remove um ciclo de amizade do usuario
	 * 
	 * @param String
	 * 		usuario
	 * 
	 * @throws Exception
	 */
	public void removeCicloDeAmizadeDoUsuario(String usuario) throws Exception;

	/**
	 * Retorna um ciclo de amizade
	 * 
	 * @param String
	 * 		login do usuario
	 * 
	 * @return CicloDeAmizade
	 * 		Ciclo de amizade do usuario
	 * 
	 * @throws Exception
	 */
	public CicloDeAmizade getCicloDeAmizade(String login) throws Exception;

	/**
	 * Aprova amizade
	 * 
	 * @param String
	 * 		seuLogin
	 * @param String
	 * 		novoAmigo
	 * 
	 * @throws Exception
	 */
	public void aprovarAmizade(String seuLogin, String novoAmigo) throws Exception;

	/**
	 * @param String
	 * 		seuLogin
	 * @param UsuarioIF
	 * 		usuario
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public void aprovouAmizade(String seuLogin, UsuarioIF usuario) throws ArgumentoInvalidoException;

	/**
	 * @param String
	 * 		seuLogin
	 * @param String
	 * 		amigoProcurado
	 * 
	 * @return boolean
	 * 		True caso o amigoProcurado seja amigo do usuario.
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public boolean ehAmigo(String seuLogin, String amigoProcurado) throws ArgumentoInvalidoException;

	/**
	 * @param String
	 * 		seuLogin
	 * @param String
	 * 		loginDoAmigo
	 * 
	 * @return boolean
	 * 		True caso amizade entre os dois usuarios já tenha sido requisitada
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public boolean amizadeDeFoiRequisitada(String seuLogin, String loginDoAmigo) throws ArgumentoInvalidoException;

	/**
	 * Requisita uma amizade
	 * 
	 * @param String
	 * 		seuLogin
	 * @param String
	 * 		loginDoAmigo
	 * 
	 * @throws Exception
	 */
	public void requisitarAmizade(String seuLogin, String loginDoAmigo) throws Exception;

	/**
	 * Adiciona um usuario a lista de pessoas que querem ser amigos do usuario
	 * 
	 * @param String
	 * 		seuLogin
	 * @param UsuarioIF
	 * 		usuarioSolicitante
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public void usuarioQuerSerMeuAmigo(String seuLogin, UsuarioIF usuarioSolicitante) throws ArgumentoInvalidoException;

	/**
	 * Retorna uma compilação da lista de amigos de um usuario
	 * 
	 * @param String
	 * 		seuLogin
	 * 
	 * @return String
	 * 		Compilação da lista de amigos.
	 * 
	 * @throws Exception
	 */
	public String getAmigos(String seuLogin) throws Exception;

	/**
	 * Encontra o usuario dono de um determinado item
	 * 
	 * @param String
	 * 		seuLogin
	 * @param String
	 * 		idItem
	 * 
	 * @return UsuarioIF
	 * 		Usuario dono do Item
	 * 
	 * @throws Exception
	 */
	public UsuarioIF ehItemDoMeuAmigo(String seuLogin, String idItem) throws Exception;
	/**
	 * Procura um determinado amigo a partir de seu login
	 * 
	 * @param String
	 * 		seuLogin
	 * @param String
	 * 		loginDoAmigo
	 * 
	 * @return UsuarioIF
	 * 		Amigo encontrado
	 * 
	 * @throws Exception
	 */
	public UsuarioIF possuoAmigoComEsteLogin(String seuLogin, String loginDoAmigo) throws Exception;
	/**
	 * Pesquisa um Item entre os amigos de um usuario
	 * 
	 * @param String
	 * 		seuLogin
	 * @param String
	 * 		chave
	 * @param String
	 * 		atributo
	 * @param String
	 * 		tipoOrdenacao
	 * @param String
	 * 		criterioOrdenacao
	 * 
	 * @return String
	 * 		Compilação da busca
	 * 
	 * @throws Exception
	 */
	public String pesquisarItem(String seuLogin, String chave,
			String atributo, String tipoOrdenacao, String criterioOrdenacao) throws Exception;
	


	/**
	 * Desfaz uma amizade
	 * 
	 * @param String
	 * 		seuLogin
	 * @param String
	 * 		amigo
	 * 
	 * @throws Exception
	 */
	public void desfazerAmizade(String seuLogin, String amigo) throws Exception;

	/**
	 * Remove um determinado amigo da lista
	 * 
	 * @param String
	 * 		seuLogin
	 * @param UsuarioIF
	 * 		amigo
	 */
	public void removerAmigoDaLista(String seuLogin, UsuarioIF amigo);
	
	/**
	 * Busca a lista de amigos de um usuario.
	 * 
	 * @param String
	 * 		seuLogin
	 * 
	 * @return List<UsuarioIF>
	 * 		Lista de amigos
	 */
	public List<UsuarioIF> getListaAmigos(String seuLogin);

	/**
	 * zera o sistema.
	 */
	public void zerarSistema();
}
