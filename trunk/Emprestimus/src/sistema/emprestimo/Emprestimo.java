package sistema.emprestimo;
import sistema.emprestimo.EmprestimoIF;
import java.util.Calendar;
import java.util.GregorianCalendar;

import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.ItemIF;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;


public class Emprestimo implements EmprestimoIF{
	
	
	String id;
	int duracao; //Em dias
	UsuarioIF emprestador; //quem concedeu De
	UsuarioIF beneficiado; // quem pediu   Para
	ItemIF item;
	String tipo;
	EmprestimoEstado estado;
	Calendar dataDeAprovacao;
//	Calendar dataDeDevolucao;
	
	public Emprestimo( UsuarioIF emprestador, UsuarioIF beneficiado, ItemIF item, String tipo, int duracao ) throws Exception{
		setEmprestador(emprestador);
		setBeneficiado(beneficiado);
		setItem(item);
		Validador.assertNaoNulo(tipo, Mensagem.EMPRESTIMO_TIPO_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(tipo.trim(), Mensagem.EMPRESTIMO_TIPO_INVALIDO.getMensagem());
		
		if(tipo.trim().equalsIgnoreCase("emprestador")){
			setTipoEmprestador();
		}else if(tipo.trim().equalsIgnoreCase("beneficiado")){
			setTipoBeneficiado();
		}else{
			throw new Exception(Mensagem.EMPRESTIMO_TIPO_INXISTENTE.getMensagem());
		}		
		this.estado = EmprestimoEstado.EM_ESPERA;
	}
	
	@Override
	public void setId(String idEmprestimo) throws Exception{
		
		Validador.assertNaoNulo(idEmprestimo, Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(idEmprestimo.trim(), Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		try{
		    Long id = Long.valueOf(idEmprestimo.trim());
		}catch(Exception e){
			throw new Exception(Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
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
	
	public void setBeneficiado( UsuarioIF beneficiado ) throws ArgumentoInvalidoException {
		Validador.assertNaoNulo(emprestador, "UsuarioIF não deve ser null");
		this.beneficiado = beneficiado;
	}
	
	public UsuarioIF getBeneficiado(){
		return this.beneficiado;
	}
	
	@Override
	public void setTipoEmprestador() {
		tipo = "emprestador";
	}
	
	@Override
	public void setTipoBeneficiado() {
		tipo = "beneficiado";
	}
	
	@Override
	public boolean ehTipoEmprestador() {
		return tipo.equalsIgnoreCase("emprestador");
	}
	
	@Override
	public boolean ehTipoBeneficiado() {
		return tipo.equalsIgnoreCase("beneficiado");
	}

	@Override
	public synchronized void setEstadoAceito() {
		this.estado = EmprestimoEstado.ACEITO;
		dataDeAprovacao = new GregorianCalendar();
		
	}

	@Override
	public void setEstadoConfirmado() {
		this.estado = EmprestimoEstado.CONFIRMADO;
		
	}

	@Override
	public void setEstadoRequisitadoDevolucao() {
		this.estado = EmprestimoEstado.REQUISITADO_PARA_DEVOLUCAO;
		
	}

	

	@Override
	public boolean estahAceito() {
		return this.estado.equals(EmprestimoEstado.EM_ESPERA) ;
	}

	
	@Override
	public void setEstadoCancelado() {
		this.estado = EmprestimoEstado.CANCELADO;
		
	}

	@Override
	public String getEstado() {
		return estado.getNome();
	}
	
	public EmprestimoEstado getEstadoEnum() {
		return estado;
	}
	
	public String getNomeParaEstado(){
		if(this.estado ==  EmprestimoEstado.ACEITO){
			return "Andamento";
		}else if(this.estado ==  EmprestimoEstado.ESPERANDO_CONFIRMACAO){
			return "Andamento";
		}else if(this.estado ==  EmprestimoEstado.CANCELADO){
			return "Cancelado";
		}else if(this.estado ==  EmprestimoEstado.CONFIRMADO){
			return "Completado";
		} else if (this.estado == EmprestimoEstado.REQUISITADO_PARA_DEVOLUCAO) {
			return "Andamento";
		}
		return " => Bug em Emprestimus.getNomeParaEstado()";	
	}
	
	@Override
	public EmprestimoEstado getTipoEstado(){
		return this.estado;
	}
	
	public synchronized Calendar getDataDeDevolucao() {
		Calendar dataDevolucao = (Calendar) dataDeAprovacao.clone();
		dataDevolucao.add(GregorianCalendar.DATE, duracao);
		return dataDevolucao;
	}

	@Override
	public void setEstadoEsperandoConfirmacao() {
		this.estado = EmprestimoEstado.ESPERANDO_CONFIRMACAO;
		
	}





	

}
