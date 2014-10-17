//http://www.careercup.com/question?id=5136081238360064

/*
 * encode(str) = if str.length == 1
 * 					concat(str, 1)
 * 				else if (substr(0, 1, str) == substr(0, 1, encode(substr(1, str)))
 * 					add(1, 1, encode(substr(1, str)))
 * 				else
 * 					concat(substr(0, 1, str) , 1, encode(substr(1, str)))
 */

package misc;

public class EncodeString {
	
	public static void main(String[] args) {
		//System.out.println(encodeString("apple"));
		//System.out.println(encodeString("mango"));
		System.out.println(encodeString("aaaa"));
		System.out.println(encodeString(""));
		//System.out.println(encodeString("a"));
		//System.out.println(encodeString("aabbbcccddeewwaaa"));
	}
	
	private static String encodeString(String string){
		String returnValue;
		if(string.length() == 0){
			return "";
		} else if(string.length() == 1){
			returnValue =  string.concat("1");
		} else if (string.substring(0, 1).equals(encodeString(string.substring(1)).substring(0, 1))){
			returnValue = new StringBuilder()
			.append(encodeString(string.substring(1)).charAt(0))
			.append(encodeString(string.substring(1)).charAt(1) - '0' + 1)
			.append(encodeString(string.substring(2))).toString();
		} else {
			returnValue =new StringBuilder()
			.append(string.substring(0, 1))
			.append('1')
			.append(encodeString(string.substring(1))).toString();			
		}			
		return returnValue;
	}
}
