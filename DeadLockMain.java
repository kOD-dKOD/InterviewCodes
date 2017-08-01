package com.example;

public class DeadLockMain {
		
	public static void main(String[] args) {
		try {
			System.out.println("Waiting till main thread finishes");
			Thread.currentThread().join(0);
			System.out.println("Main thread finished");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
