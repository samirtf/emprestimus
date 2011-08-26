package excecoes;

public class IdUsuarioDiferenteIdRedeSocialException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6412524527631566898L;

	public IdUsuarioDiferenteIdRedeSocialException() {
		super("INCONSISTENCIA:_ID_USUARIO_DIFERE_DA_ID_USUARIO_NA_REDE_SOCIAL");
	}
	
	public IdUsuarioDiferenteIdRedeSocialException(String mensagem){
		super(mensagem);
	}

}
