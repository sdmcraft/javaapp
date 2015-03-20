package misc;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class XMLSender {

	public static void send(File ipFile, File opFile, String urlString)
			throws Exception {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		OutputStream out = null;
		InputStream in = null;
		byte[] fileData = null;
		try {
			fis = new FileInputStream(ipFile);
			fos = new FileOutputStream(opFile);

			fileData = new byte[fis.available()];
			fis.read(fileData);

			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("CONTENT-TYPE", "text/xml");
			connection.setDoOutput(true);

			out = connection.getOutputStream();
			out.write(fileData);
			out.flush();

			in = connection.getInputStream();
			copyInputStream(in, fos, 8192);
		} finally {
			if (fis != null)
				fis.close();
			if (fos != null)
				fos.close();
			if (in != null)
				in.close();
			if (out != null)
				out.close();
		}

	}

	public static final void copyInputStream(InputStream in, OutputStream out,
			int bufferSize) throws IOException {
		try {
			byte[] buffer = new byte[bufferSize];
			int len;

			while ((len = in.read(buffer)) >= 0)
				out.write(buffer, 0, len);
		} finally {
			out.flush();
		}
	}

	public static void main(String[] args) throws Exception {
		send(new File("C:\\xmlFile.xml"), new File("C:\\result.xml"),
				"http://connectdev1.corp.adobe.com/api/xml");
	}

}
