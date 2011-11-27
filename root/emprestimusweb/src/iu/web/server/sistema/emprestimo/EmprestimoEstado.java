package iu.web.server.sistema.emprestimo;

import static iu.web.server.sistema.utilitarios.Validador.assertStringNaoVazia;

import java.io.Serializable;

import iu.web.server.sistema.utilitarios.Mensagem;

/**
 * Este enum serve para caracterizar itens segundo a sua categoria.
 * 
 * @author Emprestimus grupo 6
 * @version 1.0
 */
public enum EmprestimoEstado implements Serializable {
	EM_ANDAMENTO("Em Andamento"),
	DEVOLVIDO("Devolvido"),
	DEVOLUCAO_REQUISITADA("Devolucao Requisitada"),
	TERMINAL("Terminado");

	private final String nome;

	private EmprestimoEstado(String nome) {
		this.nome = nome;
	}

	/**
	 * A partir do nome da categoria, este metodo retorna o enum indicado.
	 * 
	 * @param categoria - String
	 *      Nome da categoria que deve ser retornada.
	 *            
	 * @return EmprestimoEstado
	 * 		Categoria indicada pela string passada.
	 * 
	 * @throws Exception
	 *      Caso a string, passada como parametro, nao represente nenhum
	 *          dos enums, lanca excecao.
	 */
	public static EmprestimoEstado getCategoria(String categoria) throws Exception {
		assertStringNaoVazia(categoria, Mensagem.CATEGORIA_INVALIDA.getMensagem(),
				Mensagem.CATEGORIA_INVALIDA.getMensagem());

		if (categoria.equalsIgnoreCase("Em Andamento")) {
			return EmprestimoEstado.EM_ANDAMENTO;
		} else if (categoria.equalsIgnoreCase("Devolvido")) {
			return EmprestimoEstado.DEVOLVIDO;
		} else if (categoria.equalsIgnoreCase("Devolucao Requisitada")) {
			return EmprestimoEstado.DEVOLUCAO_REQUISITADA;
		} else if (categoria.equalsIgnoreCase("Terminado")) {
			return EmprestimoEstado.TERMINAL;
		} else {
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
