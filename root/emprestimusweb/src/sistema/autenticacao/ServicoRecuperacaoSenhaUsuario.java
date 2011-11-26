package sistema.autenticacao;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


import sistema.usuario.Usuario;
import sistema.usuario.UsuarioIF;

public class ServicoRecuperacaoSenhaUsuario {
	
	private static ServicoRecuperacaoSenhaUsuario sharedInstance;
	private static int tempoHorasPassadasRedefinicaoSenha = 0;
	
	private static Map<String, Date> usuariosRedefinicoesRequisitadas = new TreeMap<String, Date>();
	
	private static ExecutorService threadExecutor = Executors.newFixedThreadPool(50);
	
	private ServicoRecuperacaoSenhaUsuario(){}
	
	public synchronized static ServicoRecuperacaoSenhaUsuario getInstance(){
		if(sharedInstance == null){
			sharedInstance = new ServicoRecuperacaoSenhaUsuario();
		}
		return sharedInstance;
	}
	
	public void iniciarServico(){
		threadExecutor = Executors.newFixedThreadPool(50);
	}
	
	public void pararServico(){
		threadExecutor.shutdown();
	}
	
	protected void adicionaRequisicaoRedefinicaoSenha(String login){
		Configuracao configuracao = Configuracao.getInstance();
		GregorianCalendar gc = new GregorianCalendar();
		gc.add(Calendar.HOUR, configuracao.getTempoHorasPrazoRefinicaoSenha());
		usuariosRedefinicoesRequisitadas.put(login, gc.getTime());
	}
	
	public void removerRequisicaoRedefinicaoSenha(String login){
		usuariosRedefinicoesRequisitadas.remove(login);
	}
	
	public synchronized void acionaRedefinicaoSenha(UsuarioIF usuario) throws Exception{
		adicionaRequisicaoRedefinicaoSenha(usuario.getLogin());
		threadExecutor.submit(new RedefineSenhaTask(usuario));
	}
	
	public boolean requisitouRedefinicaoSenha(String login){
		Iterator<Entry<String, Date>> iterador = usuariosRedefinicoesRequisitadas.entrySet().iterator();
		while(iterador.hasNext()){
			Entry<String, Date> entrada = iterador.next();
			if(entrada.getKey().equals(login)){
				GregorianCalendar dataCorrente = new GregorianCalendar();
				dataCorrente.add(Calendar.HOUR, tempoHorasPassadasRedefinicaoSenha);
				dataCorrente.add(Calendar.MILLISECOND, 0);
				if(dataCorrente.getTime().before(entrada.getValue())){
					return true;
				}
				iterador.remove();
				return false;
			}
		}
		return false;
	}
	
	public void adicionaHorasServicoRedefSenha(int horas){
		tempoHorasPassadasRedefinicaoSenha += horas;
	}
	
	
	public static void main(String[] args) throws Exception{
		
	    
		ServicoRecuperacaoSenhaUsuario srs = new ServicoRecuperacaoSenhaUsuario();
		UsuarioIF usuario = new Usuario("login", "Samir Trajano FEitosa", "endereco");
		usuario.cadastrarEmailRedefinicaoSenha("samircc20092@gmail.com");
		srs.acionaRedefinicaoSenha(usuario);
		
		Thread.sleep(6000);
		System.out.println(usuario.getCartaoAcessoRedefSenha());
		System.out.println(usuario.logar("senhaAleatoria()"));
		System.out.println(usuariosRedefinicoesRequisitadas.size());
	}

}
