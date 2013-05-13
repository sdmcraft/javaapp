/*
 * Copyright (c) 2008, 2010, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import static java.nio.file.LinkOption.NOFOLLOW_LINKS;
import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_DELETE;
import static java.nio.file.StandardWatchEventKinds.ENTRY_MODIFY;
import static java.nio.file.StandardWatchEventKinds.OVERFLOW;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;

/**
 * Example to watch a directory (or tree) for changes to files.
 */

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

	@SuppressWarnings("unchecked")
	static <T> WatchEvent<T> cast(WatchEvent<?> event) {
		return (WatchEvent<T>) event;
	}

	/**
	 * Register the given directory with the WatchService
	 */
	private void register(Path dir) throws IOException {
		WatchKey key = dir.register(watcher, ENTRY_CREATE, ENTRY_DELETE,
				ENTRY_MODIFY);
		if (trace) {
			Path prev = keys.get(key);
			if (prev == null) {
				System.out.format("register: %s\n", dir);
			} else {
				if (!dir.equals(prev)) {
					System.out.format("update: %s -> %s\n", prev, dir);
				}
			}
		}
		keys.put(key, dir);
	}

	/**
	 * Register the given directory, and all its sub-directories, with the
	 * WatchService.
	 */
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

	/**
	 * Creates a WatchService and registers the given directory
	 */
	WatchDir(String dirs, boolean recursive) throws IOException {
		this.watcher = FileSystems.getDefault().newWatchService();
		this.keys = new HashMap<WatchKey, Path>();
		this.recursive = recursive;

		for (String dir : dirs.split(",")) {
			Path path = Paths.get(dir);
			if (recursive) {
				System.out.format("Scanning %s ...\n", dir);
				registerAll(path);
				System.out.println("Done.");
			} else {
				register(path);
			}
		}
		// enable trace after initial registration
		this.trace = true;
	}

	/**
	 * Process all events for keys queued to the watcher
	 */
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

				if (child
						.toString()
						.substring(child.toString().lastIndexOf(File.separator))
						.startsWith("New ")) {
					System.out.println("Skipping default new name:"
							+ child.toString());
					continue;
				}

				// print out event
				for (String ext : filters.split(",")) {
					if (child.toString().endsWith(ext)
							|| child.toFile().isDirectory()) {
						System.out.format("Processing %s: %s\n", event.kind()
								.name(), child);
						try {
							String s = child.toString().substring(
									child.toString().indexOf("jcr_root") + 8);
							s = s.replace('\\', '/');

							String command = "";
							if (child.toFile().isDirectory()) {
								command = "cmd /C " + curl + " -X MKCOL -u "
										+ cqUser + ":" + cqPwd + " " + cqHost
										+ ":" + cqPort + s;

							} else {
								command = "cmd /C " + curl + " -u " + cqUser
										+ ":" + cqPwd + " -T "
										+ child.toString() + " " + cqHost + ":"
										+ cqPort + s;
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

						// if directory is created, and watching recursively,
						// then
						// register it and its sub-directories
						if (recursive && (kind == ENTRY_CREATE)) {
							try {
								if (Files.isDirectory(child, NOFOLLOW_LINKS)) {
									registerAll(child);
								}
							} catch (IOException x) {
								// ignore to keep sample readble
							}
						}
						break;
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
		if (args.length == 0 || args.length > 7)
			usage();
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

		// register directory and process its events
		new WatchDir(workDirs, recursive).processEvents();
	}
}
