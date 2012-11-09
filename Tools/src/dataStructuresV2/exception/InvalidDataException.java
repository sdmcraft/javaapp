package dataStructuresV2.exception;

public class InvalidDataException extends Exception {

	public static enum Code {
		DUPLICATE, INVALID, MISSING
	};

	public final Code code;

	public InvalidDataException( Code code, String message) {
		super(message);
		this.code = code;
	}

}
