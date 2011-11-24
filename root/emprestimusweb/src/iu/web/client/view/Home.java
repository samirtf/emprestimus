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
	
	private DockLayoutPanel dockLayoutPanel;
	private AbsolutePanel absolutePanel;
	private Label lblEmprestimus;
	private Hyperlink hprlnkSair;
	private AbsolutePanel absolutePanel_1;
	private InlineHyperlink nlnhprlnkMensagens;
	private Image image;
	private InlineHyperlink nlnhprlnkPerfil;
	private InlineHyperlink nlnhprlnkMensagens_1;
	private InlineHyperlink nlnhprlnkItens;
	private InlineHyperlink nlnhprlnkAmigos;
	private AbsolutePanel absolutePanel_2;
	private HTML htmlemprestimusBemVindo;

	public Home(Controlador controlador) {
		this.controlador = controlador;
		
		dockLayoutPanel = new DockLayoutPanel(Unit.EM);
		initWidget(dockLayoutPanel);
		dockLayoutPanel.setSize("900px", "600px");
		
		absolutePanel = new AbsolutePanel();
		dockLayoutPanel.addNorth(absolutePanel, 9.1);
		
		lblEmprestimus = new Label("Emprestimus");
		lblEmprestimus.setStyleName("nathaniel2");
		absolutePanel.add(lblEmprestimus, 41, 29);
		
		hprlnkSair = new Hyperlink("Sair", false, "newHistoryToken");
		absolutePanel.add(hprlnkSair, 844, 10);
		
		absolutePanel_1 = new AbsolutePanel();
		dockLayoutPanel.addWest(absolutePanel_1, 16.1);
		
		nlnhprlnkMensagens = new InlineHyperlink("Mensagens", false, "newHistoryToken");
		nlnhprlnkMensagens.setHTML("Mural");
		absolutePanel_1.add(nlnhprlnkMensagens, 41, 197);
		
		image = new Image("emprestimusweb/gwt/clean/imagens/avril.jpg");
		absolutePanel_1.add(image, 10, 10);
		image.setSize("155px", "155px");
		
		nlnhprlnkPerfil = new InlineHyperlink("Perfil", false, "newHistoryToken");
		absolutePanel_1.add(nlnhprlnkPerfil, 41, 221);
		
		nlnhprlnkMensagens_1 = new InlineHyperlink("Mensagens", false, "newHistoryToken");
		absolutePanel_1.add(nlnhprlnkMensagens_1, 41, 245);
		
		nlnhprlnkItens = new InlineHyperlink("Itens", false, "newHistoryToken");
		absolutePanel_1.add(nlnhprlnkItens, 41, 269);
		
		nlnhprlnkAmigos = new InlineHyperlink("Amigos", false, "newHistoryToken");
		absolutePanel_1.add(nlnhprlnkAmigos, 41, 293);
		
		absolutePanel_2 = new AbsolutePanel();
		dockLayoutPanel.addSouth(absolutePanel_2, 3.6);
		
		htmlemprestimusBemVindo = new HTML("<h1>Emprestimus</h1>\r\n\r\nBem vindo ao Emprestimus!<br>\r\nConvide seus amigos!!<br><br><br><br>\r\n\r\n<h2>=]</h2>", true);
		dockLayoutPanel.add(htmlemprestimusBemVindo);
	}
}
