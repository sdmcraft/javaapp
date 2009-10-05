package com.sdm.FLVParser.datatypes;

import java.io.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class FLVHeader {
	private U8[] signature;
	private U8 version;
	private U8 flags;
	private U32 dataoffset;
	boolean audioTagPresent;
	boolean videoTagPresent;

	public FLVHeader(PushbackInputStream in) throws Exception {
		signature = new U8[3];
		U8 byte1 = new U8(in);
		U8 byte2 = new U8(in);
		U8 byte3 = new U8(in);
		if (!byte1.equals(new U8((byte) 0x46))
				|| !byte2.equals(new U8((byte) 0x4C))
				|| !byte3.equals(new U8((byte) 0x56))) {
			unread(in);
			throw new InvalidDataException("Invalid FLV Signature!!!");
		}
		version = new U8(in);
		flags = new U8(in);
		if (!validateFlags()) {
			unread(in);
			throw new InvalidDataException("Invalid FLV flags!!!");
		}
		dataoffset = new U32(in);
		if (dataoffset.getIntValue() != 9) {
			unread(in);
			throw new InvalidDataException("Invalid data offset!!!");
		}

	}

	private boolean validateFlags() {
		boolean result = true;
		byte flagByte = flags.getValue();
		result = (flagByte & 0xF8) == 0;
		result = (flagByte & 0x02) == 0;
		if (result) {
			audioTagPresent = (flagByte & 0x04) != 0;
			videoTagPresent = (flagByte & 0x01) != 0;
		}
		return result;
	}

	public U8[] getSignature() {
		return signature;
	}

	public void setSignature(U8[] signature) throws Exception {
		if (signature.length != 3)
			throw new Exception("Signature must be a 3 bytes long!!!");
		this.signature = signature;
	}

	public U8 getVersion() {
		return version;
	}

	public void setVersion(U8 version) {
		this.version = version;
	}

	public U8 getFlags() {
		return flags;
	}

	public void setFlags(U8 flags) {
		this.flags = flags;
	}

	public U32 getDataoffset() {
		return dataoffset;
	}

	public void setDataoffset(U32 dataoffset) {
		this.dataoffset = dataoffset;
	}

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
