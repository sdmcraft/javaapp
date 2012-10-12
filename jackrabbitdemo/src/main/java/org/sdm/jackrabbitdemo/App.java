package org.sdm.jackrabbitdemo;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.PropertyIterator;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;
import javax.jcr.Value;
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
			Node root = adminSession.getRootNode();
			dump(root);
			// Store content
			// Node users = root.addNode("users");
			// adminSession.save();
			userSession = (JackrabbitSession) repository
					.login(new SimpleCredentials("user3", "user3".toCharArray()));
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

	private static void dump(Node node) throws RepositoryException {
		// First output the node path
		System.out.println(node.getPath());
		// Skip the virtual (and large!) jcr:system subtree
//		if (node.getName().equals("jcr:system")) {
//			return;
//		}

		// Then output the properties
		PropertyIterator properties = node.getProperties();
		while (properties.hasNext()) {
			Property property = properties.nextProperty();
			if (property.getDefinition().isMultiple()) {
				// A multi-valued property, print all values
				Value[] values = property.getValues();
				for (int i = 0; i < values.length; i++) {
					System.out.println(property.getPath() + " = "
							+ values[i].getString());
				}
			} else {
				// A single-valued property
				System.out.println(property.getPath() + " = "
						+ property.getString());
			}
		}

		// Finally output all the child nodes recursively
		NodeIterator nodes = node.getNodes();
		while (nodes.hasNext()) {
			dump(nodes.nextNode());
		}
	}

}
