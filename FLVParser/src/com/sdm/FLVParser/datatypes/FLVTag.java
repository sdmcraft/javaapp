package com.sdm.FLVParser.datatypes;

public class FLVTag implements FLVBodyComponent {
	private UI8 tagType;
	private UI24 dataSize;
	private UI24 timeStamp;
	private UI8 timeStampExtended;
	private UI24 streamID;
	private TagData data;
	
	public UI8 getTagType() {
		return tagType;
	}
	public void setTagType(UI8 tagType) {
		this.tagType = tagType;
	}
	public UI24 getDataSize() {
		return dataSize;
	}
	public void setDataSize(UI24 dataSize) {
		this.dataSize = dataSize;
	}
	public UI24 getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(UI24 timeStamp) {
		this.timeStamp = timeStamp;
	}
	public UI8 getTimeStampExtended() {
		return timeStampExtended;
	}
	public void setTimeStampExtended(UI8 timeStampExtended) {
		this.timeStampExtended = timeStampExtended;
	}
	public UI24 getStreamID() {
		return streamID;
	}
	public void setStreamID(UI24 streamID) {
		this.streamID = streamID;
	}
	public TagData getData() {
		return data;
	}
	public void setData(TagData data) {
		this.data = data;
	}
}
