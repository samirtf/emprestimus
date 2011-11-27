package iu.web.server.sistema.autenticacao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

public class Configuracao {
	
	private static final String caminhoArquivo = "./emprestimusweb/conf/conf.ins";
    private static final String caminhoDiretorio = "./emprestimusweb/conf/";
	
	private static Configuracao sharedInstance;
	
	private static int tempoHorasPrazoRedefinicao = 48;

	private static String emailSMTP = "email.com";
	private static String usernameSMTP = "email";
	private static String passwordSMTP = "umasenha";
	private static long timeoutRedefineSenhaSMTP = 4000;
	private static String senhaRedefAcessoTeste = "";
	private static String diretorioBD = "";
	private static int delayTimerTaskBD = 3000;   // delay de 1 seg * 60 = 1 minuto.
    private static int intervalTimerTaskBD = 240000;  // intervalo de 1 seg * 60 * 4 = 4 minutos.
	
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
	
	private static void verificarConsistencia() {
        File arquivo = new File(caminhoArquivo);
        if(!new File(caminhoDiretorio).exists())
                new File(caminhoDiretorio).mkdirs();
            if(!arquivo.exists()){
                File novoArquivo = new File(caminhoArquivo);
                try {
					novoArquivo.createNewFile();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

            }
    }

	private static void inicializaConfiguracao() {
		
		tempoHorasPrazoRedefinicao = 48;
		emailSMTP = "emprestimuswebsi@gmail.com";
		usernameSMTP = "emprestimuswebsi";
		passwordSMTP = "Roguespear";
	
        File arquivo = new File(caminhoArquivo);
        if(!arquivo.exists() || !arquivo.canRead()){
            verificarConsistencia();
        }else{
        	String[] configuracoes = new String[9];
        	File f = new File("./");
        	try {
				System.out.println(f.getCanonicalPath());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            BufferedReader in = null;
            try {
                in = new BufferedReader(new FileReader(caminhoArquivo));
                String str;
                int contador = 0;
                while (in.ready()) {
                    str = in.readLine();
                    String[] valores = str.split(Pattern.quote("|"));
                    //System.out.println("temp0"+valores[0]+"  "+"temp1"+valores[1]);
                    configuracoes[contador] = valores[1].trim();
                    contador++;
//                    tempoHorasPrazoRedefinicao | 48
//                    emailSMTP | email.com
//                    usernameSMTP | email
//                    passwordSMTP | umasenha
//                    timeoutRedefineSenhaSMTP | 4000
//                    senhaRedefAcessoTeste | 
//                    diretorioBD | emprestimusweb/bd/
                }
                contador = 0;
//                for(String s : configuracoes){
//                	System.out.println(":"+s+":");
//                }
            } catch (IOException e) {
            	e.printStackTrace();
            }finally{
            	try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            tempoHorasPrazoRedefinicao = Integer.valueOf(configuracoes[0]);
            emailSMTP = configuracoes[1];
            usernameSMTP = configuracoes[2];
            passwordSMTP = configuracoes[3];
            timeoutRedefineSenhaSMTP = Integer.valueOf(configuracoes[4]);
            senhaRedefAcessoTeste = configuracoes[5];
            setDiretorioBD(configuracoes[6]);
            setDelayTimerTaskBD(Integer.valueOf(configuracoes[7]));
            setIntervalTimerTaskBD(Integer.valueOf(configuracoes[8]));
            
        }
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

	public String getDiretorioBD() {
		return diretorioBD;
	}

	private static void setDiretorioBD(String diretorioBD) {
		Configuracao.diretorioBD = diretorioBD;
	}
	

	public int getDelayTimerTaskBD() {
		return delayTimerTaskBD;
	}

	private static void setDelayTimerTaskBD(int delayTimerTaskBD) {
		Configuracao.delayTimerTaskBD = delayTimerTaskBD;
	}

	public int getIntervalTimerTaskBD() {
		return intervalTimerTaskBD;
	}

	private static void setIntervalTimerTaskBD(int intervalTimerTaskBD) {
		Configuracao.intervalTimerTaskBD = intervalTimerTaskBD;
	}

	
	public static void main(String[] args){
		int delay = 5000;   // delay for 5 sec.
	    int interval = 4000;  // iterate every sec.
	    Timer timer = new Timer();
	    Long i = (long) 0;
	    timer.scheduleAtFixedRate(new TimerTask() {
	            public void run() {
	                System.out.println("OLAAAA");
	                
	            }
	        }, delay, interval);
	    
	    while(i < 100){
	    	System.out.println(i);
	    	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	i++;
	    }
	}

}
