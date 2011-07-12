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
		boolean[] done = new boolean[5];
		for(boolean b : done)
			System.out.println(b);
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
