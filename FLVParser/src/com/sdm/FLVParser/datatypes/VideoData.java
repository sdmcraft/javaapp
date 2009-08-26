package com.sdm.FLVParser.datatypes;

public class VideoData implements TagData {
	private U8 videoInfo;
	private U8[] videoData;

	public U8 getVideoInfo() {
		return videoInfo;
	}

	public void setVideoInfo(U8 videoInfo) {
		this.videoInfo = videoInfo;
	}

	public U8[] getVideoData() {
		return videoData;
	}

	public void setVideoData(U8[] videoData) {
		this.videoData = videoData;
	}

}
