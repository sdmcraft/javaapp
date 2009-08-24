package com.sdm.FLVParser.datatypes;

public class FLVHeader {
	private UI8[] signature;
	private UI8 version;
	private UI8 flags;
	private UI32 dataoffset;

	public UI8[] getSignature() {
		return signature;
	}

	public void setSignature(UI8[] signature) throws Exception {
		if (signature.length != 3)
			throw new Exception("Signature must be a 3 bytes long!!!");
		this.signature = signature;
	}

	public UI8 getVersion() {
		return version;
	}

	public void setVersion(UI8 version) {
		this.version = version;
	}

	public UI8 getFlags() {
		return flags;
	}

	public void setFlags(UI8 flags) {
		this.flags = flags;
	}

	public UI32 getDataoffset() {
		return dataoffset;
	}

	public void setDataoffset(UI32 dataoffset) {
		this.dataoffset = dataoffset;
	}

}
