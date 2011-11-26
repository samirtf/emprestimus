package iu.web.client.view;

import iu.web.client.Controlador;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.InlineHyperlink;


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
	private Image image_4;

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
		
		Image image = new Image(IMAGEM_LOGO);
		painelSuperior.add(image, 10, 10);
		image.setSize("311px", "105px");
		
		HTML htmlSair = new HTML("<a href = #> Sair </a>", true);
		painelSuperior.add(htmlSair, 867, 67);
		
		Image image_1 = new Image("emprestimusweb/imagens/05.png");
		painelSuperior.add(image_1, 651, 63);
		image_1.setSize("33px", "22px");
		
		Image image_2 = new Image("emprestimusweb/imagens/06.png");
		painelSuperior.add(image_2, 545, 59);
		image_2.setSize("33px", "26");
		
		Image image_3 = new Image("emprestimusweb/imagens/09.png");
		painelSuperior.add(image_3, 447, 59);
		image_3.setSize("30", "26px");
		
	}
	
	private void inicializaPainelLateral() {
		painelLateral = new AbsolutePanel();
		painelGlobal.addWest(painelLateral, 16.1);
		
		foto = FOTO_DEFAULT;
		painelLateral.add(foto, 28, 10);
		foto.setSize("155px", "155px");
		
		lnkMural = new InlineHyperlink("Mural", false, "newHistoryToken");
		lnkMural.setHTML("Mural");
		painelLateral.add(lnkMural, 46, 207);
		
		lnkPerfil = new InlineHyperlink("Perfil", false, "newHistoryToken");
		painelLateral.add(lnkPerfil, 46, 231);
		
		lnkMensagens = new InlineHyperlink("Mensagens", false, "newHistoryToken");
		painelLateral.add(lnkMensagens, 46, 255);
		
		lnkItens = new InlineHyperlink("Itens", false, "newHistoryToken");
		painelLateral.add(lnkItens, 46, 303);
		
		lnkAmigos = new InlineHyperlink("Amigos", false, "newHistoryToken");
		painelLateral.add(lnkAmigos, 46, 279);
		
		HTML htmlMudarImagem = new HTML("<a href = #> Mudar Imagem </a>", true);
		painelLateral.add(htmlMudarImagem, 57, 171);
		htmlMudarImagem.setHeight("");
		
		image_4 = new Image((String) null);
		painelLateral.add(image_4, -30, 151);
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
