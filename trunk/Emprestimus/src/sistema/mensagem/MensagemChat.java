package sistema.mensagem;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MensagemChat implements MensagemChatIF, Comparable<MensagemChatIF>{

	String mensagem = "";
	Date data;
	
	public MensagemChat(String mensagem) {
		this.mensagem = mensagem;
		setData();
	}
	
	public synchronized void setData(){
		this.data = new GregorianCalendar().getTime();
	}
	
	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Date getData() {
		return data;
	}

	public synchronized void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString(){
		return "DATA:"+(String.format("%1$te/%1$tm/%1$tY - %tT", data))+" MENSAGEM:"+mensagem;
	}

	@Override
	public int compareTo(MensagemChatIF outra) {
		return getData().compareTo(outra.getData());
	}
	
	
	

}
