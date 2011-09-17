package testes.aceitacao.fachadas;

public class UserFacade04 extends UserFacade03{

	/**
	 * Cria usuario a partir de um login, um nome e um endereco.
	 * @param login
	 *     O login do usuario.
	 * @param nome
	 *     O nome do usuario.
	 * @param endereco
	 *     O endereco do usuario.
	 */
	public void criarUsuario(String login, String nome, String endereco) throws Exception{
		sistema.criarUsuario(login, nome, endereco);
	}

	/**
	 * Abre uma sessao para o usuario.
	 * @param login
	 *     O login do usuario.
	 * @return
	 *     O id de sessao para o usuario.
	 */
	public String abrirSessao(String login) throws Exception{
		return sistema.abrirSessao(login);
	}
	
	public String getRequisicoesDeAmizade(String idSessao) {
		return sistema.getRequisicoesDeAmizade(idSessao);
	}
	
	public void requisitarAmizade(String idSessao, String login) {
		sistema.requisitarAmizade(idSessao, login);
	}
	
	public String ehAmigo(String idSessao, String login) {
		return sistema.ehAmigo(idSessao, login);
	}
	
	public void aprovarAmizade(String idSessao, String login) {
		sistema.aprovarAmizade(idSessao, login);
	}
	
	/**
	 * Metodo que zera o Sistema.
	 * 
	 */
	public void zerarSistema() {
		sistema.zerarSistema();
	}

	/**
	 * Metodo que encerra o Sistema.
	 * 
	 */
	public void encerrarSistema() {
		sistema.encerrarSistema();
	}

}
