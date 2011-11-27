package iu.web.server.sistema.dao;

import iu.web.server.sistema.emprestimo.BancoDeEmprestimos;
import iu.web.server.sistema.emprestimo.Conta;
import iu.web.server.sistema.emprestimo.EmprestimoIF;
import iu.web.server.sistema.item.AcervoDeItens;
import iu.web.server.sistema.usuario.UsuarioIF;

public class BancoDeEmprestimosFileDAO implements BancoDeEmprestimosDAO {

	@Override
	public synchronized void adicionaContaAoUsuario(String usuario) throws Exception {
		BancoDeEmprestimos.getInstance().adicionaContaAoUsuario(usuario);
	}

	@Override
	public synchronized void removeContaDoUsuario(String usuario) throws Exception {
		BancoDeEmprestimos.getInstance().removeContaDoUsuario(usuario);
	}

	@Override
	public Conta getConta(String login) throws Exception {
		return BancoDeEmprestimos.getInstance().getConta(login);
	}

	@Override
	public synchronized void adicionarRequisicaoEmprestimoEmEsperaDeAmigo(String login,
			EmprestimoIF emp) throws Exception {
		BancoDeEmprestimos.getInstance().adicionarRequisicaoEmprestimoEmEsperaDeAmigo(login, emp);
	}

	@Override
	public String requisitarEmprestimo(String login, String idItem, int duracao)
			throws Exception {
		return BancoDeEmprestimos.getInstance().requisitarEmprestimo(login, idItem, duracao);
	}

	@Override
	public String getEmprestimos(String login, String tipo) throws Exception {
		return BancoDeEmprestimos.getInstance().getEmprestimos(login, tipo);
	}

	@Override
	public String aprovarEmprestimo(String login, String idRequisicaoEmprestimo)
			throws Exception {
		return BancoDeEmprestimos.getInstance().aprovarEmprestimo(login, idRequisicaoEmprestimo);
	}

	@Override
	public synchronized void emprestimoAceitoPorAmigo(String login, EmprestimoIF emp)
			throws Exception {
		BancoDeEmprestimos.getInstance().emprestimoAceitoPorAmigo(login, emp);
	}

	@Override
	public synchronized void removerEmprestimosRequeridosPorAmigo(String login,
			UsuarioIF amigo) {
		BancoDeEmprestimos.getInstance().removerEmprestimosRequeridosPorAmigo(login, amigo);
	}

	@Override
	public synchronized void removerEmprestimosRequeridosPorMim(String login, UsuarioIF amigo) {
		BancoDeEmprestimos.getInstance().removerEmprestimosRequeridosPorMim(login, amigo);
	}

	@Override
	public boolean requisiteiEsteItem(String login, String idItem)
			throws Exception {
		return BancoDeEmprestimos.getInstance().requisiteiEsteItem(login, idItem);
	}

	@Override
	public synchronized void removerMinhaSolicitacaoEmprestimo(String login,
			EmprestimoIF emprestimo) {
		BancoDeEmprestimos.getInstance().removerMinhaSolicitacaoEmprestimo(login, emprestimo);
	}

	@Override
	public synchronized void zerarSistema() {
		BancoDeEmprestimos.getInstance().zerarSistema();
		
	}

	@Override
	public synchronized void notificaPersistenciaDoSistema() {
		BancoDeEmprestimos.getInstance().salvarEmArquivo();
		
	}

	@Override
	public synchronized void iniciarDAO() {
		BancoDeEmprestimos.getInstance();
	}
	
	@Override
	public synchronized void iniciarListener() {
		iniciarDAO();
	}

}
