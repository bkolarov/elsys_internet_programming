package org.elsys.homework.sample.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
	private static final String SERVER = "localhost";
	
	private final String requestMessage;
	
	public EchoClient(String request) {
		this.requestMessage = request;
	}
	
	public static void main(String[] args) throws IOException {
		System.out.println("Enter the date in the following dormat: dd-MM-yyy");
		
		final InputStream inputStream = System.in;
		final InputStreamReader inputSteramReader = new InputStreamReader(inputStream);
		final BufferedReader bufferedread = new BufferedReader(inputSteramReader);
		
		final String requestMessage = bufferedread.readLine();
		
		final EchoClient echoClient = new EchoClient(requestMessage);
		final String response = echoClient.send();
		System.out.println(response);
	}

	private String send() throws UnknownHostException, IOException {
		final Socket clientSocket = new Socket(SERVER, EchoServer.SERVER_PORT);
		
		final InputStream inputStream = clientSocket.getInputStream();
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

		final OutputStream outputStream = clientSocket.getOutputStream();
		final PrintWriter outWriter = new PrintWriter(outputStream);
		
		outWriter.println(requestMessage);
		outWriter.flush();
		
		final String response = bufferedReader.readLine();
		clientSocket.close();
		
		return response;
	}
}
