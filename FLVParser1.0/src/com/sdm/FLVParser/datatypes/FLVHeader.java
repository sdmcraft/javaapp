package com.sdm.FLVParser.datatypes;

import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

// TODO: Auto-generated Javadoc
/**
 * The Class FLVHeader.
 */
public class FLVHeader {
	
	/** The signature. */
	private U8[] signature;
	
	/** The version. */
	private U8 version;
	
	/** The flags. */
	private U8 flags;
	
	/** The dataoffset. */
	private U32 dataoffset;
	
	/** The audio tag present. */
	boolean audioTagPresent;
	
	/** The video tag present. */
	boolean videoTagPresent;

	/**
	 * Instantiates a new fLV header.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public FLVHeader(PushbackInputStream in) throws Exception {
		U8 byte1 = new U8(in);
		U8 byte2 = new U8(in);
		U8 byte3 = new U8(in);
		if (!byte1.equals(new U8((byte) 0x46))
				|| !byte2.equals(new U8((byte) 0x4C))
				|| !byte3.equals(new U8((byte) 0x56))) {
			// unread(in);
			throw new InvalidDataException("Invalid FLV Signature!!!", in);
		} else
			signature = new U8[] { byte1, byte2, byte3 };
		version = new U8(in);
		flags = new U8(in);
		if (!validateFlags()) {
			// unread(in);
			// throw new InvalidDataException("Invalid FLV flags!!!");
			////System.out.println("WARNING-Invalid FLV flags!!!");
		}
		dataoffset = new U32(in);
		if (dataoffset.getIntValue() != 9) {
			// unread(in);
			throw new InvalidDataException("Invalid data offset!!!", in);
		}

	}

	/**
	 * Write.
	 * 
	 * @param out the out
	 * 
	 * @throws Exception the exception
	 */
	public void write(OutputStream out) throws Exception {
		for (U8 u8 : signature)
			u8.write(out);
		version.write(out);
		flags.write(out);
		dataoffset.write(out);
	}

	/**
	 * Validate flags.
	 * 
	 * @return true, if successful
	 */
	private boolean validateFlags() {
		boolean result = true;
		byte flagByte = flags.getValue();
		int flagInt = flagByte & 0xFF;
		result = (flagInt & 0xF8) == 0;
		result &= (flagInt & 0x02) == 0;
		if (result) {
			audioTagPresent = (flagInt & 0x04) != 0;
			videoTagPresent = (flagInt & 0x01) != 0;
		}
		return result;
	}

	/**
	 * Gets the signature.
	 * 
	 * @return the signature
	 */
	public U8[] getSignature() {
		return signature;
	}

	/**
	 * Sets the signature.
	 * 
	 * @param signature the new signature
	 * 
	 * @throws Exception the exception
	 */
	public void setSignature(U8[] signature) throws Exception {
		if (signature.length != 3)
			throw new Exception("Signature must be a 3 bytes long!!!");
		this.signature = signature;
	}

	/**
	 * Gets the version.
	 * 
	 * @return the version
	 */
	public U8 getVersion() {
		return version;
	}

	/**
	 * Sets the version.
	 * 
	 * @param version the new version
	 */
	public void setVersion(U8 version) {
		this.version = version;
	}

	/**
	 * Gets the flags.
	 * 
	 * @return the flags
	 */
	public U8 getFlags() {
		return flags;
	}

	/**
	 * Sets the flags.
	 * 
	 * @param flags the new flags
	 */
	public void setFlags(U8 flags) {
		this.flags = flags;
	}

	/**
	 * Gets the dataoffset.
	 * 
	 * @return the dataoffset
	 */
	public U32 getDataoffset() {
		return dataoffset;
	}

	/**
	 * Sets the dataoffset.
	 * 
	 * @param dataoffset the new dataoffset
	 */
	public void setDataoffset(U32 dataoffset) {
		this.dataoffset = dataoffset;
	}

	/**
	 * Unread.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public void unread(PushbackInputStream in) throws Exception {
		if (dataoffset != null)
			dataoffset.unread(in);
		if (flags != null)
			flags.unread(in);
		if (version != null)
			version.unread(in);
		if (signature != null) {
			signature[0].unread(in);
			signature[1].unread(in);
			signature[2].unread(in);
		}
	}

}
