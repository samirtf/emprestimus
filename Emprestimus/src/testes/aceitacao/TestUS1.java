package testes.aceitacao;


import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;

public class TestUS1 {

	public static void main(String[] args) throws Exception {
		
		run();
		
	}
	
	public static void run() {
		
		List<String> files = new ArrayList<String>();
		String file1 = System.getProperty("user.dir") + "/src/testes/aceitacao/US01.txt";
		files.add(file1);

		UserFacede1 UserFacede = new UserFacede1();

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(UserFacede, files);

		eaFacade.executeTests();

		System.out.println(eaFacade.getCompleteResults());
	}

}
