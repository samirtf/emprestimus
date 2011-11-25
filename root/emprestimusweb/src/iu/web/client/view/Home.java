package iu.web.client.view;

import iu.web.client.Controlador;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.HTML;

/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public class Home extends Composite {
	private Controlador controlador;
	private DockLayoutPanel painelGlobal;
	private AbsolutePanel painelSuperior;
	private AbsolutePanel painelLateral;
	private AbsolutePanel painelInferior;
	private Label lblEmprestimus;
	private Hyperlink lnkSair;
	private InlineHyperlink lnkMural;
	private Image imagem;
	private InlineHyperlink lnkPerfil;
	private InlineHyperlink lnkMensagens;
	private InlineHyperlink lnkItens;
	private InlineHyperlink lnkAmigos;
	private HTML html;

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

		lblEmprestimus = new Label("Emprestimus");
		lblEmprestimus.setStyleName("nathaniel2");
		painelSuperior.add(lblEmprestimus, 41, 29);
		
		lnkSair = new Hyperlink("Sair", false, "newHistoryToken");
		painelSuperior.add(lnkSair, 867, 10);
		
	}
	
	private void inicializaPainelLateral() {
		painelLateral = new AbsolutePanel();
		painelGlobal.addWest(painelLateral, 16.1);
		
		imagem = new Image("emprestimusweb/gwt/clean/imagens/avril.jpg");
		painelLateral.add(imagem, 10, 10);
		imagem.setSize("155px", "155px");
		
		lnkMural = new InlineHyperlink("Mural", false, "newHistoryToken");
		lnkMural.setHTML("Mural");
		painelLateral.add(lnkMural, 41, 197);
		
		lnkPerfil = new InlineHyperlink("Perfil", false, "newHistoryToken");
		painelLateral.add(lnkPerfil, 41, 221);
		
		lnkMensagens = new InlineHyperlink("Mensagens", false, "newHistoryToken");
		painelLateral.add(lnkMensagens, 41, 245);
		
		lnkItens = new InlineHyperlink("Itens", false, "newHistoryToken");
		painelLateral.add(lnkItens, 41, 269);
		
		lnkAmigos = new InlineHyperlink("Amigos", false, "newHistoryToken");
		painelLateral.add(lnkAmigos, 41, 293);
	}

	private void inicializaPainelInferior() {
		painelInferior = new AbsolutePanel();
		painelGlobal.addSouth(painelInferior, 3.6);
		
	}
	
	private void inicializaHtmlCentral() {
		html = new HTML("<h2>"+controlador.getNome()+"</h2>"
				+controlador.getHistorico(), true);
		painelGlobal.add(html);
		
	}
}
