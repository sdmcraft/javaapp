package com.sdm.FLVParser.datatypes.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;

import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sdm.FLVParser.datatypes.FLV;

public class TestFLV extends TestCase {

	private static File testDataDir;
	private static File testLog;
	private static BufferedWriter testLogWriter;

	@BeforeClass
	public static void oneTimeSetup() throws IOException {
		testDataDir = new File("testData");
		testLog = new File(testDataDir, "test.log");
		testLogWriter = new BufferedWriter(new FileWriter(testLog));
	}

	@AfterClass
	public static void oneTimeDestroy() throws IOException {
		if (testLogWriter != null)
			testLogWriter.close();
	}

	@Test
	public void testFLV() {
		String[] flvFiles = testDataDir.list(getFileExtFilter("flv"));
		for (String flvFile : flvFiles) {
			try {
				testLogWriter.write("Parsing " + flvFile);
				FLV flv = new FLV(flvFile);
				testLogWriter.write(".....done");
				testLogWriter.newLine();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	final static FilenameFilter getFileExtFilter(final String ext) {
		return new FilenameFilter() {
			public boolean accept(File dir, String fileName) {
				return fileName.endsWith("." + ext);
			}
		};
	}
}
