package misc;

import java.net.InetAddress;

public class PingDemo {

	/**
	 * @param args
	 * @throws Exception
	 * @throws
	 */
	public static void main(String[] args) throws Exception {		
		int timeOut = 3000; // I recommend 3 seconds at least
		while (true) {
			System.out.println(InetAddress.getByName("connectdev1").isReachable(
					timeOut));
		}

	}

}
