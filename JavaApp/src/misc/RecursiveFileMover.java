package misc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecursiveFileMover {

	private static class MyFileFilter implements FileFilter {

		@Override
		public boolean accept(File pathname) {
			boolean result = pathname.exists();
			if (result) {
				result = pathname.isFile();
			}
			if (result) {
				result = !pathname.getName().endsWith("jpg");
			}
			if (result) {
				result = !pathname.getName().endsWith("JPG");
			}
			if (result) {
				result = !pathname.getName().endsWith("jpeg");
			}
			if (result) {
				result = !pathname.getName().endsWith("JPEG");
			}
			return result;
		}

	}

	public static void main(String[] args) {
		if (args.length < 2) {
			System.out
					.println("Usage: RecursiveFileMover <source directory> <target directory>");
			return;
		}
		File srcDir = new File(args[0]);
		File tgtDir = new File(args[1]);

		List<File> fileList = new ArrayList<>();
		MyFileFilter fileFilter = new MyFileFilter();
		if (!srcDir.exists()) {
			System.out.println("Source directory: " + srcDir
					+ " does not exist!");
			return;
		}
		getFileList(srcDir, fileList, fileFilter);
		for (File f : fileList) {
			String targetPath = tgtDir
					+ f.getAbsolutePath().substring(
							srcDir.getAbsolutePath().length());
			new File(targetPath).getParentFile().mkdirs();
			System.out.println("Src" + f.getAbsolutePath());
			System.out.println("Tgt:" + targetPath);
			try {
				Process process = Runtime.getRuntime().exec(
						new String[] { "cmd", "/C", "move",
								f.getAbsolutePath(), targetPath });
				BufferedReader r = new BufferedReader(new InputStreamReader(
						process.getErrorStream()));
				String line = null;
				while ((line = r.readLine()) != null) {
					System.out.println(line);
				}
				r.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		deleteEmptyFolders(srcDir);
	}

	private static void getFileList(File srcDir, List<File> fileList,
			FileFilter fileFilter) {
		fileList.addAll(Arrays.asList(srcDir.listFiles(fileFilter)));
		for (File file : srcDir.listFiles()) {
			if (file.isDirectory())
				getFileList(file, fileList, fileFilter);
		}
	}

	private static void deleteEmptyFolders(File srcDir) {
		for (File file : srcDir.listFiles()) {
			if (file.isDirectory())
				deleteEmptyFolders(file);
		}
		if (srcDir.isDirectory() && srcDir.list().length == 0)
			srcDir.delete();
	}

}
