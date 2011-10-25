package sistema.utilitarios;

/**
 * Emprestimus
 * Projeto de Sistemas de InformaÃ§Ã£o I
 * Universidade Federal de Campina Grande
 * 
 * Enum que encapsula as mensagens usadas no sistema.
 * 
 * 
 * @author Joeffison Silverio de Andrade, 21011853, joeffisonsa@gmail.com
 * @version 1.0
 */
public enum Mensagem {
	
	/*Especiais*/
	NOME_PROJETO("Emprestimus"),
	BOAS_VINDAS("Bem vindo(a) ao Emprestimus! "),
	COPYRIGHT("Copyright Â© 2010 Joeffison S. A. All rights reserved."),
	CONTATOS("Deseja contratar nossos servicos?\n" +
					 "Entao envie um email para: joeffisonsa@gmail.com"),
	CREDITOS("Copyright Â© 2010 Joeffison S. A. All rights reserved.\n\n" +
					 "" +
					 "Programadores: \n" +
					 "Joeffison Silverio de Andrade;\n" +
					 "JosÃ© Nathaniel Lacerda de Abrante;\n" +
					 "JosÃ© Ulisses de Brito Lira Filho;\n" +
					 "Samir Trajano Feitosa\n" +
					 "Talita Bac\n" +
					 "\n" +
					 "\n" +
					 "Detalhes importantes:\n\n" +
					 "" +
					 "Este e um projeto da disciplina de Sistemas de Informacao I\n" +
					 "Instituicao: UFCG\n" +
					 "Campus: Campina Grande\n" +
					 "Periodo: 2011.2\n" +
					 "Professor: Nazareno Andrade\n"),
	ADEUS("Obrigado por usar os nossos servicos."),
					 
	/*Menus: */
	MENU("\n--Menu Principal--\n" +
		 "1 - Logar\n" +
		 "2 - Cadastrar novo usuario\n" +
		 "3 - Zerar Sistema\n" +
		 "4 - Sair\n"),
		 
// Enum comentado para fins didÃ¡ticos (como NÃƒO fazer um menu para a interface do sistema com o usuÃ¡rio). Joeffison
/*	MENU_LOGADO("\n--Voce esta logado--\n" +
			" 1 - Ver atributo do usuÃ¡rio\n" +
			" 2 - Cadastrar Item\n" +
			" 3 - Ver atributo de item\n" +
			" 4 - Localizar usuÃ¡rio\n" +
			" 5 - Solicitar amizade\n" +
			" 6 - Visualizar solicitaÃ§Ãµes de amizade\n" +
			" 7 - Aprovar amizade\n" +
			" 8 - Verificar se tem amizade com outro usuÃ¡rio\n" +
			" 9 - Visualizar lista de amigos\n" +
			"10 - Visualizar lista de amigos de outro usuÃ¡rio\n" +
			"11 - Visualizar itens\n" +
			"12 - Visualizar itens de outro usuÃ¡rio\n" +
			"13 - Requisitar emprestimo\n" +
			"14 - Aprovar Emprestimo\n" +
			"15 - Visualizar Emprestimos\n" +
			"16 - Devolver Item\n" +
			"17 - Confirmar Termino do Emprestimo\n" +
			"18 - Enviar Mensagem\n" +
			"19 - Enviar Mensagem sobre emprestimo\n" +
			"20 - Ler Topicos\n" +
			"21 - Ler Mensagens\n" +
			"22 - Requisitar Devolucao\n" +
			"23 - Adicionar Dias\n" +
			"24 - Registrar Interesse\n" +
			"25 - Pesquisar item\n" +
			"26 - Desfazer amizade\n" +
			"27 - Apagar item\n" +
			"28 - Ver ranking\n" +
			"29 - Sair"),
*/
			
	MENU_LOGADO("\n" +
			"--Voce esta logado--\n" +
			" 1 - Menu do seu perfil\n" +
			" 2 - Menu do perfil de outros usuÃ¡rios\n" +
			" 3 - Menu da caixa de mensagens\n" +
			" 4 - Menu de seus itens\n" +
			" 5 - Menu de emprÃ©stimos\n" +
			" 6 - Deslogar"),
			
	MENU_CAIXA_DE_MENSAGENS("\n" +
			"--Menu da caixa de mensagens--\n" +
			" 1 - Enviar Mensagem Off-Topic\n" +
			" 2 - Enviar Mensagem sobre emprestimo\n" +
			" 3 - Ler Topicos\n" +
			" 4 - Ler Mensagens\n" +
			" 5 - Voltar"),
			
	MENU_PERFIL_PROPRIO("\n" +
			"--Menu do seu Perfil--\n" +
			" 1 - Ver atributo\n" +
			" 2 - Localizar usuÃ¡rio por uma chave\n" +
			" 3- Localizar usuÃ¡rio\n" +
			" 4 - Solicitar amizade\n" +
			" 5 - Visualizar solicitaÃ§Ãµes de amizade\n" +
			" 6 - Aprovar amizade\n" +
			" 7 - Verificar se tem amizade com outro usuÃ¡rio\n" +
			" 8 - Visualizar lista de amigos\n" +
			" 9 - Desfazer amizade\n" +
			"10 - Ver ranking\n" +
			"11 - Adicionar Dias\n" +
			"12 - Visualizar seu histÃ³rico\n" +
			"13 - Visualizar seu histÃ³rico junto com o de seus amigos\n" +
			"14 - Voltar"),
			
	MENU_PERFIL_ALHEIO("\n" +
			"--Menu do perfil de outros usuÃ¡rios--\n" +
			" 1 - Ver atributo do usuÃ¡rio\n" +
			" 2 - Visualizar lista de amigos do usuÃ¡rio\n" +
			" 3 - Visualizar itens do usuÃ¡rio\n" +
			" 4 - Voltar"),
	
	MENU_ITENS_PROPRIOS("\n" +
			"--Menu de Itens--\n" +
			" 1 - Cadastrar Item\n" +
			" 2 - Ver atributo de item\n" +
			" 3 - Visualizar itens\n" +
			" 4 - Pesquisar item\n" +
			" 5 - Apagar item\n" +
			" 6 - Voltar"),
	
	MENU_EMPRESTIMOS("\n" +
			"--Menu de emprÃ©stimos--\n" +
			" 1 - Requisitar emprestimo\n" +
			" 2 - Aprovar Emprestimo\n" +
			" 3 - Visualizar Emprestimos\n" +
			" 4 - Devolver Item\n" +
			" 5 - Confirmar Termino do Emprestimo\n" +
			" 6 - Enviar Mensagem sobre emprestimo\n" +
			" 7 - Requisitar Devolucao\n" +
			" 8 - Registrar Interesse\n" +
			" 9 - Publicar pedido\n" +
			"10 - Oferecer item\n" +
			"11 - Republicar pedido\n" +
			"12 - Voltar"),
			
	MENU_ITEMCATEGORIA("\n--Escolha uma categoria:--\n" +
					   "1 - Livro\n" +
					   "2 - Filme\n" +
					   "3 - Jogo\n"),
			
	/*UtilitÃ¡rios dos menus*/
	PERGUNTAR_TENTAR_NOVAMENTE("Deseja tentar novamente?\n"+
							   "1 - Sim\n" +
							   "2 - NÃ£o"),
	REENCAMINHAMENTO_AO_MENU_PRINCIPAL("O sistema esta sendo reencaminhado ao menu principal."),
	ENTER("\n"),
	OK("OK"),
	MEUS_AMIGOS("\n--Meus Amigos--"),
	DESLOGAR("VocÃª estÃ¡ sendo deslogado... \n\n\n"),
					 
	/*Mensagens para a entrada de dados */
	PEDIR_OPCAO("Por favor, digite uma opcao: "),
	
	PEDIR_SENHA("Entre com a sua senha: "),
	PEDIR_LOGIN("Entre com o seu login: "),
	PEDIR_LOGIN_USUARIO("Digite o login do usuÃ¡rio."),
	PEDIR_LOGIN_AMIGO_ADICIONAR("Digite o login do amigo a ser adicionado."),
	PEDIR_LOGIN_AMIGO_APROVAR("Digite o login do amigo a ser aprovado."),
	PEDIR_LOGIN_AMIGO_VERIFICAR("Digite o login do amigo a ser verificado."),
	PEDIR_LOGIN_AMIGO_DELETAR("Digite o login do amigo a ser deletado."),
	PEDIR_LOGIN_USUARIO_VERIFICAR("Digite o login do usuÃ¡rio a ser verificado."),

	PEDIR_ID_TEM("Qual o id do item desejado?"),
	PEDIR_ID_TEM_VERIFICAR("Qual o id do item a ser verificado?"),
	PEDIR_ID_EMPRESTIMO_APROVAR("Qual o id da requisitaÃ§Ã£o do emprestimo a ser aprovado?"),
	PEDIR_ID_EMPRESTIMO("Informe o id do emprestimo:"),
	PEDIR_ID_REQUISITACAO_EMPRESTIMO("Informe o id da requisitaÃ§Ã£o do emprestimo:"),
	PEDIR_ID_TOPICO("Informe o id do tÃ³pico: "),
	
	PEDIR_CATEGORIA("Qual a categoria?"),
	PEDIR_ATRIBUTO("Qual o atributo?"),
	PEDIR_CHAVE_BUSCA("Digite a palavra chave a ser procurada:"),
	PEDIR_INCREMENTO_DIAS("Informe o valor a ser incrementado."),
	
	PEDIR_DURACAO_EMPRESTIMO("Qual a duracao do emprestimo?"),
	PEDIR_TIPO_EMPRESTIMO("Qual o tipo de emprestimo que vocÃª deseja?"),
	
	PEDIR_TIPO("Informe o tipo desejado: "),
	PEDIR_TIPO_ORDENACAO("Qual o tipo de ordenaÃ§Ã£o?"),
	PEDIR_CRITERIO_ORDENACAO("Qual o critÃ©rio de ordenaÃ§Ã£o?"),
	
	PEDIR_DESTINATARIO("Informe o destinatÃ¡rio."),
	PEDIR_ASSUNTO("Informe o assunto"),
	PEDIR_MENSAGEM("Escreva a mensagem que deseja enviar."),
	
	PEDIR_NOME_DO_AMIGO("\nDigite o nome do seu amigo: <Enter para voltar>"),
	
	/*Entradas de dados para cadastros*/
	NOME_PARA_CADASTRAR("Qual o nome do usuario a ser cadastrado?"),
	LOGIN_PARA_CADASTRAR("Qual o login do usuario a ser cadastrado?"),
	ENDERECO_PARA_CADASTRAR("Qual o endereco do usuario a ser cadastrado?"),
	
	PEDIR_NOME_ITEM_CADASTRAR("Qual o nome do item a ser cadastrado?"),
	PEDIR_CATEGORIA_ITEM_CADASTRAR("Qual a categoria do item a ser cadastrado?"),
	PEDIR_DESCRICAO_ITEM_CADASTRAR("Qual a descriÃ§Ã£o do item a ser cadastrado?"),
	
	/*Informativos*/
	INFO_SAO_AMIGOS("VocÃªs sÃ£o amigos."),
	INFO_NAO_SAO_AMIGOS("VocÃªs nÃ£o sÃ£o amigos."),
			
	/*Avisos*/
	INFO_AGUARDE_APROVACAO_AMIZADE("Usuario adicionado a sua lista de amizades pendentes.\n" +
								   "Este usuÃ¡rio irÃ¡ para sua lista de amigos, assim que ele aprovar a sua solicitaÃ§Ã£o."),
	OPERACAO_SUCESSO("OperaÃ§Ã£o realizada com sucesso!"),
								   
	/*Mensagens de erro: */
	OPCAO_INVALIDA("Opcao invalida."),
	OUTRA_OPCAO("Escolha uma outra opcao. "),
	CARACTERES_INVALIDOS("Caracteres invalidos. "),
	OPERACAO_FALHOU("Ocorreu um problema na execucao desta operacao."),
	
	/*Argumentos invalidos*/
	LOGIN_INVALIDO("Login invÃ¡lido"),
	LOGIN_INEXISTENTE("Login inexistente"),
	LOGIN_JAH_CADASTRADO("JÃ¡ existe um usuÃ¡rio com este login"),
	
	NOME_INVALIDO("Nome invÃ¡lido"),
	NOME_INEXISTENTE("Nome inexistente"),
	
	ENDERECO_INVALIDO("Endereco invÃ¡lido"),
	ENDERECO_INEXISTENTE("Endereco inexistente"),
	
	CATEGORIA_INVALIDA("Categoria invÃ¡lida"),
	CATEGORIA_INEXISTENTE("Categoria inexistente"),
	
	ID_ITEM_INVALIDO("Identificador do item Ã© invÃ¡lido"),
	ID_ITEM_INEXISTENTE("Item inexistente"),
	
	ATRIBUTO_INVALIDO("Atributo invÃ¡lido"),
	ATRIBUTO_INEXISTENTE("Atributo inexistente"),
	
	PALAVRA_CHAVE_INVALIDA("Palavra-chave invÃ¡lida"),
	PALAVRA_CHAVE_INEXISTENTE("Nenhum usuÃ¡rio encontrado"),
	
	NENHUM_USUARIO_EXISTENTE("Nenhum usuÃ¡rio existente"),
	
	NAO_HA_REQUISICOES("NÃ£o hÃ¡ requisiÃ§Ãµes"),
	
	SESSAO_INVALIDA("SessÃ£o invÃ¡lida"),
	SESSAO_INEXISTENTE("SessÃ£o inexistente"),
	
	USUARIO_INEXISTENTE("UsuÃ¡rio inexistente"),
	USUARIO_JAH_SAO_AMIGOS("Os usuÃ¡rios jÃ¡ sÃ£o amigos"),
	USUARIO_NAO_POSSUI_AMIGOS("O usuÃ¡rio nÃ£o possui amigos"),
	USUARIO_SEM_ITENS_CADASTRADOS("O usuÃ¡rio nÃ£o possui itens cadastrados"),
	USUARIO_NAO_TEM_PEMISSAO_VER_ITENS("O usuÃ¡rio nÃ£o tem permissÃ£o para visualizar estes itens"),
	USUARIO_NAO_TEM_PERMISSAO_REQUISITAR_EMPREST_ITEM("O usuÃ¡rio nÃ£o tem permissÃ£o para requisitar o emprÃ©stimo deste item"),
	
	AMIZADE_JAH_SOLICITADA("RequisiÃ§Ã£o jÃ¡ solicitada"),
	REQUISICAO_AMIZADE_INEXISTENTE("RequisiÃ§Ã£o de amizade inexistente"),
	
	TIPO_INEXISTENTE("Tipo inexistente"),
	
	EMPRESTIMO_INEXISTENTE("EmprÃ©stimo inexistente"),
	ID_REQUISICAO_EMPRESTIMO_INVALIDO("Identificador da requisiÃ§Ã£o de emprÃ©stimo Ã© invÃ¡lido"),
	EMPRESTIMO_TIPO_INVALIDO("Tipo invÃ¡lido"),
	EMPRESTIMO_TIPO_INXISTENTE("Tipo inexistente"),
	EMPRESTIMO_DURACAO_INVALIDA("Duracao invÃ¡lida"),
	EMPRESTIMO_SEM_PERMISSAO_APROVAR("O emprÃ©stimo sÃ³ pode ser aprovado pelo dono do item"),
	EMPRESTIMO_JA_APROVADO("EmprÃ©stimo jÃ¡ aprovado"),
	REQUISICAO_EMPRESTIMO_JA_SOLICITADA("RequisiÃ§Ã£o jÃ¡ solicitada"),
	EMPRESTIMO_DEVOLUCAO_APENAS_BENEFICIADO("O item sÃ³ pode ser devolvido pelo usuÃ¡rio beneficiado"),
	EMPRESTIMO_DEVOLUCAO_CONFIRMADA_APENAS_EMPRESTADOR("O tÃ©rmino do emprÃ©stimo sÃ³ pode ser confirmado pelo dono do item"),
	NAO_HA_EMPRESTIMOS_DESTE_TIPO("NÃ£o hÃ¡ emprÃ©stimos deste tipo"),
	ID_EMPRESTIMO_INVALIDO("Identificador do emprÃ©stimo Ã© invÃ¡lido"),
	ID_REQUISICAO_EMP_INEXISTENTE("RequisiÃ§Ã£o de emprÃ©stimo inexistente"),
	TERMINO_EMPRESTIMO_JA_CONFIRMADO("TÃ©rmino do emprÃ©stimo jÃ¡ confirmado"),
	USUARIO_NAO_PARTICIPA_DESTE_EMPRESTIMO("O usuÃ¡rio nÃ£o participa deste emprÃ©stimo"),
	DEVOLUCAO_JA_REQUISITADA("DevoluÃ§Ã£o jÃ¡ requisitada"),
	NAO_PODE_EMPRESTAR_ITEM_EMPRESTADO("O usuÃ¡rio nÃ£o pode apagar este item enquanto estiver emprestado"),
	
	TIPO_INVALIDO("Tipo invÃ¡lido"),
	ITEM_JA_DEVOLVIDO("Item jÃ¡ devolvido"), 
	
	REMETENTE_INVALIDO("Remetente invÃ¡lido"),
	DESTINATARIO_INVALIDO("DestinatÃ¡rio invÃ¡lido"), 
	DESTINATARIO_INEXISTENTE("DestinatÃ¡rio inexistente"), 
	ASSUNTO_INVALIDO("Assunto invÃ¡lido"),
	MENSAGEM_INVALIDA("Mensagem invÃ¡lida"), 
	ID_MENSAGEM_INVALIDO("Identificador mensagem invÃ¡lido"),
	TOPICO_ID_INVALIDO("Identificador do tÃ³pico Ã© invÃ¡lido"),
	TOPICO_ID_INEXISTENTE("TÃ³pico inexistente"),
	NAO_HA_TOPICOS_CRIADOS("NÃ£o hÃ¡ tÃ³picos criados"),
	
	CHAVE_INVALIDA("Chave invÃ¡lida"),
	USUARIO_SEM_PERMISSAO_LEITURA_TOPICO("O usuÃ¡rio nÃ£o tem permissÃ£o para ler as mensagens deste tÃ³pico"),
	ORDENACAO_TIPO_INVALIDO("Tipo invÃ¡lido de ordenaÃ§Ã£o"),
	ORDENACAO_TIPO_INEXISTENTE("Tipo de ordenaÃ§Ã£o inexistente"),
	ORDENACAO_CRITERIO_INVALIDO("CritÃ©rio invÃ¡lido de ordenaÃ§Ã£o"),
	ORDENACAO_CRITERIO_INEXISTENTE("CritÃ©rio de ordenaÃ§Ã£o inexistente"),
	NENHUM_ITEM_ENCONTRADO("Nenhum item encontrado"),
	
	AMIZADE_INEXISTENTE("Amizade inexistente"),
    SEM_PERMISSAO_APAGAR_ITEM("O usuÃ¡rio nÃ£o tem permissÃ£o para apagar este item"),
    
    HISTORICO_VAZIO("NÃ£o hÃ¡ atividades"),
    NOTIFICACAO_INEXISTENTE("Notificacao inexixtente"),
	
	PROPRIETARIO_CAIXA_POSTAL_INEXISTENTE("ProprietÃ¡rio inexistente"),
	PROPRIETARIO_CAIXA_POSTAL_JAH_CADASTRADO("ProprietÃ¡rio jÃ¡ cadastrado"),
	PROPRIETARIO_CONTA_INEXISTENTE("ProprietÃ¡rio inexistente"),
	PROPRIETARIO_CONTA_JAH_CADASTRADO("ProprietÃ¡rio da conta jÃ¡ cadastrado"),
	PROPRIETARIO_BAUH_INEXISTENTE("ProprietÃ¡rio inexistente"),
	PROPRIETARIO_BAUH_JAH_CADASTRADO("Proprietario do baÃº jÃ¡ cadastrado"), 
	PROPRIETARIO_RACK_JAH_CADASTRADO("ProprietÃ¡rio do rack jÃ¡ cadastrado"),
	PROPRIETARIO_RACK_INEXISTENTE("ProprietÃ¡rio inexistente"),
	PEDIR_NOME_ITEM("Qual o nome do item?"),
	PEDIR_DESCRICAO_ITEM("Qual a descriÃ§Ã£o do item?"),
	PEDIR_ID_PUBLICACAO_PEDIDO("Informe o id da publicaÃ§Ã£o do pedido."),
	PEDIR_ID_ITEM("Informe o id do item."), 
	PUBLICACAO_ID_INVALIDO("Identificador da publicação de pedido é inválido"), 
	PUBLICACAO_ID_INEXISTENTE("Publicação de pedido inexistente"), 
	USUARIO_NAO_PODE_OFERECER_UM_ITEM_A_UM_DESCONHECIDO("O usuário não pode emprestar um item a um desconhecido"), 
	ITEM_NAO_ME_PERTENCE("Não é possível emprestar um item que não é seu"), 
	DESCRICAO_INVALIDA("Descrição inválida");
	
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