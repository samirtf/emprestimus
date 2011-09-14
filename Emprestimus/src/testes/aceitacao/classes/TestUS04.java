package testes.aceitacao.classes;


import java.util.ArrayList;
import java.util.List;

import testes.aceitacao.fachadas.UserFacade04;

import easyaccept.EasyAcceptFacade;

public class TestUS04 {

	public static void main(String[] args) throws Exception {
		
		run();
		
	}
	
	public static void run() {
		
		List<String> files = new ArrayList<String>();
		String file1 = System.getProperty("user.dir") + "/src/testes/aceitacao/US04.txt";
		files.add(file1);

		UserFacade04 userFacade = new UserFacade04();

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(userFacade, files);

		eaFacade.executeTests();

		System.out.println(eaFacade.getCompleteResults());
	}

}
