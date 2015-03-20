package dataStructures;

import java.io.LineNumberReader;
import java.io.Reader;


public class JumpLineNumberReader extends LineNumberReader
{
    public JumpLineNumberReader(Reader in)
    {
        super(in);

        // TODO Auto-generated constructor stub
    }

    public JumpLineNumberReader(Reader in, int sz)
    {
        super(in, sz);

        // TODO Auto-generated constructor stub
    }

    public void jump(int jump) throws Exception
    {
        int i = 0;

        while (i < jump)
        {
            this.readLine();
        }
    }
}
