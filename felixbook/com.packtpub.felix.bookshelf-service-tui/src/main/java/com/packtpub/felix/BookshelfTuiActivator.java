package com.packtpub.felix;

import java.util.Hashtable;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class BookshelfTuiActivator implements BundleActivator {
	public void start(BundleContext bc) {
		Hashtable props = new Hashtable();
		props.put("osgi.command.scope", BookshelfServiceProxy.SCOPE);
		props.put("osgi.command.function", BookshelfServiceProxy.FUNCTIONS);
		bc.registerService(BookshelfServiceProxy.class.getName(),
				new BookshelfServiceProxy(bc), props);
	}

	public void stop(BundleContext bc) {
	}
}