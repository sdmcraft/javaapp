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
			// createUser("user10", "user10", adminSession);
			userSession = (JackrabbitSession) repository
					.login(new SimpleCredentials("user18", "user17"
							.toCharArray()));
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			adminSession.logout();
			userSession.logout();

		}
	}

	public static void createUser(String uid, String pwd,
			JackrabbitSession session) throws RepositoryException {
		Session adminSession = session.impersonate(new SimpleCredentials(
				"admin", "admin".toCharArray()));
		UserManager userManager = session.getUserManager();
		userManager.createUser(uid, pwd);
		AccessControlManager aMgr = session.getAccessControlManager();
		Privilege[] privileges = new Privilege[] { aMgr
				.privilegeFromName(Privilege.JCR_ALL) };
		AccessControlList acl;
		acl = (AccessControlList) aMgr.getPolicies("/")[0];
		// remove all existing entries
		for (AccessControlEntry e : acl.getAccessControlEntries()) {
			acl.removeAccessControlEntry(e);
		}

		acl.addAccessControlEntry(userManager.getAuthorizable(uid)
				.getPrincipal(), privileges);

		// the policy must be re-set
		aMgr.setPolicy("/", acl);
		session.save();
		session.logout();
	}

}
