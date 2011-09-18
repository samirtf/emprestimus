package sistema.usuario;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import sistema.autenticacao.Autenticacao;
import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.Item;
import sistema.item.ItemIF;
import sistema.persistencia.ItemRepositorio;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;
import static sistema.utilitarios.Validador.*;

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
	private List<UsuarioIF> queremSerMeusAmigos; // solicitacoes de amizade
	private List<UsuarioIF> queroSerAmigoDeles; // solicitacoes de amizade
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
		amigos = new ArrayList<UsuarioIF>();
		queremSerMeusAmigos = new ArrayList<UsuarioIF>();
		queroSerAmigoDeles = new ArrayList<UsuarioIF>();
	}

	@Override
	public void setLogin(String login) throws Exception {
		assertNaoNulo(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		this.login = login.trim();
	}

	@Override
	public void setNome(String nome) throws Exception {
		assertNaoNulo(nome, Mensagem.NOME_INVALIDO.getMensagem());
		assertStringNaoVazia(nome, Mensagem.NOME_INVALIDO.getMensagem());
		this.nome = nome.trim();
	}

	@Override
	public void setEndereco(String endereco) {
		if (endereco == null) {
			this.endereco = "";
		} else {
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
		itens.add(item);// o item eh modificdo pelo repositorio possuindo agora
						// um id valido
		return String
				.valueOf((Long.valueOf(ItemRepositorio.geraIdProxItem()) - 1));

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
	 * @return Lista de itens.
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
	
	public void aprovarAmizade( String login ){
		for( UsuarioIF u : queremSerMeusAmigos ){
			if(u.getLogin().trim().equalsIgnoreCase(login.trim())){
				u.aprovouAmizade(this);
			}
			amigos.add(u);
			queremSerMeusAmigos.remove(u);
		}
	}
	
	public void aprovouAmizade( UsuarioIF usuario ){
		if(queroSerAmigoDeles.contains(usuario)){
			queroSerAmigoDeles.remove(usuario);
			amigos.add(usuario);
		}
		
	}
	
	@Override
	public List<UsuarioIF> getQueremSerMeusAmigos(){
		return this.queremSerMeusAmigos;
	}
	
	@Override
	public List<UsuarioIF> getQueroSerAmigoDe(){
		return this.queroSerAmigoDeles;
	}
	
	public boolean ehAmigo( String login ) throws ArgumentoInvalidoException{
		Validador.assertNaoNulo(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(login.trim(), Mensagem.LOGIN_INVALIDO.getMensagem());
		for( UsuarioIF u : amigos ){
			if ( u.getLogin().trim().equalsIgnoreCase(login.trim()) ) 
			return true;
		}
		return false;
	}
	
	public void requisitarAmizade( String login ) throws ArgumentoInvalidoException{
		for( UsuarioIF u : getQueroSerAmigoDe() ){
			if(u.getLogin().trim().equalsIgnoreCase(login.trim())) return;
		}
		UsuarioIF futuroAmigo = Autenticacao.getUsuarioPorLogin(login);
		if( Autenticacao.existeUsuario(login.trim()) ){
			queroSerAmigoDeles.add(futuroAmigo);
			futuroAmigo.usuarioQuerSerMeuAmigo(this);
		}
	}
	
	public void usuarioQuerSerMeuAmigo( UsuarioIF usuario ){
		for( UsuarioIF u : getQueremSerMeusAmigos() ){
			if(u.equals(usuario)) return;
		}
		queremSerMeusAmigos.add(usuario);
	}

	@Override
	public boolean equals(Object outroUsuario) {
		if (outroUsuario instanceof UsuarioIF) {
			return this.getLogin()
					.equals(((UsuarioIF) outroUsuario).getLogin());
		}
		return false;
	}

	@Override
	public boolean existeItemID(String idItem) {
		try {
			return itens.contains(new Item("placebo", "placebo", "FILME").setId(idItem));
		} catch (Exception e) {
		}// nao lanca excecao.
		return false;

	}


}
