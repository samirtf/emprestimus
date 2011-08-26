package excecoes;

public class IDMalFormadoCAException extends Exception{
	
	public IDMalFormadoCAException() {
		super("INCONSISTENCIA:_ID_MALFORMADO_CA");
	}
	
	public IDMalFormadoCAException(String mensagem){
		super(mensagem);
	}

}