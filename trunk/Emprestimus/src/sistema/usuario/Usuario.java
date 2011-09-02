package sistema.usuario;

import sistema.item.ItemIF;

public class Usuario implements UsuarioIF {
	
	private String login, nome, endereco;
	
	/**
	 * COntrutor padrao eh privado e nao oferece implementacao.
	 */
	private Usuario(){}
	
	/**
	 * Constroi um usuario a partir de um login, nome e endereco.
	 * @param login
	 * 		O login do usuario.
	 * @param nome
	 * 		O nome do usuario.
	 * @param endereco
	 * 		O endereco do usuario.
	 */
	public Usuario( String login , String nome, String endereco ) throws Exception{
		/* Se login invalido segundo as especificacoes, lancar excecao com mensagem
		 * padronizada nos requisitos. Consultar testes de aceitacao.
		 */
		/* Se nome invalido segundo as especificacoes, lancar excecao com mensagem
		 * padronizada nos requisitos. Consultar testes de aceitacao.
		 */
		/* Se endereco invalido segundo as especificacoes, lancar excecao com mensagem
		 * padronizada nos requisitos. Consultar testes de aceitacao.
		 */
		
		this.login = login;
		this.nome = nome;
		this.endereco = endereco;
	}

	@Override
	public void setLogin(String login) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setNome(String nome) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEndereco(String endereco) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getLogin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getNome() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getEndereco() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cadastrarItem(String nome, String descricao, String categoria) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removerItem(String idItem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getListaIdItens() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemIF getInformacoesItem(String idItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int qntItens() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int qntItensEmprestados() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getListaIdItensEmprestados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int qntItensBeneficiados() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getListaIdItensBeneficiados() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ItemIF getInformacoesItemBeneficiado(String idItem) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean estahItemDisponivel(String idItem) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equals(UsuarioIF outroUsuario) {
		// TODO Auto-generated method stub
		return false;
	}

}
