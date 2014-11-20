package org.elsys.internetprogramming.http.practise;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

public class Main {
	private final static String URL = "google.bg";

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		final Request request = new Request();
		request.createRequest(URL, "/", Request.HTTP_GET);

	}

	@SuppressWarnings("unused")
	private static Map<String, String> createBody() {
		final Map<String, String> body = new HashMap<>();
		body.put("key1", "value1");
		body.put("key2", "value2");

		return body;
	}

}
