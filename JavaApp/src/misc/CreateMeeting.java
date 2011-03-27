package misc;
import java.net.URL;
import java.net.URLConnection;
import java.util.StringTokenizer;

public class CreateMeeting {
	public static String create(String uname, String pwd, String meet)
			throws Exception {
		URLConnection conn = new URL("http://account.corp.adobe.com/api/xml?"
				+ "action=login&login=" + uname + "&password=" + pwd)
				.openConnection();
		conn.connect();
		conn.getInputStream();
		String breezesession = null;
		String breezesessionString = (String) (conn
				.getHeaderField("Set-Cookie"));
		StringTokenizer st = new StringTokenizer(breezesessionString, "=");
		String sessionName = null;
		if (st.countTokens() > 1)
			sessionName = st.nextToken();
		if (sessionName != null && sessionName.equals("BREEZESESSION")) {
			String breezesessionNext = st.nextToken();
			int semiIndex = breezesessionNext.indexOf(';');
			breezesession = breezesessionNext.substring(0, semiIndex);
		}
		conn = new URL("http://account.corp.adobe.com/api/xml?"
				+ "action=sco-update&type=meeting&folder-id=21204&name=" + meet)
				.openConnection();
		conn.setRequestProperty("Cookie", "BREEZESESSION=" + breezesession);
		conn.connect();
		conn.getInputStream();
		return breezesession;
	}

	public static void main(String[] args) throws Exception {
		create("user@adobe.com", "breeze", "DayMeet1");
	}
}
