package org.elsys.internetprogramming.http.practise;

import java.util.List;

public class HttpResponse {
	private static final String HEADER_CONTENT_LENGTH = "content-length";
	private static final String HEADER_TRANSFER_ENCODING = "transfer-encoding";
	private static final String HEADER_VALUE_TRANSFER_ENCODING_CHUNKED = "chunked";
	
	private String statusLine;
	private List<HttpHeaders> headers;
	private String body;
	
	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}
	
	public void setHeaders(List<HttpHeaders> headers) {
		this.headers = headers;
	}
	
	public boolean isChunkedTransferEncoding() {
		for (HttpHeaders next : headers) {
			if (next.getName().toLowerCase().equals(HEADER_TRANSFER_ENCODING)) {
				if (next.getValue().toLowerCase().equals(HEADER_VALUE_TRANSFER_ENCODING_CHUNKED)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public void printHeaders() {
		System.out.println(statusLine);
		
		for (HttpHeaders next : headers) {
			System.out.println(next.getName() + ": " + next.getValue());
		}
	}
	
	public String getHeaderValue(String name) {
		for (HttpHeaders next : headers) {
			if (next.getName().toLowerCase().equals(name.toLowerCase())) {
				return next.getValue();
			}
		}
		
		return null;
	}
	
	public String getStatusLine() {
		return this.statusLine;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public String getBody() {
		return this.body;
	}
}
