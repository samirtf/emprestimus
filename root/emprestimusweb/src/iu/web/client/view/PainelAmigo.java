package iu.web.client.view;

import iu.web.client.Controlador;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public class PainelAmigo extends Composite {
private static final String fotoPerfilDefault = "emprestimusweb/imagens/default-profile.png";
	
	private Controlador controlador;
	private DockLayoutPanel painelGlobal;
	private AbsolutePanel painelSuperior;
	private AbsolutePanel painelLateral;
	private Image foto;
	private HTML htmlCentral;
	private AbsolutePanel painelCentral;
	
	private Image imgSolicitacoes;
	private Image imgRequisicoes;
	private Image imgNovaMensagem;

	private String htmlMural = "Carregando...";
	private String htmlPerfil = "Carregando...";
	private String htmlAmigos = "Carregando...";
	private String htmlItens = "Carregando...";
	private String htmlEmprestimosTipoEmprestador = "Carregando...";
	private String htmlEmprestimosTipoBeneficiador = "Carregando...";
	private String htmlEmprestimosTipoTodos = "Carregando...";

	public PainelAmigo(Controlador controlador) {
		this.controlador = controlador;
		
		painelGlobal = new DockLayoutPanel(Unit.EM);
		initWidget(painelGlobal);
		painelGlobal.setSize("900px", "522px");
		
		inicializaPainelLateral();
		inicializaHtmlCentral();
		
	}
	
	private void inicializaPainelLateral() {
		painelLateral = new AbsolutePanel();
		painelGlobal.addWest(painelLateral, 16.1);
		
		foto = new Image(fotoPerfilDefault);
		painelLateral.add(foto, 10, 10);
		foto.setSize("155px", "155px");
		
		MenuBar menuBar = new MenuBar(true);
		menuBar.setAutoOpen(true);
		menuBar.setAnimationEnabled(true);
		painelLateral.add(menuBar, 24, 202);
		
		MenuItem mntmMural = new MenuItem("Mural", false, new MyCommandMostraMural());
		mntmMural.setHTML("<menuItem>Mural</menuItem>");
		menuBar.addItem(mntmMural);
		
		MenuItem mntmPerfil = new MenuItem("Perfil", false, new MyCommandMostraPerfil());
		mntmPerfil.setHTML("<menuItem>Perfil</menuItem>");
		menuBar.addItem(mntmPerfil);
		
		MenuItem mntmAmigos = new MenuItem("Amigos", false, new MyCommandMostraAmigos());
		mntmAmigos.setHTML("<menuItem>Amigos</menuItem>");
		menuBar.addItem(mntmAmigos);
		
		MenuItem mntmEmprestimos = new MenuItem("Emprestimos", false, new MyCommandMostraEmprestimos());
		mntmEmprestimos.setHTML("<menuItem>Empréstimos</menuItem>");
		menuBar.addItem(mntmEmprestimos);
		
		MenuItem mntmItens = new MenuItem("Itens", false, new MyCommandMostraItens());
		mntmItens.setHTML("<menuItem>Itens</menuItem>");
		menuBar.addItem(mntmItens);
	}

	
	private void inicializaHtmlCentral() {
		painelCentral = new AbsolutePanel();
		
		htmlCentral = new HTML("Carregando...", true);
		painelCentral.add(htmlCentral, 10, 114);
		htmlCentral.setSize("671px", "481px");
		painelGlobal.add(painelCentral);
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		painelCentral.add(absolutePanel, 0, 0);
		absolutePanel.setSize("691px", "108px");
		
		
		setPainelMural();
	}
	
	private void setPainelMural() {
		painelCentral.clear();
		
		AbsolutePanel painelMural = new AbsolutePanel();
		painelCentral.add(painelMural, 0, 0);
		painelMural.setSize("691px", "108px");
		
		Label lblMuralDeAtualizaes = new Label("Mural de Atualizações");
		lblMuralDeAtualizaes.setStyleName("nathaniel2");
		painelMural.add(lblMuralDeAtualizaes, 10, 25);
		
		painelGlobal.setHeight("1000px");
		htmlCentral.setHTML(htmlMural);
	}
	
	private void setPainelPerfil() {
		painelCentral.clear();
		
		AbsolutePanel painelPerfil = new AbsolutePanel();
		painelCentral.add(painelPerfil, 0, 0);
		painelPerfil.setSize("691px", "108px");
		
		Label lblNome = new Label(controlador.getNome());
		lblNome.setStyleName("nathaniel2");
		painelPerfil.add(lblNome, 10, 10);
		
		Label lblEndereco = new Label("Endereço: " + controlador.getEndereco());
		lblEndereco.setStyleName("nathaniel1");
		painelPerfil.add(lblEndereco, 10, 74);
		
		Button btnEditarPerfil = new Button("Editar Perfil");
		painelPerfil.add(btnEditarPerfil, 600, 10);
		
		htmlCentral.setHTML(htmlPerfil);
	}
	
	private void setPainelAmigos() {
		painelCentral.clear();
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		painelCentral.add(absolutePanel, 0, 0);
		absolutePanel.setSize("691px", "107px");
		
		Label lblBuscarPessoas = new Label("Buscar Pessoas:");
		absolutePanel.add(lblBuscarPessoas, 22, 35);
		
		TextBox txtBuscarPessoas = new TextBox();
		absolutePanel.add(txtBuscarPessoas, 128, 31);
		txtBuscarPessoas.setSize("276px", "18px");
		
		Button btnBuscar = new Button("Buscar");
		absolutePanel.add(btnBuscar, 431, 31);

		htmlCentral.setHTML(htmlAmigos);
	}

	private void setPainelEmprestimos() {
		painelCentral.clear();
		//TODO: Implemente isto!
		htmlCentral.setHTML(htmlEmprestimosTipoTodos);
	}
	
	private void setPainelItens() {
		painelCentral.clear();
		//TODO: Implemente isto!
		htmlCentral.setHTML(htmlItens);
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
		setPainelMural();
	}

	public void atualizaPerfil() {
		htmlPerfil = "<h2>"+controlador.getNome()+"</h2>";
		setPainelPerfil();
	}

	public void atualizaAmigos() {
		htmlAmigos = "";
		for (String amigo : controlador.getAmigos().split("; ")) {
			Hyperlink link = new Hyperlink();
			link.setHTML(amigo);
			link.addClickHandler(new MyHandlerAmigo(amigo));
			htmlAmigos += link + "<br>";
		}
		setPainelAmigos();
	}
	
	public void atualizaEmprestimos() {
		htmlEmprestimosTipoTodos = controlador.getEmprestimosTodos();
		htmlEmprestimosTipoEmprestador = controlador.getEmprestimosTipoEmprestador();
		htmlEmprestimosTipoBeneficiador = controlador.getEmprestimosTipoBeneficiador();
		setPainelEmprestimos();
	}

	public void atualizaItens() {
		htmlItens = controlador.getItens();
		setPainelItens();
	}
	
	
	
	
	
	
	
	
	
	
	
	

	class MyHandlerAmigo implements ClickHandler {
		private String nome;
		public MyHandlerAmigo(String nome) {
			this.nome = nome;
		}
		@Override
		public void onClick(ClickEvent event) {
			//TODO
		}
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
			setPainelMural();
			
		}
	}
	class MyCommandMostraPerfil implements Command {
		@Override
		public void execute() {
			controlador.atualizaNome();
			setPainelPerfil();
		}
	}
	class MyCommandMostraAmigos implements Command {
		@Override
		public void execute() {
			controlador.atualizaAmigos();
			setPainelAmigos();
		}
	}
	class MyCommandMostraEmprestimos implements Command {
		@Override
		public void execute() {
			controlador.atualizaEmprestimos();
			setPainelEmprestimos();
		}
	}
	class MyCommandMostraItens implements Command {
		@Override
		public void execute() {
			controlador.atualizaItens();
			setPainelItens();
		}
	}
}
