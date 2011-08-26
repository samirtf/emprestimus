package excecoes;

public class SenhaMalFormadaCAException extends Exception{
	
	public SenhaMalFormadaCAException() {
		super("INCONSISTENCIA:_SENHA_MALFORMADA_CA");
	}
	
	public SenhaMalFormadaCAException(String mensagem){
		super(mensagem);
	}

}
