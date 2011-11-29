package iu.web.client.view;

import iu.web.client.Controlador;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.event.dom.client.MouseOverEvent;
import com.google.gwt.event.dom.client.MouseOverHandler;
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
	private static final String fotoPerfilDefault = "emprestimusweb/imagens/default-profile.png";
	
	private Controlador controlador;
	private DockLayoutPanel painelGlobal;
	private AbsolutePanel painelSuperior;
	private AbsolutePanel painelLateral;
	private AbsolutePanel painelInferior;
	private Image foto;
	private HTML htmlCentral;
	
	private Image imgCogumelo;
	private Image imgFitaDeck;
	private Image imgNew;

	private String htmlMural = "<h2><br><br>Mural</h2>";
	private String htmlPerfil = "<h2><br><br>Pefil</h2>";
	private String htmlMensagens = "<h2><br><br>Mensagens</h2>";
	private String htmlAmigos = "<h2><br><br>Amigos</h2>";
	private String htmlItens = "<h2><br><br>Itens</h2>";

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
		
		Image imgEmprestimus = new Image(Login.IMAGEM_Emprestimus.getUrl());
		painelSuperior.add(imgEmprestimus, 10, 10);
		imgEmprestimus.setSize("311px", "105px");
		
		imgCogumelo = new Image("emprestimusweb/imagens/09.png");
		painelSuperior.add(imgCogumelo, 447, 59);
		imgCogumelo.setSize("30", "26px");
		
		imgFitaDeck = new Image("emprestimusweb/imagens/06.png");
		painelSuperior.add(imgFitaDeck, 545, 59);
		imgFitaDeck.setSize("33px", "26");
		
		imgNew = new Image("emprestimusweb/imagens/05.png");
		painelSuperior.add(imgNew, 651, 63);
		imgNew.setSize("33px", "22px");
		imgNew.addMouseOverHandler(new MyHandlerBrilhaImgNew());
		imgNew.addMouseOutHandler(new MyHandlerVoltaImgNew());
		
		Hyperlink hprlnkEditarPerfil = new Hyperlink("Editar Perfil", false, "newHistoryToken");
		painelSuperior.add(hprlnkEditarPerfil, 777, 10);
		
		Hyperlink hprlnkSair = new Hyperlink("Sair", false, "newHistoryToken");
		hprlnkSair.addClickHandler(new MyHandlerSair());
		painelSuperior.add(hprlnkSair, 867, 10);
		
	}
	
	private void inicializaPainelLateral() {
		painelLateral = new AbsolutePanel();
		painelGlobal.addWest(painelLateral, 16.1);
		
		foto = new Image(fotoPerfilDefault);
		painelLateral.add(foto, 10, 10);
		foto.setSize("155px", "155px");
		
		MenuBar menuBar = new MenuBar(true);
		painelLateral.add(menuBar, 24, 202);
		
		MenuItem mntmMural = new MenuItem("Mural", false, new MyCommandMostraMural());
		mntmMural.setHTML("<menuItem>Mural</menuItem>");
		menuBar.addItem(mntmMural);
		
		MenuItem mntmPerfil = new MenuItem("Perfil", false, new MyCommandMostraPerfil());
		mntmPerfil.setHTML("<menuItem>Perfil</menuItem>");
		menuBar.addItem(mntmPerfil);
		
		MenuItem mntmMensagens = new MenuItem("Mensagens", false, new MyCommandMostraMensagens());
		mntmMensagens.setHTML("<menuItem>Mensagens</menuItem>");
		menuBar.addItem(mntmMensagens);
		
		MenuItem mntmAmigos = new MenuItem("Amigos", false, new MyCommandMostraAmigos());
		mntmAmigos.setHTML("<menuItem>Amigos</menuItem>");
		menuBar.addItem(mntmAmigos);
		
		MenuItem mntmItens = new MenuItem("Itens", false, new MyCommandMostraItens());
		mntmItens.setHTML("<menuItem>Itens</menuItem>");
		menuBar.addItem(mntmItens);
	}

	private void inicializaPainelInferior() {
		painelInferior = new AbsolutePanel();
		painelGlobal.addSouth(painelInferior, 3.6);
		
	}
	
	private void inicializaHtmlCentral() {
		htmlCentral = new HTML(htmlMural, true);
		painelGlobal.add(htmlCentral);
		
	}
	
	public void atualizaFoto() {
		String caminhoFoto = controlador.getFoto();
		if (caminhoFoto == null) {
			foto = new Image(fotoPerfilDefault);
		} else {
			foto = new Image(caminhoFoto);
		}
	}

	public void atualizaMural() {
		htmlMural = "<h2>Histórico de atualizações:</h2><br>"
				+controlador.getHistorico();
		htmlCentral.setHTML(htmlMural);
	}

	public void atualizaPerfil() {
		htmlPerfil = "<h2>"+controlador.getNome()+"</h2>";
		htmlCentral.setHTML(htmlPerfil);
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
			controlador.atualizaHistorico();
			htmlCentral.setHTML(htmlMural);
			
		}
	}
	class MyCommandMostraPerfil implements Command {
		@Override
		public void execute() {
			controlador.atualizaNome();
			htmlCentral.setHTML(htmlPerfil);
		}
	}
	class MyCommandMostraMensagens implements Command {
		@Override
		public void execute() {
			htmlCentral.setHTML(htmlMensagens);
		}
	}
	class MyCommandMostraAmigos implements Command {
		@Override
		public void execute() {
			htmlCentral.setHTML(htmlAmigos);
		}
	}
	class MyCommandMostraItens implements Command {
		@Override
		public void execute() {
			htmlCentral.setHTML(htmlItens);
		}
	}
	
	class MyHandlerBrilhaImgNew implements MouseOverHandler{

		@Override
		public void onMouseOver(MouseOverEvent event) {
			imgNew.setSize("66px", "44px");
		}
	}	class MyHandlerVoltaImgNew implements MouseOutHandler{

		@Override
		public void onMouseOut(MouseOutEvent event) {
			imgNew.setSize("33px", "22px");
		}
	}
}
