package iu.web.client.view;

import iu.web.client.Controlador;
import iu.web.client.GreetingService;
import iu.web.client.GreetingServiceAsync;
import iu.web.shared.MensagensWeb;
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
import com.google.gwt.user.client.ui.InlineHTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Hyperlink;

/**
 * Painel de login
 * 
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 * 
 */
public class Login extends Composite {

	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	private final Image IMAGEM_Emprestimus = new Image("emprestimusweb/logo2.png");
	private Image IMAGEM1 = new Image("emprestimusweb/01.jpg");
	private Image IMAGEM2 = new Image("emprestimusweb/02.jpg");
	private Image IMAGEM3 = new Image("emprestimusweb/03.jpg");

	private AbsolutePanel painelLogin;

	// Componentes do login
	private TextBox txtLoginNick;
	private PasswordTextBox ptbLoginSenha;
	private Button btnLogin;
	private Label lblErro;

	// Componentes do cadastro
	private TextBox txtNomeCompleto;
	private TextBox txtNick;
	private TextBox txtEndereco;
	private PasswordTextBox ptbSenha;
	private PasswordTextBox ptbSenha2;
	private Button btnCadastrar;
	private Label lblErroCadastro;

	private Controlador controlador;

	public Login(Controlador controlador) {
		this.controlador = controlador;

		painelLogin = new AbsolutePanel();
		initWidget(painelLogin);
		painelLogin.setSize("877px", "543px");

		iniciaComponentesDoLogin();
		iniciaComponentesDoCadastro();
		iniciaComponentesDoLadoEsquerdo();

		// Eventos do login
		MyHandlerLogin handlerLogin = new MyHandlerLogin();
		btnLogin.addClickHandler(handlerLogin);
		btnLogin.addKeyUpHandler(handlerLogin);
		txtLoginNick.addKeyUpHandler(handlerLogin);
		ptbLoginSenha.addKeyUpHandler(handlerLogin);

		// Eventos do cadastro
		MyHandlerCadastro handlerCadastro = new MyHandlerCadastro();
		btnCadastrar.addClickHandler(handlerCadastro);
		btnCadastrar.addKeyUpHandler(handlerCadastro);
		txtNomeCompleto.addKeyUpHandler(handlerCadastro);
		txtNick.addKeyUpHandler(handlerCadastro);
		txtEndereco.addKeyUpHandler(handlerCadastro);
		ptbSenha.addKeyUpHandler(handlerCadastro);
		ptbSenha2.addKeyUpHandler(handlerCadastro);
	}

	private void iniciaComponentesDoLogin() {

		Image image = new Image("emprestimusweb/imagens/back.png");
		painelLogin.add(image, 10, 155);
		image.setSize("857px", "342px");

		Label lblLoginUsername = new Label("Username:");
		painelLogin.add(lblLoginUsername, 488, 30);

		Label lblLoginSenha = new Label("Senha:");
		painelLogin.add(lblLoginSenha, 667, 32);

		txtLoginNick = new TextBox();
		painelLogin.add(txtLoginNick, 480, 55);
		txtLoginNick.setFocus(true);

		ptbLoginSenha = new PasswordTextBox();
		painelLogin.add(ptbLoginSenha, 667, 55);

		btnLogin = new Button("Login");
		painelLogin.add(btnLogin, 777, 93);

		Hyperlink hprlnkEsqueceuASenha = new Hyperlink("Esqueceu a senha", false,
				"newHistoryToken");
		hprlnkEsqueceuASenha
				.setHTML("<a href=\"esquecimento.html\">\rEsqueceu a senha?</a> ");
		painelLogin.add(hprlnkEsqueceuASenha, 488, 95);

		lblErro = new Label("");
		lblErro.setWordWrap(false);
		lblErro.setStyleName("serverResponseLabelError");
		painelLogin.add(lblErro, 488, 119);
	}

	private void iniciaComponentesDoCadastro() {
		Label lblNovoPorAqui = new Label("Novo por aqui? Faça um cadastro:");
		lblNovoPorAqui.setStyleName("nathaniel1");
		painelLogin.add(lblNovoPorAqui, 538, 202);

		Label lblNomeCompleto = new Label("Nome:");
		painelLogin.add(lblNomeCompleto, 538, 244);

		Label lblNick = new Label("Nick:");
		painelLogin.add(lblNick, 549, 280);

		Label lblEndereo = new Label("Endereço:");
		painelLogin.add(lblEndereo, 520, 320);

		Label lblSenha = new Label("Senha:");
		painelLogin.add(lblSenha, 538, 358);

		Label lblRepitaASenha = new Label("Repita a senha:");
		painelLogin.add(lblRepitaASenha, 488, 396);

		txtNomeCompleto = new TextBox();
		painelLogin.add(txtNomeCompleto, 585, 233);
		txtNomeCompleto.setSize("249px", "18px");

		txtNick = new TextBox();
		painelLogin.add(txtNick, 585, 273);
		txtNick.setSize("249px", "18px");

		txtEndereco = new TextBox();
		painelLogin.add(txtEndereco, 585, 313);
		txtEndereco.setSize("249px", "18px");

		ptbSenha = new PasswordTextBox();
		painelLogin.add(ptbSenha, 585, 353);
		ptbSenha.setSize("249px", "16px");

		ptbSenha2 = new PasswordTextBox();
		painelLogin.add(ptbSenha2, 585, 391);
		ptbSenha2.setSize("249px", "16px");

		btnCadastrar = new Button("Cadastrar");
		painelLogin.add(btnCadastrar, 772, 425);

		lblErroCadastro = new Label("");
		lblErroCadastro.setStyleName("serverResponseLabelError");
		painelLogin.add(lblErroCadastro, 585, 464);
	}

	private void iniciaComponentesDoLadoEsquerdo() {

		Image imgEmprestimus = IMAGEM_Emprestimus;
		painelLogin.add(imgEmprestimus, 31, 10);
		imgEmprestimus.setSize("400px", "127px");

		Image imagem3 = IMAGEM3;
		painelLogin.add(imagem3, 23, 382);
		imagem3.setSize("100px", "100px");

		Image imagem2 = IMAGEM2;
		painelLogin.add(imagem2, 23, 271);
		imagem2.setSize("100px", "100px");

		Image imagem1 = IMAGEM1;
		painelLogin.add(imagem1, 23, 165);
		imagem1.setSize("100px", "100px");

		InlineHTML textoHTML = new InlineHTML(
				"<big>Pra quê comprar um novo se você pode pedir emprestado?</big><br><br>\r\nEmprestimus é a primeira rede social de empréstimos do Brasil, aqui você pode conseguir filmes, jogos, livros e muito mais...<br>\r\nConheça novas pessoas, saiba quem mora perto de você que pode lhe emprestar um ítem que você deseja. Troque mensagens com seus amigos, empreste seus ítens para aumentar sua reputação, faça Emprestimus!<br><br>\r\nCrie agora uma conta grátis!");
		painelLogin.add(textoHTML, 140, 190);
		textoHTML.setSize("291px", "279px");
	}

	/**
	 * Cria uma handler para o login
	 * 
	 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
	 * 
	 */
	class MyHandlerLogin implements ClickHandler, KeyUpHandler {
		public void onClick(ClickEvent event) {
			enviaAoServidor();

		}

		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				if ((!txtLoginNick.getText().trim().equals(""))
						&& (!ptbLoginSenha.getText().trim().equals(""))) {
					enviaAoServidor();
				}
			}
		}

		private void enviaAoServidor() {
			btnLogin.setEnabled(false);

			lblErro.setText("");
			String nick = txtLoginNick.getText();
			String senha = ptbLoginSenha.getText();
			if (!VerificadorDeCampos.ehNickValido(nick)) {
				lblErro.setText(MensagensWeb.FALHA_NA_AUTENTICACAO.getMensagem());
				btnLogin.setEnabled(true);
				txtLoginNick.selectAll();
				txtLoginNick.setFocus(true);
				return;
			} else if (!VerificadorDeCampos.ehSenhaValida(senha)) {
				lblErro.setText(MensagensWeb.FALHA_NA_AUTENTICACAO.getMensagem());
				btnLogin.setEnabled(true);
				ptbLoginSenha.selectAll();
				ptbLoginSenha.setFocus(true);
				return;
			}

			greetingService.login(nick, senha, new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {
					lblErro.setText(MensagensWeb.FALHA_NA_AUTENTICACAO.getMensagem());
					btnLogin.setEnabled(true);
					txtLoginNick.selectAll();
					txtLoginNick.setFocus(true);

				}

				public void onSuccess(String idSessao) {
					btnLogin.setEnabled(true);
					removeFromParent();
					controlador.abrirSessao(idSessao);
					Home home = new Home(controlador);
					home.setVisible(true);
				}
			});
		}
	}

	/**
	 * Cria uma handler para o cadastro de novo usuario
	 * 
	 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
	 * 
	 */
	class MyHandlerCadastro implements ClickHandler, KeyUpHandler {
		public void onClick(ClickEvent event) {
			enviaAoServidor();
		}

		public void onKeyUp(KeyUpEvent event) {
			if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
				if ((!txtNomeCompleto.getText().trim().equals(""))
						&& (!txtNick.getText().trim().equals(""))
						&& (!txtEndereco.getText().trim().equals(""))
						&& (!ptbSenha.getText().trim().equals(""))
						&& (!ptbSenha2.getText().trim().equals(""))) {
					enviaAoServidor();
				}
			}
		}

		private void enviaAoServidor() {
			btnCadastrar.setFocus(true);
			btnCadastrar.setEnabled(false);

			lblErroCadastro.setStyleName("serverResponseLabelError");
			lblErroCadastro.setText("");
			String nome = txtNomeCompleto.getText();
			String nick = txtNick.getText();
			String endereco = txtEndereco.getText();
			String senha = ptbSenha.getText();
			String senha2 = ptbSenha2.getText();

			if (!VerificadorDeCampos.ehNomeValido(nome)) {
				lblErroCadastro.setText("O nome não é válido");
				btnCadastrar.setEnabled(true);
				txtNomeCompleto.selectAll();
				txtNomeCompleto.setFocus(true);
				return;
			} else if (!VerificadorDeCampos.ehNickValido(nick)) {
				lblErroCadastro.setText("O nick não é válido");
				btnCadastrar.setEnabled(true);
				txtNick.selectAll();
				txtNick.setFocus(true);
				return;
			} else if (!VerificadorDeCampos.ehEnderecoValido(endereco)) {
				lblErroCadastro.setText("O endereço não é válido");
				btnCadastrar.setEnabled(true);
				txtEndereco.selectAll();
				txtEndereco.setFocus(true);
				return;
			} else if (!senha.equals(senha2)) {
				lblErroCadastro.setText("As senhas não são igualis");
				btnCadastrar.setEnabled(true);
				ptbSenha.selectAll();
				ptbSenha.setFocus(true);
				return;
			} else if (!VerificadorDeCampos.ehSenhaValida(senha)) {
				lblErroCadastro.setText("A senha não é válida");
				btnCadastrar.setEnabled(true);
				ptbSenha.selectAll();
				ptbSenha.setFocus(true);
				return;
			}

			greetingService.cadastra(nome, nick, endereco, senha,
					new AsyncCallback<String>() {
						public void onFailure(Throwable caught) {
							lblErroCadastro.setText(caught.getMessage());
							btnCadastrar.setEnabled(true);
							txtNomeCompleto.selectAll();
							txtNomeCompleto.setFocus(true);
						}

						public void onSuccess(String result) {
							txtNomeCompleto.setText("");
							txtNick.setText("");
							txtEndereco.setText("");
							ptbSenha.setText("");
							ptbSenha2.setText("");

							lblErroCadastro.setStyleName("label");
							lblErroCadastro
									.setText("Novo usuário cadastrado com sucesso");

							btnCadastrar.setEnabled(true);
							txtLoginNick.selectAll();
							txtLoginNick.setFocus(true);
						}
					});
		}
	}
}