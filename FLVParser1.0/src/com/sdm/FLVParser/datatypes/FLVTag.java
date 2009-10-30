package com.sdm.FLVParser.datatypes;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class FLVTag {
	private U8 tagType;
	private String tagTypeString;
	private U24 dataSize;
	private U24 timeStamp;
	private U8 timeStampExtended;
	private U24 streamID;
	private TagData data;
	private byte[] dataBytes;

	public void write(OutputStream out) throws Exception {
		tagType.write(out);
		dataSize.write(out);
		timeStamp.write(out);
		timeStampExtended.write(out);
		streamID.write(out);
		data.write(out);
	}

	public FLVTag(PushbackInputStream in) throws Exception {
		tagType = new U8(in);
		// //System.out.println("Tag type:" + tagType.getValue());
		if (tagType.getValue() == 8)
			tagTypeString = "audio";
		else if (tagType.getValue() == 9)
			tagTypeString = "video";
		else if (tagType.getValue() == 18)
			tagTypeString = "scriptdata";
		else
			throw new InvalidDataException("Invalid tag type!", in);
		dataSize = new U24(in);
		// //System.out.println("Data size:" + dataSize.getIntValue());
		if (dataSize.getIntValue() < 0)
			throw new InvalidDataException("Sub zero data size for the tag!!!",
					in);
		timeStamp = new U24(in);
		timeStampExtended = new U8(in);
		streamID = new U24(in);
		if (streamID.getIntValue() != 0)
			throw new InvalidDataException("Invalid streamId! Must be 0!!", in);
		if (dataSize.getIntValue() > 0) {
			dataBytes = new byte[dataSize.getIntValue()];
			if (in.read(dataBytes) != dataSize.getIntValue())
				throw new InvalidDataException("Unable to read tag data!!!", in);
			// for (byte b : dataBytes)
			// //System.out.print(b + " ");

			PushbackInputStream tagDataIn = new PushbackInputStream(
					new ByteArrayInputStream(dataBytes), 2048);
			try {
				if (tagType.getValue() == 8)
					data = new AudioData(tagDataIn);
				else if (tagType.getValue() == 9)
					data = new VideoData(tagDataIn);
				else if (tagType.getValue() == 18)
					data = new ScriptData(tagDataIn);
			} finally {
				if (tagDataIn != null)
					tagDataIn.close();
			}
		}
	}

	public U8 getTagType() {
		return tagType;
	}

	public void setTagType(U8 tagType) {
		this.tagType = tagType;
	}

	public U24 getDataSize() {
		return dataSize;
	}

	public void setDataSize(U24 dataSize) {
		this.dataSize = dataSize;
	}

	public U24 getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(U24 timeStamp) {
		this.timeStamp = timeStamp;
	}

	public U8 getTimeStampExtended() {
		return timeStampExtended;
	}

	public void setTimeStampExtended(U8 timeStampExtended) {
		this.timeStampExtended = timeStampExtended;
	}

	public U24 getStreamID() {
		return streamID;
	}

	public void setStreamID(U24 streamID) {
		this.streamID = streamID;
	}

	public TagData getData() {
		return data;
	}

	public void setData(TagData data) {
		this.data = data;
	}
}
