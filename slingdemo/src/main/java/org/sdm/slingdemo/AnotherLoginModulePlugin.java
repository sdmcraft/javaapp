package org.sdm.slingdemo;

import java.security.Principal;
import java.util.Map;
import java.util.Set;

import javax.jcr.Credentials;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.sling.auth.core.spi.AuthenticationHandler;
import org.apache.sling.jcr.jackrabbit.server.security.AuthenticationPlugin;
import org.apache.sling.jcr.jackrabbit.server.security.LoginModulePlugin;

@Component(metatype = false, label = "Another Login Module Plugin", description = "Another Login Module Plugin")
@Service(value = LoginModulePlugin.class)
public class AnotherLoginModulePlugin implements LoginModulePlugin {

	@Override
	public void addPrincipals(Set arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean canHandle(Credentials credentials) {
		System.out.println("AnotherLoginModulePlugin ------> canHandle is called");
//		if (credentials instanceof SimpleCredentials) {
//			SimpleCredentials creds = (SimpleCredentials) credentials;
//			return "demo".equals(creds.getAttribute("demo"));
//		}
		return false;
	}

	@Override
	public void doInit(CallbackHandler arg0, Session arg1, Map arg2)
			throws LoginException {
		System.out.println("AnotherLoginModulePlugin ------> doInit is called");

	}

	@Override
	public AuthenticationPlugin getAuthentication(Principal arg0,
			Credentials arg1) throws RepositoryException {
		System.out.println("AnotherLoginModulePlugin ------> getAuthentication is called");
		return new AuthenticationPlugin() {			
			public boolean authenticate(Credentials credentials)
					throws RepositoryException {
				System.out.println("AnotherLoginModulePlugin ------> AuthenticationPlugin.authenticate is called");
				if (credentials instanceof SimpleCredentials) {
					String user = ((SimpleCredentials) credentials).getUserID();
					return true;
				}
				throw new RepositoryException(
						"Can't authenticate credentials of type: "
								+ credentials.getClass());
			}

		};
	}

	@Override
	public Principal getPrincipal(Credentials arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int impersonate(Principal arg0, Credentials arg1)
			throws RepositoryException, FailedLoginException {
		// TODO Auto-generated method stub
		return 0;
	}

}
