package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class VideoData implements TagData {
	private U8 videoInfo;
	private List<U8> videoDataList;
	private String frameType;
	private String codecId;

	public VideoData(PushbackInputStream in) throws Exception {
		videoInfo = new U8(in);
		validateVideoInfo(in);
		byte b = -1;
		videoDataList = new ArrayList<U8>();
		while ((b = (byte) in.read()) != -1) {
			videoDataList.add(new U8(b));
		}
	}

	public void write(OutputStream out) throws Exception {
		videoInfo.write(out);
		for (U8 u8 : videoDataList)
			u8.write(out);

	}

	public U8 getVideoInfo() {
		return videoInfo;
	}

	public void setVideoInfo(U8 videoInfo) {
		this.videoInfo = videoInfo;
	}

	private void validateVideoInfo(PushbackInputStream in) throws Exception {
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
			// throw new InvalidDataException("Invalid frame type!!!", in);
			////System.out.println("WARNING-Invalid frame type!!!");
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
			throw new InvalidDataException("Invalid Codec ID!!!", in);
		}
	}

}
