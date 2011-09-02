package sistema.utilitarios;

import java.util.ArrayList;
import java.util.List;

/**
 * Esta classe eh utilizada para validar Strings,
 * 		possibilitando tanto a realizacao de testes especificos quanto completos
 * 		(testar Strings nulas, vazias e compostas apenas por espacos).
 * 
 * @author Joeffison Silverio de Andrade, 21011853, joeffisonsa@gmail.com
 * @version 1.9
 * @since 1.0
 */
public class ValidadorString {
	private static List<String> mensagens_de_erro_padroes = null;
	{
		String [] array = {"Este dado nao pode ser nulo!", "Este dado nao pode ser vazio!", "Este dado nao pode conter apenas espacos!"};
		if (mensagens_de_erro_padroes == null) mensagens_de_erro_padroes = toList(array);
	}
	
	private static List<String> mensagens_de_erro_padroes_login = null;
	{
		String [] array = {"Este dado nao pode ser nulo!",
					 	   "Este dado nao pode ser vazio!",
						   "Este dado nao pode conter apenas espacos!," +
						   "Este dado nao pode conter espacos!"};
		
		if (mensagens_de_erro_padroes_login == null) mensagens_de_erro_padroes_login = toList(array);
	}
	
	
	
	/**
	 * Submete a {@link String} apresentada a todos os testes desta classe, para verificar se ela eh nula, vazia ou se contem apenas caracteres de espaco.
	 * 
	 * @param MensagensDeErro
	 * 		List de {@link String} de tamanho 3,
	 * 			onde os itens equivalem as mensagens de quando a String eh nula, vazia ou so contem espacos, respectivamente. 
	 * @param StringParaTestar
	 * 		{@link String} que devera ser checada
	 * @return
	 * 		OK, caso nenhum problema seja detectado, ou uma mensagem de erro especifica, caso necessario.
	 */
	public static String validaString(List<String> MensagensDeErro, String StringParaTestar) {
		MensagensDeErro = povoaList(3, MensagensDeErro);
		String mensagemErro = ehNull(MensagensDeErro.get(0), StringParaTestar);
		if (!mensagemErro.equals(Mensagem.OK.getMensagem())) {
			return mensagemErro;
		}
		mensagemErro = ehBranco(MensagensDeErro.get(1), StringParaTestar);
		if (!mensagemErro.equals(Mensagem.OK.getMensagem())) {
			return mensagemErro;
		}
		mensagemErro = somenteEspacos(MensagensDeErro.get(2), StringParaTestar);
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
		return validaString(mensagens_de_erro_padroes, StringParaTestar);
	}
	
	/**
	 * Submete a {@link String} apresentada a todos os testes desta classe, para verificar se ela eh nula, vazia ou se contem apenas caracteres de espaco.
	 * 
	 * @param MensagemDeErro
	 * 		Mensagem de erro para o caso da string ser invalida. 
	 * @param StringParaTestar
	 * 		{@link String} que devera ser checada
	 * @return
	 * 		OK, caso nenhum problema seja detectado, ou uma mensagem de erro especifica, caso necessario.
	 */
	public static String validaString(String mensagemDeErro, String StringParaTestar) {
		List<String> lista = new ArrayList<String>();
        lista.add(mensagemDeErro);
		return validaString(povoaList(4, lista), StringParaTestar);
	}
	
	/**
	 * Verifica se a {@link String} informada eh nula.
	 * 
	 * @param MensagemDeErro
	 * 		Mensagem de erro, que podera ser retornada (no caso da palavra nao ser aceita).
	 * @param StringParaTestar
	 * 		{@link String} que sera analisada
	 * @return
	 * 		OK, caso nao seja nula, ou uma mensagem de erro, caso seja.
	 */
	public static String ehNull(String MensagemDeErro, String StringParaTestar) {
		if (StringParaTestar == null) {
			return MensagemDeErro;
		}
		return Mensagem.OK.getMensagem();
	}
	
	/**
	 * Verifica se a {@link String} informada eh nula.
	 * 
	 * @param StringParaTestar
	 * 		{@link String} que sera analisada
	 * @return
	 * 		OK, caso nao seja nula, ou uma mensagem de erro, caso seja.
	 */
	public static String ehNull(String StringParaTestar) {
		return ehNull(mensagens_de_erro_padroes.get(0), StringParaTestar);
	}
	
	
	/**
	 * Verifica se a string esta vazia
	 * 
	 * @param MensagemDeErro
	 * 		Mensagem de erro retornada em caso da palavra ser rejeitada.
	 * @param StringParaTestar
	 * 		String que devera ser verificada
	 * @return
	 * 		OK, caso a string nao seja vazia, ou uma mensagem de erro, caso ela seja
	 */
	public static String ehBranco(String MensagemDeErro, String StringParaTestar) {
		if (StringParaTestar.isEmpty()) {
			return MensagemDeErro;
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
		return ehBranco(mensagens_de_erro_padroes.get(1), StringParaTestar);
	}
	
	
	/**
	 * Verifica se a string contem exclusivamente espacos
	 * 
	 * @param MensagemDeErro
	 * 		Mensagem de Erro a ser retornada em caso da string ser rejeitada nos testes
	 * @param StringParaTestar
	 * 		String que devera ser verificada
	 * @return
	 * 		OK, caso a string contenha algum caractere diferente de espaco,
	 * 				ou uma mensagem de erro, caso ela apresente apenas espacos.
	 */
	public static String somenteEspacos(String MensagemDeErro, String StringParaTestar) {
		if (StringParaTestar.trim().isEmpty()) {
			return MensagemDeErro;
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
		return somenteEspacos(mensagens_de_erro_padroes.get(2), StringParaTestar);
	}
	
	/**
	 * Retorna a string passada como parametro, se esta for valida,
	 * ou lanca uma excecao personalizada, caso a string passada seja invalida.
	 * 
	 * @param MensagensDeErro
	 * 		List de {@link String} de tamanho 3,
	 * 	onde os itens equivalem as mensagens de quando a String eh nula, vazia ou so contem espacos, respectivamente. 
	 * @param minhaString
	 * 			String que deve ser validada
	 * @return
	 * 			Retorna a string validada, desde que esta passe pelos testes
	 * @throws IllegalArgumentException
	 * 			Lanca uma excecao, caso a string passada nao seja aprovada
	 */
	public static String pegaString(List<String> MensagensDeErro, String minhaString) throws IllegalArgumentException {
        String teste = ValidadorString.validaString(MensagensDeErro, minhaString);
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
	 * @throws IllegalArgumentException
	 * 			Lanca uma excecao, caso a string passada nao seja aprovada
	 */
	public static String pegaString(String minhaString) throws IllegalArgumentException {
        return pegaString(mensagens_de_erro_padroes, minhaString);
    }
	
	/**
	 * Retorna a string passada como parametro, se esta for valida,
	 * ou lanca uma excecao personalizada, caso a string passada seja invalida.
	 * 
	 * @param MensagensDeErro
	 * 		Mensagem de erro retornada em caso da string ser invalida. 
	 * @param minhaString
	 * 			String que deve ser validada
	 * @return
	 * 			Retorna a string validada, desde que esta passe pelos testes
	 * @throws IllegalArgumentException
	 * 			Lanca uma excecao, caso a string passada nao seja aprovada
	 */
	public static String pegaString(String mensagemDeErro, String minhaString) throws IllegalArgumentException {
		List<String> lista = new ArrayList<String>();
        lista.add(mensagemDeErro);
        return pegaString(povoaList(4, lista), minhaString);
    }
	
	/**
	 * Retorna a string passada como parametro, se este for valido,
	 * ou lanca uma excecao personalizada, caso nao seja.
	 * 
	 * @param MensagensDeErro
	 * 		List de {@link String} de tamanho 4,
	 * 	onde os itens equivalem as mensagens de quando a String eh nula, vazia, so contem espacos ou contem espacos, respectivamente. 
	 * @param login
	 * 			Login que deve ser validado
	 * @return
	 * 			Retorna o login validado, desde que este passe pelos testes
	 * @throws IllegalArgumentException
	 * 			Lanca uma excecao, caso o login passado nao seja aprovado
	 */
	public static String pegaLogin(List<String> MensagensDeErro, String login) throws IllegalArgumentException {
		MensagensDeErro = povoaList(4, MensagensDeErro);
        try {
        	pegaString(MensagensDeErro, login);
        } catch (IllegalArgumentException e) {
			throw e;
		}
        
        if (login.contains(" ")) throw new IllegalArgumentException(MensagensDeErro.get(3));
        
        return login;
    }
	
	/**
	 * Retorna o login passado como parametro, se este for valido,
	 * ou lanca uma excecao padrao, caso nao seja.
	 * 
	 * @param login
	 * 			Login que deve ser validado
	 * @return
	 * 			Retorna o login validado, desde que este passe pelos testes
	 * @throws IllegalArgumentException
	 * 			Lanca uma excecao, caso o login passado nao seja aprovado
	 */
	public static String pegaLogin(String login) throws IllegalArgumentException {
        return pegaLogin(mensagens_de_erro_padroes_login, login);
    }
	
	/**
	 * Retorna a string passada como parametro, se este for valido,
	 * ou lanca uma excecao personalizada, caso nao seja.
	 * 
	 * @param MensagemDeErro
	 * 		Mensagem de erro a ser retornada em caso da string ser invalida. 
	 * @param login
	 * 			Login que deve ser validado
	 * @return
	 * 			Retorna o login validado, desde que este passe pelos testes
	 * @throws IllegalArgumentException
	 * 			Lanca uma excecao, caso o login passado nao seja aprovado
	 */
	public static String pegaLogin(String mensagemDeErro, String login) throws IllegalArgumentException {
        List<String> lista = new ArrayList<String>();
        lista.add(mensagemDeErro);
		return pegaLogin(povoaList(4, lista), login);
    }
	
	private static List<String> povoaList(int tamanho_desejado, List<String> list) {
		
		if (list == null || list.isEmpty()) return mensagens_de_erro_padroes_login;
		
		List <String> resposta = new ArrayList<String>();
		resposta.addAll(list);
		
		
		for (int i = 0; i < tamanho_desejado-list.size(); i++) {
			resposta.add(list.get(0));
		}
		return resposta;
	}
	
	private static List<String> toList(String [] array) {
		List<String> resposta = new ArrayList<String>();
		for (String c : array) {
			resposta.add(c);
		}
		return resposta;
	}
	
}