package misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;

public class ConnectPasswordDecrypter {

	public static final String SETTINGS_PASSWORD_PREFIX = "#E&P!W#D%";
	public static final String FIELDS_PASSWORD_PREFIX = "#C$F%P@E!";
	public static SecretKey DESKey = null;

	public static String decryptString(String encryptedString, String keyFile)
			throws Exception {
		if (encryptedString == null) {
			return null;
		}

		InputStream inputStream = new FileInputStream(new File(keyFile));

		// XXX: inputStream.available() may NOT BE the file size
		byte[] encKey = new byte[inputStream.available()];
		readStream(inputStream, encKey);

		DESKey = new SecretKeySpec(encKey, "DES");

		// if prefixed, clean up the value
//		if (encryptedString.startsWith(SETTINGS_PASSWORD_PREFIX)) {
//			encryptedString = encryptedString
//					.substring(SETTINGS_PASSWORD_PREFIX.length());
//		} else if (encryptedString.startsWith(FIELDS_PASSWORD_PREFIX)) {
//			encryptedString = encryptedString.substring(FIELDS_PASSWORD_PREFIX
//					.length());
//		}

		byte[] cipherText = new BASE64Decoder().decodeBuffer(encryptedString);

		// Get a DES cipher object
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");

		// decrypt using the key and the encrypted string
		cipher.init(Cipher.DECRYPT_MODE, DESKey);

		byte[] newPlainText = cipher.doFinal(cipherText);

		return new String(newPlainText, "UTF8");
	}

	private static void readStream(InputStream stream, byte[] data)
			throws Exception {
		int sz = data.length; // assume this is the file size
		int p = 0; // read start position
		int n = 0; // bytes read

		while ((n = stream.read(data, p, sz - p)) > 0) {
			p += n;
		}

		stream.close();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		System.out.println(decryptString("BSQ+uCUfEFFz9jj7mxoN8kug06M=",
				"C:\\temp\\DESKey"));

	}

}
