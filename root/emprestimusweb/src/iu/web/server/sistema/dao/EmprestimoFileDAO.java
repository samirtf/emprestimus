package iu.web.server.sistema.dao;

import iu.web.server.sistema.emprestimo.EmprestimoIF;
import iu.web.server.sistema.persistencia.EmprestimoRepositorio;

public class EmprestimoFileDAO implements EmprestimoDAO {

	@Override
	public synchronized String geraIdProxNotificacao() {
		return EmprestimoRepositorio.getInstance().geraIdProxNotificacao();
	}

	@Override
	public synchronized String requisitarEmprestimo(EmprestimoIF emp) throws Exception {
		return EmprestimoRepositorio.getInstance().requisitarEmprestimo(emp);
	}

	@Override
	public synchronized EmprestimoIF recuperarEmprestimo(String idEmprestimo)
			throws Exception {
		return EmprestimoRepositorio.getInstance().recuperarEmprestimo(idEmprestimo);
	}

	@Override
	public synchronized String getAtributoItem(String idEmprestimo, String atributo)
			throws Exception {
		return EmprestimoRepositorio.getInstance().getAtributoItem(idEmprestimo, atributo);
	}

	@Override
	public synchronized int qntEmprestimos() {
		return EmprestimoRepositorio.getInstance().qntEmprestimos();
	}

	@Override
	public synchronized boolean existeEmprestimo(String idEmprestimo) {
		return EmprestimoRepositorio.getInstance().existeEmprestimo(idEmprestimo);
	}

	@Override
	public synchronized void removerEmprestimo(String idEmprestimo) {
		EmprestimoRepositorio.getInstance().removerEmprestimo(idEmprestimo);
	}

	@Override
	public synchronized void zerarRepositorio() {
		EmprestimoRepositorio.getInstance().zerarRepositorio();
	}

	@Override
	public void notificaPersistenciaDoSistema() {
		EmprestimoRepositorio.getInstance().salvarEmArquivo();
	}

	@Override
	public void iniciarDAO() {
		EmprestimoRepositorio.getInstance();
	}

}
