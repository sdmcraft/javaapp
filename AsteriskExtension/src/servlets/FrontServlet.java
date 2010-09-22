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
			String tempFolder = (String) getServletContext().getAttribute(
					"tempFolder");
		
			Process process = Runtime.getRuntime().exec("/usr/bin/text2wave -scale 1.5 -F 8000 -o " + tempFolder + File.separator + "festival.wav");
			PrintWriter writer = new PrintWriter(process.getOutputStream());
			writer.print(message);
			writer.close();
			
			File callFile = new File(tempFolder + File.separator
					+ (int) (Math.random() * 100) + ".call");
			if (callFile.exists())
				callFile.delete();
			callFile.createNewFile();
			writer = new PrintWriter(callFile);
			writer.println("Channel:" + channel + "/" + number);
			writer.println("Application:Playback");
			writer.println("Data:" + tempFolder + File.separator
					+ "festival");
			writer.close();
			System.out.println("cp " + callFile + " "
					+ getServletContext().getInitParameter("outgoingFolder")
					+ File.separator + callFile.getName());
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
