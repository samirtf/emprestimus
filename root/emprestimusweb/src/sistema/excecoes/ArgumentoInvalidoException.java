package sistema.excecoes;

/**
 * É lançada em caso de algum argumento ser invalido em alguma operação.
 * 
 * @author José Nathaniel L. de Abrante - nathaniel.una@gmail.com
 * @since 14/09/2011
 * @version 1.0
 */
public class ArgumentoInvalidoException extends Exception {

	private static final long serialVersionUID = -3102382115380877513L;

	public ArgumentoInvalidoException(String mensagem) {
		super(mensagem);
	}
}
