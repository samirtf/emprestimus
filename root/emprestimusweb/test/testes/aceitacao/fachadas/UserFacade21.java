package testes.aceitacao.fachadas;

import sistema.autenticacao.ServicoRecuperacaoSenhaUsuario;

public class UserFacade21 extends UserFacade20 {

	public void criarUsuario(String login, String senha, String nome, String endereco) throws Exception {
		sistema.criarUsuario(login, senha, nome, endereco);
	}
	
	public String abrirSessao(String login, String senha) throws Exception {
		return sistema.abrirSessao(login, senha);
	}
	
	public void alterarSenha(String idSessao, String senhaAtual, String senhaNova) throws Exception {
		sistema.alterarSenha(idSessao, senhaAtual, senhaNova);
	}
	
	public void encerrarSessao(String idSessao) throws Exception {
		sistema.encerrarSessao(idSessao);
	}
	
	public void cadastrarEmailRedefinicaoSenha(String idSessao, String email) throws Exception {
		sistema.cadastrarEmailRedefinicaoSenha(idSessao, email);
	}
	
	public String recuperarSenha(String login, String email) throws Exception {
		return sistema.recuperaSenha(login, email);
	}
	
	public void sleepSystemTeste(int milissegundos) throws Exception {
		sistema.sleepSystemTeste(milissegundos);
	}
	
	public synchronized void adicionaHorasRedefSenha(int horas) throws Exception {
		sistema.adicionaHorasRedefSenha(horas);
	}

}
