package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class AdditionUsingLists {
	public static void main(String[] args) {
		List<Integer> l1 = Arrays.asList(9,9,9,9);
		List<Integer> l2 = Arrays.asList(0);
		
		List<Integer> bigList = null;
		
		int difference = l1.size() - l2.size();
		
		Iterator<Integer> i1 = l1.iterator();
		Iterator<Integer> i2 = l2.iterator();
		
		List<Integer> out = null;
		Integer carry = 0;
		
		if(difference < 0) {
			for(int loop = 0; loop < Math.abs(difference); loop++) {
				i2.next();
			}
			bigList = l2;
		}
		
		if(difference > 0) {
			for(int loop = 0; loop < difference; loop++) {
				i1.next();
			}
			bigList = l1;
		}
		
		out = addNumbers(i1, i2);
		carry = out.remove(0);
		
		if(bigList != null) {
			for(int loop = difference - 1; loop >= 0; loop--) {
				Integer sum = bigList.get(loop) + carry;
				
				carry = sum/10;
				Integer value = sum%10;
				out.add(0, value);
			}
		}
		
		if(carry > 0) {
			out.add(0, carry);
		}
		
		System.out.println(l1);
		System.out.println(l2);
		System.out.println(out);
	}

	private static List<Integer> addNumbers(Iterator<Integer> i1, Iterator<Integer> i2) {
		if(!i1.hasNext() || !i2.hasNext()) {
			return null;
		}
		
		List<Integer> carries = new ArrayList<Integer>();
		List<Integer> output = new ArrayList<Integer>();
		
		while(i1.hasNext()) {
			Integer sum = i1.next() + i2.next();
			int carry = sum/10;
			int value = sum%10;
			
			carries.add(carry);
			
			output.add(value);
			int size = output.size();
			
			if(output.size() > 1) {
				Integer prevValue = output.get(size - 2);
				output.set(size - 2, prevValue + carry);
				carries.remove(carries.size() - 1);
			}
		}
		
		output.add(0, carries.remove(0));
		return output;
	}
}
