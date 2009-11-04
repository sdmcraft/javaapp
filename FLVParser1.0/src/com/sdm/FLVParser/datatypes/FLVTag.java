package com.sdm.FLVParser.datatypes;

import java.io.ByteArrayInputStream;
import java.io.OutputStream;

import com.sdm.FLVParser.utils.PushbackInputStream;

import com.sdm.FLVParser.exceptions.InvalidDataException;

// TODO: Auto-generated Javadoc
/**
 * The Class FLVTag.
 */
public class FLVTag {
	
	/** The tag type. */
	private U8 tagType;
	
	/** The tag type string. */
	private String tagTypeString;
	
	/** The data size. */
	private U24 dataSize;
	
	/** The time stamp. */
	private U24 timeStamp;
	
	/** The time stamp extended. */
	private U8 timeStampExtended;
	
	/** The stream id. */
	private U24 streamID;
	
	/** The data. */
	private TagData data;
	
	/** The data bytes. */
	private byte[] dataBytes;

	/**
	 * Write.
	 * 
	 * @param out the out
	 * 
	 * @throws Exception the exception
	 */
	public void write(OutputStream out) throws Exception {
		tagType.write(out);
		dataSize.write(out);
		timeStamp.write(out);
		timeStampExtended.write(out);
		streamID.write(out);
		data.write(out);
	}

	/**
	 * Instantiates a new fLV tag.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
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

	/**
	 * Gets the tag type.
	 * 
	 * @return the tag type
	 */
	public U8 getTagType() {
		return tagType;
	}

	/**
	 * Sets the tag type.
	 * 
	 * @param tagType the new tag type
	 */
	public void setTagType(U8 tagType) {
		this.tagType = tagType;
	}

	/**
	 * Gets the data size.
	 * 
	 * @return the data size
	 */
	public U24 getDataSize() {
		return dataSize;
	}

	/**
	 * Sets the data size.
	 * 
	 * @param dataSize the new data size
	 */
	public void setDataSize(U24 dataSize) {
		this.dataSize = dataSize;
	}

	/**
	 * Gets the time stamp.
	 * 
	 * @return the time stamp
	 */
	public U24 getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Sets the time stamp.
	 * 
	 * @param timeStamp the new time stamp
	 */
	public void setTimeStamp(U24 timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Gets the time stamp extended.
	 * 
	 * @return the time stamp extended
	 */
	public U8 getTimeStampExtended() {
		return timeStampExtended;
	}

	/**
	 * Sets the time stamp extended.
	 * 
	 * @param timeStampExtended the new time stamp extended
	 */
	public void setTimeStampExtended(U8 timeStampExtended) {
		this.timeStampExtended = timeStampExtended;
	}

	/**
	 * Gets the stream id.
	 * 
	 * @return the stream id
	 */
	public U24 getStreamID() {
		return streamID;
	}

	/**
	 * Sets the stream id.
	 * 
	 * @param streamID the new stream id
	 */
	public void setStreamID(U24 streamID) {
		this.streamID = streamID;
	}

	/**
	 * Gets the data.
	 * 
	 * @return the data
	 */
	public TagData getData() {
		return data;
	}

	/**
	 * Sets the data.
	 * 
	 * @param data the new data
	 */
	public void setData(TagData data) {
		this.data = data;
	}
}
