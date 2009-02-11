package misc;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RuntimeExecDemo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		Runtime runtime = Runtime.getRuntime();
		Process proc = runtime.exec("C:\\sdm\\ProgramFiles\\XmlEditor\\XmlEditor.exe");
		InputStream inputstream = proc.getInputStream();
		InputStreamReader inputstreamreader = new InputStreamReader(inputstream);
		BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
		String line;
		while ((line = bufferedreader.readLine()) != null) {
			System.out.println(line);
		}
	}

}
