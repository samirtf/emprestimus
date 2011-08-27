package codigo.logica.itens;


import java.util.GregorianCalendar;

import codigo.logica.pessoas.Usuario;
import codigo.utilitarios.ValidadorString;


/**
 * Esta classe representa os itens que podem ser cadastrados pelo usuario
 * 
 * @author Joeffison Silverio de Andrade, joeffisonsa@gmail.com
 * @version 1.5
 * @since 1.0
 */
public class Item implements ItemIF{
	
	private String nome, descricao;
	private ItemCategoria categoria;
	
	private boolean estaDisponivel;
	private Usuario emprestadoA; //item faz coisa que nao devia
	private GregorianCalendar dataEmprestimo; // codigo fedido, cara de nova(s) classe(s)
	private int diasEmprestimo;
	
	private Item() {}
		// Construtor padrao privado impede a criacao do objeto sem parametros.
	
	public Item(String nome, String descricao, ItemCategoria categoria) throws IllegalArgumentException {
		setDescricao(descricao);
		setNome(nome);
		setCategoria(categoria);
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public ItemCategoria getCategoria() {
		return this.categoria;
	}
	
	public Usuario getEmprestadoA(){ // TODO ?
		return this.emprestadoA;
	}
	
	public int getDiasEmprestimo(){ // TODO Isto fede!
		return this.diasEmprestimo;
	}
	
	public GregorianCalendar getDataEmprestimo(){ //TODO
		return this.dataEmprestimo;
	}
	
	public boolean estaDisponivel(){
		return this.estaDisponivel;
	}
	
	public void setNome(String nome) throws IllegalArgumentException {
		this.nome = ValidadorString.pegaString(nome);
	}
	
	public void setDescricao(String descricao) throws IllegalArgumentException {
		this.descricao = ValidadorString.pegaString(descricao);
	}
	
	public void setCategoria(ItemCategoria categoria) {
		this.categoria = categoria;
	}
	
	public void setDisponivel(){ // TODO public boolean setDisponibilidade
		this.estaDisponivel = true;
	}
	
	public void setIndisponivel(){
		this.estaDisponivel = false;
	}
	
	public void setEmprestadoA(Usuario login){ // TODO
		this.emprestadoA = login;
	}
	
	public void setDiasEmprestimo(int dias){ // TODO 
		this.diasEmprestimo = dias;
	}
	
	public void setDataEmprestimo(GregorianCalendar dataEmp){ // TODO
		this.dataEmprestimo = dataEmp;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item) {
			Item outro = (Item) obj;
			return ( (getNome() == outro.getNome()) &&
					    getCategoria().equals(outro.getCategoria()) );
		}
		return false;
	}
	
}
