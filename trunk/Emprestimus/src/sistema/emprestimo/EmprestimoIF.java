package sistema.emprestimo;

public interface EmprestimoIF {
	
	public void setId( String idEmprestimo ) throws Exception;
	
	public String getIdEmprestimo();
	
	public void setTipoEmprestador();
	
	public void setTipoBeneficiador();
	
	public boolean ehTipoEmprestador();
	
	public boolean ehTipoBeneficiador();
	
	

}
