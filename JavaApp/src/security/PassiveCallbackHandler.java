package security;

import java.io.IOException;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class PassiveCallbackHandler implements CallbackHandler {

	String uname;
	char[] pwd;

	public PassiveCallbackHandler(String uname, char[] pwd) {
		this.uname = uname;
		this.pwd = pwd;
	}

	@Override
	public void handle(Callback[] callbacks) throws IOException,
			UnsupportedCallbackException {
		for (int i = 0; i < callbacks.length; i++) {
			if (callbacks[i] instanceof NameCallback) {
				((NameCallback) callbacks[i]).setName(uname);
			} else if (callbacks[i] instanceof PasswordCallback) {
				((PasswordCallback) callbacks[i]).setPassword(pwd);
			} else {
				throw new UnsupportedCallbackException(
							callbacks[i], "Callback class not supported");
			}
		}
	}
}
