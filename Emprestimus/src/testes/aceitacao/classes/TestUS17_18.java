package testes.aceitacao.classes;

import java.util.ArrayList;
import java.util.List;

import testes.aceitacao.fachadas.UserFacade17_18;
import easyaccept.EasyAcceptFacade;

public class TestUS17_18 {

	public static void main(String[] args) throws Exception {
		
		run();
		
	}
	
	public static void run() {
		
		List<String> files = new ArrayList<String>();
		String file1 = System.getProperty("user.dir") + TestUS01.SCRIPTS_PATH + "US17_18.txt";
		files.add(file1);
	
		UserFacade17_18 userFacade = new UserFacade17_18();
	
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(userFacade, files);
	
		eaFacade.executeTests();
	
		System.out.println(eaFacade.getCompleteResults());
	}

}
