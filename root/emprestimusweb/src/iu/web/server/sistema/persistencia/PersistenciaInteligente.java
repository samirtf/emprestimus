package iu.web.server.sistema.persistencia;

import iu.web.server.sistema.autenticacao.Autenticacao;
import iu.web.server.sistema.autenticacao.AutenticacaoIF;
import iu.web.server.sistema.dao.AcervoDeItensDAO;
import iu.web.server.sistema.dao.AcervoDeItensFileDAO;
import iu.web.server.sistema.dao.BancoDeEmprestimosDAO;
import iu.web.server.sistema.dao.BancoDeEmprestimosFileDAO;
import iu.web.server.sistema.dao.ChatDAO;
import iu.web.server.sistema.dao.ChatFileDAO;
import iu.web.server.sistema.dao.CorreioDAO;
import iu.web.server.sistema.dao.CorreioFileDAO;
import iu.web.server.sistema.dao.EmprestimoDAO;
import iu.web.server.sistema.dao.EmprestimoFileDAO;
import iu.web.server.sistema.dao.GerenciadorDeNotificacoesDAO;
import iu.web.server.sistema.dao.GerenciadorDeNotificacoesFileDAO;
import iu.web.server.sistema.dao.ItemDAO;
import iu.web.server.sistema.dao.ItemFileDAO;
import iu.web.server.sistema.dao.NotificacaoDAO;
import iu.web.server.sistema.dao.NotificacaoFileDAO;
import iu.web.server.sistema.dao.RelacionamentosUsuariosDAO;
import iu.web.server.sistema.dao.RelacionamentosUsuariosFileDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class PersistenciaInteligente {
	
	private List<PersistenciaListener> persistenciaListeners;
	
	int delay = 5000;   // delay de 5 seg.
    int interval = 1000;  // intervalo de 1 seg.
    Timer timer = new Timer();
    
    public PersistenciaInteligente(){
    	
    	persistenciaListeners = new ArrayList<PersistenciaListener>();
    	persistenciaListeners.add(Autenticacao.getInstance());
    	persistenciaListeners.add(new ItemFileDAO());
    	persistenciaListeners.add(new EmprestimoFileDAO());
    	persistenciaListeners.add(new NotificacaoFileDAO());
    	persistenciaListeners.add(new ChatFileDAO());
    	persistenciaListeners.add(new AcervoDeItensFileDAO());
    	persistenciaListeners.add(new BancoDeEmprestimosFileDAO());
    	persistenciaListeners.add(new CorreioFileDAO());
    	persistenciaListeners.add(new GerenciadorDeNotificacoesFileDAO());
    	persistenciaListeners.add(new RelacionamentosUsuariosFileDAO());
    	
    	for(PersistenciaListener listenersDao : persistenciaListeners){
    		listenersDao.iniciarListener();
    	}
    	
    	
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
            	for(PersistenciaListener listenersDao : persistenciaListeners){
            		listenersDao.notificaPersistenciaDoSistema();
            	}
            }
        }, delay, interval);
    
    }

}
