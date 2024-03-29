package testes.aceitacao.fachadas;

public class UserFacade04 extends UserFacade03 {

	/**
	 * Cria usuario a partir de um login, um nome e um endereco.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param nome
	 *            O nome do usuario.
	 * @param endereco
	 *            O endereco do usuario.
	 */
	@Override
	public void criarUsuario(String login, String nome, String endereco) throws Exception {
		sistema.criarUsuario(login, nome, endereco);
	}

	/**
	 * Abre uma sessao para o usuario.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @return O id de sessao para o usuario.
	 */
	@Override
	public String abrirSessao(String login) throws Exception {
		return sistema.abrirSessao(login);
	}

	public String getRequisicoesDeAmizade(String idSessao) throws Exception {
		return sistema.getRequisicoesDeAmizade(idSessao);
	}

	public void requisitarAmizade(String idSessao, String login) throws Exception {
		sistema.requisitarAmizade(idSessao, login);
	}

	public String ehAmigo(String idSessao, String login) throws Exception {
		return sistema.ehAmigo(idSessao, login);
	}

	public void aprovarAmizade(String idSessao, String login) throws Exception {
		sistema.aprovarAmizade(idSessao, login);
	}

	/**
	 * Metodo que zera o Sistema.
	 * 
	 */
	@Override
	public void zerarSistema() {
		sistema.zerarSistema();
	}

	/**
	 * Metodo que encerra o Sistema.
	 * 
	 */
	@Override
	public void encerrarSistema() {
		sistema.encerrarSistema();
	}

}
