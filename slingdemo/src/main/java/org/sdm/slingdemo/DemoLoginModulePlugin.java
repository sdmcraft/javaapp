package org.sdm.slingdemo;

import java.security.Principal;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import javax.jcr.Credentials;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

import org.apache.sling.jcr.jackrabbit.server.security.AuthenticationPlugin;
import org.apache.sling.jcr.jackrabbit.server.security.LoginModulePlugin;
import org.osgi.framework.BundleContext;
import org.osgi.framework.Constants;
import org.osgi.framework.ServiceRegistration;

public class DemoLoginModulePlugin implements LoginModulePlugin {

	private static final Map map = new HashMap();
	static {
		map.put("aa", "11");
		map.put("aaa", "123");
		map.put("bbb", "456");
		map.put("bb", "22");
		map.put("cc", "33");
		map.put("dd", "44");
	}

	private final DemoAuthenticationHandler authHandler;

	static ServiceRegistration register(
			final DemoAuthenticationHandler authHandler,
			final BundleContext bundleContext) {
		DemoLoginModulePlugin plugin = new DemoLoginModulePlugin(authHandler);

		Hashtable properties = new Hashtable();

		// properties.put(Constants.SERVICE_DESCRIPTION,
		// "LoginModulePlugin Support for DemoAuthenticationHandler");
		// properties.put(Constants.SERVICE_VENDOR, bundleContext.getBundle()
		// .getHeaders().get(Constants.BUNDLE_VENDOR));

		return bundleContext.registerService(LoginModulePlugin.class.getName(),
				plugin, properties);
	}

	private DemoLoginModulePlugin(final DemoAuthenticationHandler authHandler) {
		this.authHandler = authHandler;
	}

	public void addPrincipals(Set arg0) {
		//System.out.println("addPrincipals is called");

	}

	public boolean canHandle(Credentials credentials) {
		System.out.println("canHandle is called");
		if (credentials instanceof SimpleCredentials) {
			SimpleCredentials creds = (SimpleCredentials) credentials;
			return "demo".equals(creds.getAttribute("demo"));
		}
		return false;
	}

	public void doInit(CallbackHandler arg0, Session arg1, Map arg2)
			throws LoginException {
		//System.out.println("doInit is called");

	}

	public AuthenticationPlugin getAuthentication(Principal arg0,
			Credentials arg1) throws RepositoryException {
		System.out.println("getAuthentication is called");
		return new AuthenticationPlugin() {			
			public boolean authenticate(Credentials credentials)
					throws RepositoryException {
				System.out.println("AuthenticationPlugin.authenticate is called");
				if (credentials instanceof SimpleCredentials) {
					String user = ((SimpleCredentials) credentials).getUserID();
					return new String(
							((SimpleCredentials) credentials).getPassword())
							.equals(map.get(user));
				}
				throw new RepositoryException(
						"Can't authenticate credentials of type: "
								+ credentials.getClass());
			}

		};
	}

	public Principal getPrincipal(Credentials credentials) {
		//System.out.println("getPrincipal is called");
		return new MyPrincipal(((SimpleCredentials)credentials).getUserID());
	}

	public int impersonate(Principal arg0, Credentials arg1)
			throws RepositoryException, FailedLoginException {
		System.out.println("impersonate is called");
		return LoginModulePlugin.IMPERSONATION_DEFAULT;
	}

}
