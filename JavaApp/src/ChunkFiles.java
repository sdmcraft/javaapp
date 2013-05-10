import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
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

	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.out
					.println("Usage:ChunkFiles <source-root-folder> <target-folder> <size (in MB) to be copied>");
		}
		File rootFolder = new File(args[0]);
		String targetFolder = args[1];
		new File(targetFolder).mkdirs();
		
		long copyLimit = Integer.parseInt(args[2]) * 1024 * 1024;

		List<FileWithDate> fileList = new ArrayList<FileWithDate>();
		listChildren(rootFolder, fileList);
		Collections.sort(fileList);
		int copyCounter = 0;
		for (FileWithDate f : fileList) {
			copyCounter += f.file.length();
			if (copyCounter >= copyLimit) {
				System.out.println("Limit over, exiting");
				break;
			}
			System.out.println("Copying " + f);
			copyFile(f.file,
					new File(args[1] + File.separator + f.file.getName()));
			f.file.setLastModified(new Date().getTime());
			System.out.println("Copied till now:" + copyCounter + " bytes");
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

	public static void copyFile(File sourceFile, File destFile)
			throws IOException {
		if (!destFile.exists()) {
			destFile.createNewFile();
		}

		FileChannel source = null;
		FileChannel destination = null;
		try {
			source = new FileInputStream(sourceFile).getChannel();
			destination = new FileOutputStream(destFile).getChannel();

			// previous code: destination.transferFrom(source, 0,
			// source.size());
			// to avoid infinite loops, should be:
			long count = 0;
			long size = source.size();
			while ((count += destination.transferFrom(source, count, size
					- count)) < size)
				;
		} finally {
			if (source != null) {
				source.close();
			}
			if (destination != null) {
				destination.close();
			}
		}
	}

}
