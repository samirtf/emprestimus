package iu.web.shared;

import java.io.Serializable;

/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 *
 */
public class UsuarioSimples implements Serializable{
	
	private static final long serialVersionUID = -406661456909671205L;
	private String id;
	private String nome;
	private String foto;
	private String historico;
	
	/**
	 * 
	 */
	public UsuarioSimples() {
		// TODO Auto-generated constructor stub
	}
//	
//	/**
//	 * @param id
//	 * @param nome
//	 * @param foto
//	 * @param historico
//	 */
//	public UsuarioSimples(String id, String nome, String foto, String historico) {
//		
//		//FIXME Testar parâmetros nulos
//		
//		this.id = id;
//		this.nome = nome;
//		this.foto = foto;
//		this.historico = historico;
//	}

	/**
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}
	/**
	 * @param nome nome que deseja configurar
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}
	/**
	 * @return foto
	 */
	public String getFoto() {
		return foto;
	}
	/**
	 * @param foto foto que deseja configurar
	 */
	public void setFoto(String foto) {
		this.foto = foto;
	}
	/**
	 * @return historico
	 */
	public String getHistorico() {
		return historico;
	}
	/**
	 * @param historico historico que deseja configurar
	 */
	public void setHistorico(String historico) {
		this.historico = historico;
	}

	/**
	 * @return id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id id que deseja configurar
	 */
	public void setId(String id) {
		this.id = id;
	}

}
