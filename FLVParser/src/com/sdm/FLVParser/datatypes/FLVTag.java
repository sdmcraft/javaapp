package com.sdm.FLVParser.datatypes;

public class FLVTag implements FLVBodyComponent {
	private U8 tagType;
	private U24 dataSize;
	private U24 timeStamp;
	private U8 timeStampExtended;
	private U24 streamID;
	private TagData data;
	
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
