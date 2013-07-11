package org.sdm.slingdemo;

import java.io.IOException;

import javax.jcr.RepositoryException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
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
import org.osgi.framework.ServiceRegistration;
import org.osgi.service.component.ComponentContext;

@Component(metatype = false, label = "Demo Authentication Handler", description = "Demo Authentication Handler")
@Service(value = AuthenticationHandler.class)
@Properties({
		@Property(name = "authtype", value = "demo", propertyPrivate = true),
		@Property(name = "path", value = { "/content/mynode" }) })
public class DemoAuthenticationHandler extends AbstractAuthenticationHandler {

	@Reference
	private SlingRepository repository;
	private ServiceRegistration loginModule;

	public DemoAuthenticationHandler() {
		super();
	}

	public void dropCredentials(HttpServletRequest arg0,
			HttpServletResponse arg1) throws IOException {
		// TODO Auto-generated method stub

	}

	public AuthenticationInfo extractCredentials(HttpServletRequest req,
			HttpServletResponse resp) {
		System.out.println("extractCredentials is called");
		if (req.getParameter("name") == null || req.getParameter("pwd") == null) {
			return AuthenticationInfo.FAIL_AUTH;
		}
		AuthenticationInfo authInfo = new AuthenticationInfo("demo",
				req.getParameter("name"), req.getParameter("pwd").toCharArray());
		authInfo.put("demo", "demo");
		return authInfo;
	}

	public boolean requestCredentials(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		
		resp.sendRedirect("/content/login.html?resource=" + req.getPathInfo());
		return true;
	}

	@Activate
	protected void activate(ComponentContext componentContext) {
		try {
			createUser("aa", "11");
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			createUser("bb", "22");
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			createUser("cc", "xxx");
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.loginModule = null;
		try {
			this.loginModule = DemoLoginModulePlugin.register(this,
					componentContext.getBundleContext());
		} catch (Throwable t) {
			t.printStackTrace();
		}
		try {
			createUser("aaa", "111");
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			createUser("bbb", "222");
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void authenticationFailed(HttpServletRequest request,
			HttpServletResponse response, AuthenticationInfo authInfo) {
		System.out.println("Authentication failed");
		super.authenticationFailed(request, response, authInfo);
	}
	
	@Override
	public boolean authenticationSucceeded(HttpServletRequest request, HttpServletResponse response, AuthenticationInfo authInfo) 
	{
		System.out.println("Authentication Succeeded");
		
		return super.authenticationSucceeded(request, response, authInfo);
	};

	@Deactivate
	protected void deactivate(ComponentContext componentContext) throws RepositoryException {
		
		JackrabbitSession session = (JackrabbitSession) repository.loginAdministrative(null);
		UserManager userManager = session.getUserManager();
		userManager.getAuthorizable("aa").remove();
		System.out.println("Deleted aa");
		userManager.getAuthorizable("bb").remove();
		System.out.println("Deleted bb");
		userManager.getAuthorizable("cc").remove();
		System.out.println("Deleted cc");
		userManager.getAuthorizable("aaa").remove();
		System.out.println("Deleted aaa");
		userManager.getAuthorizable("bbb").remove();
		System.out.println("Deleted bbb");
		if (loginModule != null) {
			loginModule.unregister();
			loginModule = null;
		}
	}

	private void createUser(String uid, String pwd) throws RepositoryException {
		JackrabbitSession session = (JackrabbitSession) repository
				.loginAdministrative(null);
		UserManager userManager = session.getUserManager();
		userManager.createUser(uid, pwd);			
		session.save();
		session.logout();
	}

}
