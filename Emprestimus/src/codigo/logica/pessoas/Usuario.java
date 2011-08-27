package codigo.logica.pessoas;

import java.util.LinkedList;
import java.util.List;

import codigo.logica.itens.Item;

/**
 * @author Nathaniel
 * @since 25/08/2011
 * @version 2.0
 */
public class Usuario implements PessoaIF {
	private String nome;
	private String login;
	private String endereco;
	private final int id;

	private List<Usuario> amigos;
	private List<Usuario> solicitacoes;
	private List<Item> itens;

	/**
	 * @param nome
	 * @param login
	 * @param endereco
	 * @param id
	 */
	public Usuario(String nome, String login, String endereco, int id) throws IllegalArgumentException {
		if (nome == null || login == null || endereco == null) {
			throw new IllegalArgumentException("Parametro nullo");
		}
		this.nome = nome;
		this.login = login;
		this.endereco = endereco;
		this.id = id;

		this.amigos = new LinkedList<Usuario>();
		this.solicitacoes = new LinkedList<Usuario>();
		this.itens = new LinkedList<Item>();
	}

	/**
	 * Envia sua solicitacao de amizade para um amigo. Caso a solicitacao jah
	 * exista ela nao eh alterada.
	 * 
	 * @param outro
	 *            Usuario que voce esta solicitando amizade.
	 * @return False caso voce jah tenha solicitado esta amizade antes
	 * @throws Exception
	 *             Caso o outro ja esteja entre os seus amigos
	 */
	public boolean solicitarAmizade(Usuario outro) throws Exception {
		if (amigos.contains(outro))
			throw new Exception("Usuario jah existe nos amigos");
		if (solicitacoes.contains(outro)) {
			return aceitarAmizade(outro);
		} else if (outro.solicitacoes.contains(this)) {
			return false;
		} else {
			outro.solicitacoes.add(this);
			return true;
		}
	}
		
	private Usuario() {
		id = 0;
		// Construtor padrao privado impede a construcao do objeto sem parametros.
	}

	/**
	 * Aceita um pedido de amizade, caso este esteja entre as solicitacoes
	 * 
	 * @param outro
	 *            Usuario que deseja adicionar aos amigos
	 * @return False caso a solicitacao de amizade deste usuario nao exista
	 */
	public boolean aceitarAmizade(Usuario outro) {
		if (solicitacoes.contains(outro)) {
			solicitacoes.remove(outro);
			amigos.add(outro);
			outro.amigos.add(this);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Rejeita um pedido de amizade, caso este esteja entre as solicitacoes
	 * 
	 * @param outro
	 *            Usuario que voce excluir de suas solicitacoes
	 * @return False caso o outro nao esteja entre suas solicitacoes
	 */
	public boolean rejeitarAmizade(Usuario outro) {
		if (solicitacoes.contains(outro)) {
			solicitacoes.remove(outro);
			return true;
		} else {
			return false;
		}
	}

	public boolean temAmigo(Usuario outro) {
		return amigos.contains(outro);
	}
	
	public boolean temSolicitacao(Usuario outro) {
		return solicitacoes.contains(outro);
	}
	
	public int getNumAmigos() {
		return amigos.size();
	}
	
	public int getNumSolicitacoes() {
		return solicitacoes.size();
	}
	
	public int getId() {
		return id;
	}

	@Override
	public String getNome() {
		return nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String getLogin() {
		return login;
	}

	@Override
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Override
	public String getEndereco() {
		return endereco;
	}

	@Override
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario outro = (Usuario) obj;
			return ((getId() == outro.getId()) && getLogin().equals(
					outro.getLogin()));
		}
		return false;
	}

}
