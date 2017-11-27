package com.test;

import java.util.Arrays;

public class DuplicateStringReplacement {
	public static void main(String[] args) {
		String s = "abra ka dabra";
		char[] chars = s.toCharArray();
		int[] ints = new int[Character.MAX_VALUE];
		
		System.out.println(Arrays.toString(chars));
		for(int loop = 0; loop < chars.length; loop++) {
			ints[chars[loop]]++;
			
			if(ints[chars[loop]] > 1) {
				chars[loop] = '\u0000';
			}
		}
		System.out.println(Arrays.toString(chars));
	}
}
