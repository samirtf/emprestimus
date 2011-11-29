package iu.web.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.IsSerializable;



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
	private String amigos;
	private String itens;
	private String mensagens;
	
	public Controlador(Emprestimusweb entryPoint) {
		this.entryPoint = entryPoint;
	}

	public void abrirSessao(String idSessao) {
		this.idSessao = idSessao;
		entryPoint.abrirSessao(idSessao);
		atualizaFoto();
		atualizaHistorico();
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

	public void atualizaAmigos() {
		try {
			greetingService.getAmigos(idSessao, new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {}
				public void onSuccess(String result) {
					try {
						amigos = result;
						entryPoint.amigosFoiAtualizado();
					} catch (Exception e) {}
				}
			});
		} catch (Exception e) {}
	}
	
	public void atualizaMensagens() {
		try {
			greetingService.getMensagens(idSessao, new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {}
				public void onSuccess(String result) {
					try {
						mensagens = result;
						entryPoint.mensagensFoiAtualizado();
					} catch (Exception e) {}
				}
			});
		} catch (Exception e) {}
	}
	
	public void atualizaItens() {
		try {
			greetingService.getItens(idSessao, new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {}
				public void onSuccess(String result) {
					try {
						itens = result;
						entryPoint.itensFoiAtualizado();
					} catch (Exception e) {}
				}
			});
		} catch (Exception e) {}
	}

	/**
	 * @return idSessao
	 */
	public String getIdSessao() {
		return idSessao;
	}

	public String getNome() {
		if (nome == null)
			return "Lendo informações do perfil...";
		return nome;
	}

	public String getHistorico() {
		if (historico == null)
			return "Não há atividades";
		return historico;
	}

	public String getFoto() {
		return foto;
	}

	public String getAmigos() {
		if (amigos == null)
			return "Você não tem nenhum amigo";
		return amigos;
	}
	
	public String getItens() {
		if (itens == null)
			return "Você não possue nenhum ítem";
		return itens;
	}
	
	public String getMensagens() {
		if (mensagens == null)
			return "Não há mensagens";
		return mensagens;
	}

	public void fecharSessao() {
		try {
			greetingService.encerraSessao(idSessao, new AsyncCallback<String>() {
				public void onFailure(Throwable caught) {}
				public void onSuccess(String result) {
					entryPoint.onModuleLoad();
				}
			});
		} catch (Exception e) {}
		
		idSessao = null;
	}

	public void trocaSenha(final String senha) {
		greetingService.trocaSenha(idSessao, senha, new AsyncCallback<String>() {
			public void onFailure(Throwable caught) {}
			public void onSuccess(String result) {}
		});
	}
}
