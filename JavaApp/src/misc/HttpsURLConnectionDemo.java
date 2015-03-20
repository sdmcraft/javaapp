package misc;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class HttpsURLConnectionDemo {
	public static void main(String[] args) throws Exception {
		URL url = new URL(
				"https://na1fms1.adobeconnect.com/servlet/intercall/CCAPICallbackSOAP");
		HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			System.out.println(sb);
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			bufferedReader.close();
		}
	}
}
