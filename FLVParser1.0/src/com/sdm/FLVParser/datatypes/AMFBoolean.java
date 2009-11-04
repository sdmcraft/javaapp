package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;
import com.sdm.FLVParser.utils.PushbackInputStream;
import com.sdm.FLVParser.utils.Tools;

// TODO: Auto-generated Javadoc
/**
 * An AMF 0 Boolean type is used to encode a primitive ActionScript 1.0 or 2.0
 * Boolean or an ActionScript 3.0 Boolean. The Object (non-primitive) version of
 * ActionScript 1.0 or 2.0 Booleans are not serializable. A Boolean type marker
 * is followed by an unsigned byte; a zero byte value denotes false while a
 * non-zero byte value (typically 1) denotes true.
 * 
 * @author satyam
 */
public class AMFBoolean extends AMFValue {
	
	/** The value. */
	private U8 value;
	
	/** The boolean value. */
	private boolean booleanValue;

	/**
	 * Constructs a AMF Boolean object by reading from the specified input
	 * stream.
	 * 
	 * @param in Input stream to read from
	 * 
	 * @throws Exception If an error occurred while reading
	 */
	public AMFBoolean(PushbackInputStream in) throws Exception {
		marker = new Marker(in);
		if (!Markers.BOOLEAN_MARKER.equals(marker.getValue())) {
			unread(in);
			throw new InvalidDataException(
					"Invalid marker for AMF Boolean type!", in);
		}

		value = new U8(in);
		if (0 == Tools.U8toInt(value))
			booleanValue = false;
		else
			booleanValue = true;
	}

	/**
	 * Returns the boolean value of this AMF boolean object.
	 * 
	 * @return boolean value of this AMF boolean object
	 */
	public boolean isBooleanValue() {
		return booleanValue;
	}

	/**
	 * Sets the boolean value of this AMF boolean object.
	 * 
	 * @param booleanValue boolean value of this AMF boolean object
	 * 
	 * @throws Exception If an internal error occurred
	 */
	public void setBooleanValue(boolean booleanValue) throws Exception {
		this.booleanValue = booleanValue;
		if (booleanValue)
			value = new U8((byte) 1);
		else
			value = new U8((byte) 0);
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#write(java.io.OutputStream)
	 */
	@Override
	public void write(OutputStream out) throws Exception {
		marker.write(out);
		value.write(out);
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#unread(com.sdm.FLVParser.utils.PushbackInputStream)
	 */
	@Override
	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			value.unread(in);
		if (marker != null)
			marker.unread(in);
	}

}
