package dataStructures;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import tools.IOUtils;

public class FileBackedArray {
	private final int[] array;
	private final String backingFile;
	private int count = 0;
	private int fileEntryCount = 0;

	public FileBackedArray(int size, String backingFile) {
		super();
		this.array = new int[size];
		for (int i = 0; i < array.length; i++)
			array[i] = Integer.MAX_VALUE;
		this.backingFile = backingFile;
		new File(backingFile).delete();
	}

	public void add(int item) throws Exception {
		if (count == array.length) {
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(new BufferedWriter(new FileWriter(
						backingFile, true)));
				for (int i = 0; i < array.length; i++) {
					pw.println(array[i]);
					fileEntryCount++;
					array[i] = Integer.MAX_VALUE;
				}
				count = 0;
			} finally {
				if (pw != null)
					pw.close();
			}
		}
		array[count] = item;
		count++;
	}

	/* WIP */
	public int read(int index) throws Exception {
		if (index + 1 <= fileEntryCount)
			return Integer.parseInt(IOUtils.readLineFromFile(index + 1,
					backingFile));
		else if (index + 1 > fileEntryCount
				&& index + 1 < fileEntryCount + array.length) {
			return array[index - fileEntryCount - 1];
		} else
			throw new ArrayIndexOutOfBoundsException();
	}

	public void flush() throws Exception {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(backingFile,
					true)));
			for (int i = 0; i < count; i++) {
				pw.println(array[i]);
				fileEntryCount++;
				array[i] = Integer.MAX_VALUE;
			}
			count = 0;
		} finally {
			if (pw != null)
				pw.close();
		}

	}

	public static void main(String[] args) throws Exception {
		FileBackedArray array = new FileBackedArray(5,
				"D:\\temp\\backingFile.txt");
		for (int i = 0; i < 100; i++)
			array.add(i);

		for (int i = 0; i < 100; i++)
			System.out.println(array.read(i));

		array.flush();
	}
}
