package org.sdm.timerecord.android;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class Utils {

	public static String millisecondsToHHMMSSms(Long ms) {
		Date date = new Date(ms - TimeZone.getDefault().getRawOffset());
		DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
		return formatter.format(date);

	}

}
