/**
 * 
 */
package sistema.item;

import static sistema.utilitarios.Validador.assertStringNaoVazia;
import static sistema.utilitarios.Validador.asserteTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import sistema.autenticacao.Autenticacao;
import sistema.emprestimo.BancoDeEmprestimos;
import sistema.emprestimo.Conta;
import sistema.emprestimo.Emprestimo;
import sistema.emprestimo.EmprestimoIF;
import sistema.persistencia.EmprestimoRepositorio;
import sistema.persistencia.ItemRepositorio;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;

/**
 * @author Mobile
 * 
 */
public class AcervoDeItens {
	private static AcervoDeItens acervoDeItens;
	private static Map<String, Bauh> bauhs;

	private AcervoDeItens() {
		bauhs = new TreeMap<String, Bauh>();
	}

	public static AcervoDeItens getInstance() {
		if (acervoDeItens == null) {
			acervoDeItens = new AcervoDeItens();

			return acervoDeItens;
		}
		return acervoDeItens;
	}

	public void adicionaBauhAoUsuario(String usuario) throws Exception {
		if (bauhs.containsKey(usuario))
			throw new Exception(
					Mensagem.PROPRIETARIO_BAUH_JAH_CADASTRADO.getMensagem());
		bauhs.put(usuario, new Bauh(usuario));

	}

	public void removeContaDoUsuario(String usuario) throws Exception {
		bauhs.remove(usuario);
	}

	public Bauh getConta(String login) throws Exception {
		return bauhs.get(login);
	}



}
