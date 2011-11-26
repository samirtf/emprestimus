package sistema.dao;

import sistema.emprestimo.EmprestimoIF;
import sistema.persistencia.EmprestimoRepositorio;

public class EmprestimoFileDAO implements EmprestimoDAO {

	@Override
	public String geraIdProxNotificacao() {
		return EmprestimoRepositorio.geraIdProxNotificacao();
	}

	@Override
	public String requisitarEmprestimo(EmprestimoIF emp) throws Exception {
		return EmprestimoRepositorio.requisitarEmprestimo(emp);
	}

	@Override
	public EmprestimoIF recuperarEmprestimo(String idEmprestimo)
			throws Exception {
		return EmprestimoRepositorio.recuperarEmprestimo(idEmprestimo);
	}

	@Override
	public String getAtributoItem(String idEmprestimo, String atributo)
			throws Exception {
		return EmprestimoRepositorio.getAtributoItem(idEmprestimo, atributo);
	}

	@Override
	public int qntEmprestimos() {
		return EmprestimoRepositorio.qntEmprestimos();
	}

	@Override
	public boolean existeEmprestimo(String idEmprestimo) {
		return EmprestimoRepositorio.existeEmprestimo(idEmprestimo);
	}

	@Override
	public void removerEmprestimo(String idEmprestimo) {
		EmprestimoRepositorio.removerEmprestimo(idEmprestimo);
	}

	@Override
	public void zerarRepositorio() {
		EmprestimoRepositorio.zerarRepositorio();
	}

}
