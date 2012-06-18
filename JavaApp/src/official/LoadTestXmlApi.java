package official;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class LoadTestXmlApi {

	public static void main(String[] args) throws Exception {
		String scoId = "665673780";
		String eventName = "sdmimgtest";
		for (int i = 1; i <= 20; i++) {
			RegisterTask registerTask = new RegisterTask(scoId, eventName, i);
			new Thread(registerTask).start();
			Thread.sleep(200);
		}
	}

	static class RegisterTask implements Runnable {
		String scoId;
		String eventName;
		int num;

		public RegisterTask(String scoId, String eventName, int num) {
			this.scoId = scoId;
			this.num = num;
			this.eventName = eventName;
		}

		@Override
		public void run() {
			long time = System.currentTimeMillis();
			try {
				BufferedReader in = null;
				URL url = new URL(
						"http://connectpro38125358.adobeconnect.com/api/xml?action=event-register&sco-id="
								+ scoId
								+ "&password=breeze&password-verify=breeze&login="
								+ eventName + "-" + num
								+ "@adobe.com&name=newuser" + num);
				URLConnection yc = url.openConnection();
				in = new BufferedReader(new InputStreamReader(
						yc.getInputStream()));
				String inputLine;

				while ((inputLine = in.readLine()) != null)
					System.out.println(inputLine);

				in.close();
				System.out.println("Registration complete for: " + num + "in "
						+ (System.currentTimeMillis() - time) + " ms");
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

}
