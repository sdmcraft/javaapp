package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

/**
 * An ActionScript Date is serialized as the number of milliseconds elapsed
 * since the epoch of midnight on 1st Jan 1970 in the UTC time zone. While the
 * design of this type reserves room for time zone offset information, it should
 * not be filled in, nor used, as it is unconventional to change time zones when
 * serializing dates on a network. It is suggested that the time zone be queried
 * independently as needed.
 * 
 * @author satyam
 * 
 */
public class AMFDate extends AMFValue {
	private S16 timeZone;
	private AMFDouble value;

	/**
	 * Constructs a AMF date object by reading from the specified input stream
	 * 
	 * @param in
	 *            Input stream to read from
	 * @throws Exception
	 *             If an error occurred while reading
	 */
	public AMFDate(PushbackInputStream in) throws Exception {
		super.marker = new Marker(in);
		if (!Markers.DATE_MARKER.equals(marker.getValue())) {
			unread(in);
			throw new InvalidDataException("Invalid marker for AMF Date type!",
					in);
		}
		timeZone = new S16(in);
		if (timeZone.getIntValue() != 0) {
			unread(in);
			throw new InvalidDataException(
					"Invalid timezone for AMF Date type! Should always be 0!",
					in);
		}
		value = new AMFDouble(in);
	}

	@Override
	public void unread(PushbackInputStream in) throws Exception {
		if (value != null)
			value.unread(in);
		if (timeZone != null)
			timeZone.unread(in);
		if (marker != null)
			marker.unread(in);
	}

	@Override
	public void write(OutputStream out) throws Exception {
		marker.write(out);
		timeZone.write(out);
		value.write(out);
	}

}
