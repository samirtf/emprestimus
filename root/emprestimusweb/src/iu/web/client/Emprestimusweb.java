package iu.web.client;

import iu.web.client.view.Login;
import iu.web.shared.VerificadorDeCampos;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Emprestimusweb implements EntryPoint {
//	/**
//	 * The message displayed to the user when the server cannot be reached or
//	 * returns an error.
//	 */
//	private static final String SERVER_ERROR = "An error occurred while "
//			+ "attempting to contact the server. Please check your network "
//			+ "connection and try again.";
//
//	/**
//	 * Create a remote service proxy to talk to the server-side Greeting service.
//	 */
//	private final GreetingServiceAsync greetingService = GWT
//			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		Login compositeLogin = new Login();
		compositeLogin.setVisible(true);
		RootPanel.get("nameFieldContainer").add(compositeLogin);
		
//		final Button botaoEnviar = new Button("Enviar");
//		final TextBox campoNome = new TextBox();
//		campoNome.setText("Nome");
//		final TextBox campoSenha = new PasswordTextBox();
//		campoSenha.setText("Senha");
//		final Label etiquetaErro = new Label();
//
//		// We can add style names to widgets
//		botaoEnviar.addStyleName("botaoEnviar");
//
//		// Add the nameField and sendButton to the RootPanel
//		// Use RootPanel.get() to get the entire body element
//		RootPanel.get("nameFieldContainer").add(campoNome);
//		RootPanel.get("nameFieldContainer").add(campoSenha);
//		RootPanel.get("sendButtonContainer").add(botaoEnviar);
//		RootPanel.get("errorLabelContainer").add(etiquetaErro);
//
//		// Focus the cursor on the name field when the app loads
//		campoNome.setFocus(true);
//		campoNome.selectAll();
//
//		// Create the popup dialog box
//		final DialogBox caixaDeDialogo = new DialogBox();
//		caixaDeDialogo.setText("Chamada de Procedimento Remoto");
//		caixaDeDialogo.setAnimationEnabled(true);
//		final Button botaoFechar = new Button("Fechar");
//		// We can set the id of a widget by accessing its Element
//		botaoFechar.getElement().setId("botaoFechar");
//		final Label etiquetaTextoParaServidor = new Label();
//		final HTML htmlRespostaDoServidor = new HTML();
//		VerticalPanel etiquetaPainelVertical = new VerticalPanel();
//		etiquetaPainelVertical.addStyleName("dialogVPanel");
//		etiquetaPainelVertical.add(new HTML("<b>Sending name to the server:</b>"));
//		etiquetaPainelVertical.add(etiquetaTextoParaServidor);
//		etiquetaPainelVertical.add(new HTML("<br><b>Server replies:</b>"));
//		etiquetaPainelVertical.add(htmlRespostaDoServidor);
//		etiquetaPainelVertical.setHorizontalAlignment(VerticalPanel.ALIGN_RIGHT);
//		etiquetaPainelVertical.add(botaoFechar);
//		caixaDeDialogo.setWidget(etiquetaPainelVertical);
//
//		// Add a handler to close the DialogBox
//		botaoFechar.addClickHandler(new ClickHandler() {
//			public void onClick(ClickEvent event) {
//				caixaDeDialogo.hide();
//				botaoEnviar.setEnabled(true);
//				botaoEnviar.setFocus(true);
//			}
//		});
//
//		// Create a handler for the sendButton and nameField
//		class MyHandler implements ClickHandler, KeyUpHandler {
//			/**
//			 * Fired when the user clicks on the sendButton.
//			 */
//			public void onClick(ClickEvent event) {
//				enviaAoServidor();
//			}
//
//			/**
//			 * Fired when the user types in the nameField.
//			 */
//			public void onKeyUp(KeyUpEvent event) {
//				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
//					enviaAoServidor();
//				}
//			}
//
//			/**
//			 * Send the name from the nameField to the server and wait for a response.
//			 */
//			private void enviaAoServidor() {
//				// First, we validate the input.
//				etiquetaErro.setText("");
//				String nomeParaServidor = campoNome.getText();
//				String senhaParaServidor = campoSenha.getText();
//				if (!VerificadorDeCampos.ehNomeValido(nomeParaServidor)) {
//					etiquetaErro.setText("Nome invá" +
//							"" +
//							"" +
//							"" +
//							"" +
//							"" +
//							"" +
//							"lido");
//					return;
//				} else if (!VerificadorDeCampos.ehSenhaValida(senhaParaServidor)) {
//					etiquetaErro.setText("Senha inválida");
//					return;
//				}
//
//				// Then, we send the input to the server.
//				botaoEnviar.setEnabled(false);
//				etiquetaTextoParaServidor.setText(nomeParaServidor+"\n"+senhaParaServidor);
//				htmlRespostaDoServidor.setText("");
//				greetingService.greetServer(nomeParaServidor+"|"+senhaParaServidor, new AsyncCallback<String>() {
//					public void onFailure(Throwable caught) {
//						// Show the RPC error message to the user
//						caixaDeDialogo.setText("Chamada de Procedimento Remoto - Falha");
//						htmlRespostaDoServidor.addStyleName("etiquetaRespostaDoServidorErro");
//						htmlRespostaDoServidor.setHTML(SERVER_ERROR+"\n\n"+caught.getMessage());
//						caixaDeDialogo.center();
//						botaoFechar.setFocus(true);
//					}
//
//					public void onSuccess(String result) {
//						System.out.println("uga uga");
//						caixaDeDialogo.setText("Chamada de Procedimento Remoto");
//						htmlRespostaDoServidor.removeStyleName("etiquetaRespostaDoServidorErro");
//						htmlRespostaDoServidor.setHTML(result);
//						caixaDeDialogo.center();
//						botaoFechar.setFocus(true);
//					}
//				});
//			}
//		}
//
//		// Add a handler to send the name to the server
//		MyHandler handler = new MyHandler();
//		botaoEnviar.addClickHandler(handler);
//		campoNome.addKeyUpHandler(handler);
//		campoSenha.addKeyUpHandler(handler);
	}
}
