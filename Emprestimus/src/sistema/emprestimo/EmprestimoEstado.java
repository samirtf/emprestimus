package sistema.emprestimo;

import sistema.utilitarios.Mensagem;
import sistema.utilitarios.ValidadorString;

/**
 * Este enum serve para caracterizar itens segundo a sua categoria.
 * 
 * @author Emprestimus grupo 6
 * @version 1.0
 * @since 1.0
 */
public enum EmprestimoEstado {
	ACEITO("Aceito"), RECUSADO("Recusado"), DEVOLVIDO("Devolvido"), ANDAMENTO("Andamento"), EM_ESPERA("Em espera");
	
	private final String nome;
	
	private EmprestimoEstado(String nome) {
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
	public static EmprestimoEstado getCategoria(String categoria) throws Exception {
		if (!ValidadorString.validaString(categoria).equals(Mensagem.OK.getMensagem())) {
			throw new Exception(Mensagem.CATEGORIA_INVALIDA.getMensagem());
		}
		
		categoria.toUpperCase();
		if (categoria.equals("ACEITO")) {
			return EmprestimoEstado.ACEITO;
		} else if (categoria.equals("RECUSADO")) {
			return EmprestimoEstado.RECUSADO;
		} else if (categoria.equals("REVOLVIDO")) {
			return EmprestimoEstado.DEVOLVIDO;
		} else if (categoria.equals("ANDAMENTO")) {
			return EmprestimoEstado.ANDAMENTO;
		}else {
			throw new Exception(Mensagem.CATEGORIA_INEXISTENTE.getMensagem());
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