package com.sdm.FLVParser.datatypes;

public class AudioData implements TagData {
	private UI8 soundInfo;
	private UI8[] soundData;
	public UI8 getSoundInfo() {
		return soundInfo;
	}
	public void setSoundInfo(UI8 soundInfo) {
		this.soundInfo = soundInfo;
	}
	public UI8[] getSoundData() {
		return soundData;
	}
	public void setSoundData(UI8[] soundData) {
		this.soundData = soundData;
	}
}
