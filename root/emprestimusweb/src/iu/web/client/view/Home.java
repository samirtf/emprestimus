package iu.web.client.view;

import iu.web.client.Controlador;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;


/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public class Home extends Composite {
	private static final Image IMAGEM_LOGO = new Image("emprestimusweb/imagens/logo2.png");

	private static final Image fotoPerfilDefault = new Image("emprestimusweb/imagens/default-profile.png");
	
	private Controlador controlador;
	private DockLayoutPanel painelGlobal;
	private AbsolutePanel painelSuperior;
	private AbsolutePanel painelLateral;
	private AbsolutePanel painelInferior;
	private Image foto;
	private HTML htmlCentral;

	public Home(Controlador controlador) {
		this.controlador = controlador;
		
		painelGlobal = new DockLayoutPanel(Unit.EM);
		initWidget(painelGlobal);
		painelGlobal.setSize("900px", "600px");
		
		inicializaPainelSuperior();
		inicializaPainelLateral();
		inicializaPainelInferior();
		inicializaHtmlCentral();
		
	}
	
	private void inicializaPainelSuperior() {
		painelSuperior = new AbsolutePanel();
		painelGlobal.addNorth(painelSuperior, 9.1);
		
		Image imgEmprestimus = IMAGEM_LOGO;
		painelSuperior.add(imgEmprestimus, 10, 10);
		imgEmprestimus.setSize("311px", "105px");
		
		Image imgCogumelo = new Image("emprestimusweb/imagens/09.png");
		painelSuperior.add(imgCogumelo, 447, 59);
		imgCogumelo.setSize("30", "26px");
		
		Image imgFitaDeck = new Image("emprestimusweb/imagens/06.png");
		painelSuperior.add(imgFitaDeck, 545, 59);
		imgFitaDeck.setSize("33px", "26");
		
		Image imgNew = new Image("emprestimusweb/imagens/05.png");
		painelSuperior.add(imgNew, 651, 63);
		imgNew.setSize("33px", "22px");
		
		Hyperlink hprlnkEditarPerfil = new Hyperlink("Editar Perfil", false, "newHistoryToken");
		painelSuperior.add(hprlnkEditarPerfil, 777, 10);
		
		Hyperlink hprlnkSair = new Hyperlink("Sair", false, "newHistoryToken");
		hprlnkSair.addClickHandler(new MyHandlerSair());
		painelSuperior.add(hprlnkSair, 867, 10);
		
	}
	
	private void inicializaPainelLateral() {
		painelLateral = new AbsolutePanel();
		painelGlobal.addWest(painelLateral, 16.1);
		
		foto = fotoPerfilDefault;
		painelLateral.add(foto, 10, 10);
		foto.setSize("155px", "155px");
		
		MenuBar menuBar = new MenuBar(true);
		painelLateral.add(menuBar, 24, 202);
		
		MenuItem mntmMural = new MenuItem("Mural", false, (Command) null);
		mntmMural.setHTML("<menuItem>Mural</menuItem>");
		menuBar.addItem(mntmMural);
		mntmMural.setCommand(new MyCommandMostraMural());
		
		MenuItem mntmPerfil = new MenuItem("Perfil", false, (Command) null);
		mntmPerfil.setHTML("<menuItem>Perfil</menuItem>");
		menuBar.addItem(mntmPerfil);
		mntmPerfil.setCommand(new MyCommandMostraPerfil());
		
		MenuItem mntmMensagens = new MenuItem("Mensagens", false, (Command) null);
		mntmMensagens.setHTML("<menuItem>Mensagens</menuItem>");
		menuBar.addItem(mntmMensagens);
		mntmPerfil.setCommand(new MyCommandMostraMensagens());
		
		MenuItem mntmAmigos = new MenuItem("Amigos", false, (Command) null);
		mntmAmigos.setHTML("<menuItem>Amigos</menuItem>");
		menuBar.addItem(mntmAmigos);
		
		MenuItem mntmItens = new MenuItem("Itens", false, (Command) null);
		mntmItens.setHTML("<menuItem>Itens</menuItem>");
		menuBar.addItem(mntmItens);
	}

	private void inicializaPainelInferior() {
		painelInferior = new AbsolutePanel();
		painelGlobal.addSouth(painelInferior, 3.6);
		
	}
	
	private void inicializaHtmlCentral() {
		htmlCentral = new HTML("<br><br><h2>Carregando...</h2>", true);
		painelGlobal.add(htmlCentral);
		
	}
	
	public void inicializaAtributosDoUsiario() {
		String caminhoFoto = controlador.getFoto();
		if (caminhoFoto == null) {
			foto = fotoPerfilDefault;
		} else {
			foto = new Image(caminhoFoto);
		}
		
		htmlCentral.setHTML("<h2>Bemvindo, "+controlador.getNome()+"</h2><br>Histórico de atualizações:<br>"
				+controlador.getHistorico());
	}
	
	class MyHandlerSair implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			removeFromParent();
			controlador.fecharSessao();
		}
	}
	
	class MyCommandMostraMural implements Command {
		@Override
		public void execute() {
			htmlCentral.setHTML("<h2><br><br>Mural</h2>");
			
		}
	}
	class MyCommandMostraPerfil implements Command {
		@Override
		public void execute() {
			htmlCentral.setHTML("<h2><br><br>Pefil</h2>");
		}
	}
	class MyCommandMostraMensagens implements Command {
		@Override
		public void execute() {
			htmlCentral.setHTML("<h2><br><br>Mensagens</h2>");
		}
	}
	class MyCommandMostraAmigos implements Command {
		@Override
		public void execute() {
			htmlCentral.setHTML("<h2><br><br>Amigos</h2>");
		}
	}
	class MyCommandMostraItens implements Command {
		@Override
		public void execute() {
			htmlCentral.setHTML("<h2><br><br>Itens</h2>");
		}
	}
}
