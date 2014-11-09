package org.elsys.internetprogramming.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.ParseException;

public class Client {
	private static String response;
	
	public static void main(String[] args) throws UnknownHostException, IOException, ParseException {
		response = readFromSocket();
		//System.out.println(response);
		
		//final DateParser dateParser = new DateParser("dd MMM yyyy HH:mm:ss zzzz");
		//dateParser.parse(response);
		//System.out.println(dateParser.parse(response).getTime()/1000.0/60/60);
		
		String[] time = response.split(" ");
		time = time[3].split(":");
		String result = time[0] + ":" + time[1];
		System.out.println(result);
		
	}

	private static String readFromSocket() throws UnknownHostException, IOException {
		final Socket socket = new Socket("172.16.18.89", 13);
		
		final InputStream inputStream = socket.getInputStream();
		final InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
		final BufferedReader bufferedreader = new BufferedReader(inputStreamReader);
		
		final String response = bufferedreader.readLine();
		socket.close();
		return response;
	}

}
