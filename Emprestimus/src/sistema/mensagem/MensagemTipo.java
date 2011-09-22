package sistema.mensagem;

import sistema.emprestimo.EmprestimoEstado;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.ValidadorString;

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
		if (!ValidadorString.validaString(categoria).equals(Mensagem.OK.getMensagem())) {
			throw new Exception(Mensagem.CATEGORIA_INVALIDA.getMensagem());
		}
		
		categoria.toUpperCase();
		if (categoria.equals("offtopic")) {
			return MensagemTipo.OFF_TOPIC;
		} else if (categoria.equals("negociacao")) {
			return MensagemTipo.NEGOCIACAO;
		}else {
			throw new Exception("MEnsagemTipo falta mensagem");
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
