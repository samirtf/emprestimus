package sistema.autenticacao;

public class Configuracao {
	
	private static Configuracao sharedInstance;
	
	private static int tempoHorasPrazoRedefinicao = 1;

	private static String emailSMTP = "email.com";
	private static String usernameSMTP = "email";
	private static String passwordSMTP = "umasenha";
	private static long timeoutRedefineSenhaSMTP = 4000;
	
	private static String senhaRedefAcessoTeste = "";
	
	public String getSenhaRedefAcessoTeste(){
		return senhaRedefAcessoTeste;
	}
	
	public void setSenhaRedefAcessoTeste(String senhaTeste){
		senhaRedefAcessoTeste = senhaTeste;
	}
	
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
		emailSMTP = "emprestimuswebsi@gmail.com";
		usernameSMTP = "emprestimuswebsi";
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
	
	public long getTimeoutRedefineSenhaSMTP(){
		return timeoutRedefineSenhaSMTP;
	}


}
