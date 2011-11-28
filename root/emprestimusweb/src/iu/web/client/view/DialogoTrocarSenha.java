package iu.web.client.view;

import iu.web.client.Controlador;
import iu.web.shared.MensagensWeb;
import iu.web.shared.VerificadorDeCampos;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;

/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public class DialogoTrocarSenha extends DialogBox {
	private Controlador controlador;
	
	private PasswordTextBox ptbNovaSenha;
	private PasswordTextBox ptbNovaSenha2;
	private Button btnOk;
	private Button btnCancela;
	private Label lblErro;

	public DialogoTrocarSenha(Controlador controlador) {
		setPreviewingAllNativeEvents(true);
		setAnimationEnabled(true);
		setGlassEnabled(true);
		this.controlador = controlador;
		
		setHTML("Mudar a Senha");
		
		AbsolutePanel absolutePanel = new AbsolutePanel();
		setWidget(absolutePanel);
		absolutePanel.setSize("534px", "427px");
		
		Label lblNovaSenha = new Label("Nova senha:");
		absolutePanel.add(lblNovaSenha, 170, 96);
		
		ptbNovaSenha = new PasswordTextBox();
		absolutePanel.add(ptbNovaSenha, 170, 113);
		
		Label lblRepitaASenha = new Label("Repita a senha:");
		absolutePanel.add(lblRepitaASenha, 170, 152);
		
		ptbNovaSenha2 = new PasswordTextBox();
		absolutePanel.add(ptbNovaSenha2, 170, 176);
		
		btnOk = new Button("OK");
		absolutePanel.add(btnOk, 232, 251);
		
		btnCancela = new Button("Cancela");
		absolutePanel.add(btnCancela, 288, 251);
		
		lblErro = new Label();
		lblErro.setStyleName("serverResponseLabelError");
		absolutePanel.add(lblErro, 170, 214);
		
		MyHandlerCancela handlerCancela = new MyHandlerCancela();
		btnCancela.addClickHandler(handlerCancela);
		btnCancela.addKeyUpHandler(handlerCancela);
		
		MyHandlerOK handlerOK = new MyHandlerOK();
		btnOk.addClickHandler(handlerOK);
		btnOk.addKeyUpHandler(handlerOK);
		ptbNovaSenha.addKeyUpHandler(handlerOK);
		ptbNovaSenha2.addKeyUpHandler(handlerOK);
		
	}
	
	class MyHandlerOK implements ClickHandler, KeyUpHandler {
		String senha = ptbNovaSenha.getText();
		String senha2 = ptbNovaSenha2.getText();

		@Override
		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				if (!senha.equals(senha2)) {
					ptbNovaSenha.selectAll();
					ptbNovaSenha.setFocus(true);
					lblErro.setText("As senhas devem ser iguais");
					return;
				} else if (!VerificadorDeCampos.ehSenhaValida(senha)) {
					ptbNovaSenha.selectAll();
					ptbNovaSenha.setFocus(true);
					lblErro.setText(MensagensWeb.SENHA_CURTA.getMensagem());
				} else {
					enviaAoServidor();
				}
			}
		}
		
		@Override
		public void onClick(ClickEvent event) {
			if (!senha.equals(senha2)) {
				ptbNovaSenha.selectAll();
				ptbNovaSenha.setFocus(true);
				lblErro.setText("As senhas devem ser iguais");
				return;
			} else if (!VerificadorDeCampos.ehSenhaValida(senha)) {
				ptbNovaSenha.selectAll();
				ptbNovaSenha.setFocus(true);
				lblErro.setText(MensagensWeb.SENHA_CURTA.getMensagem());
			} else {
				enviaAoServidor();
			}
		}
		
		private void enviaAoServidor() {
			controlador.trocaSenha(senha);
			setVisible(false);
			removeFromParent();
		}
	}
	
	class MyHandlerCancela implements ClickHandler, KeyUpHandler {
		@Override
		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				setVisible(false);
				removeFromParent();
			}
		}
		@Override
		public void onClick(ClickEvent event) {
			setVisible(false);
			removeFromParent();
		}
	}
}


