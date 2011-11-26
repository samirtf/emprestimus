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
                
                Image image = new Image("emprestimusweb/imagens/back.png");
                painelLogin.add(image, 10, 155);
                image.setSize("857px", "342px");
                Label lblLoginNick = new Label("Username:");
                painelLogin.add(lblLoginNick, 488, 30);
                
                Label lblLoginSenha = new Label("Senha:");
                painelLogin.add(lblLoginSenha, 667, 32);
                
                txtLoginNick = new TextBox();
                painelLogin.add(txtLoginNick, 488, 54);
                txtLoginNick.setFocus(true);
                
                ptbLoginSenha = new PasswordTextBox();
                painelLogin.add(ptbLoginSenha, 667, 56);
                
                btnLogin = new Button("Login");
                painelLogin.add(btnLogin, 777, 96);
                
                lblErro = new Label("");
                lblErro.setStyleName("serverResponseLabelError");
                painelLogin.add(lblErro, 508, 74);
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
                
                Image image_1 = new Image("emprestimusweb/imagens/.svn/text-base/logo2.png.svn-base");
                painelLogin.add(image_1, 31, 10);
                image_1.setSize("400px", "127px");

                Image imagem3 = new Image("emprestimusweb/imagens/03.jpg");
                painelLogin.add(imagem3, 23, 382);
                imagem3.setSize("100px", "100px");
                
                Image imagem2 = new Image("emprestimusweb/imagens/02.jpg");
                painelLogin.add(imagem2, 23, 271);
                imagem2.setSize("100px", "100px");
                
                Image imagem1 = new Image("emprestimusweb/imagens/01.jpg");
                painelLogin.add(imagem1, 23, 165);
                imagem1.setSize("100px", "100px");
                
                InlineHTML textoHTML = new InlineHTML("<big>Pra quê comprar um novo se você pode pedir emprestado?</big><br><br>\r\nEmprestimus é a primeira rede social de empréstimos do Brasil, aqui você pode conseguir filmes, jogos, livros e muito mais...<br>\r\nConheça novas pessoas, saiba quem mora perto de você que pode lhe emprestar um ítem que você deseja. Troque mensagens com seus amigos, empreste seus ítens para aumentar sua reputação, faça Emprestimus!<br><br>\r\nCrie agora uma conta grátis!");
                painelLogin.add(textoHTML, 140, 190);
                textoHTML.setSize("291px", "279px");
                
                InlineHTML nlnhtmlNewInlinehtml = new InlineHTML("<a href=\"esquecimento.html\">\rEsqueceu a senha?</a> ");
                nlnhtmlNewInlinehtml.setStyleName("h1");
                painelLogin.add(nlnhtmlNewInlinehtml, 546, 108);
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
                                txtLoginNick.selectAll();
                                txtLoginNick.setFocus(true);
                                return;
                        } else if (!VerificadorDeCampos.ehSenhaValida(senha)) {
                                lblErro.setText(MensagensWeb.FALHA_NA_AUTENTICACAO.getMensagem());
                                ptbLoginSenha.selectAll();
                                ptbLoginSenha.setFocus(true);
                                return;
                        }

                        greetingService.login(nick, senha, new AsyncCallback<String>() {
                                public void onFailure(Throwable caught) {
                                        lblErro.setText(MensagensWeb.FALHA_NA_AUTENTICACAO.getMensagem());
                                        
                                }

                                public void onSuccess(String idSessao) {
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
                        lblErroCadastro.setStyleName("serverResponseLabelError");
                        lblErroCadastro.setText("");
                        String nome = txtNomeCompleto.getText();
                        String nick = txtNick.getText();
                        String endereco = txtEndereco.getText();
                        String senha = ptbSenha.getText();
                        String senha2 = ptbSenha2.getText();
                        
                        if (!VerificadorDeCampos.ehNomeValido(nome)) {
                                lblErroCadastro.setText("O nome não é válido");
                                txtNomeCompleto.selectAll();
                                txtNomeCompleto.setFocus(true);
                                return;
                        } else if (!VerificadorDeCampos.ehNickValido(nick)) {
                                lblErroCadastro.setText("O nick não é válido");
                                txtNick.selectAll();
                                txtNick.setFocus(true);
                                return;
                        } else if (!VerificadorDeCampos.ehEnderecoValido(endereco)) {
                                lblErroCadastro.setText("O endereço não é válido");
                                txtEndereco.selectAll();
                                txtEndereco.setFocus(true);
                                return;
                        } else if (!senha.equals(senha2)) {
                                lblErroCadastro.setText("As senhas não são igualis");
                                ptbSenha.selectAll();
                                ptbSenha.setFocus(true);
                                return;
                        } else if (!VerificadorDeCampos.ehSenhaValida(senha)) {
                                lblErroCadastro.setText("A senha não é válida");
                                ptbSenha.selectAll();
                                ptbSenha.setFocus(true);
                                return;
                        }

                        greetingService.cadastra(nome, nick, endereco, senha, new AsyncCallback<String>() {
                                public void onFailure(Throwable caught) {
                                        lblErroCadastro.setText(caught.getMessage());
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
                                        lblErroCadastro.setText("Novo usuário cadastrado com sucesso");
                                        
                                        txtLoginNick.selectAll();
                                        txtLoginNick.setFocus(true);
                                }
                        });
                }
        }
}