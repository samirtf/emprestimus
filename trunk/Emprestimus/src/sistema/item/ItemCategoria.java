package sistema.item;

import sistema.utilitarios.Mensagem;
import sistema.utilitarios.ValidadorString;

/**
 * Este enum serve para caracterizar itens segundo a sua categoria.
 * 
 * @author Joeffison Silverio de Andrade, 21011853, joeffisonsa@gmail.com
 * @version 1.0
 * @since 1.0
 */
public enum ItemCategoria {
	LIVRO("Livro"), FILME("Filme"), JOGO("Jogo");
	
	private final String nome;
	
	private ItemCategoria(String nome) {
		this.nome = nome;
	}
	
	/**
	 * A partir do nome da categoria, este metodo retorna o enum indicado.
	 * 
	 * @param categoria
	 * 		Nome da categoria que deve ser retornada.
	 * @return
	 * 		Categoria indicada pela string passada.
	 * @throws Exception
	 * 		Caso a string, passada como parametro, nao represente nenhum dos enums, lanca excecao. 
	 */
	public static ItemCategoria getCategoria(String categoria) throws Exception {
		if (!ValidadorString.validaString(categoria).equals(Mensagem.OK.getMensagem())) {
			throw new Exception(Mensagem.CATEGORIA_INVALIDA.getMensagem());
		}
		
		categoria.toUpperCase();
		if (categoria.equals("LIVRO")) {
			return ItemCategoria.LIVRO;
		} else if (categoria.equals("FILME")) {
			return ItemCategoria.FILME;
		} else if (categoria.equals("JOGO")) {
			return ItemCategoria.JOGO;
		} else {
			throw new Exception(Mensagem.CATEGORIA_INEXISTENTE.getMensagem());
		}
		
	}

	public String getNome() {
		return this.nome;
	}
	
}
