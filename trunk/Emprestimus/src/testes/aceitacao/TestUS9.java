package testes.aceitacao;


import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;

public class TestUS9 {

	public static void main(String[] args) throws Exception {
		
		run();
		
	}
	
	public static void run() {
		
		List<String> files = new ArrayList<String>();
		String file1 = System.getProperty("user.dir") + "/src/testes/aceitacao/US09.txt";
		files.add(file1);

		UserFacade9 userFacade = new UserFacade9();

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(userFacade, files);

		eaFacade.executeTests();

		System.out.println(eaFacade.getCompleteResults());
	}

}
