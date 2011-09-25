package sistema.mensagem;

import static sistema.utilitarios.Validador.assertNaoNulo;
import static sistema.utilitarios.Validador.assertStringNaoVazia;
import static sistema.utilitarios.Validador.asserteTrue;
import sistema.emprestimo.EmprestimoEstado;
import sistema.utilitarios.Mensagem;

public enum MensagemTipo {
	
	OFF_TOPIC("offtopic"), NEGOCIACAO("negociacao");
	
private final String nome;
	
	private MensagemTipo(String nome) {
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
	public static MensagemTipo getCategoria(String categoria) throws Exception {
		assertNaoNulo(categoria, Mensagem.CATEGORIA_INVALIDA.getMensagem());
		assertStringNaoVazia(categoria, Mensagem.CATEGORIA_INVALIDA.getMensagem());
		
		categoria.toUpperCase();
		if (categoria.equals("offtopic")) {
			return MensagemTipo.OFF_TOPIC;
		} else if (categoria.equals("negociacao")) {
			return MensagemTipo.NEGOCIACAO;
		}else {
			throw new Exception("MensagemTipo falta mensagem");
		}
		
	}

	public String getNome() {
		return this.nome;
	}
	
	@Override
	public String toString() {
		return getNome();
	}
	

}
