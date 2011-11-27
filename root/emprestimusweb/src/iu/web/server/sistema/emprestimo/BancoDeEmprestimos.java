/**
 * 
 */
package iu.web.server.sistema.emprestimo;

import static iu.web.server.sistema.utilitarios.Validador.assertStringNaoVazia;
import static iu.web.server.sistema.utilitarios.Validador.asserteTrue;

import iu.web.server.sistema.autenticacao.Autenticacao;
import iu.web.server.sistema.autenticacao.Configuracao;
import iu.web.server.sistema.dao.EmprestimoFileDAO;
import iu.web.server.sistema.dao.ItemFileDAO;
import iu.web.server.sistema.item.Bauh;
import iu.web.server.sistema.item.ItemIF;
import iu.web.server.sistema.usuario.UsuarioIF;
import iu.web.server.sistema.utilitarios.Mensagem;
import iu.web.server.sistema.utilitarios.Validador;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Mobile
 */
public class BancoDeEmprestimos{

	private static BancoDeEmprestimos bancoDeEmprestimos;
	private static Map<String, Conta> contas;
	

	private BancoDeEmprestimos() {
		contas = new TreeMap<String, Conta>();
		
		Configuracao conf = Configuracao.getInstance();
		File arquivo = new File("./"+conf.getDiretorioBD()+"bancoEmprestimos.bd");
		File diretorio = new File("./"+conf.getDiretorioBD());
		if(!diretorio.exists()){
			diretorio.mkdir();
			ObjectOutputStream objectOut = null;
			try {
				arquivo.createNewFile();
				Object[] vetor = new Object[1];
				vetor[0] =  new TreeMap<String, Conta>();
				objectOut = new ObjectOutputStream(
	                    new BufferedOutputStream(new FileOutputStream("./"+conf.getDiretorioBD()+"bancoEmprestimos.bd")));
	                objectOut.writeObject(vetor);
	                
			} catch (IOException e) {
				e.printStackTrace();
			}finally{
				try {
					objectOut.close();
				} catch (IOException e) {}
			}
			
		}else{
			try {
				inicializarDados();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * @return BancoDeEmprestimos
	 */
	public static BancoDeEmprestimos getInstance() {
		if (bancoDeEmprestimos == null) {
			bancoDeEmprestimos = new BancoDeEmprestimos();

			return bancoDeEmprestimos;
		}
		return bancoDeEmprestimos;
	}
	
	private static void inicializarDados() throws Exception {
		Configuracao conf = Configuracao.getInstance();
        
        ObjectInputStream objectIn = null;
        try{
        	objectIn = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream("./"+conf.getDiretorioBD()+"bancoEmprestimos.bd")));
            Object[] vetor = ((Object[])objectIn.readObject());
            contas = ((TreeMap<String, Conta>) vetor[0]);
        
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            objectIn.close();
        }
        
    }

	/**
	 * Cadastra um usuario no banco de emprestimos
	 * 
	 * @param usuario - String
	 * 		Usuario a ser cadastrado
	 * 
	 * @throws Exception
	 * 		Caso o usuario já esteja cadastrado
	 */
	public void adicionaContaAoUsuario(String usuario) throws Exception {
		if (contas.containsKey(usuario))
			throw new Exception(
					"Mensagem.PROPRIETARIO_CONTA_JAH_CADASTRADO.getMensagem()");
		contas.put(usuario, new Conta(usuario));
	}

	/**
	 * Remove um usuario no banco de emprestimos
	 * 
	 * @param usuario - String
	 * 		Usuario a ser cadastrado
	 * 
	 * @throws Exception
	 * 		Caso o usuario já esteja cadastrado
	 */
	public void removeContaDoUsuario(String usuario) throws Exception {
		contas.remove(usuario);
	}

	/**
	 * @param login - String
	 * 		Login do usuario desejado
	 * 
	 * @return Conta
	 * 		Conta do usuario desejado
	 * 
	 * @throws Exception
	 */
	public Conta getConta(String login) throws Exception {
		return contas.get(login);
	}
	
	/**
	 * Adiciona uma requisição de emprestimo de um amigo
	 * 
	 * @param login - String
	 * 		amigo desejado
	 * @param emp - EmprestimoIF
	 * 		emprestimo a ser cadastrado na requisição
	 * 
	 * @throws Exception
	 * 		Caso a conta fornecida seja inexistente
	 */
	public void adicionarRequisicaoEmprestimoEmEsperaDeAmigo(String login,
			EmprestimoIF emp) throws Exception {
		if (!contas.containsKey(login))
			throw new Exception(Mensagem.PROPRIETARIO_CONTA_INEXISTENTE.getMensagem());
		contas.get(login).getEmprestimosRequeridosPorAmigosEmEspera().add(emp);
		// this.emprestimosRequeridosPorAmigosEmEspera.add(emp);
	}

	/**
	 * Requisita um emprestimo
	 * 
	 * @param login - String
	 * 		Loguin do usuario
	 * @param idItem - String
	 * 		ID do item desejado
	 * @param duracao - String
	 * 		Duração do emprestimo
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 * 		caso o seja fornecido algum parametro invalido ou o usuario não tenha permissao para requisitar um
	 * 			emprestimo do item em questão.
	 */
	public synchronized String requisitarEmprestimo(String login, String idItem,
			int duracao) throws Exception {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(),
				Mensagem.ID_ITEM_INVALIDO.getMensagem());
		Validador.asserteTrue(new ItemFileDAO().existeItem(idItem.trim()),
				Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		Validador.asserteTrue(duracao > 0, Mensagem.EMPRESTIMO_DURACAO_INVALIDA
				.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(login);
		UsuarioIF amigo = usuario.ehItemDoMeuAmigo(idItem);
		Validador.asserteTrue(amigo != null,
				Mensagem.USUARIO_NAO_TEM_PERMISSAO_REQUISITAR_EMPREST_ITEM.getMensagem());

		ItemIF item = new ItemFileDAO().recuperarItem(idItem);

		// verifica se jah fiz o pedido do item
		Iterator<EmprestimoIF> iterador = contas.get(login)
				.getEmprestimosRequeridosPorMimEmEspera().iterator();
		while (iterador.hasNext()) {
			if (iterador.next().getItem().equals(item))
				throw new Exception(Mensagem.REQUISICAO_EMPRESTIMO_JA_SOLICITADA
						.getMensagem());
		}

		EmprestimoIF emp = new Emprestimo(amigo, usuario, item, "beneficiado", duracao);

		// vou atualizar estado do emprestimo
		new EmprestimoFileDAO().requisitarEmprestimo(emp);
		contas.get(login).getEmprestimosRequeridosPorMimEmEspera().add(emp);// o
																			// emprestimo
																			// eh
																			// modificdo
																			// pelo
																			// repositorio
																			// possuindo
																			// agora
		// um id valido
		amigo.adicionarRequisicaoEmprestimoEmEsperaDeAmigo(emp);
		String assunto = "Empréstimo do item " + item.getNome() + " a "
				+ usuario.getNome() + "";
		String mensagem = usuario.getNome() + " solicitou o empréstimo do item "
				+ item.getNome();
		usuario.enviarMensagemEmprestimo(amigo.getLogin(), assunto, mensagem, emp
				.getIdEmprestimo());
		return emp.getIdEmprestimo();

	}

	public synchronized String getEmprestimos(String login, String tipo) throws Exception {

		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(tipo, Mensagem.EMPRESTIMO_TIPO_INVALIDO
				.getMensagem(), Mensagem.EMPRESTIMO_TIPO_INVALIDO.getMensagem());
		// "mark-steve:The Social Network:Andamento; steve-mark:Guia do mochileiro das galáxias:Andamento"
		StringBuffer str = new StringBuffer();
		List<String> listaSaida = new ArrayList<String>();
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(login);
		Iterator<EmprestimoIF> iterador = contas.get(login).getEmprestimos().iterator();

		while (iterador.hasNext()) {
			EmprestimoIF emp = iterador.next();
			if (tipo.trim().equalsIgnoreCase("emprestador")) {

				if (usuario.equals(emp.getEmprestador())) {

					listaSaida.add(emp.getEmprestador().getLogin() + "-"
							+ emp.getBeneficiado().getLogin() + ":"
							+ emp.getItem().getNome() + ":" + emp.getSituacao() + "; ");
					Collections.sort(listaSaida);
				}
			} else if (tipo.trim().equalsIgnoreCase("beneficiado")) {
				if (usuario.equals(emp.getBeneficiado())) {

					listaSaida.add(emp.getEmprestador().getLogin() + "-"
							+ emp.getBeneficiado().getLogin() + ":"
							+ emp.getItem().getNome() + ":" + emp.getSituacao() + "; ");
					Collections.sort(listaSaida);
				}
			} else if (tipo.trim().equalsIgnoreCase("todos")) {

				if (usuario.equals(emp.getEmprestador())) {
					listaSaida.add(0, emp.getEmprestador().getLogin() + "-"
							+ emp.getBeneficiado().getLogin() + ":"
							+ emp.getItem().getNome() + ":" + emp.getSituacao() + "; ");
				}

				if (usuario.equals(emp.getBeneficiado())) {
					listaSaida.add(emp.getEmprestador().getLogin() + "-"
							+ emp.getBeneficiado().getLogin() + ":"
							+ emp.getItem().getNome() + ":" + emp.getSituacao() + "; ");
				}

			} else {
				throw new Exception(Mensagem.EMPRESTIMO_TIPO_INXISTENTE.getMensagem());
			}
		}

		for (String s : listaSaida) {
			str.append(s);
		}

		if (str.toString().trim().equals(""))
			return Mensagem.NAO_HA_EMPRESTIMOS_DESTE_TIPO.getMensagem();
		return str.toString().trim().substring(0, str.toString().length() - 2);
	}

	/**
	 * Aproma um determinado emprestimo
	 * 
	 * @param login - String
	 * 		Loguin do usuario
	 * @param idRequisicaoEmprestimo String
	 * 		ID da requisição a ser aprovada
	 * 
	 * @return String
	 * 		ID do emprestimo
	 * 
	 * @throws Exception
	 */
	public synchronized String aprovarEmprestimo(String login,
			String idRequisicaoEmprestimo) throws Exception {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		assertStringNaoVazia(idRequisicaoEmprestimo,
				Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem(),
				Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		asserteTrue(
				new EmprestimoFileDAO().existeEmprestimo(idRequisicaoEmprestimo.trim()),
				Mensagem.ID_REQUISICAO_EMP_INEXISTENTE.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(login);
		EmprestimoIF emp = new EmprestimoFileDAO()
				.recuperarEmprestimo(idRequisicaoEmprestimo.trim());
		asserteTrue(usuario.equals(emp.getEmprestador()),
				Mensagem.EMPRESTIMO_SEM_PERMISSAO_APROVAR.getMensagem());

		if (!emp.estaAprovado())
			throw new Exception(Mensagem.EMPRESTIMO_JA_APROVADO.getMensagem());
		emp.aprovarEmprestimo();

		contas.get(login).getEmprestimosRequeridosPorAmigosEmEspera().remove(emp);
		contas.get(login).getEmprestimos().add(emp);
		UsuarioIF amigo = emp.getBeneficiado();
		amigo.emprestimoAceitoPorAmigo(emp);
		emp.getItem().setDisponibilidade(false);

		Autenticacao.getUsuarioPorLogin(login).addHistoricoEmprestimoEmAndamento(amigo,
				emp.getItem());

		return emp.getIdEmprestimo();

	}

	/**
	 * Adiciona ao banco de emprestimos um emprestimo aceito por um amigo
	 * 
	 * @param login - String
	 * 		Loguin do usuario
	 * @param emp - EmprestimoIF
	 * 		Emprestimo a ser adicionado
	 * 
	 * @throws Exception
	 */
	public void emprestimoAceitoPorAmigo(String login, EmprestimoIF emp) throws Exception {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		Conta conta = contas.get(login);
		conta.getEmprestimosRequeridosPorMimEmEspera().remove(emp);
		conta.getEmprestimos().add(emp);
	}

	/**
	 * Remove emprestimos requeridos por um amigo
	 * 
	 * @param login - String
	 * 		Loguin do usuario
	 * @param amigo - UsuarioIF
	 * 		Objeto referente ao amigo
	 */
	public void removerEmprestimosRequeridosPorAmigo(String login, UsuarioIF amigo) {
		Iterator<EmprestimoIF> iteradorListaEmprestimosRequeridosPorAmigo = contas.get(
				login).getEmprestimosRequeridosPorAmigosEmEspera().iterator();

		while (iteradorListaEmprestimosRequeridosPorAmigo.hasNext()) {
			EmprestimoIF emprestimo = iteradorListaEmprestimosRequeridosPorAmigo.next();
			if (emprestimo.getBeneficiado().equals(amigo)) {
				new EmprestimoFileDAO().removerEmprestimo(emprestimo.getIdEmprestimo());
				iteradorListaEmprestimosRequeridosPorAmigo.remove();
			}
		}

	}

	/**
	 * Remove emprestimos requeridos pelo proprio usuario
	 * 
	 * @param login - String
	 * 		Loguin do usuario
	 * @param amigo - UsuarioIF
	 * 		Objeto referente ao amigo
	 */
	public void removerEmprestimosRequeridosPorMim(String login, UsuarioIF amigo) {
		Iterator<EmprestimoIF> iteradorListaEmprestimosRequeridosPorMim = contas.get(
				login).getEmprestimosRequeridosPorMimEmEspera().iterator();

		while (iteradorListaEmprestimosRequeridosPorMim.hasNext()) {
			EmprestimoIF emprestimo = iteradorListaEmprestimosRequeridosPorMim.next();
			if (emprestimo.getEmprestador().equals(amigo)) {
				new EmprestimoFileDAO().removerEmprestimo(emprestimo.getIdEmprestimo());
				iteradorListaEmprestimosRequeridosPorMim.remove();
			}
		}

	}

	/**
	 * Marca um determinado item como requesitado
	 * 
	 * @param login - String
	 * 		Loguin do usuario
	 * @param idItem - String
	 * 
	 * @return boolean
	 * 
	 * @throws Exception
	 */
	public synchronized boolean requisiteiEsteItem(String login, String idItem) throws Exception {
		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(),
				Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(new ItemFileDAO().existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE
				.getMensagem());

		Iterator<EmprestimoIF> iteradorEmprestimos = contas.get(login)
				.getEmprestimosRequeridosPorMimEmEspera().iterator();
		while (iteradorEmprestimos.hasNext()) {

			if (iteradorEmprestimos.next().getItem().getId().trim().equalsIgnoreCase(
					idItem.trim())) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove uma solicitação de emprestimo
	 * 
	 * @param login - String
	 * 		Loguin do usuario
	 * @param emprestimo - EmprestimoIF
	 * 		Emprestimo a ser removido
	 */
	public void removerMinhaSolicitacaoEmprestimo(String login, EmprestimoIF emprestimo) {
		contas.get(login).getEmprestimosRequeridosPorMimEmEspera().remove(emprestimo);
	}

	/**
	 * Reseta o sistema para as configurações iniciais
	 */
	public void zerarSistema() {
		contas.clear();
	}

	public void salvarEmArquivo() {
		Configuracao conf = Configuracao.getInstance();
		File arquivo = new File("./"+conf.getDiretorioBD()+"bancoEmprestimos.bd");

		ObjectOutputStream objectOut = null;
		try {
			arquivo.createNewFile();
			Object[] vetor = new Object[1];
			contas = ((TreeMap<String, Conta>) vetor[0]);
			objectOut = new ObjectOutputStream(
	            new BufferedOutputStream(new FileOutputStream("./"+conf.getDiretorioBD()+"bancoEmprestimos.bd")));
			objectOut.reset();
	           objectOut.writeObject(vetor);
	                
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				objectOut.close();
			} catch (IOException e) {}
		}

	}

}
