package org.elsys.homework.sample.sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.ParseException;

public class EchoServer {
	public static final int SERVER_PORT = 44012;
	
	private class ClientHandler extends Thread {
		private Socket clientSocket;
		
		public ClientHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}

		@Override
		public void run() {
			try {
				handleClient(this.clientSocket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		private void handleClient(Socket clientSocket) throws IOException {
			final InputStream inputStream = clientSocket.getInputStream();			
			final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			final OutputStream outputStream = clientSocket.getOutputStream();
			final PrintWriter printWriter = new PrintWriter(outputStream);
			
			final String readLine = bufferedReader.readLine();
			
			final DateParser dateParser = new DateParser("dd-MM-yyyy");
			String response;
			
			try {
				response = "The difference between dates is: " + dateParser.getDaysBetweenTodayAndDate(readLine) + "days";
			} catch (ParseException e) {
				response = "Wrong date format.";
				e.printStackTrace();
			}
			
			printWriter.println(response);
			printWriter.flush();
			
			started = false;
			clientSocket.close();
		}
		
	}
	
	private boolean started;
	
	public static void main(String[] args) throws IOException {
		System.out.println("MAIN");
		new EchoServer().run();
	}

	private void run() throws IOException {
		this.started = true;
		
		final ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		
		Socket clientSocket;
		while (started) {
			 clientSocket = serverSocket.accept();
			 
			 new ClientHandler(clientSocket).start();
		}

		serverSocket.close();
	}
}
