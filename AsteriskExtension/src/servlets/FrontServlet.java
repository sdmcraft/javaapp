package servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontServlet extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		String context = req.getParameter("context");
		String action = req.getParameter("action");

		if ("call".equals(context)) {
			String channel = req.getParameter("channel");
			String number = req.getParameter("number");
			String tempFolder = (String) getServletContext().getAttribute(
					"tempFolder");

			File callFile = new File(tempFolder + File.separator
					+ (int) (Math.random() * 100) + ".call");
			if (callFile.exists())
				callFile.delete();
			callFile.createNewFile();

			PrintWriter callWriter = new PrintWriter(callFile);
			callWriter.println("Channel:" + channel + "/" + number);

			if ("tts".equals(action)) {
				String message = req.getParameter("message");

				Process process = Runtime.getRuntime().exec(
						"/usr/bin/text2wave -scale 1.5 -F 8000 -o "
								+ tempFolder + File.separator + "festival.wav");
				PrintWriter writer = new PrintWriter(process.getOutputStream());
				writer.print(message);
				writer.close();

				callWriter.println("Application:Playback");
				callWriter.println("Data:" + tempFolder + File.separator
						+ "festival");

			}
			else if ("meetme-dialout".equals(action)) {
				String room = req.getParameter("room");

				callWriter.println("Application:MeetMe");
				callWriter.println("Data:" + room + ",T");

			}

			callWriter.close();
			Runtime.getRuntime().exec(
					"cp "
							+ callFile
							+ " "
							+ getServletContext().getInitParameter(
									"outgoingFolder") + File.separator
							+ callFile.getName());

		}
	}

}
