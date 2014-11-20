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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Request {
	public static final String HTTP_GET = "GET";
	public static final String HTTP_POST = "POST";
	public static final String HTTP_VERSION_1_1 = "HTTP/1.1";
	public static final String HTTP_VERSION_1_0 = "HTTP/1.0";
	public static final int HTTP_PORT = 80;
	private static final String ENCODING = "UTF-8";
	private static final int MAX_CHUNK_SIZE = 1024 * 1024 * 1;

	public HttpResponse createRequest(String host, String path, String method,
			Map<String, String> body) throws UnknownHostException, IOException {
		return createRequest(host, path, method, ConvertMapToString(body));
	}

	public HttpResponse createRequest(String host, String path, String method)
			throws UnknownHostException, IOException {
		String body = null;
		return createRequest(host, path, method, body);
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

			httpResponse = parseResponse(bufferedReader);

			if (getStatusCode(httpResponse.getStatusLine()) != 200) {
				socket.close();
				return createRequest(httpResponse.getHeaderValue("Location")
						.replace("http:", "").replace("/", ""), "/", HTTP_GET);
			}
			
			readBody(bufferedReader, httpResponse);
			
			httpResponse.printHeaders();
			System.out.println("\n" + httpResponse.getBody());
			
		} finally {
			socket.close();
		}
		
		return httpResponse;
	}

	private int getStatusCode(String statusLine) {
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

	private HttpResponse parseResponse(BufferedReader bufferedReader)
			throws IOException {
		final HttpResponse response = new HttpResponse();

		response.setStatusLine(bufferedReader.readLine());

		final List<HttpHeaders> headers = new ArrayList<HttpHeaders>();

		String next;
		while (!(next = bufferedReader.readLine()).equals("")) {
			final String name = next.split(": ", 2)[0];
			final String value = next.split(": ", 2)[1];
			headers.add(new HttpHeaders(name, value));
		}

		response.setHeaders(headers);

		return response;
	}

	private void readBody(BufferedReader bufferedReader, HttpResponse response)
			throws IOException {
		final StringBuilder stringBuilder = new StringBuilder();

		if (response.isChunkedTransferEncoding()) {
			String next;
			int size;
			while ((next = bufferedReader.readLine()) != null) {
				size = Integer.parseInt(next, 16);

				if (size == 0) {
					break;
				}

				readChunkedBody(stringBuilder, size, bufferedReader);
			}

		} else {
			String next;
			while ((next = bufferedReader.readLine()) != null) {
				stringBuilder.append(next);
			}
		}

		response.setBody(stringBuilder.toString());
	}

	private void readChunkedBody(StringBuilder stringBuilder, int size,
			BufferedReader bufferedReader) throws IOException {
		final char[] chunk = new char[MAX_CHUNK_SIZE];

		while (size > 0) {
			int characterRead = bufferedReader.read(chunk, 0,
					Math.min(size, chunk.length));
			size -= characterRead;

			stringBuilder.append(chunk, 0, characterRead);
		}

		bufferedReader.readLine();
	}

	private String ConvertMapToString(Map<String, String> body)
			throws UnsupportedEncodingException {
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

	private void writeRequest(PrintWriter socketPrintWriterOut, String host,
			String method, String path, String body) {
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
