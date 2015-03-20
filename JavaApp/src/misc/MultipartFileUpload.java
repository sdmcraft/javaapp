package misc;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

public class MultipartFileUpload {
	public static void main(String args[]) throws HttpException, IOException {
		// String url = "http://localhost:8080/WebApp/servlet";
		String url = "http://localhost:8080/WebApp/Cookie Servlet";
		HttpClient client = new HttpClient();
		HttpMethod method = new GetMethod(url);
		// StringBuilder queryStr = new StringBuilder("");
		// queryStr.append("action=produce");
		// method.setQueryString(queryStr.toString());
		client.executeMethod(method);
		System.out.println("Response:" + method.getResponseBodyAsString());
		method.releaseConnection();
	}

}
