package security;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class Demo {
	public static void main(String[] args) {

		Subject subject = null;
		try {

			System.out.print("User ID:");
			String user = (new BufferedReader(new InputStreamReader(
					System.in))).readLine();
			System.out.print("Password:");
			String pwd = (new BufferedReader(new InputStreamReader(
					System.in))).readLine();

			PassiveCallbackHandler pch = new PassiveCallbackHandler(user, pwd
					.toCharArray());

			LoginContext lc = new LoginContext("Demo", pch);
			try {
				lc.login();

				subject = lc.getSubject();

				Iterator it = subject.getPrincipals().iterator();
				while (it.hasNext())
					System.out
							.println("Authenticated: " + it.next().toString());

				lc.logout();
			} catch (LoginException lex) {
				System.out.println(lex.getClass().getName() + ": "
						+ lex.getMessage());
			}

		} catch (Exception ex) {
			System.out
					.println(ex.getClass().getName() + ": " + ex.getMessage());
		}

		System.exit(0);
	}
}
