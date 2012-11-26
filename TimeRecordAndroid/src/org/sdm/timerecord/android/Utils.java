package org.sdm.timerecord.android;

public class Utils {

	public static String millisecondsToHHMMSSms(Long ms) {
		long hours = ms / (60 * 60 * 1000);
		long leftover = ms % (60 * 60 * 1000);
		long minutes = leftover / (60 * 1000);
		leftover = leftover / (60 * 1000);
		long seconds = leftover / 1000;
		long msecs = leftover % 1000;
		return ((hours > 0) ? Long.toString(hours) + ":" : "")
				+ ((minutes > 0) ? Long.toString(minutes) + ":" : "")
				+ ((seconds > 0) ? Long.toString(seconds) + ":" : "")
				+ ((msecs > 0) ? Long.toString(msecs) + ":" : "");
	}
	
}
