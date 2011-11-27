package iu.web.server.sistema.autenticacao;

import iu.web.server.sistema.usuario.UsuarioIF;
import iu.web.server.sistema.utilitarios.Validador;

import java.io.Serializable;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang.RandomStringUtils;


public class RedefineSenhaTask implements Runnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4506226829984457305L;
	
	private UsuarioIF usuario;
	
	public RedefineSenhaTask(UsuarioIF usuario) throws Exception {
		Validador.assertNaoNulo(usuario, "Usuário inválido");
		this.usuario = usuario;
	}
	
	private void enviarEmailRedefinicaoSenha()
			throws Exception {
		Validador.assertNaoNulo(usuario, "Usuário inválido");

		final Configuracao configuracao = Configuracao.getInstance();
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(configuracao.getUsernameSMTP(),
								configuracao.getSenhaSMTP());
					}
				});

		try {

			String senhaAleatoria = senhaAleatoria();
			configuracao.setSenhaRedefAcessoTeste(senhaAleatoria);
			usuario.setCartaoAcessoRedefSenha(senhaAleatoria);
			ServicoRecuperacaoSenhaUsuario.getInstance().
			    adicionaRequisicaoRedefinicaoSenha(usuario.getLogin());
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(configuracao.getEmailSMTP()));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(usuario.getEmailRedefinicaoSenha()));
			message.setSubject("Emprestimus - Redefinição de Senha");
			message.setText("Caro(a) ," + usuario.getNome()
					+ "\n\n Esta é sua nova senha de acesso." + "\n Você tem "
					+ configuracao.getTempoHorasPrazoRefinicaoSenha() + " horas para "
					+ "redefinir se autenticar e criar uma nova senha!"
					+ "\n\nSenha: " + senhaAleatoria);

			Transport.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();
			//throw new RuntimeException(e);
		}
	}
	
	private String senhaAleatoria(){
		return RandomStringUtils.randomAlphanumeric(8);
	}

	@Override
	public void run() {
		try {
			enviarEmailRedefinicaoSenha();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
