/**
 * 
 */
package iu.web.server.sistema.item;

import static iu.web.server.sistema.utilitarios.Validador.assertNaoNulo;
import static iu.web.server.sistema.utilitarios.Validador.assertStringNaoVazia;
import static iu.web.server.sistema.utilitarios.Validador.asserteTrue;

import iu.web.server.sistema.autenticacao.Autenticacao;
import iu.web.server.sistema.autenticacao.Configuracao;
import iu.web.server.sistema.dao.BancoDeEmprestimosDAO;
import iu.web.server.sistema.dao.BancoDeEmprestimosFileDAO;
import iu.web.server.sistema.dao.EmprestimoFileDAO;
import iu.web.server.sistema.dao.GerenciadorDeNotificacoesDAO;
import iu.web.server.sistema.dao.GerenciadorDeNotificacoesFileDAO;
import iu.web.server.sistema.dao.ItemFileDAO;
import iu.web.server.sistema.emprestimo.BancoDeEmprestimos;
import iu.web.server.sistema.emprestimo.EmprestimoIF;
import iu.web.server.sistema.excecoes.ArgumentoInvalidoException;
import iu.web.server.sistema.notificacao.GerenciadorDeNotificacoes;
import iu.web.server.sistema.notificacao.NotificacaoPublicarPedido;
import iu.web.server.sistema.persistencia.NotificacaoRepositorio;
import iu.web.server.sistema.usuario.RelacionamentosUsuarios;
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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Mobile
 * 
 */
public class AcervoDeItens {
	private static AcervoDeItens acervoDeItens;
	private static Map<String, Bauh> bauhs;

	private AcervoDeItens() {
		bauhs = new TreeMap<String, Bauh>();
		
		Configuracao conf = Configuracao.getInstance();
		File arquivo = new File("./"+conf.getDiretorioBD()+"acervoItens.bd");
		File diretorio = new File("./"+conf.getDiretorioBD());
		if(!diretorio.exists()){
			diretorio.mkdir();
			ObjectOutputStream objectOut = null;
			try {
				arquivo.createNewFile();
				Object[] vetor = new Object[1];
				vetor[0] =  new TreeMap<String, Bauh>();
				objectOut = new ObjectOutputStream(
	                    new BufferedOutputStream(new FileOutputStream("./"+conf.getDiretorioBD()+"acervoItens.bd")));
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

	public static AcervoDeItens getInstance() {
		if (acervoDeItens == null) {
			acervoDeItens = new AcervoDeItens();

			return acervoDeItens;
		}
		return acervoDeItens;
	}
	
	private static void inicializarDados() throws Exception {
		Configuracao conf = Configuracao.getInstance();
        
        ObjectInputStream objectIn = null;
        try{
        	objectIn = new ObjectInputStream(
                    new BufferedInputStream(new FileInputStream("./"+conf.getDiretorioBD()+"acervoItens.bd")));
            Object[] vetor = ((Object[])objectIn.readObject());
            bauhs = ((TreeMap<String, Bauh>) vetor[0]);
        
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            objectIn.close();
        }
        
    }

	/**
	 * Adiciona um Bauh a um determinado usuario.
	 * 
	 * @param usuario - String
	 * 		Usuario desejado
	 * 
	 * @throws Exception
	 */
	public void adicionaBauhAoUsuario(String usuario) throws Exception {
		if (bauhs.containsKey(usuario))
			throw new Exception(Mensagem.PROPRIETARIO_BAUH_JAH_CADASTRADO.getMensagem());
		bauhs.put(usuario, new Bauh(usuario));

	}

	/**
	 * Remove conta do usuario
	 * 
	 * @param usuario - String
	 * 		Usuario desejado
	 * 
	 * @throws Exception
	 */
	public void removeContaDoUsuario(String usuario) throws Exception {
		bauhs.remove(usuario);
	}

	/**
	 * Retorna o Bauh de um determinado usuario
	 * 
	 * @param login - String
	 * 		Login do usuario desejado
	 * 
	 * @return Bauh
	 * 		Bauh do usuario desejado
	 * 
	 * @throws Exception
	 */
	public Bauh getBauh(String login) throws Exception {
		return bauhs.get(login);
	}

	/**
	 * Cadastra um item.
	 * 
	 * @param login - String
	 * 		Login do usuario
	 * @param nome - String
	 * 		Nome do item a ser cadastrado
	 * @param descricao - String
	 * 		Descrição do item a ser cadastrado
	 * @param categoria - String
	 * 		Categoria(s) do item a ser cadastrado
	 * 
	 * @return String
	 * 		ID do item cadastrado
	 * 
	 * @throws Exception
	 */
	public String cadastrarItem(String login, String nome, String descricao,
			String categoria) throws Exception {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());

		ItemIF item = new Item(nome, descricao, categoria, Autenticacao
				.getUsuarioPorLogin(login));
		new ItemFileDAO().cadastrarItem(item);
		bauhs.get(login).getItens().add(item);// o item eh modificado pelo
												// repositorio possuindo agora
		// um id valido
		// addHistoricoCadastrarItem(item);
		return item.getId();
	}

	/**
	 * Remove um item previamente cadastrado.
	 * 
	 * @param login - String
	 * 		Login do usuario
	 * @param idItem - String
	 * 		ID do item a ser removido
	 * 
	 * @return boolean
	 * 		true caso o item seja removido com sucesso.
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public boolean removerItem(String login, String idItem) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		List<ItemIF> itens = bauhs.get(login).getItens();
		for (ItemIF item : itens) {
			if (idItem.equals(item.getId())) {
				itens.remove(item);
				return true;
			}
		}
		return false;
	}

	/**
	 * Retorna a lista completa de itens de um determinado usuario
	 * 
	 * @param login - String
	 * 		Loguin do usuario desejado
	 * 
	 * @return String
	 * 		Uma String compilada a partir da lista de itens de um determinado usuario
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public String getListaIdItens(String login) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		List<ItemIF> itens = bauhs.get(login).getItens();
		StringBuilder listaIdItensString = new StringBuilder();
		Collections.sort(itens);
		for (ItemIF item : itens) {
			listaIdItensString.append(item.getId() + " ");
		}

		return listaIdItensString.toString().trim();
	}

	/**
	 * Retorna uma lista de itens de um determinado usuario.
	 * 
	 * @param login - String
	 * 		Login do usuario desejado.
	 * 
	 * @return List<ItemIF>
	 * 		Lista de itens do usuario
	 */
	public List<ItemIF> getItens(String login) {
		return bauhs.get(login).getItens();
	}

	/**
	 * Retorna um determinado item de um determinado usuario.
	 * @param login - String
	 * 		Login do usuario desejado.
	 * @param idItem - String
	 * 		ID do item desejado.
	 * 
	 * @return ItemIF
	 * 		Objeto referente ao item desejado
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public ItemIF getItem(String login, String idItem) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		List<ItemIF> itens = bauhs.get(login).getItens();
		for (ItemIF item : itens) {
			if (item.getId().equals(idItem)) {
				return item;
			}
		}

		return null;
	}

	/**
	 * Retorna quantidade de itens de um determinado usuario
	 * @param login - String
	 * 		Login do usuario desejado.
	 * 
	 * @return int
	 * 		Numero de itens do usuario
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public int qntItens(String login) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		List<ItemIF> itens = bauhs.get(login).getItens();
		return itens.size();
	}

	/**
	 * @param login - String
	 * 		Login do usuario desejado.
	 * 
	 * @return int
	 * 		Quantidade de itens emprestados.
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public int qntItensEmprestados(String login) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		return bauhs.get(login).getItensEmprestados().size();
	}

	/**
	 * @param login - String
	 * 		Login do usuario desejado.
	 * 
	 * @return String
	 * 		String compilada a partir da lista de itens emprestados do usuario.
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public String getListaIdItensEmprestados(String login) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		List<ItemIF> itensEmprestados = bauhs.get(login).getItensEmprestados();
		StringBuilder listaIdItensEmprestadosString = new StringBuilder();

		for (ItemIF itemEmprestado : itensEmprestados) {
			listaIdItensEmprestadosString.append(itemEmprestado.getId() + " ");
		}

		return listaIdItensEmprestadosString.toString().trim();
	}

	/**
	 * Testa se um determinado item existe dentro da lista do usuario
	 * 
	 * @param login - String
	 * 		Login do usuario desejado.
	 * @param idItem - String
	 * 		ID do item a ser testado
	 * 
	 * @return boolean
	 * 		True caso o item exista
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public boolean existeItemID(String login, String idItem) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		List<ItemIF> itens = bauhs.get(login).getItens();
		try {
			return itens.contains(new Item("placebo", "placebo", "FILME").setId(idItem));
		} catch (Exception e) {
		}// nao lanca excecao.
		return false;

	}

	/**
	 * @param login - String
	 * 		Login do usuario desejado.
	 * 
	 * @return String
	 * 		String compilada a partir da lista de itens do usuario
	 * 
	 * @throws Exception
	 */
	public String getListaItens(String login) throws Exception {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		Iterator<ItemIF> iterador = bauhs.get(login).getItens().iterator();
		StringBuffer str = new StringBuffer();
		while (iterador.hasNext()) {
			str.append(iterador.next().getNome() + "; ");
		}
		if (str.toString().trim().equals(""))
			return Mensagem.USUARIO_SEM_ITENS_CADASTRADOS.getMensagem();
		return str.toString().substring(0, str.toString().length() - 2);
	}

	/**
	 * Verifica se este item pertence ao usuário.
	 * @param login
	 * 		O login do usuário.
	 * @param idItem
	 * 		O id do item.
	 * @return
	 * 		<code>True</code> - Se o item pertencer ao usuário.
	 * 		<code>False</code> - Caso contrário.
	 * @throws Exception
	 */
	public boolean esteItemMePertence(String login, String idItem) throws Exception {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(),
				Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(new ItemFileDAO().existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE
				.getMensagem());

		Iterator<ItemIF> iteradorItens = bauhs.get(login).getItens().iterator();
		while (iteradorItens.hasNext()) {
			if (iteradorItens.next().getId().trim().equalsIgnoreCase(idItem.trim()))
				return true;
		}
		return false;
	}

	/**
	 * Apaga um determinado item
	 * 
	 * @param login - String
	 * 		Login do usuario desejado.
	 * @param idItem - String
	 * 		ID do item a ser apagado
	 * 
	 * @throws Exception
	 */
	public void apagarItem(String login, String idItem) throws Exception {
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		BancoDeEmprestimosDAO banco = new BancoDeEmprestimosFileDAO();
		Iterator<EmprestimoIF> iteradorEmprestimosRequeridosPorAmigos = banco.getConta(
				login).getEmprestimosRequeridosPorAmigosEmEspera().iterator();
		while (iteradorEmprestimosRequeridosPorAmigos.hasNext()) {
			EmprestimoIF emprestimo = iteradorEmprestimosRequeridosPorAmigos.next();
			if (emprestimo.getItem().getId().equalsIgnoreCase(idItem.trim())) {

				UsuarioIF amigoQueSolicitou = emprestimo.getBeneficiado();
				amigoQueSolicitou.removerMinhaSolicitacaoEmprestimo(emprestimo);
				new EmprestimoFileDAO().removerEmprestimo(emprestimo.getIdEmprestimo());
				iteradorEmprestimosRequeridosPorAmigos.remove();
			}
		}

		Iterator<ItemIF> iteradorMeusItens = bauhs.get(login).getItens().iterator();
		while (iteradorMeusItens.hasNext()) {
			if (iteradorMeusItens.next().getId().equalsIgnoreCase(idItem.trim())) {
				iteradorMeusItens.remove();
			}
		}
	}

	/**
	 * Registra interesse por um determinado item
	 * 
	 * @param seuLogin - String
	 * 		Login do usuario.
	 * @param idItem - String
	 * 		ID do item a ser registrado
	 * 
	 * @throws Exception
	 */
	public void registrarInteressePorItem(String seuLogin, String idItem) throws Exception {

		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(),
				Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(new ItemFileDAO().existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE
				.getMensagem());

		ItemIF item = new ItemFileDAO().recuperarItem(idItem);

		asserteTrue(!item.getInteresasados().contains(
				Autenticacao.getUsuarioPorLogin(seuLogin)),
				Mensagem.USUARIO_JAH_REGISTROU_INTERESSE_NESTE_ITEM.getMensagem());
		asserteTrue(!item.getInteresasados().contains(
				Autenticacao.getUsuarioPorLogin(seuLogin)),
				Mensagem.USUARIO_NAO_PODE_REGISTRAR_INTERESSE_PROPRIO_ITEM.getMensagem());
		UsuarioIF amigo = RelacionamentosUsuarios.getInstance().ehItemDoMeuAmigo(
				seuLogin, idItem);
		assertNaoNulo(amigo,
				Mensagem.USUARIO_NAO_TEM_PERMISSAO_REGISTRAR_INTERESSE_NESSE_ITEM.getMensagem());
		item.adicionaInteressado(Autenticacao.getUsuarioPorLogin(seuLogin));
		((GerenciadorDeNotificacoesDAO) new GerenciadorDeNotificacoesFileDAO()).addHistoricoInteressePorItem(seuLogin,
				amigo, item);
	}

	/**
	 * Oferece um Item
	 * 
	 * @param  - String
	 * 		Login do usuario.
	 * @param idPublicacaoPedido - String
	 * 		ID da publicacao do pedido
	 * @param idItem - String
	 * 		ID do item pedido.
	 * 
	 * @throws Exception
	 */
	public void oferecerItem(String login, String idPublicacaoPedido, String idItem) throws Exception {

		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		assertStringNaoVazia(idPublicacaoPedido, Mensagem.PUBLICACAO_ID_INVALIDO
				.getMensagem(), Mensagem.PUBLICACAO_ID_INVALIDO.getMensagem());
		NotificacaoPublicarPedido notificacao = null;
		try {
			notificacao = (NotificacaoPublicarPedido) NotificacaoRepositorio
					.getInstance().recuperarNotificacao(idPublicacaoPedido);
		} catch (Exception e) {
			throw new Exception(Mensagem.PUBLICACAO_ID_INEXISTENTE.getMensagem());
		}
		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(),
				Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(new ItemFileDAO().existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE
				.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(login);
		if (!usuario.esteItemMePertence(idItem)) {
			throw new Exception(Mensagem.ITEM_NAO_ME_PERTENCE.getMensagem());
		}
		ItemIF item = new ItemFileDAO().recuperarItem(idItem);
		usuario.enviarMensagemOferecimentoItemOffTopic(notificacao
				.getOriginadorMensagem().getLogin(), String.format(
				"O usuário %s ofereceu o item %s", usuario.getNome(), item.getNome()),
				String.format("Item oferecido: %s - %s", item.getNome(), item
						.getDescricao()));
	}

	/**
	 * Retorna o sistema a suas configurações iniciais.
	 */
	public void zerarSistema() {
		AcervoDeItens.bauhs.clear();
	}

	public void salvarEmArquivo() {
		Configuracao conf = Configuracao.getInstance();
		File arquivo = new File("./"+conf.getDiretorioBD()+"acervoItens.bd");

		ObjectOutputStream objectOut = null;
		try {
			arquivo.createNewFile();
			Object[] vetor = new Object[1];
			bauhs = ((TreeMap<String, Bauh>) vetor[0]);
			objectOut = new ObjectOutputStream(
	            new BufferedOutputStream(new FileOutputStream("./"+conf.getDiretorioBD()+"acervoItens.bd")));
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
