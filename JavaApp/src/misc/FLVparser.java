package misc;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.util.ArrayList;
import java.util.List;

public class FLVparser {

	private static long gBytesRead = 0;

	public static void service(String input, String output) throws Exception {
		System.out.println("(+)service(+)");
		File opFile = new File(output);
		opFile.createNewFile();
		File ipFile = new File(input);
		service(ipFile, opFile);
		System.out.println("(-)service(-)");
	}

	private static void service(File input, File output) throws Exception {
		System.out.println("(+)service(+)");
		InputStream in = null;
		OutputStream out = null;

		try {
			in = new PushbackInputStream(new FileInputStream(input), 4);
			out = new FileOutputStream(output);
			handleFLVheader(in, out);
			handleFLVBody(in, out);
		} finally {
			in.close();
			out.close();
		}
		System.out.println("(-)service(-)");
	}

	private static void handleFLVheader(InputStream in, OutputStream out)
			throws Exception {
		System.out.println("(+)handleFLVheader(+)");
		byte header[] = new byte[9];
		int bytesRead = in.read(header);
		gBytesRead += bytesRead;
		if (bytesRead != 9)
			throw new Exception("Invalid FLV header");
		else
			out.write(header);
		System.out.println("(-)handleFLVheader(-)");
	}

	private static void handleFLVBody(InputStream in, OutputStream out)
			throws Exception {
		System.out.println("(+)handleFLVBody(+)");
		handleFirstPrevTagSize(in, out);
		while (!isEOF(in, out)) {
			int size = handleTag(in, out);
			handlePrevTagSize(in, out, size);
			// if (!isTagEnd(in, out))
			// handleTag(in, out);
			// else {
			// //handleTagEndMarker(in, out);
			// handlePrevTagSize(in, out);
			// }
		}
		System.out.println("(-)handleFLVBody(-)");
	}

	private static void handleFirstPrevTagSize(InputStream in, OutputStream out)
			throws Exception {
		System.out.println("(+)handleFirstPrevTagSize(+)");
		byte firstPrevTagSize[] = new byte[4];
		int bytesRead = in.read(firstPrevTagSize);
		gBytesRead += bytesRead;
		if (bytesRead != 4)
			throw new Exception("Invalid FirstPrevTagSize");
		else {
			System.out.println("Previous tag size:"
					+ displayByteArray(firstPrevTagSize));
			out.write(firstPrevTagSize);
		}
		System.out.println("(-)handleFirstPrevTagSize(-)");
	}

	private static int handleTag(InputStream in, OutputStream out)
			throws Exception {
		System.out.println("(+)handleTag(+)");
		byte tagHeader[] = new byte[11];
		int bytesRead = in.read(tagHeader);
		int size = -1;
		gBytesRead += bytesRead;
		if (bytesRead != 11)
			throw new Exception("Invalid tagHeader");
		else {
			int tagType = tagHeader[0] & 0xFF;
			System.out.println("Tag Type:" + tagType);
			int tagDataSize = UI24toInt(new byte[] { tagHeader[1],
					tagHeader[2], tagHeader[3] });
			System.out.println("Tag data size:" + tagDataSize);
			byte[] tagData = new byte[tagDataSize];
			bytesRead = in.read(tagData);
			gBytesRead += bytesRead;

			if (tagType == 8 || tagType == 9) {
				out.write(tagHeader);
				out.write(tagData);
			} else if (tagType == 18) {
				size = handleScriptDataTag1(tagHeader, tagData, out) + 11;
			} else
				throw new Exception("Invalid tag type!!!");
		}
		System.out.println("(-)handleTag(-)");
		return size;
	}

	private static int handleScriptDataTag1(byte tagHeader[], byte[] tagData,
			OutputStream out) throws Exception {
		System.out.println("(+)handleScriptDataTag(+)");
		System.out.println("Tag Header:" + displayByteArray(tagHeader));
		System.out.println("Tag Data:" + displayByteArray(tagData));

		List<Byte> chgTagData = new ArrayList<Byte>();
		handleScriptDataObject(tagData, 0, chgTagData);
		byte[] tagDataSizeBytes = IntToUI24(chgTagData.size());

		tagHeader[1] = tagDataSizeBytes[0];
		tagHeader[2] = tagDataSizeBytes[1];
		tagHeader[3] = tagDataSizeBytes[2];

		out.write(tagHeader);
		out.write(tagData);		
		System.out.println("(-)handleScriptDataTag(-)");
		return chgTagData.size();
	}

	private static void handleScriptDataTag(byte tagHeader[], byte[] tagData,
			OutputStream out) throws Exception {
		System.out.println("(+)handleScriptDataTag(+)");
		System.out.println("Tag Header:" + displayByteArray(tagHeader));
		System.out.println("Tag Data:" + displayByteArray(tagData));
		out.write(tagHeader);
		out.write(tagData);
		System.out.println("(-)handleScriptDataTag(-)");
	}

	private static int handleScriptDataObject(byte[] tagData, int bytesRead,
			List<Byte> chgTagData) throws Exception {
		System.out.println("(+)handleScriptDataObject(+)");
		if (chgTagData == null)
			chgTagData = new ArrayList<Byte>();

		boolean done = (tagData[bytesRead] == 0x00)
				&& (tagData[bytesRead + 1] == 0x00)
				&& (tagData[bytesRead + 2] == 0x09);

		while (!done) {
			chgTagData.add(new Byte(tagData[bytesRead++]));

			byte nameLength[] = new byte[] { tagData[bytesRead++],
					tagData[bytesRead++] };

			for (int i = 0; i < nameLength.length; i++)
				chgTagData.add(new Byte(nameLength[i]));

			for (int i = 0; i < byteArrayToShort(nameLength); i++)
				chgTagData.add(tagData[bytesRead++]);

			byte type = tagData[bytesRead++];
			chgTagData.add(new Byte(type));

			switch (type) {
			case 0:/* Double */
			{
				byte[] value = new byte[8];
				byte[] newValue = new byte[8];
				for (int i = 0; i < 8; i++)
					value[i] = tagData[bytesRead++];

				ByteArrayOutputStream bout = new ByteArrayOutputStream(8);
				DataInputStream dis = new DataInputStream(
						new ByteArrayInputStream(value));
				DataOutputStream dout = new DataOutputStream(bout);
				dout.writeDouble(dis.readDouble());
				newValue = bout.toByteArray();

				for (int i = 0; i < 8; i++)
					chgTagData.add(new Byte(newValue[i]));

				bout.close();
				dout.close();
				dis.close();
			}

			case 1:/* boolean */
			{
				byte[] value = new byte[1];
				byte[] newValue = new byte[1];
				for (int i = 0; i < 1; i++)
					value[i] = tagData[bytesRead++];

				ByteArrayOutputStream bout = new ByteArrayOutputStream(1);
				DataInputStream dis = new DataInputStream(
						new ByteArrayInputStream(value));
				DataOutputStream dout = new DataOutputStream(bout);
				dout.writeBoolean(dis.readBoolean());
				newValue = bout.toByteArray();

				for (int i = 0; i < 1; i++)
					chgTagData.add(new Byte(newValue[i]));

				bout.close();
				dout.close();
				dis.close();
				break;
			}

			case 2:/* string */
			case 4:/* Movie Clip */
			{
				byte strLength[] = new byte[] { tagData[bytesRead++],
						tagData[bytesRead++] };

				byte[] value = new byte[byteArrayToShort(strLength)];

				for (int i = 0; i < byteArrayToShort(strLength); i++)
					value[i] = tagData[bytesRead++];

				String oldValue = new String(value);
				String newValue = new String(oldValue);
				byte[] arr = newValue.getBytes();
				byte[] newStrLen = shortToByteArray(newValue.length());

				for (int i = 0; i < newStrLen.length; i++)
					chgTagData.add(new Byte(newStrLen[i]));

				for (int i = 0; i < arr.length; i++)
					chgTagData.add(new Byte(arr[i]));
				break;

			}

			case 3:/* Object */
			case 10:/* Strict Array */
			{
				bytesRead = handleScriptDataObject(tagData, bytesRead,
						chgTagData);
				break;
			}

			case 5: /* Null */
			case 6: /* Undefined */
			{
				break;
			}

			case 7:/* Reference */
			{
				chgTagData.add(new Byte(tagData[bytesRead++]));
				chgTagData.add(new Byte(tagData[bytesRead++]));
				break;
			}

			case 8: /* ECMA Array */
			{
				byte lengthBytes[] = new byte[] { tagData[bytesRead++],
						tagData[bytesRead++], tagData[bytesRead++],
						tagData[bytesRead++] };

				long arrLength = byteArrayToInt(lengthBytes);

				int count = 1;

				while (count <= arrLength) {
					bytesRead = handleScriptDataObject(tagData, bytesRead,
							chgTagData);
					count++;
				}
				break;
			}

			case 11:/* Date */
			{
				for (int i = 0; i < 10; i++)
					chgTagData.add(new Byte(tagData[bytesRead++]));
				break;
			}

			case 12:/* Long String */
			{
				byte strLength[] = new byte[] { tagData[bytesRead++],
						tagData[bytesRead++], tagData[bytesRead++],
						tagData[bytesRead++] };

				byte[] value = new byte[(int) byteArrayToInt(strLength)];

				for (int i = 0; i < byteArrayToInt(strLength); i++)
					value[i] = tagData[bytesRead++];

				String oldValue = new String(value);
				String newValue = new String(oldValue);
				byte[] arr = newValue.getBytes();
				byte[] newStrLen = intToByteArray(newValue.length());

				for (int i = 0; i < newStrLen.length; i++)
					chgTagData.add(new Byte(newStrLen[i]));

				for (int i = 0; i < arr.length; i++)
					chgTagData.add(new Byte(arr[i]));
				break;
			}
			}
			done = (tagData[bytesRead] == 0x00)
					&& (tagData[bytesRead + 1] == 0x00)
					&& (tagData[bytesRead + 2] == 0x09);
		}
		if (done) {
			chgTagData.add(new Byte(tagData[bytesRead++]));
			chgTagData.add(new Byte(tagData[bytesRead++]));
			chgTagData.add(new Byte(tagData[bytesRead++]));
		}
		System.out.println("(+)handleScriptDataObject(+)");
		return bytesRead;
	}

	private static byte[] intToByteArray(long i) {
		return toByteArray(i, new byte[4]);
	}

	public static long byteArrayToInt(byte[] data) {
		if (data.length != 4)
			return 0;
		long v = 0;
		for (int i = 0; i < 3; i++) {
			v += (data[i] & 0xFFL);
			v <<= 8;
		}
		v += (data[3] & 0xFFL);
		return v;
	}

	public static byte[] shortToByteArray(int s) {
		return toByteArray(s, new byte[2]);
	}

	private static byte[] toByteArray(long l, byte[] bytes) {
		for (int i = 0; i < bytes.length; i++) {
			bytes[bytes.length - 1 - i] = (byte) l;
			l >>>= 8;
		}
		return bytes;
	}

	public static int byteArrayToShort(byte[] data) {
		if (data.length != 2)
			return 0;

		int v = (data[0] & 0xFF);
		v <<= 8;
		v += (data[1] & 0xFF);
		return v;
	}

	private static void handlePrevTagSize(InputStream in, OutputStream out, int size)
			throws Exception {
		System.out.println("(+)handlePrevTagSize(+)");
		byte prevTagSize[] = new byte[4];
		int bytesRead = in.read(prevTagSize);
		gBytesRead += bytesRead;
		if (bytesRead != 4)
			throw new Exception("Invalid FirstPrevTagSize");
		else {
			System.out.println("Previous tag size:"
					+ displayByteArray(prevTagSize));
			if(size != -1)
				prevTagSize = intToByteArray(size);
			out.write(prevTagSize);
		}
		System.out.println("(-)handlePrevTagSize(-)");
	}

	private static boolean isTagEnd(InputStream in, OutputStream out)
			throws Exception {
		System.out.println("(+)isTagEnd(+)");
		byte endMarker[] = new byte[3];
		boolean result = false;
		PushbackInputStream pbin = (PushbackInputStream) in;
		pbin.read(endMarker);
		if ((endMarker[0] == 0x00) && (endMarker[1] == 0x00)
				&& (endMarker[2] == 0x09))
			result = true;
		pbin.unread(endMarker);
		System.out.println("End of tag?" + result);
		System.out.println("(-)isTagEnd(-)");
		return result;

	}

	private static void handleTagEndMarker(InputStream in, OutputStream out)
			throws Exception {
		System.out.println("(+)handleTagEndMarker(+)");
		byte endMarker[] = new byte[3];
		int bytesRead = in.read(endMarker);
		gBytesRead += bytesRead;
		if (bytesRead != 3)
			throw new Exception("Invalid PrevTagSize");
		else
			out.write(endMarker);
		System.out.println("(-)handleTagEndMarker(-)");
	}

	private static boolean isEOF(InputStream in, OutputStream out)
			throws Exception {
		System.out.println("(+)isEOF(+)");
		PushbackInputStream pbin = (PushbackInputStream) in;
		boolean result = false;
		try {
			byte b = (byte) pbin.read();
			if (b == -1)
				result = true;
			else
				pbin.unread(b);
		} finally {
		}
		System.out.println("(-)isEOF(-)");
		return result;
	}

	public static int UI24toInt(byte[] data) throws Exception {
		if (data.length != 3)
			throw new Exception("Invalid UI24 data");
		else {
			return ((data[0] & 0xFF) << 16) | ((data[1] & 0xFF) << 8)
					| (data[2] & 0xFF);
		}
	}

	public static byte[] IntToUI24(int data) throws Exception {
		int byte1 = data & 0xFF;
		int byte2 = (data >>> 8) & 0xFF;
		int byte3 = (data >>> 16) & 0xFF;

		return new byte[] { (byte) byte3, (byte) byte2, (byte) byte1 };

	}

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		FLVparser.service("C:\\temp\\mainstream.flv",
				"C:\\temp\\mainstream_new.flv");
		// byteWriter(new File("C:\\temp\\mainstream_new.flv"));

	}

	private static void byteWriter(File f) throws Exception {
		InputStream in = new FileInputStream(f);
		int b;
		while ((b = in.read()) != -1)
			System.out.print(" " + b);
		in.close();
	}

	private static String displayByteArray(byte[] arr) {
		StringBuilder sb = new StringBuilder();
		for (byte b : arr)
			sb.append(" " + b);
		return sb.toString();
	}

}
