package misc.test;

import java.io.File;

import junit.framework.TestCase;
import misc.Utils;

import org.junit.Test;

public class UtilsTest extends TestCase {

	@Test
	public void testCompare() {
		try {
			assertEquals(true, Utils.compare(null, null));
			
			File dir1 = new File("." + File.separator + "testSamples"
					+ File.separator + "dir1");
			if(dir1.exists())
				Utils.deleteFolder(dir1);
			if(!dir1.mkdir())
				throw new Exception("Unable to create directory!!!");

			File dir2 = new File("." + File.separator + "testSamples"
					+ File.separator + "dir2");
			if(dir2.exists())
				Utils.deleteFolder(dir2);
			if(!dir2.mkdir())
				throw new Exception("Unable to create directory!!!");

			assertEquals(false, Utils.compare(dir1, null));
			assertEquals(false, Utils.compare(dir2, null));
			assertEquals(false, Utils.compare(null,dir1));
			assertEquals(false, Utils.compare(null,dir2));
			assertEquals(true, Utils.compare(dir2,dir1));
			assertEquals(true, Utils.compare(dir1,dir2));
			assertEquals(true, Utils.compare(dir1,dir1));
			assertEquals(true, Utils.compare(dir2,dir2));
			
			File f1 = new File("." + File.separator + "testSamples"
					+ File.separator + "file1");
			if(f1.exists())
				if(!f1.delete())
					throw new Exception("Unable to delete file!!!");
			if(!f1.createNewFile())
				throw new Exception("Unable to create file!!!");

			File f2 = new File("." + File.separator + "testSamples"
					+ File.separator + "file2");
			if(f2.exists())
				if(!f2.delete())
					throw new Exception("Unable to delete file!!!");
			if(!f2.createNewFile())
				throw new Exception("Unable to create file!!!");

			assertEquals(false, Utils.compare(f1, null));
			assertEquals(false, Utils.compare(f2, null));
			assertEquals(false, Utils.compare(null,f1));
			assertEquals(false, Utils.compare(null,f2));
			assertEquals(false, Utils.compare(f1,f2));
			assertEquals(false, Utils.compare(f2,f1));
			assertEquals(true, Utils.compare(f1,f1));
			assertEquals(true, Utils.compare(f2,f2));
			
			assertEquals(false, Utils.compare(f1,dir1));
			assertEquals(false, Utils.compare(dir1,f1));
			assertEquals(false, Utils.compare(dir2,f2));
			assertEquals(false, Utils.compare(f2,dir2));

			File f3 = new File("." + File.separator + "testSamples"
					+ File.separator + "file1");
			assertEquals(true, Utils.compare(f1,f3));
			assertEquals(true, Utils.compare(f3,f1));
			
			File sample1 = new File("." + File.separator + "testSamples"
					+ File.separator + "coverage");
			File sample2 = new File("." + File.separator + "testSamples"
					+ File.separator + "coverage2");
			assertEquals(false, Utils.compare(sample1, null));
			assertEquals(false, Utils.compare(sample2, null));
			assertEquals(false, Utils.compare(null,sample1));
			assertEquals(false, Utils.compare(null,sample2));
			assertEquals(true, Utils.compare(sample2,sample1));
			assertEquals(true, Utils.compare(sample1,sample2));
			assertEquals(true, Utils.compare(sample1,sample1));
			assertEquals(true, Utils.compare(sample2,sample2));

			File sample3 = new File("." + File.separator + "testSamples"
					+ File.separator + "coverage3");
			assertEquals(false, Utils.compare(sample3,sample1));
			assertEquals(false, Utils.compare(sample1,sample3));
			assertEquals(true, Utils.compare(sample3,sample3));

			File sample4 = new File("." + File.separator + "testSamples"
					+ File.separator + "coverage4");
			assertEquals(false, Utils.compare(sample4,sample1));
			assertEquals(false, Utils.compare(sample1,sample4));
			assertEquals(true, Utils.compare(sample4,sample4));
			assertEquals(false, Utils.compare(sample4,sample3));
			assertEquals(false, Utils.compare(sample3,sample4));

		} catch (Exception ex) {
			fail(ex.getMessage());
		}
	}
}
