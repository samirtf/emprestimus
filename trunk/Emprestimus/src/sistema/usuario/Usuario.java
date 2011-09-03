package sistema.usuario;

import java.util.ArrayList;
import java.util.List;

import sistema.item.Item;
import sistema.item.ItemIF;

/**
 * Esta classe representa um usuario padrao do sistema.
 * 
 * @author Jos� Nathaniel L. de Abrante, 21011091
 * @author Joeffison Silverio de Andrade, 21011853
 * @author José Ulisses de Brito Lira Filho, 20911806
 * @version 1.2.3
 */
public class Usuario implements UsuarioIF {
	/* Atributos estaticos. */
	private static int ID_Prox_Usuario = 1; // ID do proximo usuario sera guardado nesta variavel estatica. 
	
	/* Atributos do objeto. */
	private String login, nome, endereco;
	
	private final int id = ID_Prox_Usuario++; // id (codigo unico) do usuario
	
	private List<UsuarioIF> amigos; // Grupo de amigos
	private List<UsuarioIF> solicitacoes; // solicitacoes de amizade
	private List<ItemIF> itens; //itens do usuario
	private List<ItemIF> itens_emprestados; // lista de itens que o usuario emprestou e ainda nao recebeu
	
	/**
	 * Construtor padrao eh privado e nao oferece implementacao.
	 */
	private Usuario(){}
	
	/**
	 * Constroi um usuario a partir de um login, nome e endereco.
	 * 
	 * @param login
	 * 		O login do usuario.
	 * @param nome
	 * 		O nome do usuario.
	 * @param endereco
	 * 		O endereco do usuario.
	 */
	public Usuario( String login , String nome, String endereco ) throws Exception{
		/* Se login invalido segundo as especificacoes, lancar excecao com mensagem
		 * padronizada nos requisitos. Consultar testes de aceitacao.
		 */
		/* Se nome invalido segundo as especificacoes, lancar excecao com mensagem
		 * padronizada nos requisitos. Consultar testes de aceitacao.
		 */
		/* Se endereco invalido segundo as especificacoes, lancar excecao com mensagem
		 * padronizada nos requisitos. Consultar testes de aceitacao.
		 */
		
		this.login = login;
		this.nome = nome;
		this.endereco = endereco;
		
		itens = new ArrayList<ItemIF>();
	}

	@Override
	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public void setEndereco(String endereco) {
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
	public String cadastrarItem(String nome, String descricao, String categoria) {
		try {
			ItemIF item = new Item(getLogin(), nome, descricao, categoria); //FIXME logica do IdItem ainda nao implementada
			itens.add(item);
			return item.getIdItem();
		} catch (Exception e) {
			return null; //TODO: lançar uma exceção aqui!
		}
	}

	@Override
	public boolean removerItem(String idItem) {
		for (ItemIF item : itens) {
			if (idItem.equals(item.getIdItem())) {
				itens.remove(item);
				return true;
			}
		}
		return false;
	}

	@Override
	public String getListaIdItens() {
		String listaIdItensString = "";
		
		//TODO: confirmar formato da string.
		for(ItemIF item: this.itens){
			listaIdItensString = listaIdItensString+item.getIdItem()+" ";
		}
		
		return listaIdItensString.trim();
	}

	@Override
	public ItemIF getInformacoesItem(String idItem) {
		
		for(ItemIF item: this.itens){
			if(item.getIdItem() == idItem){
				return item;
			}
		}
		
		return null;
	}

	@Override
	public int qntItens() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int qntItensEmprestados() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getListaIdItensEmprestados() {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equals(UsuarioIF outroUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

}
