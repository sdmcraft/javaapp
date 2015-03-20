package org.sdm.meetme.osgi;

import org.osgi.service.log.LogEntry;
import org.osgi.service.log.LogListener;

public class LogWriter implements LogListener {

	@Override
	public void logged(LogEntry logEntry) {
		System.out.println("Logger:" + logEntry.getMessage());

	}

}
