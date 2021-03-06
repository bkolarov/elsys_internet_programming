package com.elsys.url.read;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;

public class Main {

	public static void main(String[] args) throws IOException {
		//readFromURL();
		

	}

	private static void readFromURL() throws IOException {
		final URL url = new URL("http://localhost");
		final InputStream input = url.openStream();
		final InputStreamReader inpustStreamReader = new InputStreamReader(
				input, Charset.forName("UTF-8"));
		
		
		final BufferedReader reader = new BufferedReader(inpustStreamReader);

		String nextLine;

		try {
			while ((nextLine = reader.readLine()) != null) {
				System.out.println(nextLine);
			}
		} finally {
			reader.close();
			input.close();
		}

	}

}
