package iu.web.client;

import iu.web.shared.UsuarioSimples;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;



/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public class Controlador {
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	
	private String idSessao;
	private Emprestimusweb entryPoint;
	
	private UsuarioSimples usuario = new UsuarioSimples();

	/**
	 * @param emprestimusweb
	 */
	public Controlador(Emprestimusweb entryPoint) {
		synchronized (usuario) {
			this.entryPoint = entryPoint;			
		}
	}

	/**
	 * @param idSessao
	 * @throws Exception 
	 */
	public synchronized void abrirSessao(String idSessao) {
		synchronized (usuario) {
			this.idSessao = idSessao;
			criaUsuarioSimples();
			entryPoint.abrirSessao(idSessao);			
		}
		
	}
	
	/**
	 * 
	 */
	private void criaUsuarioSimples() {
		synchronized (usuario) {
			try {
				greetingService.getUsuarioSimples(idSessao, new AsyncCallback<UsuarioSimples>() {
					@Override
					public void onFailure(Throwable caught) {
						
						// TODO Auto-generated method stub
						caught.printStackTrace();
						
					}
					@Override
					public void onSuccess(UsuarioSimples result) {
						usuario = result;
					}
				});
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			
			}
		}
	}

	/**
	 * @return idSessao
	 */
	public String getIdSessao() {
		synchronized (usuario) {
			return idSessao;
		}
	}
	
	public synchronized UsuarioSimples getUsuarioSimples() {
		return usuario;
	}

	/**
	 * @return
	 */
	public synchronized String getNome() {
		synchronized (usuario) {
			return usuario.getNome();
		}
	}

	/**
	 * @return
	 */
	public synchronized String getHistorico() {
		synchronized (usuario) {
			return usuario.getHistorico();
		}
	}

}
