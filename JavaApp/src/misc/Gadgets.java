package misc;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gadgets {

	public final static void structuralCompare(String root1, String root2,
			Map<String, String> idMap, File reportFile) throws Exception {
		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(reportFile));
			File rootDir = new File(root1);
			out.write("<HTML>");
			out.newLine();
			out.write("<BODY>");
			out.newLine();
			out.write("<TABLE>");
			out.newLine();
			out.write("<TR>");
			out.newLine();
			out.write("<TD");
			out.newLine();
			out.write("Source");
			out.newLine();
			out.write("</TD>");
			out.write("<TD>");
			out.write("Target");
			out.newLine();
			out.write("</TD>");
			out.write("</TR>");
			out.newLine();
			structuralCompare(rootDir, idMap, out, Pattern.compile("[0-9]+"),
					root1, root2);
			out.write("</TABLE>");
			out.newLine();
			out.write("</BODY>");
			out.newLine();
			out.write("</HTML>");
			out.newLine();
		} finally {
			out.close();
		}
	}

	private final static void structuralCompare(File f1,
			Map<String, String> map, BufferedWriter out, Pattern pattern,
			String root1, String root2) throws Exception {
		String path1 = f1.getAbsolutePath();
		String path2 = path1.replace(root1, root2);
		Matcher m = pattern.matcher(path2);
		while (m.find()) {
			String oldVal = m.group();
			String newVal = map.get(oldVal);
			if (newVal != null && !"".equals(newVal) && !newVal.equals(oldVal)) {
				path2 = path1.replace(oldVal, newVal);
			}
		}
		out.write("<TR>");
		out.newLine();
		out.write("<TD>");
		out.newLine();
		out.write(path1);
		out.newLine();
		out.write("</TD>");
		out.newLine();
		out.write("<TD>");
		out.newLine();
		if ((new File(path2)).exists()) {
			out.write(path1);
			out.newLine();
		} else {
			out.write("NOT FOUND");
			out.newLine();
		}
		out.write("</TD>");
		out.newLine();
		out.write("</TR>");
		out.newLine();
		for (File f : f1.listFiles()) {
			structuralCompare(f, map, out, pattern, root1, root2);
		}

	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
