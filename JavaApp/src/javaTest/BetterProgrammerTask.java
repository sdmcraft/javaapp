package javaTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BetterProgrammerTask {

	public static int getSumOfNumbers(String s) {
		/*
		 * Please implement this method to return the sum of all integers found
		 * in the parameter String. You can assume that integers are separated
		 * from other parts with one or more spaces (' ' symbol). For example,
		 * s="12 some text 3  7", result: 22 (12+3+7=22)
		 */
		int result = 0;
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher(s);
		while (m.find()) {
			result += Integer.parseInt(m.group());
		}
		return result;
	}

	public static String reverseWords(String s) {
		/*
		 * Assume that the parameter String can only contain spaces and
		 * alphanumeric characters. Please implement this method to reverse each
		 * word in the original String while maintaining the word order. For
		 * example: parameter: "Hello world", result: "olleH dlrow"
		 */
		String[] wordList = s.split("[ ]+");
		String result = "";
		for (String word : wordList)
			result = result + reverseWord(word) + " ";
		return result;
	}

	public static String reverseWord(String s) {
		char[] charList = s.toCharArray();
		char[] revCharList = new char[charList.length];
		for (int i = 0, j = charList.length - 1; i < charList.length; i++, j--)
			revCharList[j] = charList[i];
		return new String(revCharList);
	}

	public static void main(String[] args) {
		// System.out.println(getSumOfNumbers("12 some text 3  7"));
		// System.out.println(getSumOfNumbers("100 adsswd 200cscvdsc  ,,,  300 some text 400"));
		// System.out.println(reverseWords("Hello World"));
		System.out.println(countWords("Hi 2 times a number like 56 goes "));
		System.out.println(countWords("Hi"));
		System.out.println(countWords("Hi    "));
		System.out.println(countWords("2"));
		System.out.println(countWords("25"));
		System.out.println(countWords("25a"));
		System.out.println(countWords(""));
		System.out.println(countWords(" "));
		System.out.println(countWords(" a25A "));
	}

	public static int countWords(String s) {
		/*
		 * Please implement this method to return the word count in a given
		 * String. Assume that the parameter String can only contain spaces and
		 * alphanumeric characters.
		 */
		int result = 0;
		if (s != null) {
			Pattern p = Pattern.compile("[0-9a-zA-Z]+");
			Matcher m = p.matcher(s);
			while (m.find()) {
				result++;
			}
		}
		return result;
	}

	public static class WriteOnceMap<K, V> extends HashMap<K, V> {

		public V put(K key, V value) {
			/*
			 * WriteOnceMap is a map that does not allow changing value for a
			 * particular key. It means that put() method should throw
			 * IllegalArgumentException if the key is already assosiated with
			 * some value in the map.
			 * 
			 * Please implement this method to conform to the above description
			 * of WriteOnceMap.
			 */
			V origValue = this.get(key);
			if (origValue == null)
				this.put(key, value);
			else if (!value.equals(origValue))
				throw new IllegalArgumentException();
			return value;
		}

		public void putAll(Map<? extends K, ? extends V> m) {
			/*
			 * Pleaase implement this method to conform to the description of
			 * WriteOnceMap above. It should either (1) copy all of the mappings
			 * from the specified map to this map or (2) throw
			 * IllegalArgumentException and leave this map intact if the
			 * parameter already contains some keys from this map.
			 */
			Set<Entry<K, V>> set = this.entrySet();
			for (Entry<K, V> entry : set) {
				K key = entry.getKey();
				V origValue = entry.getValue();
				V value = m.get(key);
				if (value != null && origValue != null
						&& !value.equals(origValue))
					throw new IllegalArgumentException();
			}
			this.putAll(m);
		}
	}

}
