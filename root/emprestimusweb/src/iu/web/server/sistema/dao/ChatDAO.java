package iu.web.server.sistema.dao;

import iu.web.server.sistema.mensagem.ChatIF;
import iu.web.server.sistema.persistencia.PersistenciaListener;

public interface ChatDAO extends PersistenciaListener{

	/**
	 * Calcula o id do proximo emprestimo a ser cadastrado.
	 * 
	 * @return String
	 * 		O id do proximo emprestimo a ser cadastrado.
	 */
	public String geraIdProxConversa();

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
	public String registrarConversa(ChatIF mensagem) throws Exception;

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
	public ChatIF recuperarConversa(String idConversa) throws Exception;

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
	public String getAtributoConversa(String idConversa, String atributo) throws Exception;

	/**
	 * Calcula a quantidade de emprestimos cadastrados.
	 * 
	 * @return int
	 * 		A quantidade de emprestimos cadastrados.
	 */
	public int qntMensagens();

	/**
	 * Verifica se um determinado emprestimos existe no repositorio.
	 * 
	 * @param String
	 * 		Um idEmprestimo.
	 * @return boolean
	 * 		True - Se o emprestimo procurado existir.
	 */
	public boolean existeConversa(String idConversa);

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
			String remetente, String destinatario, String assunto, boolean ehOffTopic);

	/**
	 * retorna o repositorio a suas configurações iniciais
	 */
	public void zerarRepositorio();

}


