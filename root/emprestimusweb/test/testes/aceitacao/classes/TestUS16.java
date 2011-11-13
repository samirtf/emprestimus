package testes.aceitacao.classes;

import java.util.ArrayList;
import java.util.List;

import testes.aceitacao.fachadas.UserFacade16;

import easyaccept.EasyAcceptFacade;

public class TestUS16 {

	public static void main(String[] args) throws Exception {

		run();

	}

	public static void run() {

		List<String> files = new ArrayList<String>();
		String file1 = System.getProperty("user.dir") + TestUS01.SCRIPTS_PATH
				+ "US16.txt";
		files.add(file1);

		UserFacade16 userFacade = new UserFacade16();

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(userFacade, files);

		eaFacade.executeTests();

		System.out.println(eaFacade.getCompleteResults());
	}

}
