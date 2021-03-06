package com.elsys.homework.internetprogramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Main {
	static Map<String, Integer> countries;
	
	public static void main(String[] args) throws IOException {
		countries = new HashMap<String, Integer>();
		
		fillCountries();
		
		System.out.println("Countries with population over 10 million:");
		printCountries(10000000);
		
		System.out.println("All countries in hash: ");
		printCountries(0);
		
		countries.clear();
		
		System.out.println("===========================================");
		System.out.println("PrintEnteredTextWithCapitalLetters:");
		printEnteredTextWithCapitalLetters();
	}

	private static void printEnteredTextWithCapitalLetters() throws IOException {
		System.out.println(getLine().toUpperCase());
		
	}

	private static String getLine() throws IOException {
		InputStream input = System.in;
		final InputStreamReader inputStreamReader = new InputStreamReader(input);
		final BufferedReader reader = new BufferedReader(inputStreamReader);
		
		final String line = reader.readLine();
		
		return line;
	}

	private static void printCountries(int population) {
		if (population > 0) {
			for (Entry<String, Integer> key : countries.entrySet()) {
				if (key.getValue() > population) {
					System.out.println(key.getKey());
				}
			}
		} else {
			for (Entry<String, Integer> key : countries.entrySet()) {
				System.out.println(key.getKey());
			}
		}
	}

	private static void fillCountries() {
		countries.put("Russia", 143455000);
		countries.put("Germany", 80586000);
		countries.put("Bulgaria", 7261000);
		countries.put("Portugal", 10609000);
		countries.put("Belarus", 9460000);
		countries.put("Finland", 5436000);
	}

}
