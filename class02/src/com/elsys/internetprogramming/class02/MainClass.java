package com.elsys.internetprogramming.class02;

import java.io.IOException;

public class MainClass {
	private static CollectionsExample collection;
	private static ListExample list;
	private static StreamExample stream;
	
	public static void main(String[] args) throws IOException {
		//collection = new CollectionsExample();
		
		//collection.array();
		
		//list = new ListExample();
		
		//list.list();
		//list.mapExample();
		
		stream = new StreamExample();
		//stream.stream();
		stream.bufferedReaderExample();
	}	
}
