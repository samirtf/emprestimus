package sistema.item;

import static sistema.utilitarios.Validador.assertNaoNulo;
import static sistema.utilitarios.Validador.assertStringNaoVazia;
import sistema.excecoes.ArgumentoInvalidoException;
import sistema.utilitarios.Mensagem;

/**
 * Esta classe representa os itens que podem ser cadastrados pelo usuario
 * 
 * @author Joeffison Silverio de Andrade, 21011853, joeffisonsa@gmail.com
 * @version 1.5.2
 * @since 1.0
 */
public class Item implements ItemIF {

	private String idItem, nome, descricao;
	private ItemCategoria categoria;
	private boolean estaDisponivel;

	/**
	 * O construtor padrao eh privado e nao oferece implementacao.
	 */
	private Item() {
	}

	public Item(String nome, String descricao, String categoria)
			throws Exception {
		setNome(nome);
		setDescricao(descricao);
		setCategoria(categoria);
		this.estaDisponivel = true;
	}

	public Item(String nome, String descricao, ItemCategoria categoria)
			throws Exception {
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
	public ItemIF setId(String id) throws Exception {
		assertNaoNulo(id, "ID do item não pode ser nula");
		assertStringNaoVazia(id, "ID do item não pode ser vazia");
		this.idItem = id;
		return this;
	}

	@Override
	public void setNome(String nome) throws Exception {
		assertNaoNulo(nome, Mensagem.NOME_INVALIDO.getMensagem());
		assertStringNaoVazia(nome, Mensagem.NOME_INVALIDO.getMensagem());
		this.nome = nome.trim();
	}

	@Override
	public void setCategoria(ItemCategoria categoria) throws Exception {
		assertNaoNulo(categoria, Mensagem.CATEGORIA_INVALIDA.getMensagem());

		this.categoria = categoria;
		// TODO Aprimorar tratamento do tipo de exceção.
	}

	@Override
	public void setCategoria(String categoria) throws Exception {
		assertNaoNulo(categoria, Mensagem.CATEGORIA_INVALIDA.getMensagem());
		assertStringNaoVazia(categoria,
				Mensagem.CATEGORIA_INVALIDA.getMensagem());

		if (categoria.trim().equalsIgnoreCase("FILME")) {
			this.categoria = ItemCategoria.FILME;
		} else if (categoria.trim().equalsIgnoreCase("JOGO")) {
			this.categoria = ItemCategoria.JOGO;
		} else if (categoria.trim().equalsIgnoreCase("LIVRO")) {
			this.categoria = ItemCategoria.LIVRO;
		} else {
			throw new ArgumentoInvalidoException(
					Mensagem.CATEGORIA_INEXISTENTE.getMensagem());
		}

	}

	@Override
	public void setDescricao(String descricao) throws Exception {
		if (descricao == null) {
			this.descricao = "";
		} else {
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
	public int compareTo(ItemIF item) {
		return (int) (Long.valueOf(this.getId()) - Long.valueOf(item.getId()));
	}

}
