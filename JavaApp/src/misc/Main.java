package misc;

import java.io.IOException;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws Exception,
			InterruptedException {
		String path = "content/connect_events/microsites/c1/Site1/en/event/shared/event_template1";
		System.out.println(path.substring(path.indexOf("/en/") + 1));
	}

	public static int reverse(String num) {
		char[] charArr = num.toCharArray();
		char[] result = new char[charArr.length];
		for (int i = 0, j = result.length - 1; i < charArr.length; i++, j--) {
			result[j] = charArr[i];
		}
		return Integer.parseInt(new String(result));
	}

}
