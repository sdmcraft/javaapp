package com.sdm.FLVParser.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

import com.sdm.FLVParser.datatypes.AMFBoolean;
import com.sdm.FLVParser.datatypes.AMFDate;
import com.sdm.FLVParser.datatypes.AMFLongString;
import com.sdm.FLVParser.datatypes.AMFNull;
import com.sdm.FLVParser.datatypes.AMFNumber;
import com.sdm.FLVParser.datatypes.AMFObject;
import com.sdm.FLVParser.datatypes.AMFRef;
import com.sdm.FLVParser.datatypes.AMFString;
import com.sdm.FLVParser.datatypes.AMFUndef;
import com.sdm.FLVParser.datatypes.AMFValue;
import com.sdm.FLVParser.datatypes.ECMAArray;
import com.sdm.FLVParser.datatypes.Marker;
import com.sdm.FLVParser.datatypes.Markers;
import com.sdm.FLVParser.datatypes.ObjectContent;
import com.sdm.FLVParser.datatypes.ObjectEnd;
import com.sdm.FLVParser.datatypes.ObjectProperty;
import com.sdm.FLVParser.datatypes.StrictArray;
import com.sdm.FLVParser.datatypes.U16;
import com.sdm.FLVParser.datatypes.U32;
import com.sdm.FLVParser.datatypes.U8;
import com.sdm.FLVParser.datatypes.XMLDoc;
import com.sdm.FLVParser.exceptions.InvalidDataException;

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
		return new U16(new byte[] { byte2, byte1 });
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
			int[] intArr = new int[byteArr.length];
			for (int i = 0; i < byteArr.length; i++)
				intArr[i] = byteArr[i] & 0xFF;
			switch (byteArr.length) {
			case 4:
				result = result + (intArr[0] << 24) + (intArr[1] << 16)
						+ (intArr[2] << 8) + intArr[3];
				break;
			case 3:
				result = result + (intArr[0] << 16) + (intArr[1] << 8)
						+ intArr[2];
				break;
			case 2:
				result = result + (intArr[0] << 8) + intArr[1];
				break;
			case 1:
				result = result + intArr[0];
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

	public static AMFValue sniffer(PushbackInputStream in) throws Exception {

		Marker marker = new Marker(in);
		AMFValue value = null;
		// System.out.println("Sniffed marker:" + marker.getByteValue());
		marker.unread(in);
		if (marker.getValue().equals(Markers.NUMBER_MARKER)) {
			value = new AMFNumber(in);
		} else if (marker.getValue().equals(Markers.BOOLEAN_MARKER)) {
			value = new AMFBoolean(in);
		} else if (marker.getValue().equals(Markers.STRING_MARKER)) {
			value = new AMFString(in);
		} else if (marker.getValue().equals(Markers.OBJECT_MARKER)) {
			value = new AMFObject(in);
		} else if (marker.getValue().equals(Markers.NULL_MARKER)) {
			value = new AMFNull(in);
		} else if (marker.getValue().equals(Markers.UNDEFINED_MARKER)) {
			value = new AMFUndef(in);
		} else if (marker.getValue().equals(Markers.REFERENCE_MARKER)) {
			value = new AMFRef(in);
		} else if (marker.getValue().equals(Markers.ECMA_ARRAY_MARKER)) {
			value = new ECMAArray(in);
		} else if (marker.getValue().equals(Markers.STRICT_ARRAY_MARKER)) {
			value = new StrictArray(in);
		} else if (marker.getValue().equals(Markers.DATE_MARKER)) {
			value = new AMFDate(in);
		} else if (marker.getValue().equals(Markers.LONG_STRING_MARKER)) {
			value = new AMFLongString(in);
		} else if (marker.getValue().equals(Markers.XML_DOCUMENT_MARKER)) {
			value = new XMLDoc(in);
		} else if (marker.getValue().equals(Markers.TYPED_OBJECT_MARKER)) {
		} else {
			throw new InvalidDataException("No AMF value to read!!!", in);
		}
		return value;
	}

	public static ObjectProperty readObjectProperty(PushbackInputStream in)
			throws Exception {
		ObjectProperty objectProperty = null;
		try {
			// //System.out.println("qqqqqqqqqqq->" + in.available());
			// System.out.println("Reading object content");
			objectProperty = new ObjectContent(in);
			// //System.out.println("Completed Reading object content");
		} catch (InvalidDataException ex) {
			// ex.printStackTrace();
			// //System.out.println("qqqqqqqqqqq->" + in.available());
			// System.out.println("Reading object end");
			objectProperty = new ObjectEnd(in);
			// //System.out.println("Object end found");
		}
		return objectProperty;
	}

	public static List<File> recursiveListFiles(File root, FilenameFilter filter) {
		List<File> resultList = null;
		if (root.isDirectory()) {
			resultList = new ArrayList<File>();
			File[] filteredFileArr = (root.listFiles(filter));
			for (File file : filteredFileArr) {
				if (file.isFile())
					resultList.add(file);
			}
			File files[] = root.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					for (File filteredFile : recursiveListFiles(file, filter)) {
						resultList.add(filteredFile);
					}
				}
			}
		}
		return resultList;
	}


	// public static void main(String[] args) throws Exception {
	// for (File f : recursiveListFiles(new
	// File("\\\\connectdev1\\C\\workspace\\branches\\breeze\\702\\content"),
	// getFileExtFilter("flv")))
	// System.out.println(f);
	// }
}
