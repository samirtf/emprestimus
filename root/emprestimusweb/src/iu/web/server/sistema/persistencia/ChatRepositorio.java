package iu.web.server.sistema.persistencia;

import iu.web.server.sistema.mensagem.ChatIF;
import iu.web.server.sistema.utilitarios.Mensagem;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


public class ChatRepositorio {
	
	private static ChatRepositorio repositorio;

	private long contadorID = 0;

	private Map<Long, ChatIF> conversas = new TreeMap<Long, ChatIF>();
	
	private ChatRepositorio() {
	}

	public static ChatRepositorio getInstance() {
		if (repositorio == null) {
			repositorio = new ChatRepositorio();
		}
		return repositorio;
	}

	/**
	 * Calcula o id do proximo emprestimo a ser cadastrado.
	 * 
	 * @return String
	 * 		O id do proximo emprestimo a ser cadastrado.
	 */
	public String geraIdProxConversa() {
		return String.valueOf(contadorID + 1);
	}

	/**
	 * Registra uma Conversa
	 * 
	 * @param ChatIF
	 * 		mensagem
	 * 
	 * @return String
	 * 		ID
	 * 
	 * @throws Exception
	 */
	public String registrarConversa(ChatIF mensagem) throws Exception {
		mensagem.setIdMensagem(geraIdProxConversa());
		conversas.put(++contadorID, mensagem);
		return String.valueOf(contadorID);
	}

	/**
	 * Recupera uma Conversa
	 * 
	 * @param String
	 * 		idConversa
	 * 
	 * @return ChatIF
	 * 		Conversa
	 * 
	 * @throws Exception
	 */
	public ChatIF recuperarConversa(String idConversa) throws Exception {
		Long idLong = null;
		try {
			idLong = Long.parseLong(idConversa);
		} catch (Exception e) {
			throw new Exception(Mensagem.ID_MENSAGEM_INVALIDO.getMensagem());
		}
		ChatIF msg = conversas.get(idLong);
		if (msg == null)
			throw new Exception(Mensagem.ID_ITEM_INEXISTENTE.getMensagem());

		return msg;
	}

	/**
	 * Retorna Atributo da Conversa
	 * 
	 * @param String
	 * 		idConversa
	 * @param String
	 * 		atributo
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	public String getAtributoConversa(String idConversa, String atributo) throws Exception {
		ChatIF msg = recuperarConversa(idConversa);

		String valor = null;
		for (Field f : msg.getClass().getDeclaredFields()) {
			if (f.getName().equals(atributo)) {
				f.setAccessible(true);
				valor = (f.get(msg)).toString();
			}
		}
		if (valor == null)
			throw new Exception(Mensagem.ATRIBUTO_INEXISTENTE.getMensagem());

		return valor;
	}

	/**
	 * Calcula a quantidade de emprestimos cadastrados.
	 * 
	 * @return int
	 * 		A quantidade de emprestimos cadastrados.
	 */
	public int qntMensagens() {
		return conversas.size();
	}

	/**
	 * Verifica se um determinado emprestimos existe no repositorio.
	 * 
	 * @param String
	 * 		Um idEmprestimo.
	 * @return boolean
	 * 		True - Se o emprestimo procurado existir.
	 */
	public boolean existeConversa(String idConversa) {
		Long idLong;
		try {
			idLong = Long.valueOf(idConversa);
		} catch (Exception e) {
			return false;
		}
		return conversas.containsKey(idLong);
	}

	/**
	 * Diz se existe uma Conversa Entre As Pessoas Sobre Mesmo Assunto E Tipo
	 * 
	 * @param String
	 * 		remetente
	 * @param String
	 * 		destinatario
	 * @param String
	 * 		assunto
	 * @param boolean
	 * 		ehOffTopic
	 * 
	 * @return
	 */
	public ChatIF existeConversaEntreAsPessoasSobreMesmoAssuntoETipo(
			String remetente, String destinatario, String assunto, boolean ehOffTopic) {

		Iterator<Entry<Long, ChatIF>> iterador = conversas.entrySet().iterator();
		while (iterador.hasNext()) {
			Entry<Long, ChatIF> conversaEntrada = iterador.next();
			ChatIF conversa = conversaEntrada.getValue();
			if ((conversa.getRemetente().getLogin().equals(remetente) && conversa
					.getDestinatario().getLogin().equals(destinatario))
					|| (conversa.getRemetente().getLogin().equals(destinatario) && conversa
							.getDestinatario().getLogin().equals(remetente))) {

				if (conversa.getAssunto().trim().equals(assunto.trim())) {
					if (ehOffTopic) {
						if (conversa.ehConversaOfftopic())
							// trata-se da mesma conversa
							return conversa;
					} else {
						if (!conversa.ehConversaOfftopic()) {
							return conversa;
						}
					}

				}
			}

		}

		return null;
	}

	/**
	 * retorna o repositorio a suas configurações iniciais
	 */
	public void zerarRepositorio() {
		conversas = new TreeMap<Long, ChatIF>();
		contadorID = 0;
	}

	public void salvarEmArquivo() {
		// TODO Auto-generated method stub
		
	}

}
