package codigo.sistema.autenticacao;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import codigo.logica.pessoas.Usuario;


import excecoes.IDMalFormadoCAException;
import excecoes.LoginMalFormadoCAException;

public class Autenticacao {
	
	private static Autenticacao instanciaUnica = null;
	
	private static Set<Usuario> usuariosCadastrados = new TreeSet<Usuario>();
	private static Map<Long, Usuario> sessoes = new TreeMap<Long, Usuario>();
	
	private Autenticacao() {}
	
	/**
	 * Recupera a instancia de Autenticacao.
	 * Nesta situacao, temos a intencao de evitar a despesa de 
	 * pegar o bloqueio da classe Singleton cada vez que o metodo 
	 * eh chamado. O bloqueio eh agarrado somente se a 
	 * instancia Singleton nao existe, e em seguida a existencia
	 * da ocorrencia eh verificada novamente no caso de outro 
	 * segmento passar o primeiro cheque um instante antes 
	 * de o segmento atual.
	 *  
	 * @return A instancia de RelacionamentoAmigos.
	 */
	public static synchronized Autenticacao getInstance(){
		if (instanciaUnica == null) {
			synchronized (Autenticacao.class) {
				if (instanciaUnica == null) {
					instanciaUnica = new Autenticacao();
					inicializaAtributos();
				}
			}
		}
		return instanciaUnica;  
	}
	
	private static void inicializaAtributos() {
		usuariosCadastrados = new TreeSet<Usuario>();
		sessoes = new TreeMap<Long, Usuario>();
	}

	public boolean cadastraUsuario(String nome, String login, String endereco, int id){
		Usuario usuario = new Usuario(nome, login, endereco, id);
		if(usuariosCadastrados.contains(usuario)) // Assegura que nao existirao dois usuarios iguais
			return false;
		return usuariosCadastrados.add(usuario);
	}
	
}
