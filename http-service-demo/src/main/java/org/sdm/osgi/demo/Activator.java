package org.sdm.osgi.demo;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;

public class Activator implements BundleActivator {

	public void start(BundleContext context) throws Exception {
		System.out.println("Starting the bundle!!");
		ServiceReference sRef = context.getServiceReference(HttpService.class
				.getName());
		if (sRef != null) {
			HttpService service = (HttpService) context.getService(sRef);
			service.registerServlet("/hello", new HelloServlet(), null, null);
		}
		else
		{
			System.out.println("No http service!!");
		}

	}

	public void stop(BundleContext arg0) throws Exception {
		// TODO Auto-generated method stub

	}

}
