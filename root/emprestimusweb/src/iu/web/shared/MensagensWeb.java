package iu.web.shared;

import java.io.Serializable;

/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public enum MensagensWeb implements Serializable{
	NOME_CURTO("O nome deve ter pelo menos 4 caracteres"),
	SENHA_CURTA("A senha deve ter pelo menos 6 caracteres"),
	FALHA_NA_AUTENTICACAO("Falha na autenticação");
	
	private String mensagem;
	
	/**
	 * 
	 */
	private MensagensWeb(String mensagem) {
		this.mensagem = mensagem;
	}
	
	public String getMensagem() {
		return mensagem;
	}

}
