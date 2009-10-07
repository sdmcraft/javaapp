package com.sdm.FLVParser.datatypes;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class VideoData implements TagData {
	private U8 videoInfo;
	private U8[] videoData;
	private String frameType;
	private String codecId;

	public VideoData(InputStream in) throws Exception {
		videoInfo = new U8(in);
		validateVideoInfo();
		byte b = -1;
		List<U8> videoDataList = new ArrayList<U8>();
		while ((b = (byte) in.read()) != -1) {
			videoDataList.add(new U8(b));
		}
		videoData = (U8[]) videoDataList.toArray();

	}

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

	private void validateVideoInfo() throws Exception {
		byte videoInfoByte = videoInfo.getValue();
		switch (videoInfoByte & 0xF0) {
		case 1:
			frameType = "keyframe";
			break;
		case 2:
			frameType = "inter frame";
			break;
		case 3:
			frameType = "disposable inter frame";
			break;
		default:
			throw new InvalidDataException("Invalid frame type!!!");
		}
		switch (videoInfoByte & 0x0F) {
		case 2:
			codecId = "Sorenson H.263";
			break;
		case 3:
			codecId = "Screen video";
			break;
		case 4:
			codecId = "On2 VP6";
			break;
		case 5:
			codecId = "On2 VP6 with alpha channel";
			break;
		case 6:
			codecId = "Screen video version 2";
			break;
		default:
			throw new InvalidDataException("Invalid Codec ID!!!");
		}
	}

}
