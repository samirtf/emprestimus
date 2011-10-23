/**
 * 
 */
package sistema.mensagem;

import java.util.List;

/**
 * @author Mobile
 *
 */
public class CaixaPostal {
	
	
	private String proprietario; //proprietario da caixa postal
	private List<ChatIF> conversasOfftopic; //lista de conversas offtopic
	private List<ChatIF> conversasNegociacao; //lista de conversas negociacao
	
	public CaixaPostal( String proprietario ){
		this.proprietario = proprietario;
	}
	
	public List<ChatIF> getConversasOffTopic() {
		return this.conversasOfftopic;
	}
	
	public List<ChatIF> getConversasNegociacao() {
		return this.conversasNegociacao;
	}
	
	public String getProprietario(){
		return this.proprietario;
	}
	

}
