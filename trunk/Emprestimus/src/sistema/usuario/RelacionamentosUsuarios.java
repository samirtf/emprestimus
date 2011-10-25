package sistema.usuario;
import static sistema.utilitarios.Validador.assertStringNaoVazia;
import static sistema.utilitarios.Validador.asserteTrue;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import sistema.autenticacao.Autenticacao;
import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.DataCriacaoItemComparador;
import sistema.item.ItemIF;
import sistema.item.NomeItemComparador;
import sistema.mensagem.CaixaPostal;
import sistema.mensagem.ChatIF;
import sistema.mensagem.Correio;
import sistema.notificacao.Notificacao;
import sistema.persistencia.EmprestimoRepositorio;
import sistema.persistencia.ItemRepositorio;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;

/**
 * Emprestimus
 * Projeto de Sistemas de Informação I
 * Universidade Federal de Campina Grande
 */

public class RelacionamentosUsuarios {
	
	private static RelacionamentosUsuarios relacionamentosUsuarios;
	private static Map<String, CicloDeAmizade> ciclosDeAmizade;

	private RelacionamentosUsuarios() {
		ciclosDeAmizade = new TreeMap<String, CicloDeAmizade>();
	}

	public static RelacionamentosUsuarios getInstance() {
		if (relacionamentosUsuarios == null) {
			relacionamentosUsuarios = new RelacionamentosUsuarios();

			return relacionamentosUsuarios;
		}
		return relacionamentosUsuarios;
	}

	public void adicionaCicloDeAmizadeAoUsuario(String usuario) throws Exception {
		if(ciclosDeAmizade.containsKey(usuario)) throw new Exception(Mensagem.PROPRIETARIO_CONTA_JAH_CADASTRADO.getMensagem());
		ciclosDeAmizade.put(usuario, new CicloDeAmizade(usuario));
		
	}
	
	public void removeCicloDeAmizadeDoUsuario(String usuario) throws Exception {
		ciclosDeAmizade.remove(usuario);
	}
	
	public CicloDeAmizade getCicloDeAmizade(String login) throws Exception {
		return ciclosDeAmizade.get(login);
	}
	
	
	//###
	public synchronized void aprovarAmizade( String seuLogin, String novoAmigo ) throws Exception{
		
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		UsuarioIF usuario = Autenticacao.getUsuarioPorLogin(seuLogin);
		UsuarioIF amigo = Autenticacao.getUsuarioPorLogin(novoAmigo);
		List<UsuarioIF> amigos = ciclosDeAmizade.get(seuLogin).getAmigos();
		if(amigos.contains(amigo)){
			throw new Exception(Mensagem.USUARIO_JAH_SAO_AMIGOS.getMensagem());
		}
		Iterator<UsuarioIF> iterador = ciclosDeAmizade.get(seuLogin).getQueremSerMeusAmigos().iterator();
		UsuarioIF amigo_solicitante = null;
		while(iterador.hasNext()){
			amigo_solicitante = iterador.next();
			if(amigo_solicitante.getLogin().trim().equalsIgnoreCase(novoAmigo.trim())){
				amigo_solicitante.aprovouAmizade(usuario);
				amigos.add(amigo_solicitante);
				iterador.remove();
			    //usuario.addHistoricoAmizadeAprovada(amigo_solicitante);
				amigo_solicitante.addHistoricoAmizadeAprovada(usuario);
				return;
			}
			
		}
		throw new Exception(Mensagem.REQUISICAO_AMIZADE_INEXISTENTE.getMensagem());
		
	}
	
    public synchronized void aprovouAmizade( String seuLogin, UsuarioIF usuario ) throws ArgumentoInvalidoException {
    	Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		
    	List<UsuarioIF> queroSerAmigoDeles = ciclosDeAmizade.get(seuLogin).getQueroSerAmigoDeles();
		if(queroSerAmigoDeles.contains(usuario)){
			queroSerAmigoDeles.remove(usuario);
			ciclosDeAmizade.get(seuLogin).getAmigos().add(usuario);
		}
		
	}
    
    public boolean ehAmigo( String seuLogin, String amigoProcurado ) throws ArgumentoInvalidoException{
    	Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(amigoProcurado, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		Iterator<UsuarioIF> iterador = ciclosDeAmizade.get(seuLogin).getAmigos().iterator();
		UsuarioIF usuario = null;
		while(iterador.hasNext()){
			usuario = iterador.next();
			if ( usuario.getLogin().trim().equalsIgnoreCase(amigoProcurado.trim()) ) 
				return true;
		}
		return false;
	}
    
    public boolean amizadeDeFoiRequisitada( String seuLogin, String loginDoAmigo ) throws ArgumentoInvalidoException{
    	Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(loginDoAmigo, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		Iterator<UsuarioIF> iterador = ciclosDeAmizade.get(seuLogin).getQueroSerAmigoDeles().iterator();
		UsuarioIF usuario = null;
		while(iterador.hasNext()){
			usuario = iterador.next();
			if ( usuario.getLogin().trim().equalsIgnoreCase(loginDoAmigo.trim()) ) 
				return true;
		}
		return false;
	}
    
    public void requisitarAmizade( String seuLogin, String loginDoAmigo ) throws Exception {
    	Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
    	RelacionamentosUsuarios relacionamentosUsuarios = RelacionamentosUsuarios.getInstance();
		if(relacionamentosUsuarios.ehAmigo(seuLogin, loginDoAmigo)){
			throw new Exception(Mensagem.USUARIO_JAH_SAO_AMIGOS.getMensagem());
		}else if(relacionamentosUsuarios.amizadeDeFoiRequisitada(seuLogin, loginDoAmigo)){
			throw new Exception(Mensagem.AMIZADE_JAH_SOLICITADA.getMensagem());
		}

		UsuarioIF futuroAmigo = Autenticacao.getUsuarioPorLogin(loginDoAmigo);
		if( Autenticacao.existeUsuario(loginDoAmigo.trim()) ){
			relacionamentosUsuarios.getCicloDeAmizade(seuLogin).getQueroSerAmigoDeles().add(futuroAmigo);
			relacionamentosUsuarios.getCicloDeAmizade(loginDoAmigo).getQueroSerAmigoDeles().add(futuroAmigo);
			UsuarioIF eu = Autenticacao.getUsuarioPorLogin(seuLogin);
			futuroAmigo.usuarioQuerSerMeuAmigo(eu);
		}
	}
    
    
    public void usuarioQuerSerMeuAmigo( String seuLogin, UsuarioIF usuarioSolicitante ) throws ArgumentoInvalidoException{
    	Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
    	List<UsuarioIF> queremSerMeusAmigos = ciclosDeAmizade.get(seuLogin).getQueremSerMeusAmigos();
		for( UsuarioIF usuario : queremSerMeusAmigos ){
			if(usuario.equals(usuarioSolicitante)) return;
		}
		queremSerMeusAmigos.add(usuarioSolicitante);
	}
    
    
	public String getAmigos(String seuLogin) throws Exception{
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		
		Iterator<UsuarioIF> iterador = ciclosDeAmizade.get(seuLogin).getAmigos().iterator();
		StringBuffer str = new StringBuffer();
		while(iterador.hasNext()){
			str.append(iterador.next().getLogin()+"; ");
		}
		if(str.toString().trim().equals("")) 
			return Mensagem.USUARIO_NAO_POSSUI_AMIGOS.getMensagem();
		return str.toString().trim().substring(0, str.toString().length()-2);
		
	}
	
	public UsuarioIF ehItemDoMeuAmigo( String seuLogin, String idItem ) throws Exception{
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		Validador.assertStringNaoVazia(idItem, Mensagem.ID_ITEM_INVALIDO.getMensagem(), Mensagem.ID_ITEM_INVALIDO.getMensagem());
		Validador.asserteTrue(ItemRepositorio.existeItem(idItem.trim()), Mensagem.ID_ITEM_INEXISTENTE.getMensagem());
		
		Iterator<UsuarioIF> iterador = ciclosDeAmizade.get(seuLogin).getAmigos().iterator();
		while(iterador.hasNext()){
			UsuarioIF amigo = iterador.next();
			if(amigo.oItemMePertence(idItem)){
				return amigo;
			}
		}
		return null;
		
	}
		
	
	public UsuarioIF possuoAmigoComEsteLogin(String seuLogin, String loginDoAmigo) throws Exception {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		assertStringNaoVazia(loginDoAmigo, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		
		Iterator<UsuarioIF> iterador = ciclosDeAmizade.get(seuLogin).getAmigos().iterator();
		while(iterador.hasNext()){
			UsuarioIF amigo = iterador.next();
			if(amigo.getLogin().equals(loginDoAmigo.trim()))
				return amigo;
		}
		
		return null;
	}
	
	
	public synchronized String pesquisarItem( String seuLogin, String chave, String atributo,
			String tipoOrdenacao, String criterioOrdenacao) throws Exception {
		Validador.assertStringNaoVazia(seuLogin, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		
		StringBuffer saida = new StringBuffer();
		
		if( criterioOrdenacao.trim().equalsIgnoreCase("dataCriacao") ){
			List<ItemIF> saidaDataCriacao = new LinkedList<ItemIF>();
			Iterator<UsuarioIF> iteradorUsuarios = ciclosDeAmizade.get(seuLogin).getAmigos().iterator();
			while(iteradorUsuarios.hasNext()){
				UsuarioIF amigo = iteradorUsuarios.next();
				buscarItem(chave, atributo, saidaDataCriacao, amigo);
			}
			buscarItem(chave, atributo, saidaDataCriacao, Autenticacao.getUsuarioPorLogin(seuLogin));
			if(tipoOrdenacao.trim().equalsIgnoreCase("crescente")){
				Collections.sort(saidaDataCriacao, new DataCriacaoItemComparador());
			}else if(tipoOrdenacao.trim().equalsIgnoreCase("decrescente")){
				Collections.sort(saidaDataCriacao, new DataCriacaoItemComparador());
				Collections.reverse(saidaDataCriacao);
			}
			Iterator<ItemIF> listaItensRefinadosIterator = saidaDataCriacao.iterator();
			while(listaItensRefinadosIterator.hasNext()){
				saida.append(listaItensRefinadosIterator.next().getNome()+"; ");
			}
			
		}else if(criterioOrdenacao.trim().equalsIgnoreCase("reputacao")){
			List<ItemIF> saidaReputacao = new LinkedList<ItemIF>();
			Map<Integer, List<ItemIF>> mapaItensPorReputacao = new HashMap<Integer, List<ItemIF>>();
			
			Iterator<UsuarioIF> iteradorUsuarios = ciclosDeAmizade.get(seuLogin).getAmigos().iterator();
			while(iteradorUsuarios.hasNext()){
				
				List<ItemIF> listaItens = new LinkedList<ItemIF>();
				UsuarioIF amigo = iteradorUsuarios.next();
				Iterator<ItemIF> iteradorItens = amigo.getItens().iterator();
				while(iteradorItens.hasNext()){
					ItemIF item = iteradorItens.next();
					if(atributo.trim().equalsIgnoreCase("nome")){
						if(item.getNome().toLowerCase().contains(chave.toLowerCase())){
							//saidaDataCriacao.add(item.getNome());
							listaItens.add(item);
						}
					}else if(atributo.trim().equalsIgnoreCase("descricao")){
						if(item.getDescricao().toLowerCase().contains(chave.toLowerCase())){
							//saidaDataCriacao.add(item.getNome());
							listaItens.add(item);
						}
					}else if(atributo.trim().equalsIgnoreCase("categoria")){
						if(item.getCategoria().toLowerCase().contains(chave.trim().toLowerCase())){
							//saidaDataCriacao.add(item.getNome());
							listaItens.add(item);
						}
					}
				}
				//já percorri itens e adicionei na lista
				//verificar se a lista nao estah vazia
				if(!listaItens.isEmpty()){
					int reputacaoAtual = amigo.getReputacao();
					if(mapaItensPorReputacao.containsKey(reputacaoAtual)){
						Iterator<ItemIF> iteradorDaListaDeItensSaidaPorUsuario = listaItens.iterator();
						while(iteradorDaListaDeItensSaidaPorUsuario.hasNext()){
							mapaItensPorReputacao.get(reputacaoAtual).add(iteradorDaListaDeItensSaidaPorUsuario.next());
						}
					}else{
						mapaItensPorReputacao.put(reputacaoAtual, listaItens);
					}
					if(tipoOrdenacao.trim().equalsIgnoreCase("crescente")){
						Collections.sort(mapaItensPorReputacao.get(reputacaoAtual), new NomeItemComparador());
					}else if(tipoOrdenacao.trim().equalsIgnoreCase("decrescente")){
						Collections.sort(mapaItensPorReputacao.get(reputacaoAtual), new NomeItemComparador());
						Collections.reverse(mapaItensPorReputacao.get(reputacaoAtual));
					}	
				}
				//limpa lista itens
				listaItens = new LinkedList<ItemIF>();
				
			}
			//após a coleta e adição de cada lista de objetos por usuario, faremos a ordenação
			Set<Integer> listaChavesReputacao = mapaItensPorReputacao.keySet();
			Object[] arrayListaChaves = listaChavesReputacao.toArray();
			Arrays.sort(arrayListaChaves);
			
			
			if(arrayListaChaves.length > 0){
				
				if(tipoOrdenacao.trim().equalsIgnoreCase("crescente")){
					for( int i = 0; i < arrayListaChaves.length; i++ ){
						Iterator<ItemIF> iteradorListaReputadacaoMapaIterator = mapaItensPorReputacao.get(i).iterator();
						while(iteradorListaReputadacaoMapaIterator.hasNext()){
							saidaReputacao.add(iteradorListaReputadacaoMapaIterator.next());
						}
					}
					
				}else if(tipoOrdenacao.trim().equalsIgnoreCase("decrescente")){
					for( int i = arrayListaChaves.length-1; i >= 0; i-- ){
						Collections.reverse(mapaItensPorReputacao.get(i));
						Iterator<ItemIF> iteradorListaReputadacaoMapaIterator = mapaItensPorReputacao.get(i).iterator();
						while(iteradorListaReputadacaoMapaIterator.hasNext()){
							saidaReputacao.add(iteradorListaReputadacaoMapaIterator.next());
						}
					}
				}
				
				
			}
			//verificar saida reputacao
			if(!saidaReputacao.isEmpty()){
				Iterator<ItemIF> iteradorDaListaReputacao = saidaReputacao.iterator();
				while(iteradorDaListaReputacao.hasNext()){
					saida.append(iteradorDaListaReputacao.next().getNome()+"; ");
				}
			}
			
		}
		
		if(saida.toString().trim().equals(""))
			return Mensagem.NENHUM_ITEM_ENCONTRADO.getMensagem();
		
		
		return saida.toString().trim().substring(0, saida.toString().trim().length()-1);
	}

	private void buscarItem(String chave, String atributo,
			List<ItemIF> saidaDataCriacao, UsuarioIF amigo) {
		Iterator<ItemIF> iteradorItens = amigo.getItens().iterator();
		while(iteradorItens.hasNext()){
			ItemIF item = iteradorItens.next();
			if(atributo.trim().equalsIgnoreCase("nome")){
				if(item.getNome().toLowerCase().contains(chave.toLowerCase())){
					//saidaDataCriacao.add(item.getNome());
					saidaDataCriacao.add(item);
				}
			}else if(atributo.trim().equalsIgnoreCase("descricao")){
				if(item.getDescricao().toLowerCase().contains(chave.toLowerCase())){
					//saidaDataCriacao.add(item.getNome());
					saidaDataCriacao.add(item);
				}
			}else if(atributo.trim().equalsIgnoreCase("categoria")){
				if(item.getCategoria().toLowerCase().contains(chave.trim().toLowerCase())){
					//saidaDataCriacao.add(item.getNome());
					saidaDataCriacao.add(item);
				}
			}
		}
	}
	
	
	public void desfazerAmizade(String seuLogin, String amigo) throws Exception {
		assertStringNaoVazia(amigo, Mensagem.LOGIN_INVALIDO.getMensagem(), Mensagem.LOGIN_INVALIDO.getMensagem());
		asserteTrue(Autenticacao.existeUsuario(amigo), Mensagem.USUARIO_INEXISTENTE.getMensagem());
		UsuarioIF eu = Autenticacao.getUsuarioPorLogin(seuLogin);
		if(!ehAmigo(seuLogin, amigo)){
			throw new Exception(Mensagem.AMIZADE_INEXISTENTE.getMensagem());
		}
		
		Iterator<UsuarioIF> iteradorAmigos = ciclosDeAmizade.get(seuLogin).getAmigos().iterator();
		while(iteradorAmigos.hasNext()){
			
			UsuarioIF umAmigo = iteradorAmigos.next();
			if(amigo.trim().equalsIgnoreCase(umAmigo.getLogin().trim())){
				
				//remover requisições do usuário
				umAmigo.removerEmprestimosRequeridosPorAmigo(eu);
				umAmigo.removerEmprestimosRequeridosPorMim(eu);
				eu.removerEmprestimosRequeridosPorMim(umAmigo);
				eu.removerEmprestimosRequeridosPorAmigo(umAmigo);
				
				//remover usuario da lista
				umAmigo.removerAmigoDaLista(eu);
				iteradorAmigos.remove();
			}
		}
	}
	
	
	public void removerAmigoDaLista(String seuLogin, UsuarioIF amigo) {
		ciclosDeAmizade.get(seuLogin).getAmigos().remove(amigo);		
	}
	
	
	public List<UsuarioIF> getListaAmigos(String seuLogin) {
		return ciclosDeAmizade.get(seuLogin).getAmigos();
	}
	
}
