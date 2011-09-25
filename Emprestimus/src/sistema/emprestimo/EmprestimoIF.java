package sistema.emprestimo;

import java.util.Calendar;

import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.ItemIF;
import sistema.usuario.UsuarioIF;

public interface EmprestimoIF extends Comparable<EmprestimoIF> {
	
	public void setId( String idEmprestimo ) throws Exception;
	
	public void setEmprestador( UsuarioIF emprestador ) throws ArgumentoInvalidoException;
	
	public void setBeneficiado( UsuarioIF beneficiado ) throws ArgumentoInvalidoException;
	
	public String getNomeParaEstado();
		
	public void setTipoEmprestador();
	
	public void setTipoBeneficiado();
	
	public void setDuracao( int duracao ) throws Exception;
	
	public String getIdEmprestimo();
	
	public UsuarioIF getEmprestador();
	
	public UsuarioIF getBeneficiado();
	
	public ItemIF getItem();
	
	public int getDuracao();
	
	//public boolean estahAceito();
	
	public boolean ehTipoEmprestador();
	
	public boolean ehTipoBeneficiado();
	
	public String getEstado();

	EmprestimoEstado getTipoEstado();

	public Calendar getDataDeDevolucao();
	
	public EmprestimoEstado getEstadoEnum();

	//######################### Autômato Finito
	
	public void setSituacaoAndamento();
	public void setSituacaoCancelado();
	public void setSituacaoCompletado();
	public boolean ehSituacaoAndamento();
	public boolean ehSituacaoCancelado();
	public boolean ehSituacaoCompletado();
	
	public boolean estaAprovado();
	
	public void setEstadoAndamento();
	public void setEstadoDevolucaoRequisitada();
	public void setEstadoDevolvido();
	public void setEstadoTermino();
	public boolean ehEstadoAndamento();
	public boolean ehEstadoDevolucaoRequisitada();
	public boolean ehEstadoDevolvido();
	public boolean ehEstadoTermino();
	
	public void aprovarEmprestimo() throws Exception;
	
	public void requisitarDevolucaoEmprestimo( boolean noPrazo ) throws Exception;
	
	public void devolverEmprestimo() throws Exception;
	
	public void confirmarEmprestimo() throws Exception;
	
	public String getSituacao();
	
	public void setDataAprovacao();
	

}
