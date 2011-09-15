package sistema.utilitarios;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Log {

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
