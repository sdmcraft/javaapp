package org.sdm.jackrabbitdemo;

import javax.jcr.LoginException;
import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

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
		Session session = repository.login(new SimpleCredentials("admin",
				"admin".toCharArray()));
		try {
			Node root = session.getRootNode();

			// Store content
//			Node hello = root.addNode("hello2");
//			Node world = hello.addNode("world2");
//			world.setProperty("message", "Hello, World2!");
//			session.save();

			// Retrieve content
			Node node = root.getNode("hello/world");
			System.out.println(node.getPath());
			System.out.println(node.getProperty("message").getString());

			// Remove content
			//root.getNode("hello").remove();
			session.save();
		} finally {
			session.logout();
		}
	}

	private void createUser(String uid, String pwd, Repository repository,
			JackrabbitSession session) throws RepositoryException {
		UserManager userManager = session.getUserManager();
		userManager.createUser(uid, pwd);
		session.save();
		session.logout();
	}

}
