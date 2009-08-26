package com.sdm.FLVParser.datatypes;

public class FLVHeader {
	private U8[] signature;
	private U8 version;
	private U8 flags;
	private U32 dataoffset;

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

}
