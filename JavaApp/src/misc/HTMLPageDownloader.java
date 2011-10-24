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

public class HTMLPageDownloader {

	public static String getHtmlSource(String urlStr) throws Exception {
		URL url = new URL(urlStr);
		BufferedReader bufferedReader = null;
		try {
			bufferedReader = new BufferedReader(new InputStreamReader(url
					.openStream()));
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
			String localFile = null;
			StringTokenizer st = new StringTokenizer(url.getFile(), "/");
			while (st.hasMoreTokens())
				localFile = st.nextToken();
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

	private static String fixCSS(String rawHtml) throws Exception {
		URL cssFixService = new URL(
				"http://premailer.dialect.ca/api/0.1/documents");
		URLConnection urlConnection = cssFixService.openConnection();
		StringBuilder query = new StringBuilder();
		query.append("html=" + URLEncoder.encode(rawHtml, "UTF-8"));
		urlConnection.setDoOutput(true);
		urlConnection.setDoInput(true);
		OutputStreamWriter wr = new OutputStreamWriter(urlConnection
				.getOutputStream());
		wr.write(query.toString());
		wr.flush();
		// Get the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(
				urlConnection.getInputStream()));
		String line;
		while ((line = rd.readLine()) != null) {
			System.out.println(line);
		}
		wr.close();
		rd.close();
		return rd.toString();

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
		} finally {
			out.close();
		}
	}

	public static void downloadHtmlPageWithImages(String pageUrl,
			String targetFolder) throws Exception {

		String pageParent = pageUrl.substring(0, pageUrl.lastIndexOf("/"));
		String htmlSource = getHtmlSource(pageUrl);
		List<String> imageList = getReferredImages(htmlSource, pageParent);
		String htmlFile = downloadFile(pageUrl, targetFolder);
		for (String image : imageList)
			downloadFile(image, targetFolder);
		String rewrittenHtmlSource = rewriteImagePaths(htmlSource);
		fixCSS(rewrittenHtmlSource);
		stringToFile(rewrittenHtmlSource, htmlFile);
	}
	
	public static void main(String[] args) throws Exception {
		downloadHtmlPageWithImages("http://satyadeep.cloudaccess.net", "temp");
	}
}
