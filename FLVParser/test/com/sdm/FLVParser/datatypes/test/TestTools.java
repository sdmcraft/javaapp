package com.sdm.FLVParser.datatypes.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;

import junit.framework.TestCase;

import org.junit.Test;

import com.sdm.FLVParser.datatypes.U16;
import com.sdm.FLVParser.utils.PushbackInputStream;
import com.sdm.FLVParser.utils.Tools;

public class TestTools extends TestCase {

	@Test
	public void testU16toInt() throws Exception {
		for (int i = 0; i <= 65535; i++) {
			DataOutputStream dout = null;
			ByteArrayOutputStream bout = null;
			ByteArrayInputStream bin = null;
			DataInputStream din = null;
			PushbackInputStream pbin = null;
			try {
				bout = new ByteArrayOutputStream();
				dout = new DataOutputStream(bout);
				dout.write(Tools.intToByteArr(i)[1]);
				dout.write(Tools.intToByteArr(i)[0]);
			} finally {
				if (bout != null)
					bout.close();
				if (dout != null)
					dout.close();
			}
			try {
				byte[] byteArr = bout.toByteArray();
				bin = new ByteArrayInputStream(byteArr);
				din = new DataInputStream(bin);
				pbin = new PushbackInputStream(din);
				U16 u16 = new U16(pbin);
				assertEquals(i, Tools.U16toInt(u16));
			} finally {
				if (bin != null)
					bin.close();
				if (din != null)
					din.close();
				if (pbin != null)
					pbin.close();
			}
		}
	}
}
