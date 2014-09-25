package com.elsys.internetprogramming.class02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class StreamExample {
	public void stream() throws IOException {
		InputStream input = System.in;
		
		final int bytes = input.read();
		System.out.println(Character.toString((char) bytes) + " has ASCII " + bytes);
		
		final byte[] a = new byte[3];
		
		input.read(a);
		System.out.println(new String(a));
		System.out.println("here");
	}
	
	public void bufferedReaderExample() throws IOException {
		final InputStream input = System.in;
		final InputStreamReader inputStreamReader = new InputStreamReader(input);
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		
		final String line = reader.readLine();
		countSymbols(line, 'a');
	}

	private void countSymbols(String line, char c) {
		int result = 0;
		
		for (int i = 0; i < line.length(); i++) {
			final char next = line.charAt(i);
			
			if (next == c) {
				result++;
			}
		}
		

		System.out.println(result);
		System.out.println("char count = " + line.length());
		
	}
}
