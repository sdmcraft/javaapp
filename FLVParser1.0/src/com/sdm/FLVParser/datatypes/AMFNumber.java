package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

// TODO: Auto-generated Javadoc
/**
 * The Class AMFNumber.
 */
public class AMFNumber extends AMFValue {
	
	/** The value. */
	private AMFDouble value;

	/**
	 * Instantiates a new aMF number.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public AMFNumber(PushbackInputStream in) throws Exception {
		super.marker = new Marker(in);
		if (!Markers.NUMBER_MARKER.equals(marker.getValue())) {
			unread(in);
			throw new InvalidDataException(
					"Invalid marker for AMF Number type!", in);
		}
		value = new AMFDouble(in);
		//System.out.println("AN AMF number:" + value.getDoubleValue());
	}

	/**
	 * Gets the double value.
	 * 
	 * @return the double value
	 */
	public double getDoubleValue() {
		return value.getDoubleValue();
	}

	/**
	 * Sets the double value.
	 * 
	 * @param value the new double value
	 * 
	 * @throws Exception the exception
	 */
	public void setDoubleValue(double value) throws Exception {
		this.value.setDoubleValue(value);

	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#unread(com.sdm.FLVParser.utils.PushbackInputStream)
	 */
	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			value.unread(in);
		if (marker != null)
			marker.unread(in);
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.AMFValue#write(java.io.OutputStream)
	 */
	@Override
	public void write(OutputStream out) throws Exception {
		marker.write(out);
		value.write(out);
	}

}
