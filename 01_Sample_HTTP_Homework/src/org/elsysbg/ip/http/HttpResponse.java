package org.elsysbg.ip.http;

import java.util.LinkedList;
import java.util.List;

public class HttpResponse {
	private String statusLine;
	private final List<HttpHeader> headers = new LinkedList<HttpHeader>();
	private byte[] body;

	public String getStatusLine() {
		return this.statusLine;
	}

	public List<HttpHeader> getHeaders() {
		return this.headers;
	}

	public byte[] getBody() {
		if (body == null) {
			body = new byte[getContentLength()];
		}

		return this.body;
	}

	private int getContentLength() {
		for (HttpHeader next : headers) {
			if (next.getName().toLowerCase().equals("content-length")) {
				final int result = Integer.parseInt(next.getValue());
				return Math.min(1024 * 1024 * 10, result);
			}
		}
		return 0;
	}

	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}
}