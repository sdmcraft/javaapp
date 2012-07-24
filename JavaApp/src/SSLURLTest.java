
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class SSLURLTest {
	public static void main(String[] args) throws Exception {
		if(args.length != 1)
		{
			System.out.println("Usage:java SSLURLTest <ssl url> \n For e.g. java SSLURLTest https://na1fms1.adobeconnect.com/servlet/intercall/CCAPICallbackSOAP");
			return;
		}
		URL url = new URL(args[0]);
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
