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

	public boolean confirmUser(String userNumber) throws Exception {
		Config userConfig = new Config(asteriskConfFolder + File.separator
				+ "users.conf");
		return userConfig.entryChecker(userNumber);
	}

}
