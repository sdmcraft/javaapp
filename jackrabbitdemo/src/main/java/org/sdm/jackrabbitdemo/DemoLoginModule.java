package org.sdm.jackrabbitdemo;

import java.security.Principal;
import java.util.Map;

import javax.jcr.Credentials;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

import org.apache.jackrabbit.api.JackrabbitSession;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.User;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.jackrabbit.core.security.authentication.DefaultLoginModule;

public class DemoLoginModule extends DefaultLoginModule {

	Session session;
	UserManager userManager;

	@Override
	protected void doInit(CallbackHandler callbackHandler, Session session,
			Map options) throws LoginException {
		super.doInit(callbackHandler, session, options);
		this.session = session;
		try {
			userManager = ((JackrabbitSession) session).getUserManager();
		} catch (Exception e) {
			throw new LoginException("Unable to initialize LoginModule: "
					+ e.getMessage());
		}
	}

	// @Override
	// protected Principal getPrincipal(final Credentials credentials) {
	//
	// final String userId = getUserID(credentials);
	// Principal principal = principalProvider.getPrincipal(userId);
	// try {
	// userManager = ((JackrabbitSession) session).getUserManager();
	//
	// if (principal == null) {
	// System.out.println("Creating a new principal");
	// principal = new Principal() {
	// @Override
	// public String getName() {
	// return userId;
	// }
	// };
	// SimpleCredentials simpleCredentials = (SimpleCredentials) credentials;
	// principal = userManager.createUser(userId,
	// new String(simpleCredentials.getPassword()), principal,
	// null).getPrincipal();
	//
	// }
	// user = (User) userManager.getAuthorizable(principal);
	// } catch (Exception ex) {
	// ex.printStackTrace();
	// }
	// return principal;
	// }

	@Override
	protected Principal getPrincipal(Credentials credentials) {
		Principal principal = null;
		final String userId = getUserID(credentials);
		try {
			Authorizable authrz = userManager.getAuthorizable(userId);
			if (authrz == null) {
				System.out.println("Creating a new principal");
				principal = new Principal() {
					@Override
					public String getName() {
						return userId;
					}
				};
				SimpleCredentials simpleCredentials = (SimpleCredentials) credentials;
				user = userManager.createUser(userId, new String(
						simpleCredentials.getPassword()), principal, null);
			} else if (authrz != null && !authrz.isGroup()) {
				user = (User) authrz;
				if (user.isDisabled()) {
					// log message and return null -> login module returns
					// false.
					System.out
							.println("User " + userId + " has been disabled.");
				}
			}
			principal = user.getPrincipal();

		} catch (RepositoryException e) {
			// should not get here
			System.out.println("Error while retrieving principal."
					+ e.getMessage());
		}
		return principal;
	}

}
