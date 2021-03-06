package com.elsys.internetprogramming.sockets.echo.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class Server {
	public final static int SERVER_PORT = 44012;
	private static boolean accept = true;
	
	public static void main(String[] args) throws IOException {
		final ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
		
		while (accept) {
			final Socket clientSocket = serverSocket.accept();
			
			new Thread(new Runnable() {
				
				@Override
				public void run() {
					try {
						handleClient(clientSocket);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
		
		serverSocket.close();
	}

	private static void handleClient(Socket clientSocket) throws IOException {
		final InputStream inputStream = clientSocket.getInputStream();
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
		
		final OutputStream outputStream = clientSocket.getOutputStream();
		
		final PrintWriter printWriter = new PrintWriter(outputStream);
		
		while (true) {
			String message;
			
			try {
				message = bufferedReader.readLine();
			} catch (SocketException e) {
				message = "END";
			}
			
			if (message.equals("END")) {
				clientSocket.close();
			}
			
			printWriter.println(message);
			printWriter.flush();
		}
	}
}
