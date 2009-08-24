package com.sdm.FLVParser.datatypes;

public class VideoData implements TagData {
	private UI8 videoInfo;
	private UI8[] videoData;

	public UI8 getVideoInfo() {
		return videoInfo;
	}

	public void setVideoInfo(UI8 videoInfo) {
		this.videoInfo = videoInfo;
	}

	public UI8[] getVideoData() {
		return videoData;
	}

	public void setVideoData(UI8[] videoData) {
		this.videoData = videoData;
	}

}
