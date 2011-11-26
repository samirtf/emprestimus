package sistema.dao;

import sistema.mensagem.ChatIF;
import sistema.persistencia.ChatRepositorio;

public class ChatFileDAO implements ChatDAO{

	@Override
	public String geraIdProxConversa() {
		return ChatRepositorio.geraIdProxConversa();
	}

	@Override
	public String registrarConversa(ChatIF mensagem) throws Exception {
		return ChatRepositorio.registrarConversa(mensagem);
	}

	@Override
	public ChatIF recuperarConversa(String idConversa) throws Exception {
		return ChatRepositorio.recuperarConversa(idConversa);
	}

	@Override
	public String getAtributoConversa(String idConversa, String atributo)
			throws Exception {
		return ChatRepositorio.getAtributoConversa(idConversa, atributo);
	}

	@Override
	public int qntMensagens() {
		return ChatRepositorio.qntMensagens();
	}

	@Override
	public boolean existeConversa(String idConversa) {
		return ChatRepositorio.existeConversa(idConversa);
	}

	@Override
	public ChatIF existeConversaEntreAsPessoasSobreMesmoAssuntoETipo(
			String remetente, String destinatario, String assunto,
			boolean ehOffTopic) {
		return ChatRepositorio.existeConversaEntreAsPessoasSobreMesmoAssuntoETipo(remetente, destinatario, assunto, ehOffTopic);
	}

	@Override
	public void zerarRepositorio() {
		ChatRepositorio.zerarRepositorio();	
	}

}
