package sistema.usuario;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import sistema.autenticacao.Autenticacao;
import sistema.emprestimo.Emprestimo;
import sistema.emprestimo.EmprestimoIF;
import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.Item;
import sistema.item.ItemIF;
import sistema.mensagem.Chat;
import sistema.mensagem.ChatIF;
import sistema.mensagem.MensagemTipo;
import sistema.persistencia.EmprestimoRepositorio;
import sistema.persistencia.ItemRepositorio;
import sistema.persistencia.ChatRepositorio;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;
import static sistema.utilitarios.Validador.*;

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

	private List<UsuarioIF> amigos; // Grupo de amigos
	private List<UsuarioIF> queremSerMeusAmigos; // solicitacoes de amizade
	private List<UsuarioIF> queroSerAmigoDeles; // solicitacoes de amizade
	private List<ItemIF> itens; // itens do usuario
	private List<ItemIF> itens_emprestados; // lista de itens que o usuario
											// emprestou e ainda nao recebeu
	
	private List<EmprestimoIF> emprestimos; //lista de emprestimos do tipo emprestimo/beneficios
	private List<EmprestimoIF> emprestimosRequeridosPorAmigosEmEspera; //lista de emprestimos em espera que amigos fizeram a mim
	private List<EmprestimoIF> emprestimosRequeridosPorMimEmEspera; //lista de emprestimos em espera que fiz a amigos
	
	private List<ChatIF> conversasOfftopic; //lista de conversas offtopic
	private List<ChatIF> conversasNegociacao; //lista de conversas negociacao

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

		itens = new ArrayList<ItemIF>();
		amigos = new ArrayList<UsuarioIF>();
		queremSerMeusAmigos = new ArrayList<UsuarioIF>();
		queroSerAmigoDeles = new ArrayList<UsuarioIF>();
		emprestimos = new ArrayList<EmprestimoIF>();
		emprestimosRequeridosPorAmigosEmEspera = new ArrayList<EmprestimoIF>();
		emprestimosRequeridosPorMimEmEspera = new ArrayList<EmprestimoIF>();
		conversasOfftopic = new LinkedList<ChatIF>();
		conversasNegociacao = new LinkedList<ChatIF>();
	}

	@Override
	public void setLogin(String login) throws Exception {
		assertNaoNulo(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		this.login = login.trim();
	}

	@Override
	public void setNome(String nome) throws Exception {
		assertNaoNulo(nome, Mensagem.NOME_INVALIDO.getMensagem());
		assertStringNaoVazia(nome, Mensagem.NOME_INVALIDO.getMensagem());
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
		ItemIF item = new Item(nome, descricao, categoria);
		ItemRepositorio.cadastrarItem(item);
		itens.add(item);// o item eh modificdo pelo repositorio possuindo agora
						// um id valido
		return String
				.valueOf((Long.valueOf(ItemRepositorio.geraIdProxItem()) - 1));

	}

	@Override
	public boolean removerItem(String idItem) {
		for (ItemIF item : itens) {
			if (idItem.equals(item.getId())) {
				itens.remove(item);
				return true;
			}
		}
		return false;
	}

	@Override
	public String getListaIdItens() {
		StringBuilder listaIdItensString = new StringBuilder();
		Collections.sort(this.itens);
		for (ItemIF item : this.itens) {
			listaIdItensString.append(item.getId() + " ");
		}

		return listaIdItensString.toString().trim();
	}

	/**
	 * Recupera a lista detodos os itens do usuario.
	 * 
	 * @return Lista de itens.
	 */
	@Override
	public List<ItemIF> getItens() {
		return this.itens;
	}

	@Override
	public ItemIF getItem(String idItem) {
		for (ItemIF item : this.itens) {
			if (item.getId().equals(idItem)) {
				return item;
			}
		}

		return null;
	}

	@Override
	public int qntItens() {
		return this.itens.size();
	}

	@Override
	public int qntItensEmprestados() {
		return this.itens_emprestados.size();
	}

	@Override
	public String getListaIdItensEmprestados() {
		StringBuilder listaIdItensEmprestadosString = new StringBuilder();

		for (ItemIF itensEmprestados : this.itens_emprestados) {
			listaIdItensEmprestadosString
					.append(itensEmprestados.getId() + " ");
		}

		return listaIdItensEmprestadosString.toString().trim();
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
		UsuarioIF u = null;
		while(iterador.hasNext()){
			u = iterador.next();
			if(u.getLogin().trim().equalsIgnoreCase(login.trim())){
				u.aprovouAmizade(this);
				amigos.add(u);
				//queremSerMeusAmigos.remove(u);
				iterador.remove();
				return;
			}
			
		}
		throw new Exception(Mensagem.REQUISICAO_AMIZADE_INEXISTNTE.getMensagem());
		
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
		Validador.assertNaoNulo(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(login.trim(), Mensagem.LOGIN_INVALIDO.getMensagem());
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
		Validador.assertNaoNulo(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(login.trim(), Mensagem.LOGIN_INVALIDO.getMensagem());
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
		Iterator<UsuarioIF> iterador = getQueroSerAmigoDe().iterator();
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
	public boolean existeItemID(String idItem) {
		try {
			return itens.contains(new Item("placebo", "placebo", "FILME").setId(idItem));
		} catch (Exception e) {
		}// nao lanca excecao.
		return false;

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
	public String getListaItens() throws Exception{
		Iterator<ItemIF> iterador = itens.iterator();
		StringBuffer str = new StringBuffer();
		while(iterador.hasNext()){
			str.append(iterador.next().getNome()+"; ");
		}
		if(str.toString().trim().equals(""))
			return Mensagem.USUARIO_SEM_ITENS_CADASTRADOS.getMensagem();
		return str.toString().substring(0, str.toString().length()-2);
	}
	
	public boolean oItemMePertence( String idItem ) throws ArgumentoInvalidoException{
		Validador.assertNaoNulo(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(idItem.trim(), Mensagem.ID_ITEM_INVALIDO.getMensagem());
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
		Validador.assertNaoNulo(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(idItem.trim(), Mensagem.ID_ITEM_INVALIDO.getMensagem());
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
	
	public void adicionarRequisicaoEmprestimoEmEsperaDeAmigo(EmprestimoIF emp){
		this.emprestimosRequeridosPorAmigosEmEspera.add(emp);
	}

	@Override
	public String requisitarEmprestimo(String idItem, int duracao) throws Exception{
		Validador.assertNaoNulo(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(idItem.trim(), Mensagem.ID_ITEM_INVALIDO.getMensagem());
		Validador.asserteTrue(ItemRepositorio.existeItem(idItem.trim()), Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		Validador.asserteTrue(duracao > 0, Mensagem.EMPRESTIMO_DURACAO_INVALIDA.getMensagem());
		UsuarioIF amigo = ehItemDoMeuAmigo(idItem);
		Validador.asserteTrue( amigo != null , Mensagem.USUARIO_NAO_TEM_PERMISSAO_REQUISITAR_EMPREST_ITEM.getMensagem());
		
		
		ItemIF item = ItemRepositorio.recuperarItem(idItem);
		EmprestimoIF emp = new Emprestimo(amigo, this, item, "beneficiado", duracao);
		
		//verifica se jah fiz o pedido do item
		Iterator<EmprestimoIF> iterador = emprestimosRequeridosPorMimEmEspera.iterator();
		while(iterador.hasNext()){
			if(iterador.next().getItem().equals(item))
				throw new Exception(Mensagem.REQUISICAO_EMPRESTIMO_JA_SOLICITADA.getMensagem());
		}
		
		EmprestimoRepositorio.requisitarEmprestimo(emp);
		emprestimosRequeridosPorMimEmEspera.add(emp);// o emprestimo eh modificdo pelo repositorio possuindo agora
						// um id valido
		amigo.adicionarRequisicaoEmprestimoEmEsperaDeAmigo(emp);
		return emp.getIdEmprestimo();
		
	}
	

	@Override
	public String getEmprestimos(String tipo) throws Exception {
		Validador.assertNaoNulo(tipo, Mensagem.EMPRESTIMO_TIPO_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(tipo.trim(), Mensagem.EMPRESTIMO_TIPO_INVALIDO.getMensagem());
		//"mark-steve:The Social Network:Andamento; steve-mark:Guia do mochileiro das galáxias:Andamento"
		StringBuffer str = new StringBuffer();
		List<String> listaSaida = new ArrayList<String>();
		Iterator<EmprestimoIF> iterador = emprestimos.iterator();
		
		while(iterador.hasNext()){
			EmprestimoIF emp = iterador.next();
			if(tipo.trim().equalsIgnoreCase("emprestador")){
				
				if(this.equals(emp.getEmprestador())){
					
					listaSaida.add(emp.getEmprestador().getLogin()+"-"+
				               emp.getBeneficiado().getLogin()+":"+
				               emp.getItem().getNome()+":"+emp.getEstado()+"; ");
					Collections.sort(listaSaida);
				}
			}else if (tipo.trim().equalsIgnoreCase("beneficiado")){
				if(this.equals(emp.getBeneficiado())){
					
					listaSaida.add(emp.getEmprestador().getLogin()+"-"+
				               emp.getBeneficiado().getLogin()+":"+
				               emp.getItem().getNome()+":"+emp.getEstado()+"; ");
					Collections.sort(listaSaida);
				}
			}else if (tipo.trim().equalsIgnoreCase("todos")){
				
				if(this.equals(emp.getEmprestador())){
					listaSaida.add(0, emp.getEmprestador().getLogin()+"-"+
				               emp.getBeneficiado().getLogin()+":"+
				               emp.getItem().getNome()+":"+emp.getEstado()+"; ");
				}
				
				if(this.equals(emp.getBeneficiado())){
					listaSaida.add(emp.getEmprestador().getLogin()+"-"+
				               emp.getBeneficiado().getLogin()+":"+
				               emp.getItem().getNome()+":"+emp.getEstado()+"; ");
				}
				
			}else{
				throw new Exception(Mensagem.EMPRESTIMO_TIPO_INXISTENTE.getMensagem());
			}
		}
		
		for(String s : listaSaida){
			str.append(s);
		}
		
		if(str.toString().trim().equals("")) return Mensagem.NAO_HA_EMPRESTIMOS_DESTE_TIPO.getMensagem();
		return str.toString().trim().substring(0, str.toString().length()-2);
	}

	@Override
	public String aprovarEmprestimo( String idRequisicaoEmprestimo )
			throws Exception {
		
		assertNaoNulo(idRequisicaoEmprestimo, Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		assertStringNaoVazia(idRequisicaoEmprestimo, Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		asserteTrue(EmprestimoRepositorio.existeEmprestimo(idRequisicaoEmprestimo.trim()), Mensagem.ID_REQUISICAO_EMP_INEXISTENTE.getMensagem());
		
		EmprestimoIF emp = EmprestimoRepositorio.recuperarEmprestimo(idRequisicaoEmprestimo.trim());
				asserteTrue(this.equals(emp.getEmprestador()), Mensagem.EMPRESTIMO_SEM_PERMISSAO_APROVAR.getMensagem());
		asserteTrue(!emp.estahAceito(), Mensagem.EMPRESTIMO_JA_APROVADO.getMensagem());
		
		emp.setEstadoAceito();
		
		emprestimosRequeridosPorAmigosEmEspera.remove(emp);
		emprestimos.add(emp);
		UsuarioIF amigo = emp.getBeneficiado();
		amigo.emprestimoAceitoPorAmigo(emp);
		return emp.getIdEmprestimo();
		
	}
	
	public void emprestimoAceitoPorAmigo( EmprestimoIF emp ) throws Exception {
		if(!emp.estahAceito()) throw new Exception("Meu Amigo Recusou Emprestimo - ERRO");
		this.emprestimosRequeridosPorAmigosEmEspera.remove(emp);
		emprestimos.add(emp);
	}

	@Override
	public UsuarioIF possuoAmigoComEsteLogin(String login) throws Exception {
		assertNaoNulo(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem());
		
		Iterator<UsuarioIF> iterador = amigos.iterator();
		while(iterador.hasNext()){
			UsuarioIF amigo = iterador.next();
			if(amigo.getLogin().equals(login.trim()))
				return amigo;
		}
		
		return null;
	}
	
	public void adicionaConversaOfftopicNaLista( ChatIF conversa ){
		this.conversasOfftopic.add(conversa);
	}
	
	public void adicionaConversaNegociacaoNaLista( ChatIF conversa ){
		this.conversasNegociacao.add(conversa);
	}

	@Override
	public String enviarMensagemOffTopic( String destinatario, String assunto,
			String mensagem) throws Exception {
		
		assertNaoNulo(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		assertStringNaoVazia(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		UsuarioIF amigo = this.possuoAmigoComEsteLogin(destinatario);
		asserteTrue( amigo != null, Mensagem.DESTINATARIO_INEXISTENTE.getMensagem());
		
		assertNaoNulo(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem());
		assertStringNaoVazia(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem());
		
		assertNaoNulo(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem());
		assertStringNaoVazia(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem());
		
		ChatIF conversa = ChatRepositorio.existeConversaEntreAsPessoasSobreMesmoAssuntoETipo(this.login, 
				destinatario, assunto, true);
		
		if(conversa == null){
			conversa = new Chat(this, amigo, assunto.trim(), mensagem.trim());
			conversa.setTipoOffTopicMsg();
			ChatRepositorio.registrarConversa(conversa);
			this.conversasOfftopic.add(conversa);
			amigo.adicionaConversaOfftopicNaLista(conversa);
		}else{
			conversa.adicionaMensagem(mensagem);		
		}
		
		return conversa.getIdMensagem();
	}


	@Override
	public String enviarMensagemEmprestimo(String destinatario, String assunto,
			String mensagem, String idRequisicaoEmprestimo) throws Exception {

		assertNaoNulo(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		assertStringNaoVazia(destinatario, Mensagem.DESTINATARIO_INVALIDO.getMensagem());
		UsuarioIF amigo = this.possuoAmigoComEsteLogin(destinatario);
		asserteTrue( amigo != null, Mensagem.DESTINATARIO_INEXISTENTE.getMensagem());
		
		assertNaoNulo(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem());
		assertStringNaoVazia(assunto, Mensagem.ASSUNTO_INVALIDO.getMensagem());
		
		assertNaoNulo(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem());
		assertStringNaoVazia(mensagem, Mensagem.MENSAGEM_INVALIDA.getMensagem());
		
		assertNaoNulo(idRequisicaoEmprestimo, Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		assertStringNaoVazia(idRequisicaoEmprestimo, Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		asserteTrue(EmprestimoRepositorio.existeEmprestimo(idRequisicaoEmprestimo.trim()), 
				Mensagem.ID_REQUISICAO_EMP_INEXISTENTE.getMensagem());
		
		ChatIF conversa = ChatRepositorio.existeConversaEntreAsPessoasSobreMesmoAssuntoETipo(this.login, 
				destinatario, assunto, false);
		
		if(conversa == null){
			conversa = new Chat(this, amigo, assunto.trim(), 
					mensagem, idRequisicaoEmprestimo );
			conversa.setTipoNegociacaoMsg();
			ChatRepositorio.registrarConversa(conversa);
			this.conversasNegociacao.add(conversa);
			amigo.adicionaConversaNegociacaoNaLista(conversa);
		}else{
		    conversa.adicionaMensagem(mensagem);
		}
		
		return conversa.getIdMensagem();
		
	}

	@Override
	public String lerTopicos(String tipo) throws Exception {
		assertNaoNulo(tipo, Mensagem.TIPO_INVALIDO.getMensagem());
		assertStringNaoVazia(tipo, Mensagem.TIPO_INVALIDO.getMensagem());
		List<ChatIF> listaTopicos = new ArrayList<ChatIF>();
		StringBuffer saida = new StringBuffer();
		if(tipo.trim().equalsIgnoreCase("negociacao") || tipo.trim().equalsIgnoreCase("todos")){
			Iterator<ChatIF> iterador = conversasNegociacao.iterator();
			
			while(iterador.hasNext()){
				listaTopicos.add(iterador.next());
				//saida.append(iterador.next().getAssunto()+"; ");
			}
		}
		if(tipo.trim().equalsIgnoreCase("offtopic") || tipo.trim().equalsIgnoreCase("todos")){
			Iterator<ChatIF> iterador = conversasOfftopic.iterator();
			
			while(iterador.hasNext()){
				listaTopicos.add(iterador.next());
				//saida.append(iterador.next().getAssunto()+"; ");
			}		
		}
		if(!tipo.trim().equalsIgnoreCase("negociacao") && 
				!tipo.trim().equalsIgnoreCase("offtopic") && !tipo.trim().equalsIgnoreCase("todos")){
			return Mensagem.TIPO_INEXISTENTE.getMensagem();
		}
		//if(saida.toString().trim().equals(""))
		if(listaTopicos.isEmpty())
			return Mensagem.NAO_HA_TOPICOS_CRIADOS.getMensagem();
		Collections.sort(listaTopicos);
		Collections.reverse(listaTopicos);
		for (ChatIF c : listaTopicos) {
			System.out.println(c.getAssunto());
			saida.append(c.getAssunto()+"; ");
		}
		return saida.toString().trim().substring(0, saida.toString().trim().length()-1);
	}


}
