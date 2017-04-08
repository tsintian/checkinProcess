package test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger logger = LogManager.getLogger("filelog2");
		logger.debug("test debug22");
		logger.error("test error22");
	}

}
