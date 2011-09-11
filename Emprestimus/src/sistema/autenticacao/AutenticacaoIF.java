package sistema.autenticacao;

import java.util.List;

import sistema.item.ItemIF;
import sistema.usuario.UsuarioIF;

public interface AutenticacaoIF {
	
	public void zerarSistema();
	
	public void encerrarSistema();
	
	public void criarUsuario( String login, String nome, String endereco ) throws Exception;
	
	public String abrirSessao( String login ) throws Exception;
	
	public String getAtributoUsuario( String login,  String atributo ) throws Exception;
	
	public boolean existeIdSessao(String idSessao);
	
	public UsuarioIF getUsuarioPeloIDSessao( String idSessao ) throws Exception;

	public ItemIF getItemComID(String id) throws Exception;

	public List<UsuarioIF> getUsuarioNome(String nome);
	
	public List<UsuarioIF> getUsuarioEndereco(String endereco);

}
