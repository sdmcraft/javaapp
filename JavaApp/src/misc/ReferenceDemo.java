package misc;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class ReferenceDemo {
	public static void main(String[] args) {
		WeakReference<String> wr = new WeakReference<String>(new String("weak"));
		SoftReference<String> sr = new SoftReference<String>(new String("soft"));
		System.out.println(wr.get());
		System.out.println(sr.get());
		boolean b = true;
		List<Object> list = new ArrayList<Object>();
		try {
			while (b) {
				list.add(new byte[100]);
			}
		} catch (OutOfMemoryError e) {
			System.gc();
		}
		System.out.println(wr.get());
		System.out.println(sr.get());
	}
}
