package com.example;

public class AmbiguousMethod {
	public static void main(String[] args) {
		AmbiguousMethod ambiguousMethod = new AmbiguousMethod();
		ambiguousMethod.call(null);
	}
	
	public void call(String i) {
		
	}

	public void call(Double i) {
		
	}
	
	public void call(Object i) {
			
		}
	
	public void call(Integer i) {
		
	}
	
	public void call(int ... i) {
		
	}
}
