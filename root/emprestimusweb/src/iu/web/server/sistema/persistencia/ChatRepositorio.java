package iu.web.server.sistema.persistencia;

import iu.web.server.sistema.autenticacao.Configuracao;
import iu.web.server.sistema.emprestimo.Conta;
import iu.web.server.sistema.item.Bauh;
import iu.web.server.sistema.mensagem.ChatIF;
import iu.web.server.sistema.utilitarios.Mensagem;

/*import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;*/
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;


public class ChatRepositorio implements Serializable{
	
	private static ChatRepositorio repositorio;

	private static long contadorID = 0;

	private static Map<Long, ChatIF> conversas;// = new TreeMap<Long, ChatIF>();
	
	private ChatRepositorio() {
		
		Configuracao conf = Configuracao.getInstance();
		/*File arquivo = new File("./"+conf.getDiretorioBD()+"chatRepositorio.bd");
		File diretorio = new File("./"+conf.getDiretorioBD());
		if(!diretorio.exists() || !arquivo.exists()){
			diretorio.mkdir();
			try {
				arquivo.createNewFile();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			ObjectOutputStream objectOut = null;
			try {
				Object[] vetor = new Object[1];
				vetor[0] =  new TreeMap<Long, ChatIF>();
				conversas = new TreeMap<Long, ChatIF>();
				objectOut = new ObjectOutputStream(
	                    new BufferedOutputStream(new FileOutputStream("./"+conf.getDiretorioBD()+"chatRepositorio.bd")));
	                objectOut.writeObject(vetor);
	                
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					objectOut.close();
				} catch (IOException e) {}
			}
			
		}else{
			try {
				inicializarDados();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}*/
		conversas = new TreeMap<Long, ChatIF>();
		try {
			inicializarDados();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ChatRepositorio getInstance() {
		if (repositorio == null) {
			repositorio = new ChatRepositorio();
		}
		return repositorio;
	}
	
	private static void inicializarDados() throws Exception {
		Configuracao conf = Configuracao.getInstance();
        
        /*ObjectInputStream objectIn = null;
        try{
        	objectIn = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream("./"+conf.getDiretorioBD()+"chatRepositorio.bd")));
            Object[] vetor = ((Object[])objectIn.readObject());
            conversas = ((TreeMap<Long, ChatIF>) vetor[0]);
            contadorID = conversas.size();
        
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            objectIn.close();
        }*/
        conversas = new TreeMap<Long, ChatIF>();
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
		Configuracao conf = Configuracao.getInstance();
		/*File arquivo = new File("./"+conf.getDiretorioBD()+"chatRepositorio.bd");
		File diretorio = new File("./"+conf.getDiretorioBD());
		ObjectOutputStream objectOut = null;
		try {
			Object[] vetor = new Object[1];
			conversas = ((TreeMap<Long, ChatIF>) vetor[0]);
			objectOut = new ObjectOutputStream(
	            new BufferedOutputStream(new FileOutputStream("./"+conf.getDiretorioBD()+"chatRepositorio.bd")));
			objectOut.reset();
	           objectOut.writeObject(vetor);
	                
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				objectOut.close();
			} catch (IOException e) {}
		}*/

	}

}
