package iu;

import java.util.Scanner;
import sistema.utilitarios.Mensagem;

public class InterfaceTexto {
	
	private static EmprestimusIF emprestimus;
	private static String login_logado;
	private static String id_sessao;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		emprestimus = Emprestimus.getInstance();
		
		menuDeslogado();
	}
	
	private static void menuDeslogado() {
		
		final int logar = 1;
		final int criaUsuario = 2;
		final int zerarSistema = 3;
		final int encerrarSistema = 4;
		int opcao;

		iuExibirMensagem(Mensagem.BOAS_VINDAS.getMensagem());
		iuExibirMensagem("\n\n" + Mensagem.MENU.getMensagem());
		iuExibirMensagem(Mensagem.PEDIR_OPCAO.getMensagem());
		
		opcao = pegarOpcao(1, 4);
		
		switch (opcao) {
		case logar:
			logar();
			break;
			
		case criaUsuario:
			criarUsuario();
			break;
			
		case zerarSistema:
			emprestimus.zerarSistema();
			break;
			
		case encerrarSistema:
			iuExibirMensagem(Mensagem.ADEUS.getMensagem());
			encerrarSistema();
			break;
			
		default:
			menuDeslogado();
			break;
		}
		
		menuDeslogado();
		
	}

	private static void encerrarSistema() {
		emprestimus.encerrarSistema();
		System.exit(0);
		
	}

	private static void logar() {
		iuExibirMensagem(Mensagem.PEDIR_LOGIN.getMensagem());
		
		try {
			login_logado = pegaStringDaEntrada();
			id_sessao = emprestimus.abrirSessao(login_logado);
			menuLogado();
			
		} catch (Exception e) {
			id_sessao = null;
			login_logado = null;
			iuExibirMensagem(e.getMessage());
			
			if(!tentarNovamente()){
				menuDeslogado();
			}
			
			logar();
		}
	}

	private static void menuLogado() {
		int opcao;
		iuExibirMensagem(Mensagem.MENU_LOGADO.getMensagem());
		
		final int OPCAO_SAIR = 6;
		opcao = pegarOpcao(1, OPCAO_SAIR);
		
		switch (opcao) {
		case 1:
			menuPerfilProprio();
			break;
			
		case 2:
			menuPerfilOutroUsuario();
			break;
			
		case 3:
			menuCaixaMensagem();
			break;
			
		case 4:
			menuItens();
			break;
	
		case 5:
			menuEmprestimos();
			break;
			
		case OPCAO_SAIR:
			deslogar();
			break;
			
		default:
			break;
		}
		if (opcao != OPCAO_SAIR) menuLogado();
	}

	private static void deslogar() {
		iuExibirMensagem(Mensagem.DESLOGAR.getMensagem());
		menuDeslogado();
	}
	
	private static void menuPerfilProprio() {
		int opcao;
		iuExibirMensagem(Mensagem.MENU_PERFIL_PROPRIO.getMensagem());
		
		final int OPCAO_SAIR = 11;
		opcao = pegarOpcao(1, OPCAO_SAIR);
		
		
		switch (opcao) {
		case 1:
			getAtributo();
			break;
			
		case 2:
			localizarUsuarioPalavraChave();
			break;
	
		case 3:
			requisitarAmizade();
			break;
			
		case 4:
			visualizarRequisitacoesAmizade();
			break;
	
		case 5:
			aprovarAmizade();
			break;
			
		case 6:
			verificarAmizade();
			break;
			
		case 7:
			verAmigos();
			break;
		
		case 8:
			desfazerAmizade();
			break;
		
		case 9:
			verRanking();
			break;
		
		case 10:
			incrementarDias();
			break;
		
		case OPCAO_SAIR:
			voltarMenuLogado();
			break;
			
		default:
			break;
		}
		if (opcao != OPCAO_SAIR) menuPerfilProprio();
	}
	
	private static void menuPerfilOutroUsuario() {
		int opcao;
		iuExibirMensagem(Mensagem.MENU_PERFIL_ALHEIO.getMensagem());
		
		final int OPCAO_SAIR = 4;
		opcao = pegarOpcao(1, OPCAO_SAIR);
		
		switch (opcao) {
		case 1:
			getAtributoOutroUsuario();
			break;
		
		case 2:
			verAmigosOutroUsuario();
			break;
		
		case 3:
			verItensOutroUsuario();
			break;
		
		case OPCAO_SAIR:
			voltarMenuLogado();
			break;
			
		default:
			break;
		}
		if (opcao != OPCAO_SAIR) menuPerfilOutroUsuario();
	}
	
	private static void menuCaixaMensagem() {
		int opcao;
		iuExibirMensagem(Mensagem.MENU_CAIXA_DE_MENSAGENS.getMensagem());
		
		final int OPCAO_SAIR = 5;
		opcao = pegarOpcao(1, OPCAO_SAIR);
		
		switch (opcao) {
		case 1:
			enviarMensagemOFFTopic();
			break;
			
		case 2:
			enviarMensagemNegociacao();
			break;
			
		case 3:
			lerTopicos();
			break;
			
		case 4:
			lerMensagens();
			break;
		
		case OPCAO_SAIR:
			voltarMenuLogado();
			break;
			
		default:
			break;
		}
		if (opcao != OPCAO_SAIR) menuCaixaMensagem();
	}
	
	private static void menuItens() {
		int opcao;
		iuExibirMensagem(Mensagem.MENU_ITENS_PROPRIOS.getMensagem());
		
		final int OPCAO_SAIR = 6;
		opcao = pegarOpcao(1, OPCAO_SAIR);
		
		switch (opcao) {
		case 1:
			cadastrarItem();
			break;
			
		case 2:
			getAtributoItem();
			break;
		
		case 3:
			verItens();
			break;
			
		case 4:
			pesquisarItem();
			break;
			
		case 5:
			apagarItem();
			break;
			
		case OPCAO_SAIR:
			voltarMenuLogado();
			break;
			
		default:
			break;
		}
		if (opcao != OPCAO_SAIR) menuItens();
	}
	
	private static void menuEmprestimos() {
		int opcao;
		iuExibirMensagem(Mensagem.MENU_EMPRESTIMOS.getMensagem());
		
		final int OPCAO_SAIR = 9;
		opcao = pegarOpcao(1, OPCAO_SAIR);
		
		switch (opcao) {
		case 1:
			requisitarEmprestimo();
			break;
			
		case 2:
			aprovarEmprestimo();
			break;
			
		case 3:
			visualizarEmprestimos();
			break;
			
		case 4:
			devolverItem();
			break;
			
		case 5:
			confirmarTerminoEmprestimo();
			break;
			
		case 6:
			enviarMensagemNegociacao();
			break;
			
		case 7:
			requisitarDevolucao();
			break;
			
		case 8:
			registrarInteresse();
			break;
		
		case OPCAO_SAIR:
			voltarMenuLogado();
			break;
			
		default:
			break;
		}
		if (opcao != OPCAO_SAIR) menuEmprestimos();
	}
	
	private static void voltarMenuLogado() {
		// Já volta por default.
	}

	private static void verRanking() {
		iuExibirMensagem(Mensagem.PEDIR_CATEGORIA.getMensagem());
		
		try {
			iuExibirMensagem(emprestimus.getRanking(id_sessao, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()){
				verRanking();
			}
		}
	}

	private static void apagarItem() {
		iuExibirMensagem(Mensagem.PEDIR_ID_TEM.getMensagem());
		
		try {
			emprestimus.apagarItem(id_sessao, pegaStringDaEntrada());
			iuExibirMensagem(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()){
				apagarItem();
			}
		}
	}

	private static void desfazerAmizade() {
		iuExibirMensagem(Mensagem.PEDIR_LOGIN_AMIGO_DELETAR.getMensagem());
		
		try {
			emprestimus.desfazerAmizade(id_sessao, pegaStringDaEntrada());
			iuExibirMensagem(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()){
				desfazerAmizade();
			}
		}
	}

	private static void pesquisarItem() {
		String chave, atributo, tipoOrdenacao;
		iuExibirMensagem(Mensagem.PEDIR_CHAVE_BUSCA.getMensagem());
		
		chave = pegaStringDaEntrada();
		iuExibirMensagem(Mensagem.PEDIR_ATRIBUTO.getMensagem());
		
		atributo = pegaStringDaEntrada();
		iuExibirMensagem(Mensagem.PEDIR_TIPO_ORDENACAO.getMensagem());
		
		tipoOrdenacao = pegaStringDaEntrada();
		iuExibirMensagem(Mensagem.PEDIR_CRITERIO_ORDENACAO.getMensagem());
		
		try {
			iuExibirMensagem(emprestimus.pesquisarItem(id_sessao, chave, 
									atributo, tipoOrdenacao, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()){
				pesquisarItem();
			}
		}
	}

	private static void registrarInteresse() {
		iuExibirMensagem(Mensagem.PEDIR_ID_TEM.getMensagem());
		
		try {
			emprestimus.registraInteresse(id_sessao, pegaStringDaEntrada());
			iuExibirMensagem(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()) {
				registrarInteresse();
			}
		}
	}

	private static void incrementarDias() {
		iuExibirMensagem(Mensagem.PEDIR_INCREMENTO_DIAS.getMensagem());
		
		try {
			emprestimus.adicionarDias(pegaStringDaEntrada());
			iuExibirMensagem(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()) {
				incrementarDias();
			}
		}
	}

	private static void requisitarDevolucao() {
		iuExibirMensagem(Mensagem.PEDIR_ID_EMPRESTIMO.getMensagem());
		
		try {
			emprestimus.requisitarDevolucao(id_sessao, pegaStringDaEntrada());
			iuExibirMensagem(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()){
				requisitarDevolucao();
			}
		}
	}

	private static void lerMensagens() {
		iuExibirMensagem(Mensagem.PEDIR_ID_TOPICO.getMensagem());
		
		try {
			iuExibirMensagem(emprestimus.lerMensagens(id_sessao, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()){
				lerMensagens();
			}
		}
	}

	private static void lerTopicos() {
		iuExibirMensagem(Mensagem.PEDIR_TIPO.getMensagem());
		
		try {
			iuExibirMensagem(emprestimus.lerTopicos(id_sessao, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()){
				lerTopicos();
			}
		}
	}

	private static void enviarMensagemNegociacao() {
		iuExibirMensagem(Mensagem.PEDIR_DESTINATARIO.getMensagem());
		
		String destinatario = pegaStringDaEntrada();
		iuExibirMensagem(Mensagem.PEDIR_ASSUNTO.getMensagem());
		
		String assunto = pegaStringDaEntrada();
		iuExibirMensagem(Mensagem.PEDIR_MENSAGEM.getMensagem());
		
		String mensagem = pegaStringDaEntrada();
		iuExibirMensagem(Mensagem.PEDIR_ID_REQUISITACAO_EMPRESTIMO.getMensagem());
		
		try {
			iuExibirMensagem("O id do tópico é: "+
									emprestimus.enviarMensagem(id_sessao, destinatario, 
											assunto, mensagem, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()) {
				enviarMensagemNegociacao();
			}
		}
	}

	private static void enviarMensagemOFFTopic() {
		iuExibirMensagem(Mensagem.PEDIR_DESTINATARIO.getMensagem());
		String destinatario = pegaStringDaEntrada();
		iuExibirMensagem(Mensagem.PEDIR_ASSUNTO.getMensagem());
		String assunto = pegaStringDaEntrada();
		iuExibirMensagem(Mensagem.PEDIR_MENSAGEM.getMensagem());
		try {
			iuExibirMensagem("O id do tópico é: "+
									emprestimus.enviarMensagem(id_sessao, destinatario, 
											assunto, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()) {
				enviarMensagemOFFTopic();
			}
		}
	}

	private static void confirmarTerminoEmprestimo() {
		iuExibirMensagem(Mensagem.PEDIR_ID_EMPRESTIMO.getMensagem());
		
		try {
			emprestimus.confirmarTerminoEmprestimo(id_sessao, pegaStringDaEntrada());
			iuExibirMensagem(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if (tentarNovamente()) {
				confirmarTerminoEmprestimo();
			}
		}
	}

	private static void devolverItem() {
		iuExibirMensagem(Mensagem.PEDIR_ID_EMPRESTIMO.getMensagem());
		
		try {
			emprestimus.devolverItem(id_sessao, pegaStringDaEntrada());
			iuExibirMensagem(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()) {
				devolverItem();
			}
		}
	}

	private static void visualizarEmprestimos() {
		iuExibirMensagem(Mensagem.PEDIR_TIPO_EMPRESTIMO.getMensagem());
		
		try {
			iuExibirMensagem(emprestimus.getEmprestimos(id_sessao, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()) {
				visualizarEmprestimos();
			}
		}
	}

	private static void aprovarEmprestimo() {
		iuExibirMensagem(Mensagem.PEDIR_ID_EMPRESTIMO_APROVAR.getMensagem());
		
		try {
			iuExibirMensagem("ID do emprestimo: "+
								emprestimus.aprovarEmprestimo(id_sessao, 
										pegaStringDaEntrada()));
			iuExibirMensagem(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()) {
				aprovarEmprestimo();
			}
		}
	}

	private static void requisitarEmprestimo() {
		iuExibirMensagem(Mensagem.PEDIR_ID_TEM_VERIFICAR.getMensagem());
		String idItem = pegaStringDaEntrada();
		iuExibirMensagem(Mensagem.PEDIR_DURACAO_EMPRESTIMO.getMensagem());
		
		try {
			iuExibirMensagem("O id do emprestrimo eh "+
									emprestimus.requisitarEmprestimo(id_sessao, 
											idItem, 15));
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if (tentarNovamente()) {
				requisitarEmprestimo();
			}
		}
	}

	private static void getAtributoItem() {
		iuExibirMensagem(Mensagem.PEDIR_ID_TEM_VERIFICAR.getMensagem());
		String idItem = pegaStringDaEntrada();
		iuExibirMensagem(Mensagem.PEDIR_ATRIBUTO.getMensagem());
		
		try {
			iuExibirMensagem(emprestimus.getAtributoItem(idItem, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()) {
				getAtributoItem();
			}
		}
	}

	private static void verItensOutroUsuario() {
		iuExibirMensagem(Mensagem.PEDIR_LOGIN_USUARIO_VERIFICAR.getMensagem());
		
		try {
			iuExibirMensagem(emprestimus.getItens(id_sessao, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()) {
				verItensOutroUsuario();
			}
		}
	}

	private static void verItens() {
		try {
			iuExibirMensagem(emprestimus.getItens(id_sessao));
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
		}
	}

	private static void verAmigosOutroUsuario() {
		iuExibirMensagem(Mensagem.PEDIR_LOGIN_USUARIO_VERIFICAR.getMensagem());
		
		try {
			iuExibirMensagem(emprestimus.getAmigos(id_sessao, pegaStringDaEntrada()));
		} catch (Exception e) {
			
			iuExibirMensagem(e.getMessage());
			if(tentarNovamente()) {
				verAmigosOutroUsuario();
			}
		}
	}

	private static void verAmigos() {
		try {
			iuExibirMensagem(emprestimus.getAmigos(id_sessao));
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
		}
	}

	private static void verificarAmizade() {
		iuExibirMensagem(Mensagem.PEDIR_LOGIN_AMIGO_VERIFICAR.getMensagem());
		
		try {
			if (emprestimus.ehAmigo(id_sessao, pegaStringDaEntrada()).equalsIgnoreCase("true")) {
				iuExibirMensagem(Mensagem.INFO_SAO_AMIGOS.getMensagem());
				
			} else {
				iuExibirMensagem(Mensagem.INFO_NAO_SAO_AMIGOS.getMensagem());
			}
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if (tentarNovamente()) {
				verificarAmizade();
			}
		}
	}

	private static void aprovarAmizade() {
		iuExibirMensagem(Mensagem.PEDIR_LOGIN_AMIGO_APROVAR.getMensagem());
		
		try {
			emprestimus.aprovarAmizade(id_sessao, pegaStringDaEntrada());
			iuExibirMensagem(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()) {
				aprovarAmizade();
			}
		}
	}

	private static void visualizarRequisitacoesAmizade() {
		try {
			iuExibirMensagem(emprestimus.getRequisicoesDeAmizade(id_sessao));
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
		}
	}

	private static void requisitarAmizade() {
		iuExibirMensagem(Mensagem.PEDIR_LOGIN_AMIGO_ADICIONAR.getMensagem());
		
		try {
			emprestimus.requisitarAmizade(id_sessao, pegaStringDaEntrada());
			iuExibirMensagem(Mensagem.INFO_AGUARDE_APROVACAO_AMIZADE.getMensagem());
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()){
				requisitarAmizade();
			}
		}
	}

	private static void localizarUsuarioPalavraChave() {
		String chave, atributo;
		iuExibirMensagem(Mensagem.PEDIR_CHAVE_BUSCA.getMensagem());
		
		chave = pegaStringDaEntrada();
		iuExibirMensagem(Mensagem.PEDIR_ATRIBUTO.getMensagem());
		
		atributo = pegaStringDaEntrada();
		
		try {
			iuExibirMensagem(emprestimus.localizarUsuario(id_sessao, chave, atributo));
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if (tentarNovamente()) {
				localizarUsuarioPalavraChave();
			}
		}
	}

	private static void localizarUsuario() {
		try {
			iuExibirMensagem(emprestimus.localizarUsuario(id_sessao));
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			if (tentarNovamente()) {
				localizarUsuario();
			}
		}
	}
	
	private static void cadastrarItem() {
		String nome, descricao, categoria;
		iuExibirMensagem(Mensagem.PEDIR_NOME_ITEM_CADASTRAR.getMensagem());
		
		nome = pegaStringDaEntrada();
		iuExibirMensagem(Mensagem.PEDIR_DESCRICAO_ITEM_CADASTRAR.getMensagem());
		
		descricao = pegaStringDaEntrada();
		iuExibirMensagem(Mensagem.PEDIR_CATEGORIA_ITEM_CADASTRAR.getMensagem());
		
		categoria = pegaStringDaEntrada();
		
		try {
			iuExibirMensagem("O id do item eh: "+
									emprestimus.cadastrarItem(id_sessao, nome, 
											descricao, categoria));
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()) {
				cadastrarItem();
			}
		}
	}

	private static void getAtributo() {
		iuExibirMensagem(Mensagem.PEDIR_ATRIBUTO.getMensagem());
		
		try {
			iuExibirMensagem(emprestimus.getAtributoUsuario(login_logado, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()){
				getAtributo();
			}
		}
	}

	private static void getAtributoOutroUsuario() {
		iuExibirMensagem(Mensagem.PEDIR_LOGIN_USUARIO.getMensagem());
		String login = pegaStringDaEntrada();
		iuExibirMensagem(Mensagem.PEDIR_ATRIBUTO.getMensagem());
		
		try {
			iuExibirMensagem(emprestimus.getAtributoUsuario(login, pegaStringDaEntrada()));
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			if(tentarNovamente()){
				getAtributo();
			}
		}
	}
	
	private static void criarUsuario() {
		String login, nome, endereco;
		iuExibirMensagem(Mensagem.LOGIN_PARA_CADASTRAR.getMensagem());
		
		login = pegaStringDaEntrada();
		iuExibirMensagem(Mensagem.NOME_PARA_CADASTRAR.getMensagem());
		
		nome = pegaStringDaEntrada();
		iuExibirMensagem(Mensagem.ENDERECO_PARA_CADASTRAR.getMensagem());
		
		endereco = pegaStringDaEntrada();
		
		try {
			emprestimus.criarUsuario(login, nome, endereco);
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if (!tentarNovamente()) {
				menuDeslogado();
				
			} else {
				criarUsuario();
			}
		}
	}
	
	private static int pegarOpcao(int primeira_opcao, int ultima_opcao) {
		int entrada;
		try {
			entrada = Integer.parseInt(pegaStringDaEntrada());
			
			if ( !((entrada >= primeira_opcao) && (entrada <= ultima_opcao)) ) {
				throw new Exception();
				
			} else {
				return entrada;
			}
			
		} catch (Exception e) {
			iuExibirMensagem("Você deve digitar um numero inteiro entre "+primeira_opcao+"e "+ultima_opcao);
			System.out.print("\nDigite novamente: ");
			
			return pegarOpcao(primeira_opcao, ultima_opcao);
		}	
	}
	
	private static String pegaStringDaEntrada() {
		Scanner sc = new Scanner(System.in);
		while(!sc.hasNext()) {}
		return sc.next();
	}
	
	private static boolean tentarNovamente() {
		final int UM = 1;
		
		try {
			iuExibirMensagem(Mensagem.PERGUNTAR_TENTAR_NOVAMENTE.getMensagem());
			return (Integer.parseInt(pegaStringDaEntrada()) == UM);
			
		} catch (Exception e) {
			System.out.print("Opção Inválida. \n" +
							 "Digite novamente: ");
			return tentarNovamente();
		}
	}

	private static void iuExibirMensagem(String mensagem) {
		System.out.println(mensagem);
	}
	
	private static void visualizarHistorico() {
		try {
			iuExibirMensagem(emprestimus.historicoAtividades(id_sessao));
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			if (tentarNovamente()) {
				visualizarHistorico();
			}
		}
	}
	
	private static void visualizarHistorico_timeLine() {
		try {
			iuExibirMensagem(emprestimus.historicoAtividades(id_sessao));
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			if (tentarNovamente()) {
				visualizarHistorico_timeLine();
			}
		}
	}
	
	private static void oferecerItem() {
		String id_publ;
		
		iuExibirMensagem(Mensagem.PEDIR_ID_PUBLICACAO_PEDIDO.getMensagem());
		id_publ = pegaStringDaEntrada();

		iuExibirMensagem(Mensagem.PEDIR_ID_ITEM.getMensagem());
		
		try {
			emprestimus.oferecerItem(id_sessao, id_publ, pegaStringDaEntrada());
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()) {
				oferecerItem();
			}
		}
	}
	
	private static void publicarPedido() {
		String nome;
		
		iuExibirMensagem(Mensagem.PEDIR_NOME_ITEM.getMensagem());
		nome = pegaStringDaEntrada();

		iuExibirMensagem(Mensagem.PEDIR_DESCRICAO_ITEM.getMensagem());
		
		try {
			iuExibirMensagem("O id da publicação do pedido eh: "+
									emprestimus.publicarPedido(id_sessao, nome, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()) {
				publicarPedido();
			}
		}
	}
	
	private static void republicarPedido() {
		iuExibirMensagem(Mensagem.PEDIR_ID_PUBLICACAO_PEDIDO.getMensagem());
		try {
			emprestimus.republicarPedido(id_sessao, pegaStringDaEntrada());
		} catch (Exception e) {
			iuExibirMensagem(e.getMessage());
			
			if(tentarNovamente()) {
				republicarPedido();
			}
		}
	}
}
