package misc;

import com.sun.jna.Library;
import com.sun.jna.Native;

public class JNADemo {

	public interface User32 extends Library {
		boolean LockWorkStation();		
	}

	public static void main(String[] args) {

		// Create a proxy for user32.dll ...
		User32 user32 = (User32) Native.loadLibrary("user32", User32.class);

		// Invoke "LockWorkStation()" via the proxy ...
		user32.LockWorkStation();

	}

}
