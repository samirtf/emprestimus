package iu.web.server.sistema.item;

import static iu.web.server.sistema.utilitarios.Validador.assertStringNaoVazia;
import static iu.web.server.sistema.utilitarios.Validador.asserteTrue;

import iu.web.server.sistema.notificacao.Notificacao;
import iu.web.server.sistema.notificacao.NotificacaoRegistrarInteresseItem;
import iu.web.server.sistema.persistencia.NotificacaoRepositorio;
import iu.web.server.sistema.usuario.UsuarioIF;
import iu.web.server.sistema.utilitarios.Mensagem;
import iu.web.server.sistema.utilitarios.Validador;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.google.gwt.user.client.rpc.core.java.util.Collections;


/**
 * Esta classe representa os itens que podem ser cadastrados pelo usuario
 * 
 * @author Joeffison Silverio de Andrade, 21011853
 * @author José Ulisses de Brito Lira Filho, 20911806
 * @version 1.5.2
 */
public class Item implements ItemIF {

	private String idItem, nome, descricao;
	private List<String> categorias;
	private Date dataCriacao;
	private boolean estaDisponivel;
	private List<UsuarioIF> interessados;
	private UsuarioIF dono;

	/**
	 * O construtor padrao eh privado e nao oferece implementacao.
	 */
	private Item() {}

	public Item(String nome, String descricao, String categoria, UsuarioIF dono) throws Exception {
		setNome(nome);
		setDescricao(descricao);
		this.categorias = new ArrayList<String>();
		setCategorias(categoria);
		setDataCriacao();
		this.estaDisponivel = true;
		this.interessados = new ArrayList<UsuarioIF>();
		setDono(dono);
	}

	@Deprecated
	public Item(String nome, String descricao, String categoria) throws Exception {
		this(nome, descricao, categoria, null);
	}

	private void setDataCriacao() {
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
		assertStringNaoVazia(id, "ID do item não pode ser nula",
				"ID do item não pode ser vazia");
		this.idItem = id;
		return this;
	}

	@Override
	public void setNome(String nome) throws Exception {
		assertStringNaoVazia(nome, Mensagem.NOME_INVALIDO.getMensagem(),
				Mensagem.NOME_INVALIDO.getMensagem());
		this.nome = nome.trim();
	}

	
	@Override
	public void setCategorias(String categorias) throws Exception {
		Validador.assertStringNaoVazia(categorias, Mensagem.CATEGORIA_INVALIDA.getMensagem(), 
				Mensagem.CATEGORIA_INVALIDA.getMensagem());
		//"Filme, Biografia, Drama, Facebook"
		String novaCategoria = categorias.substring(0);
		String[] listaCategorias = novaCategoria.split(",");
		if(listaCategorias.length == 0)
			throw new Exception(Mensagem.CATEGORIA_INVALIDA.getMensagem());
		this.categorias = new ArrayList<String>();
		for(String categ : listaCategorias){
			if(!this.categorias.contains(categ))
				this.categorias.add(categ.trim());
		}
	}
	
	@Override
	public String[] getListaCategorias() throws Exception {
		return this.categorias.toArray(new String[0]);
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
		asserteTrue(!interessados.contains(interessado),
				"O usuario já está entre os interessados.");
		// FIXME utilizar as mensagens constantes do enum Mensagem..
		interessados.add(interessado);
		Notificacao notif = new NotificacaoRegistrarInteresseItem(interessado, this
				.getDono(), this);
		NotificacaoRepositorio.getInstance().novaNotificacao(notif);
	}

	@Override
	public void removeInteressado(UsuarioIF interessado) throws Exception {
		asserteTrue(interessados.remove(interessado),
				"O usuario não está entre os interessados.");
		// FIXME utilizar as mensagens constantes do enum Mensagem.

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
