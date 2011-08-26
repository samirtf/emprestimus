package excecoes;

public class LoginMalFormadoCAException extends Exception{
	
	public LoginMalFormadoCAException() {
		super("INCONSISTENCIA:_LOGIN_MALFORMADO_CA");
	}
	
	public LoginMalFormadoCAException(String mensagem){
		super(mensagem);
	}

}
