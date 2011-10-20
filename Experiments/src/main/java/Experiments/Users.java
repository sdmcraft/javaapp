package Experiments;

import java.util.HashMap;
import java.util.Map;

public class Users {
	private static Map<String, String> map = new HashMap<String, String>();

	static {
		map.put("admin", "welcome");
	}

	public static void addUser(String uname, String pwd) {
		map.put(uname, pwd);
	}

	public static boolean validate(String name, String pwd) {
		if (!map.containsKey(name))
			return false;
		return map.get(name).equals(pwd);
	}
}
