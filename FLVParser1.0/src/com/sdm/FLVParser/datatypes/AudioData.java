package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.sdm.FLVParser.exceptions.InvalidDataException;

// TODO: Auto-generated Javadoc
/**
 * The Class AudioData.
 */
public class AudioData implements TagData {
	
	/** The sound info. */
	private U8 soundInfo;
	
	/** The sound data list. */
	private List<U8> soundDataList;
	
	/** The sound format. */
	private String soundFormat;
	
	/** The sound rate. */
	private String soundRate;
	
	/** The sound size. */
	private String soundSize;
	
	/** The sound type. */
	private String soundType;

	/**
	 * Instantiates a new audio data.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	public AudioData(PushbackInputStream in) throws Exception {
		soundInfo = new U8(in);
		validateSoundInfo(in);
		byte b = -1;
		List<U8> soundDataList = new ArrayList<U8>();
		while ((b = (byte) in.read()) != -1) {
			soundDataList.add(new U8(b));
		}
	}

	/* (non-Javadoc)
	 * @see com.sdm.FLVParser.datatypes.TagData#write(java.io.OutputStream)
	 */
	public void write(OutputStream out) throws Exception {
		soundInfo.write(out);
		for (U8 u8 : soundDataList)
			u8.write(out);
	}

	/**
	 * Validate sound info.
	 * 
	 * @param in the in
	 * 
	 * @throws Exception the exception
	 */
	private void validateSoundInfo(PushbackInputStream in) throws Exception {
		byte soundInfoByte = soundInfo.getValue();
		switch (soundInfoByte & 0xF0) {
		case 0:
			soundFormat = "uncompressed";
			break;
		case 1:
			soundFormat = "ADPCM";
			break;
		case 2:
			soundFormat = "MP3";
			break;
		case 5:
			soundFormat = "Nellymoser 8kHz mono";
			break;
		case 6:
			soundFormat = "Nellymoser";
			break;
		default:
			// throw new InvalidDataException("Invalid sound format!!!", in);
			////System.out.println("WARNING-Invalid sound format!!!");
		}
		switch (soundInfoByte & 0x0C) {
		case 0:
			soundRate = "5.5kHz";
			break;
		case 1:
			soundRate = "11kHz";
			break;
		case 2:
			soundRate = "22kHz";
			break;
		case 3:
			soundRate = "44kHz";
			break;
		default:
			// throw new InvalidDataException("Invalid sound rate!!!", in);
			////System.out.println("WARNING-Invalid sound rate!!!");
		}
		switch (soundInfoByte & 0x02) {
		case 0:
			soundSize = "snd8bit";
			break;
		case 1:
			soundSize = "snd16bit";
			break;
		default:
			// throw new InvalidDataException("Invalid sound size!!!", in);
			////System.out.println("WARNING-Invalid sound size!!!");
		}
		switch (soundInfoByte & 0x01) {
		case 0:
			soundType = "sndMono";
			break;
		case 1:
			soundType = "sndStereo";
			if (soundFormat.contains("Nellymoser"))
				throw new InvalidDataException(
						"Steroe sound not allowed with Nellymoser!!!", in);
			break;
		default:
			throw new InvalidDataException("Invalid sound type!!!", in);
		}

	}

	/**
	 * Gets the sound info.
	 * 
	 * @return the sound info
	 */
	public U8 getSoundInfo() {
		return soundInfo;
	}

	/**
	 * Sets the sound info.
	 * 
	 * @param soundInfo the new sound info
	 */
	public void setSoundInfo(U8 soundInfo) {
		this.soundInfo = soundInfo;
	}
}
