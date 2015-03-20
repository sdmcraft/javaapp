package security;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.callback.UnsupportedCallbackException;

public class ConsoleCallbackHandler implements CallbackHandler {
	/**
	 * <p>
	 * Creates a callback handler that prompts and reads from the command line
	 * for answers to authentication questions. This can be used by JAAS
	 * applications to instantiate a CallbackHandler.
	 */
	public ConsoleCallbackHandler() {
	}

	/**
	 * Handles the specified set of callbacks. This class supports NameCallback
	 * and PasswordCallback.
	 * 
	 * @param callbacks
	 *            the callbacks to handle
	 * @throws IOException
	 *             if an input or output error occurs.
	 * @throws UnsupportedCallbackException
	 *             if the callback is not an instance of NameCallback or
	 *             PasswordCallback
	 */
	public void handle(Callback[] callbacks)
			throws java.io.IOException, UnsupportedCallbackException {

		for (int i = 0; i < callbacks.length; i++) {

			if (callbacks[i] instanceof NameCallback) {
				System.out.print(((NameCallback) callbacks[i]).getPrompt());
				String user = (new BufferedReader(new InputStreamReader(
						System.in))).readLine();
				((NameCallback) callbacks[i]).setName(user);
			} else if (callbacks[i] instanceof PasswordCallback) {
				System.out.print(((PasswordCallback) callbacks[i]).getPrompt());
				String pass = (new BufferedReader(new InputStreamReader(
						System.in))).readLine();
				((PasswordCallback) callbacks[i]).setPassword(pass
						.toCharArray());
			} else {
				throw (new UnsupportedCallbackException(
							callbacks[i], "Callback class not supported"));
			}
		}
	}
}
