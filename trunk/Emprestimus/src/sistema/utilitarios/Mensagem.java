package sistema.utilitarios;

/**
 * Enum que encapsula as mensagens usadas no sistema
 * 
 * @author Joeffison Silverio de Andrade, 21011853, joeffisonsa@gmail.com
 * @version 1.0
 * @since 1.0
 */
public enum Mensagem {
	
	/*Mensagens do menu: */
	NOME_PROJETO("Emprestimus"),
	BOAS_VINDAS("Bem vindo(a) ao Emprestimus! "),
	MENU("\n--Menu Principal--\n" +
		 "1 - Logar\n" +
		 "2 - Cadastrar novo usuario\n" +
		 "3 - Sair\n"),
	// Nao modeifique a ordem das opções dos menus, sem alterar a iu.
	MENU_LOGADO("\n--Voce esta logado--\n" +
			"1 - Ver atributo do usuário\n" +
			"2 - Cadastrar Item\n" +
			"3 - Ver atributo de item\n" +
			"4 - Localizar usuário\n" +
			"5 - Solicitar amizade\n" +
			"6 - Visualizar solicitações de amizade\n" +
			"7 - Aprovar amizade\n" +
			"8 - Verificar se tem amizade com outro usuário" +
			"9 - Sair\n"),

	PERGUNTAR_TENTAR_NOVAMENTE("Deseja tentar novamente?\n"+
							   "1 - Sim\n" +
							   "2 - Não"),

	COPYRIGHT("Copyright © 2010 Joeffison S. A. All rights reserved."),
	CONTATOS("Deseja contratar nossos servicos?\n" +
					 "Entao envie um email para: joeffisonsa@gmail.com"),
	CREDITOS("Copyright © 2010 Joeffison S. A. All rights reserved.\n\n" +
					 "" +
					 "Programadores: \n" +
					 "Joeffison Silverio de Andrade;\n" +
					 "José Nathaniel Lacerda de Abrante;\n" +
					 "José Ulisses de Brito Lira Filho;\n" +
					 "Samir Trajano Feitosa\n\n\n" +
					 "" +
					 "" +
					 "Detalhes importantes:\n\n" +
					 "" +
					 "Este e um projeto da disciplina de Sistemas de Informacao I\n" +
					 "Instituicao: UFCG\n" +
					 "Campus: Campina Grande\n" +
					 "Periodo: 2011.2\n" +
					 "Professor: Nazareno Andrade\n"),
	REENCAMINHAMENTO_AO_MENU_PRINCIPAL("O sistema esta sendo reencaminhado ao menu principal."),
	ADEUS("Obrigado por usar os nossos servicos."),
	ENTER("\n"),
	OK("OK"),
	MEUS_AMIGOS("\n--Meus Amigos--"),
					 
			
	
	/*Mensagens de pedido de dados */
	PEDIR_LOGIN("Entre com o seu login: "),
	PEDIR_SENHA("Entre com a sua senha: "),
	PEDIR_OPCAO("Por favor, digite uma opcao: "),
	PERGUNTAR_ATRIBUTO("Qual o atributo que você deseja?"),
	PEDIR_CHAVE_BUSCA("Digite a palavra chave a ser procurada:"),
	PEDIR_LOGIN_AMIGO_ADICIONAR("Digite o login do amigo a ser adicionado."),
	PEDIR_LOGIN_AMIGO_APROVAR("Digite o login do amigo a ser aprovado."),
	PEDIR_LOGIN_AMIGO_VERIFICAR("Digite o login do amigo a ser verificado."),
	INFO_SAO_AMIGOS("Vocês são amigos."),
	INFO_NAO_SAO_AMIGOS("Vocês não são amigos."),
	/*Cadastrar*/
	NOME_PARA_CADASTRAR("Qual o nome do usuario a ser cadastrado?"),
	LOGIN_PARA_CADASTRAR("Qual o login do usuario a ser cadastrado?"),
	ENDERECO_PARA_CADASTRAR("Qual o endereco do usuario a ser cadastrado?"),
	PEDIR_NOME_ITEM_CADASTRAR("Qual o nome do item a ser cadastrado?"),
	PEDIR_CATEGORIA_ITEM_CADASTRAR("Qual a categoria do item a ser cadastrado?"),
	PEDIR_DESCRICAO_ITEM_CADASTRAR("Qual a descrição do item a ser cadastrado?"),
	
	PEDIR_NOME_DO_AMIGO("\nDigite o nome do seu amigo: <Enter para voltar>"),
	MENU_ITEMCATEGORIA("\n--Escolha uma categoria:--\n" +
			"1 - Livro\n" +
			"2 - Filme\n" +
			"3 - Jogo\n"),
	
	/*Avisos*/
	INFO_AGUARDE_APROVACAO_AMIZADE("Usuario adicionado a sua lista de amizades pendentes.\n" +
								   "Este usuário irá para sua lista de amigos, assim que ele aprovar a sua solicitação."),
	OPERACAO_SUCESSO("Operação realizada com sucesso!"),
								   
	/*Mensagens de erro: */
	OPCAO_INVALIDA("Opcao invalida."),
	OUTRA_OPCAO("Escolha uma outra opcao. "),
	CARACTERES_INVALIDOS("Caracteres invalidos. "),
	INICIO_PADRAO_MENSAGEM_ERRO("Este dado"),
	OPERACAO_FALHOU("Ocorreu um problema na execucao desta operacao."),
	
	/*Argumentos invalidos*/
	LOGIN_INVALIDO("Login inválido"),
	LOGIN_INEXISTENTE("Login inexistente"),
	LOGIN_JAH_CADASTRADO("Já existe um usuário com este login"),
	NOME_INVALIDO("Nome inválido"),
	NOME_INEXISTENTE("Nome inexistente"),
	ENDERECO_INVALIDO("Endereco inválido"),
	ENDERECO_INEXISTENTE("Endereco inexistente"),
	CATEGORIA_INEXISTENTE("Categoria inexistente"),
	CATEGORIA_INVALIDA("Categoria inválida"),
	ID_ITEM_INVALIDO("Identificador do item é inválido"),
	ID_ITEM_INEXISTENTE("Item inexistente"),
	ATRIBUTO_INVALIDO("Atributo inválido"),
	ATRIBUTO_INEXISTENTE("Atributo inexistente"),
	PALAVRA_CHAVE_INVALIDA("Palavra-chave inválida"),
	PALAVRA_CHAVE_INEXISTENTE("Nenhum usuário encontrado"),
	NENHUM_USUARIO_EXISTENTE("Nenhum usuário existente"),
	NAO_HA_REQUISICOES("Não há requisições"),
	SESSAO_INVALIDA("Sessão inválida"),
	SESSAO_INEXISTENTE("Sessão inexistente"),
	USUARIO_INEXISTENTE("Usuário inexistente"),
	USUARIO_JAH_SAO_AMIGOS("Os usuários já são amigos"),
	USUARIO_NAO_POSSUI_AMIGOS("O usuário não possui amigos"),
	USUARIO_SEM_ITENS_CADASTRADOS("O usuário não possui itens cadastrados"),
	USUARIO_NAO_TEM_PEMISSAO_VER_ITENS("O usuário não tem permissão para visualizar estes itens"),
	USUARIO_NAO_TEM_PERMISSAO_REQUISITAR_EMPREST_ITEM("O usuário não tem permissão para requisitar o empréstimo deste item"),
	AMIZADE_JAH_SOLICITADA("Requisição já solicitada"),
	REQUISICAO_AMIZADE_INEXISTNTE("Requisição de amizade inexistente"),
	ID_REQUISICAO_EMPRESTIMO_INVALIDO("Identificador da requisição de empréstimo é inválido"),
	EMPRESTIMO_TIPO_INVALIDO("Tipo inválido"),
	EMPRESTIMO_TIPO_INXISTENTE("Tipo inexistente"),
	EMPRESTIMO_DURACAO_INVALIDA("Duracao inválida"),
	TIPO_INEXISTENTE("Tipo inexistente"),
	EMPRESTIMO_SEM_PERMISSAO_APROVAR("O empréstimo só pode ser aprovado pelo dono do item"),
	EMPRESTIMO_JA_APROVADO("Empréstimo já aprovado"),
	EMPRESTIMO_INEXISTENTE("Empréstimo inexistente"),
	EMPRESTIMO_DEVOLUCAO_APENAS_BENEFICIADO("O item só pode ser devolvido pelo usuário beneficiado"),
	EMPRESTIMO_DEVOLUCAO_CONFIRMADA_APENAS_EMPRESTADOR("O término do empréstimo só pode ser confirmado pelo dono do item"),
	REQUISICAO_EMPRESTIMO_JA_SOLICITADA("Requisição já solicitada"),
	NAO_HA_EMPRESTIMOS_DESTE_TIPO("Não há empréstimos deste tipo"),
	ID_REQUISICAO_EMP_INEXISTENTE("Requisição de empréstimo inexistente"),
	ID_EMPRESTIMO_INVALIDO("Identificador do empréstimo é inválido"),
	TIPO_INVALIDO("Tipo inválido"),
	ITEM_JA_DEVOLVIDO("Item já devolvido"), 
	TERMINO_EMPRESTIMO_JA_CONFIRMADO("Término do empréstimo já confirmado"),
	REMETENTE_INVALIDO("Remetente inválido"),
	DESTINATARIO_INVALIDO("Destinatário inválido"), 
	ASSUNTO_INVALIDO("Assunto inválido"),
	MENSAGEM_INVALIDA("Mensagem inválida"), 
	DESTINATARIO_INEXISTENTE("Destinatário inexistente"), 
	ID_MENSAGEM_INVALIDO("Identificador mensagem inválido"),
	TOPICO_ID_INVALIDO("Identificador do tópico é inválido"),
	TOPICO_ID_INEXISTENTE("Tópico inexistente"),
	NAO_HA_TOPICOS_CRIADOS("Não há tópicos criados"),
	DEVOLUCAO_JA_REQUISITADA("Devolução já requisitada"),
	USUARIO_NAO_PARTICIPA_DESTE_EMPRESTIMO("O usuário não participa deste empréstimo"),
	USUARIO_SEM_PERMISSAO_LEITURA_TOPICO("O usuário não tem permissão para ler as mensagens deste tópico");
	
	private final String mensagem;

	/**
	 * Associa os parametros dos enums aos seus respectivos atributos
	 * @param mensagem
	 * 			Mensagem correspondente ao enum
	 */
	private Mensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	
	/**
	 * Recupera a mensagem associada ao enum
	 * 
	 * @return
	 * 		Retorna a mensagem que o enum carrega
	 */
	public String getMensagem() {
		return this.mensagem;
	}
	
	@Override
	/**
	 * Gera a mensagem especifica do enum.
	 * 
	 * @return
	 * 			Retorna a mensagem especifica do enum.
	 */
	public String toString() {
		return this.mensagem;
	}

}