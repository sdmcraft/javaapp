package com.sdm.FLVParser.datatypes.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;
import misc.Utils;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.sdm.FLVParser.datatypes.FLV;
import com.sdm.FLVParser.examples.Mapper;
import com.sdm.FLVParser.utils.Tools;

public class TestFLV extends TestCase {

	private static File testDataDir;
	private static File testLog;
	private static PrintWriter testLogWriter;

	@BeforeClass
	public static void oneTimeSetup() throws IOException {
		testDataDir = new File("testData");
		testLog = new File(testDataDir, "test.log");
		testLogWriter = new PrintWriter(new BufferedWriter(new FileWriter(
				testLog)));
	}

	@AfterClass
	public static void oneTimeDestroy() throws IOException {
		if (testLogWriter != null)
			testLogWriter.close();
	}

	@Test
	public void testFLV() throws IOException {
		try {
			boolean pass = true;
			oneTimeSetup();
			List<File> flvFiles = Tools
					.recursiveListFiles(
							new File(
									"\\\\connectdev1\\C\\workspace\\branches\\breeze\\702\\content"),
							Utils.getFileExtFilter("flv"));
			for (File flvFile : flvFiles) {
				try {
					testLogWriter.print("Parsing " + flvFile);
					FLV flv = new FLV(flvFile.getAbsolutePath());
					testLogWriter.println(".....done");
				} catch (Exception ex) {
					pass = false;
					ex.printStackTrace(testLogWriter);
				}
			}
			if (!pass)
				fail("testFLV failed");
		} finally {
			oneTimeDestroy();
		}
	}

	@Test
	public void testFLVthruMapping() throws Exception {
		File origDir = new File(testDataDir, "orig");
		File mapDir = new File(testDataDir, "mapped");
		File amMapDir = new File(testDataDir, "am-mapped");
		File idMapFile = new File(testDataDir, "idMap.ser");
		Map<String, String> map = (Map<String, String>) Utils
				.deserializeObject(idMapFile);
		for (File origFile : origDir.listFiles()) {
			FLV mappedFLV = Mapper.map(origFile.getAbsolutePath(), map);
			mappedFLV.write(new File(mapDir, origFile.getName())
					.getAbsolutePath());
		}
		assertEquals(true, Utils.compare(mapDir, amMapDir));
	}

}
