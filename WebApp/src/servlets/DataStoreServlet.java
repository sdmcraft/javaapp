package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

public class DataStoreServlet extends HttpServlet {

	private static final Map<String, String> dataStore = new HashMap<String, String>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		if (req.getParameter("key") != null) {
			dataStore.put(req.getParameter("key"), req.getParameter("value"));
		}
		JSONObject jsonObject = new JSONObject(dataStore);
		resp.setContentType("application/json");
		resp.getOutputStream().write(jsonObject.toString().getBytes());
		resp.getOutputStream().close();
	}

}
