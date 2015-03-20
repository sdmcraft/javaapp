//http://www.careercup.com/question?id=5136081238360064

package misc;

import java.util.HashMap;
import java.util.Map;

public class EncodeString {

	private static Map<String, String> memo = new HashMap<String, String>();


	private static String encodeString(String string) {
		String returnValue;
		if (memo.containsKey(string)) {
			returnValue = memo.get(string);
		} else {
			if (string.length() == 0) {
				return "";
			} else if (string.length() == 1) {
				returnValue = string.concat("1");
			} else {
				String encodedSubstring = encodeString(string.substring(1));
				if (string.substring(0, 1).equals(
						encodedSubstring.substring(0, 1))) {
					returnValue = new StringBuilder()
							.append(encodedSubstring.charAt(0))
							.append(encodedSubstring.charAt(1) - '0' + 1)
							.append(encodedSubstring.substring(2)).toString();
				} else {
					returnValue = new StringBuilder()
							.append(string.substring(0, 1)).append('1')
							.append(encodedSubstring).toString();
				}
				memo.put(string, returnValue);

			}

		}
		return returnValue;
	}
	
	public static void main(String[] args) {
		System.out.println(encodeString("apple"));
		memo.clear();
		System.out.println(encodeString("mango"));
		memo.clear();
		System.out.println(encodeString("aaaa"));
		memo.clear();
		System.out.println(encodeString(""));
		memo.clear();
		System.out.println(encodeString("a"));
		memo.clear();
		System.out.println(encodeString("wwwwaaadexxxxxxwwww"));
		memo.clear();
		System.out.println(encodeString("aa"));
		memo.clear();
		System.out.println(encodeString("aaa"));
		memo.clear();
		System.out.println(encodeString("aaaa"));
		memo.clear();
		System.out.println(encodeString("aabaabaa"));
		memo.clear();
		System.out.println(encodeString("aabbcbbcaa"));
		memo.clear();
	}

}
