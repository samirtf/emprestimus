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

/**
 * Painel de login
 * 
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public class Login extends Composite {
	
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

	private AbsolutePanel painelLogin;
	
	//Componentes do login 
	private TextBox txtLoginNick;
	private PasswordTextBox ptbLoginSenha;
	private Button btnLogin;
	private Label lblErro;
	
	//Componentes do cadastro
	private TextBox txtNomeCompleto;
	private TextBox txtNick;
	private TextBox txtEndereco;
	private PasswordTextBox ptbSenha;
	private PasswordTextBox ptbSenha2;
	private Button btnCadastrar;
	
	private Controlador controlador;

	public Login(Controlador controlador) {
		this.controlador = controlador;
		
		painelLogin = new AbsolutePanel();
		initWidget(painelLogin);
		painelLogin.setSize("877px", "492px");
		
		iniciaComponentesDoLogin();
		iniciaComponentesDoCadastro();
		iniciaComponentesDoLadoEsquerdo();
		
		//Eventos do login
		MyHandlerLogin handlerLogin = new MyHandlerLogin();
		btnLogin.addClickHandler(handlerLogin);
		txtLoginNick.addKeyUpHandler(handlerLogin);
		ptbLoginSenha.addKeyUpHandler(handlerLogin);
		
		//Eventos do cadastro
		MyHandlerCadastro handlerCadastro = new MyHandlerCadastro();
		btnCadastrar.addClickHandler(handlerCadastro);
		txtNomeCompleto.addKeyUpHandler(handlerCadastro);
		txtNick.addKeyUpHandler(handlerCadastro);
		txtEndereco.addKeyUpHandler(handlerCadastro);
		ptbSenha.addKeyUpHandler(handlerCadastro);
		ptbSenha2.addKeyUpHandler(handlerCadastro);
	}

	private void iniciaComponentesDoLogin() {
		Label lblLoginNick = new Label("Nick:");
		painelLogin.add(lblLoginNick, 508, 10);
		
		Label lblLoginSenha = new Label("Senha:");
		painelLogin.add(lblLoginSenha, 687, 12);
		
		txtLoginNick = new TextBox();
		painelLogin.add(txtLoginNick, 508, 34);
		
		ptbLoginSenha = new PasswordTextBox();
		painelLogin.add(ptbLoginSenha, 687, 36);
		
		btnLogin = new Button("Login");
		painelLogin.add(btnLogin, 797, 76);
		
		lblErro = new Label("");
		lblErro.setStyleName("serverResponseLabelError");
		painelLogin.add(lblErro, 508, 74);
	}
	
	private void iniciaComponentesDoCadastro() {
		Label lblNovoPorAqui = new Label("Novo por aqui? Faça um cadastro:");
		lblNovoPorAqui.setStyleName("nathaniel1");
		painelLogin.add(lblNovoPorAqui, 538, 202);
		
		Label lblNomeCompleto = new Label("Nome Completo:");
		painelLogin.add(lblNomeCompleto, 482, 238);
		
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
	}
	
	private void iniciaComponentesDoLadoEsquerdo() {
		Label lblEmprestimus = new Label("Emprestimus"); //nome Emprestimus grande
		lblEmprestimus.setStyleName("nathaniel2");
		painelLogin.add(lblEmprestimus, 10, 10);

		Image imagem3 = new Image("emprestimusweb/gwt/clean/imagens/mj.jpg");
		painelLogin.add(imagem3, 10, 382);
		imagem3.setSize("100px", "100px");
		
		Image imagem2 = new Image("emprestimusweb/gwt/clean/imagens/katy.jpg");
		painelLogin.add(imagem2, 10, 271);
		imagem2.setSize("100px", "100px");
		
		Image imagem1 = new Image("emprestimusweb/gwt/clean/imagens/avril.jpg");
		painelLogin.add(imagem1, 10, 165);
		imagem1.setSize("100px", "100px");
		
		InlineHTML textoHTML = new InlineHTML("<big>Pra que comprar um novo se você pode pedir emprestado?</big><br><br>\r\nEmprestimus é a primeira rede social de empréstimos do Brasil, aqui você pode conseguir filmes, jogos, livros e muito mais...<br>\r\nConheça novas pessoas, saiba quem mora perto de você que pode lhe emprestar um ítem que você deseja. Troque mensagens com seus amigos, empreste seus ítens para aumentar sua reputação, faça Emprestimus!<br><br>\r\nCrie agora uma conta grátis ou use seu Emprestimus Nick para entrar no sistema.");
		painelLogin.add(textoHTML, 129, 190);
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
				if ((!txtLoginNick.getText().trim().equals(""))&&(!ptbLoginSenha.getText().trim().equals(""))) {
					enviaAoServidor();
				}
			}
		}
		private void enviaAoServidor() {
			lblErro.setText("");
			String nick = txtLoginNick.getText();
			String senha = ptbLoginSenha.getText();
			if (!VerificadorDeCampos.ehNickValido(nick)) {
				lblErro.setText(MensagensWeb.FALHA_NA_AUTENTICACAO.getMensagem());
				return;
			} else if (!VerificadorDeCampos.ehSenhaValida(senha)) {
				lblErro.setText(MensagensWeb.FALHA_NA_AUTENTICACAO.getMensagem());
				return;
			}

//			btnLogin.setEnabled(false);
//			txtLoginNick.setEnabled(false);
//			ptbLoginSenha.setEnabled(false);
			greetingService.greetServer("login|"+nick+"|"+senha, new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {
					lblErro.setText(MensagensWeb.FALHA_NA_AUTENTICACAO.getMensagem());
				}

				public void onSuccess(String idSessao) {
					lblErro.setText("Sucesso: " + idSessao);
					removeFromParent();
					controlador.abrirSessao(idSessao);
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
				if ((!txtNomeCompleto.getText().trim().equals(""))&&(!txtNick.getText().trim().equals(""))
						&&(!txtEndereco.getText().trim().equals(""))&&(!ptbSenha.getText().trim().equals(""))
						&&(!ptbSenha2.getText().trim().equals(""))) {
					enviaAoServidor();
				}
			}
		}
		private void enviaAoServidor() {
			String nome = txtNomeCompleto.getText();
			String nick = txtNick.getText();
			String endereco = txtEndereco.getText();
			String senha = ptbSenha.getText();
			String senha2 = ptbSenha2.getText();
			
			if (!VerificadorDeCampos.ehNomeValido(nome)) {
				txtNomeCompleto.selectAll();
				return;
			} else if (!VerificadorDeCampos.ehNickValido(nick)) {
				txtNick.selectAll();
				return;
			} else if (!VerificadorDeCampos.ehEnderecoValido(endereco)) {
				txtEndereco.selectAll();
				return;
			} else if (!senha.equals(senha2)) {
				ptbSenha.selectAll();
				return;
			} else if (!VerificadorDeCampos.ehSenhaValida(senha)) {
				ptbSenha.selectAll();
				return;
			}

//			btnCadastrar.setEnabled(false);
//			txtNomeCompleto.setEnabled(false);
//			txtNick.setEnabled(false);
//			txtEndereco.setEnabled(false);
//			ptbSenha.setEnabled(false);
//			ptbSenha2.setEnabled(false);
			
			greetingService.greetServer("cadastro|"+nome+"|"+nick+"|"+endereco+"|"+senha, new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {
					lblErro.setText(caught.getMessage());
				}
				public void onSuccess(String result) {
					lblErro.setText("Suceso!!!");
				}
			});
		}
	}
}
