package iu.web.client;

import iu.web.shared.UsuarioSimples;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;
import com.google.gwt.user.client.ui.Image;



/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public class Controlador implements IsSerializable{
	private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);
	
	private String idSessao;
	private Emprestimusweb entryPoint;
	
	private String nome;
	private String foto;
	private String historico;
	
	
	
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
		entryPoint.abrirSessao(idSessao);
		atualizaFoto();
		atualizaHistorico();
		
		
//		criaUsuarioSimples();
	}

	public void atualizaHistorico() {
		try {
			greetingService.getHistoricoConjunto(idSessao, new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {}
				public void onSuccess(String result) {
					try {
						historico = result;
						entryPoint.historicoFoiAtualizado();
					} catch (Exception e) {}
				}
			});
		} catch (Exception e) {}
	}

	public void atualizaFoto() {
		try {
			greetingService.getImagem(idSessao, new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {}
				public void onSuccess(String result) {
					try {
						foto = result;
						entryPoint.fotoFoiAtualizada();
					} catch (Exception e) {}
				}
			});
		} catch (Exception e) {}
	}

	public void atualizaNome() {
		try {
			greetingService.getNome(idSessao, new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {}
				public void onSuccess(String result) {
					try {
						nome = result;
						entryPoint.nomeFoiAtualizado();
					} catch (Exception e) {}
				}
			});
		} catch (Exception e) {}
	}
	
//	private void criaUsuarioSimples() {
//		try {
//			greetingService.getUsuarioSimples(idSessao, new AsyncCallback<UsuarioSimples>() {
//				@Override
//				public void onFailure(Throwable caught) {
//					
//					// TODO Auto-generated method stub
//					caught.printStackTrace();
//					
//				}
//				@Override
//				public void onSuccess(UsuarioSimples result) {
//					try {
//						usuario = result;
//						entryPoint.usuarioFoiAtualizado();
//					} catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			});
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		
//		}
//	}


	/**
	 * @return idSessao
	 */
	public String getIdSessao() {
		return idSessao;
	}

	/**
	 * @return
	 */
	public String getNome() {
		if (nome == null)
			return "Carregando...";
		return nome;
	}

	/**
	 * @return
	 */
	public String getHistorico() {
		if (historico == null)
			return "Carregando...";
		return historico;
	}

	/**
	 * @return
	 */
	public String getFoto() {
		return foto;
	}

	/**
	 * 
	 */
	public void fecharSessao() {
		try {
			greetingService.encerraSessao(idSessao, new AsyncCallback<String>() {
				@Override
				public void onFailure(Throwable caught) {
					
					// TODO Auto-generated method stub
					caught.printStackTrace();
					
				}
				@Override
				public void onSuccess(String result) {
					entryPoint.onModuleLoad();
				}
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}
		idSessao = null;
		usuario = null;
	}

	/**
	 * @param senha
	 */
	public void trocaSenha(final String senha) {
		greetingService.trocaSenha(idSessao, senha, new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub
				trocaSenha(senha);
			}

			@Override
			public void onSuccess(String result) {
				// TODO Auto-generated method stub
			}
		});
	}

}
