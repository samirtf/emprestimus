package testes.aceitacao;


import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;

public class TestUS4 {

	public static void main(String[] args) throws Exception {
		
		run();
		
	}
	
	public static void run() {
		
		List<String> files = new ArrayList<String>();
		String file1 = System.getProperty("user.dir") + "/src/testes/aceitacao/US04.txt";
		files.add(file1);

		UserFacade4 userFacade = new UserFacade4();

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(userFacade, files);

		eaFacade.executeTests();

		System.out.println(eaFacade.getCompleteResults());
	}

}
