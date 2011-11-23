package sistema.usuario;


import java.util.List;
import maps.Coordenadas;
import sistema.emprestimo.EmprestimoIF;
import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.ItemIF;
import sistema.mensagem.ChatIF;
import sistema.utilitarios.Criptografia;

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
	 * @param String
	 * 		O login do usuario.
	 * 
	 * @throws Exception
	 * 		Caso o parâmetro seja inválido.
	 */
	public void setLogin(String login) throws Exception;

	/**
	 * Configura o nome do usuario.
	 * 
	 * @param String
	 * 		O nome do usuario.
	 * 
	 * @throws ArgumentoInvalidoException
	 * 			Caso o parâmetro seja inválido.
	 * 
	 * @throws Exception
	 * 			Caso o parâmetro seja inválido.
	 */
	public void setNome(String nome) throws ArgumentoInvalidoException, Exception;

	/**
	 * COnfigura o endereco do usuario.
	 * 
	 * @param String
	 * 		Endereço do usuário.
	 */
	public void setEndereco(String endereco);

	/**
	 * Recupera o nome do usuario.
	 * 
	 * @return String
	 * 		O nome do usuario.
	 */
	public String getLogin();

	/**
	 * Recupera o nome do usuario.
	 * 
	 * @return String
	 * 		O nome do usuario.
	 */
	public String getNome();

	/**
	 * Recupera o endereco do usuario.
	 * 
	 * @return String
	 * 		O endereco do usuario.
	 */
	public String getEndereco();
	

	/**
	 * Configura uma senha criptografada para um usuário.
	 * 
	 * @param String
	 * 		nova senha.
	 */
	public void setSenha(String senha) throws Exception;

	/**
	 * Loga usuário.
	 * 
	 * @param String
	 * 		senha
	 * 
	 * @return boolean
	 * 		True - Se o usuário puder se logar.
	 */
	public boolean logar(String senha);
	
	/**
	 * Cadastra um item na lista de itens do usuario. Eh retornado o idItem do
	 * item cadastrado. O idItem tem o seguinte formato:
	 * [loginUsuario]_[inteiro]. Por exemplo, samir_1, para o item primeiro;
	 * samir_2.
	 * 
	 * @param String
	 * 		O nome do item.
	 * @param String
	 * 		A descrição do item.
	 * @param String
	 * 		A categoria do item.
	 * 
	 * @return String
	 * 		O idItem no formato login_inteiro.
	 */
	public String cadastrarItem(String nome, String descricao, String categoria)
			throws Exception;

	/**
	 * Remove item da lista de itens do usuario. A remocao eh feita apenas pelo
	 * idItem.
	 * 
	 * @param String
	 * 		IdItem do item a ser removido.
	 * 
	 * @return boolean
	 * 		True - Se o item for removido.
	 */
	public boolean removerItem(String idItem) throws Exception;

	/**
	 * Recupera a lista ordenada de idItens de itens cadastrados.
	 * 
	 * @return String
	 * 		Uma lista de idItens ordenada. Formatacao: "7 17 18 20 21"
	 *         Retorna-se uma string vazia caso nao haja elementos.
	 */
	public String getListaIdItens() throws ArgumentoInvalidoException;
	
	/**
	 * Recupera a lista de itens do usário.
	 * 
	 * @return String
	 * 		Lista (uma string) representativa dos itens.
	 * 
	 * @throws Exception
	 * 		Devido a problemas internos.
	 */
	public String getListaItens() throws Exception;

	/**
	 * Recupera o ItemIF a partir do idItem.
	 * 
	 * @param String
	 * 		O idItem do item a ser procurado.
	 * 
	 * @return ItemIF
	 * 		A Item encontrado.
	 *      <code>null</code> - Caso o item nao exista.
	 */
	public ItemIF getItem(String idItem) throws ArgumentoInvalidoException;

	/**
	 * Verifica se o usuario possui o item com id especifico cadastrado.
	 * 
	 * @param String
	 * 		O id do item a ser procurado.
	 * 
	 * @return boolean
	 * 		True - Se o item existir.
	 */
	public boolean existeItemID(String idItem) throws ArgumentoInvalidoException;

	/**
	 * Calcula a quantidade total de itens cadastrados.
	 * 
	 * @return int
	 * 		quantidade de itens cadastrados.
	 */
	public int qntItens() throws ArgumentoInvalidoException;

	/**
	 * Calcula a quantidade de itens atualmente concedidos em emprestimo, ou
	 * seja, a quantidade de livros nao disponiveis para emprestivo.
	 * 
	 * @return int
	 * 		quantidade de itens atualmente emprestados.
	 */
	public int qntItensEmprestados() throws ArgumentoInvalidoException;

	/**
	 * Recupera a lista ordenada de idItens de itens emprestados (concedidos a
	 * alguém).
	 * 
	 * @return String
	 * 		Uma lista ordenada de idItens emprestados.
	 */
	public String getListaIdItensEmprestados() throws ArgumentoInvalidoException;

	


	/**
	 * Verifica se o item pessoal estah disponivel a partir de um idItem.
	 * 
	 * @param String
	 * 		O idItem
	 * 
	 * @return boolean
	 * 		True - Se o item nao estiver emprestado.
			False - Se o item nao existir ou se estiver emprestado.
	 */
	public boolean estahItemDisponivel(String idItem);
	
	/**
	 * Adiciona uma requisitação de amizade a lista do usuário.
	 * 
	 * @param UsuarioIF
	 * 		Usuario a ser adicionado.
	 * 
	 * @throws ArgumentoInvalidoException 
	 */
	public void usuarioQuerSerMeuAmigo( UsuarioIF usuario ) throws ArgumentoInvalidoException;
	
	/**
	 * Requisita a amizade do usuário com o login informado.
	 * 
	 * @param String
	 * 		Login a ser adicionado.
	 * 
	 * @throws Exception
	 * 		Parâmetro inválido ou já adicionado.
	 */
	public void requisitarAmizade( String login ) throws Exception;
	
	/**
	 * Verifica se os usuários já são amigos.
	 * 
	 * @param String
	 * 		Login do outro usuário.
	 * 
	 * @return boolean
	 * 		True em caso o usuario passado seja amigo deste. 
	 * 
	 * @throws ArgumentoInvalidoException
	 * 		Parâmetro inválido.
	 */
	public boolean ehAmigo( String amigoProcurado ) throws ArgumentoInvalidoException;
	
	/**
	 * Verifica se a amizade já foi requisitada.
	 * 
	 * @param String
	 * 		Login do outro usuário.
	 * 
	 * @return boolean
	 * 		True em caso a amizade já tenha sido requisitada.
	 * 
	 * @throws ArgumentoInvalidoException
	 * 		Caso o login seja inválido.
	 */
	public boolean amizadeDeFoiRequisitada( String login ) throws ArgumentoInvalidoException;
	
	/**
	 * Recupera a lista de usuários que requisitaram a amizade do usuário.
	 * 
	 * @return List<UsuarioIF>
	 * 		Lista de usuários que requisitaram a amizade do usuário.
	 */
	public List<UsuarioIF> getQueremSerMeusAmigos() throws Exception;
	
	/**
	 * Recupera a lista de usuários dos quais o usuário requisitou a amizade.
	 * 
	 * @return List<UsuarioIF>
	 * 		Lista de usuários dos quais o usuário requisitou a amizade.
	 */
	public List<UsuarioIF> getQueroSerAmigoDe() throws Exception;

	/**
	 * Verifica se dois Usuarios sao iguais. A verificacao eh feita a nivel de
	 * interface. A comparacao eh realizada comparando apenas logins.
	 * 
	 * @param Object
	 * 		Uma implementacao de UsuarioIF sob o aspecto de interface
	 *            UsuarioIF.
	 * 
	 * @return boolean
	 * 		True - Se os objetos forem iguais.
	 */
	public boolean equals(Object outroUsuario);

	/**
	 * Recupera a lista de itens do usuário.
	 * 
	 * @return List<ItemIF>
	 * 		Lista de itens do usuário.
	 */
	public List<ItemIF> getItens();
	
	/**
	 * Recupera a lista de amigos do usuário.
	 * 
	 * @return String
	 * 		Lista de amigos do usuário.
	 * 
	 * @throws Exception
	 * 		Haja problemas internos.
	 */
	public String getAmigos() throws Exception;

	/**
	 * Aprova uma requisitação de amizade.
	 * 
	 * @param String
	 * 		Login do usuário que fez a requisitação de amizade.
	 * 
	 * @throws Exception
	 * 		Em caso deles serem amigos ou a requisitação não existir.
	 */
	public void aprovarAmizade(String login) throws Exception;

	/**
	 * Verifica se a requisitação de amizade foi aprovada.
	 * 
	 * @param UsuarioIF
	 * 		Outro usuário, para fazer a verificação.
	 */
	public void aprovouAmizade(UsuarioIF usuario) throws ArgumentoInvalidoException;
	
	/**
	 * Verifica se o item seja do usuário.
	 * 
	 * @param String
	 * 		ID do item a ser verificado.
	 * 
	 * @return boolean
	 * 		True caso o item seja do usuário.
	 * 
	 * @throws Exception
	 * 		Caso o ID do item não exista.
	 */
    public boolean oItemMePertence( String idItem ) throws Exception;
	
    /**
     * Verifica se o item é de algum dos amigos do usuário.
     * 
     * @param String
     * 		ID do item a ser verificado.
     * 
     * @return UsuarioIF
     * 		True caso o item seja de algum dos amigos do usuário.
     * 
     * @throws Exception
     * 		Caso o ID do item não exista.
     */
	public UsuarioIF ehItemDoMeuAmigo( String idItem ) throws Exception;
	
	/**
	 * Requisita o empréstimo de um determinado item.
	 * 
	 * @param String
	 * 		ID do item a ser requerido.
	 * @param int
	 * 		Duração pretendida para o empréstimo.
	 * 
	 * @return String
	 * 		ID da requisitação.
	 * 
	 * @throws Exception
	 * 		Caso o id seja inexistente ou a duração seja não-positiva.
	 */
	public String requisitarEmprestimo( String idItem, int duracao ) throws Exception;
	
	/**
	 * Recupera a lista de empréstimos do usuário.
	 * 
	 * @param String
	 * 		Tipo dos empréstimos que serão retornados (emprestador e/ou beneficiado)
	 * 
	 * @return String
	 * 		String que representa a lista de empréstimos do usuário.
	 * 
	 * @throws Exception
	 * 		CAso o tipo seja inválido.
	 */
	public String getEmprestimos(String tipo) throws Exception;
	
	/**
	 * Adiciona uma requisitação de empréstimo em espera de um amigo.
	 * 
	 * @param EmprestimoIF
	 * 		Empréstimo requerido.
	 */
	public void adicionarRequisicaoEmprestimoEmEsperaDeAmigo(EmprestimoIF emp) throws Exception;
	
	/**
	 * Aprova um empréstimo.
	 * 
	 * @param String
	 * 		ID da requisitação a ser aprovada.
	 * 
	 * @return String
	 * 		ID do empréstimo criado.
	 * 
	 * @throws Exception
	 * 		Caso o parâmetro não corresponda à uma requisitação. 
	 */
	public String aprovarEmprestimo( String idRequisicaoEmprestimo ) throws Exception;
	
	/**
	 * Verificar se o empréstimo foi aceito pelo amigo.
	 * 
	 * @param EmprestimoIF
	 * 		Empréstimo a ser verificado.
	 * 
	 * @throws Exception
	 * 		Caso o parâmetro seja inválido.
	 */
	public void emprestimoAceitoPorAmigo( EmprestimoIF emp ) throws Exception;
	
	/**
	 * Verfica se o usuário possui algum amigo com o login especificado.
	 * 
	 * @param String
	 * 		Login a ser pesquisado.
	 * 
	 * @return UsuarioIF
	 * 		O usuário do login, caso seja amigo realmente.
	 * 
	 * @throws Exception
	 * 		Caso o login seja inexistente.
	 */
	public UsuarioIF possuoAmigoComEsteLogin(  String login ) throws Exception;
	
	//public UsuarioIF getAmigoPeloLogin( String login ) throws Exception;
	
	/**
	 * Adiciona uma conversa off-topic à lista de conversas do usuário.
	 * 
	 * @param ChatIF
	 * 		Conversa a ser adicionada.
	 */
	public void adicionaConversaOfftopicNaLista( ChatIF conversa ) throws Exception;
	
	/**
	 * Adiciona uma conversa sobre uma negociação à lista de conversas do usuário.
	 * 
	 * @param ChatIF
	 * 		Conversa a ser adicionada.
	 */
	public void adicionaConversaNegociacaoNaLista( ChatIF conversa ) throws Exception;

	/**
	 * Envia uma mensagem off-topic.
	 * 
	 * @param String
	 * 		Destino da mensagem.
	 * @param String
	 * 		Assunto da mensagem.
	 * @param String
	 * 		Mensagem a ser enviada.
	 * 
	 * @return String
	 * 		ID do tópico.
	 * 
	 * @throws Exception
	 * 		Caso os parâmetros sejam inválidos.
	 */
	public String enviarMensagemOffTopic( String destinatario, String assunto, String mensagem ) throws Exception;
	
	/**
	 * Envia mensagem de emprestimo
	 * 
	 * @param String
	 * 		destinatario da mensagem
	 * @param String
	 * 		assunto da mensagem
	 * @param String
	 * 		texto da mensagem
	 * @param String idRequisicaoEmprestimo
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	public String enviarMensagemEmprestimo( String destinatario, String assunto, String mensagem, String idRequisicaoEmprestimo ) throws Exception;
	
	/**
	 * Lê topicos
	 * @param String
	 * 		tipo do topico a ser lido
	 * 
	 * @return String
	 * 		Topicos
	 * 
	 * @throws Exception
	 */
	public String lerTopicos( String tipo ) throws Exception;
	
	/**
	 * Pesquisa um item
	 * 
	 * @param String
	 * 		chave
	 * @param String
	 * 		atributo
	 * @param String
	 * 		tipoOrdenacao
	 * @param String
	 * 		criterioOrdenacao
	 * 
	 * @return String
	 * 		Itens encontrados
	 * 
	 * @throws Exception
	 */
	public String pesquisarItem( String chave, String atributo, 
			String tipoOrdenacao, String criterioOrdenacao ) throws Exception;
	
	/**
	 * Incrementa a reputação do usuario
	 */
	public void incrementaReputacao();
	
	/**
	 * Decrementa a reputação do usuario
	 */
	public void decrementaReputacao();
	
	/**
	 * @return int
	 * 		Reputação do usuario
	 */
	public int getReputacao();
	
	/**
	 * Desfaz uma amizade
	 * 
	 * @param String
	 * 		amigo a ser removido
	 * 
	 * @throws Exception
	 */
	public void desfazerAmizade( String amigo ) throws Exception;
	
	/**
	 * Remove um amigo da lista
	 * 
	 * @param UsuarioIF
	 * 		amigo a ser removido
	 */
	public void removerAmigoDaLista( UsuarioIF amigo );
	
	/**
	 * Remove emprestimos requeridos por um amigo
	 * 
	 * @param UsuarioIF
	 * 		amigo
	 */
	public void removerEmprestimosRequeridosPorAmigo( UsuarioIF amigo );
	
	/**
	 * Remove emprestimos requeridos pelo usuario para um amigo
	 * 
	 * @param UsuarioIF
	 * 		amigo
	 */
	public void removerEmprestimosRequeridosPorMim( UsuarioIF amigo );
	
	/**
	 * Diz se um determinado item está incluido na lista de itens do usuario
	 * 
	 * @param String
	 * 		id do item
	 * 
	 * @return boolean
	 * 		True caso o item esteja incluido na lista de itens do usuario.
	 * 
	 * @throws Exception
	 */
	public boolean esteItemMePertence( String idItem ) throws Exception;

	/**
	 * Diz se um item já foi requisitado pelo usuario
	 * 
	 * @param String
	 * 		id do item
	 * 
	 * @return String
	 * 
	 * @throws Exception
	 */
	public boolean requisiteiEsteItem( String idItem ) throws Exception;
	
	/** 
	 * @return List<UsuarioIF>
	 * 		Lista de amigos do usuario
	 */
	public List<UsuarioIF> getListaAmigos();
	
	/**
	 * Apaga um determinado item da lista do usuario
	 * 
	 * @param String
	 * 		id do item
	 * 
	 * @throws Exception
	 */
	public void apagarItem( String idItem ) throws Exception;
	
	/**
	 * Remove a soliciacao de emprestimo feita pelo usuario
	 * 
	 * @param EmprestimoIF
	 * 		emprestimo a ser removido
	 */
	public void removerMinhaSolicitacaoEmprestimo( EmprestimoIF emprestimo );

	/**
	 * Zera o historico do usuario
	 */
	public void zerarHistorico();
	
	/**
	 * @return String
	 * 		Historico do usuario
	 * 
	 * @throws ArgumentoInvalidoException
	 */
	public String getHistoricoToString() throws ArgumentoInvalidoException;

	/**
	 * Adiciona historico de emprestimo em andamento no usuario
	 * 
	 * @param UsuarioIF
	 * 		amigo
	 * @param ItemIF
	 * 		item
	 * 
	 * @throws Exception
	 */
	public void addHistoricoEmprestimoEmAndamento(UsuarioIF amigo, ItemIF item) throws Exception;
	
	/**
	 * Adiciona historico de amizade aprovada no usuario
	 * 
	 * @param UsuarioIF
	 * 		amigo
	 * 
	 * @throws Exception
	 */
	public void addHistoricoAmizadeAprovada(UsuarioIF amigo) throws Exception;

	/**
	 * Registra interesse por um item
	 * 
	 * @param String
	 * 		Id do item.
	 * 
	 * @throws Exception
	 */
	public void registrarInteressePorItem(String idItem) throws Exception;

	/**
	 * Adiciona historico de termino de emprestimo
	 * 
	 * @param ItemIF
	 * 		item emprestado
	 * 
	 * @throws Exception
	 */
	public void addHistoricoTerminoEmprestimo(ItemIF item) throws Exception;

	/**
	 * Publica pedido de item
	 * 
	 * @param String
	 * 		nome do Item
	 * @param String
	 * 		descricao do Item
	 * 
	 * @return String
	 * 		ID da publicacao
	 * 
	 * @throws Exception
	 */
	public String publicarPedido(String nomeItem, String descricaoItem) throws Exception;

	/**
	 * Oferece Item
	 * 
	 * @param String
	 * 		id da Publicacao de Pedido
	 * @param String
	 * 		id do Item
	 * 
	 * @throws Exception
	 */
	public void oferecerItem(String idPublicacaoPedido, String idItem) throws Exception;

	/**
	 * Republica pedido de Item
	 * 
	 * @param String
	 * 		id da Publicacao de Pedido
	 * 
	 * @throws Exception
	 */
	public void republicarPedido(String idPublicacaoPedido) throws Exception;

	/**
	 * Envia uma Mensagem de Oferecimento de Item offtopic
	 * 
	 * @param String
	 * 		destinatario
	 * @param String
	 * 		assunto
	 * @param String
	 * 		mensagem
	 * 
	 * @return String
	 * 		ID da mensagem
	 * 
	 * @throws Exception
	 */
	public String enviarMensagemOferecimentoItemOffTopic(String destinatario,
			String assunto, String mensagem) throws Exception;

	/**
	 * Cadastra e-mail de redefinicao de senha
	 * 
	 * @param String
	 * 		email
	 * 
	 * @throws Exception
	 */
	public void cadastrarEmailRedefinicaoSenha(String email) throws Exception;

	/**
	 * Altera a senha do usuario
	 * 
	 * @param String
	 * 		senha Atual
	 * @param String
	 * 		senha Nova
	 * 
	 * @throws Exception
	 */
	public void alterarSenha(String senhaAtual, String senhaNova) throws Exception;

	/**
	 * @return String
	 * 		e-mail de redefinicao de senha.
	 */
	public String getEmailRedefinicaoSenha();

	/**
	 * Modifica cartao de acesso de redefinicao de senha
	 * 
	 * @param String senhaAleatoria
	 * 
	 * @throws Exception
	 */
	public void setCartaoAcessoRedefSenha(String senhaAleatoria) throws Exception;
	
	/**
	 * @return String
	 * 		Cartao de acesso a redefinição de senha
	 * 
	 * @throws Exception
	 */
	public String getCartaoAcessoRedefSenha() throws Exception;

	
}
