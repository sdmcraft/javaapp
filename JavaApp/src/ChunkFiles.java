import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ChunkFiles {

	private static class FileWithDate implements Comparable {

		public File file;
		public long lastModifiedDate;

		public FileWithDate(File file, long lastModifiedDate) {
			super();
			this.file = file;
			this.lastModifiedDate = lastModifiedDate;
		}

		@Override
		public int compareTo(Object o) {
			long diff = lastModifiedDate - ((FileWithDate) o).lastModifiedDate;
			if (diff > 0) {
				return 1;
			} else if (diff < 0) {
				return -1;
			} else
				return 0;
		}

		@Override
		public String toString() {
			return file + "-" + new Date(lastModifiedDate);
		}
	}

	public static void main(String[] args) {
		// File rootFolder = new File(args[0]);
		File rootFolder = new File("C:\\temp");
		List<FileWithDate> fileList = new ArrayList<FileWithDate>();
		listChildren(rootFolder, fileList);
		Collections.sort(fileList);
		for (FileWithDate f : fileList) {
			System.out.println(f);
		}
	}

	private static void listChildren(File root, List<FileWithDate> list) {
		if (root.isFile()) {
			list.add(new FileWithDate(root, root.lastModified()));
		} else {
			for (File child : root.listFiles()) {
				listChildren(child, list);
			}
		}
	}

}
