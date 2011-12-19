package misc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		// for(String s : "123/456-7/abc-def/".split("[/-]"))
		// {
		// System.out.println(s);
		// }
		// File f = new File("C:\\sdm\\temp\\abc\\def");
		// f.mkdirs();

		// System.out.println(f.getAbsolutePath());
		// System.out.println(f.getName());
		// String path = f.getAbsolutePath();
		// path = path.replace("test", "pest");
		// System.out.println(path);
		// f.renameTo(new File(path));
		String tag = "i";
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p
				.matcher("500 px");
		m.find();
		System.out.println(m.group());
		while (m.find()) {
			System.out.println(m.group());
		}
	}

}
