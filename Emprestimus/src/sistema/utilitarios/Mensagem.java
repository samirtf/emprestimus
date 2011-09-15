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
		 "5 - Sair\n"),
	MENU_LOGADO("\n--Voce esta logado--\n" +
			"1 - Cadastrar item\n" +
			"2 - Ver meus itens\n" +
			"3 - Ver meus amigos\n" +
			"4 - Procurar amigos\n" +
			"5 - Ver solicitacoes de amizade\n" +
			"6 - Ver perfil proprio\n" +
			"7 - Sair\n"),
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
	NOME_PARA_CADASTRAR("Qual o nome do usuario a ser cadastrado?"),
	LOGIN_PARA_CADASTRAR("Qual o login do usuario a ser cadastrado?"),
	ENDERECO_PARA_CADASTRAR("Qual o endereco do usuario a ser cadastrado?"),
	DESCRICAO_PARA_CADASTRAR("Qual o endereco do usuario a ser cadastrado?"),
	PEDIR_NOME_DO_AMIGO("\nDigite o nome do seu amigo: <Enter para voltar>"),
	MENU_ITEMCATEGORIA("\n--Escolha uma categoria:--\n" +
			"1 - Livro\n" +
			"2 - Filme\n" +
			"3 - Jogo\n"),
	
	
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
	SESSAO_INVALIDA("Sessão inválida"),
	SESSAO_INEXISTENTE("Sessão inexistente"),
	USUARIO_INEXISTENTE("Usuário inexistente");
	
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