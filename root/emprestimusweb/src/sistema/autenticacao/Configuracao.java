package sistema.autenticacao;

public class Configuracao {
	
	private static Configuracao sharedInstance;
	
	private static int tempoHorasPrazoRedefinicao = 1;
	private static String emailSMTP = "samircc20092@gmail.com";
	private static String usernameSMTP = "samircc20092";
	private static String passwordSMTP = "Roguespear";
	private static long timeoutRedefineSenhaSMTP = 30000;
	
	private Configuracao(){}
	
	public synchronized static Configuracao getInstance(){
		if(sharedInstance == null){
			sharedInstance = new Configuracao();
			inicializaConfiguracao();
		}
		return sharedInstance;
	}

	private static void inicializaConfiguracao() {
		tempoHorasPrazoRedefinicao = 1;
		emailSMTP = "samircc20092@gmail.com";
		usernameSMTP = "samircc20092";
		passwordSMTP = "Roguespear";
	}
	
	protected int getTempoHorasPrazoRefinicaoSenha(){
		return tempoHorasPrazoRedefinicao;
	}
	
	protected String getEmailSMTP(){
		return emailSMTP;
	}
	
	protected String getUsernameSMTP(){
		return usernameSMTP;
	}
	
	protected String getSenhaSMTP(){
		return passwordSMTP;
	}
	
	protected long getTimeoutRedefineSenhaSMTP(){
		return timeoutRedefineSenhaSMTP;
	}

}
