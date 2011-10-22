package sistema.item;

import static sistema.utilitarios.Validador.assertNaoNulo;
import static sistema.utilitarios.Validador.assertStringNaoVazia;
import static sistema.utilitarios.Validador.asserteTrue;
import sistema.utilitarios.Mensagem;


/**
 * Este enum serve para caracterizar itens segundo a sua categoria.
 * 
 * @deprecated Classe a ser deletada.
 * 
 * @author Joeffison Silverio de Andrade, 21011853
 * @author José Ulisses de Brito Lira Filho, 20911806
 * @version 1.0
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
	 * @deprecated
	 * @param categoria
	 * 		Nome da categoria que deve ser retornada.
	 * @return
	 * 		Categoria indicada pela string passada.
	 * @throws Exception
	 * 		Caso a string, passada como parametro, nao represente nenhum dos enums, lanca excecao. 
	 */
	public static ItemCategoria getCategoria(String categoria) throws Exception {
		assertStringNaoVazia(categoria, Mensagem.CATEGORIA_INVALIDA.getMensagem(), Mensagem.CATEGORIA_INVALIDA.getMensagem());
		
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

	/**
	 * @deprecated
	 * @return
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * @deprecated
	 */
	@Override
	public String toString() {
		return getNome();
	}
	
}
