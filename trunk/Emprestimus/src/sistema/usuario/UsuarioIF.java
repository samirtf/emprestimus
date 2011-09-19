package sistema.usuario;

import java.util.List;

import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.ItemIF;

/**
 * Representa um usuario do sistema. Esta interface assegura que os metodos
 * exigidos serao implementados. A instanciacao de classes que implementam esta
 * interface deve ser feita com upcast para a interface.
 * 
 * @author Joeffison Silverio de Andrade, 21011853
 * @author José Ulisses de Brito Lira Filho, 20911806
 * @version 1.0.2.1
 * @version 1.2
 */
public interface UsuarioIF {

	/**
	 * Configura o login do usuario.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @throws Exception
	 */
	public void setLogin(String login) throws Exception;

	/**
	 * Configura o nome do usuario.
	 * 
	 * @param nome
	 *            O nome do usuario.
	 * @throws ArgumentoInvalidoException
	 * @throws Exception
	 */
	public void setNome(String nome) throws ArgumentoInvalidoException,
			Exception;

	/**
	 * COnfigura o endereco do usuario.
	 * 
	 * @param endereco
	 */
	public void setEndereco(String endereco);

	/**
	 * Recupera o nome do usuario.
	 * 
	 * @return O nome do usuario.
	 */
	public String getLogin();

	/**
	 * Recupera o nome do usuario.
	 * 
	 * @return O nome do usuario.
	 */
	public String getNome();

	/**
	 * Recupera o endereco do usuario.
	 * 
	 * @return O endereco do usuario.
	 */
	public String getEndereco();

	/**
	 * Cadastra um item na lista de itens do usuario. Eh retornado o idItem do
	 * item cadastrado. O idItem tem o seguinte formato:
	 * [loginUsuario]_[inteiro]. Por exemplo, samir_1, para o item primeiro;
	 * samir_2.
	 * 
	 * @param nome
	 *            O nome do item.
	 * @param descricao
	 *            A descricao do item.
	 * @param categoria
	 *            A categoria do item.
	 * @return O idItem (String) no formato login_inteiro.
	 */
	public String cadastrarItem(String nome, String descricao, String categoria)
			throws Exception;

	/**
	 * Remove item da lista de itens do usuario. A remocao eh feita apenas pelo
	 * idItem.
	 * 
	 * @param idItem
	 *            IdItem do item a ser removido.
	 * @return True - Se o item for removido. False - Se o item a ser removido
	 *         nao existir.
	 */
	public boolean removerItem(String idItem);

	/**
	 * Recupera a lista ordenada de idItens de itens cadastrados.
	 * 
	 * @return Uma lista de idItens ordenada. Formatacao: "7 17 18 20 21"
	 *         Retorna-se uma string vazia caso nao haja elementos.
	 */
	public String getListaIdItens();
	
	public String getListaItens() throws Exception;

	/**
	 * Recupera o ItemIF a partir do idItem.
	 * 
	 * @param idItem
	 *            O idItem do item a ser procurado.
	 * @return A instancia de ItemIF. null - Caso o item nao exista.
	 */
	// TODO: o que acontece se o item não for encontrado?
	// ADICIONAR aqui na interface para que eu possa implementar
	public ItemIF getItem(String idItem);

	/**
	 * Verifica se o usuario possui o item com id especifico cadastrado.
	 * 
	 * @param idItem
	 *            O id do item a ser procurado.
	 * @return True - Se o item existir. False - Caso nao exista.
	 */
	public boolean existeItemID(String idItem);

	/**
	 * Calcula a quantidade total de itens cadastrados.
	 * 
	 * @return int: quantidade de itens cadastrados.
	 */
	public int qntItens();

	/**
	 * Calcula a quantidade de itens atualmente concedidos em emprestimo, ou
	 * seja, a quantidade de livros nao disponiveis para emprestivo.
	 * 
	 * @return int: quantidade de itens atualmente emprestados.
	 */
	public int qntItensEmprestados();

	/**
	 * Recupera a lista ordenada de idItens de itens emprestados (concedidos a
	 * alguém).
	 * 
	 * @return Uma lista ordenada de idItens emprestados.
	 */
	public String getListaIdItensEmprestados();

	/**
	 * Calcula a quantidade de itens atualmente beneficiados em emprestimo, ou
	 * seja, a quantidade de itens alheios que estao em minha posse..
	 * 
	 * @return Um valor inteiro da quantidade de itens atualmente pegos
	 *         emprestado.
	 */
	public int qntItensBeneficiados();

	/**
	 * Recupera a lista ordenada de idItens de itens beneficiados.
	 * 
	 * @return Uma lista ordenada de idItens beneficiados.
	 */
	public String getListaIdItensBeneficiados();

	/**
	 * Recupera uma COPIA do Item a partir do idItem. A COPIA do item garante
	 * que o original nao podera ser alterado.
	 * 
	 * @param idItem
	 *            O idItem do item a ser procurado.
	 * @return
	 */
	public ItemIF getInformacoesItemBeneficiado(String idItem);

	/**
	 * Verifica se o item pessoal estah disponivel a partir de um idItem.
	 * 
	 * @param idItem
	 *            O idItem
	 * @return True - Se o item nao estiver emprestado. False - Se o item nao
	 *         existir ou se estiver emprestado.
	 */
	public boolean estahItemDisponivel(String idItem);
	
	public void usuarioQuerSerMeuAmigo( UsuarioIF usuario );
	
	public void requisitarAmizade( String login ) throws Exception;
	
	public boolean ehAmigo( String login ) throws ArgumentoInvalidoException;
	
	public boolean amizadeDeFoiRequisitada( String login ) throws ArgumentoInvalidoException;
	
	public List<UsuarioIF> getQueremSerMeusAmigos();
	
	public List<UsuarioIF> getQueroSerAmigoDe();

	/**
	 * Verifica se dois Usuarios sao iguais. A verificacao eh feita a nivel de
	 * interface. A comparacao eh realizada comparando apenas logins.
	 * 
	 * @param usuarioIF
	 *            Uma implementacao de UsuarioIF sob o aspecto de interface
	 *            UsuarioIF.
	 * 
	 * @return True - Se os objetos forem iguais. False - Se forem objetos de
	 *         instancias diferentes ou nao forem iguais.
	 */
	public boolean equals(Object outroUsuario);

	public List<ItemIF> getItens();
	
	public String getAmigos() throws Exception;

	public void aprovarAmizade(String login) throws Exception;

	public void aprovouAmizade(UsuarioIF usuario);
	
    public boolean oItemMePertence( String idItem ) throws Exception;
	
	public boolean ehItemDoMeuAmigo( String idItem ) throws Exception;
	
	public void requisitarEmprestimo( String idItem ) throws Exception;
	

}
