package iu.web.client.view;

import iu.web.client.Controlador;
import iu.web.shared.MensagensWeb;
import iu.web.shared.VerificadorDeCampos;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.PushButton;

/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public class Home extends Composite {
	private static final String IMAGEM_LOGO = "emprestimusweb/logo2.png";

	private static final Image FOTO_DEFAULT = new Image("emprestimusweb/imagens/defaultFoto.jpg");
	
	private Controlador controlador;
	private DockLayoutPanel painelGlobal;
	private AbsolutePanel painelSuperior;
	private AbsolutePanel painelLateral;
	private AbsolutePanel painelInferior;
	private InlineHyperlink lnkMural;
	private Image foto;
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
		
		Image image = new Image("emprestimusweb/imagens/logo2.png");
		painelSuperior.add(image, 10, 10);
		image.setSize("311px", "105px");
		
		HTML htmlSair = new HTML("<a href = #> Sair </a>", true);
		painelSuperior.add(htmlSair, 867, 10);
		
		Image image_1 = new Image((String) null);
		painelSuperior.add(image_1, 515, 18);
		image_1.setSize("100px", "100px");
		
	}
	
	private void inicializaPainelLateral() {
		painelLateral = new AbsolutePanel();
		painelGlobal.addWest(painelLateral, 16.1);
		
		Image image = new Image("emprestimusweb/imagens/vertical.png");
		painelLateral.add(image, 204, 0);
		image.setSize("5px", "390px");
		
		foto = FOTO_DEFAULT;
		painelLateral.add(foto, 28, 10);
		foto.setSize("155px", "155px");
		
		lnkMural = new InlineHyperlink("Mural", false, "newHistoryToken");
		lnkMural.setHTML("Mural");
		painelLateral.add(lnkMural, 41, 197);
		
		lnkPerfil = new InlineHyperlink("Perfil", false, "newHistoryToken");
		painelLateral.add(lnkPerfil, 41, 221);
		
		lnkMensagens = new InlineHyperlink("Mensagens", false, "newHistoryToken");
		painelLateral.add(lnkMensagens, 41, 245);
		
		lnkItens = new InlineHyperlink("Itens", false, "newHistoryToken");
		painelLateral.add(lnkItens, 41, 293);
		
		lnkAmigos = new InlineHyperlink("Amigos", false, "newHistoryToken");
		painelLateral.add(lnkAmigos, 41, 269);
	}

	private void inicializaPainelInferior() {
		painelInferior = new AbsolutePanel();
		painelGlobal.addSouth(painelInferior, 3.6);
		
	}
	
	private void inicializaHtmlCentral() {
		html = new HTML("Carregando...", true);
		painelGlobal.add(html);
		
	}
	
	public void inicializaAtributosDoUsiario() {
		String caminhoFoto = controlador.getFoto();
		if (caminhoFoto == null) {
			foto = FOTO_DEFAULT;
		} else {
			foto = new Image(caminhoFoto);
		}
		
		html.setHTML("<h2>Bemvindo, "+controlador.getNome()+"</h2><br>Histórico de atualizações:<br>"
				+controlador.getHistorico());
	}
}
