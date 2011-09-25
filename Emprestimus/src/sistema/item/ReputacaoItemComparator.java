package sistema.item;

import java.util.Comparator;
import java.util.Date;

import sistema.usuario.UsuarioIF;

public class ReputacaoItemComparator implements Comparator<UsuarioIF>{
	
	@Override
	public int compare(UsuarioIF usuario1, UsuarioIF usuario2) {
		int reputacaoUsuario1 = usuario1.getReputacao();        
	    int reputacaoUsuario2 = usuario2.getReputacao();
		return reputacaoUsuario1 - reputacaoUsuario2;
	}}
