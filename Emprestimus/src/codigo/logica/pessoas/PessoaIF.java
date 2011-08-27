/**
 * Pacote da interface
 */
package codigo.logica.pessoas;

/**
 * @author Joeffison Silverio de Andrade, joeffisonsa@gmail.com
 * @version 1.0
 */
public interface PessoaIF {
	
	public String getNome();
	public String getLogin();
	public String getEndereco();
	
	public void setNome(String nome);
	public void setLogin(String login);
	public void setEndereco(String endereco);

}
