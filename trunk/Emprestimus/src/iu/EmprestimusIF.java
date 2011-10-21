package iu;

import java.util.List;

import sistema.usuario.UsuarioIF;

/**
 * Fachada para as funcionalidades do sistema.
 * 
 * 
 * @author Joeffison Silverio de Andrade, 21011853, joeffisonsa@gmail.com
 * @version 1.5
 * @since 1.0
 */
public interface EmprestimusIF {

	// Creio que este sistema seria melhor aplicado à aparelhos móveis.	(Joeffison)
	
	//US01
	/**
	 * Metodo para a criação de novos usuários do sistema.
	 * 
	 * @param login
	 * 		Login do usuário (identificador único)
	 * @param nome
	 * 		Nome do usuário para cadastrá-lo
	 * @param endereco
	 * 		Endereço do usuário.
	 * @throws
	 * 		Caso o login já exista ou algum parâmetro seja inválido.
	 */
	public void criarUsuario( String login, String nome, String endereco ) throws Exception;
	
	/**
	 * Metodo utilizado para logar um usuário e, consequentemente, abrir uma nova sessão para este.
	 * 
	 * @param login
	 * 		Login do usuário a ser logado.
	 * @return
	 * 		ID da sessão aberta (que será anulado ao deslogar).
	 * @throws Exception
	 * 		Caso o usuário já esteja logado ou o login seja inválido/inexistente.
	 */
	public String abrirSessao( String login ) throws Exception;
	
	/**
	 * Resgata um atributo específico de algum usuário.
	 * 
	 * @param login
	 * 		Login do usuário do qual se deseja visualizar o atributo.
	 * @param atributo
	 * 		Nome do atributo desejado.
	 * @return
	 * 		Valor do atributo requisitado.
	 * @throws Exception
	 * 		Caso o login e/ou atributo seja(m) inválido(s)/inexistente(s).
	 */
	public String getAtributoUsuario(String login, String atributo ) throws Exception;
	
	//US02
	/**
	 * Cadastra um novo item no sistema.
	 * 
	 * @param idSessao
	 * 		ID da sessão do usuário que deseja adicionar o item.
	 * @param nome
	 * 		Nome do item as ser cadastrado.
	 * @param descricao
	 * 		Descricao do item as ser cadastrado.
	 * @param categoria
	 * 		Categoria do item as ser cadastrado.
	 * @return
	 * 		ID do item cadastrado.
	 * @throws Exception
	 * 		Caso algum dos parâmetros seja inválido/inexistente.
	 */
	public String cadastrarItem( String idSessao, String nome, String descricao, String categoria ) throws Exception;
	
	/**
	 * Recupera o valor de algfum atributo de um item cadastrado.
	 * 
	 * @param idItem
	 * 		ID do item do qual se deseja recuperar a informação.
	 * @param atributo
	 * 		Atributo do qual se deseja ter a informação.
	 * @return
	 * 		Valor do atributo desejado.
	 * @throws Exception
	 * 		Caso ao menos um dos parâmetros seja inválido/inexistente.
	 */
	public String getAtributoItem( String idItem, String atributo ) throws Exception;
	
	/**
	 * Localiza usuários a partir de uma palavra-chave.
	 * 
	 * @param idSessao
	 * 		ID da sessão do usuário que deseja fazer a pesquisa.	
	 * @param chave
	 * 		Palavra-chave para a pesquisa.
	 * @param atributo
	 * 		Atributo ao qual a palavra-chave corresponde. 
	 * @return
	 * 		Uma {@link String} representando os usuários (nome e endereço) que atendem à pesquisa.
	 * @throws Exception
	 * 		Caso algum dos parâmetros seja inválido/inexistente.
	 */
	public String localizarUsuario( String idSessao, String chave, String atributo ) throws Exception;
	
	//US04
	/**
	 * Método para requisitar a amizade de outro usuário.
	 * 
	 * @param idSessao
	 * 		ID da sessão do usuário que deseja fazer a requisitação.
	 * @param login
	 * 		Login do usuário do qual se deseja ser amigo.
	 * @throws Exception
	 * 		Caso algum dos parâmetros seja inválido/inexistente.
	 */
	public void requisitarAmizade( String idSessao, String login ) throws Exception;
	
	/**
	 * Recupera as requisitações de amizade do usuário.
	 * 
	 * @param idSessao
	 * 		ID da sessão do usuário que deseja visualizar as requisitações de sua amizade.
	 * @return
	 * 		O login de todos os usuários que requisitaram amizade ao usuário que fez a verificação.
	 * @throws Exception
	 * 		Caso o ID da sessão seja inválido/inexistente.
	 */
	public String getRequisicoesDeAmizade( String idSessao ) throws Exception;
	
	/**
	 * Aprova uma requisitação de amizade.
	 * 
	 * @param idSessao
	 * 		ID da sessão do usuário que recebeu a requisitação de amizade.
	 * @param login
	 * 		Login do usuário que realizou a requisitação de amizade.
	 * @throws Exception
	 * 		Caso algum dos parâmetros seja inválido/inesxistente ou não exista a requisitação.
	 */
	public void aprovarAmizade( String idSessao, String login ) throws Exception;
	
	/**
	 * Verifica se dois usuários são ou não amigos.
	 * 
	 * @param idSessao
	 * 		ID da sessão do usuário que deseja fazer a verificação.
	 * @param login
	 * 		Login do usuário a ser verificado na lista de amigos do usuário que fez a verificação.
	 * @return
	 * 		Retorna "true", caso sejam amigos, ou "false", caso não.
	 * @throws Exception
	 * 		Caso algum dos parâmetros seja inválido/inesxistente.
	 */
	public String ehAmigo (String idSessao, String login ) throws Exception;
	
	//US05
	/**
	 * Recupera a lista de amigos do usuário.
	 * 
	 * @param idSessao
	 * 		ID da sessão do usuário que deseja ver sua lista de amigos.
	 * @return
	 * 		Logins dos amigos do usuário requisitante.
	 * @throws Exception
	 * 		Caso o ID da sessão seja inválido/inexistente.
	 */
	public String getAmigos( String idSessao ) throws Exception;
	
	/**
	 * Recupera a lista de amigos de outro usuário.
	 * 
	 * @param idSessao
	 * 		Id da sessão do usuário que deseja recuperar a lista de amigos do outro usuário. 
	 * @param login
	 * 		Login do usuário que terá sua lista de amigos verificada.
	 * @return
	 * 		Logins dos amigos do usuário com o login informado.
	 * @throws Exception
	 * 		Caso algum dos parâmetros seja inválido/inesxistente.
	 */
	public String getAmigos( String idSessao, String login ) throws Exception;
	
	/**
	 * Recupera a lista de itens do usuário.
	 * 
	 * @param idSessao
	 * 		ID da sessão do usuário que deseja fazer a verificação.
	 * @return
	 * 		Nome dos itens do usuário.
	 * @throws Exception
	 * 		Caso o ID da sessão inválido/inesxistente.
	 */
	public String getItens( String idSessao ) throws Exception;
	
	/**
	 * Recupera a lista de itens de outro usuário.
	 * 
	 * @param idSessao
	 * 		ID da sessão do usuário que deseja fazer a verificação.
	 * @param login
	 * 		Login do usuário que terá sua lista de itens verificada.
	 * @return
	 * 		Nome dos itens do usuário com o login informado.
	 * @throws Exception
	 * 		Caso algum dos parâmetros seja inválido/inesxistente.
	 */
	public String getItens( String idSessao, String login ) throws Exception;
	
	//US06
	/**
	 * Requisita o empréstimo de um item especifíco. 
	 * 
	 * @param idSessao
	 * 		ID da sessão do usuário que deseja fazer a requisitação.
	 * @param idItem
	 * 		ID od item requerido.
	 * @param duracao
	 * 		Duração pretendida para o empréstimo.
	 * @return
	 * 		ID da requisitação do empréstimo.
	 * @throws Exception
	 * 		Caso algum dos parâmetros seja inválido/inesxistente.
	 */
	public String requisitarEmprestimo( String idSessao, String idItem, int duracao ) throws Exception;
	
	/**
	 * Aprova alguma requisitação de empréstimo que o usuário recebeu.
	 * 
	 * @param idSessao
	 * 		ID da sessão do usuário que deseja aprovar a requisitação.
	 * @param idRequisicaoEmprestimo
	 * 		ID da requisicao de emprestimo que deve ser aprovada.
	 * @return
	 * 		ID do empréstimo aprovado.
	 * @throws Exception
	 * 		Caso algum dos parâmetros seja inválido/inesxistente.
	 */
	public String aprovarEmprestimo( String idSessao, String idRequisicaoEmprestimo ) throws Exception;
	
	/**
	 * Verifica os empréstimos de um dado tipo (emprestador, beneficiado ou ambos) do usuário.
	 * 
	 * @param idSessao
	 * 		ID da sessão do usuário que deseja fazer a verificação.
	 * @param tipo
	 * 		Tipo de empréstimo que deve ser retornado.
	 * @return
	 * 		Lista com a representação de todos os empréstimos.
	 * @throws Exception
	 * 		Caso algum dos parâmetros seja inválido/inesxistente.
	 */
	public String getEmprestimos( String idSessao, String tipo ) throws Exception;
	
	//US07
	/**
	 * Devolve um item ao dono deste.
	 * 
	 * @param idSessao
	 * 		ID da sessão do usuário que deseja fazer a devolução.
	 * @param idEmprestimo
	 * 		ID do empréstimo do item.
	 * @throws Exception
	 * 		Caso algum dos parâmetros seja inválido/inesxistente.
	 */
    public void devolverItem( String idSessao, String idEmprestimo ) throws Exception;
    
    /**
     * Comfirma a devolução de um empréstimo.
     * 
     * @param idSessao
     * 		ID da sessão do usuário que deseja fazer a confirmação.
     * @param idEmprestimo
     * 		ID do empréstimo a ser concluído.
     * @throws Exception
     * 		Caso algum dos parâmetros seja inválido/inesxistente.
     */
    public void confirmarTerminoEmprestimo( String idSessao, String idEmprestimo ) throws Exception;
    
    //US08
    //mensagm off-topic
    /**
     * Envia uma mensagem off-topic para um outro usuário.
     * 
     * @param idSessao
     * 		ID da sessão do usuário que deseja enviar a mensagem.
     * @param destinatario
     * 		Destinatário da mensagem.
     * @param assunto
     * 		Assunto da mensagem.
     * @param mensagem
     * 		Mensagem a ser enviada.
     * @return
     * 		ID do tópico da mensagem.
     * @throws Exception
     * 		Caso algum dos parâmetros seja inválido ou o id da sessão seja inesxistente.
     */
    public String enviarMensagem( String idSessao, String destinatario, String assunto, String mensagem ) throws Exception;
    
    //relativa a negociacao
    /**
     * Envia uma mensagem relativa à um empréstimo para um outro usuário.
     * 
     * @param idSessao
     * 		ID da sessão do usuário que deseja enviar a mensagem.
     * @param destinatario
     * 		Destinatário da mensagem.
     * @param assunto
     * 		Assunto da mensagem.
     * @param mensagem
     * 		Mensagem a ser enviada.
     * @param idRequisicaoEmprestimo
     * 		ID da requisitação do empréstimo do qual se trata a mensagem.
     * @return
     * 		ID do tópico da mensagem.
     * @throws Exception
     * 		Caso algum dos parâmetros seja inválido ou algum dos id's seja inesxistente.
     */
    public String enviarMensagem( String idSessao, String destinatario, String assunto, String mensagem, String idRequisicaoEmprestimo ) throws Exception;
    
    /**
     * Recupera a lista de tópicos do usuário, informando o tipo dos tópicos que devem ser recuperados (negociação, off-topic ou todos). 
     * 
     * @param idSessao
     * 		ID da sessão do usuário que deseja fazer a verificação.
     * @param tipo
     * 		Tipo de tópico desejado (negociação, off-topic ou todos).
     * @return
     * 		Lista de tópicos requirida do usuário, ordenada pela data de criação, do tópico mais recente ao mais antigo.
     * @throws Exception
     * 		Caso algum dos parâmetros seja inválido/inesxistente.
     */
    public String lerTopicos( String idSessao, String tipo ) throws Exception;
    
    /**
     * Recupera a lista de mensagens relativa a um tópico. 
     * 
     * @param idSessao
     * 		ID da sessão do usuário que deseja fazer a verificação.
     * @param idTopico
     * 		ID do tópico desejado.
     * @return
     * 		Lista de mensagens relativa a um tópico, ordenada pela data de criação, da mensagem mais antiga à mais recente.
     * @throws Exception
     * 		Caso algum dos parâmetros seja inválido/inesxistente.
     */
    public String lerMensagens( String idSessao, String idTopico ) throws Exception;
    
    //US09
    /**
     * Requisita a devolução de um item do usuário.
     * 
     * @param idSessao
     * 		ID da sessão do usuário que deseja fazer a requisitação.
     * @param idEmprestimo
     * 		ID do empréstimo referente ao item.
     * @throws Exception
     * 		Caso algum dos parâmetros seja inválido/inesxistente.
     */
    public void requisitarDevolucao(String idSessao, String idEmprestimo) throws Exception;
    
    /**
     * Incrementa o contador de dias.
     * 
     * @param dias
     * 		Quantidade de dias a ser adicionada.
     * 
     * @throws Exception
     * 		Caso o parâmetro seja inválido.
     */
    public void adicionarDias(String dias) throws Exception ;
	
    //US10
    /**
     * Registra o interesse de um usuário em um determinado item.
     * 
     * @param idSessao
     * 		ID da sessão do usuário que deseja registrar interesse no item.
     * @param idItem
     * 		ID do item ao qual o usuário se interessa.
     * @throws Exception
     * 		Caso algum dos parâmetros seja inválido/inesxistente.
     */
    public void registraInteresse(String idSessao, String idItem) throws Exception;
    
    //US11
    /**
     * Pesquisa um item no sistema a partir da palavra chave.
     * 
     * @param idSessao
     * 		ID da sessão do usuário que deseja fazer a pesquisa.
     * @param chave
     * 		Palavra-chave a ser pesquisada.
     * @param atributo
     * 		Atributo ao qual se refere a palavra-chave.
     * @param tipoOrdenacao
     * 		O tipo desejado para a ordenação
     * @param criterioOrdenacao
     * 		O critério desejado para a ordenação
     * @return
     * 		O resultado da pesquisa.
     * @throws Exception
     * 		Caso algum dos parâmetros seja inválido/inesxistente.
     */
    public String pesquisarItem( String idSessao, String chave, String atributo, 
			String tipoOrdenacao, String criterioOrdenacao ) throws Exception;
    
    //US12
    /**
     * Desfaz amizades.
     * 
     * @param idSessao
     * 		ID da sessão do usuário que deseja fazer a pesquisa.
     * @param amigo
     * 		Amigo a ser deletado da lista de amigos.
     * @throws Exception
     * 		Caso algum dos parâmetros seja inválido/inesxistente.
     */
    public void desfazerAmizade(String idSessao, String amigo) throws Exception;
    
    //US14
  	public String getRanking( String idSessao, String categoria ) throws Exception;
        
  	//US15
  	/**
  	 * Permite que o usuário veja as atividades realizadas
  	 * em ordem de ocorrência (da atividade mais recente à mais antiga).
  	 * 
  	 * @param idSessao
  	 * @return
  	 * @throws Exception
  	 */
  	public String historicoAtividades(String idSessao) throws Exception;
  	
  	/**
  	 * Permite que o usuário visualize suas atividades
  	 * em conjunto com as atividades passadas dos seus amigos
  	 * (da atividade mais recente à mais antiga).
  	 * As atividades consideradas do método acima.
  	 * Note que não existe a duplicação da atividade “adição de amigo concluída”,
  	 * portanto mostra apenas a atividade do usuario que requisitou o histórico de atividades em conjunto.
  	 * 
  	 * @param idSessao
  	 * @return
  	 * @throws Exception
  	 */
  	public String historicoAtividadesConjunto(String idSessao) throws Exception;
  	
  	//US17
  	public String publicarPedido(String idSessao, String nomeItem, String descricaoItem) throws Exception;
  	
  	//US18
  	public void oferecerItem(String idSessao, String idPublicacaoPedido, String idItem) throws Exception;
  	
	//Utils
    /**
     * Zera as infoprmações do sistema.
     * 
     */
	public void zerarSistema();
	
	/**
	 * Encerra o sistema.
	 * 
	 */
	public void encerrarSistema();

	

	public void apagarItem(String idSessao, String idItem) throws Exception;
	

}
