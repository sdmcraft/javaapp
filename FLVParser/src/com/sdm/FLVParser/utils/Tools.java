package com.sdm.FLVParser.utils;

import com.sdm.FLVParser.datatypes.U16;
import com.sdm.FLVParser.datatypes.U32;
import com.sdm.FLVParser.datatypes.U8;

public class Tools {

	public static int U16toInt(U16 value) throws Exception {
		int result = 0;
		byte[] valueBytes = value.getValueBytes();
		if (valueBytes.length != 2)
			throw new Exception("Invalid U16 value");
		else {
			result = ((valueBytes[0] & 0xFF) << 8) | ((valueBytes[1] & 0xFF));
		}
		return result;
	}

	public static U16 intToU16(int value) throws Exception {

		byte byte1 = (byte) (value & 0xFF);
		byte byte2 = (byte) ((value >>> 8) & 0xFF);
		return new U16(new byte[] { byte1, byte2 });
	}

	public static int U32toInt(U32 value) throws Exception {
		int result = 0;
		byte[] valueBytes = value.getValueBytes();
		if (valueBytes.length != 4)
			throw new Exception("Invalid U32 value");
		else {
			result = (valueBytes[0] << 24) | (valueBytes[1] << 16)
					| (valueBytes[2] << 8) | valueBytes[3];
		}
		return result;
	}

	public static int U8toInt(U8 value) throws Exception {
		return value.getValue() & 0xFF;
	}

	public static U8[] byteArrToU8Arr(byte[] byteArr) throws Exception {
		U8[] result = new U8[byteArr.length];
		for (int i = 0; i < byteArr.length; i++)
			result[i] = new U8(byteArr[i]);
		return result;
	}

	public static long byteArrToInt(byte[] byteArr) throws Exception {
		long result = 0;
		if (byteArr.length > 4)
			throw new Exception("Byte array too large for int!!!");
		else {
			switch (byteArr.length) {
			case 4:
				result = result + (byteArr[0] << 24) + (byteArr[1] << 16)
						+ (byteArr[2] << 8) + byteArr[3];
				break;
			case 3:
				result = result + (byteArr[0] << 16) + (byteArr[1] << 8)
						+ byteArr[2];
				break;
			case 2:
				result = result + (byteArr[0] << 8) + byteArr[1];
				break;
			case 1:
				result = result + byteArr[0];
				break;
			default:
				throw new Exception("Invalid byte array for int!!!");
			}
		}
		return result;
	}

	public static byte[] intToByteArr(int value) {
		byte[] result = new byte[4];
		result[0] = (byte) (value & 0x000000FF);
		result[1] = (byte) ((value >>> 8) & 0x000000FF);
		result[2] = (byte) ((value >>> 16) & 0x000000FF);
		result[3] = (byte) ((value >>> 24) & 0x000000FF);
		return result;
	}

	public static byte[] U8ArrToByteArr(U8[] U8Arr) throws Exception {
		byte[] byteArr = new byte[U8Arr.length];
		for (int i = 0; i < U8Arr.length; i++)
			byteArr[i] = U8Arr[i].getValue();
		return byteArr;
	}

	public static void main(String[] args) throws Exception {
		byte b1 = 1;
		byte b2 = 5;
		U16 u16 = new U16(new byte[] { b1, b2 });
		System.out.println(U16toInt(u16));
	}

}
