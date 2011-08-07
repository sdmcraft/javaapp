package security;

import java.util.Map;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;

public class SecondLoginModule implements LoginModule {

	CallbackHandler callbackHandler;
	MyPrincipal myPrincipal;
	Subject subject;

	@Override
	public boolean abort() throws LoginException {
		System.out.println("abort called for SecondLoginModule");
		subject.getPrincipals().remove(myPrincipal);
		return true;
	}

	@Override
	public boolean commit() throws LoginException {
		System.out.println("commit called for SecondLoginModule");
		if (myPrincipal != null) {
			subject.getPrincipals().add(myPrincipal);
			myPrincipal = null;
			return true;
		}
		return false;
	}

	@Override
	public void initialize(Subject subject, CallbackHandler callbackHandler,
			Map<String, ?> sharedState, Map<String, ?> options) {
		this.callbackHandler = callbackHandler;		
		this.subject = subject;
		System.out.println("initialize called for SecondLoginModule");
	}

	@Override
	public boolean login() throws LoginException {
		System.out.println("login called for SecondLoginModule");
		// Setup default callback handlers.
		Callback[] callbacks = new Callback[] {
					new NameCallback("Username: "),
					new PasswordCallback("Password: ", false)
			};

		try {
			callbackHandler.handle(callbacks);
		} catch (Exception e) {
			throw new LoginException(e.getMessage());
		}

		String username = ((NameCallback) callbacks[0]).getName();
		String password = new String(((PasswordCallback) callbacks[1])
					.getPassword());

		return validate(username, password);

	}

	@Override
	public boolean logout() throws LoginException {
		System.out.println("logout called for SecondLoginModule");
		subject.getPrincipals().remove(myPrincipal);
		return true;
	}

	private boolean validate(String uname, String pwd) {
		boolean success = Info.map2.containsKey(uname + "-" + pwd);
		if (success)
			myPrincipal = new MyPrincipal(Info.map2.get(uname + "-" + pwd));
		return success;
	}

}
