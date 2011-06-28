package network;

import java.net.InetAddress;

import org.xbill.DNS.Address;

public class DNSLookup {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		InetAddress addr = Address.getByName("www.dnsjava.org");

	}

}
