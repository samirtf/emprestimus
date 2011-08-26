package testes.utilitarios;

import org.junit.Assert;
import org.junit.Test;

import codigo.utilitarios.ValidadorString;


public class TestValidadorString {
	
	private static enum MeuEnum {
		EH_NULO_PADRAO("Este dado nao pode ser nulo!"),
		EH_BRANCO_PADRAO("Este dado nao pode ser vazio!"),
		SOMENTE_ESPACOS_PADRAO("Este dado nao pode conter apenas espacos!"),
		
		EH_NULO(" nao pode ser nulo!"),
		EH_BRANCO(" nao pode ser vazio!"),
		
		SOMENTE_ESPACOS(" nao pode conter apenas espacos!"),
		
		EH_NULA(" nao pode ser nula!"),
		EH_BRANCA(" nao pode ser vazia!");
		
		private String mensagem;
		
		private MeuEnum(String mensagem) {
			this.mensagem = mensagem;
		}
		
		public String getMensagem() {
			return this.mensagem;
		}
		
		@Override
		public String toString() {
			return this.mensagem;
		}
		
	}
	
	@Test
	public void testeStringNula_MensagemPadrao() {
		Assert.assertEquals(MeuEnum.EH_NULO_PADRAO.getMensagem(), ValidadorString.ehNull(null));
		Assert.assertEquals(MeuEnum.EH_NULO_PADRAO.getMensagem(), ValidadorString.ehNull(null, null));
		Assert.assertEquals(MeuEnum.EH_NULO_PADRAO.getMensagem(), ValidadorString.ehNull("", null));
		Assert.assertEquals(MeuEnum.EH_NULO_PADRAO.getMensagem(), ValidadorString.ehNull(" ", null));
		Assert.assertEquals(MeuEnum.EH_NULO_PADRAO.getMensagem(), ValidadorString.ehNull("Este dado", null));
		
		Assert.assertEquals(MeuEnum.EH_NULO_PADRAO.getMensagem(), ValidadorString.validaString(null));
		Assert.assertEquals(MeuEnum.EH_NULO_PADRAO.getMensagem(), ValidadorString.validaString(null, null));
		Assert.assertEquals(MeuEnum.EH_NULO_PADRAO.getMensagem(), ValidadorString.validaString("", null));
		Assert.assertEquals(MeuEnum.EH_NULO_PADRAO.getMensagem(), ValidadorString.validaString(" ", null));
		Assert.assertEquals(MeuEnum.EH_NULO_PADRAO.getMensagem(), ValidadorString.validaString("Este dado", null));
	}
	
	@Test
	public void testeStringVazia_MensagemPadrao() {
		Assert.assertEquals(MeuEnum.EH_BRANCO_PADRAO.getMensagem(), ValidadorString.ehBranco(""));
		Assert.assertEquals(MeuEnum.EH_BRANCO_PADRAO.getMensagem(), ValidadorString.ehBranco(null, ""));
		Assert.assertEquals(MeuEnum.EH_BRANCO_PADRAO.getMensagem(), ValidadorString.ehBranco("", ""));
		Assert.assertEquals(MeuEnum.EH_BRANCO_PADRAO.getMensagem(), ValidadorString.ehBranco(" ", ""));
		Assert.assertEquals(MeuEnum.EH_BRANCO_PADRAO.getMensagem(), ValidadorString.ehBranco("Este dado", ""));
		
		Assert.assertEquals(MeuEnum.EH_BRANCO_PADRAO.getMensagem(), ValidadorString.validaString(""));
		Assert.assertEquals(MeuEnum.EH_BRANCO_PADRAO.getMensagem(), ValidadorString.validaString(null, ""));
		Assert.assertEquals(MeuEnum.EH_BRANCO_PADRAO.getMensagem(), ValidadorString.validaString("", ""));
		Assert.assertEquals(MeuEnum.EH_BRANCO_PADRAO.getMensagem(), ValidadorString.validaString(" ", ""));
		Assert.assertEquals(MeuEnum.EH_BRANCO_PADRAO.getMensagem(), ValidadorString.validaString("Este dado", ""));
	}
	
	@Test
	public void testeStringComApenasEspacos_MensagemPadrao() {
		Assert.assertEquals(MeuEnum.SOMENTE_ESPACOS_PADRAO.getMensagem(), ValidadorString.somenteEspacos(" "));
		Assert.assertEquals(MeuEnum.SOMENTE_ESPACOS_PADRAO.getMensagem(), ValidadorString.somenteEspacos(null, "  "));
		Assert.assertEquals(MeuEnum.SOMENTE_ESPACOS_PADRAO.getMensagem(), ValidadorString.somenteEspacos("", "   "));
		Assert.assertEquals(MeuEnum.SOMENTE_ESPACOS_PADRAO.getMensagem(), ValidadorString.somenteEspacos(" ", "    "));
		Assert.assertEquals(MeuEnum.SOMENTE_ESPACOS_PADRAO.getMensagem(), ValidadorString.somenteEspacos("Este dado", "    "));
		
		Assert.assertEquals(MeuEnum.SOMENTE_ESPACOS_PADRAO.getMensagem(), ValidadorString.validaString("             "));
		Assert.assertEquals(MeuEnum.SOMENTE_ESPACOS_PADRAO.getMensagem(), ValidadorString.validaString(null, " bla bla bla".replace("bla", "")));
		Assert.assertEquals(MeuEnum.SOMENTE_ESPACOS_PADRAO.getMensagem(), ValidadorString.validaString("", " "));
		Assert.assertEquals(MeuEnum.SOMENTE_ESPACOS_PADRAO.getMensagem(), ValidadorString.validaString(" ", "lol ".replace("lol", "")));
		Assert.assertEquals(MeuEnum.SOMENTE_ESPACOS_PADRAO.getMensagem(), ValidadorString.validaString("Este dado", "  "));
	}
	
	@Test
	public void testeStringNula_MensagemPersonalizada() {
		Assert.assertEquals("O nome"         + MeuEnum.EH_NULO.getMensagem(), ValidadorString.ehNull("O nome", null));
		Assert.assertEquals("Este parametro" + MeuEnum.EH_NULO.getMensagem(), ValidadorString.ehNull("Este parametro", null));
		Assert.assertEquals("Esta senha"     + MeuEnum.EH_NULA.getMensagem(), ValidadorString.ehNull("Esta senha", null));
		
		Assert.assertEquals("Este email"       + MeuEnum.EH_NULO.getMensagem(), ValidadorString.validaString("Este email", null));
		Assert.assertEquals("A palavra"        + MeuEnum.EH_NULA.getMensagem(), ValidadorString.validaString("A palavra", null));
		Assert.assertEquals("a coisa qualquer" + MeuEnum.EH_NULA.getMensagem(), ValidadorString.validaString("a coisa qualquer", null));
	}
	
	@Test
	public void testeStringVazia_MensagemPersonalizada() {
		Assert.assertEquals("Alguma coisa"        + MeuEnum.EH_BRANCA.getMensagem(), ValidadorString.ehBranco("Alguma coisa", new String("")));
		Assert.assertEquals("Isto"                + MeuEnum.EH_BRANCO.getMensagem(), ValidadorString.ehBranco("Isto", ""));
		Assert.assertEquals("o nome do professor" + MeuEnum.EH_BRANCO.getMensagem(), ValidadorString.ehBranco("o nome do professor", ""));
		
		Assert.assertEquals("a senha"              + MeuEnum.EH_BRANCA.getMensagem(), ValidadorString.validaString("a senha", ""));
		Assert.assertEquals("o nome da disciplina" + MeuEnum.EH_BRANCO.getMensagem(), ValidadorString.validaString("o nome da disciplina", ""));
		Assert.assertEquals("O nome do periodo"    + MeuEnum.EH_BRANCO.getMensagem(), ValidadorString.validaString("O nome do periodo", ""));
	}
	
	@Test
	public void testeStringComApenasEspacos_MensagemPersonalizada() {
		Assert.assertEquals("O codigo"        + MeuEnum.SOMENTE_ESPACOS.getMensagem(), ValidadorString.somenteEspacos("O codigo", "  "));
		Assert.assertEquals("O email"         + MeuEnum.SOMENTE_ESPACOS.getMensagem(), ValidadorString.somenteEspacos("O email", "   "));
		Assert.assertEquals("o nome do aluno" + MeuEnum.SOMENTE_ESPACOS.getMensagem(), ValidadorString.somenteEspacos("o nome do aluno", "    "));
		
		Assert.assertEquals("A frase"       + MeuEnum.SOMENTE_ESPACOS.getMensagem(), ValidadorString.validaString("A frase", " bla bla bla".replace("bla", "")));
		Assert.assertEquals("esta sentenca" + MeuEnum.SOMENTE_ESPACOS.getMensagem(), ValidadorString.validaString("esta sentenca", " "));
		Assert.assertEquals("a expressao"   + MeuEnum.SOMENTE_ESPACOS.getMensagem(), ValidadorString.validaString("a expressao", "Emprestimus ".replace("Emprestimus", "")));
	}
	
}