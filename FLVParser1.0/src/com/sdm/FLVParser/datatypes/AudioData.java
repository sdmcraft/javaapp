package com.sdm.FLVParser.datatypes;

import com.sdm.FLVParser.utils.PushbackInputStream;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.sdm.FLVParser.exceptions.InvalidDataException;

public class AudioData implements TagData {
	private U8 soundInfo;
	private List<U8> soundDataList;
	private String soundFormat;
	private String soundRate;
	private String soundSize;
	private String soundType;

	public AudioData(PushbackInputStream in) throws Exception {
		soundInfo = new U8(in);
		validateSoundInfo(in);
		byte b = -1;
		List<U8> soundDataList = new ArrayList<U8>();
		while ((b = (byte) in.read()) != -1) {
			soundDataList.add(new U8(b));
		}
	}

	public void write(OutputStream out) throws Exception {
		soundInfo.write(out);
		for (U8 u8 : soundDataList)
			u8.write(out);
	}

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

	public U8 getSoundInfo() {
		return soundInfo;
	}

	public void setSoundInfo(U8 soundInfo) {
		this.soundInfo = soundInfo;
	}
}
