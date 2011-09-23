package sistema.persistencia;

import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import sistema.emprestimo.EmprestimoIF;
import sistema.mensagem.ChatIF;
import sistema.utilitarios.Mensagem;

public class ChatRepositorio {
	
	private static long contadorID = 0;

	private static Map<Long, ChatIF> conversas = new TreeMap<Long, ChatIF>();

	/**
	 * Calcula o id do proximo emprestimo a ser cadastrado.
	 * 
	 * @return O id do proximo emprestimo a ser cadastrado.
	 */
	public static String geraIdProxConversa() {
		return String.valueOf(contadorID + 1);
	}

	
	public static String registrarConversa(ChatIF mensagem) throws Exception {
		mensagem.setIdMensagem(ChatRepositorio.geraIdProxConversa());
		conversas.put(++contadorID, mensagem);
		return String.valueOf(contadorID);
	}

	public static ChatIF recuperarConversa(String idConversa) throws Exception {
		Long idLong = null;
		try {
			idLong = Long.parseLong(idConversa);
		} catch (Exception e) {
			throw new Exception(Mensagem.ID_MENSAGEM_INVALIDO.getMensagem());
		}
		ChatIF msg = conversas.get(Long.parseLong(idConversa));
		if (msg == null)
			throw new Exception(Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		
		return msg;
	}

	public static String getAtributoConversa(String idConversa, String atributo)
			throws Exception {
		ChatIF msg = recuperarConversa(idConversa);

		String valor = null;
		for (Field f : msg.getClass().getDeclaredFields()) {
			if (f.getName().equals(atributo)) {
				f.setAccessible(true);
				valor = (f.get((ChatIF) msg)).toString();
			}
		}
		if (valor == null)
			throw new Exception(Mensagem.ATRIBUTO_INEXISTENTE.getMensagem());

		return valor;
	}
	
	/**
	 * Calcula a quantidade de emprestimos cadastrados.
	 * @return
	 * 		A quantidade de emprestimos cadastrados.
	 */
	public static int qntMensagens(){
		return conversas.size();
	}
	
	
	/**
	 * Verifica se um determinado emprestimos existe no repositorio.
	 * 
	 * @param idEmprestimo
	 * 		Um idEmprestimo.
	 * @return
	 * 		True - Se o emprestimo procurado existir.
	 * 		False - Se o emprestimo não existir.
	 */
	public static boolean existeConversa( String idConversa ){
		Long id;
		try{
			id = Long.valueOf(idConversa);
		}catch(Exception e){
			return false;
		}
		return conversas.containsKey(Long.valueOf(idConversa));
	}
	
	public static ChatIF existeConversaEntreAsPessoasSobreMesmoAssuntoETipo( String remetente, 
			String destinatario, String assunto, boolean ehOffTopic ) {
		
		Iterator<Entry<Long, ChatIF>> iterador = conversas.entrySet().iterator();
		ChatIF conversaSaida = null;
		while(iterador.hasNext()){			
			Entry<Long, ChatIF> conversaEntrada = iterador.next();
			ChatIF conversa = conversaEntrada.getValue();
			if( ( conversa.getRemetente().getLogin().equals(remetente) && 
					conversa.getDestinatario().getLogin().equals(destinatario) ) ||
				( conversa.getRemetente().getLogin().equals(destinatario) && 
					conversa.getDestinatario().getLogin().equals(remetente)) ){
				
				if( conversa.getAssunto().trim().equals(assunto.trim()) ){
					if(ehOffTopic){
						if(conversa.ehConversaOfftopic())
							// trata-se da mesma conversa
							return conversa;
					}else{
						if(!conversa.ehConversaOfftopic()){
							return conversa;
						}
					}
					
				}
			}
			
		}
		
		return null;
	}
	
	
	
}
