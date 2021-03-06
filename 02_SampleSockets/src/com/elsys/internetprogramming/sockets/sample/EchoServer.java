package com.elsys.internetprogramming.sockets.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {
	private static final int SERVER_PORT = 44012;
	private boolean started;

	private class ClientHandler extends Thread {
		private final Socket clientSocket;
		
		public ClientHandler(Socket clientSocket) {
			this.clientSocket = clientSocket;
		}
		
		@Override
		public void run() {
			try {
				handleClient(clientSocket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		private void handleClient(final Socket clientSocket) throws IOException {
			final InputStream inputStream = clientSocket.getInputStream();
			final OutputStream outputStream = clientSocket.getOutputStream();

			final InputStreamReader inputStreamReader = new InputStreamReader(
					inputStream);
			final BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
			
			final PrintWriter out = new PrintWriter(outputStream);
			
			final String readLine = bufferedReader.readLine();
			
			out.println(readLine);
			
			if ("END".equals(readLine)) {
				started = false;
			}
			
			out.flush();		
			clientSocket.close();
		}
	}
	
	public EchoServer() {

	}

	public void doTheMagic() throws IOException {
		final ServerSocket serverSocket = new ServerSocket(SERVER_PORT);

		started = true;
		while(started ) {
			final Socket clientSocket = serverSocket.accept();
			new ClientHandler(clientSocket).start();
		}
		
		serverSocket.close();
	}

	
}
