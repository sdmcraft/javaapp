import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class ChunkFiles {

    private static String rootPath;

	private static class FileWithDate implements Comparable {

		public String fileName;
		public long lastAccessDate;

		public FileWithDate(String fileName, long lastAccessDate) {
			super();
			this.fileName = fileName;
			this.lastAccessDate = lastAccessDate;
		}

		@Override
		public int compareTo(Object o) {
			long diff = lastAccessDate - ((FileWithDate) o).lastAccessDate;
			if (diff > 0) {
				return 1;
			} else if (diff < 0) {
				return -1;
			} else
				return 0;
		}

		@Override
		public String toString() {
			return fileName + "," + new Date(lastAccessDate);
		}
	}

	public static void main(String[] args) throws IOException {
		if (args.length != 3) {
			System.out
					.println("Usage:ChunkFiles <source-root-folder> <target-folder> <size (in MB) to be copied>");
			return;
		}
        rootPath = args[0];
		File rootFolder = new File(rootPath);
		String targetFolder = args[1];
		new File(targetFolder).mkdirs();
		
		long copyLimit = Integer.parseInt(args[2]) * 1024 * 1024;

		List<FileWithDate> originalFileList = new ArrayList<FileWithDate>();
		listChildren(rootFolder, originalFileList);

        List<FileWithDate> archivedFileList = readFromFile(new File(rootFolder.getAbsolutePath() + File.separator + "archive.txt"));

        List<FileWithDate> consolidatedFileList = consolidateLists(originalFileList, archivedFileList);

        Collections.sort(consolidatedFileList);
		int copyCounter = 0;
		for (FileWithDate f : consolidatedFileList) {
            File imageFile = new File(rootPath + File.separator + f.fileName);
			copyCounter += imageFile.length();
			if (copyCounter >= copyLimit) {
				System.out.println("Limit over, exiting");
				break;
			}
			System.out.println("Copying " + f);
			copyFile(imageFile,
					new File(args[1] + File.separator + imageFile.getName()));
			System.out.println("Copied till now:" + copyCounter + " bytes");
		}
	}

	private static void listChildren(File root, List<FileWithDate> list) {
		if (root.isFile()) {
            list.add(new FileWithDate(root.getAbsolutePath().replace(rootPath, ""), root.lastModified()));
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

    private static List<FileWithDate> readFromFile(File file) throws IOException {
        List<FileWithDate> list = new ArrayList<FileWithDate>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        String line;
        while((line = bufferedReader.readLine()) != null) {
            list.add(new FileWithDate(line.split(",")[0], Long.parseLong(line.split(",")[1])));
        }
        return list;
    }

    private static List<FileWithDate> consolidateLists(List<FileWithDate> originalList, List<FileWithDate> archivedList) {
        for(FileWithDate original : originalList) {
            for(FileWithDate archived : archivedList) {
                if(archived.fileName.equals(original.fileName)) {
                    original.lastAccessDate = archived.lastAccessDate;
                    break;
                }
            }
        }
        return  originalList;
    }

    private static void writeToFile(File file, List<FileWithDate> list) throws IOException {
        if(!file.exists()) {
            file.mkdirs();
        } else {
            copyFile(file, new File(file.getAbsolutePath() + ".bkp"));
            file.delete();
            file.createNewFile();
        }
        PrintWriter writer = new PrintWriter(new FileWriter(file));
        for(FileWithDate item : list) {
            writer.println(item);
        }
    }

}
