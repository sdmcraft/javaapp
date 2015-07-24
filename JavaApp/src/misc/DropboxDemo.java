package misc;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import com.dropbox.client2.DropboxAPI;
//import com.dropbox.client2.DropboxAPI.Entry;
//import com.dropbox.client2.exception.DropboxException;
//import com.dropbox.client2.jsonextract.JsonExtractionException;
//import com.dropbox.client2.jsonextract.JsonList;
//import com.dropbox.client2.jsonextract.JsonMap;
//import com.dropbox.client2.jsonextract.JsonThing;
//import com.dropbox.client2.session.AccessTokenPair;
//import com.dropbox.client2.session.AppKeyPair;
//import com.dropbox.client2.session.Session;
//import com.dropbox.client2.session.WebAuthSession;

public class DropboxDemo {
	/*public static final String STATE_FILE = "DropboxDemo.json";

	public static void main(String[] args) throws DropboxException {
		doFileListHacked();
		if(1!=2)
			return;
		if (args.length == 0) {
			printUsage(System.out);
			throw die();
		}

		String command = args[0];
		if (command.equals("reset")) {
			doReset(args);
		} else if (command.equals("link")) {
			doLink(args);
		} else if (command.equals("list")) {
			doList(args);
		} else if (command.equals("copy")) {
			doCopy(args);
		}else if (command.equals("file-list")) {
			doFileList();
		}  
		else {
			System.err.println("ERROR: Unknown command: \"" + command + "\"");
			System.err.println("Run with no arguments for help.");
			throw die();
		}
	}

	// ------------------------------------------------------------------------
	// Reset state

	private static void doReset(String[] args) throws DropboxException {
		AppKeyPair appKeyPair = new AppKeyPair("2hv8zb2liw7l2xo", "8a75nirc8qac2lb");

		// Save state
		State state = new State(appKeyPair);
		state.save(STATE_FILE);
	}

	// ------------------------------------------------------------------------
	// Link another account.

	private static void doLink(String[] args) throws DropboxException {
		if (args.length != 1) {
			throw die("ERROR: \"link\" takes no arguments.");
		}

		// Load state.
		State state = State.load(STATE_FILE);

		WebAuthSession was = new WebAuthSession(state.appKey,
				Session.AccessType.APP_FOLDER);

		// Make the user log in and authorize us.
		WebAuthSession.WebAuthInfo info = was.getAuthInfo();
		System.out.println("1. Go to: " + info.url);
		System.out.println("2. Allow access to this app.");
		System.out.println("3. Press ENTER.");

		try {
			while (System.in.read() != '\n') {
			}
		} catch (IOException ex) {
			throw die("I/O error: " + ex.getMessage());
		}

		// This will fail if the user didn't visit the above URL and hit
		// 'Allow'.
		String uid = was.retrieveWebAccessToken(info.requestTokenPair);
		AccessTokenPair accessToken = was.getAccessTokenPair();
		System.out.println("Link successful.");

		state.links.put(uid, accessToken);
		state.save(STATE_FILE);
	}

	// ------------------------------------------------------------------------
	// Link another account.

	private static void doList(String[] args) throws DropboxException {
		if (args.length != 1) {
			throw die("ERROR: \"list\" takes no arguments.");
		}

		// Load state.
		State state = State.load(STATE_FILE);

		if (state.links.isEmpty()) {
			System.out.println("No links.");
		} else {
			System.out.println("[uid: access token]");
			for (Map.Entry<String, AccessTokenPair> link : state.links
					.entrySet()) {
				AccessTokenPair at = link.getValue();
				System.out.println(link.getKey() + ": " + at.key + " "
						+ at.secret);
			}
		}
	}

	// ------------------------------------------------------------------------
	// Copy a file

	private static void doCopy(String[] args) throws DropboxException {
		if (args.length != 3) {
			throw die("ERROR: \"copy\" takes exactly two arguments");
		}

		// Load cached state.
		State state = State.load(STATE_FILE);

		GlobalPath source, target;
		try {
			source = GlobalPath.parse(args[1]);
		} catch (GlobalPath.FormatException ex) {
			throw die("ERROR: Bad <source>: " + ex.getMessage());
		}
		try {
			target = GlobalPath.parse(args[2]);
		} catch (GlobalPath.FormatException ex) {
			throw die("ERROR: Bad <source>: " + ex.getMessage());
		}

		AccessTokenPair sourceAccess = state.links.get(source.uid);
		if (sourceAccess == null) {
			throw die("ERROR: <source> refers to UID that isn't linked.");
		}
		AccessTokenPair targetAccess = state.links.get(target.uid);
		if (targetAccess == null) {
			throw die("ERROR: <target> refers to UID that isn't linked.");
		}

		// Connect to the <source> UID and create a copy-ref.
		WebAuthSession sourceSession = new WebAuthSession(state.appKey,
				Session.AccessType.DROPBOX, sourceAccess);
		DropboxAPI<?> sourceClient = new DropboxAPI<WebAuthSession>(
				sourceSession);
		DropboxAPI.CreatedCopyRef cr = sourceClient.createCopyRef(source.path);

		// Connect to the <target> UID and add the target file.
		WebAuthSession targetSession = new WebAuthSession(state.appKey,
				Session.AccessType.DROPBOX, targetAccess);
		DropboxAPI<?> targetClient = new DropboxAPI<WebAuthSession>(
				targetSession);
		targetClient.addFromCopyRef(cr.copyRef, target.path);

		System.out.println("Copied.");
	}

	private static void doFileList() throws DropboxException {
		State state = State.load(STATE_FILE);
		WebAuthSession session = new WebAuthSession(state.appKey,
				Session.AccessType.APP_FOLDER);
		session.setAccessTokenPair(state.links.get("76292055"));
		DropboxAPI<?> client = new DropboxAPI<WebAuthSession>(session);
		DropboxAPI.Entry metadata = client.metadata("/abc",
				-1, null, true, null);
		for (Entry entry : metadata.contents) {
			System.out.println(entry.fileName());
		}
	}

	private static final class GlobalPath {
		public final String uid;
		public final String path;

		private GlobalPath(String uid, String path) {
			this.uid = uid;
			this.path = path;
		}

		/**
		 * Parse a 'global path' string of the form "[uid]:[path]" into UID and
		 * path parts.
		 */
		/*public static GlobalPath parse(String s) throws FormatException {
			int colonPos = s.indexOf(':');
			if (colonPos < 0)
				throw new FormatException("missing colon");
			String uid = s.substring(0, colonPos);
			String path = s.substring(colonPos + 1);
			if (uid.length() == 0)
				throw new FormatException("empty UID");
			if (!path.startsWith("/"))
				throw new FormatException("path doesn't start with \"/\": \""
						+ path + "\"");
			return new GlobalPath(uid, path);
		}

		public static final class FormatException extends Exception {
			public FormatException(String message) {
				super(message);
			}
		}
	}

	// ------------------------------------------------------------------------

	private static void printUsage(PrintStream out) {
		out.println("Usage:");
		out.println("    ./run reset <app-key> <secret>  Initialize the state with the given app key.");
		out.println("    ./run link                      Link an account to this app.");
		out.println("    ./run list                      List accounts that have been linked.");
		out.println("    ./run copy <source> <target>    Copy a file; paths are of the form <uid>:<path>.");
		out.println("    ./run file-list");
	}

	private static RuntimeException die(String message) {
		System.err.println(message);
		return die();
	}

	private static RuntimeException die() {
		System.exit(1);
		return new RuntimeException();
	}

	// ------------------------------------------------------------------------
	// State model (load+save to JSON)

	public static final class State {
		public final AppKeyPair appKey;
		public final Map<String, AccessTokenPair> links = new HashMap<String, AccessTokenPair>();

		public State(AppKeyPair appKey) {
			this.appKey = appKey;
		}

		public void save(String fileName) {
			JSONObject jstate = new JSONObject();

			// Convert app key
			JSONArray japp = new JSONArray();
			japp.add(appKey.key);
			japp.add(appKey.secret);
			jstate.put("app_key", japp);

			// Convert 'Link' objects (uid -> access token)
			JSONObject jlinks = new JSONObject();
			for (Map.Entry<String, AccessTokenPair> link : links.entrySet()) {
				String uid = link.getKey();
				AccessTokenPair access = link.getValue();
				JSONArray jaccess = new JSONArray();
				jaccess.add(access.key);
				jaccess.add(access.secret);
				jlinks.put(uid, jaccess);
			}
			jstate.put("links", jlinks);

			try {
				FileWriter fout = new FileWriter(fileName);
				try {
					jstate.writeJSONString(fout);
				} finally {
					fout.close();
				}
			} catch (IOException ex) {
				throw die("ERROR: unable to save to state file \"" + fileName
						+ "\": " + ex.getMessage());
			}
		}

		public static State load(String fileName) {
			JsonThing j;
			try {
				FileReader fin = new FileReader(fileName);
				try {
					j = new JsonThing(new JSONParser().parse(fin));
				} catch (ParseException ex) {
					throw die("ERROR: State file \"" + fileName
							+ "\" isn't valid JSON: " + ex.getMessage());
				} finally {
					fin.close();
				}
			} catch (IOException ex) {
				throw die("ERROR: unable to load state file \"" + fileName
						+ "\": " + ex.getMessage());
			}

			try {
				JsonMap jm = j.expectMap();

				JsonList japp = jm.get("app_key").expectList();
				AppKeyPair appKey = new AppKeyPair(japp.get(0).expectString(),
						japp.get(1).expectString());
				State state = new State(appKey);

				JsonMap jlinks = jm.get("links").expectMap();
				for (Map.Entry<String, JsonThing> jlink : jlinks) {
					JsonList jaccess = jlink.getValue().expectList();
					AccessTokenPair access = new AccessTokenPair(jaccess.get(0)
							.expectString(), jaccess.get(1).expectString());
					state.links.put(jlink.getKey(), access);
				}

				return state;
			} catch (JsonExtractionException ex) {
				throw die("ERROR: State file has incorrect structure: "
						+ ex.getMessage());
			}
		}
	}

	private static void doFileListHacked() throws DropboxException {    
		AppKeyPair appKeyPair = new AppKeyPair("2hv8zb2liw7l2xo", "8a75nirc8qac2lb");
		AccessTokenPair access = new AccessTokenPair("gmbo9ihwxp9fwfb", "wbuq4qw1lzx32m6");
		WebAuthSession session = new WebAuthSession(appKeyPair,
				Session.AccessType.APP_FOLDER);
		session.setAccessTokenPair(access);
		DropboxAPI<?> client = new DropboxAPI<WebAuthSession>(session);
		DropboxAPI.Entry metadata = client.metadata("/abc",
				-1, null, true, null);
		for (Entry entry : metadata.contents) {
			System.out.println(entry.fileName());
			System.out.println(entry.mimeType);
			System.out.println(entry.size);
			System.out.println(entry.icon);			 
		}
	}
*/
}