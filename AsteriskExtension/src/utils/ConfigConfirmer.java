package utils;

import java.io.File;

public class ConfigConfirmer {

	private final String asteriskConfFolder;

	public ConfigConfirmer(String asteriskConfFolder) {
		this.asteriskConfFolder = asteriskConfFolder;
	}

	public boolean confirmMeetMeRoom(String roomNumber) throws Exception {
		Config meetMeConfig = new Config(asteriskConfFolder + File.separator
				+ "meetme.conf");
		return meetMeConfig.entryChecker("rooms", "conf", roomNumber, 0);
	}

}
