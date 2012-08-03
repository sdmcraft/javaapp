package org.sdm.jackrabbitdemo;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.apache.jackrabbit.api.JackrabbitSession;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.jackrabbit.core.security.authentication.RepositoryCallback;
import org.cyberneko.html.HTMLTagBalancer.Info;

public class DemoLoginModule implements LoginModule {

	CallbackHandler callbackHandler;
	MyPrincipal myPrincipal;
	Subject subject;

	
	public boolean abort() throws LoginException {
		System.out.println("abort called for FirstLoginModule");
		subject.getPrincipals().remove(myPrincipal);
		return true;
	}

	
	public boolean commit() throws LoginException {
		System.out.println("commit called for FirstLoginModule");
		if (myPrincipal != null) {
			subject.getPrincipals().add(myPrincipal);
			myPrincipal = null;
			return true;
		}
		return false;
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
        
		try {
			callbackHandler.handle(new Callback[] { repositoryCb });
			JackrabbitSession jcrSession = (JackrabbitSession) repositoryCb.getSession();
			UserManager jcrUserManager = jcrSession.getUserManager();
			jcrUserManager.getAuthorizable(arg0);
		} catch (Exception e) {
			throw new LoginException(e.getMessage());
		}

		

	}

	
	public boolean logout() throws LoginException {
		System.out.println("logout called for FirstLoginModule");
		subject.getPrincipals().remove(myPrincipal);
		return true;
	}


}
