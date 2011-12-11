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
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PushButton;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.LongBox;
import com.google.gwt.user.client.ui.TextArea;


public class CadastroItem extends Composite {
	private static final String fotoPerfilDefault = Imagem.PERFIL_DEFAULT.getEndereco();
	
	private Controlador controlador;
	private DockLayoutPanel painelGlobal;
	private AbsolutePanel painelSuperior;
	private AbsolutePanel painelLateral;
	private AbsolutePanel painelInferior;
	private HTML htmlCentral;
	private AbsolutePanel painelCentral;
	
	private Image imgSolicitacoes;
	private Image imgRequisicoes;
	private Image imgNovaMensagem;

	private String htmlMural = "Carregando...";
	private String htmlPerfil = "Carregando...";
	private String htmlMensagens = "Carregando...";
	private String htmlAmigos = "Carregando...";
	private String htmlItens = "Carregando...";
	private String htmlEmprestimos = "Carregando...";

	public CadastroItem(Controlador controlador) {
		this.controlador = controlador;
		
		painelGlobal = new DockLayoutPanel(Unit.EM);
		initWidget(painelGlobal);
		painelGlobal.setSize("900px", "522px");
		
		inicializaPainelSuperior();
		inicializaHtmlCentral();
		
	}
	
	private void inicializaPainelSuperior() {
		painelSuperior = new AbsolutePanel();
		painelGlobal.addNorth(painelSuperior, 9.1);
		
		Image imgEmprestimus = new Image(Login.IMAGEM_Emprestimus.getUrl());
		painelSuperior.add(imgEmprestimus, 10, 10);
		imgEmprestimus.setSize("311px", "105px");
		
		imgSolicitacoes = new Image(Imagem.SOLICITACOES.getEndereco());
		painelSuperior.add(imgSolicitacoes, 447, 59);
		imgSolicitacoes.setSize("30", "26px");
		
		imgRequisicoes = new Image(Imagem.REQUISITACOES.getEndereco());
		painelSuperior.add(imgRequisicoes, 545, 59);
		imgRequisicoes.setSize("33px", "26");
		
		imgNovaMensagem = new Image(Imagem.NEW.getEndereco());
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
	
		
	private void inicializaHtmlCentral() {
		painelCentral = new AbsolutePanel();
		
		htmlCentral = new HTML("Carregando...", true);
		painelCentral.add(htmlCentral, 10, 114);
		htmlCentral.setSize("671px", "1000px");
		painelGlobal.add(painelCentral);
		
		
		setPainelMural(); //FIXME: lemrar de descomentar isso!
	}
	
	private void setPainelMural() {
		painelCentral.clear();
		
		AbsolutePanel painelMural = new AbsolutePanel();
		painelCentral.add(painelMural, 0, 0);
		painelMural.setSize("890px", "106px");
		
		Image image = new Image(Imagem.REQUISITACOES.getEndereco());
		painelMural.add(image, 33, 39);
		image.setSize("53px", "43px");
		
		Label label = new Label("Cadastro de Item");
		label.setStyleName("nathaniel2");
		painelMural.add(label, 128, 29);
		label.setSize("329px", "53px");
		
		painelGlobal.setHeight("1000px");
		htmlCentral.setHTML(htmlMural);
		
		Image image_1 = new Image(Imagem.BACK_ITEM.getEndereco());
		painelCentral.add(image_1, 10, 112);
		image_1.setSize("880px", "216px");
		
		HTML htmlNome = new HTML("Nome:", true);
		painelCentral.add(htmlNome, 112, 136);
		
		HTML htmlCategoria = new HTML("Categoria:", true);
		painelCentral.add(htmlCategoria, 91, 221);
		
		HTML htmlDescrio = new HTML("Descrição:", true);
		painelCentral.add(htmlDescrio, 437, 136);
		
		TextBox nome = new TextBox();
		painelCentral.add(nome, 156, 136);
		
		TextBox categoria = new TextBox();
		painelCentral.add(categoria, 156, 221);
		
		TextArea descricao = new TextArea();
		painelCentral.add(descricao, 505, 136);
		descricao.setSize("207px", "109px");
		
		Button btnCadastrar = new Button("Cadastrar");
		painelCentral.add(btnCadastrar, 650, 279);
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
		htmlCentral.setHTML(htmlEmprestimos);
	}
	
	private void setPainelItens() {
		painelCentral.clear();
		//TODO: Implemente isto!
		htmlCentral.setHTML(htmlItens);
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
			htmlAmigos += "<a href=\"" + amigo + "\">" + amigo + "</a><br>";
		}
		setPainelAmigos();
	}
	
	public void atualizaMensagens() {
		htmlMensagens = controlador.getMensagens();
		setPainelMensagens();
	}

	public void atualizaItens() {
		htmlItens = controlador.getItens();
		setPainelItens();
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
