package iu.web.server.sistema.usuario;

import static iu.web.server.sistema.utilitarios.Validador.assertStringNaoVazia;
import static iu.web.server.sistema.utilitarios.Validador.asserteTrue;

import iu.web.server.sistema.autenticacao.Autenticacao;
import iu.web.server.sistema.autenticacao.ServicoRecuperacaoSenhaUsuario;
import iu.web.server.sistema.dao.AcervoDeItensDAO;
import iu.web.server.sistema.dao.AcervoDeItensFileDAO;
import iu.web.server.sistema.dao.BancoDeEmprestimosDAO;
import iu.web.server.sistema.dao.BancoDeEmprestimosFileDAO;
import iu.web.server.sistema.dao.CorreioDAO;
import iu.web.server.sistema.dao.CorreioFileDAO;
import iu.web.server.sistema.dao.GerenciadorDeNotificacoesDAO;
import iu.web.server.sistema.dao.GerenciadorDeNotificacoesFileDAO;
import iu.web.server.sistema.dao.ItemFileDAO;
import iu.web.server.sistema.dao.RelacionamentosUsuariosDAO;
import iu.web.server.sistema.dao.RelacionamentosUsuariosFileDAO;
import iu.web.server.sistema.emprestimo.BancoDeEmprestimos;
import iu.web.server.sistema.emprestimo.Emprestimo;
import iu.web.server.sistema.emprestimo.EmprestimoIF;
import iu.web.server.sistema.excecoes.ArgumentoInvalidoException;
import iu.web.server.sistema.item.AcervoDeItens;
import iu.web.server.sistema.item.DataCriacaoItemComparador;
import iu.web.server.sistema.item.Item;
import iu.web.server.sistema.item.ItemIF;
import iu.web.server.sistema.mensagem.ChatIF;
import iu.web.server.sistema.mensagem.Correio;
import iu.web.server.sistema.notificacao.GerenciadorDeNotificacoes;
import iu.web.server.sistema.persistencia.ItemRepositorio;
import iu.web.server.sistema.utilitarios.Criptografia;
import iu.web.server.sistema.utilitarios.Mensagem;
import iu.web.server.sistema.utilitarios.Validador;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;

import maps.GetCoordenadas;
import maps.RefCoordenadas;

/**
 * Emprestimus Projeto de Sistemas de Informação I Universidade Federal de
 * Campina Grande
 * 
 * Esta classe representa um usuario padrao do sistema.
 */

public class Usuario implements UsuarioIF {

	/* Atributos do objeto. */
	private String login, nome, endereco, senha, emailRedefSenha = "";
	private String cartaoAcessoRedefSenha = "";
	
	private int reputacao = 0;
	
	private double longitude, latitude;

	private String caminhoImagemPerfil;
	private transient CorreioDAO correioDao = new CorreioFileDAO();
	private transient AcervoDeItensDAO acervoItensDao = new AcervoDeItensFileDAO();
	private transient GerenciadorDeNotificacoesDAO gerenciadorNotificacoesDao = new GerenciadorDeNotificacoesFileDAO();
	private transient RelacionamentosUsuariosDAO relacionamentosUsuariosDao = new RelacionamentosUsuariosFileDAO();
	private transient BancoDeEmprestimosDAO bancoEmprestimosDao = new BancoDeEmprestimosFileDAO();

	/**
	 * Construtor padrao eh privado e nao oferece implementacao.
	 */
	private Usuario() {}

	/**
	 * Constroi um usuario a partir de um login, nome e endereco.
	 * 
	 * @param String
	 * 		O login do usuario.
	 * @param String
	 * 		O nome do usuario.
	 * @param String
	 * 		O endereco do usuario.
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
		Validador.assertStringNaoVazia(nome, Mensagem.NOME_INVALIDO.getMensagem(), Mensagem.NOME_INVALIDO.getMensagem());
		String idItem = acervoItensDao.cadastrarItem(this.getLogin(), nome,
				descricao, categoria);
		ItemIF item = new ItemFileDAO().recuperarItem(idItem);
		addHistoricoCadastrarItem(item);
		return item.getId();

	}

	public void addHistoricoCadastrarItem(ItemIF item) throws Exception {
		gerenciadorNotificacoesDao.addHistoricoNovoItem(this.getLogin(),
				item);
	}

	@Override
	public boolean removerItem(String idItem) throws ArgumentoInvalidoException {
		return acervoItensDao.removerItem(this.getLogin(), idItem);
	}

	@Override
	public String getListaIdItens() throws ArgumentoInvalidoException {
		return acervoItensDao.getListaIdItens(login);
	}

	@Override
	public List<ItemIF> getItens() {
		return acervoItensDao.getItens(this.getLogin());
	}

	@Override
	public ItemIF getItem(String idItem) throws ArgumentoInvalidoException{
		return acervoItensDao.getItem(this.getLogin(), idItem);
	}

	@Override
	public int qntItens() throws ArgumentoInvalidoException{
		return acervoItensDao.qntItens(login);
	}

	@Override
	public int qntItensEmprestados() throws ArgumentoInvalidoException{
		return acervoItensDao.qntItensEmprestados(login);
	}

	@Override
	public String getListaIdItensEmprestados() throws ArgumentoInvalidoException{
		return acervoItensDao.getListaIdItensEmprestados(login);
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
		relacionamentosUsuariosDao.aprovarAmizade(this.getLogin(), login);
	}

	@Override
	public void addHistoricoAmizadeAprovada(UsuarioIF amigo) throws Exception {
		gerenciadorNotificacoesDao.addHistoricoAmizadeAprovada(
				this.getLogin(), amigo);
	}

	@Override
	public synchronized void aprovouAmizade(UsuarioIF usuario) throws ArgumentoInvalidoException {
		relacionamentosUsuariosDao.aprovouAmizade(this.getLogin(), usuario);
	}

	@Override
	public List<UsuarioIF> getQueremSerMeusAmigos() throws Exception {
		return relacionamentosUsuariosDao.getCicloDeAmizade(this.getLogin())
				.getQueremSerMeusAmigos();
	}
	
	@Override
	public List<UsuarioIF> getQueroSerAmigoDe() throws Exception {
		return relacionamentosUsuariosDao.getCicloDeAmizade(this.getLogin()).getQueroSerAmigoDeles();
	}

	@Override
	public boolean ehAmigo(String login) throws ArgumentoInvalidoException {
		return relacionamentosUsuariosDao.ehAmigo(this.getLogin(), login);
	}

	@Override
	public boolean amizadeDeFoiRequisitada(String login) throws ArgumentoInvalidoException {
		return relacionamentosUsuariosDao.amizadeDeFoiRequisitada(
				this.getLogin(), login);
	}

	@Override
	public void requisitarAmizade(String login) throws Exception {
		relacionamentosUsuariosDao.requisitarAmizade(this.getLogin(), login);
	}

	@Override
	public void usuarioQuerSerMeuAmigo(UsuarioIF usuarioSolicitante) throws ArgumentoInvalidoException {
		relacionamentosUsuariosDao.usuarioQuerSerMeuAmigo(this.getLogin(),
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
		return acervoItensDao.existeItemID(this.getLogin(), idItem);
	}

	@Override
	public String getAmigos() throws Exception {
		return relacionamentosUsuariosDao.getAmigos(this.getLogin());
	}

	@Override
	public String getListaItens() throws Exception {
		return acervoItensDao.getListaItens(login);
	}

	@Override
	public boolean oItemMePertence(String idItem) throws ArgumentoInvalidoException {
		Validador.assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(),
				Mensagem.ID_ITEM_INVALIDO.getMensagem());
		Validador.asserteTrue(new ItemFileDAO().existeItem(idItem.trim()),
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
		return relacionamentosUsuariosDao.ehItemDoMeuAmigo(this.getLogin(),
				idItem);
	}

	@Override
	public void adicionarRequisicaoEmprestimoEmEsperaDeAmigo(EmprestimoIF emp) throws Exception {
		bancoEmprestimosDao.adicionarRequisicaoEmprestimoEmEsperaDeAmigo(
				this.getLogin(), emp);
	}

	@Override
	public synchronized String requisitarEmprestimo(String idItem, int duracao) throws Exception {
		return bancoEmprestimosDao.requisitarEmprestimo(this.getLogin(),
				idItem, duracao);
	}

	@Override
	public String getEmprestimos(String tipo) throws Exception {
		return bancoEmprestimosDao.getEmprestimos(this.getLogin(), tipo);
	}

	@Override
	public String aprovarEmprestimo(String idRequisicaoEmprestimo) throws Exception {
		return bancoEmprestimosDao.aprovarEmprestimo(this.getLogin(),
				idRequisicaoEmprestimo);
	}
	
	@Override
	public void addHistoricoEmprestimoEmAndamento(UsuarioIF amigo, ItemIF item) throws Exception {
		gerenciadorNotificacoesDao.addHistoricoEmprestimoEmAndamento(
				this.getLogin(), amigo, item);
	}

	@Override
	public void emprestimoAceitoPorAmigo(EmprestimoIF emp) throws Exception {
		bancoEmprestimosDao.emprestimoAceitoPorAmigo(this.getLogin(), emp);
	}

	@Override
	public UsuarioIF possuoAmigoComEsteLogin(String loginDoAmigo) throws Exception {
		return relacionamentosUsuariosDao.possuoAmigoComEsteLogin(
				this.getLogin(), loginDoAmigo);
	}

	@Override
	public void adicionaConversaOfftopicNaLista(ChatIF conversa) throws Exception {
		new CorreioFileDAO().adicionaConversaOfftopicNaLista(this.getLogin(), conversa);
	}

	@Override
	public void adicionaConversaNegociacaoNaLista(ChatIF conversa) throws Exception {
		correioDao.adicionaConversaNegociacaoNaLista(this.getLogin(), conversa);
	}

	@Override
	public synchronized String enviarMensagemOffTopic(String destinatario,
			String assunto, String mensagem) throws Exception {

		return correioDao.enviarMensagemOffTopic(this.getLogin(), destinatario, assunto,
				mensagem);
	}

	@Override
	public synchronized String enviarMensagemEmprestimo(String destinatario,
			String assunto, String mensagem, String idRequisicaoEmprestimo) throws Exception {

		return correioDao.enviarMensagemEmprestimo(this.getLogin(), destinatario, assunto,
				mensagem, idRequisicaoEmprestimo);
	}

	@Override
	public synchronized String lerTopicos(String tipo) throws Exception {
		return correioDao.lerTopicos(this.getLogin(), tipo);
	}

	@Override
	public synchronized String pesquisarItem(String chave, String atributo,
			String tipoOrdenacao, String criterioOrdenacao) throws Exception {

		String retorno = null;
		try{
			retorno = relacionamentosUsuariosDao.pesquisarItem(this.getLogin(),
					chave, atributo, tipoOrdenacao, criterioOrdenacao);
		}catch(Exception e){
			e.printStackTrace();
		}
		return retorno;
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
		relacionamentosUsuariosDao.desfazerAmizade(this.getLogin(), amigo);
	}

	@Override
	public void removerAmigoDaLista(UsuarioIF amigo) {
		relacionamentosUsuariosDao.removerAmigoDaLista(this.getLogin(), amigo);
	}

	@Override
	public void removerEmprestimosRequeridosPorAmigo(UsuarioIF amigo) {
		bancoEmprestimosDao.removerEmprestimosRequeridosPorAmigo(
				this.getLogin(), amigo);
	}

	@Override
	public void removerEmprestimosRequeridosPorMim(UsuarioIF amigo) {
		bancoEmprestimosDao.removerEmprestimosRequeridosPorMim(
				this.getLogin(), amigo);
	}

	@Override
	public boolean esteItemMePertence(String idItem) throws Exception {
		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(),
				Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(new ItemFileDAO().existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE
				.getMensagem());
		return acervoItensDao.esteItemMePertence(this.getLogin(), idItem);
	}

	@Override
	public synchronized boolean requisiteiEsteItem(String idItem) throws Exception {
		return bancoEmprestimosDao.requisiteiEsteItem(this.getLogin(),
				idItem);
	}

	@Override
	public List<UsuarioIF> getListaAmigos() {
		return relacionamentosUsuariosDao.getListaAmigos(this.getLogin());
	}

	@Override
	public void apagarItem(String idItem) throws Exception {
		acervoItensDao.apagarItem(this.getLogin(), idItem);
	}

	@Override
	public void removerMinhaSolicitacaoEmprestimo(EmprestimoIF emprestimo) {
		bancoEmprestimosDao.removerMinhaSolicitacaoEmprestimo(
				this.getLogin(), emprestimo);
		// this.emprestimosRequeridosPorMimEmEspera.remove(emprestimo);
	}

	@Override
	public void zerarHistorico() {
		gerenciadorNotificacoesDao.zerarHistorico(this.getLogin());
	}

	@Override
	public String getHistoricoToString() throws ArgumentoInvalidoException {
		return gerenciadorNotificacoesDao.getHistoricoToString(
				this.getLogin());
	}
	
	public void registrarInteressePorItem(String idItem)
			throws Exception {

		assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(), Mensagem.ID_ITEM_INVALIDO.getMensagem());
		asserteTrue(new ItemFileDAO().existeItem(idItem), Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		acervoItensDao.registrarInteressePorItem(this.getLogin(), idItem);
	}
	

	@Override
	public void addHistoricoTerminoEmprestimo(ItemIF item) throws Exception {
		gerenciadorNotificacoesDao.addHistoricoTerminoEmprestimo(this.getLogin(), item);
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
	public synchronized String publicarPedido(String nomeItem, String descricaoItem) throws Exception {

		return gerenciadorNotificacoesDao.addHistoricoPublicarPedido(
				this.getLogin(), nomeItem, descricaoItem);
	}

	@Override
	public void oferecerItem(String idPublicacaoPedido, String idItem) throws Exception {
		acervoItensDao.oferecerItem(this.getLogin(), idPublicacaoPedido,
				idItem);

	}

	@Override
	public synchronized void republicarPedido(String idPublicacaoPedido) throws Exception {
		gerenciadorNotificacoesDao.republicarPedido(this, idPublicacaoPedido);

	}

	@Override
	public String enviarMensagemOferecimentoItemOffTopic(String destinatario,
			String assunto, String mensagem) throws Exception {

		return correioDao.enviarMensagemOferecimentoItemOffTopic(this.getLogin(),
				destinatario, assunto, mensagem);
	}

	/**
	 * @return String
	 * 		A senha do usuario
	 */
	private String getSenha() {
		return senha;
	}

	/**
	 * @param String
	 * 		Nova senha
	 */
	public void setSenha(String senha) throws Exception {
		assertStringNaoVazia(senha, Mensagem.SENHA_INVALIDA.getMensagem(), Mensagem.SENHA_INVALIDA.getMensagem());
		this.senha = Criptografia.criptografaMD5(getLogin(), senha);
	}

	@Override
	public synchronized boolean logar(String senha) {
		boolean chaveRequisitouRedefSenha = ServicoRecuperacaoSenhaUsuario.getInstance()
				.requisitouRedefinicaoSenha(this.getLogin());
		try {
			String passeCriptografado = Criptografia.criptografaMD5(getLogin(), senha);
			if(chaveRequisitouRedefSenha){
				if(passeCriptografado.equals(getCartaoAcessoRedefSenha())){
					ServicoRecuperacaoSenhaUsuario.getInstance().
					removerRequisicaoRedefinicaoSenha(this.getLogin());
					return true;
				}
					
			}
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
	public synchronized void alterarSenha(String senhaAtual, String senhaNova)
			throws Exception {
		assertStringNaoVazia(senhaAtual, Mensagem.SENHA_ATUAL_INVALIDA.getMensagem(), 
				Mensagem.SENHA_ATUAL_INVALIDA.getMensagem());
		assertStringNaoVazia(senhaNova, Mensagem.SENHA_NOVA_INVALIDA.getMensagem(), 
				Mensagem.SENHA_NOVA_INVALIDA.getMensagem());
		
		if(Criptografia.criptografaMD5(getLogin(), senhaAtual).equals(this.senha)){
			this.senha = Criptografia.criptografaMD5(getLogin(), senhaNova);
			this.cartaoAcessoRedefSenha = Criptografia.criptografaMD5(getLogin(), 
					RandomStringUtils.randomAlphanumeric(20));
		}else{
			throw new Exception(Mensagem.SENHA_ATUAL_INVALIDA.getMensagem());
		}
		
	}

	/**
	 * @return String
	 * 		Cartão de acesso de redefinição de senha
	 */
	public String getCartaoAcessoRedefSenha() {
		return cartaoAcessoRedefSenha;
	}

	/**
	 * @param String
	 * 		Novo cartao de acesso de redefinicao de senha
	 */
	public synchronized void setCartaoAcessoRedefSenha(String cartaoAcessoRedefSenha) throws Exception {
		this.cartaoAcessoRedefSenha = Criptografia.criptografaMD5(getLogin(), cartaoAcessoRedefSenha);;
	}

	@Override
	public String getEmailRedefinicaoSenha() {
		return this.emailRedefSenha;
	}

	@Override
	public String getCaminhaImagemPerfil() {
		return this.caminhoImagemPerfil;
	}

	@Override
	public void setCaminhoImagemPerfil(String caminhoImagemPerfil)
			throws Exception {
		
		Validador.assertStringNaoVazia(caminhoImagemPerfil, Mensagem.CAMINHO_IMG_INVALIDA.getMensagem(), Mensagem.CAMINHO_IMG_INVALIDA.getMensagem());
		this.caminhoImagemPerfil = caminhoImagemPerfil;
		
	}

	
		
}