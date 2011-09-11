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
	
	private static List<String> mensagens_de_erro_padroes_campoSemEspacos = null;
	{
		String [] array = {"Este dado nao pode ser nulo!",
					 	   "Este dado nao pode ser vazio!",
						   "Este dado nao pode conter apenas espacos!," +
						   "Este dado nao pode conter espacos!"};
		
		if (mensagens_de_erro_padroes_campoSemEspacos == null) mensagens_de_erro_padroes_campoSemEspacos = toList(array);
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
	 * 	onde os itens equivalem as mensagens de quando a String eh nula, vazia, so contem espacos ou contem algum espaco, respectivamente. 
	 * @param campoSemEspacos
	 * 			String que deve ser validada.
	 * @return
	 * 			Retorna a string validada, desde que este passe pelos testes
	 * @throws IllegalArgumentException
	 * 			Lanca uma excecao, caso a String passada nao seja aprovada.
	 */
	public static String pegaCampoSemEspacos(List<String> MensagensDeErro, String campoSemEspacos) throws IllegalArgumentException {
		MensagensDeErro = povoaList(4, MensagensDeErro);
        try {
        	pegaString(MensagensDeErro, campoSemEspacos);
        } catch (IllegalArgumentException e) {
			throw e;
		}
        
        if (campoSemEspacos.contains(" ")) throw new IllegalArgumentException(MensagensDeErro.get(3));
        
        return campoSemEspacos;
    }
	
	/**
	 * Retorna a String passada como parametro, se este for valido,
	 * ou lanca uma excecao padrao, caso nao seja.
	 * 
	 * @param campoSemEspacos
	 * 			CampoSemEspacos que deve ser validado
	 * @return
	 * 			Retorna a string validada, desde que este passe pelos testes
	 * @throws IllegalArgumentException
	 * 			Lanca uma excecao, caso a String passada nao seja aprovada.
	 */
	public static String pegaCampoSemEspacos(String campoSemEspacos) throws IllegalArgumentException {
        return pegaCampoSemEspacos(mensagens_de_erro_padroes_campoSemEspacos, campoSemEspacos);
    }
	
	/**
	 * Retorna a string passada como parametro, se este for valido,
	 * ou lanca uma excecao personalizada, caso nao seja.
	 * 
	 * @param MensagemDeErro
	 * 		Mensagem de erro a ser retornada em caso da string ser invalida. 
	 * @param campoSemEspacos
	 * 			String que deve ser validada.
	 * @return
	 * 			Retorna a string validada, desde que este passe pelos testes
	 * @throws IllegalArgumentException
	 * 			Lanca uma excecao, caso a String passada nao seja aprovada.
	 */
	public static String pegaCampoSemEspacos(String mensagemDeErro, String campoSemEspacos) throws IllegalArgumentException {
        List<String> lista = new ArrayList<String>();
        lista.add(mensagemDeErro);
		return pegaCampoSemEspacos(povoaList(4, lista), campoSemEspacos);
    }
	
	/**
	 * Verifica se a string, passada como parametro, eh nao nula, nao vazia, e nao possui nenhum espaco.
	 * 
	 * @param MensagemDeErro
	 * 		Mensagem de erro a ser retornada em caso da string ser invalida. 
	 * @param string_para_validacao
	 * 			String que deve ser validada
	 * @return 
	 * @return
	 * 			{@link Mensagem}.OK, caso a string passe nos testes,
	 * 				ou uma mensagem de erro, caso nao.
	 */
	public static String validaCampoSemEspaco(String mensagemDeErro, String string_para_validacao) {
		List<String> lista = new ArrayList<String>();
        lista.add(mensagemDeErro);
        return validaCampoSemEspaco(povoaList(4, lista), string_para_validacao);
	}
	
	/**
	 * Verifica se a string, passada como parametro, eh nao nula, nao vazia, e nao possui nenhum espaco.
	 * 
	 * @param mensagensDeErro
	 * 		Mensagens de erro a ser retornada em caso da string ser invalida. 
	 * @param string_para_validacao
	 * 			String que deve ser validada
	 * @return
	 * 			{@link Mensagem}.OK, caso a string passe nos testes,
	 * 				ou uma mensagem de erro, caso nao.
	 */
	public static String validaCampoSemEspaco(List<String> mensagensDeErro, String string_para_validacao) {
		String teste = pegaCampoSemEspacos(mensagensDeErro, string_para_validacao); 
		try {
        	if (!teste.equals(string_para_validacao)) return mensagensDeErro.get(0); // so por garantia
        	return Mensagem.OK.getMensagem();
        } catch (Exception e) { // a mensagem de erro certa esta nesta excecao
			return e.getMessage();
		}
	}
	
	/**
	 * Verifica se a string, passada como parametro, eh nao nula, nao vazia, e nao possui nenhum espaco.
	 * 
	 * @param string_para_validacao
	 * 			String que deve ser validada
	 * @return 
	 * @return
	 * 			{@link Mensagem}.OK, caso a string passe nos testes,
	 * 				ou uma mensagem de erro, caso nao.
	 */
	public static String validaCampoSemEspaco(String string_para_validacao) {
		return validaCampoSemEspaco(mensagens_de_erro_padroes_campoSemEspacos, string_para_validacao);
	}
	
	
	private static List<String> povoaList(int tamanho_desejado, List<String> list) {
		
		if (list == null || list.isEmpty()) return mensagens_de_erro_padroes_campoSemEspacos;
		
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