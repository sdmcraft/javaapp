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
		String action = req.getParameter("action");
		if ("tts".equals(action)) {
			String channel = req.getParameter("channel");
			String number = req.getParameter("number");
			String message = req.getParameter("message");
			File callFile = new File(getServletContext().getInitParameter(
					"tempFolder")
					+ File.separator + (int) (Math.random() * 100) + ".call");
			if (callFile.exists())
				callFile.delete();
			callFile.createNewFile();
			PrintWriter writer = new PrintWriter(callFile);
			writer.println("Channel:" + channel + "/" + number);
			writer.println("Application:Playback");
			writer.println("Data: " + message);
			Runtime.getRuntime().exec(
					"mv "
							+ callFile
							+ " "
							+ getServletContext().getInitParameter(
									"outgoingFolder") + File.separator
							+ callFile.getName());
		}
	}

}
