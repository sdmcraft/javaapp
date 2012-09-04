package org.sdm.meetme;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.felix.scr.annotations.Activate;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.http.HttpService;
import org.sdm.meetme.event.Event;

@Component
public class MeetMeActionServlet extends HttpServlet implements Observer {

	@Reference
	HttpService httpService;

	BlockingQueue<String> eventQueue = new ArrayBlockingQueue<String>(1024);

	@Activate
	protected void activate(ComponentContext componentContext) throws Exception {
		httpService.registerServlet("/meetme-service", this, null, null);
	}

	@Override
	public void update(Observable dispatcher, Object eventObject) {

		try {
			Event event = (Event) eventObject;
			switch (event.getType()) {
			case USER_JOINED:
				User user = (User) event.getData();
				user.addObserver(this);
				eventQueue
						.put("{\"type\":\"user\",\"sub-type\":\"joined\",\"data\":{\"user-id\":"
								+ user.getUserId() + "}}");
				System.out.println(user.getUserId()
						+ " joined the audio conference");
				break;
			case CONFERENCE_ENDED:
				eventQueue
						.put("{\"type\":\"conference\",\"sub-type\":\"ended\"}");

				System.out.println("The audio conference ended");
				break;
			case MUTE:
				user = (User) dispatcher;
				eventQueue
						.put("{\"type\":\"user\",\"sub-type\":\"muted\",\"data\":{\"user-id\":"
								+ user.getUserId() + "}}");
				System.out.println(user.getPhoneNumber() + " was muted");
				break;
			case UNMUTE:
				user = (User) dispatcher;
				eventQueue
						.put("{\"type\":\"user\",\"sub-type\":\"unmuted\",\"data\":{\"user-id\":"
								+ user.getUserId() + "}}");
				System.out.println(user.getPhoneNumber() + " was unmuted");
				break;
			case TALKING:
				user = (User) dispatcher;
				eventQueue
						.put("{\"type\":\"user\",\"sub-type\":\"talking\",\"data\":{\"user-id\":"
								+ user.getUserId() + "}}");
				System.out.println(user.getPhoneNumber() + " talking");
				break;
			case NOT_TALKING:
				user = (User) dispatcher;
				eventQueue
						.put("{\"type\":\"user\",\"sub-type\":\"not-talking\",\"data\":{\"user-id\":"
								+ user.getUserId() + "}}");
				System.out.println(user.getPhoneNumber() + " not talking");
				break;
			case USER_LEFT:
				user = (User) dispatcher;
				eventQueue
						.put("{\"type\":\"user\",\"sub-type\":\"left\",\"data\":{\"user-id\":"
								+ user.getUserId() + "}}");

				System.out.println(user.getPhoneNumber()
						+ " left the audio conference");
				break;
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String action = req.getParameter("action");
			PrintWriter pw = null;
			if ("subscribe".equalsIgnoreCase(action)) {
				resp.setContentType("text/event-stream");
				pw = resp.getWriter();
				while (true) {
					String data = eventQueue.take();
					pw.print("event: conference-event\n");
					pw.print("data: " + data + "\n\n");
					pw.flush();
				}
			} else if ("start-conference".equalsIgnoreCase(action)) {
				startConference(req.getParameter("ip"),
						req.getParameter("admin"), req.getParameter("pwd"),
						req.getParameter("conf-number"));
			}
			if (pw != null) {
				pw.close();
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}

	private void startConference(String ip, String admin, String pwd,
			String conferenceNumber) throws Exception {
		Context context = Context.getInstance(ip, admin, pwd);
		Conference conference = Conference.getInstance(conferenceNumber,
				context);
		conference.addObserver(this);

	}
}
