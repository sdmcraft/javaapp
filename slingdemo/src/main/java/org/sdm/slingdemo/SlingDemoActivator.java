package org.sdm.slingdemo;

import javax.jcr.RepositoryException;
import javax.servlet.ServletException;

import org.apache.felix.http.api.ExtHttpService;
import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.apache.jackrabbit.api.JackrabbitSession;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.framework.BundleContext;

@Component
public class SlingDemoActivator {

	@Reference
	ExtHttpService extHttpService;

	@Reference
	private SlingRepository repository;

	BundleContext bundleContext;
	FelixFilter felixFilter;

	@Activate
	public void start(BundleContext bundleContext) throws ServletException {
		this.bundleContext = bundleContext;
		this.felixFilter = new FelixFilter();
		extHttpService.registerFilter(felixFilter, "/mynode.json", null,
				0, null);
	}

	@Deactivate
	public void stop() {
		extHttpService.unregisterFilter(felixFilter);
		this.bundleContext = null;
	}
	
	private void createRootUser(String uid, String pwd) throws RepositoryException {
		JackrabbitSession session = (JackrabbitSession) repository
				.loginAdministrative(null);
		UserManager userManager = session.getUserManager();
		userManager.createUser(uid, pwd);			
		session.save();
		session.logout();
	}


}
