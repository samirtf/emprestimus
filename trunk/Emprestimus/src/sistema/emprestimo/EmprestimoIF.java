package sistema.emprestimo;

import java.util.Calendar;

import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.ItemIF;
import sistema.usuario.UsuarioIF;

/**
 * Representa um empréstimo.
 * 
 * @author Joeffison Silverio de Andrade, 21011853, joeffisonsa@gmail.com
 * @since 1.3
 * @version 1.5
 */
public interface EmprestimoIF extends Comparable<EmprestimoIF> {
	
	/**
	 * Altera o id do empréstimo.
	 * 
	 * @param idEmprestimo
	 * 		Novo id para o empréstimo.
	 * @throws Exception
	 * 		Caso o parâmetro seja inválido.
	 */
	public void setId( String idEmprestimo ) throws Exception;
	
	/**
	 * Altera o usuário que emprestou.
	 * 
	 * @param emprestador
	 * 		Novo usuário que substituirá o antigo emprestador.
	 * @throws ArgumentoInvalidoException
	 * 		Caso o parâmetro seja nulo.
	 */
	public void setEmprestador( UsuarioIF emprestador ) throws ArgumentoInvalidoException;
	
	/**
	 * Altera o usuário que pegou emprestado.
	 * 
	 * @param beneficiado
	 * 		Novo usuário que substituirá o antigo emprestador.
	 * @throws ArgumentoInvalidoException
	 * 		Caso o parâmetro seja nulo.
	 */
	public void setBeneficiado( UsuarioIF beneficiado ) throws ArgumentoInvalidoException;
	
	/**
	 * Recupera o nome do estado do empréstimo.
	 * 
	 * @return
	 * 		Nome do estado do empréstimo.
	 */
	public String getNomeParaEstado();
		
	/**
	 * Altera o tipo para emprestador.
	 * 
	 */
	public void setTipoEmprestador();
	
	/**
	 * Altera o tipo para beneficiado.
	 * 
	 */
	public void setTipoBeneficiado();
	
	/**
	 * Modifica a duração do empréstimo.
	 * 
	 * @param duracao
	 * 		Nova duração para o empréstimo.
	 * @throws Exception
	 * 		Caso a duração seja não-positiva.
	 */
	public void setDuracao( int duracao ) throws Exception;
	
	/**
	 * Recupera o id do empréstimo.
	 * 
	 * @return
	 * 		ID do empréstimo.
 	 */
	public String getIdEmprestimo();
	
	/**
	 * Recupera o usuário que emprestou.
	 * 
	 * @return
	 * 		Usuário que emprestou.
	 */
	public UsuarioIF getEmprestador();
	
	/**
	 * Recupera o usuário que pegou o empréstimo.
	 * 
	 * @return
	 * 		Usuário que pegou o empréstimo
	 */
	public UsuarioIF getBeneficiado();
	
	/**
	 * Recupera o ID do item que foi emprestado.
	 * 
	 * @return
	 * 		ID do item que foi emprestado.
	 */
	public ItemIF getItem();
	
	/**
	 * Recupera a duração do empréstimo.
	 * 
	 * @return
	 * 		Duração do empréstimo.
	 */
	public int getDuracao();
	
	
	//public boolean estahAceito();
	
	/**
	 * Verifica se o emprétimo é do tipo emprestador.
	 * 
	 * @return
	 * 		Retorna true, caso seja do tipo emprestador, ou false, caso não.
	 */
	public boolean ehTipoEmprestador();
	
	/**
	 * Verifica se o emprétimo é do tipo beneficiado.
	 * 
	 * @return
	 * 		Retorna true, caso seja do tipo beneficiado, ou false, caso não.
	 */
	public boolean ehTipoBeneficiado();
	
	/**
	 * Recupera o estado do empréstimo.
	 * 
	 * @return
	 * 		Estado do empréstimo.
	 */
	public String getEstado();
	
	/**
	 * Recupera o tipo do estado do empréstimo.
	 * 
	 * @return
	 * 		Enum que representa o estado do empréstimo.
	 */
	public EmprestimoEstado getTipoEstado();

	/**
	 * Recupera a data da devolução.
	 * 
	 * @return
	 * 		Data prevista para o empréstimo ser concluído.
	 */
	public Calendar getDataDeDevolucao();
	
	/**
	 * Recupera o tipo do estado do empréstimo.
	 * 
	 * @return
	 * 		Enum que representa o estado do empréstimo.
	 */
	public EmprestimoEstado getEstadoEnum();

	//######################### Autômato Finito
	
	/**
	 * Altera a situação do empréstimo para em andamento.
	 * 
	 */
	public void setSituacaoAndamento();
	
	/**
	 * Altera a situação do empréstimo para cancelado.
	 * 
	 */
	public void setSituacaoCancelado();
	
	/**
	 * Altera a situação do empréstimo para completado.
	 * 
	 */
	public void setSituacaoCompletado();
	
	/**
	 * Verifica se o empréstimo está em andamento.
	 * 
	 * @return
	 * 		Retorna true, caso esteja em andamento, ou false, caso contrário. 
	 */
	public boolean ehSituacaoAndamento();
	
	/**
	 * Verifica se o empréstimo está cancelado.
	 * 
	 * @return
	 * 		Retorna true, caso esteja cancelado, ou false, caso contrário. 
	 */
	public boolean ehSituacaoCancelado();
	
	/**
	 * Verifica se o empréstimo está completado.
	 * 
	 * @return
	 * 		Retorna true, caso esteja completado, ou false, caso contrário. 
	 */
	public boolean ehSituacaoCompletado();
	
	/**
	 * Verifica se o empréstimo foi aprovado.
	 * 
	 * @return
	 * 		Retorna true, se o empréstimo foi aprovado., ou false, caso não.
	 */
	public boolean estaAprovado();
	
	/**
	 * Altera o estado para em andamento.
	 * 
	 */
	public void setEstadoAndamento();
	
	/**
	 * Altera o estado para requisitação requisitada.
	 * 
	 */
	public void setEstadoDevolucaoRequisitada();
	
	/**
	 * Altera o estado para devolvido.
	 * 
	 */
	public void setEstadoDevolvido();
	
	/**
	 * Altera o estado para requisitação terminado.
	 * 
	 */
	public void setEstadoTermino();
	
	/**
	 * Verifica se o empréstimo está em andamento.
	 * 
	 * @return
	 * 		Retorna true, se o empréstimo está em andamento, ou false, caso não.
	 */
	public boolean ehEstadoAndamento();
	
	/**
	 * Verifica se o empréstimo está com devolução requisitada.
	 * 
	 * @return
	 * 		Retorna true, se o empréstimo com devolução requisitada, ou false, caso não.
	 */
	public boolean ehEstadoDevolucaoRequisitada();
	
	/**
	 * Verifica se o item foi devolvido.
	 * 
	 * @return
	 * 		Retorna true, se o item foi devolvido, ou false, caso não.
	 */
	public boolean ehEstadoDevolvido();
	
	/**
	 * Verifica se o empréstimo está terminado.
	 * 
	 * @return
	 * 		Retorna true, se o empréstimo está terminado, ou false, caso não.
	 */
	public boolean ehEstadoTermino();
	
	/**
	 * Aprova o empréstimo.
	 * 
	 * @throws Exception
	 * 		Caso o estado não esteja definido como em andamento.
	 */
	public void aprovarEmprestimo() throws Exception;
	
	/**
	 * Requisita a devolução do empréstimo.
	 * 
	 * @param noPrazo
	 * 		Boolean que diz se está no prazo.
	 * @throws Exception
	 * 		Caso o empréstimo não esteja mais em andamento.
	 */
	public void requisitarDevolucaoEmprestimo( boolean noPrazo ) throws Exception;
	
	/**
	 * Devolve o empréstimo.
	 * 
	 * @throws Exception
	 * 		Caso já tenha devolvido ou terminado.
	 */
	public void devolverEmprestimo() throws Exception;
	
	/**
	 * Confirma a devolução do empréstimo.
	 * 
	 * @throws Exception
	 * 		Caso o item não tenha sido devolvido.
	 */
	public void confirmarEmprestimo() throws Exception;
	
	/**
	 * Recupera a situação do empréstimo.
	 * 
	 * @return
	 * 		Situação do empréstimo.
	 */
	public String getSituacao();
	
	/**
	 * Altera a data de aprovação.
	 * 
	 */
	public void setDataAprovacao();
	
}
