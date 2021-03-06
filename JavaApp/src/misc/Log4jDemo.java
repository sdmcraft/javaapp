package misc;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
public class Log4jDemo {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//BasicConfigurator.configure();
		//Logger logger = Logger.getLogger("com.foo");
		//Logger logger1 = Logger.getLogger("com.foo.bar");
		//logger.setLevel(Level.INFO);
		//logger.warn("Warning");
		//logger.debug("debug");
		//logger1.warn("Warning");
		//logger1.debug("debug");		
		//FileAppender a0 = new FileAppender(new SimpleLayout(), "a0.log");
		//FileAppender a1 = new FileAppender(new SimpleLayout(), "a1.log");
		//Logger root = Logger.getRootLogger();
		//root.addAppender(a0);
		Properties logProperties = new Properties();
		logProperties.load(new FileInputStream("conf/log4j.properties"));
		PropertyConfigurator.configure(logProperties);
		Logger x = Logger.getLogger("x");
		//x.addAppender(a1);
		x.info("this is a log message");
		x.info("Log file:"+System.getProperty("logFile"));
		//System.setProperty("logFile", "C:\\my.log");
	}

}
