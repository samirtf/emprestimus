package codigo.logica.emprestimos;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import codigo.logica.itens.ItemIF;
import codigo.logica.pessoas.Usuario;


/**
 * Esta classe representa um emprestimo que pode ser feito pelo usuario
 * 
 * @author Joeffison Silverio de Andrade, joeffisonsa@gmail.com
 * @version 1.0
 * @since 1.0 (27/08/2011)
 */
public class Emprestimo {
	
	// private SimpleDateFormat sd = new SimpleDateFormat("dd/MM/yyyy");
	/* O q esta acima ajuda a imprimir a data, atraves de "sd.data_da_devolucao.getTime()"
	 */
	
	// Referente a datas e prazos
	private Calendar data_do_emprestimo;
	private Calendar data_da_devolucao;
	private int duracao;
	
	// Outros atributos
	private Usuario credor; // Aquele que empresta
	private Usuario devedor; // Aquele que pega emprestado
	private ItemIF item; // item pego emprestado
	
	
	private Emprestimo() {}
	
	public Emprestimo(ItemIF item, Calendar data_do_emprestimo, int duracao) {
		setData_do_emprestimo(data_do_emprestimo);
		setDuracao(duracao);
		setData_da_devolucao(data_do_emprestimo, duracao);
	}
	
	public void setData_do_emprestimo(Calendar data_do_emprestimo) {
		this.data_do_emprestimo = data_do_emprestimo;
	}
	
	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}
	
	public void setData_da_devolucao(Calendar data_do_emprestimo, int duracao) {
		this.data_da_devolucao = ( (Calendar) data_do_emprestimo.clone() ); // pega a data do emprestimo, sem fazer referencia ao mesmo objeto
		this.data_da_devolucao.add(Calendar.DAY_OF_MONTH, duracao);  // calcula a data de devolucao do emprestimo 
	}
	
	public Calendar getData_do_emprestimo() {
		return data_do_emprestimo;
	}
	
	public int getDuracao() {
		return duracao;
	}
	
	public Calendar getData_da_devolucao() {
		return data_da_devolucao;
	}
	
	public ItemIF getItem() {
		return item;
	}
	
	private void encerrarEmprestimo() {
		credor.receberItemQueEmprestou(devedor, item);
	}
	
}