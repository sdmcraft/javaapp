package com.sdm.FLVParser.datatypes;

public class AudioData implements TagData {
	private U8 soundInfo;
	private U8[] soundData;
	public U8 getSoundInfo() {
		return soundInfo;
	}
	public void setSoundInfo(U8 soundInfo) {
		this.soundInfo = soundInfo;
	}
	public U8[] getSoundData() {
		return soundData;
	}
	public void setSoundData(U8[] soundData) {
		this.soundData = soundData;
	}
}
