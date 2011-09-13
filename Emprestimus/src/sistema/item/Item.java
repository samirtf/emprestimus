package sistema.item;

import sistema.persistencia.ItemRepositorio;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;
import sistema.utilitarios.ValidadorString;

/**
 * Esta classe representa os itens que podem ser cadastrados pelo usuario
 * 
 * @author Joeffison Silverio de Andrade, 21011853, joeffisonsa@gmail.com
 * @version 1.5.2
 * @since 1.0
 */
public class Item implements ItemIF{
	
	private String idItem, nome, descricao;
	private ItemCategoria categoria;
	private boolean estaDisponivel;
	
	/**
	 * O construtor padrao eh privado e nao oferece implementacao.
	 */
	private Item(){}
	
	public Item( String nome, String descricao, String categoria) throws Exception{
		setNome(nome);
		setDescricao(descricao);
		setCategoria(categoria);
		this.estaDisponivel = true;
	}
	
    public Item( String nome, String descricao, ItemCategoria categoria) throws Exception{
    	this.idItem = idItem;
    	
    	setNome(nome);
    	setDescricao(descricao);
		setCategoria(categoria);
		
		this.estaDisponivel = true;
	}
    
    
	@Override
	public String getId() {
		return this.idItem;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getCategoria() {
		return this.categoria.getNome();
	}

	@Override
	public ItemCategoria getCategoriaType() {
		return this.categoria;
	}

	@Override
	public String getDescricao() {
		return this.descricao;
	}
	
	@Override
	public void setId(String id) throws Exception {
		this.idItem = id;
	}

	@Override
	public void setNome(String nome) throws Exception {
		Validador.testaNaoNulo(nome, Mensagem.NOME_INVALIDO.getMensagem());
		Validador.testaStringVazia(nome.trim(), Mensagem.NOME_INVALIDO.getMensagem());
		this.nome = nome.trim();
	}
	
	@Override
	public void setCategoria(ItemCategoria categoria) throws Exception {
		if (categoria == null) {
			throw new Exception(Mensagem.CATEGORIA_INVALIDA.getMensagem());
		}
		this.categoria = categoria;
		//TODO Aprimorar tratamento do tipo de exceção.
	}
	
	@Override
	public void setCategoria(String categoria) throws Exception {
		Validador.testaNaoNulo(categoria, Mensagem.CATEGORIA_INVALIDA.getMensagem());
		Validador.testaStringVazia(categoria.trim(), Mensagem.CATEGORIA_INVALIDA.getMensagem());
		
		if(categoria.trim().equalsIgnoreCase("FILME")){
			this.categoria = ItemCategoria.FILME;
		}else if(categoria.trim().equalsIgnoreCase("JOGO")){
			this.categoria = ItemCategoria.JOGO;
		}else if(categoria.trim().equalsIgnoreCase("LIVRO")){
			this.categoria = ItemCategoria.LIVRO;
		}else{
			throw new IllegalArgumentException(Mensagem.CATEGORIA_INEXISTENTE.getMensagem());
		}
		
	}

	@Override
	public void setDescricao(String descricao) throws Exception {
		if(descricao == null){
			this.descricao = "";
		}else{
			this.descricao = descricao.trim();
		}
	}

	@Override
	public boolean estahDisponivel() {
		return this.estaDisponivel;
	}
	
	@Override
	public void setDisponibilidade(boolean disponibilidade) {
		this.estaDisponivel = disponibilidade;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item) {
			Item outro = (Item) obj;
			return this.idItem.equals(outro.getId());
		}
		return false;
	}

	@Override
	public int compareTo(Object obj) {
		if(!(obj instanceof Item)) throw new IllegalArgumentException("Nao é uma instancia de Item");
		Item item = (Item) obj;
		if(Long.valueOf(this.getId()) < Long.valueOf(item.getId())){
			return -1;
		}else if(Long.valueOf(this.getId()) == Long.valueOf(item.getId())){
			return 0;
		}
		return 1;
				//this.getId().compareTo(item.getId());
	}

	
}
