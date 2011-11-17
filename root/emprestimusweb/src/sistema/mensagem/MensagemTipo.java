package sistema.mensagem;

import static sistema.utilitarios.Validador.assertStringNaoVazia;
import sistema.utilitarios.Mensagem;

public enum MensagemTipo {

	OFF_TOPIC("offtopic"),
	NEGOCIACAO("negociacao");

	private final String nome;

	private MensagemTipo(String nome) {
		this.nome = nome;
	}

	/**
	 * A partir do nome da categoria, este metodo retorna o enum indicado.
	 * 
	 * @param categoria - String
	 * 		Nome da categoria que deve ser retornada.
	 * 
	 * @return MensagemTipo
	 * 		Categoria indicada pela string passada.
	 * 
	 * @throws Exception
	 * 		Caso a string, passada como parametro, nao represente nenhum
	 *			dos enums, lanca excecao.
	 */
	public static MensagemTipo getCategoria(String categoria) throws Exception {
		assertStringNaoVazia(categoria, Mensagem.CATEGORIA_INVALIDA.getMensagem(),
				Mensagem.CATEGORIA_INVALIDA.getMensagem());

		categoria.toUpperCase();
		if (categoria.equals("offtopic")) {
			return MensagemTipo.OFF_TOPIC;
		} else if (categoria.equals("negociacao")) {
			return MensagemTipo.NEGOCIACAO;
		} else {
			throw new Exception("MensagemTipo falta mensagem");
		}

	}

	/**
	 * retorna o nome do tipo mensagem
	 * 
	 * @return String
	 */
	public String getNome() {
		return this.nome;
	}

	@Override
	public String toString() {
		return getNome();
	}

}
