/**
 * 
 */
package iu.web.server.sistema.mensagem;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mobile
 * 
 */
public class CaixaPostal {

	private String proprietario; // proprietario da caixa postal
	private List<ChatIF> conversasOfftopic; // lista de conversas offtopic
	private List<ChatIF> conversasNegociacao; // lista de conversas negociacao

	public CaixaPostal(String proprietario) {
		this.proprietario = proprietario;
		conversasOfftopic = new ArrayList<ChatIF>();
		conversasNegociacao = new ArrayList<ChatIF>();
	}

	/**
	 * @return List<ChatIF>
	 * 		Conversas Off Topic
	 */
	public List<ChatIF> getConversasOffTopic() {
		return this.conversasOfftopic;
	}

	/**
	 * @return List<ChatIF>
	 * 		Conversas de Negociação
	 */
	public List<ChatIF> getConversasNegociacao() {
		return this.conversasNegociacao;
	}

	/**
	 * @return String
	 * 		Proprietario da caixa postal
	 */
	public String getProprietario() {
		return this.proprietario;
	}

}
