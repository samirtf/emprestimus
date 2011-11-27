package iu.web.server.sistema.emprestimo;

import iu.web.server.sistema.excecoes.ArgumentoInvalidoException;
import iu.web.server.sistema.item.ItemIF;
import iu.web.server.sistema.usuario.UsuarioIF;

import java.io.Serializable;
import java.util.Calendar;


/**
 * Representa um empréstimo.
 * 
 * @author Joeffison Silverio de Andrade, 21011853, joeffisonsa@gmail.com
 * @version 1.5
 */
public interface EmprestimoIF extends Comparable<EmprestimoIF>, Serializable {

	/**
	 * Altera o id do empréstimo.
	 * 
	 * @param idEmprestimo - String
	 * 		Novo id para o empréstimo.
	 *            
	 * @throws Exception
	 * 		Caso o parâmetro seja inválido.
	 */
	public void setId(String idEmprestimo) throws Exception;

	/**
	 * Altera o usuário que emprestou.
	 * 
	 * @param emprestador - UsuarioIF
	 * 		Novo usuário que substituirá o antigo emprestador.
	 *            
	 * @throws ArgumentoInvalidoException
	 * 		Caso o parâmetro seja nulo.
	 */
	public void setEmprestador(UsuarioIF emprestador) throws ArgumentoInvalidoException;

	/**
	 * Altera o usuário que pegou emprestado.
	 * 
	 * @param beneficiado - UsuarioIF
	 * 		Novo usuário que substituirá o antigo emprestador.
	 *            
	 * @throws ArgumentoInvalidoException
	 * 		Caso o parâmetro seja nulo.
	 */
	public void setBeneficiado(UsuarioIF beneficiado) throws ArgumentoInvalidoException;

	/**
	 * Recupera o nome do estado do empréstimo.
	 * 
	 * @return String
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
	 * @param duracao - int
	 * 		Nova duração para o empréstimo.
	 *            
	 * @throws Exception
	 * 		Caso a duração seja não-positiva.
	 */
	public void setDuracao(int duracao) throws Exception;

	/**
	 * Recupera o id do empréstimo.
	 * 
	 * @return String
	 * 		ID do empréstimo.
	 */
	public String getIdEmprestimo();

	/**
	 * Recupera o usuário que emprestou.
	 * 
	 * @return UsuarioIF
	 * 		Usuário que emprestou.
	 */
	public UsuarioIF getEmprestador();

	/**
	 * Recupera o usuário que pegou o empréstimo.
	 * 
	 * @return UsuarioIF
	 * 		Usuário que pegou o empréstimo
	 */
	public UsuarioIF getBeneficiado();

	/**
	 * Recupera o ID do item que foi emprestado.
	 * 
	 * @return ItemIF
	 * 		ID do item que foi emprestado.
	 */
	public ItemIF getItem();

	/**
	 * Recupera a duração do empréstimo.
	 * 
	 * @return int
	 * 		Duração do empréstimo.
	 */
	public int getDuracao();

	// public boolean estahAceito();

	/**
	 * Verifica se o emprétimo é do tipo emprestador.
	 * 
	 * @return boolean
	 * 		True caso seja do tipo emprestador.
	 */
	public boolean ehTipoEmprestador();

	/**
	 * Verifica se o emprétimo é do tipo beneficiado.
	 * 
	 * @return boolean
	 * 		true caso seja do tipo beneficiado.
	 */
	public boolean ehTipoBeneficiado();

	/**
	 * Recupera o estado do empréstimo.
	 * 
	 * @return String
	 * 		Estado do empréstimo.
	 */
	public String getEstado();

	/**
	 * Recupera o tipo do estado do empréstimo.
	 * 
	 * @return EmprestimoEstado
	 * 		Enum que representa o estado do empréstimo.
	 */
	public EmprestimoEstado getTipoEstado();

	/**
	 * Recupera a data da devolução.
	 * 
	 * @return Calendar
	 * 		Data prevista para o empréstimo ser concluído.
	 */
	public Calendar getDataDeDevolucao();

	/**
	 * Recupera o tipo do estado do empréstimo.
	 * 
	 * @return EmprestimoEstado
	 * 		Enum que representa o estado do empréstimo.
	 */
	public EmprestimoEstado getEstadoEnum();

	// ######################### Autômato Finito

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
	 * @return boolean
	 * 		True caso esteja em andamento.
	 */
	public boolean ehSituacaoAndamento();

	/**
	 * Verifica se o empréstimo está cancelado.
	 * 
	 * @return boolean
	 * 		True, caso esteja cancelado.
	 */
	public boolean ehSituacaoCancelado();

	/**
	 * Verifica se o empréstimo está completado.
	 * 
	 * @return boolean
	 * 		True caso esteja completado.
	 */
	public boolean ehSituacaoCompletado();

	/**
	 * Verifica se o empréstimo foi aprovado.
	 * 
	 * @return boolean
	 * 		true se o empréstimo foi aprovado.
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
	 * @return boolean
	 * 		true se o empréstimo está em andamento.
	 */
	public boolean ehEstadoAndamento();

	/**
	 * Verifica se o empréstimo está com devolução requisitada.
	 * 
	 * @return boolean 
	 * 		true se o empréstimo com devolução requisitada.
	 */
	public boolean ehEstadoDevolucaoRequisitada();

	/**
	 * Verifica se o item foi devolvido.
	 * 
	 * @return boolean
	 * 		true se o item foi devolvido.
	 */
	public boolean ehEstadoDevolvido();

	/**
	 * Verifica se o empréstimo está terminado.
	 * 
	 * @return boolean
	 * 		true se o empréstimo está terminado.
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
	 * @param noPrazo - boolean
	 *            Boolean que diz se está no prazo.
	 *            
	 * @throws Exception
	 *             Caso o empréstimo não esteja mais em andamento.
	 */
	public void requisitarDevolucaoEmprestimo(boolean noPrazo) throws Exception;

	/**
	 * Devolve o empréstimo.
	 * 
	 * @throws Exception
	 *             Caso já tenha devolvido ou terminado.
	 */
	public void devolverEmprestimo() throws Exception;

	/**
	 * Confirma a devolução do empréstimo.
	 * 
	 * @throws Exception
	 *             Caso o item não tenha sido devolvido.
	 */
	public void confirmarTerminoEmprestimo() throws Exception;

	/**
	 * Recupera a situação do empréstimo.
	 * 
	 * @return Situação do empréstimo.
	 */
	public String getSituacao();

	/**
	 * Altera a data de aprovação.
	 * 
	 */
	public void setDataAprovacao();

}
