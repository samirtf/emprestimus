package codigo.logica.pessoas;

/**
 * @author Nathaniel
 * @since 25/08/2011
 * @version 2.0
 */
public class Usuario implements PessoaIF {
	
	private String nome;
	private String login;
	private String endereco;

	/**
	 * 
	 */
	private Usuario() {
		// Construtor padrao privado impede a construcao do objeto sem parametros.
	}
	
	public Usuario(String nome, String login, String endereco){
		this.nome = nome;
		this.login = login;
		this.endereco = endereco;
	}

	/* (non-Javadoc)
	 * @see codigo.logica.pessoas.PessoaIF#getNome()
	 */
	@Override
	public String getNome() {
		return this.nome;
	}

	/* (non-Javadoc)
	 * @see codigo.logica.pessoas.PessoaIF#getLogin()
	 */
	@Override
	public String getLogin() {
		return this.login;
	}

	/* (non-Javadoc)
	 * @see codigo.logica.pessoas.PessoaIF#getEndereco()
	 */
	@Override
	public String getEndereco() {
		return this.endereco;
	}

	/* (non-Javadoc)
	 * @see codigo.logica.pessoas.PessoaIF#setNome(java.lang.String)
	 */
	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	/* (non-Javadoc)
	 * @see codigo.logica.pessoas.PessoaIF#setLogin(java.lang.String)
	 */
	@Override
	public void setLogin(String login) {
		this.login = login;
	}

	/* (non-Javadoc)
	 * @see codigo.logica.pessoas.PessoaIF#setEndereco(java.lang.String)
	 */
	@Override
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}
	
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Usuario){
			return this.login.equals(((Usuario)obj).login);
		}
		return false;
	}

}
