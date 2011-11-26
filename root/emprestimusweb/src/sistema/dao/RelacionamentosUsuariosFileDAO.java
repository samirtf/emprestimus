package sistema.dao;

import java.util.List;

import sistema.excecoes.ArgumentoInvalidoException;
import sistema.usuario.CicloDeAmizade;
import sistema.usuario.RelacionamentosUsuarios;
import sistema.usuario.UsuarioIF;

public class RelacionamentosUsuariosFileDAO implements RelacionamentosUsuariosDAO {

	@Override
	public void adicionaCicloDeAmizadeAoUsuario(String usuario)
			throws Exception {
		adicionaCicloDeAmizadeAoUsuario(usuario);
	}

	@Override
	public void removeCicloDeAmizadeDoUsuario(String usuario) throws Exception {
		RelacionamentosUsuarios.getInstance().removeCicloDeAmizadeDoUsuario(usuario);
	}

	@Override
	public CicloDeAmizade getCicloDeAmizade(String login) throws Exception {
		return RelacionamentosUsuarios.getInstance().getCicloDeAmizade(login);
	}

	@Override
	public void aprovarAmizade(String seuLogin, String novoAmigo)
			throws Exception {
		RelacionamentosUsuarios.getInstance().aprovarAmizade(seuLogin, novoAmigo);
	}

	@Override
	public void aprovouAmizade(String seuLogin, UsuarioIF usuario)
			throws ArgumentoInvalidoException {
		RelacionamentosUsuarios.getInstance().aprovouAmizade(seuLogin, usuario);
	}

	@Override
	public boolean ehAmigo(String seuLogin, String amigoProcurado)
			throws ArgumentoInvalidoException {
		return RelacionamentosUsuarios.getInstance().ehAmigo(seuLogin, amigoProcurado);
	}

	@Override
	public boolean amizadeDeFoiRequisitada(String seuLogin, String loginDoAmigo)
			throws ArgumentoInvalidoException {
		return RelacionamentosUsuarios.getInstance().amizadeDeFoiRequisitada(seuLogin, loginDoAmigo);
	}

	@Override
	public void requisitarAmizade(String seuLogin, String loginDoAmigo)
			throws Exception {
		RelacionamentosUsuarios.getInstance().requisitarAmizade(seuLogin, loginDoAmigo);
	}

	@Override
	public void usuarioQuerSerMeuAmigo(String seuLogin,
			UsuarioIF usuarioSolicitante) throws ArgumentoInvalidoException {
		RelacionamentosUsuarios.getInstance().usuarioQuerSerMeuAmigo(seuLogin, usuarioSolicitante);
	}

	@Override
	public String getAmigos(String seuLogin) throws Exception {
		return RelacionamentosUsuarios.getInstance().getAmigos(seuLogin);
	}

	@Override
	public UsuarioIF ehItemDoMeuAmigo(String seuLogin, String idItem)
			throws Exception {
		return RelacionamentosUsuarios.getInstance().ehItemDoMeuAmigo(seuLogin, idItem);
	}

	@Override
	public UsuarioIF possuoAmigoComEsteLogin(String seuLogin,
			String loginDoAmigo) throws Exception {
		return RelacionamentosUsuarios.getInstance().possuoAmigoComEsteLogin(seuLogin, loginDoAmigo);
	}

	@Override
	public String pesquisarItem(String seuLogin, String chave, String atributo,
			String tipoOrdenacao, String criterioOrdenacao) throws Exception {
		return RelacionamentosUsuarios.getInstance().pesquisarItem(seuLogin, chave, atributo, tipoOrdenacao, criterioOrdenacao);
	}

	@Override
	public void desfazerAmizade(String seuLogin, String amigo) throws Exception {
		RelacionamentosUsuarios.getInstance().desfazerAmizade(seuLogin, amigo);
	}

	@Override
	public void removerAmigoDaLista(String seuLogin, UsuarioIF amigo) {
		RelacionamentosUsuarios.getInstance().removerAmigoDaLista(seuLogin, amigo);
	}

	@Override
	public List<UsuarioIF> getListaAmigos(String seuLogin) {
		return RelacionamentosUsuarios.getInstance().getListaAmigos(seuLogin);
	}

	@Override
	public void zerarSistema() {
		RelacionamentosUsuarios.getInstance().zerarSistema();
	}

}
