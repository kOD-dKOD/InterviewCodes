package com.algo.problems;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

/**
 * Generates Reverse Polish Notation(RPN) from an algebraic expression
 * @author NM
 *
 */
public class ONP {
	
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int lines = in.nextInt();
		//String onpString =  "(a+(c/d))";
		//String onpString =  "((a+b)*(c/d))";
		//String onpString = "((((a+c)+b)^(c+d))*(a+t))";
		//String onpString = "((a+t)*((b+(a+c))^(c+d)))";
		String onpString = "(((b+(a+c))^(c+d))*(a+t))";
		//String onpString = "(((a+c)^(b+(c+d)))*(a+t))";
		System.out.println(outputRPN(expression));
	}

  /**
  *  Generates RPN of algebraic expression.
  *
  */
	private static String outputRPN(String onpString) {
	
		StringBuffer output = new StringBuffer();
		if(onpString != null && !onpString.trim().isEmpty()) {
			Stack<Character> operatorStack = new Stack<Character>();
			
			for(int index=0; index < onpString.length(); index++) {
				
				switch (onpString.charAt(index)) {
					case '+':
					case '-':
					case '*':
					case '/':
					case '^':
					case '(':
						operatorStack.add(onpString.charAt(index));
						break;
					
					case ')':
						// Pop all elements from stack till first '('
						// and add to output except ')' and '('
						char pop = operatorStack.pop();
						while(pop != '(') {
							output.append(pop);
							pop = operatorStack.pop();
						}
						break;
	
					default:
						output.append(onpString.charAt(index));
						break;
				}
			}
			
			
			while (!operatorStack.isEmpty()) {
				output.append(operatorStack.pop());
			}
			
		}
		
		return output.toString();
	}
}
