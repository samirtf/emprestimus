/**
 * Pacote da interface
 */
package codigo.logica.pessoas;

/**
 * @author Joeffison Silverio de Andrade, joeffisonsa@gmail.com
 * @version 1.0
 */
public interface PessoaIF {
	
	String getNome();
	String getLogin();
	String getEndereco();
	
	void setNome(String nome);
	void setLogin(String login);
	void setEndereco(String endereco);

}
