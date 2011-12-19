package misc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws Exception,
			InterruptedException {
		// InternetAddress address = new
		// InternetAddress("\"Satya Deep\"<satyam@adobe.com>");
		// System.out.println(address.getPersonal());
		// System.out.println(address.getAddress());
		//
		// address = new InternetAddress("satyam@adobe.com");
		// System.out.println(address.getPersonal());
		// System.out.println(address.getAddress());
		//
		// address = new InternetAddress("Satya Deep <satyam@adobe.com>");
		// System.out.println(address.getPersonal());
		// System.out.println(address.getAddress());

		// String icalText = readFileAsString("C:\\temp\\a.txt");
		// System.out.println(icalText);
		// icalText = icalText.replaceAll("\r\n|\n", "\\\\n");
		// System.out.println(icalText);
		System.out.println(1f / 2 * 4);

		System.out
				.println(	"SELECT"
			    		+ " ae.filename, ae.width, ae.height, '' as mime_type"
						+ " FROM pps_asset_entries ae, pps_sco_assets sa, pps_scos s "
						+ " WHERE ae.asset_id = sa.asset_id"
						+ " AND sa.sco_id = ?"
						+ " AND sa.asset_type = ? "
			            + " AND sa.sco_id = s.sco_id "
			            + " AND sa.version = s.version ");
	}

	private static String readFileAsString(String filePath)
			throws java.io.IOException {
		StringBuffer fileData = new StringBuffer(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		char[] buf = new char[1024];
		int numRead = 0;
		while ((numRead = reader.read(buf)) != -1) {
			String readData = String.valueOf(buf, 0, numRead);
			fileData.append(readData);
			buf = new char[1024];
		}
		reader.close();
		return fileData.toString();
	}

}
