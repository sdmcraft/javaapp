package misc;

import java.io.File;
import java.io.IOException;

public class RegexDemo {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		for(String s : "123/456-7/abc-def/".split("[/-]"))
		{
			System.out.println(s);
		}
		File f = new File("C:\\sdm\\temp\\abc\\def");
		f.mkdirs();
		
//		System.out.println(f.getAbsolutePath());
//		System.out.println(f.getName());
//		String path = f.getAbsolutePath();
//		path = path.replace("test", "pest");
//		System.out.println(path);
//		f.renameTo(new File(path));
	}

}
