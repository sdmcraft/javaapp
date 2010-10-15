package utils;

public class Entry {
	private String key;
	private String[] values = null;

	public Entry(String key, String[] values) {
		this.key = key;
		this.values = values;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(key + "=>");
		for (String value : values) {
			sb.append(value + ",");
		}
		sb.deleteCharAt(sb.lastIndexOf(","));
		return sb.toString();
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}
}
