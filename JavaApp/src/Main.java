import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.TimeZone;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.json.JSONArray;
import org.json.JSONObject;

class Main {
	public static void main1(String[] args) throws Exception {
		class PasswordAuthenticator extends Authenticator {
			String m_username;
			String m_password;

			public PasswordAuthenticator(String username, String password) {
				super();
				this.m_username = username;
				this.m_password = password;
			}

			public PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(m_username, m_password);
			}
		}

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props,
				new PasswordAuthenticator("m.satyadeep@gmail.com", "xxx"));
		MimeMessage mimeMessage = new MimeMessage(session);
		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(
				"m.satyadeep@gmail.com"));
		mimeMessage.setSubject("Test Email");
		mimeMessage.setText("Hello World!!");
		Transport.send(mimeMessage);
	}

	public static void fireRequest(String strURL) {
		try {

			URL url = new URL(strURL.toString());

			/*
			 * this is another method of calling the below function. However as
			 * status code itself is sufficient so commenting it out
			 * 
			 * 
			 * 
			 * BufferedReader in = new BufferedReader(new
			 * InputStreamReader(url.openStream())); String inputLine; while
			 * ((inputLine = in.readLine()) != null) {
			 * System.out.println(inputLine); }
			 * 
			 * in.close();
			 */

			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			connection.setReadTimeout(15 * 1000);
			connection.connect();
			if (connection.getResponseCode() == 200) {
				// System.out.println("PASS:-File " + strURL
				// +" Downloaded Successfully");

				// Second Level Verification
				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				String inputLine = "";
				while ((inputLine = in.readLine()) != null) {
					// System.out.println(inputLine);
					// Everything correct
				}
				in.close();
			} else
				System.out.println("FAIL:-File " + strURL
						+ " Failed to Download");

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main2(String[] args) {
		for (String s : TimeZone.getAvailableIDs()) {
			//System.out.println(s);
			System.out.println(TimeZone.getTimeZone(s));
		}
	}
	
	public static void main3(String[] args) {
		TimeZone timeZone = TimeZone.getTimeZone("America/Los_Angeles");
		System.out.println(timeZone.getDisplayName(true, 1));
	}
	
	public static void main(String[] args) throws IOException {
		Map<String, String> map = new HashMap<String, String>();
		String s = readFile("C:\\temp\\result.json");
		System.out.println();
		JSONArray jsonArray = new JSONArray(s);
		System.out.println("Total number of master posts:" + jsonArray.length());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z"); // the format of your date
		sdf.setTimeZone(TimeZone.getTimeZone("IST"));
		PrintWriter writer = new PrintWriter("C:\\temp\\result-ist.csv");

		for(int i = 0; i < jsonArray.length(); i++)
		{
			JSONObject masterPost = jsonArray.getJSONObject(i);
			JSONObject template = masterPost.getJSONObject("template");
			String campaign = "";
			if(template.has("campaign"))
			{
				campaign = template.getJSONObject("campaign").getString("name");
			}
			JSONArray childPosts = masterPost.getJSONArray("posts");
			for(int j = 0; j < childPosts.length(); j++)
			{
				JSONObject childPost = childPosts.getJSONObject(j);
				Date scheduledDate = new Date(childPost.getJSONObject("to").getJSONObject("scheduled_time").getLong("utc")*1000L);
				String formattedDate = sdf.format(scheduledDate);
				
				String postType = childPost.getString("type");	
				writer.println(postType + "," + campaign + "," + formattedDate);
			}			
		}
		writer.close();
	}
	
	
	public static String readFile(String fileName) throws IOException {
	    BufferedReader br = new BufferedReader(new FileReader(fileName));
	    try {
	        StringBuilder sb = new StringBuilder();
	        String line = br.readLine();

	        while (line != null) {
	            sb.append(line);
	            sb.append("\n");
	            line = br.readLine();
	        }
	        return sb.toString();
	    } finally {
	        br.close();
	    }
	}
}