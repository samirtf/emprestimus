package iu.web.shared;

/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public enum MensagensWeb {
	NOME_CURTO("O nome deve ter pelo menos 4 caracteres"),
	SENHA_CURTA("A senha deve ter pelo menos 6 caracteres");
	
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
