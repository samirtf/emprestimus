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
		Scanner sc = new Scanner(System.in);
		int opcao;

		System.out.println(Mensagem.BOAS_VINDAS.getMensagem());
		
		System.out.println("\n\n" + Mensagem.MENU.getMensagem());
		System.out.println(Mensagem.PEDIR_OPCAO.getMensagem());
		
		opcao = pegarOpcao(1, 3);

		// Reage segundo a opcao dada na entrada.
		switch (opcao) {
		case 1:
			logar();
			break;
			
		case 2:
			criarUsuario();
			break;
			
		case 3:
			System.out.println(Mensagem.ADEUS.getMensagem());
			emprestimus.encerrarSistema();
			break;
			
		default:
			menuDeslogado();
			break;
		}
		
		menuDeslogado();
		
	}

	private static void logar() {
		System.out.println(Mensagem.PEDIR_LOGIN.getMensagem());
		try {
			login_logado = pegaStringDaEntrada();
			id_sessao = emprestimus.abrirSessao(login_logado);
			menuLogado();
		} catch (Exception e) {
			id_sessao = null;
			login_logado = null;
			System.out.println(e.getMessage());
			if(!tentarNovamente()){
				menuDeslogado();
			}
			logar();
		}
	}

	private static void menuLogado() {
		int opcao;
		System.out.println(Mensagem.MENU_LOGADO.getMensagem());
		opcao = pegarOpcao(1, 15);
		switch (opcao) {
		case 1:
			getAtributo();
			break;
			
		case 2:
			cadastrarItem();
			break;
			
		case 3:
			// FIXME sem implentacao. Como passar idItem?
			break;
			
		case 4:
			localizarUsuario();
			break;
	
		case 5:
			requisitarAmizade();
			break;
			
		case 6:
			visualizarRequisitacoesAmizade();
			break;
	
		case 7:
			aprovarAmizade();
			break;
			
		case 8:
			verificarAmizade();
			break;
			
		case 15:
			emprestimus.encerrarSistema();
			break;
			
		default:
			menuLogado();
			break;
		}
		menuLogado();
	}

	private static void verificarAmizade() {
		System.out.println(Mensagem.PEDIR_LOGIN_AMIGO_VERIFICAR.getMensagem());
		try {
			if (emprestimus.ehAmigo(id_sessao, pegaStringDaEntrada()).equalsIgnoreCase("true")) {
				System.out.println(Mensagem.INFO_SAO_AMIGOS.getMensagem());
			} else {
				System.out.println(Mensagem.INFO_NAO_SAO_AMIGOS.getMensagem());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (tentarNovamente()) {
				verificarAmizade();
			}
		}
	}

	private static void aprovarAmizade() {
		System.out.println(Mensagem.PEDIR_LOGIN_AMIGO_APROVAR.getMensagem());
		try {
			emprestimus.aprovarAmizade(id_sessao, pegaStringDaEntrada());
			System.out.println(Mensagem.OPERACAO_SUCESSO.getMensagem());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if(tentarNovamente()) {
				aprovarAmizade();
			}
		}
	}

	private static void visualizarRequisitacoesAmizade() {
		try {
			System.out.println(emprestimus.getRequisicoesDeAmizade(id_sessao));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			menuLogado();
		}
	}

	private static void requisitarAmizade() {
		System.out.println(Mensagem.PEDIR_LOGIN_AMIGO_ADICIONAR.getMensagem());
		try {
			emprestimus.requisitarAmizade(id_sessao, pegaStringDaEntrada());
			System.out.println(Mensagem.INFO_AGUARDE_APROVACAO_AMIZADE.getMensagem());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if(tentarNovamente()){
				requisitarAmizade();
			}
		}
	}

	private static void localizarUsuario() {
		String chave, atributo;
		System.out.println(Mensagem.PEDIR_CHAVE_BUSCA.getMensagem());
		chave = pegaStringDaEntrada();
		System.out.println(Mensagem.PERGUNTAR_ATRIBUTO.getMensagem());
		atributo = pegaStringDaEntrada();
		try {
			System.out.println(emprestimus.localizarUsuario(id_sessao, chave, atributo));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if (tentarNovamente()) {
				localizarUsuario();
			}
		}
	}

	private static void cadastrarItem() {
		String nome, descricao, categoria;
		System.out.println(Mensagem.PEDIR_NOME_ITEM_CADASTRAR.getMensagem());
		nome = pegaStringDaEntrada();
		System.out.println(Mensagem.PEDIR_DESCRICAO_ITEM_CADASTRAR.getMensagem());
		descricao = pegaStringDaEntrada();
		System.out.println(Mensagem.PEDIR_CATEGORIA_ITEM_CADASTRAR.getMensagem());
		categoria = pegaStringDaEntrada();
		
		try {
			emprestimus.cadastrarItem(id_sessao, nome, descricao, categoria);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if(tentarNovamente()) {
				cadastrarItem();
			}
		}
	}

	private static void getAtributo() {
		System.out.println(Mensagem.PERGUNTAR_ATRIBUTO.getMensagem());
		try {
			System.out.println(emprestimus.getAtributoUsuario(login_logado, pegaStringDaEntrada()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if(tentarNovamente()){
				getAtributo();
			}
		}
	}

	private static void criarUsuario() {
		String login, nome, endereco;
		System.out.println(Mensagem.LOGIN_PARA_CADASTRAR.getMensagem());
		login = pegaStringDaEntrada();
		System.out.println(Mensagem.NOME_PARA_CADASTRAR.getMensagem());
		nome = pegaStringDaEntrada();
		System.out.println(Mensagem.ENDERECO_PARA_CADASTRAR.getMensagem());
		endereco = pegaStringDaEntrada();
		try {
			emprestimus.criarUsuario(login, nome, endereco);
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
			System.out.println("Você deve digitar um numero inteiro entre "+primeira_opcao+"e "+ultima_opcao);
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
		try {
			System.out.println(Mensagem.PERGUNTAR_TENTAR_NOVAMENTE.getMensagem());
			return (Integer.parseInt(pegaStringDaEntrada()) == 1);
		} catch (Exception e) {
			System.out.print("Opção Inválida. \n" +
							 "Digite novamente: ");
			return tentarNovamente();
		}
	}
	
}

