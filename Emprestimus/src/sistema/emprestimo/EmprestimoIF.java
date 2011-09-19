package sistema.emprestimo;

import sistema.excecoes.ArgumentoInvalidoException;
import sistema.usuario.UsuarioIF;

public interface EmprestimoIF {
	
	public void setId( String idEmprestimo ) throws Exception;
	
	public void setEmprestador( UsuarioIF emprestador ) throws ArgumentoInvalidoException;
	
	public void setBeneficiado( UsuarioIF beneficiado ) throws ArgumentoInvalidoException;
	
	public void setEstadoAceito();
	
	public void setEstadoDevolvido();
	
	public void setEstadoRecusado();
	
	public void setEstadoAndamento();
	
	public void setTipoEmprestador();
	
	public void setTipoBeneficiado();
	
	public void setDuracao( int duracao ) throws Exception;
	
	public String getIdEmprestimo();
	
	public UsuarioIF getEmprestador();
	
	public UsuarioIF getBeneficiado();
	
	public int getDuracao();
	
	public boolean ehTipoEmprestador();
	
	public boolean ehTipoBeneficiado();
	

}
