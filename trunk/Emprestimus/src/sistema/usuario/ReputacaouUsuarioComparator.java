package sistema.usuario;

import java.util.Comparator;
import java.util.Date;


public class ReputacaouUsuarioComparator implements Comparator<UsuarioIF>{
	
	@Override
	public int compare(UsuarioIF usuario1, UsuarioIF usuario2) {
		int reputacaoUsuario1 = usuario1.getReputacao();        
	    int reputacaoUsuario2 = usuario2.getReputacao();
		return reputacaoUsuario1 - reputacaoUsuario2;
	}}
