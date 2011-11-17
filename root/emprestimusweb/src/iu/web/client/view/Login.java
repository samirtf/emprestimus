package iu.web.client.view;

import iu.web.client.GreetingService;
import iu.web.client.GreetingServiceAsync;
import iu.web.shared.VerificadorDeCampos;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public class Login extends Composite {
	
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	private Label lblErro;
	private TextBox txtLoginNick;
	private PasswordTextBox ptbLoginSenha;
	private Button btnLogin;

	public Login() {
		
		AbsolutePanel painelLogin = new AbsolutePanel();
		initWidget(painelLogin);
		painelLogin.setSize("854px", "409px");
		
		Label lblLoginNick = new Label("Nick:");
		painelLogin.add(lblLoginNick, 508, 10);
		
		Label lblLoginSenha = new Label("Senha:");
		painelLogin.add(lblLoginSenha, 687, 12);
		
		txtLoginNick = new TextBox();
		painelLogin.add(txtLoginNick, 508, 34);
		
		ptbLoginSenha = new PasswordTextBox();
		painelLogin.add(ptbLoginSenha, 687, 36);
		
		btnLogin = new Button("Login");
		painelLogin.add(btnLogin, 797, 74);
		
		Label lblNovoPorAqui = new Label("Novo por aqui? Faça um cadastro:");
		lblNovoPorAqui.setStyleName("nathaniel1");
		painelLogin.add(lblNovoPorAqui, 480, 135);
		
		Label lblNomeCompleto = new Label("Nome Completo:");
		painelLogin.add(lblNomeCompleto, 482, 190);
		
		Label lblNick = new Label("Nick:");
		painelLogin.add(lblNick, 549, 230);
		
		Label lblEndereo = new Label("Endereço:");
		painelLogin.add(lblEndereo, 520, 270);
		
		Label lblSenha = new Label("Senha:");
		painelLogin.add(lblSenha, 538, 308);
		
		Label lblRepitaASenha = new Label("Repita a senha:");
		painelLogin.add(lblRepitaASenha, 488, 346);
		
		TextBox txtNomeCompleto = new TextBox();
		painelLogin.add(txtNomeCompleto, 585, 174);
		txtNomeCompleto.setSize("249px", "18px");
		
		TextBox txtNick = new TextBox();
		painelLogin.add(txtNick, 585, 214);
		txtNick.setSize("249px", "18px");
		
		TextBox txtEndereco = new TextBox();
		painelLogin.add(txtEndereco, 585, 254);
		txtEndereco.setSize("249px", "18px");
		
		PasswordTextBox ptbSenha = new PasswordTextBox();
		painelLogin.add(ptbSenha, 585, 294);
		ptbSenha.setSize("249px", "16px");
		
		PasswordTextBox ptbSenha2 = new PasswordTextBox();
		painelLogin.add(ptbSenha2, 585, 332);
		ptbSenha2.setSize("249px", "16px");
		
		Button btnCadastrar = new Button("Cadastrar");
		painelLogin.add(btnCadastrar, 772, 366);
		
		Image image = new Image("emprestimusweb/gwt/clean/images/TAI.jpg");
		painelLogin.add(image, 118, 0);
		image.setSize("199px", "403px");
		
		lblErro = new Label("");
		lblErro.setStyleName("serverResponseLabelError");
		painelLogin.add(lblErro, 508, 74);
		
		MyHandler handler = new MyHandler();
		btnLogin.addClickHandler(handler);
		txtLoginNick.addKeyUpHandler(handler);
		ptbLoginSenha.addKeyUpHandler(handler);
	}
	
	// Create a handler for the sendButton and nameField
	class MyHandler implements ClickHandler, KeyUpHandler {
		public void onClick(ClickEvent event) {
			enviaAoServidor();
			
		}
		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				if ((!txtLoginNick.getText().trim().equals(""))&&(!ptbLoginSenha.getText().trim().equals(""))) {
					enviaAoServidor();
				}
			}
		}
		private void enviaAoServidor() {
			lblErro.setText("");
			String nomeParaServidor = txtLoginNick.getText();
			String senhaParaServidor = ptbLoginSenha.getText();
			if (!VerificadorDeCampos.ehNomeValido(nomeParaServidor)) {
				lblErro.setText("Nome inválido");
				return;
			} else if (!VerificadorDeCampos.ehSenhaValida(senhaParaServidor)) {
				lblErro.setText("Senha inválida");
				return;
			}

			btnLogin.setEnabled(false);
			txtLoginNick.setEnabled(false);
			ptbLoginSenha.setEnabled(false);
			greetingService.greetServer(nomeParaServidor+"|"+senhaParaServidor, new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {
					lblErro.setText("Falha na conexão!");
				}

				public void onSuccess(String result) {
					lblErro.setText("Sucesso: " + result);
				}
			});
		}
	}
}
