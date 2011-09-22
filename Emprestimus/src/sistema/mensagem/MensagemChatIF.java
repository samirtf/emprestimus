package sistema.mensagem;

import java.util.Date;

public interface MensagemChatIF {
	
	public String getMensagem();

	public void setMensagem(String mensagem);

	public Date getData();

	public void setData(Date data);

	@Override
	public String toString();
	

}
