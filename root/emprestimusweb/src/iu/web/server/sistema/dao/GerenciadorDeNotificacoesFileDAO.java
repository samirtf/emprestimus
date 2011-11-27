package iu.web.server.sistema.dao;

import iu.web.server.sistema.excecoes.ArgumentoInvalidoException;
import iu.web.server.sistema.item.ItemIF;
import iu.web.server.sistema.notificacao.GerenciadorDeNotificacoes;
import iu.web.server.sistema.notificacao.Rack;
import iu.web.server.sistema.persistencia.EmprestimoRepositorio;
import iu.web.server.sistema.usuario.UsuarioIF;

public class GerenciadorDeNotificacoesFileDAO implements GerenciadorDeNotificacoesDAO {

	@Override
	public synchronized void adicionaRackAoUsuario(String usuario) throws Exception {
		GerenciadorDeNotificacoes.getInstance().adicionaRackAoUsuario(usuario);
	}

	@Override
	public synchronized void removeRackDoUsuario(String usuario) throws Exception {
		GerenciadorDeNotificacoes.getInstance().removeRackDoUsuario(usuario);
	}

	@Override
	public Rack getRack(String login) throws Exception {
		return GerenciadorDeNotificacoes.getInstance().getRack(login);
	}

	@Override
	public synchronized void addHistoricoNovoItem(String seuLogin, ItemIF item)
			throws Exception {
		GerenciadorDeNotificacoes.getInstance().addHistoricoNovoItem(seuLogin, item);
	}

	@Override
	public synchronized void addHistoricoAmizadeAprovada(String seuLogin, UsuarioIF amigo)
			throws Exception {
		GerenciadorDeNotificacoes.getInstance().addHistoricoAmizadeAprovada(seuLogin, amigo);
	}

	@Override
	public synchronized void addHistoricoEmprestimoEmAndamento(String seuLogin,
			UsuarioIF amigo, ItemIF item) throws Exception {
		GerenciadorDeNotificacoes.getInstance().addHistoricoEmprestimoEmAndamento(seuLogin, amigo, item);
	}

	@Override
	public synchronized void zerarHistorico(String seuLogin) {
		GerenciadorDeNotificacoes.getInstance().zerarHistorico(seuLogin);
	}

	@Override
	public String getHistoricoToString(String seuLogin)
			throws ArgumentoInvalidoException {
		return GerenciadorDeNotificacoes.getInstance().getHistoricoToString(seuLogin);
	}

	@Override
	public synchronized void addHistoricoInteressePorItem(String seuLogin, UsuarioIF amigo,
			ItemIF item) throws Exception {
		GerenciadorDeNotificacoes.getInstance().addHistoricoInteressePorItem(seuLogin, amigo, item);
	}

	@Override
	public synchronized void addHistoricoTerminoEmprestimo(String seuLogin, ItemIF item)
			throws Exception {
		GerenciadorDeNotificacoes.getInstance().addHistoricoTerminoEmprestimo(seuLogin, item);
	}

	@Override
	public String getHistoricoAtividadesConjunto(UsuarioIF usuario)
			throws Exception {
		return GerenciadorDeNotificacoes.getInstance().getHistoricoAtividadesConjunto(usuario);
	}

	@Override
	public String addHistoricoPublicarPedido(String seuLogin, String nomeItem,
			String descricaoItem) throws Exception {
		return GerenciadorDeNotificacoes.getInstance().addHistoricoPublicarPedido(seuLogin, nomeItem, descricaoItem);
	}

	@Override
	public synchronized void republicarPedido(UsuarioIF usuario, String idPublicacaoPedido)
			throws Exception {
		GerenciadorDeNotificacoes.getInstance().republicarPedido(usuario, idPublicacaoPedido)		;
	}

	@Override
	public synchronized void zerarSistema() {
		GerenciadorDeNotificacoes.getInstance().zerarSistema();		
	}

	@Override
	public synchronized void notificaPersistenciaDoSistema() {
		GerenciadorDeNotificacoes.getInstance().salvarEmArquivo();
	}

	@Override
	public synchronized void iniciarDAO() {
		GerenciadorDeNotificacoes.getInstance();
	}
	
	@Override
	public synchronized void iniciarListener() {
		iniciarDAO();
	}

}
