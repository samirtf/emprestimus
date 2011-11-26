package sistema.dao;

import sistema.emprestimo.EmprestimoIF;
import sistema.persistencia.EmprestimoRepositorio;

public class EmprestimoFileDAO implements EmprestimoDAO {

	@Override
	public String geraIdProxNotificacao() {
		return EmprestimoRepositorio.getInstance().geraIdProxNotificacao();
	}

	@Override
	public String requisitarEmprestimo(EmprestimoIF emp) throws Exception {
		return EmprestimoRepositorio.getInstance().requisitarEmprestimo(emp);
	}

	@Override
	public EmprestimoIF recuperarEmprestimo(String idEmprestimo)
			throws Exception {
		return EmprestimoRepositorio.getInstance().recuperarEmprestimo(idEmprestimo);
	}

	@Override
	public String getAtributoItem(String idEmprestimo, String atributo)
			throws Exception {
		return EmprestimoRepositorio.getInstance().getAtributoItem(idEmprestimo, atributo);
	}

	@Override
	public int qntEmprestimos() {
		return EmprestimoRepositorio.getInstance().qntEmprestimos();
	}

	@Override
	public boolean existeEmprestimo(String idEmprestimo) {
		return EmprestimoRepositorio.getInstance().existeEmprestimo(idEmprestimo);
	}

	@Override
	public void removerEmprestimo(String idEmprestimo) {
		EmprestimoRepositorio.getInstance().removerEmprestimo(idEmprestimo);
	}

	@Override
	public void zerarRepositorio() {
		EmprestimoRepositorio.getInstance().zerarRepositorio();
	}

}
