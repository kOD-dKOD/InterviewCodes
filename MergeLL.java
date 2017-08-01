package com.example;

import java.util.Collections;
import java.util.LinkedList;

public class MergeLL {
	public static void main(String[] args) {
		LinkedList<Integer> l1 = new LinkedList<Integer>();
		/*l1.add(1);
		l1.add(3);
		l1.add(5);
		l1.add(7);
		l1.add(9);*/
		l1.add(7);
		l1.add(8);
		l1.add(9);
		l1.add(6);
		l1.add(16);
		
		//19 10 14 11 12 20 15 17
		LinkedList<Integer> l2 = new LinkedList<Integer>();
		/*l2.add(2);
		l2.add(4);
		l2.add(6);
		l2.add(8);*/
		//l2.add(19);
		l2.add(10);
		/*l2.add(14);
		l2.add(11);
		l2.add(12);
		l2.add(20);
		l2.add(15);
		l2.add(17);*/
		
		Collections.sort(l1);
		Collections.sort(l2);
		mergeLists(l1, l2);
		
	}

	private static void mergeLists(LinkedList<Integer> l1, LinkedList<Integer> l2) {
		Integer element2 = l2.poll();
		
		System.out.println("L1: " + l1.toString());
		System.out.println("L2: " + l2.toString());
		for(int loop = 0; loop < l1.size(); loop++) {
			Integer element1 = l1.get(loop);
			
			if(element2 != null && element1 != null && element2 < element1) {
				l1.add(loop, element2);
				element2 = l2.poll();
			}
			/*if(element2 != null && element1 != null && element2 < element1) {
				l1.add(index + 1, element2);
				element2 = l2.poll();
			} else if (element2 != null && element1 != null && element2 > element1) {
				index = loop;
			}*/
		}
		
		/*if(l2.size() == 0) {
			l1.add(element2);
			element2 = null;
		}*/
		
		while(element2 != null) {
			l1.add(element2);
			element2 = l2.poll();
		}
		
		System.out.println("L1: " + l1.toString());
		System.out.println("L2: " + l2.toString());
		
	}
}
