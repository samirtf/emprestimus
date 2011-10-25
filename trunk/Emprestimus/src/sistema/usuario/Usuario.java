package sistema.usuario;
import static sistema.utilitarios.Validador.assertNaoNulo;
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
import maps.GetCoordenadas;
import maps.RefCoordenadas;
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
import sistema.notificacao.GerenciadorDeNotificacoes;
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
 * Emprestimus
 * Projeto de Sistemas de Informação I
 * Universidade Federal de Campina Grande
 * 
 * Esta classe representa um usuario padrao do sistema.
 */

public class Usuario implements UsuarioIF {
	/* Atributos estaticos. */
	private static int ID_Prox_Usuario = 1; // ID do proximo usuario sera
											// guardado nesta variavel estatica.

	/* Atributos do objeto. */
	private String login, nome, endereco;

	private final int id = ID_Prox_Usuario++; // id (codigo unico) do usuario
	
	private int reputacao = 0;
	
	private double longitude, latitude;

	//private List<UsuarioIF> amigos; // Grupo de amigos
	//private List<UsuarioIF> queremSerMeusAmigos; // solicitacoes de amizade
	//private List<UsuarioIF> queroSerAmigoDeles; // solicitacoes de amizade
	
	//	protected List<String> historico;
	
	//private Stack<Notificacao> historico;
	
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
		//amigos = new ArrayList<UsuarioIF>();
		//queremSerMeusAmigos = new ArrayList<UsuarioIF>();
		//queroSerAmigoDeles = new ArrayList<UsuarioIF>();
		//emprestimos = new ArrayList<EmprestimoIF>();
		//emprestimosRequeridosPorAmigosEmEspera = new ArrayList<EmprestimoIF>();
		//emprestimosRequeridosPorMimEmEspera = new ArrayList<EmprestimoIF>();
		//conversasOfftopic = new LinkedList<ChatIF>();
		//conversasNegociacao = new LinkedList<ChatIF>();
		//historico = new Stack<Notificacao>();
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
			setCoordenadas();
		}
	}

	private void setCoordenadas() {
		RefCoordenadas r1 = GetCoordenadas.getCoordenadas(endereco);
		this.latitude = r1.getLatitude();
		this.longitude = r1.getLongitude();
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

	public void addHistoricoCadastrarItem(ItemIF item) throws Exception {
		GerenciadorDeNotificacoes.getInstance().addHistoricoNovoItem(this.getLogin(), item);
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
		RelacionamentosUsuarios.getInstance().aprovarAmizade(this.getLogin(), login);
	}
	
	public void addHistoricoAmizadeAprovada(UsuarioIF amigo) throws Exception {
		GerenciadorDeNotificacoes.getInstance().addHistoricoAmizadeAprovada(this.getLogin(), amigo);
	}

	public synchronized void aprovouAmizade( UsuarioIF usuario ) throws ArgumentoInvalidoException {
		RelacionamentosUsuarios.getInstance().aprovouAmizade(this.getLogin(), usuario);
	}
	
	@Override
	public List<UsuarioIF> getQueremSerMeusAmigos() throws Exception{
		return RelacionamentosUsuarios.getInstance().getCicloDeAmizade(this.getLogin()).getQueremSerMeusAmigos();
	}
	
	@Override
	public List<UsuarioIF> getQueroSerAmigoDe() throws Exception {
		return RelacionamentosUsuarios.getInstance().getCicloDeAmizade(this.getLogin()).getQueroSerAmigoDeles();
	}
	
	public boolean ehAmigo( String login ) throws ArgumentoInvalidoException{
		return RelacionamentosUsuarios.getInstance().ehAmigo(this.getLogin(), login);
	}
	
	public boolean amizadeDeFoiRequisitada( String login ) throws ArgumentoInvalidoException {
		return RelacionamentosUsuarios.getInstance().amizadeDeFoiRequisitada(this.getLogin(), login);
	}
	
	public void requisitarAmizade( String login ) throws Exception {
		RelacionamentosUsuarios.getInstance().requisitarAmizade(this.getLogin(), login);
	}
	
	public void usuarioQuerSerMeuAmigo( UsuarioIF usuarioSolicitante ) throws ArgumentoInvalidoException{
		RelacionamentosUsuarios.getInstance().usuarioQuerSerMeuAmigo(this.getLogin(), usuarioSolicitante);
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
		return RelacionamentosUsuarios.getInstance().getAmigos(this.getLogin());
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
		return RelacionamentosUsuarios.getInstance().ehItemDoMeuAmigo(this.getLogin(), idItem);
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
		GerenciadorDeNotificacoes.getInstance().addHistoricoEmprestimoEmAndamento(this.getLogin(), amigo, item);
	}

	public void emprestimoAceitoPorAmigo( EmprestimoIF emp ) throws Exception {
		BancoDeEmprestimos.getInstance().emprestimoAceitoPorAmigo(this.getLogin(), emp);
	}

	@Override
	public UsuarioIF possuoAmigoComEsteLogin(String loginDoAmigo) throws Exception {
		return RelacionamentosUsuarios.getInstance().possuoAmigoComEsteLogin(this.getLogin(), loginDoAmigo);
	}
	
	public void adicionaConversaOfftopicNaLista( ChatIF conversa ) throws Exception{
		Correio.adicionaConversaOfftopicNaLista(this.getLogin(), conversa);
	}
	
	public void adicionaConversaNegociacaoNaLista( ChatIF conversa ) throws Exception{
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
		
		return RelacionamentosUsuarios.getInstance().pesquisarItem(this.getLogin(), chave, atributo, tipoOrdenacao, criterioOrdenacao);
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
		RelacionamentosUsuarios.getInstance().desfazerAmizade(this.getLogin(), amigo);
	}

	@Override
	public void removerAmigoDaLista(UsuarioIF amigo) {
		RelacionamentosUsuarios.getInstance().removerAmigoDaLista(this.getLogin(), amigo);
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
		return RelacionamentosUsuarios.getInstance().getListaAmigos(this.getLogin());
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
		GerenciadorDeNotificacoes.getInstance().zerarHistorico(this.getLogin());
	}
	
	@Override
	public String getHistoricoToString() throws ArgumentoInvalidoException {
		return GerenciadorDeNotificacoes.getInstance().getHistoricoToString(this.getLogin());
	}
	
	public void registrarInteressePorItem(String idItem)
			throws Exception {

		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(), Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(ItemRepositorio.existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		AcervoDeItens.getInstance().registrarInteressePorItem(this.getLogin(), idItem);
	}
	
	public static void main(String[] args){
		Stack<String> pilha = new Stack<String>();
		pilha.push("samir");
		pilha.push("musculos");
		pilha.push("etcetara");
		pilha.push("caos");
		Collections.reverse(pilha);
		Iterator<String> iteraString = pilha.iterator();
		while(iteraString.hasNext()){
			System.out.println(iteraString.next());
		}
		
		System.out.println(pilha.pop());
	}

	@Override
	public void addHistoricoTerminoEmprestimo(ItemIF item) throws Exception {
		GerenciadorDeNotificacoes.getInstance().addHistoricoTerminoEmprestimo(this.getLogin(), item);
	}

	@Override
	public double getLongitude() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getLatitude() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
