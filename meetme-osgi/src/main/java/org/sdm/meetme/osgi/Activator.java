package org.sdm.meetme.osgi;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	public void start(BundleContext context) throws Exception {
		System.out.println("Starting the bundle MeetMe!!");
	}

	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping the bundle MeetMe!!");
	}

}
