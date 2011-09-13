package sistema.usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sistema.item.Item;
import sistema.item.ItemIF;
import sistema.persistencia.ItemRepositorio;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;
import sistema.utilitarios.ValidadorString;

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
		
		// Estes métodos podem lançar exceção
		setLogin(login);
		setNome(nome);
		setEndereco(endereco);

		itens = new ArrayList<ItemIF>();
	}

	@Override
	public void setLogin(String login) throws IllegalArgumentException {
		Validador.testaNaoNulo(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.testaStringVazia(login.trim(), Mensagem.LOGIN_INVALIDO.getMensagem());
		this.login = login.trim();
	}

	@Override
	public void setNome(String nome) throws IllegalArgumentException {
		Validador.testaNaoNulo(nome, Mensagem.NOME_INVALIDO.getMensagem());
		Validador.testaStringVazia(nome.trim(), Mensagem.NOME_INVALIDO.getMensagem());
		this.nome = nome.trim();
	}

	@Override
	public void setEndereco(String endereco) {
		if(endereco == null){
			this.endereco = "";
		}else{
			this.endereco = endereco.trim();
		}
		
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
		ItemIF item = new Item(nome, descricao, categoria);
		ItemRepositorio.cadastrarItem(item);
		itens.add(item);//o item eh modificdo pelo repositorio possuindo agora um id valido
		return String.valueOf((Long.valueOf(ItemRepositorio.geraIdProxItem()) - 1)) ;

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
		Collections.sort(this.itens);
		for (ItemIF item : this.itens) {
			listaIdItensString.append(item.getId() + " ");
		}

		return listaIdItensString.toString().trim();
	}
	
	/**
	 * Recupera a lista detodos os itens do usuario.
	 * 
	 * @return
	 * 	Lista de itens.
	 */
	@Override
	public List<ItemIF> getItens() {
		return this.itens;
	}
	
	@Override
	public ItemIF getItem(String idItem) {
		for (ItemIF item : this.itens) {
			if (item.getId().equals(idItem)) {
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
			return getItem(idItem).estahDisponivel();

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean equals(Object outroUsuario) { 
		if(outroUsuario instanceof UsuarioIF ){
			return this.getLogin().equals(((UsuarioIF) outroUsuario).getLogin());
		}
		return false;
	}

	@Override
	public boolean existeItemID(String idItem) {
		try {
			ItemIF item = new Item("placebo", "placebo", "FILME");
			item.setId(idItem);
			return itens.contains(item);
		} catch (Exception e) {}//nao lanca excecao.
		return false;
		
	}


}
