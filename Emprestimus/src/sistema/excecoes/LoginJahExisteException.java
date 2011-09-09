package sistema.excecoes;

/**
 * É lançada caso um login já existente for passado para a construção de um novo
 * usuário.
 * 
 * @author José Nathaniel L. de Abrante - nathaniel.una@gmail.com
 * @since 05/09/2011
 * @version 1.0
 */
public class LoginJahExisteException extends Exception {

	private static final long serialVersionUID = 3598835072516174988L;
	private String login;

	public LoginJahExisteException(String login) {
		super("O login " + login + " já existe");
		this.login = login;
	}
	

	public String getLogin() {
		return login;
	}

}
