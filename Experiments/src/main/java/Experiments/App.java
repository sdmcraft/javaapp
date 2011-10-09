package Experiments;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Calendar;

import javax.jcr.Node;
import javax.jcr.Repository;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.SimpleCredentials;

import org.apache.jackrabbit.api.JackrabbitSession;
import org.apache.jackrabbit.api.security.user.User;
import org.apache.jackrabbit.core.TransientRepository;

/**
 * Hello world!
 * 
 */
public class App {
	public static void main(String[] args) throws Exception {
		Repository repository = new TransientRepository();
//		Repository repository = JcrUtils.getRepository();
		JackrabbitSession session = (JackrabbitSession) repository
				.login(new SimpleCredentials("satyadeep", "P@$$w0rd"
						.toCharArray()));
		User user = ((User) session.getUserManager().getAuthorizable(
				session.getUserID()));
		try {
			Node rootNode = session.getRootNode();
			Node rootFolderNode = createFolder(rootNode, "root_folder");
			Node userNode = createFolder(rootFolderNode, "satyadeep");
			importFile(userNode, new File("temp/test.txt"), session);
			exportToXML(new File("repositoryXML.xml"), rootFolderNode, session);
			rootFolderNode.remove();
			// session.importXML(rootNode.getPath(), new FileInputStream(new
			// File(
			// "repositoryXML.xml")),
			// ImportUUIDBehavior.IMPORT_UUID_CREATE_NEW);
			session.save();
		} finally {
			session.logout();
		}
	}

	public static Node importFile(Node folderNode, File file, Session session)
			throws RepositoryException, IOException {
		// create the file node - see section 6.7.22.6 of the spec
		Node fileNode = folderNode.addNode(file.getName(), "nt:file");

		// create the mandatory child node - jcr:content
		Node resNode = fileNode.addNode("jcr:content", "nt:resource");
		resNode.setProperty("jcr:data", session.getValueFactory().createBinary(
				new FileInputStream(file)));
		Calendar lastModified = Calendar.getInstance();
		lastModified.setTimeInMillis(file.lastModified());
		resNode.setProperty("jcr:lastModified", lastModified);

		return fileNode;
	}

	public static Node createFolder(Node parentNode, String name)
			throws Exception {
		return parentNode.addNode(name, "nt:folder");
	}

	public static void exportToXML(File file, Node node, Session session)
			throws Exception {
		OutputStream out = null;
		try {
			out = new FileOutputStream(new File("repositoryXML.xml"));
			session.exportSystemView(node.getPath(), out, false, false);
		} finally {
			if (out != null)
				out.close();
		}
	}
}
