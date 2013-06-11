package dataStructuresV2.exception;

public class InvalidDataException extends Exception
{
    public final Code code;

    public InvalidDataException(Code code, String message)
    {
        super(message);
        this.code = code;
    }
    public static enum Code
    {DUPLICATE, INVALID, MISSING;
    }
}
