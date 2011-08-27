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
	
	private Set<Usuario> usuariosCadastrados = new TreeSet<Usuario>();
	
	public boolean cadastraUsuario(String nome, String login, String endereco, int id){
		Usuario usuario = new Usuario(nome, login, endereco, id);
		if(usuariosCadastrados.contains(usuario))
			return false;
		return usuariosCadastrados.add(usuario);
	}
	
	
	private Map<Long, Usuario> sessoes = new TreeMap<Long, Usuario>();

}
