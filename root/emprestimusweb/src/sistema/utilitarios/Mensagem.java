package sistema.utilitarios;

/**
 * Emprestimus Projeto de Sistemas de Informação I Universidade Federal de
 * Campina Grande
 * 
 * Enum que encapsula as mensagens usadas no sistema.
 * 
 * 
 * @author Joeffison Silverio de Andrade, 21011853, joeffisonsa@gmail.com
 * @version 1.0
 */
public enum Mensagem {

	/* Especiais */
	NOME_PROJETO("Emprestimus"),
	BOAS_VINDAS("Bem vindo(a) ao Emprestimus! "),
	COPYRIGHT("Copyright © 2010 Joeffison S. A. All rights reserved."),
	CONTATOS("Deseja contratar nossos servicos?\n"
			+ "Entao envie um email para: joeffisonsa@gmail.com"),
	CREDITOS("Copyright © 2010 Joeffison S. A. All rights reserved.\n\n" + ""
			+ "Programadores: \n" + "Joeffison Silverio de Andrade;\n"
			+ "José Nathaniel Lacerda de Abrante;\n"
			+ "José Ulisses de Brito Lira Filho;\n" + "Samir Trajano Feitosa\n"
			+ "Talita Bac\n" + "\n" + "\n" + "Detalhes importantes:\n\n" + ""
			+ "Este e um projeto da disciplina de Sistemas de Informacao I\n"
			+ "Instituicao: UFCG\n" + "Campus: Campina Grande\n" + "Periodo: 2011.2\n"
			+ "Professor: Nazareno Andrade\n"),
	ADEUS("Obrigado por usar os nossos servicos."),

	/* Menus: */
	MENU("\n--Menu Principal--\n" + "1 - Logar\n" + "2 - Cadastrar novo usuario\n"
			+ "3 - Zerar Sistema\n" + "4 - Sair\n"),

	// Enum comentado para fins didáticos (como NÃO fazer um menu para a
	// interface do sistema com o usuário). Joeffison
	/*
	 * MENU_LOGADO("\n--Voce esta logado--\n" + " 1 - Ver atributo do usuário\n"
	 * + " 2 - Cadastrar Item\n" + " 3 - Ver atributo de item\n" +
	 * " 4 - Localizar usuário\n" + " 5 - Solicitar amizade\n" +
	 * " 6 - Visualizar solicitações de amizade\n" + " 7 - Aprovar amizade\n" +
	 * " 8 - Verificar se tem amizade com outro usuário\n" +
	 * " 9 - Visualizar lista de amigos\n" +
	 * "10 - Visualizar lista de amigos de outro usuário\n" +
	 * "11 - Visualizar itens\n" + "12 - Visualizar itens de outro usuário\n" +
	 * "13 - Requisitar emprestimo\n" + "14 - Aprovar Emprestimo\n" +
	 * "15 - Visualizar Emprestimos\n" + "16 - Devolver Item\n" +
	 * "17 - Confirmar Termino do Emprestimo\n" + "18 - Enviar Mensagem\n" +
	 * "19 - Enviar Mensagem sobre emprestimo\n" + "20 - Ler Topicos\n" +
	 * "21 - Ler Mensagens\n" + "22 - Requisitar Devolucao\n" +
	 * "23 - Adicionar Dias\n" + "24 - Registrar Interesse\n" +
	 * "25 - Pesquisar item\n" + "26 - Desfazer amizade\n" +
	 * "27 - Apagar item\n" + "28 - Ver ranking\n" + "29 - Sair"),
	 */

	MENU_LOGADO("\n" + "--Voce esta logado--\n" + " 1 - Menu do seu perfil\n"
			+ " 2 - Menu do perfil de outros usuários\n"
			+ " 3 - Menu da caixa de mensagens\n" + " 4 - Menu de seus itens\n"
			+ " 5 - Menu de empréstimos\n" + " 6 - Deslogar"),

	MENU_CAIXA_DE_MENSAGENS("\n" + "--Menu da caixa de mensagens--\n"
			+ " 1 - Enviar Mensagem Off-Topic\n"
			+ " 2 - Enviar Mensagem sobre emprestimo\n" + " 3 - Ler Topicos\n"
			+ " 4 - Ler Mensagens\n" + " 5 - Voltar"),

	MENU_PERFIL_PROPRIO("\n" + "--Menu do seu Perfil--\n" + " 1 - Ver atributo\n"
			+ " 2 - Localizar usuário por uma chave\n" + " 3 - Localizar usuário\n"
			+ " 4 - Solicitar amizade\n" + " 5 - Visualizar solicitações de amizade\n"
			+ " 6 - Aprovar amizade\n"
			+ " 7 - Verificar se tem amizade com outro usuário\n"
			+ " 8 - Visualizar lista de amigos\n" + " 9 - Desfazer amizade\n"
			+ "10 - Ver ranking\n" + "11 - Adicionar Dias\n"
			+ "12 - Visualizar seu histórico\n"
			+ "13 - Visualizar seu histórico junto com o de seus amigos\n"
			+ "14 - Voltar"),

	MENU_PERFIL_ALHEIO("\n" + "--Menu do perfil de outros usuários--\n"
			+ " 1 - Ver atributo do usuário\n"
			+ " 2 - Visualizar lista de amigos do usuário\n"
			+ " 3 - Visualizar itens do usuário\n" + " 4 - Voltar"),

	MENU_ITENS_PROPRIOS("\n" + "--Menu de Itens--\n" + " 1 - Cadastrar Item\n"
			+ " 2 - Ver atributo de item\n" + " 3 - Visualizar itens\n"
			+ " 4 - Pesquisar item\n" + " 5 - Apagar item\n" + " 6 - Voltar"),

	MENU_EMPRESTIMOS("\n" + "--Menu de empréstimos--\n" + " 1 - Requisitar emprestimo\n"
			+ " 2 - Aprovar Emprestimo\n" + " 3 - Visualizar Emprestimos\n"
			+ " 4 - Devolver Item\n" + " 5 - Confirmar Termino do Emprestimo\n"
			+ " 6 - Enviar Mensagem sobre emprestimo\n" + " 7 - Requisitar Devolucao\n"
			+ " 8 - Registrar Interesse\n" + " 9 - Publicar pedido\n"
			+ "10 - Oferecer item\n" + "11 - Republicar pedido\n" + "12 - Voltar"),

	/* Utilitários dos menus */
	PERGUNTAR_TENTAR_NOVAMENTE("Deseja tentar novamente?\n" + "1 - Sim\n" + "2 - Não"),
	REENCAMINHAMENTO_AO_MENU_PRINCIPAL("O sistema esta sendo reencaminhado ao menu principal."),
	ENTER("\n"),
	OK("OK"),
	MEUS_AMIGOS("\n--Meus Amigos--"),
	DESLOGAR("Você está sendo deslogado... \n\n\n"),

	/* Mensagens para a entrada de dados */
	PEDIR_OPCAO("Por favor, digite uma opcao: "),

	PEDIR_SENHA("Entre com a sua senha: "),
	PEDIR_LOGIN("Entre com o seu login: "),
	PEDIR_LOGIN_USUARIO("Digite o login do usuário."),
	PEDIR_LOGIN_AMIGO_ADICIONAR("Digite o login do amigo a ser adicionado."),
	PEDIR_LOGIN_AMIGO_APROVAR("Digite o login do amigo a ser aprovado."),
	PEDIR_LOGIN_AMIGO_VERIFICAR("Digite o login do amigo a ser verificado."),
	PEDIR_LOGIN_AMIGO_DELETAR("Digite o login do amigo a ser deletado."),
	PEDIR_LOGIN_USUARIO_VERIFICAR("Digite o login do usuário a ser verificado."),

	PEDIR_ID_TEM("Qual o id do item desejado?"),
	PEDIR_ID_TEM_VERIFICAR("Qual o id do item a ser verificado?"),
	PEDIR_ID_EMPRESTIMO_APROVAR("Qual o id da requisitação do emprestimo a ser aprovado?"),
	PEDIR_ID_EMPRESTIMO("Informe o id do emprestimo:"),
	PEDIR_ID_REQUISITACAO_EMPRESTIMO("Informe o id da requisitação do emprestimo:"),
	PEDIR_ID_TOPICO("Informe o id do tópico: "),

	PEDIR_CATEGORIA("Qual a categoria?"),
	PEDIR_ATRIBUTO("Qual o atributo?"),
	PEDIR_CHAVE_BUSCA("Digite a palavra chave a ser procurada:"),
	PEDIR_INCREMENTO_DIAS("Informe o valor a ser incrementado."),

	PEDIR_DURACAO_EMPRESTIMO("Qual a duracao do emprestimo?"),
	PEDIR_TIPO_EMPRESTIMO("Qual o tipo de emprestimo que você deseja?"),

	PEDIR_TIPO("Informe o tipo desejado: "),
	PEDIR_TIPO_ORDENACAO("Qual o tipo de ordenação?"),
	PEDIR_CRITERIO_ORDENACAO("Qual o critério de ordenação?"),

	PEDIR_DESTINATARIO("Informe o destinatário."),
	PEDIR_ASSUNTO("Informe o assunto"),
	PEDIR_MENSAGEM("Escreva a mensagem que deseja enviar."),

	PEDIR_NOME_DO_AMIGO("\nDigite o nome do seu amigo: <Enter para voltar>"),

	/* Entradas de dados para cadastros */
	NOME_PARA_CADASTRAR("Qual o nome do usuario a ser cadastrado?"),
	LOGIN_PARA_CADASTRAR("Qual o login do usuario a ser cadastrado?"),
	ENDERECO_PARA_CADASTRAR("Qual o endereco do usuario a ser cadastrado?"),

	PEDIR_NOME_ITEM_CADASTRAR("Qual o nome do item a ser cadastrado?"),
	PEDIR_CATEGORIA_ITEM_CADASTRAR("Qual a categoria do item a ser cadastrado?"),
	PEDIR_DESCRICAO_ITEM_CADASTRAR("Qual a descrição do item a ser cadastrado?"),

	/* Informativos */
	INFO_SAO_AMIGOS("Vocês são amigos."),
	INFO_NAO_SAO_AMIGOS("Vocês não são amigos."),

	/* Avisos */
	INFO_AGUARDE_APROVACAO_AMIZADE("Usuario adicionado a sua lista de amizades pendentes.\n"
			+ "Este usuário irá para sua lista de amigos, assim que ele aprovar a sua solicitação."),
	OPERACAO_SUCESSO("Operação realizada com sucesso!"),

	/* Mensagens de erro: */
	OPCAO_INVALIDA("Opcao invalida."),
	OUTRA_OPCAO("Escolha uma outra opcao. "),
	CARACTERES_INVALIDOS("Caracteres invalidos. "),
	OPERACAO_FALHOU("Ocorreu um problema na execucao desta operacao."),

	/* Argumentos invalidos */
	LOGIN_INVALIDO("Login inválido"),
	LOGIN_INEXISTENTE("Login inexistente"),
	LOGIN_JAH_CADASTRADO("Já existe um usuário com este login"),

	NOME_INVALIDO("Nome inválido"),
	NOME_INEXISTENTE("Nome inexistente"),

	ENDERECO_INVALIDO("Endereco inválido"),
	ENDERECO_INEXISTENTE("Endereco inexistente"),

	CATEGORIA_INVALIDA("Categoria inválida"),
	CATEGORIA_INEXISTENTE("Categoria inexistente"),

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
	REQUISICAO_AMIZADE_INEXISTENTE("Requisição de amizade inexistente"),

	TIPO_INEXISTENTE("Tipo inexistente"),

	EMPRESTIMO_INEXISTENTE("Empréstimo inexistente"),
	ID_REQUISICAO_EMPRESTIMO_INVALIDO("Identificador da requisição de empréstimo é inválido"),
	EMPRESTIMO_TIPO_INVALIDO("Tipo inválido"),
	EMPRESTIMO_TIPO_INXISTENTE("Tipo inexistente"),
	EMPRESTIMO_DURACAO_INVALIDA("Duracao inválida"),
	EMPRESTIMO_SEM_PERMISSAO_APROVAR("O empréstimo só pode ser aprovado pelo dono do item"),
	EMPRESTIMO_JA_APROVADO("Empréstimo já aprovado"),
	REQUISICAO_EMPRESTIMO_JA_SOLICITADA("Requisição já solicitada"),
	EMPRESTIMO_DEVOLUCAO_APENAS_BENEFICIADO("O item só pode ser devolvido pelo usuário beneficiado"),
	EMPRESTIMO_DEVOLUCAO_CONFIRMADA_APENAS_EMPRESTADOR("O término do empréstimo só pode ser confirmado pelo dono do item"),
	NAO_HA_EMPRESTIMOS_DESTE_TIPO("Não há empréstimos deste tipo"),
	ID_EMPRESTIMO_INVALIDO("Identificador do empréstimo é inválido"),
	ID_REQUISICAO_EMP_INEXISTENTE("Requisição de empréstimo inexistente"),
	TERMINO_EMPRESTIMO_JA_CONFIRMADO("Término do empréstimo já confirmado"),
	USUARIO_NAO_PARTICIPA_DESTE_EMPRESTIMO("O usuário não participa deste empréstimo"),
	DEVOLUCAO_JA_REQUISITADA("Devolução já requisitada"),
	NAO_PODE_EMPRESTAR_ITEM_EMPRESTADO("O usuário não pode apagar este item enquanto estiver emprestado"),

	TIPO_INVALIDO("Tipo inválido"),
	ITEM_JA_DEVOLVIDO("Item já devolvido"),

	REMETENTE_INVALIDO("Remetente inválido"),
	DESTINATARIO_INVALIDO("Destinatário inválido"),
	DESTINATARIO_INEXISTENTE("Destinatário inexistente"),
	ASSUNTO_INVALIDO("Assunto inválido"),
	MENSAGEM_INVALIDA("Mensagem inválida"),
	ID_MENSAGEM_INVALIDO("Identificador mensagem inválido"),
	TOPICO_ID_INVALIDO("Identificador do tópico é inválido"),
	TOPICO_ID_INEXISTENTE("Tópico inexistente"),
	NAO_HA_TOPICOS_CRIADOS("Não há tópicos criados"),

	CHAVE_INVALIDA("Chave inválida"),
	USUARIO_SEM_PERMISSAO_LEITURA_TOPICO("O usuário não tem permissão para ler as mensagens deste tópico"),
	ORDENACAO_TIPO_INVALIDO("Tipo inválido de ordenação"),
	ORDENACAO_TIPO_INEXISTENTE("Tipo de ordenação inexistente"),
	ORDENACAO_CRITERIO_INVALIDO("Critério inválido de ordenação"),
	ORDENACAO_CRITERIO_INEXISTENTE("Critério de ordenação inexistente"),
	NENHUM_ITEM_ENCONTRADO("Nenhum item encontrado"),

	AMIZADE_INEXISTENTE("Amizade inexistente"),
	SEM_PERMISSAO_APAGAR_ITEM("O usuário não tem permissão para apagar este item"),

	HISTORICO_VAZIO("Não há atividades"),
	NOTIFICACAO_INEXISTENTE("Notificacao inexixtente"),

	PROPRIETARIO_CAIXA_POSTAL_INEXISTENTE("Proprietário inexistente"),
	PROPRIETARIO_CAIXA_POSTAL_JAH_CADASTRADO("Proprietário já cadastrado"),
	PROPRIETARIO_CONTA_INEXISTENTE("Proprietário inexistente"),
	PROPRIETARIO_CONTA_JAH_CADASTRADO("Proprietário da conta já cadastrado"),
	PROPRIETARIO_BAUH_INEXISTENTE("Proprietário inexistente"),
	PROPRIETARIO_BAUH_JAH_CADASTRADO("Proprietario do baú já cadastrado"),
	PROPRIETARIO_RACK_JAH_CADASTRADO("Proprietário do rack já cadastrado"),
	PROPRIETARIO_RACK_INEXISTENTE("Proprietário inexistente"),
	PEDIR_NOME_ITEM("Qual o nome do item?"),
	PEDIR_DESCRICAO_ITEM("Qual a descrição do item?"),
	PEDIR_ID_PUBLICACAO_PEDIDO("Informe o id da publicação do pedido."),
	PEDIR_ID_ITEM("Informe o id do item."),
	PUBLICACAO_ID_INVALIDO("Identificador da publicação de pedido é inválido"),
	PUBLICACAO_ID_INEXISTENTE("Publicação de pedido inexistente"),
	USUARIO_NAO_PODE_OFERECER_UM_ITEM_A_UM_DESCONHECIDO("O usuário não pode emprestar um item a um desconhecido"),
	ITEM_NAO_ME_PERTENCE("Não é possível emprestar um item que não é seu"),
	DESCRICAO_INVALIDA("Descrição inválida"),
	PROPRIETARIO_CONTA_CICLO_AMIZADE_JAH_CADASTRADO("Proprietário da conta ciclo de amizade já cadastrado"), 
	EMAIL_INVALIDO("Email inválido"), 
	SENHA_ATUAL_INVALIDA("Senha atual inválida"),
	SENHA_NOVA_INVALIDA("Senha nova inválida"), 
	SENHA_INVALIDA("Senha inválida"), 
	SESSAO_JAH_ENCERRADA("Sessão já encerrada"),
	EMAIL_INVALIDO_INEXISTENTE("Email inválido ou inexistente"),
	CAMINHO_IMG_INVALIDA("O caminho da imagem é invalido");

	private final String mensagem;

	/**
	 * Associa os parametros dos enums aos seus respectivos atributos
	 * 
	 * @param mensagem
	 *            Mensagem correspondente ao enum
	 */
	private Mensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	/**
	 * Recupera a mensagem associada ao enum
	 * 
	 * @return Retorna a mensagem que o enum carrega
	 */
	public String getMensagem() {
		return this.mensagem;
	}

	@Override
	/**
	 * Gera a mensagem especifica do enum.
	 * 
	 * @return
	 *                      Retorna a mensagem especifica do enum.
	 */
	public String toString() {
		return this.mensagem;
	}

}
