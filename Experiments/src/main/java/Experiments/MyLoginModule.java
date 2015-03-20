package Experiments;

import java.io.IOException;
import java.util.Map;

import javax.jcr.AccessDeniedException;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.UnsupportedRepositoryOperationException;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.apache.jackrabbit.api.JackrabbitSession;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.jackrabbit.core.security.SystemPrincipal;
import org.apache.jackrabbit.core.security.authentication.CredentialsCallback;
import org.apache.jackrabbit.core.security.authentication.RepositoryCallback;

public class MyLoginModule implements LoginModule {

	MyPrincipal myPrincipal;
	Subject subject;
	CallbackHandler callbackHandler;
	Session jcrSession;

	@Override
	public boolean abort() throws LoginException {
		System.out.println("abort called for MyLoginModule");
		subject.getPrincipals().remove(myPrincipal);
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		System.out.println("commit called for MyLoginModule");
		if (myPrincipal != null) {
			subject.getPrincipals().add(myPrincipal);
			subject.getPrincipals().add(new SystemPrincipal());
			subject.getPublicCredentials().add(
					new SimpleCredentials(myPrincipal.getName(), "xxx"
							.toCharArray()));

			try {
				UserManager userManager = ((JackrabbitSession) jcrSession)
						.getUserManager();
				Authorizable authorizable = userManager
						.getAuthorizable(myPrincipal.getName());
				if (authorizable == null) {
					System.out
							.println("User does not already exist in repository, creating a new one.");
					userManager.createUser(myPrincipal.getName(), "xxx");
					jcrSession.save();
				} else {
					System.out.println("This user exists in the repository.");
				}
			} catch (AccessDeniedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (UnsupportedRepositoryOperationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (RepositoryException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			myPrincipal = null;
			return true;
		}
		return false;
	}

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		System.out.println("initialize called for MyLoginModule");
		this.callbackHandler = callbackHandler;
		this.subject = subject;
	}

	@Override
	public boolean login() throws LoginException {
		System.out.println("login called for MyLoginModule");
		CredentialsCallback credentialsCallback = new CredentialsCallback();
		RepositoryCallback repositoryCallback = new RepositoryCallback();
		try {
			callbackHandler.handle(new Callback[] { credentialsCallback,
					repositoryCallback });
			SimpleCredentials credentials = (SimpleCredentials) (credentialsCallback
					.getCredentials());
			jcrSession = repositoryCallback.getSession();
			boolean result = Users.validate(credentials.getUserID(),
					new String(credentials.getPassword()));
			if (result) {
				myPrincipal = new MyPrincipal(credentials.getUserID());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedCallbackException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return true;
	}

	@Override
	public boolean logout() throws LoginException {
		System.out.println("logout called for MyLoginModule");
		subject.getPrincipals().remove(myPrincipal);
		return true;
	}

}
