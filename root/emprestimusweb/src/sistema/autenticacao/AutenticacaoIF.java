package sistema.autenticacao;

import java.util.Collection;
import java.util.List;

import sistema.item.ItemIF;
import sistema.persistencia.PersistenciaListener;
import sistema.usuario.UsuarioIF;

public interface AutenticacaoIF extends PersistenciaListener {
	
	/**
	 * Reseta o sistema para as configurações iniciais
	 */
	public void zerarSistema();
	
	/**
	 * Encerra a execução do sistema
	 */
	public void encerrarSistema();
	
	/**
	 * Cria um novo usuario dentro do sistema
	 * 
	 * @param login - String
	 * 		Login do usuario
	 * @param nome - String
	 * 		Nome do usuario
	 * @param endereco - String
	 * 		Endereco do usuario
	 * 
	 * @throws Exception
	 * 		Caso o login fornecido já exista dentro do sistema
	 */
	public void criarUsuario( String login, String nome, String endereco ) throws Exception;
	
	/**
	 * Abre uma nova sessão para um determinado usuario
	 * 
	 * @param login - String
	 * 		Login do usuario
	 * 
	 * @return String
	 * 		ID da nova sessão
	 * 
	 * @throws Exception
	 * 		caso o usuario seja inexistente ou o login fornecido seja considerado invalido
	 */
	public String abrirSessao( String login ) throws Exception;
	
	/**
	 *  * Abre uma nova sessão para um determinado usuario
	 * 
	 * @param login - String
	 * 		Login do usuario
	 * @param senha - String
	 * 		Senha do usuario
	 * 
	 * @return String
	 * 		ID da nova sessão
	 * 
	 * @throws Exception
	 * 		caso o usuario seja inexistente ou o login fornecido seja considerado invalido
	 */
	public String abrirSessao( String login, String senha ) throws Exception;
	
	/**
	 * Retorna um determinado atributo de um determinado usuario
	 * 
	 * @param login - String
	 * 		Login do usuario
	 * @param atributo - String
	 * 		Atributo do usuario
	 * 
	 * @return String
	 * 		Valor do atributo do usuario
	 * 
	 * @throws Exception
	 * 		Caso seja fornecido um atributo inexistente
	 */
	public String getAtributoUsuario( String login,  String atributo ) throws Exception;
	
	/**
	 * Testa se uma determinada sessão existe dentro do sistema
	 * 
	 * @param idSessao - String
	 * 		ID da sessão a ser testada
	 * 
	 * @return boolean
	 * 		True caso a sessão exista dentro do sistema
	 */
	public boolean existeIdSessao(String idSessao);

	/**
	 * Retorna um determinado usuario a partir de seu ID de sessão
	 * 
	 * @param idSessao - String
	 * 		ID da sessão do usuario desejado
	 * 
	 * @return UsuarioIF
	 * 		Objeto referente ao usuario desejado
	 * 
	 * @throws Exception
	 */
	public UsuarioIF getUsuarioPeloIDSessao(String idSessao) throws Exception;

	/**
	 * Retorna um determinado item a partir de seu ID
	 * 
	 * @param id - String
	 * 		ID do item
	 * 
	 * @return ItemIF
	 * 		Objeto referente ao Item desejado
	 * 
	 * @throws Exception
	 */
	public ItemIF getItemComID(String id) throws Exception;

	/**
	 * Faz uma busca de usuarios dentro do sistema.
	 * 
	 * @param nome - String
	 * 		Nome do usuario desejado
	 * 
	 * @return List<UsuarioIF>
	 * 		Lista de usuarios encontrados.
	 */
	public List<UsuarioIF> getUsuarioNome(String nome);
	
	/**
	 * Faz uma busca de usuarios dentro do sistema.
	 * 
	 * @param endereco - String
	 * 		Endereço do usuario desejado
	 * 
	 * @return List<UsuarioIF>
	 * 		Lista de usuarios encontrados.
	 */
	public List<UsuarioIF> getUsuarioEndereco(String endereco);
	
	/**
	 * @return Collection<UsuarioIF>
	 */
	public Collection<UsuarioIF> getListaUsuarios();
	
	public void criarUsuario( String login, String senha, String nome, String endereco ) throws Exception;

}
