package com.sdm.support;

import java.io.File;

//import org.apache.log4j.Logger;

public class Tools {

//	private static final Logger logger = Logger
//			.getLogger(com.sdm.support.Tools.class);

	public static final void deleteFolder(File folder) throws Exception {
//		logger.debug("(+)deleteFolder(+)");
//		logger.debug("Arg:folder->" + folder);
		if (folder.isDirectory()) {
			for (File file : folder.listFiles())
				deleteFolder(file);
		}
		if (!folder.delete())
			throw new Exception("Unable to delete directory!");
		// logger.debug("(-)deleteFolder(-)");
	}

}
