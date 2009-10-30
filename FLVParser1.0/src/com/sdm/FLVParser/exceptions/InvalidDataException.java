package com.sdm.FLVParser.exceptions;

import com.sdm.FLVParser.utils.PushbackInputStream;

public class InvalidDataException extends Exception {
	public InvalidDataException(String message, PushbackInputStream in) {
		super(message + " @ " + in.getBytesRead());
	}

}
