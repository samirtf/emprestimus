package codigo.logica.emprestimos;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import codigo.logica.itens.ItemIF;

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
	
	private Calendar data_do_emprestimo;
	private Calendar data_da_devolucao;
	
	
	private Emprestimo() {}
	
	public Emprestimo(ItemIF item, Calendar data_do_emprestimo) {
		setData_do_emprestimo(data_do_emprestimo);
	}
	
	public void setData_do_emprestimo(Calendar data_do_emprestimo) {
		this.data_do_emprestimo = data_do_emprestimo;
	}
	
	public void setData_da_devolucao(Calendar data_do_emprestimo, int duracao) {
		this.data_da_devolucao = ( (Calendar) data_do_emprestimo.clone() );
		this.data_da_devolucao.add(Calendar.DAY_OF_MONTH, duracao);
	}
	
	public Calendar getData_do_emprestimo() {
		return data_do_emprestimo;
	}
	
	public Calendar getData_da_devolucao() {
		return data_da_devolucao;
	}
	
	
	
}
