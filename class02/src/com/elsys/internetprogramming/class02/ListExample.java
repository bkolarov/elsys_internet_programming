package com.elsys.internetprogramming.class02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListExample {
	public void list() {
		final List<String> list = new ArrayList<String>();
		
		list.add("First element");
		list.add("Second element");
		
		for (String next : list) {
			System.out.println(next);
		}
		
	}
	
	public void mapExample() {
		final Map<String, Integer> map = new HashMap<String, Integer>();
		
		map.put("Bulgaria", 6);
		map.put("Germany", 80);
		
		System.out.println(map.get("Bulgaria"));
		System.out.println(map.get("Germany"));
	}
}