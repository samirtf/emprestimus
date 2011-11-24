package iu.web.shared;



/**
 * <p>
 * FieldVerifier validates that the name the user enters is valid.
 * </p>
 * <p>
 * This class is in the <code>shared</code> package because we use it in both
 * the client code and on the server. On the client, we verify that the name is
 * valid before sending an RPC request so the user doesn't have to wait for a
 * network round trip to get feedback. On the server, we verify that the name is
 * correct to ensure that the input is correct regardless of where the RPC
 * originates.
 * </p>
 * <p>
 * When creating a class that is used on both the client and the server, be sure
 * that all code is translatable and does not use native JavaScript. Code that
 * is not translatable (such as code that interacts with a database or the file
 * system) cannot be compiled into client side JavaScript. Code that uses native
 * JavaScript (such as Widgets) cannot be run on the server.
 * </p>
 */
public class VerificadorDeCampos {

	/**
	 * Verifies that the specified name is valid for our service.
	 * 
	 * In this example, we only require that the name is at least four
	 * characters. In your application, you can use more complex checks to ensure
	 * that usernames, passwords, email addresses, URLs, and other fields have the
	 * proper syntax.
	 * 
	 * @param nick the name to validate
	 * @return true if valid, false if invalid
	 */
	public static boolean ehNickValido(String nick) {
		if (nick == null || nick.contains("|")) {
			return false;
		}
		return nick.length() > 3;
	}

	/**
	 * @param senha
	 * @return true se for valida
	 */
	public static boolean ehSenhaValida(String senha) {
		if (senha == null || senha.contains("|")) {
			return false;
		}
		return senha.length() > 5;
	}

	/**
	 * @param nome
	 * @return
	 */
	public static boolean ehNomeValido(String nome) {
		if (nome == null || nome.contains("|")) {
			return false;
		}
		return nome.length() > 3;
	}

	/**
	 * @param endereco
	 * @return
	 */
	public static boolean ehEnderecoValido(String endereco) {
		if (endereco == null || endereco.contains("|")) {
			return false;
		}
		return endereco.length() > 3;
	}
}
