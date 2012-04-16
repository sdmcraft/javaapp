package org.sdm.osgi.demo;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;

public class Activator implements BundleActivator, ServiceListener {

	public void start(BundleContext context) throws Exception {
		System.out.println("Starting the bundle YODA YODA2!!");
		context.addServiceListener(this,
				"(&(objectClass=" + HttpService.class.getName() + "))");
		ServiceReference sRef = context.getServiceReference(HttpService.class
				.getName());
		if (sRef != null) {
			HttpService service = (HttpService) context.getService(sRef);
			service.registerServlet("/hello", new HelloServlet(), null, null);
		} else {
			System.out.println("No http service!!");
		}

	}

	public void stop(BundleContext context) throws Exception {
		context.removeServiceListener(this);

	}

	public void serviceChanged(ServiceEvent event) {
		String[] objectClass = (String[]) event.getServiceReference()
				.getProperty("objectClass");

		if (event.getType() == ServiceEvent.REGISTERED) {
			System.out.println("Ex1: Service of type " + objectClass[0]
					+ " registered.");
		} else if (event.getType() == ServiceEvent.UNREGISTERING) {
			System.out.println("Ex1: Service of type " + objectClass[0]
					+ " unregistered.");
		} else if (event.getType() == ServiceEvent.MODIFIED) {
			System.out.println("Ex1: Service of type " + objectClass[0]
					+ " modified.");
		}

	}

}
