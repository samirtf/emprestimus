package codigo.sistema.autenticacao;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import codigo.logica.pessoas.PessoaIF;
import codigo.logica.pessoas.Usuario;
import codigo.utilitarios.ValidadorString;

public class Autenticacao {
	
	private static Autenticacao instanciaUnica = null;
	
	private static Set<Usuario> usuariosCadastrados = new TreeSet<Usuario>();
	private static Map<Long, Usuario> sessoes = new TreeMap<Long, Usuario>();
	private static String [] nome_invalido = {"Nome inválido", "Nome inválido", "Nome inválido"};
	private static String [] login_invalido = {"Login inválido", "Login inválido", "Login inválido", "Login inválido"}; 
	private static String [] endereco_invalido = {"Endereco inválido", "Endereco inválido", "Endereco inválido"}; 
	private static String [] atributo_invalido = {"Atributo inválido", "Atributo inválido", "Atributo inválido", "Atributo inválido"}; 
		
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

	public boolean criarUsuario(String nome, String login, String endereco) throws IllegalArgumentException {
		if (login != null && login.contains(" ")) throw new IllegalArgumentException(login_invalido[0]);
		Usuario usuario = new Usuario(ValidadorString.pegaString(nome_invalido, nome),
									  ValidadorString.pegaString(login_invalido, login),
									  ValidadorString.pegaString(endereco_invalido, endereco));
		if(usuariosCadastrados.contains(usuario)) // Assegura que nao existirao dois usuarios iguais
			throw new IllegalArgumentException("Já existe um usuário com este login");
		return usuariosCadastrados.add(usuario);
	}

	public boolean abrirSessao(String login) throws IllegalArgumentException {
		Usuario user = getUsuario(ValidadorString.pegaLogin(login_invalido, login));
		if (user != null) {
			return sessoes.put(geraIDSessao(), user) == null;
		} else {
			throw new IllegalArgumentException("Usuário inexistente");
		}
	}
	
	/**
	 * Captura o usuario cadastrado com aquele login
	 * 
	 * @param login
	 * 		Login a ser verificado
	 * @return
	 * 		O Usuario com aquele login ou Null se nao existir o login.
	 */
	public Usuario getUsuario(String login) {
		for (Usuario user : usuariosCadastrados) {
			if (user.getLogin().equals(login)) {
				return user;
			}
		}
		return null;
	}
	
	public boolean estaLogado(PessoaIF pessoa) {
		return sessoes.values().contains(pessoa);
	}
	
	private long geraIDSessao() {
		double semente = (System.currentTimeMillis() - ((Math.random() * 21011853)));
		Random rand = new Random((long) (semente - semente %10));
		long ID = rand.nextLong();
		
		if(!(sessoes.keySet().contains((Long) ID))) {
			return ID;
		}
		return geraIDSessao();
	}

	public String getAtributo(String login, String atributo) throws IllegalArgumentException {
		login = ValidadorString.pegaLogin(login_invalido, login);
		Usuario user = getUsuario(login);
		if (user == null) {
			throw new IllegalArgumentException("Usuário inexistente");
		}
		atributo = ValidadorString.pegaLogin(atributo_invalido, atributo);
		
		String [] atributos_validos = {"nome", "endereco"};
		int indice_do_atributo = 0;
		for (; indice_do_atributo < atributos_validos.length; indice_do_atributo++) {
			if (atributos_validos[indice_do_atributo++].equals(atributo)) {
				break;
			}
		}
		switch (indice_do_atributo) {
		case 1:
			return user.getNome();
			
		case 2:
			return user.getEndereco();

		default:
			throw new IllegalArgumentException("Atributo inexistente");
		}
	}

	public boolean zerarSistema() {
		inicializaAtributos();
		return (usuariosCadastrados.size() == 0 && sessoes.size() == 0);
	}
	
	public void encerrarSistema() {
		System.exit(1);
	}
	
}

















