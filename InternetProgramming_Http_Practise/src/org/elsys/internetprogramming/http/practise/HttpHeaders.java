package org.elsys.internetprogramming.http.practise;

public class HttpHeaders {
	private String name;
	private String value;
	
	public HttpHeaders(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
