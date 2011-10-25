package testes.aceitacao.classes;

import java.util.ArrayList;
import java.util.List;

import testes.aceitacao.fachadas.UserFacade01;

import easyaccept.EasyAcceptFacade;

public class TestUS01 {

	public static final String SCRIPTS_PATH = "/src/testes/aceitacao/txt/";

	public static void main(String[] args) throws Exception {

		run();

	}

	public static void run() {

		List<String> files = new ArrayList<String>();
		String file1 = System.getProperty("user.dir") + SCRIPTS_PATH + "US01.txt";
		files.add(file1);

		UserFacade01 userFacade = new UserFacade01();

		EasyAcceptFacade eaFacade = new EasyAcceptFacade(userFacade, files);

		eaFacade.executeTests();

		System.out.println(eaFacade.getCompleteResults());

	}

}
