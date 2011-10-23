package sistema.item;

import static sistema.utilitarios.Validador.assertStringNaoVazia;
import static sistema.utilitarios.Validador.asserteTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import sistema.notificacao.Notificacao;
import sistema.notificacao.NotificacaoRegistroInteresse;
import sistema.persistencia.NotificacaoRepositorio;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;

/**
 * Esta classe representa os itens que podem ser cadastrados pelo usuario
 * 
 * @author Joeffison Silverio de Andrade, 21011853
 * @author José Ulisses de Brito Lira Filho, 20911806
 * @version 1.5.2
 */
public class Item implements ItemIF {

	private String idItem, nome, descricao;
	private String categoria;
	private Date dataCriacao;
	private boolean estaDisponivel;
	private List<UsuarioIF> interessados;
	private UsuarioIF dono;

	/**
	 * O construtor padrao eh privado e nao oferece implementacao.
	 */
	private Item() {
	}

	public Item(String nome, String descricao, String categoria, UsuarioIF dono)
			throws Exception {
		setNome(nome);
		setDescricao(descricao);
		setCategoria(categoria);
		setDataCriacao();
		this.estaDisponivel = true;
		this.interessados = new ArrayList<UsuarioIF>();
		setDono(dono);
	}

	@Deprecated
	public Item(String nome, String descricao, String categoria)
	throws Exception {
		this(nome, descricao, categoria, null);
	}
	
	private void setDataCriacao(){
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.dataCriacao = new GregorianCalendar().getTime();
	}

	@Override
	public String getId() {
		return this.idItem;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	@Override
	public String getCategoria() {
		return this.categoria;
	}
	
	public UsuarioIF getDono() {
		return dono;
	}
	
	public void setDono(UsuarioIF dono) {
		this.dono = dono;
	}

	@Override
	public String getDescricao() {
		return this.descricao;
	}

	@Override
	public ItemIF setId(String id) throws Exception {
		assertStringNaoVazia(id, "ID do item não pode ser nula", "ID do item não pode ser vazia");
		this.idItem = id;
		return this;
	}

	@Override
	public void setNome(String nome) throws Exception {
		assertStringNaoVazia(nome, Mensagem.NOME_INVALIDO.getMensagem(), Mensagem.NOME_INVALIDO.getMensagem());
		this.nome = nome.trim();
	}

	@Override
	public void setCategoria(String categoria) throws Exception {
		this.categoria = categoria;
		//TODO: tratar erros de entrada.

	}

	@Override
	public void setDescricao(String descricao) throws Exception {
		if (descricao == null) {
			this.descricao = "";
		} else {
			this.descricao = descricao.trim();
		}
	}

	@Override
	public boolean estahDisponivel() {
		return this.estaDisponivel;
	}

	@Override
	public void setDisponibilidade(boolean disponibilidade) {
		this.estaDisponivel = disponibilidade;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Item) {
			Item outro = (Item) obj;
			return this.idItem.equals(outro.getId());
		}
		return false;
	}

	@Override
	public int compareTo(ItemIF item) {
		return (int) (Long.valueOf(this.getId()) - Long.valueOf(item.getId()));
	}

	@Override
	public void adicionaInteressado(UsuarioIF interessado) throws Exception {
		asserteTrue(!interessados.contains(interessado), "O usuario já está entre os interessados."); 
		//FIXME utilizar as mensagens constantes do enum Mensagem
		interessados.add(interessado);
		
		Notificacao notif = new NotificacaoRegistroInteresse(interessado, this.getDono(), this);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
		interessado.addNotificacao(notif);
	}

	@Override
	public void removeInteressado(UsuarioIF interessado) throws Exception {
		asserteTrue(interessados.remove(interessado), "O usuario não está entre os interessados."); 
		//FIXME utilizar as mensagens constantes do enum Mensagem
		
	}

	@Override
	public void removeTodosInteressados() {
		interessados.clear();
		
	}

	@Override
	public List<UsuarioIF> getInteresasados() {
		return interessados;
	}

	@Override
	public Date getDataCriacao() {
		return this.dataCriacao;
	}

}
