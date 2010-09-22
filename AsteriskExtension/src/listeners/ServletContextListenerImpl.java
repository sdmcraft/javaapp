package listeners;

import java.io.File;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletContextListenerImpl implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("Within context initialized");
		System.out.println(event.getServletContext().getRealPath("/"));
		File tempFolder = new File(event.getServletContext().getRealPath("/")
				+ File.separator + "temp");
		if (!tempFolder.exists()) {
			if (tempFolder.mkdir()) {
				System.out.println("Successfully created temp folder:"
						+ tempFolder);
			}
		} else
			System.out.println("temp folder already exists:"
					+ tempFolder.getAbsolutePath());
		event.getServletContext().setAttribute("tempFolder",
				tempFolder.getAbsolutePath());
	}

}
