package iu.web.client;

import iu.web.shared.UsuarioSimples;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.rpc.AsyncCallback;



/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public class Controlador {
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	
	private String idSessao;
	private Emprestimusweb entryPoint;
	
	private UsuarioSimples usuario;

	/**
	 * @param emprestimusweb
	 */
	public Controlador(Emprestimusweb entryPoint) {
		this.entryPoint = entryPoint;			
	}

	/**
	 * @param idSessao
	 * @throws Exception 
	 */
	public void abrirSessao(String idSessao) {
		this.idSessao = idSessao;
		criaUsuarioSimples();
		entryPoint.abrirSessao(idSessao);			
		
	}
	
	/**
	 * 
	 */
	private void criaUsuarioSimples() {
		try {
			greetingService.getUsuarioSimples(idSessao, new AsyncCallback<UsuarioSimples>() {
				@Override
				public void onFailure(Throwable caught) {
					
					// TODO Auto-generated method stub
					caught.printStackTrace();
					
				}
				@Override
				public void onSuccess(UsuarioSimples result) {
					try {
						usuario = result;
						entryPoint.usuarioFoiAtualizado();
//						notifyAll();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
	}

	/**
	 * @return idSessao
	 */
	public String getIdSessao() {
		return idSessao;
	}
	
//	public synchronized UsuarioSimples getUsuarioSimples() {
//		return usuario;
//	}

	/**
	 * @return
	 */
	public String getNome() {
		return usuario.getNome();
	}

	/**
	 * @return
	 */
	public String getHistorico() {
		return usuario.getHistorico();
	}

	/**
	 * @return
	 */
	public String getFoto() {
		return usuario.getFoto();
	}

	/**
	 * 
	 */
	public void fecharSessao() {
		idSessao = null;
		usuario = null;
		entryPoint.onModuleLoad();
	}

}