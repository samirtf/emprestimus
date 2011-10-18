package sistema.atualizacao;

import java.util.Date;

public interface Atualizacao {
	
	public Date getData();
	
	public String getMensagem();
	
	public String getId();
	
	public Atualizacao setId(String id) throws Exception;
	
	
	

}
