package excecoes;

public class CartaoDeAcessoCorrompidoCAException extends Exception{
	
	public CartaoDeAcessoCorrompidoCAException() {
		super("INCONSISTENCIA:_CARTOES_DE_ACESSO_CORROMPIDOS");
	}
	
	public CartaoDeAcessoCorrompidoCAException(String mensagem){
		super(mensagem);
	}

}
