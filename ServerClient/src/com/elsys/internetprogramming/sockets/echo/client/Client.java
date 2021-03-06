package com.elsys.internetprogramming.sockets.echo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public final static String SERVER = "localhost";
	private String message;
	
	public Client(String message) {
		this.message = message;
	}
	
	public static void main(String[] args) throws IOException {
		final InputStream inputStream = System.in;
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		final String message = bufferedReader.readLine();
		
		Client client = new Client(message);
		
		final String response = client.send();
		System.out.println(response);
	}
	
	
	public String send() throws UnknownHostException, IOException {
		String response = null;
		final Socket clientSocket = new Socket(SERVER, Server.SERVER_PORT);
		
		final InputStream inputStream = clientSocket.getInputStream();
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		final BufferedReader buffereedReader = new BufferedReader(inputStreamReader);
		
		final OutputStream outputStream = clientSocket.getOutputStream();
		
		final PrintWriter printWriter = new PrintWriter(outputStream);
		
		printWriter.println(this.message);
		printWriter.flush();
		
		response = buffereedReader.readLine();
		
		clientSocket.close();
		
		return response;
	}
}
