package testes.aceitacao.classes;


import java.util.ArrayList;
import java.util.List;

import testes.aceitacao.fachadas.UserFacade12;

import easyaccept.EasyAcceptFacade;

public class TestUS12 {

	public static void main(String[] args) throws Exception {
		
		run();
		
	}
	
	public static void run() {
		
		List<String> files = new ArrayList<String>();
		String file1 = System.getProperty("user.dir") + "/src/testes/aceitacao/US12.txt";
		files.add(file1);

		UserFacade12 userFacade = new UserFacade12();

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(userFacade, files);

		eaFacade.executeTests();

		System.out.println(eaFacade.getCompleteResults());
	}

}
