package sistema.persistencia;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.TreeMap;

import sistema.emprestimo.EmprestimoIF;
import sistema.mensagem.MensagemChatIF;
import sistema.utilitarios.Mensagem;

public class MensagemChatRepositorio {
	
	private static long contadorID = 0;

	private static Map<Long, MensagemChatIF> mensagensEnviadas = new TreeMap<Long, MensagemChatIF>();

	/**
	 * Calcula o id do proximo emprestimo a ser cadastrado.
	 * 
	 * @return O id do proximo emprestimo a ser cadastrado.
	 */
	public static String geraIdProxMensagem() {
		return String.valueOf(contadorID + 1);
	}

	
	public static String registrarMensagem(MensagemChatIF msg) throws Exception {
		msg.setIdMensagem(MensagemChatRepositorio.geraIdProxMensagem());
		mensagensEnviadas.put(++contadorID, msg);
		return String.valueOf(contadorID);
	}

	public static MensagemChatIF recuperarMensagem(String idMensagem) throws Exception {
		Long idLong = null;
		try {
			idLong = Long.parseLong(idMensagem);
		} catch (Exception e) {
			throw new Exception(Mensagem.ID_MENSAGEM_INVALIDO.getMensagem());
		}
		MensagemChatIF msg = mensagensEnviadas.get(Long.parseLong(idMensagem));
		if (msg == null)
			throw new Exception(Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		
		return msg;
	}

	public static String getAtributoItem(String idMensagem, String atributo)
			throws Exception {
		MensagemChatIF msg = recuperarMensagem(idMensagem);

		String valor = null;
		for (Field f : msg.getClass().getDeclaredFields()) {
			if (f.getName().equals(atributo)) {
				f.setAccessible(true);
				valor = (f.get((MensagemChatIF) msg)).toString();
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
		return mensagensEnviadas.size();
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
	public static boolean existeMensagem( String idMensagem ){
		Long id;
		try{
			id = Long.valueOf(idMensagem);
		}catch(Exception e){
			return false;
		}
		return mensagensEnviadas.containsKey(Long.valueOf(idMensagem));
	}
	
}
