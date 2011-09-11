package sistema.autenticacao;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import sistema.item.Item;
import sistema.item.ItemIF;
import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.ValidadorString;

public class Autenticacao implements AutenticacaoIF{
	
	// Mapa dos usuarios cadastrados no sistema
	private Map<String, UsuarioIF> usuariosCadastrados = new TreeMap<String, UsuarioIF>();
	
	// Mapa das sessoes de usuarios logados no sistema
	private Map<String, UsuarioIF> sessoes = new TreeMap<String, UsuarioIF>();
	private final int qntMaxSessoes = Integer.MAX_VALUE - 1024; // Quantidade maxima de sessoes.
	
	
	

	@Override
	public void zerarSistema() {
		//FIXME implemente isto;
	}

	@Override
	public void encerrarSistema() {
		System.exit(0);
	}
	
	@Override
	public ItemIF getItemComID(String id) throws Exception {
		for (UsuarioIF usuario : usuariosCadastrados.values()) {
			try {
				for(ItemIF item : usuario.getItens()) {
					if (item.getId().equals(id)) {
						return item;
					}
				}
			} catch (Exception e) {} // Descartando possiveis excecoes que nao precisam ser capturadas.
		}
		throw new Exception(Mensagem.ITEM_INEXISTENTE.getMensagem());
	}
	
	@Override
	public void criarUsuario(String login, String nome, String endereco) throws Exception {
		UsuarioIF novoUsuario = new Usuario(login, nome, endereco);
		usuariosCadastrados.put(login, novoUsuario);
	}

	
	@Override
	public String abrirSessao(String login) throws Exception {

		if( login == null || login.trim().equals("")) throw new Exception("Login inválido");
		if(!existeUsuario(login)) throw new Exception("Usuário inexistente");
		String idSessao = gerarIdSessao();
		while(existeIdSessao(idSessao)){
			idSessao = gerarIdSessao();
		}
		sessoes.put(idSessao, getUsuario(login));
		System.out.println(idSessao);
		System.out.println("help now");
		System.out.println(sessoes.containsKey(idSessao));
		return idSessao;
		
	}

	@Override
	public String getAtributoUsuario(String login, String atributo) throws Exception{
		
		if( login == null || login.trim().equals("")) throw new Exception("Login inválido");
		if(!existeUsuario(login)) throw new Exception("Usuário inexistente");//Atributo inexistente
		if(!ValidadorString.validaString(atributo).equals(Mensagem.OK.getMensagem())) throw new Exception("Atributo inválido");
		UsuarioIF usuario = getUsuario(login);
		
		Class cls = usuario.getClass();
		Field[] campos = cls.getDeclaredFields();
		String valor = null;
		for( Field f : campos){
			//if(f.toString().endsWith("."+atributo)) return usuario.
			System.out.println(f.getName()); //FIXME retirar este syso
			if(f.getName().equals(atributo)){
				f.setAccessible(true);
				valor = (f.get((Usuario)usuario)).toString();
				System.out.println(valor);
			}
		}
		if(valor == null) throw new Exception("Atributo inexistente");
		
		return valor;
	}
	
	/**
	 * Verifica se existe usuario cadastrado.
	 * @param login
	 * 		O login do usuario.
	 * @return
	 * 		True - Se o usuario ja estiver cadastrado.
	 * 		False - Caso contrario.
	 */
	private boolean existeUsuario( String login ){
		return usuariosCadastrados.containsKey(login);
	}
	
	private UsuarioIF getUsuario( String login ) throws Exception{
		return usuariosCadastrados.get(login);
	}
	
	@Override
	public UsuarioIF getUsuarioPeloIDSessao(String idSessao) throws Exception {
		if( idSessao == null || idSessao.trim().equals("")) throw new Exception("Sessão inválida");
		if(!existeIdSessao(idSessao)) throw new Exception("Sessão inexistente");
		return sessoes.get(idSessao);
	}
	
	/**
	 * Gera um idSessao.
	 * @return
	 * 		Um idSessao.
	 */
	private String gerarIdSessao(){
		Random rd = new Random();
		return String.valueOf((rd.nextInt(Integer.MAX_VALUE - 1024 - 1) + 1));
	}
	
	/**
	 * Verifica se um idSessao ja existe.
	 * @param idSessao
	 * 		Um idSessao.
	 * @return
	 * 		True - Se existir o idSessao.
	 * 		False - Se nao existir.
	 */
	public boolean existeIdSessao(String idSessao){
		return sessoes.containsKey(idSessao);
	}
	
	
	public static void main( String[] args) throws Exception{
		Autenticacao aut = new Autenticacao();
		aut.criarUsuario("samirtf", "Samir", "meuEndereco");
		
		aut.getAtributoUsuario("samirtf", "login");
	}

	
	

}
