package codigo.utilitarios;

public class TrataAcentuacao {

	
	public static String geraCodigoUnicode(char letra) {
	    String hexa = Integer.toHexString( (int)letra );
	    
	    String prefix;
	    if( hexa.length() == 1 ) {
	        prefix = "\\u000";
	    } else if( hexa.length() == 2 ) {
	        prefix = "\\u00";
	    } else if( hexa.length() == 3 ) {
	        prefix = "\\u0";
	    } else {
	        prefix = "\\u";
	    }

	    return prefix + hexa;
	}
	
}
