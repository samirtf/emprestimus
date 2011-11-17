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
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.HasVerticalAlignment;

/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public class Login extends Composite {
	
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	private Grid grade;
	private Label labelErro;
	private Label labelNome;
	private Label labelSenha;
	private TextBox campoNome;
	private PasswordTextBox campoSenha;
	private Button botaoLogin;
	
	public Login() {
		
		grade = new Grid(4, 2);
		initWidget(grade);
		grade.setSize("306px", "186px");
		
		labelErro = new Label("");
		labelErro.setStyleName("serverResponseLabelError");
		grade.setWidget(0, 1, labelErro);
		
		labelNome = new Label("Nome:");
		labelNome.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		grade.setWidget(1, 0, labelNome);
		
		campoNome = new TextBox();
		grade.setWidget(1, 1, campoNome);
		campoNome.setWidth("100%");
		
		labelSenha = new Label("Senha:");
		labelSenha.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
		grade.setWidget(2, 0, labelSenha);
		
		campoSenha = new PasswordTextBox();
		grade.setWidget(2, 1, campoSenha);
		campoSenha.setWidth("100%");
		
		botaoLogin = new Button("Login");
		grade.setWidget(3, 1, botaoLogin);
		grade.getCellFormatter().setVerticalAlignment(1, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		grade.getCellFormatter().setVerticalAlignment(2, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		grade.getCellFormatter().setVerticalAlignment(3, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		grade.getCellFormatter().setVerticalAlignment(2, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		grade.getCellFormatter().setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		grade.getCellFormatter().setVerticalAlignment(1, 0, HasVerticalAlignment.ALIGN_MIDDLE);
		grade.getCellFormatter().setHorizontalAlignment(1, 0, HasHorizontalAlignment.ALIGN_RIGHT);
		grade.getCellFormatter().setVerticalAlignment(0, 1, HasVerticalAlignment.ALIGN_MIDDLE);
		grade.getCellFormatter().setHorizontalAlignment(0, 1, HasHorizontalAlignment.ALIGN_LEFT);
		grade.getCellFormatter().setHorizontalAlignment(3, 1, HasHorizontalAlignment.ALIGN_RIGHT);
		
		MyHandler handler = new MyHandler();
		botaoLogin.addClickHandler(handler);
		campoNome.addKeyUpHandler(handler);
		campoSenha.addKeyUpHandler(handler);
	}
	
	// Create a handler for the sendButton and nameField
	class MyHandler implements ClickHandler, KeyUpHandler {
		public void onClick(ClickEvent event) {
			enviaAoServidor();
			
		}
		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				if ((!campoNome.getText().trim().equals(""))&&(!campoSenha.getText().trim().equals(""))) {
					enviaAoServidor();
				}
			}
		}
		private void enviaAoServidor() {
			labelErro.setText("");
			String nomeParaServidor = campoNome.getText();
			String senhaParaServidor = campoSenha.getText();
			if (!VerificadorDeCampos.ehNomeValido(nomeParaServidor)) {
				labelErro.setText("Nome inválido");
				return;
			} else if (!VerificadorDeCampos.ehSenhaValida(senhaParaServidor)) {
				labelErro.setText("Senha inválida");
				return;
			}

			botaoLogin.setEnabled(false);
			greetingService.greetServer(nomeParaServidor+"|"+senhaParaServidor, new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {
					labelErro.setText("Falha na conexão!");
				}

				public void onSuccess(String result) {
					labelErro.setText("Sucesso: " + result);
				}
			});
		}
	}
}
