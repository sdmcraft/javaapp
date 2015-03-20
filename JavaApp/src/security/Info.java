package security;

import java.util.HashMap;
import java.util.Map;

public class Info {
	public static Map<String, String> map1 = new HashMap<String, String>();
	static {
		map1.put("aa-11", "king");
		map1.put("bb-22", "queen");
		map1.put("cc-33", "jack");
		map1.put("dd-44", "ace");
	}

	public static Map<String, String> map2 = new HashMap<String, String>();
	static {
		map2.put("aa-11", "spade");
		map2.put("bb-22", "club");
		map2.put("cc-33", "heart");
		map2.put("dd-44", "diamond");
	}
}
