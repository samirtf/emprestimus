package testes.aceitacao.classes;

import java.util.ArrayList;
import java.util.List;

import testes.aceitacao.fachadas.UserFacade09;

import easyaccept.EasyAcceptFacade;

public class TestUS09 {

	public static void main(String[] args) throws Exception {

		run();

	}

	public static void run() {

		List<String> files = new ArrayList<String>();
		String file1 = System.getProperty("user.dir") + TestUS01.SCRIPTS_PATH
				+ "US09.txt";
		files.add(file1);

		UserFacade09 userFacade = new UserFacade09();

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(userFacade, files);

		eaFacade.executeTests();

		System.out.println(eaFacade.getCompleteResults());
	}

}
