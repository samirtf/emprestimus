package sistema.emprestimo;

import sistema.emprestimo.EmprestimoIF;
import java.util.Calendar;
import java.util.GregorianCalendar;

import sistema.excecoes.ArgumentoInvalidoException;
import sistema.item.ItemIF;
import sistema.usuario.UsuarioIF;
import sistema.utilitarios.Mensagem;
import sistema.utilitarios.Validador;

public class Emprestimo implements EmprestimoIF {

	String id;
	int duracao; // Em dias
	UsuarioIF emprestador; // quem concedeu De
	UsuarioIF beneficiado; // quem pediu Para
	ItemIF item;
	String tipo;
	EmprestimoEstado estado;
	String situacao;
	GregorianCalendar dataDeAprovacao;

	/**
	 * Cria um novo emprestimo
	 * 
	 * @param emprestador - UsuarioIF
	 * 		Objeto referente aquele que empresta
	 * @param beneficiado - UsuarioIF
	 * 		Objeto referente aquele que recebe o emprestimo
	 * @param item - ItemIF
	 * 		Item a ser emprestado
	 * @param tipo - String
	 * @param duracao - int
	 * 		Duração do emprestimo
	 * 
	 * @throws Exception
	 */
	public Emprestimo(UsuarioIF emprestador, UsuarioIF beneficiado, ItemIF item,
			String tipo, int duracao) throws Exception {
		setEmprestador(emprestador);
		setBeneficiado(beneficiado);
		setItem(item);
		setDuracao(duracao);
		Validador.assertStringNaoVazia(tipo, Mensagem.EMPRESTIMO_TIPO_INVALIDO
				.getMensagem(), Mensagem.EMPRESTIMO_TIPO_INVALIDO.getMensagem());

		if (tipo.trim().equalsIgnoreCase("emprestador")) {
			setTipoEmprestador();
		} else if (tipo.trim().equalsIgnoreCase("beneficiado")) {
			setTipoBeneficiado();
		} else {
			throw new Exception(Mensagem.EMPRESTIMO_TIPO_INXISTENTE.getMensagem());
		}
		this.estado = EmprestimoEstado.EM_ANDAMENTO;
		this.situacao = "Andamento";
	}

	@Override
	public void setId(String idEmprestimo) throws Exception {

		Validador.assertStringNaoVazia(idEmprestimo,
				Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem(),
				Mensagem.ID_REQUISICAO_EMPRESTIMO_INVALIDO.getMensagem());
		try {
			Long id = Long.valueOf(idEmprestimo.trim());
		} catch (Exception e) {
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

	@Override
	public ItemIF getItem() {
		return this.item;
	}

	@Override
	public void setDuracao(int duracao) throws Exception {
		if (duracao <= 0)
			throw new Exception(Mensagem.EMPRESTIMO_DURACAO_INVALIDA.getMensagem());
		this.duracao = duracao;
	}

	@Override
	public int getDuracao() {
		return this.duracao;
	}

	@Override
	public void setEmprestador(UsuarioIF emprestador) throws ArgumentoInvalidoException {
		Validador.assertNaoNulo(emprestador, "UsuarioIF não deve ser null");
		this.emprestador = emprestador;
	}

	@Override
	public UsuarioIF getEmprestador() {
		return this.emprestador;
	}

	@Override
	public void setBeneficiado(UsuarioIF beneficiado) throws ArgumentoInvalidoException {
		Validador.assertNaoNulo(emprestador, "UsuarioIF não deve ser null");
		this.beneficiado = beneficiado;
	}

	@Override
	public UsuarioIF getBeneficiado() {
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
	public boolean estaAprovado() {
		return this.dataDeAprovacao == null;
	}

	@Override
	public String getEstado() {
		return estado.getNome();
	}

	@Override
	public EmprestimoEstado getEstadoEnum() {
		return estado;
	}

	@Override
	public String getNomeParaEstado() {
		if (estado == EmprestimoEstado.EM_ANDAMENTO) {
			return "EM_ANDAMENTO";

		} else if (estado == EmprestimoEstado.DEVOLVIDO) {
			return "DEVOLVIDO";

		} else if (estado == EmprestimoEstado.DEVOLUCAO_REQUISITADA) {
			return "DEVOLUCAO_REQUISITADA";

		} else if (estado == EmprestimoEstado.TERMINAL) {
			return "TERMINAL";

		} else {
			return " => Bug em Emprestimus.getNomeParaEstado()";
		}

	}

	@Override
	public EmprestimoEstado getTipoEstado() {
		return this.estado;
	}

	@Override
	public synchronized Calendar getDataDeDevolucao() {
		Calendar dataDevolucao = (Calendar) dataDeAprovacao.clone();
		dataDevolucao.add(Calendar.DAY_OF_YEAR, duracao);
		return dataDevolucao;
	}

	@Override
	public void setSituacaoAndamento() {
		this.situacao = "Andamento";
	}

	@Override
	public void setSituacaoCancelado() {
		this.situacao = "Cancelado";
	}

	@Override
	public void setSituacaoCompletado() {
		this.situacao = "Completado";
	}

	@Override
	public boolean ehSituacaoAndamento() {
		return this.situacao.equals("Andamento");
	}

	@Override
	public boolean ehSituacaoCancelado() {
		return this.situacao.equals("Cancelado");
	}

	@Override
	public boolean ehSituacaoCompletado() {
		return this.situacao.equals("Completado");
	}

	@Override
	public synchronized void aprovarEmprestimo() throws Exception {
		if (ehEstadoAndamento()) {
			setDataAprovacao();
			setEstadoAndamento();
			setSituacaoAndamento();

		} else if (ehEstadoDevolucaoRequisitada()) {
			throw new Exception(
					"Transicao aprovarEmprestimo de Devolucao Requisitada Nao Permitida");

		} else if (ehEstadoDevolvido()) {
			throw new Exception("Transicao aprovarEmprestimo de Devolvido Nao Permitida");

		} else if (ehEstadoTermino()) {
			throw new Exception("Transicao aprovarEmprestimo de Terminal Nao Permitida");

		} else {
			throw new Exception("Estado não implementado");

		}
		this.getItem().setDisponibilidade(false);

	}

	@Override
	public synchronized void requisitarDevolucaoEmprestimo(boolean noPrazo) throws Exception {
		if (ehEstadoAndamento()) {
			setEstadoDevolucaoRequisitada();

			if (noPrazo) {
				setSituacaoCancelado();
			} else {
				setSituacaoAndamento();
			}

		} else if (ehEstadoDevolucaoRequisitada()) {
			throw new Exception(Mensagem.DEVOLUCAO_JA_REQUISITADA.getMensagem());

		} else if (ehEstadoDevolvido()) {
			throw new Exception(Mensagem.ITEM_JA_DEVOLVIDO.getMensagem());

		} else if (ehEstadoTermino()) {
			throw new Exception(Mensagem.ITEM_JA_DEVOLVIDO.getMensagem());

		} else {
			throw new Exception("Estado não implementado");
		}

	}

	@Override
	public synchronized void devolverEmprestimo() throws Exception {
		if (ehEstadoAndamento()) {
			setEstadoDevolvido();
			setSituacaoAndamento();

		} else if (ehEstadoDevolucaoRequisitada()) {
			if (ehSituacaoAndamento()) {
				setSituacaoAndamento();

			} else {
				setSituacaoCancelado();
			}
			setEstadoDevolvido();

		} else if (ehEstadoDevolvido()) {
			throw new Exception(Mensagem.ITEM_JA_DEVOLVIDO.getMensagem());

		} else if (ehEstadoTermino()) {
			throw new Exception(Mensagem.ITEM_JA_DEVOLVIDO.getMensagem());

		} else {
			throw new Exception("Estado não implementado");
		}

	}

	@Override
	public synchronized void confirmarTerminoEmprestimo() throws Exception {
		if (ehEstadoAndamento()) {
			throw new Exception("Item não foi devolvido");

		} else if (ehEstadoDevolucaoRequisitada()) {
			throw new Exception("Item não foi devolvido");

		} else if (ehEstadoDevolvido()) {
			if (ehSituacaoAndamento()) {
				setSituacaoCompletado();

			} else {
				setSituacaoCancelado();
			}

			setEstadoTermino();
			this.getItem().setDisponibilidade(true);
			addHistoricoTerminoEmprestimo();

		} else if (ehEstadoTermino()) {
			throw new Exception(Mensagem.TERMINO_EMPRESTIMO_JA_CONFIRMADO.getMensagem());

		} else {
			throw new Exception("Estado não implementado");
		}

	}

	private void addHistoricoTerminoEmprestimo() throws Exception {
		emprestador.addHistoricoTerminoEmprestimo(item);
		// this.getEmprestador().addNotificacao(getEmprestador().getNome() +
		// " confirmou o término no empréstimo do item " + getItem().getNome());
	}

	@Override
	public boolean ehEstadoAndamento() {
		return estado == EmprestimoEstado.EM_ANDAMENTO;
	}

	@Override
	public boolean ehEstadoDevolucaoRequisitada() {
		return estado == EmprestimoEstado.DEVOLUCAO_REQUISITADA;
	}

	@Override
	public boolean ehEstadoDevolvido() {
		return estado == EmprestimoEstado.DEVOLVIDO;
	}

	@Override
	public boolean ehEstadoTermino() {
		return estado == EmprestimoEstado.TERMINAL;
	}

	@Override
	public void setEstadoAndamento() {
		estado = EmprestimoEstado.EM_ANDAMENTO;
	}

	@Override
	public void setEstadoDevolucaoRequisitada() {
		estado = EmprestimoEstado.DEVOLUCAO_REQUISITADA;
	}

	@Override
	public void setEstadoDevolvido() {
		estado = EmprestimoEstado.DEVOLVIDO;
	}

	@Override
	public void setEstadoTermino() {
		estado = EmprestimoEstado.TERMINAL;
	}

	@Override
	public String getSituacao() {
		return situacao;
	}

	@Override
	public void setDataAprovacao() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();

		}
		this.dataDeAprovacao = new GregorianCalendar();

	}

	@Override
	public int compareTo(EmprestimoIF outro) {
		Long id1 = null;
		Long id2 = null;

		try {
			id1 = Long.valueOf(this.getIdEmprestimo());
			id2 = Long.valueOf(outro.getIdEmprestimo());

		} catch (Exception e) {
			// TODO: Adicionar Tratamento de Exception!
		}

		return id1.compareTo(id2);
	}

}
