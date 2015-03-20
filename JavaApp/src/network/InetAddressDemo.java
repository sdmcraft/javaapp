package network;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class InetAddressDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			InetAddress[] addresses = InetAddress.getAllByName("192.168.1.1");
			for (int i = 0; i < addresses.length; i++) {
				System.out.println(addresses[i]);
				System.out.println(addresses[i].getAddress().length);
			      if (addresses[i].isAnyLocalAddress( )) {
			    	  System.out.println(addresses[i] + " is a wildcard address."); 
			      } 
			      if (addresses[i].isLoopbackAddress( )) { 
			        System.out.println(addresses[i] + " is loopback address."); 
			      } 
			       
			      if (addresses[i].isLinkLocalAddress( )) { 
			        System.out.println(addresses[i] + " is a link-local address."); 
			      } 
			      else if (addresses[i].isSiteLocalAddress( )) { 
			        System.out.println(addresses[i] + " is a site-local address."); 
			      } 
			      else { 
			        System.out.println(addresses[i] + " is a global address."); 
			      } 
			       
			      if (addresses[i].isMulticastAddress( )) { 
			        if (addresses[i].isMCGlobal( )) { 
			          System.out.println(addresses[i] + " is a global multicast address."); 
			        }           
			        else if (addresses[i].isMCOrgLocal( )) { 
			          System.out.println(addresses[i]  
			           + " is an organization wide multicast address."); 
			        }   
			        else if (addresses[i].isMCSiteLocal( )) { 
			          System.out.println(addresses[i] + " is a site wide multicast address."); 
			        }   
			        else if (addresses[i].isMCLinkLocal( )) { 
			          System.out.println(addresses[i] + " is a subnet wide multicast address."); 
			        }   
			        else if (addresses[i].isMCNodeLocal( )) { 
			          System.out.println(addresses[i] + " is an interface-local multicast address."); 
			        }   
			        else { 
			          System.out.println(addresses[i] + " is an unknown multicast address type."); 
			        } 
			           
			      } 
			      else { 
			        System.out.println(addresses[i] + " is a unicast address.");           
			      }
			      Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			      while (interfaces.hasMoreElements())
			      { 
			          NetworkInterface ni = (NetworkInterface) interfaces.nextElement( ); 
			          System.out.println(ni);
			          Enumeration addresses1 = ni.getInetAddresses( ); 
			          while (addresses1.hasMoreElements( )) { 
			        	  System.out.println(addresses1.nextElement( ));     
			        	}
			      }
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		try
		{
			InetAddress address = InetAddress.getLocalHost();
			System.out.println(address.getHostAddress());
			System.out.println(address);
		} catch (UnknownHostException ex) {
			System.out.println("Could not find localhost");
		}

	}

}
