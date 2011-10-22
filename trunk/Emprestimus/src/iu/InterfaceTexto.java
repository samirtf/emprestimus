package iu;

import java.util.Scanner;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;
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
		//Scanner já é implementado na funcao interior a pegarOpcao.
		Scanner sc = new Scanner(System.in);
		int opcao;

		System.out.println(Mensagem.BOAS_VINDAS.getMensagem());
		System.out.println("\n\n" + Mensagem.MENU.getMensagem());
		System.out.println(Mensagem.PEDIR_OPCAO.getMensagem());
		
		opcao = pegarOpcao(1, 4);

		// Reage segundo a opcao dada na entrada.
		//FIXME: Ulisses vai fazer isto!
		//TODO: retirar numero magico aqui.
		switch (opcao) {
		case 1:
			logar();
			break;
			
		case 2:
			criarUsuario();
			break;
			
		case 3:
			emprestimus.zerarSistema();
			break;
			
		case 4:
			System.out.println(Mensagem.ADEUS.getMensagem());
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
		
		opcao = pegarOpcao(1, 29);
		//FIXME: Ulisses vai fazer isto!
		//TODO: retirar numero magico aqui.
		switch (opcao) {
		case 1:
			getAtributo();
			break;
			
		case 2:
			cadastrarItem();
			break;
			
		case 3:
			getAtributoItem();
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
			
		case 9:
			verAmigos();
			break;
			
		case 10:
			verAmigosOutroUsuario();
			break;
			
		case 11:
			verItens();
			break;
			
		case 12:
			verItensOutroUsuario();
			break;
			
		case 13:
			requisitarEmprestimo();
			break;
			
		case 14:
			aprovarEmprestimo();
			break;
			
		case 15:
			visualizarEmprestimos();
			break;
			
		case 16:
			devolverItem();
			break;
			
		case 17:
			confirmarTerminoEmprestimo();
			break;
			
		case 18:
			enviarMensagemOFFTopic();
			break;
			
		case 19:
			enviarMensagemNegociacao();
			break;
			
		case 20:
			lerTopicos();
			break;
			
		case 21:
			lerMensagens();
			break;
			
		case 22:
			requisitarDevolucao();
			break;
			
		case 23:
			incrementarDias();
			break;
			
		case 24:
			registrarInteresse();
			break;
		
		case 25:
			pesquisarItem();
			break;
			
		case 26:
			desfazerAmizade();
			break;
			
		case 27:
			apagarItem();
			break;
			
		case 28:
			verRanking();
			break;
			
		case 29:
			System.out.println("Saindo... \n\n\n");
			menuDeslogado();
			break;
			
		default:
			menuLogado();
			break;
		}
		menuLogado();
	}

	
	private static void verRanking() {
		System.out.println(Mensagem.PEDIR_CATEGORIA.getMensagem());
		
		try {
			System.out.println(emprestimus.getRanking(id_sessao, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()){
				verRanking();
			}
		}
	}

	private static void apagarItem() {
		System.out.println(Mensagem.PEDIR_ID_TEM.getMensagem());
		
		try {
			emprestimus.apagarItem(id_sessao, pegaStringDaEntrada());
			System.out.println(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()){
				apagarItem();
			}
		}
	}

	private static void desfazerAmizade() {
		System.out.println(Mensagem.PEDIR_LOGIN_AMIGO_DELETAR.getMensagem());
		
		try {
			emprestimus.desfazerAmizade(id_sessao, pegaStringDaEntrada());
			System.out.println(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()){
				desfazerAmizade();
			}
		}
	}

	private static void pesquisarItem() {
		String chave, atributo, tipoOrdenacao;
		System.out.println(Mensagem.PEDIR_CHAVE_BUSCA.getMensagem());
		
		chave = pegaStringDaEntrada();
		System.out.println(Mensagem.PEDIR_ATRIBUTO.getMensagem());
		
		atributo = pegaStringDaEntrada();
		System.out.println(Mensagem.PEDIR_TIPO_ORDENACAO.getMensagem());
		
		tipoOrdenacao = pegaStringDaEntrada();
		System.out.println(Mensagem.PEDIR_CRITERIO_ORDENACAO.getMensagem());
		
		try {
			System.out.println(emprestimus.pesquisarItem(id_sessao, chave, 
									atributo, tipoOrdenacao, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()){
				pesquisarItem();
			}
		}
	}

	private static void registrarInteresse() {
		System.out.println(Mensagem.PEDIR_ID_TEM.getMensagem());
		
		try {
			emprestimus.registraInteresse(id_sessao, pegaStringDaEntrada());
			System.out.println(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()) {
				registrarInteresse();
			}
		}
	}

	private static void incrementarDias() {
		System.out.println(Mensagem.PEDIR_INCREMENTO_DIAS.getMensagem());
		
		try {
			emprestimus.adicionarDias(pegaStringDaEntrada());
			System.out.println(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()) {
				incrementarDias();
			}
		}
	}

	private static void requisitarDevolucao() {
		System.out.println(Mensagem.PEDIR_ID_EMPRESTIMO.getMensagem());
		
		try {
			emprestimus.requisitarDevolucao(id_sessao, pegaStringDaEntrada());
			System.out.println(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()){
				requisitarDevolucao();
			}
		}
	}

	private static void lerMensagens() {
		System.out.println(Mensagem.PEDIR_ID_TOPICO.getMensagem());
		
		try {
			System.out.println(emprestimus.lerMensagens(id_sessao, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()){
				lerMensagens();
			}
		}
	}

	private static void lerTopicos() {
		System.out.println(Mensagem.PEDIR_TIPO.getMensagem());
		
		try {
			System.out.println(emprestimus.lerTopicos(id_sessao, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()){
				lerTopicos();
			}
		}
	}

	private static void enviarMensagemNegociacao() {
		System.out.println(Mensagem.PEDIR_DESTINATARIO.getMensagem());
		
		String destinatario = pegaStringDaEntrada();
		System.out.println(Mensagem.PEDIR_ASSUNTO.getMensagem());
		
		String assunto = pegaStringDaEntrada();
		System.out.println(Mensagem.PEDIR_MENSAGEM.getMensagem());
		
		String mensagem = pegaStringDaEntrada();
		System.out.println(Mensagem.PEDIR_ID_REQUISITACAO_EMPRESTIMO.getMensagem());
		
		try {
			System.out.println("O id do tópico é: "+
									emprestimus.enviarMensagem(id_sessao, destinatario, 
											assunto, mensagem, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()) {
				enviarMensagemNegociacao();
			}
		}
	}

	private static void enviarMensagemOFFTopic() {
		System.out.println(Mensagem.PEDIR_DESTINATARIO.getMensagem());
		String destinatario = pegaStringDaEntrada();
		System.out.println(Mensagem.PEDIR_ASSUNTO.getMensagem());
		String assunto = pegaStringDaEntrada();
		System.out.println(Mensagem.PEDIR_MENSAGEM.getMensagem());
		try {
			System.out.println("O id do tópico é: "+
									emprestimus.enviarMensagem(id_sessao, destinatario, 
											assunto, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()) {
				enviarMensagemOFFTopic();
			}
		}
	}

	private static void confirmarTerminoEmprestimo() {
		System.out.println(Mensagem.PEDIR_ID_EMPRESTIMO.getMensagem());
		
		try {
			emprestimus.confirmarTerminoEmprestimo(id_sessao, pegaStringDaEntrada());
			System.out.println(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if (tentarNovamente()) {
				confirmarTerminoEmprestimo();
			}
		}
	}

	private static void devolverItem() {
		System.out.println(Mensagem.PEDIR_ID_EMPRESTIMO.getMensagem());
		
		try {
			emprestimus.devolverItem(id_sessao, pegaStringDaEntrada());
			System.out.println(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()) {
				//TODO: Adicionar acao ao tentar novamente()?
			}
		}
	}

	private static void visualizarEmprestimos() {
		System.out.println(Mensagem.PEDIR_TIPO_EMPRESTIMO.getMensagem());
		
		try {
			System.out.println(emprestimus.getEmprestimos(id_sessao, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()) {
				visualizarEmprestimos();
			}
		}
	}

	private static void aprovarEmprestimo() {
		System.out.println(Mensagem.PEDIR_ID_EMPRESTIMO_APROVAR.getMensagem());
		
		try {
			System.out.println("ID do emprestimo: "+
								emprestimus.aprovarEmprestimo(id_sessao, 
										pegaStringDaEntrada()));
			System.out.println(Mensagem.OPERACAO_SUCESSO.getMensagem());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()) {
				aprovarEmprestimo();
			}
		}
	}

	private static void requisitarEmprestimo() {
		System.out.println(Mensagem.PEDIR_ID_TEM_VERIFICAR.getMensagem());
		String idItem = pegaStringDaEntrada();
		System.out.println(Mensagem.PEDIR_DURACAO_EMPRESTIMO.getMensagem());
		
		try {
			System.out.println("O id do emprestrimo eh "+
									emprestimus.requisitarEmprestimo(id_sessao, 
											idItem, 15));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if (tentarNovamente()) {
				requisitarEmprestimo();
			}
		}
	}

	private static void getAtributoItem() {
		//FIXME: sem implentacao. Como passar idItem?
		//TODO: talvez imprimir o id dos itens cadastrados.
		System.out.println(Mensagem.PEDIR_ID_TEM_VERIFICAR.getMensagem());
		String idItem = pegaStringDaEntrada();
		System.out.println(Mensagem.PEDIR_ATRIBUTO.getMensagem());
		
		try {
			System.out.println(emprestimus.getAtributoItem(idItem, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()) {
				getAtributoItem();
			}
		}
	}

	private static void verItensOutroUsuario() {
		System.out.println(Mensagem.PEDIR_LOGIN_USUARIO_VERIFICAR.getMensagem());
		
		try {
			System.out.println(emprestimus.getItens(id_sessao, pegaStringDaEntrada()));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()) {
				verItensOutroUsuario();
			}
		}
	}

	private static void verItens() {
		try {
			System.out.println(emprestimus.getItens(id_sessao));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private static void verAmigosOutroUsuario() {
		System.out.println(Mensagem.PEDIR_LOGIN_USUARIO_VERIFICAR.getMensagem());
		
		try {
			System.out.println(emprestimus.getAmigos(id_sessao, pegaStringDaEntrada()));
		} catch (Exception e) {
			
			System.out.println(e.getMessage());
			if(tentarNovamente()) {
				verAmigosOutroUsuario();
			}
		}
	}

	private static void verAmigos() {
		try {
			System.out.println(emprestimus.getAmigos(id_sessao));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
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
		System.out.println(Mensagem.PEDIR_ATRIBUTO.getMensagem());
		
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
			System.out.println("O id do item eh: "+
									emprestimus.cadastrarItem(id_sessao, nome, 
											descricao, categoria));
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			
			if(tentarNovamente()) {
				cadastrarItem();
			}
		}
	}

	private static void getAtributo() {
		System.out.println(Mensagem.PEDIR_ATRIBUTO.getMensagem());
		
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

			//FIXME: Ulisses vai fazer isto!
			//TODO: retirar numero magico aqui.
			return (Integer.parseInt(pegaStringDaEntrada()) == 1);
			
		} catch (Exception e) {
			System.out.print("Opção Inválida. \n" +
							 "Digite novamente: ");
			return tentarNovamente();
		}
	}
	
}

