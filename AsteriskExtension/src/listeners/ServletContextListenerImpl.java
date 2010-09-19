package listeners;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextListenerImpl implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("Within context initialized");
		if (!new File("temp").exists()) {
			if (new File("temp").mkdir())
				System.out.println("Successfully created temp folder");
		}

	}

}
