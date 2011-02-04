package misc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
//		for(String s : "123/456-7/abc-def/".split("[/-]"))
//		{
//			System.out.println(s);
//		}
//		File f = new File("C:\\sdm\\temp\\abc\\def");
//		f.mkdirs();
		
//		System.out.println(f.getAbsolutePath());
//		System.out.println(f.getName());
//		String path = f.getAbsolutePath();
//		path = path.replace("test", "pest");
//		System.out.println(path);
//		f.renameTo(new File(path));
		Pattern p = Pattern.compile("[0-9]+");
		Matcher m = p.matcher("a = 100123 111/2-0 b - 200 c[30{e40}/+ d 50");
		while (m.find()) {
			System.out.println(m.group());
		}
	}

}
