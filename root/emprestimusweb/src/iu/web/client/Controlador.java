package iu.web.client;



/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public class Controlador {
	private String idSessao;
	private Emprestimusweb entryPoint;

	/**
	 * @param emprestimusweb
	 */
	public Controlador(Emprestimusweb entryPoint) {
		this.entryPoint = entryPoint;
	}

	/**
	 * @param idSessao
	 */
	public void abrirSessao(String idSessao) {
		this.idSessao = idSessao;
		entryPoint.abrirSessao(idSessao);
		
	}
	
	/**
	 * @return idSessao
	 */
	public String getIdSessao() {
		return idSessao;
	}

}
