package org.sdm.slingdemo;

import java.io.IOException;

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.jackrabbit.api.JackrabbitSession;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.auth.core.spi.AbstractAuthenticationHandler;
import org.apache.sling.auth.core.spi.AuthenticationHandler;
import org.apache.sling.auth.core.spi.AuthenticationInfo;
import org.apache.sling.jcr.api.SlingRepository;
import org.osgi.service.component.ComponentContext;

@Component(metatype = false, label = "Demo Authentication Handler", description = "Demo Authentication Handler")
@Service(value = AuthenticationHandler.class)
@Properties({
		@Property(name = "authtype", value = "demo", propertyPrivate = true),
		@Property(name = "path", value = { "/content/connect", "/" }) })


public class DemoAuthenticationHandler extends AbstractAuthenticationHandler {

	@Reference
	private SlingRepository repository;

	public DemoAuthenticationHandler() {
		super();
	}

	
	public void dropCredentials(HttpServletRequest arg0,
			HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub

	}

	
	public AuthenticationInfo extractCredentials(HttpServletRequest arg0,
			HttpServletResponse arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean requestCredentials(HttpServletRequest arg0,
			HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	@Activate
	protected void activate(ComponentContext componentContext) {
	}
	
	private void createUser() throws RepositoryException
	{
		JackrabbitSession session = (JackrabbitSession)repository.loginAdministrative(null);
		UserManager userManager = session.getUserManager();
		userManager.createUser("test", "test");
		session.save();
		session.logout();
	}

}
