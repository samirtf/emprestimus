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
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.TextBox;


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
	private Image foto;
	private HTML htmlCentral;
	private AbsolutePanel painelCentral;
	
	private PainelAmigo painel;
	
	private Image imgSolicitacoes;
	private Image imgRequisicoes;
	private Image imgNovaMensagem;

	private String htmlMural = "Carregando...";
	private String htmlPerfil = "Carregando...";
	private String htmlMensagens = "Carregando...";
	private String htmlAmigos = "Carregando...";
	private String htmlItens = "Carregando...";
	private String htmlEmprestimosTipoEmprestador = "Carregando...";
	private String htmlEmprestimosTipoBeneficiador = "Carregando...";
	private String htmlEmprestimosTipoTodos = "Carregando...";

	public Home(Controlador controlador) {
		this.controlador = controlador;
		
		painelGlobal = new DockLayoutPanel(Unit.EM);
		initWidget(painelGlobal);
		painelGlobal.setSize("900px", "1000px");
		
		inicializaPainelSuperior();
		inicializaPainelLateral();
		inicializaHtmlCentral();
		
	}
	
	private void inicializaPainelSuperior() {
		painelSuperior = new AbsolutePanel();
		painelGlobal.addNorth(painelSuperior, 9.1);
		
		Image imgEmprestimus = new Image(Login.IMAGEM_Emprestimus.getUrl());
		painelSuperior.add(imgEmprestimus, 10, 10);
		imgEmprestimus.setSize("311px", "105px");
		imgEmprestimus.addClickHandler(new MyHandlerHome());
		
		imgSolicitacoes = new Image("emprestimusweb/imagens/09.png");
		painelSuperior.add(imgSolicitacoes, 447, 59);
		imgSolicitacoes.setSize("30", "26px");
		
		imgRequisicoes = new Image("emprestimusweb/imagens/06.png");
		painelSuperior.add(imgRequisicoes, 545, 59);
		imgRequisicoes.setSize("33px", "26");
		
		imgNovaMensagem = new Image("emprestimusweb/imagens/05.png");
		painelSuperior.add(imgNovaMensagem, 651, 63);
		imgNovaMensagem.setSize("33px", "22px");
		
		Hyperlink hprlnkSair = new Hyperlink("Sair", false, "newHistoryToken");
		hprlnkSair.addClickHandler(new MyHandlerSair());
		painelSuperior.add(hprlnkSair, 867, 10);
		
		Hyperlink hprlnkNumSolicitacoesAmizade = new Hyperlink("", false, "newHistoryToken");
		hprlnkNumSolicitacoesAmizade.setStyleName("nathaniel1");
		painelSuperior.add(hprlnkNumSolicitacoesAmizade, 477, 59);
		
		Hyperlink hprlnkNumRequisicoesItens = new Hyperlink("", false, "newHistoryToken");
		hprlnkNumRequisicoesItens.setStyleName("nathaniel1");
		painelSuperior.add(hprlnkNumRequisicoesItens, 584, 59);
		
		Hyperlink hprlnkNumNovasMensagens = new Hyperlink("", false, "newHistoryToken");
		hprlnkNumNovasMensagens.setStyleName("nathaniel1");
		painelSuperior.add(hprlnkNumNovasMensagens, 690, 59);
		
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
		
		MenuItem mntmMensagens = new MenuItem("Mensagens", false, new MyCommandMostraMensagens());
		mntmMensagens.setHTML("<menuItem>Mensagens</menuItem>");
		menuBar.addItem(mntmMensagens);
		
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
		
		
		setPainelMural(); //FIXME: lemrar de descomentar isso!
	}
	
	private void setPainelMural() {
		painelCentral.clear();
		
		AbsolutePanel painelMural = new AbsolutePanel();
		painelCentral.add(painelMural, 0, 0);
		painelMural.setSize("691px", "108px");
		
		Label lblMuralDeAtualizaes = new Label("Mural de Atualizações");
		lblMuralDeAtualizaes.setStyleName("nathaniel2");
		painelMural.add(lblMuralDeAtualizaes, 10, 25);
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
	
	private void setPainelMensagens() {
		AbsolutePanel absolutePanel = new AbsolutePanel();
		painelCentral.add(absolutePanel, 0, 0);
		absolutePanel.setSize("691px", "108px");
		
		Label lblFiltrar = new Label("Filtrar:");
		absolutePanel.add(lblFiltrar, 22, 41);
		
		ListBox comboBoxFiltro = new ListBox();
		comboBoxFiltro.addItem("Todas as Mensagens");
		comboBoxFiltro.addItem("Mensagens de Negociação");
		comboBoxFiltro.addItem("Mensagens Off-Topic");
		comboBoxFiltro.setSelectedIndex(0);
		absolutePanel.add(comboBoxFiltro, 65, 41);
		comboBoxFiltro.setSize("236px", "22px");
		
		Button btnNovaMensagem = new Button("Nova mensagem");
		absolutePanel.add(btnNovaMensagem, 378, 36);
		
		htmlCentral.setHTML(htmlMensagens);
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
	
	public void atualizaMensagens() {
		htmlMensagens = controlador.getMensagens();
		setPainelMensagens();
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
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	class MyHandlerHome implements ClickHandler {
		@Override
		public void onClick(ClickEvent event) {
			painel.setVisible(true);
			painel.removeFromParent();
			
			painelGlobal.setHeight("1000px");
			painelLateral.setVisible(true);
			painelCentral.setVisible(true);
			htmlCentral.setVisible(true);
		}
	}

	class MyHandlerAmigo implements ClickHandler {
		private String nome;
		public MyHandlerAmigo(String nome) {
			this.nome = nome;
		}
		@Override
		public void onClick(ClickEvent event) {
			painelLateral.setVisible(false);
			painelCentral.setVisible(false);
			htmlCentral.setVisible(false);
			painelGlobal.setHeight("118px");
			
			painel = new PainelAmigo(controlador, nome);
			painel.setVisible(true);
			RootPanel.get("centro").add(painel);
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
	class MyCommandMostraMensagens implements Command {
		@Override
		public void execute() {
			controlador.atualizaMensagens();
			setPainelMensagens();
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
