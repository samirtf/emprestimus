package testesdeaceitacao;
/**
 * UFCG - CEEI - DSC- SI1
 * Projeto da Disciplina Sistema de Informacao 1 - 2011.2
 * 
 * Professor: Nazareno
 * 
 * Alunos: Samir Trajano Feitosa - samircc20092@gmail.com
 * 
 */


import java.util.ArrayList;
import java.util.List;

import easyaccept.EasyAcceptFacade;

public class TestUS1 {

	public static void main(String[] args) throws Exception {
		
		run();
		
	}
	
	public static void run() {
		
		List<String> files = new ArrayList<String>();
		String file1 = System.getProperty("user.dir") + "/src/testesdeaceitacao/US01.txt";
		files.add(file1);

		UserFacede1 UserFacede = new UserFacede1();

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(UserFacede, files);

		eaFacade.executeTests();

		System.out.println(eaFacade.getCompleteResults());
	}

}
