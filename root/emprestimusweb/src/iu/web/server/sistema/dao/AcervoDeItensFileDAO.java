package iu.web.server.sistema.dao;

import iu.web.server.sistema.excecoes.ArgumentoInvalidoException;
import iu.web.server.sistema.item.AcervoDeItens;
import iu.web.server.sistema.item.Bauh;
import iu.web.server.sistema.item.ItemIF;

import java.util.List;


public class AcervoDeItensFileDAO implements AcervoDeItensDAO {

	@Override
	public void adicionaBauhAoUsuario(String usuario) throws Exception {
		AcervoDeItens.getInstance().adicionaBauhAoUsuario(usuario);
	}

	@Override
	public void removeContaDoUsuario(String usuario) throws Exception {
		AcervoDeItens.getInstance().removeContaDoUsuario(usuario);
	}

	@Override
	public Bauh getBauh(String login) throws Exception {
		return AcervoDeItens.getInstance().getBauh(login);
	}

	@Override
	public String cadastrarItem(String login, String nome, String descricao,
			String categoria) throws Exception {
		return AcervoDeItens.getInstance().cadastrarItem(login, nome, descricao, categoria);
	}

	@Override
	public boolean removerItem(String login, String idItem)
			throws ArgumentoInvalidoException {
		return AcervoDeItens.getInstance().removerItem(login, idItem);
	}

	@Override
	public String getListaIdItens(String login)
			throws ArgumentoInvalidoException {
		return AcervoDeItens.getInstance().getListaIdItens(login);
	}

	@Override
	public List<ItemIF> getItens(String login) {
		return AcervoDeItens.getInstance().getItens(login);
	}

	@Override
	public ItemIF getItem(String login, String idItem)
			throws ArgumentoInvalidoException {
		return AcervoDeItens.getInstance().getItem(login, idItem);
	}

	@Override
	public int qntItens(String login) throws ArgumentoInvalidoException {
		return AcervoDeItens.getInstance().qntItens(login);
	}

	@Override
	public int qntItensEmprestados(String login)
			throws ArgumentoInvalidoException {
		return AcervoDeItens.getInstance().qntItensEmprestados(login);
	}

	@Override
	public String getListaIdItensEmprestados(String login)
			throws ArgumentoInvalidoException {
		return AcervoDeItens.getInstance().getListaIdItensEmprestados(login);
	}

	@Override
	public boolean existeItemID(String login, String idItem)
			throws ArgumentoInvalidoException {
		return AcervoDeItens.getInstance().existeItemID(login, idItem);
	}

	@Override
	public String getListaItens(String login) throws Exception {
		return AcervoDeItens.getInstance().getListaIdItens(login);
	}

	@Override
	public boolean esteItemMePertence(String login, String idItem)
			throws Exception {
		return AcervoDeItens.getInstance().esteItemMePertence(login, idItem);
	}

	@Override
	public void apagarItem(String login, String idItem) throws Exception {
		AcervoDeItens.getInstance().apagarItem(login, idItem);
	}

	@Override
	public void registrarInteressePorItem(String seuLogin, String idItem)
			throws Exception {
		AcervoDeItens.getInstance().registrarInteressePorItem(seuLogin, idItem);
	}

	@Override
	public void oferecerItem(String login, String idPublicacaoPedido,
			String idItem) throws Exception {
		AcervoDeItens.getInstance().oferecerItem(login, idPublicacaoPedido, idItem);
	}

	@Override
	public void zerarSistema() {
		AcervoDeItens.getInstance().zerarSistema();
	}

	@Override
	public void notificaPersistenciaDoSistema() {
		AcervoDeItens.getInstance().salvarEmArquivo();
	}

	@Override
	public void iniciarDAO() {
		AcervoDeItens.getInstance();
	}

}
