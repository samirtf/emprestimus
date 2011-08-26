package codigo.utilitarios;

import codigo.enums.Mensagem;

/**
 * Esta classe eh utilizada para validar Strings,
 * 		possibilitando tanto a realizacao de testes especificos quanto completos
 * 		(testar Strings nulas, vazias e compostas apenas por espacos).
 * 
 * @author Joeffison Silverio de Andrade, 21011853, joeffisonsa@gmail.com
 * @version 1.0
 * @since 1.0
 */
public class ValidadorString {
	private static String inicioPadraoMensagemErro = Mensagem.INICIO_PADRAO_MENSAGEM_ERRO.getMensagem();

	/**
	 * Submete a {@link String} apresentada a todos os testes desta classe, para verificar se ela eh nula, vazia ou se contem apenas caracteres de espaco.
	 * 
	 * @param inicioMensagemErro
	 * 		Inicio desejado para a mensagem de erro, que sera substituido pelo padrao, caso nao atenda aos requisitos basicos necessarios (passar nos testes desta mesma classe)
	 * @param StringParaTestar
	 * 		{@link String} que devera ser checada
	 * @return
	 * 		OK, caso nenhum problema seja detectado, ou uma mensagem de erro especifica, caso necessario.
	 */
	public static String validaString(String inicioMensagemErro, String StringParaTestar) {
		String mensagemErro = ehNull(inicioMensagemErro, StringParaTestar);
		if (!mensagemErro.equals(Mensagem.OK.getMensagem())) {
			return mensagemErro;
		}
		mensagemErro = ehBranco(inicioMensagemErro, StringParaTestar);
		if (!mensagemErro.equals(Mensagem.OK.getMensagem())) {
			return mensagemErro;
		}
		mensagemErro = somenteEspacos(inicioMensagemErro, StringParaTestar);
		if (!mensagemErro.equals(Mensagem.OK.getMensagem())) {
			return mensagemErro;
		}
		return Mensagem.OK.getMensagem();
	}
	
	/**
	 * Submete a {@link String} apresentada a todos os testes desta classe, para verificar se ela eh nula, vazia ou se contem apenas caracteres de espaco.
	 * 
	 * @param StringParaTestar
	 * 		{@link String} que devera ser checada
	 * @return
	 * 		OK, caso nenhum problema seja detectado, ou uma mensagem de erro especifica, caso necessario.
	 */
	public static String validaString(String StringParaTestar) {
		return validaString(inicioPadraoMensagemErro, StringParaTestar);
	}
	
	/**
	 * Verifica se a {@link String} informada eh nula.
	 * 
	 * @param inicioMensagemErro
	 * 		Inicio desejado para a mensagem de erro, que sera sera descartado caso nao passe nos testes desta classe
	 * @param StringParaTestar
	 * 		{@link String} que sera analisada
	 * @return
	 * 		OK, caso nao seja nula, ou uma mensagem de erro, caso seja.
	 */
	public static String ehNull(String inicioMensagemErro, String StringParaTestar) {
		if (!inicioPadraoMensagemErro.equals(inicioMensagemErro)) {
			inicioMensagemErro = validaInicioMensagemErro(inicioMensagemErro);
		}
		if (StringParaTestar == null) {
			return inicioMensagemErro + " nao pode ser nul" + pegaArtigoDefinido(inicioMensagemErro) + "!";
		}
		return Mensagem.OK.getMensagem();
	}
	
	/**
	 * Verifica se a {@link String} informada eh nula.
	 * 
	 * @param inicioMensagemErro
	 * 		Inicio desejado para a mensagem de erro, que sera sera descartado caso nao passe nos testes desta classe
	 * @param StringParaTestar
	 * 		{@link String} que sera analisada
	 * @return
	 * 		OK, caso nao seja nula, ou uma mensagem de erro, caso seja.
	 */
	public static String ehNull(String StringParaTestar) {
		return ehNull(inicioPadraoMensagemErro, StringParaTestar);
	}
	
	
	/**
	 * Verifica se a string esta vazia
	 * 
	 * @param StringParaTestar
	 * 		String que devera ser verificada
	 * @return
	 * 		OK, caso a string nao seja vazia, ou uma mensagem de erro, caso ela seja
	 */
	public static String ehBranco(String inicioMensagemErro, String StringParaTestar) {
		if (!inicioPadraoMensagemErro.equals(inicioMensagemErro)) {
			inicioMensagemErro = validaInicioMensagemErro(inicioMensagemErro);
		}
		if (StringParaTestar.isEmpty()) {
			return inicioMensagemErro + " nao pode ser vazi" + pegaArtigoDefinido(inicioMensagemErro) + "!";
		}
		return Mensagem.OK.getMensagem();
	}
	
	/**
	 * Verifica se a string esta vazia
	 * 
	 * @param StringParaTestar
	 * 		String que devera ser verificada
	 * @return
	 * 		OK, caso a string nao seja vazia, ou uma mensagem de erro, caso ela seja
	 */
	public static String ehBranco(String StringParaTestar) {
		return ehBranco(inicioPadraoMensagemErro, StringParaTestar);
	}
	
	
	/**
	 * Verifica se a string contem exclusivamente espacos
	 * 
	 * @param StringParaTestar
	 * 		String que devera ser verificada
	 * @return
	 * 		OK, caso a string contenha algum caractere diferente de espaco,
	 * 				ou uma mensagem de erro, caso ela apresente apenas espacos.
	 */
	public static String somenteEspacos(String inicioMensagemErro, String StringParaTestar) {
		if (!inicioPadraoMensagemErro.equals(inicioMensagemErro)) {
			inicioMensagemErro = validaInicioMensagemErro(inicioMensagemErro);
		}
		if (StringParaTestar.trim().isEmpty()) {
			return inicioMensagemErro + " nao pode conter apenas espacos!";
		}
		return Mensagem.OK.getMensagem();
	}
	
	/**
	 * Verifica se a string contem exclusivamente espacos
	 * 
	 * @param StringParaTestar
	 * 		String que devera ser verificada
	 * @return
	 * 		OK, caso a string contenha algum caractere diferente de espaco,
	 * 				ou uma mensagem de erro, caso ela apresente apenas espacos.
	 */
	public static String somenteEspacos(String StringParaTestar) {
		return somenteEspacos(inicioPadraoMensagemErro, StringParaTestar);
	}
	
	
	// A partir deste ponto, os metodos sao de baixa importancia,
	// sao usados por pura estetica da mensagem de erro retornada,
	// portanto, podem ser ignorados, sem afetar o entendimento desta classe e seus metodos,
	// rendendo, consequentemente, um bom tempo de leitura
	
	
	//Este metodo nao tem muita importancia, podendo ser ignorado sem afetar o entendimento desta classe
	/**
	 * Verifica qual o artigo definido que deve ser associado a {@link String}.
	 * 
	 * @param s
	 * 		String que devera ser analisada
	 * 
	 * @return
	 * 		O artigo definido que se encaixa melhor com a String, o default e o artigo definido o
	 */
	private static Character pegaArtigoDefinido(String s) {
		Character a = "a".charAt(0);
		Character o = "o".charAt(0);
		Character artDef = o;
		String[] palavras = s.split(" ");
		String primeiraPalavra = palavras[0].toLowerCase();
		String ultimaPalavra = palavras[palavras.length - 1].toLowerCase();
		Character ultimaLetra = primeiraPalavra.charAt(primeiraPalavra.length() - 1);
		
		if (primeiraPalavra.toLowerCase().equals("este")) {
			ultimaLetra = o;
		} else if (a.equals(ultimaLetra) || o.equals(ultimaLetra)) {
			artDef = ultimaLetra;
		} else if (!primeiraPalavra.equals(ultimaPalavra)) {
			ultimaLetra = ultimaPalavra.charAt(ultimaPalavra.length() - 1);
			if (a.equals(ultimaLetra) || o.equals(ultimaLetra)) {
				artDef = ultimaLetra;
			}
		}
		return artDef;
	}

	// Este metodo serve apenas para a estetica da String de saida, no caso da string testada seja invalida,
	// podendo ser ignorado.
	/**
	 * Verifica a consistencia do inicio da mensagem de erro, podendo ser substituido pelo inicio padrao, caso necessario.
	 * 
	 * @param inicioMensagemErro
	 * 		Inicio informado para a mensagem de erro, para ter sua consistencia analisada
	 * 
	 * @return
	 * 		O parametro recebido, caso este seja um inicio valido para a mensagem de erro, ou o inicio padrao, caso contrario
	 */
	private static String validaInicioMensagemErro(String inicioMensagemErro) {
		if (inicioMensagemErro == null) {
			return inicioPadraoMensagemErro;
		} else if (inicioMensagemErro.equals(inicioPadraoMensagemErro)) {
			return inicioMensagemErro;
		} else if (validaString(inicioMensagemErro).equals(Mensagem.OK.getMensagem())) {
			return inicioMensagemErro;
		}
		return inicioPadraoMensagemErro;
	}
	
	/**
	 * Retorna a string passada como parametro, se esta for valida,
	 * ou lanca uma excecao personalizada, caso a string passada seja invalida.
	 * 
	 * @param inicioMensagemErro
	 * 			Este parametro serve, unicamente, diante da necessidade de se personalizar
	 * a mensagem de erro retornada, com o nome do tipo de string passada para ser testada.
	 * @param minhaString
	 * 			String que deve ser validada
	 * @return
	 * 			Retorna a string validada, desde que esta passe pelos testes
	 * @throws Exception
	 * 			Lanca uma excecao, caso a string passada nao seja aprovada
	 */
	public static String pegaString(String inicioMensagemErro, String minhaString) throws IllegalArgumentException {
        String teste = ValidadorString.validaString(inicioMensagemErro, minhaString);
        if (!teste.equals(Mensagem.OK.getMensagem())) {
            throw new IllegalArgumentException(teste);
        }
        return minhaString;
    }
	
	/**
	 * Retorna a string passada como parametro, se esta for valida,
	 * ou lanca uma excecao padrao, caso a string passada seja invalida.
	 * 
	 * @param minhaString
	 * 			String que deve ser validada
	 * @return
	 * 			Retorna a string validada, desde que esta passe pelos testes
	 * @throws Exception
	 * 			Lanca uma excecao, caso a string passada nao seja aprovada
	 */
	public static String pegaString(String minhaString) throws IllegalArgumentException {
        return pegaString(Mensagem.INICIO_PADRAO_MENSAGEM_ERRO.getMensagem(), minhaString);
    }
	
}