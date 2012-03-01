package misc;

import java.io.FileWriter;
import java.net.URL;
import java.net.URLClassLoader;

import jode.decompiler.Decompiler;
import jode.decompiler.ProgressListener;

public class DecompilerDemo {
	public static void main(String[] args) throws Exception {
		System.out.println(System.getProperty("user.dir"));
		Decompiler decompiler = new Decompiler();
		decompiler.setClassPath("D:\\svn\\javaapp.googlecode.com\\Tool\\bin\\");
		FileWriter fw = new FileWriter("D:\\temp\\jode.txt");
		ProgressListener p = new ProgressListener() {

			public void updateProgress(double arg0, String arg1) {
				System.out.println("inside of progress listener with arg0 = "
						+ arg0 + " and arg1 = " + arg1);
			}
		};

		decompiler.decompile("misc.Main", fw, p);

		printClasspath();
		// decompiler.decompile(arg0, arg1, arg2);
	}

	public static void printClasspath() {
		ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader();

		// Get the URLs
		URL[] urls = ((URLClassLoader) sysClassLoader).getURLs();

		for (int i = 0; i < urls.length; i++) {
			System.out.println(urls[i].getFile());
		}
	}
}
