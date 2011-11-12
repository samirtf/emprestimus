package sistema.usuario;

import static sistema.utilitarios.Validador.assertStringNaoVazia;
import static sistema.utilitarios.Validador.asserteTrue;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import sistema.mensagem.ChatIF;
import sistema.mensagem.Correio;
import sistema.notificacao.GerenciadorDeNotificacoes;
import sistema.persistencia.ItemRepositorio;
import sistema.utilitarios.Criptografia;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;

/**
 * Emprestimus Projeto de Sistemas de Informação I Universidade Federal de
 * Campina Grande
 * 
 * Esta classe representa um usuario padrao do sistema.
 */

public class Usuario implements UsuarioIF {
	/* Atributos estaticos. */
	private static int ID_Prox_Usuario = 1; // ID do proximo usuario sera
											// guardado nesta variavel estatica.

	/* Atributos do objeto. */
	private String login, nome, endereco, senha, emailRedefSenha;

	private final int id = ID_Prox_Usuario++; // id (codigo unico) do usuario
	
	private int reputacao = 0;
	
	private double longitude, latitude;

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
	}

	public Usuario(String login, String senha, String nome, String endereco) throws Exception{
		// Estes métodos podem lançar exceção
		setLogin(login);
		setSenha(senha);
		setNome(nome);
		setEndereco(endereco);
	}

	@Override
	public void setLogin(String login) throws Exception {
		assertStringNaoVazia(login, Mensagem.LOGIN_INVALIDO.getMensagem(),
				Mensagem.LOGIN_INVALIDO.getMensagem());
		this.login = login.trim();
	}

	@Override
	public void setNome(String nome) throws Exception {
		assertStringNaoVazia(nome, Mensagem.NOME_INVALIDO.getMensagem(),
				Mensagem.NOME_INVALIDO.getMensagem());
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
	public String cadastrarItem(String nome, String descricao, String categoria) throws Exception {
		String idItem = AcervoDeItens.getInstance().cadastrarItem(this.getLogin(), nome,
				descricao, categoria);
		ItemIF item = ItemRepositorio.recuperarItem(idItem);
		addHistoricoCadastrarItem(item);
		return item.getId();

	}

	public void addHistoricoCadastrarItem(ItemIF item) throws Exception {
		GerenciadorDeNotificacoes.getInstance().addHistoricoNovoItem(this.getLogin(),
				item);
	}

	@Override
	public boolean removerItem(String idItem) throws ArgumentoInvalidoException {
		return AcervoDeItens.getInstance().removerItem(this.getLogin(), idItem);
	}

	@Override
	public String getListaIdItens() throws ArgumentoInvalidoException {
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
	public boolean estahItemDisponivel(String idItem) {
		try {
			return getItem(idItem).estahDisponivel();

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public synchronized void aprovarAmizade(String login) throws Exception {
		RelacionamentosUsuarios.getInstance().aprovarAmizade(this.getLogin(), login);
	}

	@Override
	public void addHistoricoAmizadeAprovada(UsuarioIF amigo) throws Exception {
		GerenciadorDeNotificacoes.getInstance().addHistoricoAmizadeAprovada(
				this.getLogin(), amigo);
	}

	@Override
	public synchronized void aprovouAmizade(UsuarioIF usuario) throws ArgumentoInvalidoException {
		RelacionamentosUsuarios.getInstance().aprovouAmizade(this.getLogin(), usuario);
	}

	@Override
	public List<UsuarioIF> getQueremSerMeusAmigos() throws Exception {
		return RelacionamentosUsuarios.getInstance().getCicloDeAmizade(this.getLogin())
				.getQueremSerMeusAmigos();
	}
	
	@Override
	public List<UsuarioIF> getQueroSerAmigoDe() throws Exception {
		return RelacionamentosUsuarios.getInstance().getCicloDeAmizade(this.getLogin()).getQueroSerAmigoDeles();
	}

	@Override
	public boolean ehAmigo(String login) throws ArgumentoInvalidoException {
		return RelacionamentosUsuarios.getInstance().ehAmigo(this.getLogin(), login);
	}

	@Override
	public boolean amizadeDeFoiRequisitada(String login) throws ArgumentoInvalidoException {
		return RelacionamentosUsuarios.getInstance().amizadeDeFoiRequisitada(
				this.getLogin(), login);
	}

	@Override
	public void requisitarAmizade(String login) throws Exception {
		RelacionamentosUsuarios.getInstance().requisitarAmizade(this.getLogin(), login);
	}

	@Override
	public void usuarioQuerSerMeuAmigo(UsuarioIF usuarioSolicitante) throws ArgumentoInvalidoException {
		RelacionamentosUsuarios.getInstance().usuarioQuerSerMeuAmigo(this.getLogin(),
				usuarioSolicitante);
	}

	@Override
	public boolean equals(Object outroUsuario) {
		if (outroUsuario instanceof UsuarioIF) {
			return this.getLogin().equals(((UsuarioIF) outroUsuario).getLogin());
		}
		return false;
	}

	@Override
	public boolean existeItemID(String idItem) throws ArgumentoInvalidoException {
		return AcervoDeItens.getInstance().existeItemID(this.getLogin(), idItem);
	}

	@Override
	public String getAmigos() throws Exception {
		return RelacionamentosUsuarios.getInstance().getAmigos(this.getLogin());
	}

	@Override
	public String getListaItens() throws Exception {
		return AcervoDeItens.getInstance().getListaItens(login);
	}

	@Override
	public boolean oItemMePertence(String idItem) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(),
				Mensagem.ID_ITEM_INVALIDO.getMensagem());
		Validador.asserteTrue(ItemRepositorio.existeItem(idItem.trim()),
				Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		Iterator<ItemIF> iterador = getItens().iterator();
		while (iterador.hasNext()) {
			if (iterador.next().getId().trim().equalsIgnoreCase(idItem.trim())) {
				return true;
			}
		}
		return false;

	}

	@Override
	public UsuarioIF ehItemDoMeuAmigo(String idItem) throws Exception {
		return RelacionamentosUsuarios.getInstance().ehItemDoMeuAmigo(this.getLogin(),
				idItem);
	}

	@Override
	public void adicionarRequisicaoEmprestimoEmEsperaDeAmigo(EmprestimoIF emp) throws Exception {
		BancoDeEmprestimos.getInstance().adicionarRequisicaoEmprestimoEmEsperaDeAmigo(
				this.getLogin(), emp);
	}

	@Override
	public synchronized String requisitarEmprestimo(String idItem, int duracao) throws Exception {
		return BancoDeEmprestimos.getInstance().requisitarEmprestimo(this.getLogin(),
				idItem, duracao);
	}

	@Override
	public String getEmprestimos(String tipo) throws Exception {
		return BancoDeEmprestimos.getInstance().getEmprestimos(this.getLogin(), tipo);
	}

	@Override
	public String aprovarEmprestimo(String idRequisicaoEmprestimo) throws Exception {
		return BancoDeEmprestimos.getInstance().aprovarEmprestimo(this.getLogin(),
				idRequisicaoEmprestimo);
	}
	
	/**
	 * @param amigo
	 * @param item
	 * @throws Exception 
	 */
	@Override
	public void addHistoricoEmprestimoEmAndamento(UsuarioIF amigo, ItemIF item) throws Exception {
		GerenciadorDeNotificacoes.getInstance().addHistoricoEmprestimoEmAndamento(
				this.getLogin(), amigo, item);
	}

	@Override
	public void emprestimoAceitoPorAmigo(EmprestimoIF emp) throws Exception {
		BancoDeEmprestimos.getInstance().emprestimoAceitoPorAmigo(this.getLogin(), emp);
	}

	@Override
	public UsuarioIF possuoAmigoComEsteLogin(String loginDoAmigo) throws Exception {
		return RelacionamentosUsuarios.getInstance().possuoAmigoComEsteLogin(
				this.getLogin(), loginDoAmigo);
	}

	@Override
	public void adicionaConversaOfftopicNaLista(ChatIF conversa) throws Exception {
		Correio.adicionaConversaOfftopicNaLista(this.getLogin(), conversa);
	}

	@Override
	public void adicionaConversaNegociacaoNaLista(ChatIF conversa) throws Exception {
		Correio.adicionaConversaNegociacaoNaLista(this.getLogin(), conversa);
	}

	@Override
	public synchronized String enviarMensagemOffTopic(String destinatario,
			String assunto, String mensagem) throws Exception {

		return Correio.enviarMensagemOffTopic(this.getLogin(), destinatario, assunto,
				mensagem);
	}

	@Override
	public synchronized String enviarMensagemEmprestimo(String destinatario,
			String assunto, String mensagem, String idRequisicaoEmprestimo) throws Exception {

		return Correio.enviarMensagemEmprestimo(this.getLogin(), destinatario, assunto,
				mensagem, idRequisicaoEmprestimo);
	}

	@Override
	public synchronized String lerTopicos(String tipo) throws Exception {
		return Correio.lerTopicos(this.getLogin(), tipo);
	}

	@Override
	public synchronized String pesquisarItem(String chave, String atributo,
			String tipoOrdenacao, String criterioOrdenacao) throws Exception {

		return RelacionamentosUsuarios.getInstance().pesquisarItem(this.getLogin(),
				chave, atributo, tipoOrdenacao, criterioOrdenacao);
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
		BancoDeEmprestimos.getInstance().removerEmprestimosRequeridosPorAmigo(
				this.getLogin(), amigo);
	}

	@Override
	public void removerEmprestimosRequeridosPorMim(UsuarioIF amigo) {
		BancoDeEmprestimos.getInstance().removerEmprestimosRequeridosPorMim(
				this.getLogin(), amigo);
	}

	@Override
	public boolean esteItemMePertence(String idItem) throws Exception {
		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(),
				Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(ItemRepositorio.existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE
				.getMensagem());
		return AcervoDeItens.getInstance().esteItemMePertence(this.getLogin(), idItem);
	}

	@Override
	public synchronized boolean requisiteiEsteItem(String idItem) throws Exception {
		return BancoDeEmprestimos.getInstance().requisiteiEsteItem(this.getLogin(),
				idItem);
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
		BancoDeEmprestimos.getInstance().removerMinhaSolicitacaoEmprestimo(
				this.getLogin(), emprestimo);
		// this.emprestimosRequeridosPorMimEmEspera.remove(emprestimo);
	}

	@Override
	public void zerarHistorico() {
		GerenciadorDeNotificacoes.getInstance().zerarHistorico(this.getLogin());
	}

	@Override
	public String getHistoricoToString() throws ArgumentoInvalidoException {
		return GerenciadorDeNotificacoes.getInstance().getHistoricoToString(
				this.getLogin());
	}
	
	public void registrarInteressePorItem(String idItem)
			throws Exception {

		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(), Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(ItemRepositorio.existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		AcervoDeItens.getInstance().registrarInteressePorItem(this.getLogin(), idItem);
	}
	

	@Override
	public void addHistoricoTerminoEmprestimo(ItemIF item) throws Exception {
		GerenciadorDeNotificacoes.getInstance().addHistoricoTerminoEmprestimo(this.getLogin(), item);
	}

	@Override
	public double getLongitude() {
		return this.longitude;
	}

	@Override
	public double getLatitude() {
		return this.latitude;
	}

	@Override
	public String publicarPedido(String nomeItem, String descricaoItem) throws Exception {

		return GerenciadorDeNotificacoes.getInstance().addHistoricoPublicarPedido(
				this.getLogin(), nomeItem, descricaoItem);
	}

	@Override
	public void oferecerItem(String idPublicacaoPedido, String idItem) throws Exception {
		AcervoDeItens.getInstance().oferecerItem(this.getLogin(), idPublicacaoPedido,
				idItem);

	}

	@Override
	public void republicarPedido(String idPublicacaoPedido) throws Exception {
		GerenciadorDeNotificacoes.getInstance()
				.republicarPedido(this, idPublicacaoPedido);

	}

	@Override
	public String enviarMensagemOferecimentoItemOffTopic(String destinatario,
			String assunto, String mensagem) throws Exception {

		return Correio.enviarMensagemOferecimentoItemOffTopic(this.getLogin(),
				destinatario, assunto, mensagem);
	}

	/**
	 * @return the senha
	 */
	private String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) throws Exception {
		assertStringNaoVazia(senha, Mensagem.SENHA_INVALIDA.getMensagem(), Mensagem.SENHA_INVALIDA.getMensagem());
		this.senha = Criptografia.criptografaMD5(getLogin(), senha);
	}

	@Override
	public boolean logar(String senha) {
		try {
			String passeCriptografado = Criptografia.criptografaMD5(getLogin(), senha);
			return passeCriptografado.equals(getSenha());
		} catch (Exception e) {}
		return false;
	}

	@Override
	public void cadastrarEmailRedefinicaoSenha(String email) throws Exception {
		assertStringNaoVazia(email, Mensagem.EMAIL_INVALIDO.getMensagem(), Mensagem.EMAIL_INVALIDO.getMensagem());
		
		//Set the email pattern string ".+@.+\\.[a-z]+" "^[\\w\\-]([\\.\\w])+[\\w]+@([\\w\\-]+\\.)+[A-Z]{2,4}$"
	      Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");  
	  
	      //Match the given string with the pattern  
	      Matcher m = p.matcher(email);  
	  
	      //check whether match is found   
	      boolean matchFound = m.matches();  
	  
	      if (!matchFound)
	    	  throw new Exception(Mensagem.EMAIL_INVALIDO.getMensagem());
	        
		this.emailRedefSenha = email;
	}

	private String getEmailRedefSenha() {
		return emailRedefSenha;
	}

	@Override
	public void alterarSenha(String senhaAtual, String senhaNova)
			throws Exception {
		assertStringNaoVazia(senhaAtual, Mensagem.SENHA_ATUAL_INVALIDA.getMensagem(), 
				Mensagem.SENHA_ATUAL_INVALIDA.getMensagem());
		assertStringNaoVazia(senhaNova, Mensagem.SENHA_NOVA_INVALIDA.getMensagem(), 
				Mensagem.SENHA_NOVA_INVALIDA.getMensagem());
		
		if(Criptografia.criptografaMD5(getLogin(), senhaAtual).equals(this.senha)){
			this.senha = Criptografia.criptografaMD5(getLogin(), senhaNova);
		}else{
			throw new Exception(Mensagem.SENHA_ATUAL_INVALIDA.getMensagem());
		}
		
	}
	
		
}
