package sistema.usuario;

import java.util.List;
import maps.Coordenadas;
import sistema.emprestimo.EmprestimoIF;
import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.ItemIF;
import sistema.mensagem.ChatIF;

/**
 * Emprestimus Projeto de Sistemas de Informação I Universidade Federal de
 * Campina Grande
 * 
 * Representa um usuario do sistema. Esta interface assegura que os metodos
 * exigidos serao implementados. A instanciacao de classes que implementam esta
 * interface deve ser feita com upcast para a interface.
 * 
 */
public interface UsuarioIF extends Comparable<UsuarioIF>, Coordenadas {

	/**
	 * Configura o login do usuario.
	 * 
	 * @param login
	 *            O login do usuario.
	 * @throws Exception
	 *             Caso o parâmetro seja inválido.
	 */
	public void setLogin(String login) throws Exception;

	/**
	 * Configura o nome do usuario.
	 * 
	 * @param nome
	 *            O nome do usuario.
	 * @throws ArgumentoInvalidoException
	 *             Caso o parâmetro seja inválido.
	 * @throws Exception
	 *             Caso o parâmetro seja inválido.
	 */
	public void setNome(String nome) throws ArgumentoInvalidoException, Exception;

	/**
	 * COnfigura o endereco do usuario.
	 * 
	 * @param endereco
	 *            Endereço do usuário.
	 */
	public void setEndereco(String endereco);

	/**
	 * Recupera o nome do usuario.
	 * 
	 * @return O nome do usuario.
	 */
	public String getLogin();

	/**
	 * Recupera o nome do usuario.
	 * 
	 * @return O nome do usuario.
	 */
	public String getNome();

	/**
	 * Recupera o endereco do usuario.
	 * 
	 * @return O endereco do usuario.
	 */
	public String getEndereco();

	/**
	 * Cadastra um item na lista de itens do usuario. Eh retornado o idItem do
	 * item cadastrado. O idItem tem o seguinte formato:
	 * [loginUsuario]_[inteiro]. Por exemplo, samir_1, para o item primeiro;
	 * samir_2.
	 * 
	 * @param nome
	 *            O nome do item.
	 * @param descricao
	 *            A descrição do item.
	 * @param categoria
	 *            A categoria do item.
	 * @return O idItem (String) no formato login_inteiro.
	 */
	public String cadastrarItem(String nome, String descricao, String categoria) throws Exception;

	/**
	 * Remove item da lista de itens do usuario. A remocao eh feita apenas pelo
	 * idItem.
	 * 
	 * @param idItem
	 *            IdItem do item a ser removido.
	 * @return True - Se o item for removido. False - Se o item a ser removido
	 *         nao existir.
	 */
	public boolean removerItem(String idItem) throws Exception;

	/**
	 * Recupera a lista ordenada de idItens de itens cadastrados.
	 * 
	 * @return Uma lista de idItens ordenada. Formatacao: "7 17 18 20 21"
	 *         Retorna-se uma string vazia caso nao haja elementos.
	 */
	public String getListaIdItens() throws ArgumentoInvalidoException;

	/**
	 * Recupera a lista de itens do usário.
	 * 
	 * @return Lista (uma string) representativa dos itens
	 * @throws Exception
	 *             Devido a problemas internos.
	 */
	public String getListaItens() throws Exception;

	/**
	 * Recupera o ItemIF a partir do idItem.
	 * 
	 * @param idItem
	 *            O idItem do item a ser procurado.
	 * @return A instancia de ItemIF. <code>null</code> - Caso o item nao
	 *         exista.
	 */
	public ItemIF getItem(String idItem) throws ArgumentoInvalidoException;

	/**
	 * Verifica se o usuario possui o item com id especifico cadastrado.
	 * 
	 * @param idItem
	 *            O id do item a ser procurado.
	 * @return True - Se o item existir. False - Caso nao exista.
	 */
	public boolean existeItemID(String idItem) throws ArgumentoInvalidoException;

	/**
	 * Calcula a quantidade total de itens cadastrados.
	 * 
	 * @return int: quantidade de itens cadastrados.
	 */
	public int qntItens() throws ArgumentoInvalidoException;

	/**
	 * Calcula a quantidade de itens atualmente concedidos em emprestimo, ou
	 * seja, a quantidade de livros nao disponiveis para emprestivo.
	 * 
	 * @return int: quantidade de itens atualmente emprestados.
	 */
	public int qntItensEmprestados() throws ArgumentoInvalidoException;

	/**
	 * Recupera a lista ordenada de idItens de itens emprestados (concedidos a
	 * alguém).
	 * 
	 * @return Uma lista ordenada de idItens emprestados.
	 */
	public String getListaIdItensEmprestados() throws ArgumentoInvalidoException;

	/**
	 * Verifica se o item pessoal estah disponivel a partir de um idItem.
	 * 
	 * @param idItem
	 *            O idItem
	 * @return True - Se o item nao estiver emprestado. False - Se o item nao
	 *         existir ou se estiver emprestado.
	 */
	public boolean estahItemDisponivel(String idItem);

	/**
	 * Adiciona uma requisitação de amizade a lista do usuário.
	 * 
	 * @param usuario
	 *            Usuario a ser adicionado.
	 * @throws ArgumentoInvalidoException
	 */
	public void usuarioQuerSerMeuAmigo(UsuarioIF usuario) throws ArgumentoInvalidoException;

	/**
	 * Requisita a amizade do usuário com o login informado.
	 * 
	 * @param login
	 *            Login a ser adicionado.
	 * @throws Exception
	 *             Parâmetro inválido ou já adicionado.
	 */
	public void requisitarAmizade(String login) throws Exception;

	/**
	 * Verifica se os usuários já são amigos.
	 * 
	 * @param amigoProcurado
	 *            Login do outro usuário.
	 * @return Retorna true em caso afirmativo e false no inverso.
	 * @throws ArgumentoInvalidoException
	 *             Parâmetro inválido.
	 */
	public boolean ehAmigo(String amigoProcurado) throws ArgumentoInvalidoException;

	/**
	 * Verifica se a amizade já foi requisitada.
	 * 
	 * @param login
	 *            Login do outro usuário.
	 * @return Retorna true em caso afirmativo e false no inverso.
	 * @throws ArgumentoInvalidoException
	 *             Caso o login seja inválido.
	 */
	public boolean amizadeDeFoiRequisitada(String login) throws ArgumentoInvalidoException;

	/**
	 * Recupera a lista de usuários que requisitaram a amizade do usuário.
	 * 
	 * @return Lista de usuários que requisitaram a amizade do usuário.
	 */
	public List<UsuarioIF> getQueremSerMeusAmigos() throws Exception;

	/**
	 * Recupera a lista de usuários dos quais o usuário requisitou a amizade.
	 * 
	 * @return Lista de usuários dos quais o usuário requisitou a amizade.
	 */
	public List<UsuarioIF> getQueroSerAmigoDe() throws Exception;

	/**
	 * Verifica se dois Usuarios sao iguais. A verificacao eh feita a nivel de
	 * interface. A comparacao eh realizada comparando apenas logins.
	 * 
	 * @param usuarioIF
	 *            Uma implementacao de UsuarioIF sob o aspecto de interface
	 *            UsuarioIF.
	 * 
	 * @return True - Se os objetos forem iguais. False - Se forem objetos de
	 *         instancias diferentes ou nao forem iguais.
	 */
	@Override
	public boolean equals(Object outroUsuario);

	/**
	 * Recupera a lista de itens do usuário.
	 * 
	 * @return Lista de itens do usuário.
	 */
	public List<ItemIF> getItens();

	/**
	 * Recupera a lista de amigos do usuário.
	 * 
	 * @return Lista de amigos do usuário.
	 * @throws Exception
	 *             Haja problemas internos.
	 */
	public String getAmigos() throws Exception;

	/**
	 * Aprova uma requisitação de amizade.
	 * 
	 * @param login
	 *            Login do usuário que fez a requisitação de amizade.
	 * @throws Exception
	 *             Em caso deles serem amigos ou a requisitação não existir.
	 */
	public void aprovarAmizade(String login) throws Exception;

	/**
	 * Verifica se a requisitação de amizade foi aprovada.
	 * 
	 * @param usuario
	 *            Outro usuário, para fazer a verificação.
	 */
	public void aprovouAmizade(UsuarioIF usuario) throws ArgumentoInvalidoException;

	/**
	 * Verifica se o item seja do usuário.
	 * 
	 * @param idItem
	 *            ID do item a ser verificado.
	 * @return Caso o item seja do usuário, true, senão, false.
	 * @throws Exception
	 *             Caso o ID do item não exista.
	 */
	public boolean oItemMePertence(String idItem) throws Exception;

	/**
	 * Verifica se o item é de algum dos amigos do usuário.
	 * 
	 * @param idItem
	 *            ID do item a ser verificado.
	 * @return Caso o item seja de algum dos amigos do usuário, true, senão,
	 *         false.
	 * @throws Exception
	 *             Caso o ID do item não exista.
	 */
	public UsuarioIF ehItemDoMeuAmigo(String idItem) throws Exception;

	/**
	 * Requisita o empréstimo de um determinado item.
	 * 
	 * @param idItem
	 *            ID do item a ser requerido.
	 * @param duracao
	 *            Duração pretendida para o empréstimo.
	 * @return ID da requisitação.
	 * @throws Exception
	 *             Caso o id seja inexistente ou a duração seja não-positiva.
	 */
	public String requisitarEmprestimo(String idItem, int duracao) throws Exception;

	/**
	 * Recupera a lista de empréstimos do usuário.
	 * 
	 * @param tipo
	 *            Tipo dos empréstimos que serão retornados (emprestador e/ou
	 *            beneficiado)
	 * @return String que representa a lista de empréstimos do usuário.
	 * @throws Exception
	 *             CAso o tipo seja inválido.
	 */
	public String getEmprestimos(String tipo) throws Exception;

	/**
	 * Adiciona uma requisitação de empréstimo em espera de um amigo.
	 * 
	 * @param emp
	 *            Empréstimo requerido.
	 */
	public void adicionarRequisicaoEmprestimoEmEsperaDeAmigo(EmprestimoIF emp) throws Exception;

	/**
	 * Aprova um empréstimo.
	 * 
	 * @param idRequisicaoEmprestimo
	 *            ID da requisitação a ser aprovada.
	 * @return ID do empréstimo criado.
	 * @throws Exception
	 *             Caso o parâmetro não corresponda à uma requisitação.
	 */
	public String aprovarEmprestimo(String idRequisicaoEmprestimo) throws Exception;

	/**
	 * Verificar se o empréstimo foi aceito pelo amigo.
	 * 
	 * @param emp
	 *            Empréstimo a ser verificado.
	 * @throws Exception
	 *             Caso o parâmetro seja inválido.
	 */
	public void emprestimoAceitoPorAmigo(EmprestimoIF emp) throws Exception;

	/**
	 * Verfica se o usuário possui algum amigo com o login especificado.
	 * 
	 * @param login
	 *            Login a ser pesquisado.
	 * @return O usuário do login, caso seja amigo realmente.
	 * @throws Exception
	 *             Caso o login seja inexistente.
	 */
	public UsuarioIF possuoAmigoComEsteLogin(String login) throws Exception;

	// public UsuarioIF getAmigoPeloLogin( String login ) throws Exception;

	/**
	 * Adiciona uma conversa off-topic à lista de conversas do usuário.
	 * 
	 * @param conversa
	 *            Conversa a ser adicionada.
	 */
	public void adicionaConversaOfftopicNaLista(ChatIF conversa) throws Exception;

	/**
	 * Adiciona uma conversa sobre uma negociação à lista de conversas do
	 * usuário.
	 * 
	 * @param conversa
	 *            Conversa a ser adicionada.
	 */
	public void adicionaConversaNegociacaoNaLista(ChatIF conversa) throws Exception;

	/**
	 * Envia uma mensagem off-topic.
	 * 
	 * @param destinatario
	 *            Destino da mensagem.
	 * @param assunto
	 *            Assunto da mensagem.
	 * @param mensagem
	 *            Mensagem a ser enviada.
	 * @return ID do tópico.
	 * @throws Exception
	 *             Caso os parâmetros sejam inválidos.
	 */
	public String enviarMensagemOffTopic(String destinatario, String assunto,
			String mensagem) throws Exception;

	/**
	 * 
	 * @param destinatario
	 * @param assunto
	 * @param mensagem
	 * @param idRequisicaoEmprestimo
	 * @return
	 * @throws Exception
	 */
	public String enviarMensagemEmprestimo(String destinatario, String assunto,
			String mensagem, String idRequisicaoEmprestimo) throws Exception;

	public String lerTopicos(String tipo) throws Exception;

	public String pesquisarItem(String chave, String atributo, String tipoOrdenacao,
			String criterioOrdenacao) throws Exception;

	public void incrementaReputacao();

	public void decrementaReputacao();

	public int getReputacao();

	public void desfazerAmizade(String amigo) throws Exception;

	public void removerAmigoDaLista(UsuarioIF amigo);

	public void removerEmprestimosRequeridosPorAmigo(UsuarioIF amigo);

	public void removerEmprestimosRequeridosPorMim(UsuarioIF amigo);

	public boolean esteItemMePertence(String idItem) throws Exception;

	public boolean requisiteiEsteItem(String idItem) throws Exception;

	public List<UsuarioIF> getListaAmigos();

	public void apagarItem(String idItem) throws Exception;

	public void removerMinhaSolicitacaoEmprestimo(EmprestimoIF emprestimo);

	public void zerarHistorico();

	public String getHistoricoToString() throws ArgumentoInvalidoException;

	public void addHistoricoEmprestimoEmAndamento(UsuarioIF amigo, ItemIF item) throws Exception;

	public void addHistoricoAmizadeAprovada(UsuarioIF amigo) throws Exception;

	public void registrarInteressePorItem(String idItem) throws Exception;

	public void addHistoricoTerminoEmprestimo(ItemIF item) throws Exception;

	public String publicarPedido(String nomeItem, String descricaoItem) throws Exception;

	public void oferecerItem(String idPublicacaoPedido, String idItem) throws Exception;

	public void republicarPedido(String idPublicacaoPedido) throws Exception;

	String enviarMensagemOferecimentoItemOffTopic(String destinatario, String assunto,
			String mensagem) throws Exception;

}
