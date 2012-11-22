package org.sdm.timerecord.business.model;

public enum ResourceType {
	PRINCIPAL(1);
	private int value;

	private ResourceType(int value) {
		this.value = value;
	}
}
