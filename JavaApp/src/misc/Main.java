package misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader reader = null;
		String result = "";
		boolean done = false;
		try {
			reader = new BufferedReader(new InputStreamReader(System.in));
			String line = "";
			line = reader.readLine();
			int testCaseCount = Integer.parseInt(line);
			for(int i=0;i<testCaseCount;i++)
			{
				line = reader.readLine();
				int num1 = reverse(line.split(" ")[0]);
				int num2 = reverse(line.split(" ")[1]);
				int answer = num1 + num2;
				result += reverse(Integer.toString(answer)) +"\n";				
			}
			System.out.println(result);
		} finally {
			if (reader != null)
				reader.close();
			//System.exit(0);
		}

	}
	
	public static int reverse(String num)
	{		
		char[] charArr = num.toCharArray();
		char[] result = new char[charArr.length];
		for(int i =0,j = result.length -1;i<charArr.length;i++,j--)
		{
			result[j] = charArr[i];
		}
		return Integer.parseInt(new String(result));	
	}

}
