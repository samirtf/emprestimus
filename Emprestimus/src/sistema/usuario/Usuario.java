package sistema.usuario;

import static sistema.utilitarios.Validador.assertStringNaoVazia;
import static sistema.utilitarios.Validador.asserteTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import sistema.autenticacao.Autenticacao;
import sistema.emprestimo.BancoDeEmprestimos;
import sistema.emprestimo.Emprestimo;
import sistema.emprestimo.EmprestimoIF;
import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.AcervoDeItens;
import sistema.item.DataCriacaoItemComparador;
import sistema.item.Item;
import sistema.item.ItemIF;
import sistema.item.NomeItemComparador;
import sistema.mensagem.Chat;
import sistema.mensagem.ChatIF;
import sistema.mensagem.Correio;
import sistema.notificacao.Notificacao;
import sistema.notificacao.NotificacaoEmprestimoAndamento;
import sistema.notificacao.NotificacaoNovoAmigo;
import sistema.notificacao.NotificacaoNovoItem;
import sistema.persistencia.ChatRepositorio;
import sistema.persistencia.EmprestimoRepositorio;
import sistema.persistencia.ItemRepositorio;
import sistema.persistencia.NotificacaoRepositorio;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;

/**
 * Esta classe representa um usuario padrao do sistema.
 * 
 * @author Joeffison Silverio de Andrade, 21011853
 * @author José Nathaniel L. de Abrante, 21011091
 * @author José Ulisses de Brito Lira Filho, 20911806
 * @author Samir Trajano Feitosa, 20921299
 * @version 1.2.3
 */
public class Usuario implements UsuarioIF {
	/* Atributos estaticos. */
	private static int ID_Prox_Usuario = 1; // ID do proximo usuario sera
											// guardado nesta variavel estatica.

	/* Atributos do objeto. */
	private String login, nome, endereco;

	private final int id = ID_Prox_Usuario++; // id (codigo unico) do usuario
	
	private int reputacao = 0;

	private List<UsuarioIF> amigos; // Grupo de amigos
	private List<UsuarioIF> queremSerMeusAmigos; // solicitacoes de amizade
	private List<UsuarioIF> queroSerAmigoDeles; // solicitacoes de amizade
	
	//private List<ItemIF> itens; // itens do usuario
	//private List<ItemIF> itensEmprestados; // lista de itens que o usuario
											// emprestou e ainda nao recebeu
//	protected List<String> historico;
	
	private Stack<Notificacao> historico;
	
	/**
	 * Construtor padrao eh privado e nao oferece implementacao.
	 */
	private Usuario() {
	}

	/**
	 * Constroi um usuario a partir de um login, nome e endereco.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @param nome
	 *            O nome do usuario.
	 * @param endereco
	 *            O endereco do usuario.
	 */
	public Usuario(String login, String nome, String endereco) throws Exception {

		// Estes métodos podem lançar exceção
		setLogin(login);
		setNome(nome);
		setEndereco(endereco);

		//itens = new ArrayList<ItemIF>();
		//itensEmprestados = new ArrayList<ItemIF>();
		amigos = new ArrayList<UsuarioIF>();
		queremSerMeusAmigos = new ArrayList<UsuarioIF>();
		queroSerAmigoDeles = new ArrayList<UsuarioIF>();
		//emprestimos = new ArrayList<EmprestimoIF>();
		//emprestimosRequeridosPorAmigosEmEspera = new ArrayList<EmprestimoIF>();
		//emprestimosRequeridosPorMimEmEspera = new ArrayList<EmprestimoIF>();
		//conversasOfftopic = new LinkedList<ChatIF>();
		//conversasNegociacao = new LinkedList<ChatIF>();
		historico = new Stack<Notificacao>();
	}

	@Override
	public void setLogin(String login) throws Exception {
		assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		this.login = login.trim();
	}

	@Override
	public void setNome(String nome) throws Exception {
		assertStringNaoVazia(nome, Mensagem.NOME_INVALIDO.getMensagem(), Mensagem.NOME_INVALIDO.getMensagem());
		this.nome = nome.trim();
	}

	@Override
	public void setEndereco(String endereco) {
		if (endereco == null) {
			this.endereco = "";
		} else {
			this.endereco = endereco.trim();
		}

	}

	@Override
	public synchronized String getLogin() {
		return this.login;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getEndereco() {
		return this.endereco;
	}

	@Override
	public String cadastrarItem(String nome, String descricao, String categoria)
			throws Exception {
		String idItem = AcervoDeItens.getInstance().cadastrarItem(this.getLogin(), nome, descricao, categoria);
		ItemIF item = ItemRepositorio.recuperarItem(idItem);
		addHistoricoCadastrarItem(item);
		return item.getId();

	}

	private void addHistoricoCadastrarItem(ItemIF item) throws Exception {
		Notificacao notif = new NotificacaoNovoItem(this, item);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		addNotificacao(notif);
	}

	@Override
	public boolean removerItem(String idItem) throws ArgumentoInvalidoException {
		return AcervoDeItens.getInstance().removerItem(this.getLogin(), idItem);
	}

	@Override
	public String getListaIdItens() throws ArgumentoInvalidoException{
		return AcervoDeItens.getInstance().getListaIdItens(login);
	}

	/**
	 * Recupera a lista detodos os itens do usuario.
	 * 
	 * @return Lista de itens.
	 */
	@Override
	public List<ItemIF> getItens() {
		return AcervoDeItens.getInstance().getItens(this.getLogin());
	}

	@Override
	public ItemIF getItem(String idItem) throws ArgumentoInvalidoException{
		return AcervoDeItens.getInstance().getItem(this.getLogin(), idItem);
	}

	@Override
	public int qntItens() throws ArgumentoInvalidoException{
		return AcervoDeItens.getInstance().qntItens(login);
	}

	@Override
	public int qntItensEmprestados() throws ArgumentoInvalidoException{
		return AcervoDeItens.getInstance().qntItensEmprestados(login);
	}

	@Override
	public String getListaIdItensEmprestados() throws ArgumentoInvalidoException{
		return AcervoDeItens.getInstance().getListaIdItensEmprestados(login);
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
		try {
			return getItem(idItem).estahDisponivel();

		} catch (Exception e) {
			return false;
		}
	}
	
	public synchronized void aprovarAmizade( String login ) throws Exception{
		UsuarioIF amigo = Autenticacao.getUsuarioPorLogin(login);
		if(amigos.contains(amigo)){
			throw new Exception(Mensagem.USUARIO_JAH_SAO_AMIGOS.getMensagem());
		}
		Iterator<UsuarioIF> iterador = queremSerMeusAmigos.iterator();
		UsuarioIF amigo_solicitante = null;
		while(iterador.hasNext()){
			amigo_solicitante = iterador.next();
			if(amigo_solicitante.getLogin().trim().equalsIgnoreCase(login.trim())){
				amigo_solicitante.aprovouAmizade(this);
				amigos.add(amigo_solicitante);
				//queremSerMeusAmigos.remove(u);
				iterador.remove();
				addHistoricoAmizadeAprovada(amigo_solicitante);
				return;
			}
			
		}
		throw new Exception(Mensagem.REQUISICAO_AMIZADE_INEXISTENTE.getMensagem());
		
	}
	
	private void addHistoricoAmizadeAprovada(UsuarioIF amigo) throws Exception {
		Notificacao notif = new NotificacaoNovoAmigo(this, amigo);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		this.addNotificacao(notif);
		amigo.addNotificacao(notif);
	}

	public synchronized void aprovouAmizade( UsuarioIF usuario ){
		
		if(queroSerAmigoDeles.contains(usuario)){
			queroSerAmigoDeles.remove(usuario);
			amigos.add(usuario);
		}
		
	}
	
	@Override
	public List<UsuarioIF> getQueremSerMeusAmigos(){
		return this.queremSerMeusAmigos;
	}
	
	@Override
	public List<UsuarioIF> getQueroSerAmigoDe(){
		return this.queroSerAmigoDeles;
	}
	
	public boolean ehAmigo( String login ) throws ArgumentoInvalidoException{
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		Iterator<UsuarioIF> iterador = amigos.iterator();
		UsuarioIF u = null;
		while(iterador.hasNext()){
			u = iterador.next();
			if ( u.getLogin().trim().equalsIgnoreCase(login.trim()) ) 
				return true;
		}
		return false;
	}
	
	public boolean amizadeDeFoiRequisitada( String login ) throws ArgumentoInvalidoException{
		Validador.assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		Iterator<UsuarioIF> iterador = getQueroSerAmigoDe().iterator();
		UsuarioIF u = null;
		while(iterador.hasNext()){
			u = iterador.next();
			if ( u.getLogin().trim().equalsIgnoreCase(login.trim()) ) 
				return true;
		}
		return false;
	}
	
	public void requisitarAmizade( String login ) throws Exception{
		if(ehAmigo(login)){
			throw new Exception(Mensagem.USUARIO_JAH_SAO_AMIGOS.getMensagem());
		}else if(amizadeDeFoiRequisitada(login)){
			throw new Exception(Mensagem.AMIZADE_JAH_SOLICITADA.getMensagem());
		}

		UsuarioIF futuroAmigo = Autenticacao.getUsuarioPorLogin(login);
		if( Autenticacao.existeUsuario(login.trim()) ){
			queroSerAmigoDeles.add(futuroAmigo);
			futuroAmigo.usuarioQuerSerMeuAmigo(this);
		}
	}
	
	public void usuarioQuerSerMeuAmigo( UsuarioIF usuario ){
		for( UsuarioIF u : getQueremSerMeusAmigos() ){
			if(u.equals(usuario)) return;
		}
		queremSerMeusAmigos.add(usuario);
	}

	@Override
	public boolean equals(Object outroUsuario) {
		if (outroUsuario instanceof UsuarioIF) {
			return this.getLogin()
					.equals(((UsuarioIF) outroUsuario).getLogin());
		}
		return false;
	}

	@Override
	public boolean existeItemID(String idItem) throws ArgumentoInvalidoException{
		return AcervoDeItens.getInstance().existeItemID(this.getLogin(), idItem);
	}

	@Override
	public String getAmigos() throws Exception{
		Iterator<UsuarioIF> iterador = amigos.iterator();
		StringBuffer str = new StringBuffer();
		while(iterador.hasNext()){
			str.append(iterador.next().getLogin()+"; ");
		}
		if(str.toString().trim().equals("")) 
			return Mensagem.USUARIO_NAO_POSSUI_AMIGOS.getMensagem();
		return str.toString().trim().substring(0, str.toString().length()-2);
		
	}

	@Override
	public String getListaItens() throws Exception {
		return AcervoDeItens.getInstance().getListaItens(login);
	}
	
	public boolean oItemMePertence( String idItem ) throws ArgumentoInvalidoException{
		Validador.assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(), Mensagem.ID_ITEM_INVALIDO.getMensagem());
		Validador.asserteTrue(ItemRepositorio.existeItem(idItem.trim()), Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		Iterator<ItemIF> iterador = getItens().iterator();
		while(iterador.hasNext()){
			if(iterador.next().getId().trim().equalsIgnoreCase(idItem.trim())){
				return true;
			}
		}
		return false;
		
	}
	
	public UsuarioIF ehItemDoMeuAmigo( String idItem ) throws Exception{
		Validador.assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(), Mensagem.ID_ITEM_INVALIDO.getMensagem());
		Validador.asserteTrue(ItemRepositorio.existeItem(idItem.trim()), Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		Iterator<UsuarioIF> iterador = amigos.iterator();
		while(iterador.hasNext()){
			UsuarioIF amigo = iterador.next();
			if(amigo.oItemMePertence(idItem)){
				return amigo;
			}
		}
		return null;
		
	}
	
	public void adicionarRequisicaoEmprestimoEmEsperaDeAmigo(EmprestimoIF emp) throws Exception{
		BancoDeEmprestimos.getInstance().adicionarRequisicaoEmprestimoEmEsperaDeAmigo(this.getLogin(), emp);
		//this.emprestimosRequeridosPorAmigosEmEspera.add(emp);
	}

	@Override
	public synchronized String requisitarEmprestimo(String idItem, int duracao) throws Exception{
		return BancoDeEmprestimos.getInstance().requisitarEmprestimo(this.getLogin(), idItem, duracao);		
	}
	

	@Override
	public String getEmprestimos(String tipo) throws Exception {
		return BancoDeEmprestimos.getInstance().getEmprestimos(this.getLogin(), tipo);
	}

	@Override
	public String aprovarEmprestimo( String idRequisicaoEmprestimo )
			throws Exception {
		return BancoDeEmprestimos.getInstance().aprovarEmprestimo(this.getLogin(), idRequisicaoEmprestimo);
	}
	
	/**
	 * @param amigo
	 * @param item
	 * @throws Exception 
	 */
	public void addHistoricoEmprestimoEmAndamento(UsuarioIF amigo, ItemIF item) throws Exception {
		Notificacao notif = new NotificacaoEmprestimoAndamento(this, amigo, item);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		this.addNotificacao(notif);
		amigo.addNotificacao(notif);
		
	}

	public void addNotificacao(Notificacao notificacao) throws Exception {
		historico.add(notificacao);
	}

	public void emprestimoAceitoPorAmigo( EmprestimoIF emp ) throws Exception {
		BancoDeEmprestimos.getInstance().emprestimoAceitoPorAmigo(this.getLogin(), emp);
		//this.emprestimosRequeridosPorMimEmEspera.remove(emp);
		//emprestimos.add(emp);
	}

	@Override
	public UsuarioIF possuoAmigoComEsteLogin(String login) throws Exception {
		assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		
		Iterator<UsuarioIF> iterador = amigos.iterator();
		while(iterador.hasNext()){
			UsuarioIF amigo = iterador.next();
			if(amigo.getLogin().equals(login.trim()))
				return amigo;
		}
		
		return null;
	}
	
	public void adicionaConversaOfftopicNaLista( ChatIF conversa ) throws Exception{
		//this.conversasOfftopic.add(conversa);
		Correio.adicionaConversaOfftopicNaLista(this.getLogin(), conversa);
	}
	
	public void adicionaConversaNegociacaoNaLista( ChatIF conversa ) throws Exception{
		//this.conversasNegociacao.add(conversa);
		Correio.adicionaConversaNegociacaoNaLista(this.getLogin(), conversa);
	}

	@Override
	public synchronized String enviarMensagemOffTopic( String destinatario, String assunto,
			String mensagem) throws Exception {
		
		return Correio.enviarMensagemOffTopic(this.getLogin(), destinatario, assunto, mensagem);
	}

	@Override
	public synchronized String enviarMensagemEmprestimo(String destinatario, String assunto,
			String mensagem, String idRequisicaoEmprestimo) throws Exception {

		return Correio.enviarMensagemEmprestimo(this.getLogin(), destinatario, assunto, mensagem, idRequisicaoEmprestimo);
	}

	@Override
	public synchronized String lerTopicos(String tipo) throws Exception {
		return Correio.lerTopicos(this.getLogin(), tipo);
	}

	@Override
	public synchronized String pesquisarItem( String chave, String atributo,
			String tipoOrdenacao, String criterioOrdenacao) throws Exception {
		
		StringBuffer saida = new StringBuffer();
		
		if( criterioOrdenacao.trim().equalsIgnoreCase("dataCriacao") ){
			//List<String> saidaDataCriacao = new LinkedList<String>();
			List<ItemIF> saidaDataCriacao = new LinkedList<ItemIF>();
			Iterator<UsuarioIF> iteradorUsuarios = this.amigos.iterator();
			while(iteradorUsuarios.hasNext()){
				UsuarioIF amigo = iteradorUsuarios.next();
				Iterator<ItemIF> iteradorItens = amigo.getItens().iterator();
				while(iteradorItens.hasNext()){
					ItemIF item = iteradorItens.next();
					if(atributo.trim().equalsIgnoreCase("nome")){
						if(item.getNome().toLowerCase().contains(chave.toLowerCase())){
							//saidaDataCriacao.add(item.getNome());
							saidaDataCriacao.add(item);
						}
					}else if(atributo.trim().equalsIgnoreCase("descricao")){
						if(item.getDescricao().toLowerCase().contains(chave.toLowerCase())){
							//saidaDataCriacao.add(item.getNome());
							saidaDataCriacao.add(item);
						}
					}else if(atributo.trim().equalsIgnoreCase("categoria")){
						if(item.getCategoria().toLowerCase().contains(chave.trim().toLowerCase())){
							//saidaDataCriacao.add(item.getNome());
							saidaDataCriacao.add(item);
						}
					}
				}
			}
			
			if(tipoOrdenacao.trim().equalsIgnoreCase("crescente")){
				Collections.sort(saidaDataCriacao, new DataCriacaoItemComparador());
			}else if(tipoOrdenacao.trim().equalsIgnoreCase("decrescente")){
				Collections.sort(saidaDataCriacao, new DataCriacaoItemComparador());
				Collections.reverse(saidaDataCriacao);
			}
			Iterator<ItemIF> listaItensRefinadosIterator = saidaDataCriacao.iterator();
			while(listaItensRefinadosIterator.hasNext()){
				saida.append(listaItensRefinadosIterator.next().getNome()+"; ");
			}
			
		}else if(criterioOrdenacao.trim().equalsIgnoreCase("reputacao")){
			//List<String> saidaDataCriacao = new LinkedList<String>();
			List<ItemIF> saidaReputacao = new LinkedList<ItemIF>();
			Map<Integer, List<ItemIF>> mapaItensPorReputacao = new HashMap<Integer, List<ItemIF>>();
			
			Iterator<UsuarioIF> iteradorUsuarios = this.amigos.iterator();
			while(iteradorUsuarios.hasNext()){
				
				List<ItemIF> listaItens = new LinkedList<ItemIF>();
				UsuarioIF amigo = iteradorUsuarios.next();
				Iterator<ItemIF> iteradorItens = amigo.getItens().iterator();
				while(iteradorItens.hasNext()){
					ItemIF item = iteradorItens.next();
					if(atributo.trim().equalsIgnoreCase("nome")){
						if(item.getNome().toLowerCase().contains(chave.toLowerCase())){
							//saidaDataCriacao.add(item.getNome());
							listaItens.add(item);
						}
					}else if(atributo.trim().equalsIgnoreCase("descricao")){
						if(item.getDescricao().toLowerCase().contains(chave.toLowerCase())){
							//saidaDataCriacao.add(item.getNome());
							listaItens.add(item);
						}
					}else if(atributo.trim().equalsIgnoreCase("categoria")){
						if(item.getCategoria().toLowerCase().contains(chave.trim().toLowerCase())){
							//saidaDataCriacao.add(item.getNome());
							listaItens.add(item);
						}
					}
				}
				//já percorri itens e adicionei na lista
				//verificar se a lista nao estah vazia
				if(!listaItens.isEmpty()){
					int reputacaoAtual = amigo.getReputacao();
					if(mapaItensPorReputacao.containsKey(reputacaoAtual)){
						Iterator<ItemIF> iteradorDaListaDeItensSaidaPorUsuario = listaItens.iterator();
						while(iteradorDaListaDeItensSaidaPorUsuario.hasNext()){
							mapaItensPorReputacao.get(reputacaoAtual).add(iteradorDaListaDeItensSaidaPorUsuario.next());
						}
					}else{
						mapaItensPorReputacao.put(reputacaoAtual, listaItens);
					}
					if(tipoOrdenacao.trim().equalsIgnoreCase("crescente")){
						Collections.sort(mapaItensPorReputacao.get(reputacaoAtual), new NomeItemComparador());
					}else if(tipoOrdenacao.trim().equalsIgnoreCase("decrescente")){
						Collections.sort(mapaItensPorReputacao.get(reputacaoAtual), new NomeItemComparador());
						Collections.reverse(mapaItensPorReputacao.get(reputacaoAtual));
					}	
				}
				//limpa lista itens
				listaItens = new LinkedList<ItemIF>();
				
			}
			//após a coleta e adição de cada lista de objetos por usuario, faremos a ordenação
			Set<Integer> listaChavesReputacao = mapaItensPorReputacao.keySet();
			Object[] arrayListaChaves = listaChavesReputacao.toArray();
			Arrays.sort(arrayListaChaves);
			
			
			if(arrayListaChaves.length > 0){
				
				if(tipoOrdenacao.trim().equalsIgnoreCase("crescente")){
					for( int i = 0; i < arrayListaChaves.length; i++ ){
						Iterator<ItemIF> iteradorListaReputadacaoMapaIterator = mapaItensPorReputacao.get(i).iterator();
						while(iteradorListaReputadacaoMapaIterator.hasNext()){
							saidaReputacao.add(iteradorListaReputadacaoMapaIterator.next());
						}
					}
					
				}else if(tipoOrdenacao.trim().equalsIgnoreCase("decrescente")){
					for( int i = arrayListaChaves.length-1; i >= 0; i-- ){
						Collections.reverse(mapaItensPorReputacao.get(i));
						Iterator<ItemIF> iteradorListaReputadacaoMapaIterator = mapaItensPorReputacao.get(i).iterator();
						while(iteradorListaReputadacaoMapaIterator.hasNext()){
							saidaReputacao.add(iteradorListaReputadacaoMapaIterator.next());
						}
					}
				}
				
				
			}
			//verificar saida reputacao
			if(!saidaReputacao.isEmpty()){
				Iterator<ItemIF> iteradorDaListaReputacao = saidaReputacao.iterator();
				while(iteradorDaListaReputacao.hasNext()){
					saida.append(iteradorDaListaReputacao.next().getNome()+"; ");
				}
			}
			
		}
		
		if(saida.toString().trim().equals(""))
			return Mensagem.NENHUM_ITEM_ENCONTRADO.getMensagem();
		
		
		return saida.toString().trim().substring(0, saida.toString().trim().length()-1);
	}
	

	@Override
	public int compareTo(UsuarioIF o) {
		return this.getLogin().compareTo(o.getLogin());
	}

	@Override
	public int getReputacao() {
		return this.reputacao;
	}

	@Override
	public void incrementaReputacao() {
		this.reputacao++;
	}

	@Override
	public void decrementaReputacao() {
		this.reputacao--;
	}

	@Override
	public void desfazerAmizade(String amigo) throws Exception {
		assertStringNaoVazia(amigo, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		asserteTrue(Autenticacao.existeUsuario(amigo), Mensagem.USUARIO_INEXISTENTE.getMensagem());
		
		if(!ehAmigo(amigo)){
			throw new Exception(Mensagem.AMIZADE_INEXISTENTE.getMensagem());
		}
		
		Iterator<UsuarioIF> iteradorAmigos = amigos.iterator();
		while(iteradorAmigos.hasNext()){
			
			UsuarioIF umAmigo = iteradorAmigos.next();
			if(amigo.trim().equalsIgnoreCase(umAmigo.getLogin().trim())){
				
				//remover requisições do usuário
				umAmigo.removerEmprestimosRequeridosPorAmigo(this);
				umAmigo.removerEmprestimosRequeridosPorMim(this);
				this.removerEmprestimosRequeridosPorMim(umAmigo);
				this.removerEmprestimosRequeridosPorAmigo(umAmigo);
				
				//remover usuario da lista
				umAmigo.removerAmigoDaLista(this);
				iteradorAmigos.remove();
			}
		}
	}

	@Override
	public void removerAmigoDaLista(UsuarioIF amigo) {
		this.amigos.remove(amigo);		
	}

	@Override
	public void removerEmprestimosRequeridosPorAmigo(UsuarioIF amigo) {
		BancoDeEmprestimos.getInstance().removerEmprestimosRequeridosPorAmigo(this.getLogin(), amigo);		
	}

	@Override
	public void removerEmprestimosRequeridosPorMim(UsuarioIF amigo) {
		BancoDeEmprestimos.getInstance().removerEmprestimosRequeridosPorMim(this.getLogin(), amigo);
	}

	@Override
	public boolean esteItemMePertence( String idItem ) throws Exception {
		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(), Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(ItemRepositorio.existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		return AcervoDeItens.getInstance().esteItemMePertence(this.getLogin(), idItem);
	}

	@Override
	public synchronized boolean requisiteiEsteItem(String idItem) throws Exception {
		return BancoDeEmprestimos.getInstance().requisiteiEsteItem(this.getLogin(), idItem);
	}

	@Override
	public List<UsuarioIF> getListaAmigos() {
		return this.amigos;
	}

	@Override
	public void apagarItem(String idItem) throws Exception {
		AcervoDeItens.getInstance().apagarItem(this.getLogin(), idItem);
	}

	@Override
	public void removerMinhaSolicitacaoEmprestimo(EmprestimoIF emprestimo) {
		BancoDeEmprestimos.getInstance().removerMinhaSolicitacaoEmprestimo(this.getLogin(), emprestimo);
		//this.emprestimosRequeridosPorMimEmEspera.remove(emprestimo);
	}

	@Override
	public void zerarHistorico() {
		historico.removeAllElements();
	}
	
	@Override
	public String getHistoricoToString() {
		StringBuffer sb = new StringBuffer();
		Iterator<Notificacao> iterador = historico.iterator();
		while (iterador.hasNext()) {
			sb.append(iterador.next().getMensagem(this));
			sb.append("; ");
		}
		if(sb.toString().equals("")) 
			return Mensagem.HISTORICO_VAZIO.getMensagem();
		return sb.toString().substring(0, sb.length()-2);
	}
	
}
