package listeners;

import java.util.HashMap;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class MySessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		System.out.println("MySessionListener#sessionCreated");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		System.out.println("MySessionListener#sessionDestroyed");
		Object lock = sessionEvent.getSession().getAttribute("lock");
		System.out.println("Sign:"
				+ ((HashMap<String, String>) lock).get("sign"));
		if (lock != null)
			lock.notifyAll();
	}

}
