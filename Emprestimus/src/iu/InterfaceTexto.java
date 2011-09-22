package iu;

import java.util.Scanner;

import sistema.utilitarios.Mensagem;

public class InterfaceTexto {
	
	private static EmprestimusIF emprestimus;
	
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
		String login_logado;
		System.out.println(Mensagem.PEDIR_LOGIN.getMensagem());
		try {
			login_logado = pegaStringDaEntrada();
			emprestimus.abrirSessao(login_logado);
			menuLogado(login_logado);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if(!tentarNovamente()){
				menuDeslogado();
			}
			logar();
		}
	}

	private static void menuLogado(String login_logado) {
		int opcao;
		System.out.println(Mensagem.MENU_LOGADO.getMensagem());
		opcao = pegarOpcao(1, 8);
		switch (opcao) {
		case 7:
			getAtributo(login_logado);
			break;
			
		case 8:
			emprestimus.encerrarSistema();
			break;
			
		default:
			menuLogado(login_logado);
			break;
		}
		menuLogado(login_logado);
	}

	private static void getAtributo(String login_logado) {
		System.out.println(Mensagem.PERGUNTAR_ATRIBUTO.getMensagem());
		try {
			System.out.println(emprestimus.getAtributoUsuario(login_logado, pegaStringDaEntrada()));
		} catch (Exception e) {
			System.out.println(e.getMessage());
			if(!tentarNovamente()){
				menuLogado(login_logado);
			}
			getAtributo(login_logado);
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

