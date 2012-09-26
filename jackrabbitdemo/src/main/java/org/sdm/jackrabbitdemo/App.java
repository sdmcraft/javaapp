package org.sdm.jackrabbitdemo;

import javax.jcr.LoginException;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.security.AccessControlEntry;
import javax.jcr.security.AccessControlList;
import javax.jcr.security.AccessControlManager;
import javax.jcr.security.Privilege;

import org.apache.jackrabbit.api.JackrabbitSession;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.jackrabbit.core.TransientRepository;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws LoginException,
			RepositoryException {
		Repository repository = null;
		JackrabbitSession adminSession = null;
		Session userSession = null;
		try {
			repository = new TransientRepository();
			adminSession = (JackrabbitSession) repository
					.login(new SimpleCredentials("admin", "admin".toCharArray()));
			// createUser("user3", "user3", repository, adminSession);
			userSession = (JackrabbitSession) repository
					.login(new SimpleCredentials("user8", "user8".toCharArray()));
		} finally {
			adminSession.logout();
			userSession.logout();

		}
}
