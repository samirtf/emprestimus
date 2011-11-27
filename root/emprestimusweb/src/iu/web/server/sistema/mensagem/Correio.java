/**
 * 
 */
package iu.web.server.sistema.mensagem;

import static iu.web.server.sistema.utilitarios.Validador.assertStringNaoVazia;
import static iu.web.server.sistema.utilitarios.Validador.asserteTrue;

import iu.web.server.sistema.autenticacao.Autenticacao;
import iu.web.server.sistema.autenticacao.Configuracao;
import iu.web.server.sistema.dao.ChatFileDAO;
import iu.web.server.sistema.dao.EmprestimoFileDAO;
import iu.web.server.sistema.emprestimo.EmprestimoIF;
import iu.web.server.sistema.item.Bauh;
import iu.web.server.sistema.usuario.UsuarioIF;
import iu.web.server.sistema.utilitarios.Mensagem;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Mobile
 * 
 */
public class Correio {

	private static Correio correio;
	private static Map<String, CaixaPostal> caixasPostais = new TreeMap<String, CaixaPostal>();

	private Correio() {
		caixasPostais = new TreeMap<String, CaixaPostal>();
		
		Configuracao conf = Configuracao.getInstance();
		File arquivo = new File("./"+conf.getDiretorioBD()+"correio.bd");
		File diretorio = new File("./"+conf.getDiretorioBD());
		if(!diretorio.exists()){
			diretorio.mkdir();
			ObjectOutputStream objectOut = null;
			try {
				arquivo.createNewFile();
				Object[] vetor = new Object[1];
				vetor[0] =  new TreeMap<String, CaixaPostal>();
				objectOut = new ObjectOutputStream(
	                    new BufferedOutputStream(new FileOutputStream("./"+conf.getDiretorioBD()+"correio.bd")));
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
		}
		
	}

	public static Correio getInstance() {
		if (correio == null) {
			correio = new Correio();

			return correio;
		}
		return correio;
	}
	
	private static void inicializarDados() throws Exception {
		Configuracao conf = Configuracao.getInstance();
        
        ObjectInputStream objectIn = null;
        try{
        	objectIn = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream("./"+conf.getDiretorioBD()+"correio.bd")));
            Object[] vetor = ((Object[])objectIn.readObject());
            caixasPostais = ((TreeMap<String, CaixaPostal>) vetor[0]);
        
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            objectIn.close();
        }
        
    }

	public void adicionaCaixaPostalAoUsuario(String usuario) throws Exception {
		if (caixasPostais.containsKey(usuario))
			throw new Exception(Mensagem.PROPRIETARIO_CAIXA_POSTAL_JAH_CADASTRADO
					.getMensagem());
		caixasPostais.put(usuario, new CaixaPostal(usuario));
	}

	public void removeCaixaPostalDoUsuario(String usuario) throws Exception {
		caixasPostais.remove(usuario);
	}

	/**
	 * Adiciona uma conversa off-topic à lista de conversas do usuário.
	 * 
	 * @param usuario - String
	 * 		O login do usuario.
	 *            
	 * @param conversa - ChatIF
	 * 		Conversa a ser adicionada.
	 * 
	 * @throws Exception
	 */
	public synchronized void adicionaConversaOfftopicNaLista(String usuario, ChatIF conversa) throws Exception {
		CaixaPostal caixaPostal = caixasPostais.get(usuario);
		if (caixaPostal == null)
			throw new Exception(Mensagem.PROPRIETARIO_CAIXA_POSTAL_INEXISTENTE
					.getMensagem());
		caixaPostal.getConversasOffTopic().add(conversa);
	}

	/**
	 * Adiciona uma conversa sobre uma negociação à lista de conversas do
	 * usuário.
	 * 
	 * @param usuario - String
	 * 		O login do usuario.
	 *            
	 * @param conversa - ChatIF
	 * 		Conversa a ser adicionada.
	 * 
	 * @throws Exception
	 */
	public void adicionaConversaNegociacaoNaLista(String usuario, ChatIF conversa) throws Exception {
		CaixaPostal caixaPostal = caixasPostais.get(usuario);
		if (caixaPostal == null)
			throw new Exception(Mensagem.PROPRIETARIO_CAIXA_POSTAL_INEXISTENTE
					.getMensagem());
		caixaPostal.getConversasNegociacao().add(conversa);
	}

	/**
	 * Envia uma mensagem off-topic.
	 * 
	 * @param usuario - String
	 *  	O login do usuario.
	 * @param destinatario - String
	 * 		Destino da mensagem.
	 * @param assunto - String
	 * 		Assunto da mensagem.
	 * @param mensagem - String
	 * 		Mensagem a ser enviada.
	 *            
	 * @return String
	 * 		ID do tópico.
	 * 
	 * @throws Exception
	 * 		Caso os parâmetros sejam inválidos.
	 */
	public synchronized String enviarMensagemOffTopic(String remetente,
			String destinatario, String assunto, String mensagem) throws Exception {

		assertStringNaoVazia(remetente, Mensagem.REMETENTE_INVALIDO.getMensagem(),
				Mensagem.REMETENTE_INVALIDO.getMensagem());

		assertStringNaoVazia(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem(),
				Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(remetente);
		UsuarioIF amigo = usuario.possuoAmigoComEsteLogin(destinatario);

		asserteTrue(amigo != null, Mensagem.DESTINATARIO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem(),
				Mensagem.ASSUNTO_INVALIDO.getMensagem());
		assertStringNaoVazia(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem(),
				Mensagem.MENSAGEM_INVALIDA.getMensagem());

		ChatIF conversa = new ChatFileDAO()
				.existeConversaEntreAsPessoasSobreMesmoAssuntoETipo(usuario.getLogin(),
						destinatario, assunto, true);

		if (conversa == null) {
			conversa = new Chat(usuario, amigo, assunto.trim(), mensagem.trim());
			conversa.setTipoOffTopicMsg();
			new ChatFileDAO().registrarConversa(conversa);
			caixasPostais.get(remetente).getConversasOffTopic().add(conversa);
			// usuario.conversasOfftopic.add(conversa);
			amigo.adicionaConversaOfftopicNaLista(conversa);
		} else {
			conversa.adicionaMensagem(mensagem);
		}

		return conversa.getIdMensagem();

	}

	/**
	 * Envia mensagem de emprestimo
	 * 
	 * @param usuario - String
	 * 		O login do usuario.
	 * @param destinatario - String
	 * 		Destino da mensagem.
	 * @param assunto - String
	 * 		Assunto da mensagem.
	 * @param mensagem - String
	 * 		Mensagem a ser enviada.
	 * @param idRequisicaoEmprestimo - String
	 * 		Id da requisicao de emprestimo.
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	public synchronized String enviarMensagemEmprestimo(String remetente,
			String destinatario, String assunto, String mensagem,
			String idRequisicaoEmprestimo) throws Exception {

		assertStringNaoVazia(remetente, Mensagem.REMETENTE_INVALIDO.getMensagem(),
				Mensagem.REMETENTE_INVALIDO.getMensagem());
		assertStringNaoVazia(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem(),
				Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(remetente);
		UsuarioIF amigo = usuario.possuoAmigoComEsteLogin(destinatario);
		asserteTrue(amigo != null, Mensagem.DESTINATARIO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem(),
				Mensagem.ASSUNTO_INVALIDO.getMensagem());
		assertStringNaoVazia(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem(),
				Mensagem.MENSAGEM_INVALIDA.getMensagem());
		assertStringNaoVazia(idRequisicaoEmprestimo,
				Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem(),
				Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		asserteTrue(
				new EmprestimoFileDAO().existeEmprestimo(idRequisicaoEmprestimo.trim()),
				Mensagem.ID_REQUISICAO_EMP_INEXISTENTE.getMensagem());

		EmprestimoIF emprestimo = new EmprestimoFileDAO()
				.recuperarEmprestimo(idRequisicaoEmprestimo);
		if (!usuario.equals(emprestimo.getEmprestador())
				&& !usuario.equals(emprestimo.getBeneficiado()))
			throw new Exception(Mensagem.USUARIO_NAO_PARTICIPA_DESTE_EMPRESTIMO
					.getMensagem());

		ChatIF conversa = new ChatFileDAO()
				.existeConversaEntreAsPessoasSobreMesmoAssuntoETipo(usuario.getLogin(),
						destinatario, assunto, false);

		if (conversa == null) {
			conversa = new Chat(usuario, amigo, assunto.trim(), mensagem,
					idRequisicaoEmprestimo);
			conversa.setTipoNegociacaoMsg();
			new ChatFileDAO().registrarConversa(conversa);
			caixasPostais.get(remetente).getConversasNegociacao().add(conversa);
			// this.conversasNegociacao.add(conversa);
			amigo.adicionaConversaNegociacaoNaLista(conversa);
		} else {
			conversa.adicionaMensagem(mensagem);
		}
		return conversa.getIdMensagem();
	}

	/**
	 * Lê topicos
	 * 
	 * @param usuario - String
	 * 		O login do usuario.
	 * @param tipo String
	 * 		Tipo do topico.
	 * 
	 * @return String
	 * 		compilação dos textos.
	 * 
	 * @throws Exception
	 */
	public String lerTopicos(String proprietario, String tipo) throws Exception {

		assertStringNaoVazia(proprietario, Mensagem.PROPRIETARIO_CAIXA_POSTAL_INEXISTENTE
				.getMensagem(), Mensagem.PROPRIETARIO_CAIXA_POSTAL_INEXISTENTE
				.getMensagem());
		assertStringNaoVazia(tipo, Mensagem.TIPO_INVALIDO.getMensagem(),
				Mensagem.TIPO_INVALIDO.getMensagem());

		List<ChatIF> listaTopicos = new ArrayList<ChatIF>();
		StringBuffer saida = new StringBuffer();
		if (tipo.trim().equalsIgnoreCase("negociacao")
				|| tipo.trim().equalsIgnoreCase("todos")) {
			Iterator<ChatIF> iterador = caixasPostais.get(proprietario)
					.getConversasNegociacao().iterator();

			while (iterador.hasNext()) {
				listaTopicos.add(iterador.next());
				// saida.append(iterador.next().getAssunto()+"; ");
			}
		}
		if (tipo.trim().equalsIgnoreCase("offtopic")
				|| tipo.trim().equalsIgnoreCase("todos")) {
			Iterator<ChatIF> iterador = caixasPostais.get(proprietario)
					.getConversasOffTopic().iterator();

			while (iterador.hasNext()) {
				listaTopicos.add(iterador.next());
				// saida.append(iterador.next().getAssunto()+"; ");
			}
		}
		if (!tipo.trim().equalsIgnoreCase("negociacao")
				&& !tipo.trim().equalsIgnoreCase("offtopic")
				&& !tipo.trim().equalsIgnoreCase("todos")) {
			throw new Exception(Mensagem.TIPO_INEXISTENTE.getMensagem());
		}

		if (listaTopicos.isEmpty())
			return Mensagem.NAO_HA_TOPICOS_CRIADOS.getMensagem();
		Collections.sort(listaTopicos);
		Collections.reverse(listaTopicos);
		for (ChatIF c : listaTopicos) {
			saida.append(c.getAssunto() + "; ");
		}
		return saida.toString().trim().substring(0, saida.toString().trim().length() - 1);
	}

	/**
	 * Envia uma mensagem de oferecimento de item Off Topic
	 * 
	 * @param remetente - String
	 * 		remetente da mensagem.
	 * @param destinatario - String
	 * 		Destinatario da mensagem
	 * @param assunto - String
	 * 		Assunto da mensagem
	 * @param mensagem - String
	 * 		texto da mensagem
	 * 
	 * @return String
	 * 		id da mensagem de oferencimento de item off topic
	 * 
	 * @throws Exception
	 */
	public String enviarMensagemOferecimentoItemOffTopic(String remetente,
			String destinatario, String assunto, String mensagem) throws Exception {

		assertStringNaoVazia(remetente, Mensagem.REMETENTE_INVALIDO.getMensagem(),
				Mensagem.REMETENTE_INVALIDO.getMensagem());

		assertStringNaoVazia(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem(),
				Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(remetente);
		UsuarioIF amigoDeAmigo = Autenticacao.getUsuarioPorLogin(destinatario);

		asserteTrue(amigoDeAmigo != null, Mensagem.DESTINATARIO_INEXISTENTE.getMensagem());
		assertStringNaoVazia(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem(),
				Mensagem.ASSUNTO_INVALIDO.getMensagem());
		assertStringNaoVazia(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem(),
				Mensagem.MENSAGEM_INVALIDA.getMensagem());

		ChatIF conversa = new ChatFileDAO()
				.existeConversaEntreAsPessoasSobreMesmoAssuntoETipo(usuario.getLogin(),
						destinatario, assunto, true);

		if (conversa == null) {
			conversa = new Chat(usuario, amigoDeAmigo, assunto.trim(), mensagem.trim());
			conversa.setTipoOffTopicMsg();
			new ChatFileDAO().registrarConversa(conversa);
			caixasPostais.get(remetente).getConversasOffTopic().add(conversa);
			// usuario.conversasOfftopic.add(conversa);
			amigoDeAmigo.adicionaConversaOfftopicNaLista(conversa);
		} else {
			conversa.adicionaMensagem(mensagem);
		}

		return conversa.getIdMensagem();

	}

	/**
	 * retorna o correio a sua configuração inicial
	 */
	public void zerarSistema() {
		Correio.caixasPostais.clear();
	}

	public void salvarEmArquivo() {
		// TODO Auto-generated method stub
		
	}

}
