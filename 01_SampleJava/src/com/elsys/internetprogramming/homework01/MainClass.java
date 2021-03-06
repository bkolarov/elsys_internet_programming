package com.elsys.internetprogramming.homework01;

public class MainClass {
	private static SampleClass instanceSampleClass;
	private static SampleClass instance2SampleClass;

	public static void main(String[] args) {
		init();
		compare(instanceSampleClass, instance2SampleClass);
		
		instance2SampleClass = instanceSampleClass;
		compare(instanceSampleClass, instance2SampleClass);
		
		compareStringObjects(instanceSampleClass.getString(), instance2SampleClass.getString());
		instance2SampleClass.makeALoop();
		
	}

	private static void compare(SampleClass obj1, SampleClass obj2) {
		System.out.println("Line 20: Comparing objects by refference");
		
		if (instanceSampleClass.equals(instance2SampleClass)) {
			System.out.println("Line 23: The two objects have same references.");
		} else {
			System.out.println("Line 25: The two objects have different refferences.");
		}

	}

	private static void init() {
		instanceSampleClass = new SampleClass("instance1", 5);
		instance2SampleClass = new SampleClass("instance2", 10);
	}
	
	private static void compareStringObjects(String str1, String str2) {
		System.out.println("Line 36: Comparing String objects. They are compared by their value");
		
		System.out.println("Line 38: String1 = " + str1 + "\nString2 = " + str2);
		
		if (str1.equals(str2)) {
			System.out.println("Line 41: Strings are equal.");
		} else {
			System.out.println("Line 43: Strings are not equal.");
		}
	}

}
