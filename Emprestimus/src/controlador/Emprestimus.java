package controlador;

public class Emprestimus implements EmprestimusIF{

	@Override
	public void criarUsuario(String login, String nome, String endereco) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int abrirSessao(String login) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getAtributoUsuario(String login, String atributo) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Metodo que zera o Sistema.
	 * 
	 */
	public void zerarSistema() {
		//TODO zerar dados do sistema
	}

	/**
	 * Metodo que encerra o Sistema.
	 * 
	 */
	public void encerrarSistema() {
		//TODO posteriormente deve-se salvar dados em DB.
	}
	

}
