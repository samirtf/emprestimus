package testes.aceitacao.fachadas;

public class UserFacade08 extends UserFacade07{

	
	public String enviarMensagem(String idSessao, String destinatario,
			String assunto, String mensagem) throws Exception {
		 return sistema.enviarMensagem(idSessao, destinatario, assunto, mensagem);
		
	}

	
	public String enviarMensagm(String idSessao, String destinatario,
			String assunto, String mensagem, String idRequisicaoEmprestimo)
			throws Exception {
		 return sistema.enviarMensagem(idSessao, destinatario, assunto, mensagem, idRequisicaoEmprestimo);
		
	}

	
	public String lerTopicos(String idSessao, String tipo) throws Exception {
		return sistema.lerTopicos(idSessao, tipo);
	}

	
	public String lerMensagens(String idSessao, String idTopico)
			throws Exception {
		return sistema.lerMensagens(idSessao, idTopico);
	}

	
}

