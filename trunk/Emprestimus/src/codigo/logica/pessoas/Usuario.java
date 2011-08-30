package codigo.logica.pessoas;

import java.util.LinkedList;
import java.util.List;

import codigo.logica.emprestimos.Emprestimo;
import codigo.logica.itens.ItemIF;

/**
 * Esta classe representa um usuario padrao do sistema 
 * 
 * @author Joeffison Silverio de Andrade, joeffisonsa@gmail.com
 * @author Nathaniel
 * @since 1.0
 * @version 2.0
 */
public class Usuario implements PessoaIF, Comparable<Usuario> {
	public static int ID_Prox_Usuario = 1; // ID do proximo usuario sera guardado nesta variavel estatica. 
	private String nome;
	private String login;
	private String endereco;
	
	private final int id; // id (codigo unico) do usuario
	private List<Usuario> amigos; // Grupo de amigos
	private List<Usuario> solicitacoes; // solicitacoes de amizade
	private List<ItemIF> itens; //itens do usuario
	private List<Emprestimo> emprestimos; // lista de emprestimos do usuario
	private List<ItemIF> itens_emprestados; // lista de itens que o usuario emprestou e ainda nao recebeu
	
	private Usuario() {
		id = 0;
		// Construtor padrao privado impede a construcao do objeto sem parametros.
	}
	
	/**
	 * @param nome
	 * @param login
	 * @param endereco
	 * @param id
	 */
	public Usuario(String nome, String login, String endereco) throws IllegalArgumentException {
		if(nome == null || nome.trim().equals("")) throw new IllegalArgumentException("Nome null ou vazio");
		if(login == null || login.trim().equals("")) throw new IllegalArgumentException("Login null ou vazio");
		if(endereco == null || endereco.trim().equals("")) throw new IllegalArgumentException("Endereco null ou vazio");
		setNome(nome);
		setLogin(login);
		setEndereco(endereco);
		
		this.id = ID_Prox_Usuario++; // ID comeca em zero
		this.amigos = new LinkedList<Usuario>(); // inicializando a lista de amigos
		this.solicitacoes = new LinkedList<Usuario>(); // inicializando a lista de solicitacoes de amizade
		this.itens = new LinkedList<ItemIF>(); // inicializando a lista de itens
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
			throw new Exception("Usuario jah existe nos amigos"); // TODO implementar excecao expecifica
		if (solicitacoes.contains(outro)) { // TODO A logica nao esta errada aqui? Pra isso serve o TDD
			return aceitarAmizade(outro);
		} else if (outro.solicitacoes.contains(this)) { // TODO Isto quebra, solicitacoes eh private
			return false;
		} else {
			outro.solicitacoes.add(this);
			return true;
		}
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
			outro.amigos.add(this);// TODO
			return true;
		}
		return false;
	}

	/**
	 * Rejeita um pedido de amizade, caso este esteja entre as solicitacoes
	 * 
	 * @param outro
	 *            Usuario que voce excluir de suas solicitacoes
	 * @return False caso o outro nao esteja entre suas solicitacoes
	 */
	public boolean rejeitarAmizade(Usuario outro) { // TODO
		if (solicitacoes.contains(outro)) {
			solicitacoes.remove(outro);
			return true;
		} else {
			return false;
		}
	}

	public boolean temAmigo(Usuario outro) { // TODO poderia estar em uso
		return this.amigos.contains(outro);
	}
	
	public boolean temSolicitacao(Usuario outro) { // TODO poderia estar em uso
		return this.solicitacoes.contains(outro);
	}
	
	public int getNumAmigos() {
		return this.amigos.size();
	}
	
	public int getNumSolicitacoes() {
		return this.solicitacoes.size();
	}
	
	public int getId() {
		return this.id;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String getLogin() {
		return this.login;
	}

	@Override
	public void setLogin(String login) {
		this.login = login;
	}
	
	@Override
	public String getEndereco() {
		return this.endereco;
	}

	@Override
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	@Override
	public int hashCode() {
		return this.getId();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario outro = (Usuario) obj;
			return this.login.equals(outro.getLogin());
			
		}
		return false;
	}

	public boolean receberItemQueEmprestou(Usuario devedor, ItemIF item) {
		for (ItemIF meuItem : this.itens_emprestados) {
			if (meuItem.equals(item)) {
				return this.itens_emprestados.remove(item) && this.itens.add(item);
			}
		}
		return false;
	}

	@Override
	public int compareTo(Usuario outro) {
		return getId() - outro.getId();
	}
	
	public static void resetIDClasse(){
		ID_Prox_Usuario = 0;
	}

}



