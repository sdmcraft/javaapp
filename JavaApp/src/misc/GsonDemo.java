package misc;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonDemo {
	public static void main(String[] args) {
		JsonParser jsonParser = new JsonParser();
		JsonElement jsonElement = jsonParser
				.parse("{\"status\":201,"
						+ "\"message\":\"Created\","
						+ "\"version\":\"0.1\","
						+ "\"documents\":"
						+ "{\"txt\":\"https://s3.amazonaws.com/premailer/6e27f442edf1a4a895c37744875e4ec7.txt?AWSAccessKeyId=AKIAJGV6CM4KYKPTRSHA&Expires=1319974481&Signature=cS5cn4ZeThsFeNaiWobx1Nilu9k%3D\","
						+ "\"html\":\"https://s3.amazonaws.com/premailer/6e27f442edf1a4a895c37744875e4ec7.html?AWSAccessKeyId=AKIAJGV6CM4KYKPTRSHA&Expires=1319974481&Signature=U1ZvVYMYyiImTgZjPaTqg88G9ks%3D\"},"
						+ "\"options\":" + "{\"with_html_string\":true,"
						+ "\"remove_classes\":false,"
						+ "\"preserve_styles\":true," + "\"remove_ids\":false,"
						+ "\"adapter\":\"hpricot\","
						+ "\"remove_comments\":false}}");
		JsonObject jsonObject = jsonElement.getAsJsonObject();
		JsonElement documentsElement = jsonObject.getAsJsonObject("documents");	
		JsonObject documentsObject = documentsElement.getAsJsonObject();
		JsonElement htmlElement = documentsObject.get("html");		
		System.out.println(htmlElement.getAsString());
	}
}
