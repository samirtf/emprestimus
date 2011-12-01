package iu.web.client.view;

public enum Imagem {
	PERFIL_DEFAULT("https://lh3.googleusercontent.com/-ekfOc_ZcyPM/TtbG37slaHI/AAAAAAAAAIU/2E5q9H1LWJc/s213/default-profile.png"),
	SOLICITACOES("https://lh3.googleusercontent.com/-QEqvNoTol9k/TtbG1pyK_RI/AAAAAAAAAG8/5taAPvv6YNY/s43/09.png"),
	REQUISITACOES("https://lh4.googleusercontent.com/-xfCNDXnN4lM/TtbG07Oad4I/AAAAAAAAAGg/eVSegsBaSbE/s49/06.png"),
	NOVA_MENSAGEM("https://lh3.googleusercontent.com/-1jgLHvTZ_zc/TtbG1HOwcRI/AAAAAAAAAGs/wXqzrCPxGRo/s42/07.png"),
	BACK_ITEM("https://lh3.googleusercontent.com/-reWmsym4Axo/TtbG3H8AlGI/AAAAAAAAAIE/QwjbyMkaJmY/s40/back-item.png"),
	NEW("https://lh4.googleusercontent.com/-niWkH-cRvDM/TtbG00NuU6I/AAAAAAAAAGc/1Lo263d6Qac/s64/05.png"),
	LOGO_PRINCIPAL("https://lh3.googleusercontent.com/-ZeE_VuLXllc/TtbG8Acg-bI/AAAAAAAAAI0/okTCjb8VWv4/s873/logo2.png"),
	INICIAL_1("https://lh3.googleusercontent.com/-hHc7NychYkU/TtbG0NG04FI/AAAAAAAAAGE/XmYFbOhxJ1E/s100/01.jpg"),
	INICIAL_2("https://lh6.googleusercontent.com/-u6XwqfuFyC4/Tta81IWrexI/AAAAAAAAAE8/exlToYFKtnw/s180/1232583652_4561_full.gif"),
	INICIAL_3("https://lh5.googleusercontent.com/-3Yj_bk5AK2k/TtbG0fmdWqI/AAAAAAAAAGM/IO4FgUukVjk/s100/03.jpg"),
	BACK("https://lh6.googleusercontent.com/-ZgKeWaW4ofE/TtbG3EQzc9I/AAAAAAAAAH0/kys1c4-5FRc/s100/back.png");
	
	private String endereco;
	
	private Imagem(String endereco) {
		this.endereco = endereco;
	}
	
	public String getEndereco() {
		return endereco;
	}
	
	@Override
	public String toString() {
		return this.endereco;
	}
	
}
