package testes.aceitacao.classes;

import java.util.ArrayList;
import java.util.List;

import testes.aceitacao.fachadas.UserFacade17;
import easyaccept.EasyAcceptFacade;

public class TestUS17 {

	public static void main(String[] args) throws Exception {
		
		run();
		
	}
	
	public static void run() {
		
		List<String> files = new ArrayList<String>();
		String file1 = System.getProperty("user.dir") + TestUS01.SCRIPTS_PATH + "US17.txt";
		files.add(file1);
	
		UserFacade17 userFacade = new UserFacade17();
	
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(userFacade, files);
	
		eaFacade.executeTests();
	
		System.out.println(eaFacade.getCompleteResults());
	}

}
