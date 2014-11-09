package org.elsysbg.ip.http;

import java.util.LinkedList;
import java.util.List;

public class CharacterHttpResponse {
	// should be in lower case
	private static final String HEADER_CONTENT_LENGTH = "content-length";
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 10;
	private String statusLine;
	private final List<HttpHeader> headers = new LinkedList<HttpHeader>();
	private char[] body;

	public String getStatusLine() {
		return statusLine;
	}

	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}

	public List<HttpHeader> getHeaders() {
		return this.headers;
	}

	public char[] getBody() {
		if (body == null) {
			body = new char[getContentLength()];
		}
		return this.body;
	}

	private int getContentLength() {
		for (HttpHeader next : headers) {
			// in HTTP header names are case insensitive
			if (next.getName().toLowerCase().equals(HEADER_CONTENT_LENGTH)) {
				final int result = Integer.parseInt(next.getValue());
				// prevent heavy memory allocation
				return Math.min(MAX_REQUEST_SIZE, result);
			}
		}
		return 0;
	}

}