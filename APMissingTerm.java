package com.algo.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Finds a missing term from an Arithmetic Progression
 * @author NM
 *
 */
 
public class MissingTerm {

public static void main(String[] args) {
	/*System.out.println(findMissingTerm(new int[] { 1, 5, 7, 9, 11 }));
	System.out.println(findMissingTerm(new int[] { 1, 11, 31, 41, 51 }));
	System.out.println(findMissingTerm(new int[] { -10, -6, -4, -2 }));*/
	System.out.println(findMissingNumber(new int[] { 1, 5, 7, 9, 11 }));
	System.out.println(findMissingNumber(new int[] { 1, 11, 31, 41, 51 }));
	System.out.println(findMissingNumber(new int[] { -10, -6, -4, -2 }));
	System.out.println(findMissingNumber(new int[] { -10, -8, -6, -4, -2, 2, 4 }));
	System.out.println(findMissingNumber(new int[] { 4, 0, -2, -4, -6, -8 }));
}

/**
* One Apporoach: by Using HashMap based logic, crude one
*/
private static int findMissingTerm(int[] ap) {
	int missingTerm = 0;

	if (ap != null && ap.length > 0) {
		int difference = 0;
		// A map of diff. value (key) and indices(value) 
		Map<Integer, List<Integer>> diffHolder = new HashMap<Integer, List<Integer>>();
		
		for (int loop = 0; loop < ap.length; loop++) {
			difference = (ap[loop + 1] - ap[loop]);
			List<Integer> countCurr = diffHolder.get(difference);
			if (countCurr == null) {
				countCurr = new ArrayList<Integer>();
				diffHolder.put(difference, countCurr);
			}
			countCurr.add(loop);

			if (diffHolder.size() > 1) {

				for (Integer ki : diffHolder.keySet()) {
					if (ki.equals(difference)) {
						continue;
					}
					int index = 0;
					if (countCurr.size() > diffHolder.get(ki).size()) {
						index = diffHolder.get(ki).get(0);
					}

					if (countCurr.size() < diffHolder.get(ki).size()) {
						index = countCurr.get(0);
						difference = ki;
					}

					missingTerm = ap[index] + difference;
					loop = ap.length + 1;
					break;
				}
			}

		}

	}
	return missingTerm;
}

/**
*  Second Approach: Find difference and check successively by adding
*/
private static int findMissingNumber(int[] ap) {
	int missingNumber = 0;
	
	if(ap != null && ap.length > 0) {
		int last = ap[ap.length - 1];
		int first = ap[0];
		int difference = (last + first)/ap.length;
		// Handles case: if AP is combo of +ve and -ve numbers
		if(first < 0 || last < 0) {
			difference = (last - first)/ap.length;
		}
		
		for(int loop = 0; loop < ap.length; loop++) {
			int next = ap[loop] + difference;
			if(next != ap[loop + 1]) {
				//System.out.println(next);
				missingNumber = next;
				break;
			}
		}
	}
	return missingNumber;
 }
}
