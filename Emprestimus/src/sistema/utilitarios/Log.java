package sistema.utilitarios;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Classe responsável pelo "log" do sistema.
 * 
 * @author Joeffison Silverio de Andrade, 21011853, joeffisonsa@gmail.com
 * @since 1.3
 * @version 1.0
 */
public class Log {

	/**
	 * Gera o log.
	 * 
	 * @return
	 * 		Log gerado.
	 */
	public static Logger getLogger() {

		Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
		FileHandler fh;
		try {
			fh = new FileHandler("log.txt");
			fh.setFormatter(new SimpleFormatter());
			logger.addHandler(fh);
		} catch (Exception e) {
			logger.severe(e.getMessage());
		}
		return logger;
	}

}
