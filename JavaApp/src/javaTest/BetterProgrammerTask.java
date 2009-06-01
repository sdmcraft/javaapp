package javaTest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BetterProgrammerTask {

    public static int getSumOfNumbers(String s) {
        /*
          Please implement this method to
          return the sum of all integers found in the parameter String. You can assume that
          integers are separated from other parts with one or more spaces (' ' symbol).
          For example, s="12 some text 3  7", result: 22 (12+3+7=22)
         */
    	int result = 0;
    	Pattern p = Pattern.compile("[0-9]+");
    	Matcher m = p.matcher(s);
    	while(m.find())
    	{
    		result += Integer.parseInt(m.group());
    	}
    	return result;
    }
    
    public static String reverseWords(String s) {
        /*
          Assume that the parameter String can only contain spaces and alphanumeric characters.
          Please implement this method to
          reverse each word in the original String while maintaining the word order.
          For example:
          parameter: "Hello world", result: "olleH dlrow"
         */
    	String[] wordList = s.split("[ ]+");
    	String result = "";
    	for(String word : wordList)
    		result = result + reverseWord(word) + " ";
    	return result;
    }
    
    public static String reverseWord(String s) {
    	char[] charList = s.toCharArray();
    	char[] revCharList = new char[charList.length];
    	for(int i = 0,j = charList.length -1  ; i < charList.length ; i++,j--)
    		revCharList[j] = charList[i];
    	return new String(revCharList);
    }
    public static void main(String[] args)
    {
    	System.out.println(getSumOfNumbers("12 some text 3  7"));
    	System.out.println(getSumOfNumbers("100 adsswd 200cscvdsc  ,,,  300 some text 400"));
    	System.out.println(reverseWords("Hello World"));
    }
}

