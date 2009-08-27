package com.sdm.FLVParser.utils;

import com.sdm.FLVParser.datatypes.U16;
import com.sdm.FLVParser.datatypes.U8;

public class Tools {

	public static int U16toInt(U16 value) throws Exception {
		int result = 0;
		byte[] valueBytes = value.getValue();
		if (valueBytes.length != 2)
			throw new Exception("Invalid U16 value");
		else {
			result = ((valueBytes[0] & 0xFF) << 8) | ((valueBytes[1] & 0xFF));
		}
		return result;
	}

	public static U8[] byteArrToU8Arr(byte[] byteArr) throws Exception {
		U8[] result = new U8[byteArr.length];
		for (int i = 0; i < byteArr.length; i++)
			result[i] = new U8(byteArr[i]);
		return result;
	}

	public static void main(String[] args) throws Exception {
		byte b1 = 1;
		byte b2 = 5;
		U16 u16 = new U16(new byte[] { b1, b2 });
		System.out.println(U16toInt(u16));
	}

}
