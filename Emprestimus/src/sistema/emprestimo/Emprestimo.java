package sistema.emprestimo;
import sistema.emprestimo.EmprestimoIF;
import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.ItemIF;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;


public class Emprestimo implements EmprestimoIF{
	
	
	String id;
	int duracao;
	UsuarioIF emprestador;
	UsuarioIF beneficiador;
	ItemIF item;
	String tipo;
	
	public Emprestimo( UsuarioIF emprestador, UsuarioIF beneficiador, ItemIF item, String tipo, int duracao ) throws Exception{
		setEmprestador(emprestador);
		setBeneficiador(beneficiador);
		setItem(item);
		Validador.assertNaoNulo(tipo, Mensagem.EMPRESTIMO_TIPO_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(tipo.trim(), Mensagem.EMPRESTIMO_TIPO_INVALIDO.getMensagem());
		
		if(tipo.trim().equalsIgnoreCase("emprestador")){
			setTipoEmprestador();
		}else if(tipo.trim().equalsIgnoreCase("beneficiador")){
			setTipoBeneficiador();
		}else{
			throw new Exception(Mensagem.EMPRESTIMO_TIPO_INXISTENTE.getMensagem());
		}
	}
	
	@Override
	public void setId(String idEmprestimo) throws Exception{
		Validador.assertNaoNulo(idEmprestimo, Mensagem.ID_EMPRESTIMO_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(idEmprestimo.trim(), Mensagem.ID_EMPRESTIMO_INVALIDO.getMensagem());
		try{
		    Long id = Long.valueOf(idEmprestimo.trim());
		}catch(Exception e){
			throw new Exception(Mensagem.ID_EMPRESTIMO_INVALIDO.getMensagem());
		}
		
		id = idEmprestimo.trim();
		
	}
	
	@Override
	public String getIdEmprestimo() {
		return this.id;
	}
	
	public void setItem(ItemIF item) throws ArgumentoInvalidoException {
		Validador.assertNaoNulo(item, "ItemIF não pode ser nulo");
		this.item = item;
	}
	
	public ItemIF getItem(){
		return this.item;
	}
	
	public void setDuracao( int duracao ) throws Exception{
		if(duracao <= 0) throw new Exception(Mensagem.EMPRESTIMO_DURACAO_INVALIDA.getMensagem());
		this.duracao = duracao;
	}
	
	public int getDuracao(){
		return this.duracao;
	}
	
	public void setEmprestador( UsuarioIF emprestador ) throws ArgumentoInvalidoException{
		Validador.assertNaoNulo(emprestador, "UsuarioIF não deve ser null");
		this.emprestador = emprestador;
	}
	
	public UsuarioIF getEmprestador(){
		return this.emprestador;
	}
	
	public void setBeneficiador( UsuarioIF beneficiador ) throws ArgumentoInvalidoException {
		Validador.assertNaoNulo(emprestador, "UsuarioIF não deve ser null");
		this.beneficiador = beneficiador;
	}
	
	public UsuarioIF getBeneficiador(){
		return this.beneficiador;
	}
	
	@Override
	public void setTipoEmprestador() {
		tipo = "emprestador";
	}
	
	@Override
	public void setTipoBeneficiador() {
		tipo = "beneficiador";
	}
	
	@Override
	public boolean ehTipoEmprestador() {
		return tipo.equalsIgnoreCase("emprestador");
	}
	
	@Override
	public boolean ehTipoBeneficiador() {
		return tipo.equalsIgnoreCase("beneficiador");
	}

}
