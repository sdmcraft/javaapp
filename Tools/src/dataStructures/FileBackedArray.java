package dataStructures;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import tools.IOUtils;

public class FileBackedArray {
	private final int[] buffer;
	private final String backingFile;
	private int bufferEntryCount = 0;	
	private int fileEntryCount = 0;

	public FileBackedArray(int bufferSize, String backingFile) {
		super();
		this.buffer = new int[bufferSize];
		for (int i = 0; i < buffer.length; i++)
			buffer[i] = Integer.MAX_VALUE;
		this.backingFile = backingFile;
		new File(backingFile).delete();
	}

	public void add(int item) throws Exception {
		if (bufferEntryCount == buffer.length) {
			PrintWriter pw = null;
			try {
				pw = new PrintWriter(new BufferedWriter(new FileWriter(
						backingFile, true)));
				for (int i = 0; i < buffer.length; i++) {
					pw.println(buffer[i]);
					fileEntryCount++;
					buffer[i] = Integer.MAX_VALUE;
				}
				bufferEntryCount = 0;
			} finally {
				if (pw != null)
					pw.close();
			}
		}
		buffer[bufferEntryCount] = item;
		bufferEntryCount++;
	}

	/* WIP 
	 * index = 99
	 * fileEntryCount = 95
	 * */
	public int read(int index) throws Exception {
		if (index + 1 <= fileEntryCount)
			return Integer.parseInt(IOUtils.readLineFromFile(index + 1,
					backingFile));
		else if (index + 1 > fileEntryCount
				&& index + 1 <= fileEntryCount + buffer.length) {
			return buffer[index - fileEntryCount];
		} else
			throw new ArrayIndexOutOfBoundsException();
	}

	public void flush() throws Exception {
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(new BufferedWriter(new FileWriter(backingFile,
					true)));
			for (int i = 0; i < bufferEntryCount; i++) {
				pw.println(buffer[i]);
				fileEntryCount++;
				buffer[i] = Integer.MAX_VALUE;
			}
			bufferEntryCount = 0;
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
		array.flush();
		for (int i = 0; i < 100; i++)
			System.out.println(array.read(i));

		array.flush();
	}
}
