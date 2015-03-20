package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Config {

	Map<String, List<Entry>> data = new HashMap<String, List<Entry>>();

	public Config(String file) throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		Pattern contextPattern = Pattern.compile("\\[\\w+\\]");

		List<Entry> list = null;
		String context = null;
		while ((line = reader.readLine()) != null) {
			line = line.trim();
			System.out.println(line);
			if (line.startsWith(";") || "".equals(line))
				continue;
			Matcher contextMatcher = contextPattern.matcher(line);
			if (contextMatcher.matches()) {
				context = line.substring(1, line.length() - 1);
				list = new ArrayList<Entry>();
				data.put(context, list);
				continue;
			}
			String splitter = line.contains("=>") ? "=>" : "=";
			String[] entryParts = line.split(splitter);
			Entry entry = new Entry(entryParts[0],
					entryParts.length >= 2 ? entryParts[1].split(",") : null);
			list.add(entry);
		}
	}

	@Override
	public String toString() {
		return data.toString();
	}

	public boolean entryChecker(String context, String entryName,
			String entryValue, int pos) {

		List<Entry> entries = data.get(context);
		if (entries != null) {
			for (Entry entry : entries) {
				if (entry.getKey().trim().equals(entryName)) {
					if (entry.getValues()[pos].trim().equals(entryValue)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean entryChecker(String context) {
		return data.containsKey(context);
	}

	public static void main(String[] args) throws Exception {
		Config config = new Config("C:\\temp\\meetme.conf");
		System.out.println(config);
		System.out.println(config.entryChecker("rooms", "conf", "9000", 0));
	}
}
