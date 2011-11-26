package iu.web.shared;

import java.io.Serializable;

/**
 * @author José Nathaniel L de Abrante - nathaniel.una@gmail.com
 * 
 */
public class UsuarioSimples implements Serializable {

	private static final long serialVersionUID = -406661456909671205L;
	private static final int INDICE_ID = 0;
	private static final int INDICE_NOME = 1;
	private static final int INDICE_FOTO = 2;
	private static final int INDICE_HISTORICO = 3;

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
	// /**
	// * @param id
	// * @param nome
	// * @param foto
	// * @param historico
	// */
	// public UsuarioSimples(String id, String nome, String foto, String
	// historico) {
	//
	// //FIXME Testar parâmetros nulos
	//
	// this.id = id;
	// this.nome = nome;
	// this.foto = foto;
	// this.historico = historico;
	// }

	/**
	 * @return nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            nome que deseja configurar
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
	 * @param foto
	 *            foto que deseja configurar
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
	 * @param historico
	 *            historico que deseja configurar
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
	 * @param id
	 *            id que deseja configurar
	 */
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(id);
		sb.append("|");
		sb.append(nome);
		sb.append("|");
		sb.append(foto);
		sb.append("|");
		sb.append(historico);

		return sb.toString();
	}

	/**
	 * Cria um novo {@code UsuarioSimples} a partir de uma {@code String}
	 * devidamente formatada
	 * 
	 * @param entrada
	 *            {@code String} de que será transformada no
	 *            {@code UsuarioSimples}
	 * @return Um novo {@code UsuarioSimples}
	 * @throws Exception
	 */
	public static UsuarioSimples valueOf(String entrada) throws Exception {
		UsuarioSimples usuario = new UsuarioSimples();
		String[] array = entrada.split("|");
		usuario.setId(array[INDICE_ID]);
		usuario.setNome(array[INDICE_NOME]);
		usuario.setFoto(array[INDICE_FOTO]);
		usuario.setHistorico(array[INDICE_HISTORICO]);

		return usuario;
	}

}
