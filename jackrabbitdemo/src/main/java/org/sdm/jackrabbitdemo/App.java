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
		Repository repository = new TransientRepository();
		JackrabbitSession adminSession = (JackrabbitSession)repository.login(new SimpleCredentials("admin",
				"admin".toCharArray()));
		//createUser("user3", "user3", repository, adminSession);
		Session userSession = (JackrabbitSession)repository.login(new SimpleCredentials("user3",
				"user3".toCharArray()));
		adminSession.logout();
		userSession.logout();
		
//		try {
//			Node root = session.getRootNode();

			// Store content
//			Node hello = root.addNode("hello2");
//			Node world = hello.addNode("world2");
//			world.setProperty("message", "Hello, World2!");
//			session.save();

			// Retrieve content
//			Node node = root.getNode("hello/world");
//			System.out.println(node.getPath());
//			System.out.println(node.getProperty("message").getString());

			// Remove content
			//root.getNode("hello").remove();
//			session.save();
//		} finally {
//			session.logout();
//		}
	}

	private static void createUser(String uid, String pwd, Repository repository,
			JackrabbitSession session) throws RepositoryException {
		UserManager userManager = session.getUserManager();
		userManager.createUser(uid, pwd);
		AccessControlManager aMgr = session.getAccessControlManager();
		Privilege[] privileges = new Privilege[] { aMgr.privilegeFromName(Privilege.JCR_ALL) };
		AccessControlList acl;
		acl = (AccessControlList) aMgr.getPolicies("/")[0];
		// remove all existing entries
		for (AccessControlEntry e : acl.getAccessControlEntries()) {
		    acl.removeAccessControlEntry(e);
		}

		acl.addAccessControlEntry(userManager.getAuthorizable(uid).getPrincipal(), privileges);

		// the policy must be re-set
		aMgr.setPolicy("/", acl);
		session.save();
		session.logout();
	}

}
