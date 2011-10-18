package testes.aceitacao.classes;

import java.util.ArrayList;
import java.util.List;

import testes.aceitacao.fachadas.UserFacade18_19;
import easyaccept.EasyAcceptFacade;

public class TestUS18_19 {

	public static void main(String[] args) throws Exception {
		
		run();
		
	}
	
	public static void run() {
		
		List<String> files = new ArrayList<String>();
		String file1 = System.getProperty("user.dir") + TestUS01.SCRIPTS_PATH + "US18_19.txt";
		files.add(file1);
	
		UserFacade18_19 userFacade = new UserFacade18_19();
	
		EasyAcceptFacade eaFacade = new EasyAcceptFacade(userFacade, files);
	
		eaFacade.executeTests();
	
		System.out.println(eaFacade.getCompleteResults());
	}

}
