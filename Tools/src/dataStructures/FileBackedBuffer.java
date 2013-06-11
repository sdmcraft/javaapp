package dataStructures;

import tools.IOUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class FileBackedBuffer
{
    private final String mode;
    private final int[] buffer;
    private BufferedReader reader;
    private PrintWriter writer;
    private int bufferEntryCount = 0;

    public FileBackedBuffer(int bufferSize, String backingFile, String mode) throws Exception
    {
        super();
        this.buffer = new int[bufferSize];

        for (int i = 0; i < buffer.length; i++)
        {
            buffer[i] = Integer.MAX_VALUE;
        }

        this.mode = mode;

        if ("w".equals(this.mode))
        {
            new File(backingFile).delete();
            writer = new PrintWriter(new BufferedWriter(new FileWriter(backingFile, true)));
        }
        else if ("r".equals(this.mode))
        {
            reader = new BufferedReader(new FileReader(backingFile));
        }
    }

    public void close() throws Exception
    {
        if ("w".equals(this.mode))
        {
            flush();
        }

        if (writer != null)
        {
            writer.close();
        }

        if (reader != null)
        {
            reader.close();
        }
    }

    public void add(int item) throws Exception
    {
        if ("w".equals(this.mode))
        {
            if (bufferEntryCount == buffer.length)
            {
                flush();
            }

            buffer[bufferEntryCount] = item;
            bufferEntryCount++;
        }
        else
        {
            throw new Exception("Cannot write unless in write mode!!");
        }
    }

    public int read() throws Exception
    {
        if ("r".equals(this.mode))
        {
            if (bufferEntryCount == 0)
            {
                fill();
            }

            int i = 0;

            while (buffer[i] == Integer.MAX_VALUE)
            {
                i++;

                if (i == buffer.length)
                {
                    return Integer.MAX_VALUE;
                }
            }

            int returnValue = buffer[i];
            buffer[i] = Integer.MAX_VALUE;
            bufferEntryCount--;

            return returnValue;
        }
        else
        {
            throw new Exception("Cannot read unless in read mode!!");
        }
    }

    public int peek() throws Exception
    {
        if ("r".equals(this.mode))
        {
            if (bufferEntryCount == 0)
            {
                fill();
            }

            int i = 0;

            while (buffer[i] == Integer.MAX_VALUE)
            {
                i++;

                if (i == buffer.length)
                {
                    return Integer.MAX_VALUE;
                }
            }

            int returnValue = buffer[i];

            return returnValue;
        }
        else
        {
            throw new Exception("Cannot peek unless in read mode!!");
        }
    }

    private void flush() throws Exception
    {
        if ("w".equals(this.mode))
        {
            for (int i = 0; i < bufferEntryCount; i++)
            {
                writer.println(buffer[i]);
                buffer[i] = Integer.MAX_VALUE;
            }

            bufferEntryCount = 0;
        }
        else
        {
            throw new Exception("Cannot flush unless in write mode!!");
        }
    }

    private void fill() throws Exception
    {
        if ("r".equals(this.mode))
        {
            for (int i = 0; i < buffer.length; i++)
            {
                String line = reader.readLine();

                if (line != null)
                {
                    buffer[i] = Integer.parseInt(line);
                }
                else
                {
                    buffer[i] = Integer.MAX_VALUE;
                }

                bufferEntryCount++;
            }
        }
        else
        {
            throw new Exception("Cannot fill unless in read mode!!");
        }
    }

    public static void main(String[] args) throws Exception
    {
        FileBackedBuffer buffer = new FileBackedBuffer(5, "D:\\temp\\backingFile.txt", "w");

        for (int i = 0; i < 100; i++)
        {
            buffer.add(i);
        }

        buffer.close();

        buffer = new FileBackedBuffer(5, "D:\\temp\\backingFile.txt", "r");

        for (int i = 0; i < 100; i++)
        {
            System.out.println(buffer.read());
        }
    }
}
