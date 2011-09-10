package sistema.usuario;

import java.util.ArrayList;
import java.util.List;

import sistema.item.Item;
import sistema.item.ItemIF;

/**
 * Esta classe representa um usuario padrao do sistema.
 * 
 * @author Joeffison Silverio de Andrade, 21011853
 * @author José Nathaniel L. de Abrante, 21011091
 * @author José Ulisses de Brito Lira Filho, 20911806
 * @version 1.2.3
 */
public class Usuario implements UsuarioIF {
	/* Atributos estaticos. */
	private static int ID_Prox_Usuario = 1; // ID do proximo usuario sera
											// guardado nesta variavel estatica.

	/* Atributos do objeto. */
	private String login, nome, endereco;

	private final int id = ID_Prox_Usuario++; // id (codigo unico) do usuario

	private List<UsuarioIF> amigos; // Grupo de amigos
	private List<UsuarioIF> solicitacoes; // solicitacoes de amizade
	private List<ItemIF> itens; // itens do usuario
	private List<ItemIF> itens_emprestados; // lista de itens que o usuario
											// emprestou e ainda nao recebeu

	/**
	 * Construtor padrao eh privado e nao oferece implementacao.
	 */
	private Usuario() {
	}

	/**
	 * Constroi um usuario a partir de um login, nome e endereco.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param nome
	 *            O nome do usuario.
	 * @param endereco
	 *            O endereco do usuario.
	 */
	public Usuario(String login, String nome, String endereco) throws Exception {
		/*
		 * Se login invalido segundo as especificacoes, lancar excecao com
		 * mensagem padronizada nos requisitos. Consultar testes de aceitacao.
		 */
		/*
		 * Se nome invalido segundo as especificacoes, lancar excecao com
		 * mensagem padronizada nos requisitos. Consultar testes de aceitacao.
		 */
		/*
		 * Se endereco invalido segundo as especificacoes, lancar excecao com
		 * mensagem padronizada nos requisitos. Consultar testes de aceitacao.
		 */

		// Estes métodos podem lançar exceção
		setLogin(login);
		setNome(nome);
		setEndereco(endereco);

		itens = new ArrayList<ItemIF>();
	}

	@Override
	public void setLogin(String login) { // TODO: lançar uma exceção aqui!
		this.login = login;
	}

	@Override
	public void setNome(String nome) { // TODO: lançar uma exceção aqui!
		this.nome = nome;
	}

	@Override
	public void setEndereco(String endereco) { // TODO: lançar uma exceção aqui!
		this.endereco = endereco;
	}

	@Override
	public String getLogin() {
		return this.login;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getEndereco() {
		return this.endereco;
	}

	@Override
	public String cadastrarItem(String nome, String descricao, String categoria)
			throws Exception {

		System.out.println("lista de itens eh null: "+(itens == null));
		System.out.println("meu login:" +getLogin());
		System.out.println("nome:"+nome);
		System.out.println("descricao:"+descricao);
		System.out.println("categoria:"+categoria);
		System.out.println("itens size:"+itens.size());
		System.out.println();
		ItemIF item = new Item(getLogin() + (itens.size() + 1), nome, //FIXME HAVERÁ itens com o mesmo id, quando o user puder apagar seus itens
				descricao, categoria); // FIXME logica do IdItem ainda nao
										// implementada
		System.out.println("cheguei aqui");
		itens.add(item);
		System.out.println("OPS");
		System.out.println("eh null e algum momento" + item == null);
		return item.getId();

	}

	@Override
	public boolean removerItem(String idItem) {
		for (ItemIF item : itens) {
			if (idItem.equals(item.getId())) {
				itens.remove(item);
				return true;
			}
		}
		return false;
	}

	@Override
	public String getListaIdItens() {
		StringBuilder listaIdItensString = new StringBuilder();

		// FIXME: confirmar formato da string. Nathaniel concorda!
		for (ItemIF item : this.itens) {
			listaIdItensString.append(item.getId() + " ");
		}

		return listaIdItensString.toString().trim();
	}

	@Override
	public ItemIF getInformacoesItem(String idItem) {
		// FIXME Não entendi a necessidade<->nome do metodo, Joeffison.
		for (ItemIF item : this.itens) {
			if (item.getId() == idItem) {
				return item;
			}
		}

		return null;
	}

	@Override
	// FIXME Quantidade de itens totais, disponiveis ou emprestados? Ass.
	// Joeffison
	public int qntItens() {
		return this.itens.size();
	}

	@Override
	public int qntItensEmprestados() {
		return this.itens_emprestados.size();
	}

	@Override
	public String getListaIdItensEmprestados() {
		StringBuilder listaIdItensEmprestadosString = new StringBuilder();

		// FIXME: confirmar formato da string. Nathaniel concorda!
		for (ItemIF itensEmprestados : this.itens_emprestados) {
			listaIdItensEmprestadosString
					.append(itensEmprestados.getId() + " ");
		}

		return listaIdItensEmprestadosString.toString().trim();
	}

	@Override
	public int qntItensBeneficiados() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getListaIdItensBeneficiados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemIF getInformacoesItemBeneficiado(String idItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean estahItemDisponivel(String idItem) {
		try {
			return getInformacoesItem(idItem).estahDisponivel();

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean equals(UsuarioIF outroUsuario) { // FIXME: Este método
													// deveria testar apenas o
													// login, por que facilitria
													// outras coisas adiante

		return (this.getLogin() == outroUsuario.getLogin()
				&& this.getNome() == outroUsuario.getNome() && this
					.getEndereco() == outroUsuario.getEndereco());
	}
	// TODO: fazer um método hashCode() que retorna o ID... pra facilitar o uso
	// das listas e mapas... [quero opiniões]

}
