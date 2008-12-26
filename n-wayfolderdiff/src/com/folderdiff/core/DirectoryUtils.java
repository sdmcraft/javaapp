/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.folderdiff.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * 
 * @author satyam
 */
public class DirectoryUtils {

	private static final Logger logger = Logger
			.getLogger(com.folderdiff.core.DirectoryUtils.class);

	public static List<File> listContents(File node) {
		List<File> list = new ArrayList<File>();
		if (node.isFile()) {
			list.add(node);
		} else if (node.isDirectory() && (node.list() != null)) {
			File[] contents = node.listFiles();
			for (File f : contents) {
				list.addAll(listContents(f));
			}
		}
		return list;
	}

	public static List<File> listContents(String root) {
		return listContents(new File(root));
	}

	public static String stripRoot(File f, String root) throws Exception {
		return stripRoot(f.toString(), root);
	}

	public static String stripRoot(String f, String root) throws Exception {
		String name = null;
		if (f.substring(0, root.length()).equals(root)) {
			name = f.substring(root.length());
		} else {
			throw new Exception(
					"Incorrect root for this file. Cannot be stripped!!!");
		}
		return name;
	}

	public static String getHelpUrl() {
		String os = System.getProperty("os.name");
		os = os.toLowerCase();
		if (os.indexOf("windows") != -1)
			return ".\\\\..\\\\docs\\\\Help.html";
		else
			return "./../docs/Help.html";
	}

	public static String getInstallDir() {
		logger.info("(+)getInstallDir(+)");
		String path = DirectoryUtils.class.getResource("/").toString();
		if ((path.startsWith("file://")) && path.endsWith("dist/")) {
			path = path.substring(6, path.lastIndexOf("dist/"));
		}                
                else if ((path.startsWith("file:/")) && path.endsWith("dist/")) {
			path = path.substring(5, path.lastIndexOf("dist/"));
		}
		else if ((path.startsWith("file://")) && path.endsWith("conf/")) {
			path = path.substring(6, path.lastIndexOf("conf/"));
		}
                else if ((path.startsWith("file:/")) && path.endsWith("conf/")) {
			path = path.substring(5, path.lastIndexOf("conf/"));
		}
		//logger.info(path);
		logger.info("(-)getInstallDir(-)");
		return path;
	}

	// public static int[] chooseRoot(File f, String[] roots) {
	// int result[] = new int[roots.length];
	// for (int i = 0; i < roots.length; i++) {
	// String root = roots[i];
	// if (f.toString().startsWith(root)) {
	// result[i] = 1;
	// }
	// }
	// return result;
	// }

	// public static void main(String[] args) {
	// List<File> list = listContents("C:\\setup");
	// for (File f : list) {
	// System.out.println(f.toString());
	// }
	// }
}
