package org.sdm.meetme.osgi;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Deactivate;
import org.apache.felix.scr.annotations.Reference;
import org.osgi.framework.BundleContext;
import org.osgi.service.log.LogReaderService;
import org.osgi.service.log.LogService;

@Component
public class Activator {

	@Reference
	LogService logger;

	@Reference
	LogReaderService logReaderService;

	LogWriter logWriter;

	@Activate
	public void start(BundleContext context) throws Exception {

		logWriter = new LogWriter();
		logReaderService.addLogListener(logWriter);

		System.out.println("Starting the bundle MeetMe!!");
		logger.log(LogService.LOG_INFO, "Starting the bundle MeetMe!!");
	}

	@Deactivate
	public void stop(BundleContext context) throws Exception {
		System.out.println("Stopping the bundle MeetMe!!");
		logger.log(LogService.LOG_INFO, "Stopping the bundle MeetMe!!");
		logReaderService.removeLogListener(logWriter);
	}

}
