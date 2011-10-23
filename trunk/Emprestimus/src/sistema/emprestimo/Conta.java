package sistema.emprestimo;

import java.util.ArrayList;
import java.util.List;

public class Conta implements Comparable<Conta>{

	private String proprietario;
	private List<EmprestimoIF> emprestimos;
	private List<EmprestimoIF> emprestimosRequeridosPorAmigosEmEspera;
	private List<EmprestimoIF> emprestimosRequeridosPorMimEmEspera;
	
	public Conta(String proprietario) {
		this.proprietario = proprietario;
		emprestimos = new ArrayList<EmprestimoIF>();
		emprestimosRequeridosPorAmigosEmEspera = new ArrayList<EmprestimoIF>();
		emprestimosRequeridosPorMimEmEspera = new ArrayList<EmprestimoIF>();
	}
	
	public String getProprietario(){
		return this.proprietario;
	}
	
	public List<EmprestimoIF> getEmprestimos(){
		return this.emprestimos;
	}
	
	public List<EmprestimoIF> getEmprestimosRequeridosPorAmigosEmEspera(){
		return this.emprestimosRequeridosPorAmigosEmEspera;
	}
	
	public List<EmprestimoIF> getEmprestimosRequeridosPorMimEmEspera(){
		return this.emprestimosRequeridosPorMimEmEspera;
	}

	@Override
	public int compareTo(Conta o) {
		return this.proprietario.compareTo(o.getProprietario());
	}
	
	

}
