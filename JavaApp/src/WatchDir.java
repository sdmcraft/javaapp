import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class WatchDir {
	private final WatchService watcher;
	private final Map<WatchKey, Path> keys;
	private final boolean recursive;
	private boolean trace = false;
	private static String cqHost;
	private static String cqPort;
	private static String cqUser;
	private static String cqPwd;
	private static String curl;
	private static String workDirs;
	private static String filters = ".js,.jsp";

	static <T> WatchEvent<T> cast(WatchEvent<?> event) {
		return (WatchEvent<T>) event;
	}

	private void register(Path dir) throws IOException {
		WatchKey key = dir.register(this.watcher, new WatchEvent.Kind[] {
				//StandardWatchEventKinds.ENTRY_CREATE,
				//StandardWatchEventKinds.ENTRY_DELETE,
				StandardWatchEventKinds.ENTRY_MODIFY });
		if (this.trace) {
			Path prev = (Path) this.keys.get(key);
			if (prev == null) {
				System.out.format("register: %s\n", new Object[] { dir });
			} else if (!dir.equals(prev)) {
				System.out.format("update: %s -> %s\n", new Object[] { prev,
						dir });
			}
		}

		this.keys.put(key, dir);
	}

	private void registerAll(final Path start) throws IOException {
		// register directory and sub-directories
		Files.walkFileTree(start, new SimpleFileVisitor<Path>() {
			@Override
			public FileVisitResult preVisitDirectory(Path dir,
					BasicFileAttributes attrs) throws IOException {
				register(dir);

				return FileVisitResult.CONTINUE;
			}
		});
	}

	WatchDir(String dirs, boolean recursive) throws IOException {
		this.watcher = FileSystems.getDefault().newWatchService();
		this.keys = new HashMap<WatchKey, Path>();
		this.recursive = recursive;

		for (String dir : dirs.split(",")) {
			Path path = Paths.get(dir, new String[0]);
			if (recursive) {
				System.out.format("Scanning %s ...\n", new Object[] { dir });
				registerAll(path);
				System.out.println("Done.");
			} else {
				register(path);
			}
		}

		this.trace = true;
	}

	void processEvents() {
		for (;;) {
			// wait for key to be signalled
			WatchKey key;

			try {
				key = watcher.take();
			} catch (InterruptedException x) {
				return;
			}

			Path dir = keys.get(key);

			if (dir == null) {
				System.err.println("WatchKey not recognized!!");

				continue;
			}

			for (WatchEvent<?> event : key.pollEvents()) {
				WatchEvent.Kind kind = event.kind();

				// TBD - provide example of how OVERFLOW event is handled
				if (kind == OVERFLOW) {
					continue;
				}

				// Context for directory entry event is the file name of entry
				WatchEvent<Path> ev = cast(event);
				Path name = ev.context();
				Path child = dir.resolve(name);

				// print out event
				System.out.format("%s: %s\n", event.kind().name(), child);

				try {
					String s = child.toString().substring(
							child.toString().indexOf("jcr_root") + 8);
					s = s.replace('\\', '/');

					String os = System.getProperty("os.name");
					String command = curl + " -u " + cqUser + ":" + cqPwd
							+ " -T " + child.toString() + " " + cqHost + ":"
							+ cqPort + s;

					if (os.contains("Windows")) {
						command = "cmd /C " + command;
					}

					System.out.println(command);

					Process p = Runtime.getRuntime().exec(command);
					BufferedReader in = new BufferedReader(
							new InputStreamReader(p.getInputStream()));
					String line = null;

					while ((line = in.readLine()) != null) {
						System.out.println(line);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

				// if directory is created, and watching recursively, then
				// register it and its sub-directories
				if (recursive && (kind == ENTRY_CREATE)) {
					try {
						if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
							registerAll(child);
						}
					} catch (IOException x) {
						// ignore to keep sample readbale
					}
				}
			}

			// reset key and remove from set if directory no longer accessible
			boolean valid = key.reset();

			if (!valid) {
				keys.remove(key);

				// all directories are inaccessible
				if (keys.isEmpty()) {
					break;
				}
			}
		}
	}

	static void usage() {
		System.err
				.println("usage: java WatchDir dir curl-path cq-host cq-port cq-user cq-pwd curl-path [filters]");
		System.exit(-1);
	}

	public static void main(String[] args) throws IOException {
		if ((args.length == 0) || (args.length > 7)) {
			usage();
		}
		boolean recursive = true;

		workDirs = args[0];
		cqHost = args[1];
		cqPort = args[2];
		cqUser = args[3];
		cqPwd = args[4];
		curl = args[5];
		if (args.length >= 7) {
			filters = args[6];
		}

		new WatchDir(workDirs, recursive).processEvents();
	}
}