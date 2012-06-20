package org.sdm.osgi.demo;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.http.HttpService;

public class Activator implements BundleActivator, ServiceListener {

	private ServiceReference httpServiceRef = null;
	private BundleContext bundleCtx = null;

	public void start(BundleContext context) throws Exception {
		System.out.println("Starting the bundle YODA YODA2!!");
		bundleCtx = context;		
		ServiceReference sRef = bundleCtx
				.getServiceReference(HttpService.class.getName());
		if (sRef != null) {
			HttpService service = (HttpService) bundleCtx
					.getService(sRef);
			service.registerServlet("/hello", new HelloServlet(),
					null, null);
			System.out
					.println("Registered servlet with path /hello");
		} else {
			System.out.println("No http service!!");
		}

		context.addServiceListener(this,
				"(&(objectClass=" + HttpService.class.getName() + "))");

	}

	public void stop(BundleContext context) throws Exception {
		context.removeServiceListener(this);

	}

	public void serviceChanged(ServiceEvent event) {
		try {
			String[] objectClass = (String[]) event.getServiceReference()
					.getProperty("objectClass");

			if (event.getType() == ServiceEvent.REGISTERED) {
				System.out.println("Ex1: Service of type " + objectClass[0]
						+ " registered.");
				if (httpServiceRef == null) {
					ServiceReference sRef = bundleCtx
							.getServiceReference(HttpService.class.getName());
					if (sRef != null) {
						HttpService service = (HttpService) bundleCtx
								.getService(sRef);
						service.registerServlet("/hello", new HelloServlet(),
								null, null);
						System.out
								.println("Registered servlet with path /hello");
					} else {
						System.out.println("No http service!!");
					}

				}
			} else if (event.getType() == ServiceEvent.UNREGISTERING) {
				System.out.println("Ex1: Service of type " + objectClass[0]
						+ " unregistered.");
			} else if (event.getType() == ServiceEvent.MODIFIED) {
				System.out.println("Ex1: Service of type " + objectClass[0]
						+ " modified.");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
