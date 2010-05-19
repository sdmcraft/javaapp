package com.sdm.largeMap.master;

public class Slave {

	private final String url;
	private Double load;

	public Slave(String url, Double load) {
		this.url = url;
		this.load = load;
	}

	public Double getLoad() {
		return load;
	}

	public void setLoad(Double load) {
		this.load = load;
	}

	public String getUrl() {
		return url;
	}

	@Override
	public String toString() {
		return url + "@" + load;
	}

}
