package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.sdm.FLVParser.exceptions.InvalidDataException;

// TODO: Auto-generated Javadoc
/**
 * The Class VideoData.
 */
public class VideoData implements TagData {
	
	/** The video info. */
	private U8 videoInfo;
	
	/** The video data list. */
	private List<U8> videoDataList;
	
	/** The frame type. */
	private String frameType;
	
	/** The codec id. */
	private String codecId;

	/**
	 * Instantiates a new video data.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public VideoData(PushbackInputStream in) throws Exception {
		videoInfo = new U8(in);
		validateVideoInfo(in);
		byte b = -1;
		videoDataList = new ArrayList<U8>();
		while ((b = (byte) in.read()) != -1) {
			videoDataList.add(new U8(b));
		}
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.TagData#write(java.io.OutputStream)
	 */
	public void write(OutputStream out) throws Exception {
		videoInfo.write(out);
		for (U8 u8 : videoDataList)
			u8.write(out);

	}

	/**
	 * Gets the video info.
	 * 
	 * @return the video info
	 */
	public U8 getVideoInfo() {
		return videoInfo;
	}

	/**
	 * Sets the video info.
	 * 
	 * @param videoInfo the new video info
	 */
	public void setVideoInfo(U8 videoInfo) {
		this.videoInfo = videoInfo;
	}

	/**
	 * Validate video info.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
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
