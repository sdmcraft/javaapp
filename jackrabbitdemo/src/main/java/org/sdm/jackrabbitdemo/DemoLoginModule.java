package org.sdm.jackrabbitdemo;

import java.security.Principal;
import java.util.Map;

import javax.jcr.SimpleCredentials;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.apache.jackrabbit.api.JackrabbitSession;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.jackrabbit.core.security.authentication.CredentialsCallback;
import org.apache.jackrabbit.core.security.authentication.RepositoryCallback;

public class DemoLoginModule implements LoginModule {

	CallbackHandler callbackHandler;
	Principal myPrincipal;
	Subject subject;

	public boolean abort() throws LoginException {
		System.out.println("abort called for DemoLoginModule");
		subject.getPrincipals().remove(myPrincipal);
		return true;
	}

	public boolean commit() throws LoginException {
		System.out.println("commit called for DemoLoginModule");
		if (myPrincipal != null) {
			subject.getPrincipals().add(myPrincipal);
			myPrincipal = null;
			return true;
		}
		return true;
	}

	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		this.callbackHandler = callbackHandler;
		this.subject = subject;
		System.out.println("initialize called for DemoLoginModule");
	}

	public boolean login() throws LoginException {
		System.out.println("login called for DemoLoginModule");
		// Setup default callback handlers.
		RepositoryCallback repositoryCb = new RepositoryCallback();
		CredentialsCallback credentialsCb = new CredentialsCallback();
		try {
			callbackHandler
					.handle(new Callback[] { repositoryCb, credentialsCb });
			SimpleCredentials simpleCredentials = (SimpleCredentials) credentialsCb
					.getCredentials();
			JackrabbitSession jcrSession = (JackrabbitSession) repositoryCb
					.getSession();
			UserManager jcrUserManager = jcrSession.getUserManager();
			Authorizable authorizable = jcrUserManager
					.getAuthorizable(simpleCredentials.getUserID());
			if (authorizable != null)
				myPrincipal = authorizable.getPrincipal();
			else {
				System.out.println("User not found, creating a new one");
				myPrincipal = jcrUserManager.createUser(
						simpleCredentials.getUserID(),
						new String(simpleCredentials.getPassword()))
						.getPrincipal();
				jcrSession.save();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new LoginException(e.getMessage());
		}

	}

	public boolean logout() throws LoginException {
		subject.getPrincipals().remove(myPrincipal);
		System.out.println("logout called for DemoLoginModule");
		return true;
	}

}
