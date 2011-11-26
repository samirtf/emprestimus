package sistema.persistencia;

import java.util.Map;
import java.util.TreeMap;

import sistema.notificacao.Notificacao;
import sistema.utilitarios.Mensagem;

/**
 * Singleton
 * 
 * @author Nathaniel
 * 
 */
public class NotificacaoRepositorio {
	private static long contadorID = 0;
	private static NotificacaoRepositorio repositorio;

	private static Map<Long, Notificacao> notificacoesCadastradas = new TreeMap<Long, Notificacao>();

	private NotificacaoRepositorio() {
	}

	public static NotificacaoRepositorio getInstance() {
		if (repositorio == null) {
			repositorio = new NotificacaoRepositorio();
		}
		return repositorio;
	}

	/**
	 * Calcula o id do proximo emprestimo a ser cadastrado.
	 * 
	 * @return String
	 * 		O id do proximo emprestimo a ser cadastrado.
	 */
	public static String geraIdProxNotificacao() {
		return String.valueOf(contadorID + 1);
	}

	/**
	 * Cria uma nova notificação
	 * 
	 * @param Notificacao - notif
	 * 		Nova notificação
	 * 
	 * @return String
	 * 		ID
	 * 
	 * @throws Exception
	 */
	public String novaNotificacao(Notificacao notif) throws Exception {
		notif.setId(NotificacaoRepositorio.geraIdProxNotificacao());
		notificacoesCadastradas.put(++contadorID, notif);
		return String.valueOf(contadorID);
	}

	/**
	 * Recupera uma notificação
	 * 
	 * @param String idNotificacao
	 * 
	 * @return Notificacao
	 * 		Notificação encontrada
	 * 
	 * @throws Exception
	 */
	public Notificacao recuperarNotificacao(String idNotificacao) throws Exception {
		Long idLong = null;
		try {
			idLong = Long.parseLong(idNotificacao);
		} catch (Exception e) {
			throw new Exception(Mensagem.NOTIFICACAO_INEXISTENTE.getMensagem());
		}
		Notificacao notif = notificacoesCadastradas.get(idLong);
		if (notif == null)
			throw new Exception(Mensagem.NOTIFICACAO_INEXISTENTE.getMensagem());

		return notif;
	}

	/**
	 * Calcula a quantidade de notificações cadastradas.
	 * 
	 * @return int
	 * 		A quantidade de notificações cadastradas.
	 */
	public int qntNotificacoes() {
		return notificacoesCadastradas.size();
	}

	/**
	 * Verifica se um determinada notificação existe no repositorio.
	 * 
	 * @param String - idNotificacao
	 * 		Um idEmprestimo.
	 * @return boolean
	 * 		True - Se a notificação procurado existir.
	 */
	public boolean existeNotificacao(String idNotificacao) {
		Long id;
		try {
			id = Long.valueOf(idNotificacao);
		} catch (Exception e) {
			return false;
		}
		return notificacoesCadastradas.containsKey(id);
	}

	/**
	 * Remove uma notificação
	 * 
	 * @param String
	 * 		idNotificacao
	 */
	public void removerNotificacao(String idNotificacao) {
		Long id;
		try {
			id = Long.valueOf(idNotificacao);
			if (existeNotificacao(idNotificacao)) {
				notificacoesCadastradas.remove(id);
			}
		} catch (Exception e) {

		}

	}

	/**
	 * Zera o repositorio
	 */
	public static void zerarRepositorio() {
		notificacoesCadastradas = new TreeMap<Long, Notificacao>();
		contadorID = 0;
	}

	public void salvarEmArquivo() {
		// TODO Auto-generated method stub
		
	}
}
