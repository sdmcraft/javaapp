package misc;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws Exception, InterruptedException {
		File file = new File("C:\\a.jpg");
		System.out.println(file.renameTo(new File("C:\\?.jpg")));
//		PrintWriter out = new PrintWriter(System.out);
//		for (int i = 0; i < 10; i++) {
//			out.println("i="+i);
//			Thread.sleep(1000);
//			//out.flush();
//		}
//		out.close();
		// BufferedReader reader = null;
		// String result = "";
		// boolean done = false;
		// try {
		// reader = new BufferedReader(new InputStreamReader(System.in));
		// String line = "";
		// line = reader.readLine();
		// int testCaseCount = Integer.parseInt(line);
		// for(int i=0;i<testCaseCount;i++)
		// {
		// line = reader.readLine();
		// int num1 = reverse(line.split(" ")[0]);
		// int num2 = reverse(line.split(" ")[1]);
		// int answer = num1 + num2;
		// result += reverse(Integer.toString(answer)) +"\n";
		// }
		// System.out.println(result);
		// } finally {
		// if (reader != null)
		// reader.close();
		// //System.exit(0);
		//		
		// }getRingingUsers().put
		// Object lock = new Object();
		// synchronized (lock) {
		// System.out.println("1");
		// synchronized (lock) {
		// System.out.println("2");
		// }
		// }
		// System.out.println("1");
		// ConcurrentHashMap<String, String> map = new ConcurrentHashMap<String,
		// String>();
		// System.out.println("2");
		// Set set = map.keySet();
		// System.out.println("3");
		// Iterator<String> itr = set.iterator();
		// System.out.println("4");
		// itr.hasNext();
		// System.out.println("5");
		//		
//		String s1 = "abc";
//		String s2 = "def";
//		String s3 = (s1+s2);
//		System.out.println(s3 == "abcdef");
		
	}

	public static int reverse(String num) {
		char[] charArr = num.toCharArray();
		char[] result = new char[charArr.length];
		for (int i = 0, j = result.length - 1; i < charArr.length; i++, j--) {
			result[j] = charArr[i];
		}
		return Integer.parseInt(new String(result));
	}

}
