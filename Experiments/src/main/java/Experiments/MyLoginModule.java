package Experiments;

import java.io.IOException;
import java.util.Map;

import javax.jcr.SimpleCredentials;
import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

import org.apache.jackrabbit.core.security.SystemPrincipal;
import org.apache.jackrabbit.core.security.authentication.CredentialsCallback;

public class MyLoginModule implements LoginModule {

	MyPrincipal myPrincipal;
	Subject subject;
	CallbackHandler callbackHandler;

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
		try {
			callbackHandler.handle(new Callback[] { credentialsCallback });
			SimpleCredentials credentials = (SimpleCredentials) (credentialsCallback
					.getCredentials());
			return Users.validate(credentials.getUserID(), new String(
					credentials.getPassword()));
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
