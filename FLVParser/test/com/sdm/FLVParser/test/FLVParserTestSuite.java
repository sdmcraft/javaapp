package com.sdm.FLVParser.test;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.sdm.FLVParser.datatypes.test.TestFLV;

public class FLVParserTestSuite extends TestSuite {
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(TestFLV.class);
		return suite;
	}

}
