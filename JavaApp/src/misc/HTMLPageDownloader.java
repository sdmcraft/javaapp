package misc;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class HTMLPageDownloader {

	public static String getHtmlSource(String urlStr) throws Exception {
		URL url = new URL(urlStr);
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(
					url.openStream()));
			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
			return sb.toString();
		} finally {
			bufferedReader.close();
		}
	}

	public static List<String> getReferredImages(String htmlSource,
			String pageParent) throws Exception {
		Document htmlDoc = Jsoup.parse(htmlSource);
		Elements images = htmlDoc.getElementsByTag("img");
		List<String> imageList = new ArrayList<String>();
		for (Element image : images) {
			String src = image.attr("src");
			if (!src.startsWith("http://")) {
				if (src.startsWith("/")) {
					URL parentUrl = new URL(pageParent);
					String protocol = parentUrl.getProtocol();
					String host = parentUrl.getHost();
					int port = parentUrl.getPort();
					String hostUrl = protocol + "://" + host
							+ ((port > 0) ? ":" + port : "");
					imageList.add(hostUrl + image.attr("src"));
				} else
					imageList.add(pageParent + "/" + image.attr("src"));
			} else
				imageList.add(image.attr("src"));
		}
		return imageList;
	}

	public static String rewriteImagePaths(String htmlSource) throws Exception {
		Document htmlDoc = Jsoup.parse(htmlSource);
		Elements images = htmlDoc.getElementsByTag("img");
		for (Element image : images) {
			String src = image.attr("src");
			if (src.contains("/")) {
				src = src.substring(src.lastIndexOf("/") + 1);
				image.attr("src", src);
			}
		}
		return htmlDoc.toString();
	}

	public static String downloadFile(String urlStr, String target)
			throws Exception {
		FileOutputStream fos = null;
		BufferedInputStream is = null;
		String targetFile = null;

		new File(target).mkdirs();
		try {
			URL url = new URL(urlStr);
			is = new BufferedInputStream(url.openStream());
			String localFile = "test.html";
			targetFile = target + File.separator + localFile;
			fos = new FileOutputStream(targetFile);
			int oneChar;
			while ((oneChar = is.read()) != -1) {
				fos.write(oneChar);
			}
			return targetFile;
		} catch (Exception ex) {
			// ex.printStackTrace();
			return null;
		} finally {
			if (is != null)
				is.close();
			if (fos != null)
				fos.close();
		}
	}

	private static String fixHtmlForEmail(String rawHtmlUrl) throws Exception {
		OutputStreamWriter out = null;
		BufferedReader reader = null;
		try {
			URL cssFixService = new URL(
					"http://premailer.dialect.ca/api/0.1/documents");
			URLConnection urlConnection = cssFixService.openConnection();
			StringBuilder query = new StringBuilder();
			query.append("url=" + URLEncoder.encode(rawHtmlUrl, "UTF-8"));
			query.append("&preserve_styles=false");
			query.append("&remove_classes=true");
			//query.append("&base_url=" + URLEncoder.encode(rawHtmlUrl, "UTF-8"));
			urlConnection.setDoOutput(true);
			urlConnection.setDoInput(true);
			out = new OutputStreamWriter(urlConnection.getOutputStream());
			out.write(query.toString());
			out.flush();
			// Get the response
			reader = new BufferedReader(new InputStreamReader(
					urlConnection.getInputStream()));
			StringBuilder jsonResponse = new StringBuilder();
			String line;
			while ((line = reader.readLine()) != null) {
				jsonResponse.append(line + "\n");
			}
			JsonParser jsonParser = new JsonParser();
			JsonElement jsonElement = jsonParser.parse(jsonResponse.toString());
			JsonObject jsonObject = jsonElement.getAsJsonObject();
			JsonElement documentsElement = jsonObject
					.getAsJsonObject("documents");
			JsonObject documentsObject = documentsElement.getAsJsonObject();
			JsonElement htmlElement = documentsObject.get("html");
			return htmlElement.getAsString();
		} finally {
			if (out != null)
				out.close();
			if (reader != null)
				reader.close();
		}
	}

	public static void stringToFile(String string, String file)
			throws Exception {
		FileWriter fstream = null;
		BufferedWriter out = null;
		try {
			// Create file
			fstream = new FileWriter(file);
			out = new BufferedWriter(fstream);
			out.write(string);
		} catch (Exception ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (out != null)
				out.close();
		}
	}

	public static void downloadHtmlPageWithImages(String pageUrl,
			String imagesBaseUrl, String targetFolder) throws Exception {

		String pageParent = pageUrl.substring(0, pageUrl.lastIndexOf("/"));
		String htmlSource = getHtmlSource(pageUrl);
		List<String> imageList = getReferredImages(htmlSource, imagesBaseUrl);
		String htmlFile = downloadFile(pageUrl, targetFolder);
		for (String image : imageList)
			downloadFile(image, targetFolder);
		String rewrittenHtmlSource = rewriteImagePaths(htmlSource);
		stringToFile(rewrittenHtmlSource, htmlFile);
	}

	public static void main(String[] args) throws Exception {
		String fixedHtmlUrl = fixHtmlForEmail("https://sites.google.com/site/satyadeep1980/");
		downloadHtmlPageWithImages(fixedHtmlUrl,
				"https://sites.google.com/site/satyadeep1980/", "temp");
	}
}
