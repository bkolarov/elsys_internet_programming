package com.elsys.internetprogramming.homework01;

public class SampleClass {
	private String str;
	private int num;
	
	public SampleClass(String str, int num) {
		this.str = str;
		this.num = num;
	}
	
	public void makeALoop() {
		System.out.println("\nSampleClass\n\n");
		System.out.println("Line 14: Loop..");
		
		for (int count = 0; count < this.num; ++count) {
			System.out.println("Iteration: " + count);
		}
	}
	
	public String getString() {
		return this.str;
	}
}
