package org.cosiproject.toolkit.argumentor;

public enum ArgumentDelimiter {
	NONE(" ");
	
	private String value;
	
	private ArgumentDelimiter(String s) {
		value = s;
	}

	public String getDelimiter() {
		return value;
	}
}
