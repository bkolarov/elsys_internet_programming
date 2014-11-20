package org.elsys.internetprogramming.http.practise;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Map.Entry;

@SuppressWarnings("resource")
public class Request {
	public static final String HTTP_GET = "GET";
	public static final String HTTP_POST = "POST";
	public static final String HTTP_VERSION_1_1 = "HTTP/1.1";
	public static final String HTTP_VERSION_1_0 = "HTTP/1.0";
	public static final int HTTP_PORT = 80;
	private static final String ENCODING = "UTF-8";

	public Request() {

	}

	public HttpResponse createRequest(String host, String path, String method,
			Map<String, String> body) throws UnknownHostException, IOException {
		return createRequest(host, path, method, ConvertMapToString(body));
	}

	private String ConvertMapToString(Map<String, String> body) throws UnsupportedEncodingException {
		final StringBuilder result = new StringBuilder();

		for (Entry<String, String> next : body.entrySet()) {
			if (result.length() > 0) {
				result.append("&");
			}
			
			result.append(URLEncoder.encode(next.getKey(), ENCODING));
			result.append("=");
			result.append(URLEncoder.encode(next.getValue(), ENCODING));
		}

		return result.toString();
	}

	public HttpResponse createRequest(String host, String path, String method,
			String body) throws UnknownHostException, IOException {
		HttpResponse httpResponse = null;

		final Socket socket = new Socket(host, HTTP_PORT);
		try {

			final InputStream socketInputStream = socket.getInputStream();
			final InputStreamReader inputStreamReader = new InputStreamReader(
					socketInputStream);
			final BufferedReader bufferedReader = new BufferedReader(
					inputStreamReader);

			final OutputStream socketOutputStream = socket.getOutputStream();
			final PrintWriter socketPrintWriterOut = new PrintWriter(
					socketOutputStream);

			writeRequest(socketPrintWriterOut, host, method, path, body);
			socketPrintWriterOut.flush();

			while (bufferedReader.readLine() != null) {
				System.out.println(bufferedReader.readLine());
			}

			// parseResponse();

			return httpResponse;
		} finally {
			socket.close();
		}
	}

	private void writeRequest(PrintWriter socketPrintWriterOut, String host,
			String method, String path, String body) {
		System.out.println("WriteRequest");
		socketPrintWriterOut.printf("%s %s %s\n", method, path,
				HTTP_VERSION_1_1);
		socketPrintWriterOut.printf("Host: %s\n", host);

		if (body != null) {
			socketPrintWriterOut.printf("Content-Length: %d\n", body.length());
			socketPrintWriterOut.printf("Content-Type: %s\n",
					"application/x-www-form-urlencoded");
			socketPrintWriterOut.printf("\n");
			socketPrintWriterOut.println(body);
		} else {
			socketPrintWriterOut.printf("\n");
		}

	}
}
