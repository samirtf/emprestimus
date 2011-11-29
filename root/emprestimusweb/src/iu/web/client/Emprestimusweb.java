package iu.web.client;

import iu.web.client.view.Home;
import iu.web.client.view.Login;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Emprestimusweb implements EntryPoint {
	Controlador controlador = new Controlador(this);
	String idSessao;
	
	Home compositeHome;

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Login compositeLogin = new Login(controlador);
		compositeLogin.setVisible(true);
		RootPanel.get("nameFieldContainer").add(compositeLogin);

//		new Image("emprestimusweb/gwt/clean/imagens/mj.jpg")
	}
	
	public void abrirSessao(String idSessao) {
		compositeHome = new Home(controlador);
		compositeHome.setVisible(true);
		RootPanel.get("nameFieldContainer").add(compositeHome);
	}
	
	public void fotoFoiAtualizada() {
		compositeHome.atualizaFoto();
	}

	public void historicoFoiAtualizado() {
		compositeHome.atualizaMural();
	}

	public void nomeFoiAtualizado() {
		compositeHome.atualizaPerfil();
	}

	/**
	 * 
	 */
	public void amigosFoiAtualizado() {
		compositeHome.atualizaAmigos();
	}

	/**
	 * 
	 */
	public void mensagensFoiAtualizado() {
		compositeHome.atualizaMensagens();
	}

	/**
	 * 
	 */
	public void itensFoiAtualizado() {
		compositeHome.atualizaItens();
	}
	
}
