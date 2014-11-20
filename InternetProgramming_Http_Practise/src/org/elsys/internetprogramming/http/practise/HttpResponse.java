package org.elsys.internetprogramming.http.practise;

import java.util.List;

public class HttpResponse {
	private static final String HEADER_CONTENT_LENGTH = "content-length";
	private static final String HEADER_TRANSFER_ENCODING = "transfer-encoding";
	private static final String HEADER_VALUE_TRANSFER_ENCODING_CHUNKED = "chunked";
	
	private String statusLine;
	private List<HttpHeaders> headers;
	
	public void setStatusLine(String statusLine) {
		this.statusLine = statusLine;
	}
	
	public void setHeaders(List<HttpHeaders> headers) {
		this.headers = headers;
	}
	
	public boolean isChunkedTransferEncoding() {
		for (HttpHeaders next : headers) {
			if (next.getName().equals(HEADER_TRANSFER_ENCODING)) {
				if (next.getValue().equals(HEADER_VALUE_TRANSFER_ENCODING_CHUNKED)) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	

}
