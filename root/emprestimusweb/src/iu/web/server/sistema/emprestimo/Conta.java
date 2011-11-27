package iu.web.server.sistema.emprestimo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Conta implements Comparable<Conta>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 33704979272994725L;
	
	private String proprietario;
	private List<EmprestimoIF> emprestimos;
	private List<EmprestimoIF> emprestimosRequeridosPorAmigosEmEspera;
	private List<EmprestimoIF> emprestimosRequeridosPorMimEmEspera;

	/**
	 * Cria uma nova instancia de Conta
	 * 
	 * @param proprietario - String
	 * 		Proprietario desta Conta
	 */
	public Conta(String proprietario) {
		this.proprietario = proprietario;
		emprestimos = new ArrayList<EmprestimoIF>();
		emprestimosRequeridosPorAmigosEmEspera = new ArrayList<EmprestimoIF>();
		emprestimosRequeridosPorMimEmEspera = new ArrayList<EmprestimoIF>();
	}

	/**
	 * @return String
	 * 		Proprietario desta Conta
	 */
	public String getProprietario() {
		return this.proprietario;
	}

	/**
	 * @return List<EmprestimoIF>
	 * 		Lista de emprestimos desta Conta
	 */
	public List<EmprestimoIF> getEmprestimos() {
		return this.emprestimos;
	}

	/**
	 * @return List<EmprestimoIF>
	 * 		Lista contendo requerimentos de emprestimos feitos por amigos.
	 */
	public List<EmprestimoIF> getEmprestimosRequeridosPorAmigosEmEspera() {
		return this.emprestimosRequeridosPorAmigosEmEspera;
	}

	/**
	 * @return List<EmprestimoIF>
	 * 		Lista contendo requerimentos de emprestimos feitos pelo proprio usuario.
	 */
	public List<EmprestimoIF> getEmprestimosRequeridosPorMimEmEspera() {
		return this.emprestimosRequeridosPorMimEmEspera;
	}

	@Override
	public int compareTo(Conta o) {
		return this.proprietario.compareTo(o.getProprietario());
	}

}
