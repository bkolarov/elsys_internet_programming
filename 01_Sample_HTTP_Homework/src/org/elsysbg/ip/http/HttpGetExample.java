package org.elsysbg.ip.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpGetExample {

	private static final String HTTP_METHOD_GET = "GET";
	private static final int HTTP_PORT = 80;
	private static final String PROTOCOL_VERSION = "HTTP/1.1";
	private static final String HOST = "google.bg";

	public static void main(String[] args) throws UnknownHostException,
			IOException {
		
		request(HOST, "/");
	}

	private static void request(String host, String path) throws UnknownHostException, IOException {
		final HttpGetExample example = new HttpGetExample();
		final CharacterHttpResponse response = example.createRequest(
				host, HTTP_METHOD_GET, path);
		
		String statusLine = new String(response.getStatusLine());
		final int statusCode = getStatusCode(statusLine);
		
		if (statusCode != 200) {
			final String location = getLocationFromHeader(response);
			if (location != null) {
				request(location.substring(0, location.length() - 1).replace("http://", ""), "/");
			}
		} else {
			printHeaders(response);
		}
		
		
	}

	private static void printHeaders(CharacterHttpResponse response) {
		System.out.println(response.getStatusLine());
		
		for (HttpHeader next : response.getHeaders()) {
			System.out.printf("%s: %s\n", next.getName(), next.getValue());
		}
	}

	private static String getLocationFromHeader(CharacterHttpResponse response) {
		for (HttpHeader next : response.getHeaders()) {		
			if (next.getName().contains("Location")) {
				return next.getValue();
			}
		}
		
		return null;
	}

	private static int getStatusCode(String statusLine) {
		final Pattern pattern = Pattern.compile("\\d{3}");
		final Matcher matcher = pattern.matcher(statusLine);
		
		int result;
		
		if (matcher.find()) {
			result = new Integer(matcher.group(0));
		} else {
			result = -1;
		}

		return result;
	}
	
	public CharacterHttpResponse createRequest(String host, String method,
			String path) throws UnknownHostException, IOException {
		final Socket clientSocket = new Socket(host, HTTP_PORT);
		
		try {
			final InputStream inputStream = clientSocket.getInputStream();
			final OutputStream outputStream = clientSocket.getOutputStream();

			final InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);
			final BufferedReader in = new BufferedReader(inputStreamReader);
			final PrintWriter out = new PrintWriter(outputStream);

			writeRequest(out, host, method, path);
			out.flush();

			return parseResponse(in);
		} finally {
			clientSocket.close();
		}

	}

	private CharacterHttpResponse parseResponse(BufferedReader in)
			throws IOException {
		final CharacterHttpResponse result = new CharacterHttpResponse();

		result.setStatusLine(in.readLine());

		String next;

		while (!(next = in.readLine()).equals("")) {
			result.getHeaders().add(HttpHeader.createFromHeaderLine(next));
		}

		// TODO chunked transfer-encoding is not supported!

		// reading body - we already know how many bytes the body is
		// (from the content-length header line)
		in.read(result.getBody());
		return result;
	}

	private void writeRequest(PrintWriter out, String host, String method,
			String path) {
		out.printf("%s %s %s\n", method, path, PROTOCOL_VERSION);
		out.printf("Host: %s\n", host);
		out.printf("\n");
	}

}