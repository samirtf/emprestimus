package testes.aceitacao;


import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;

public class TestUS14 {

	public static void main(String[] args) throws Exception {
		
		run();
		
	}
	
	public static void run() {
		
		List<String> files = new ArrayList<String>();
		String file1 = System.getProperty("user.dir") + "/src/testes/aceitacao/US14.txt";
		files.add(file1);

		UserFacade14 userFacade = new UserFacade14();

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(userFacade, files);

		eaFacade.executeTests();

		System.out.println(eaFacade.getCompleteResults());
	}

}
