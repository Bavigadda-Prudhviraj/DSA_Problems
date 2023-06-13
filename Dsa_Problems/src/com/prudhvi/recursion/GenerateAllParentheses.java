package com.prudhvi.recursion;

import java.util.*;

/*
 	Given an integer A pairs of parentheses, write a function to generate all combinations of well-formed parentheses of length 2*A. 
    Problem Constraints:-1 <= A <= 10
    
    input:- A = 3
    output:- [ "((()))", "(()())", "(())()", "()(())", "()()()" ] 
    
    Algorithm goes:-
    				To generate all valid parentheses combinations, you can use a backtracking algorithm. The idea is to build the combinations recursively while keeping track of the count of opening and closing parentheses.
					Here's an approach to solve the problem:
						1.Create an empty list to store the valid combinations of parentheses.
						2.Define a recursive function, let's call it generateParentheses, that takes the following parameters:
							i.current: A string representing the current combination of parentheses.
							ii.open_count: The count of remaining opening parentheses to be placed.
							iii.close_count: The count of remaining closing parentheses to be placed.
						3.If both open_count and close_count are 0, append current to the list of valid combinations and return.
						4.If open_count is greater than 0, call generateParentheses recursively with current + '(', open_count - 1, and close_count.
						5.If close_count is greater than open_count, call generateParentheses recursively with current + ')', open_count, and close_count - 1.
						6.Implement the main function, let's call it generateParenthesis, which initializes the list of valid combinations and calls generateParentheses with an empty string, the given number of opening parentheses, and the same number of closing parentheses.
						7.Return the list of valid combinations
						
			Time complexity:(Total number of recursive calls)*(Time Complexity of one function call except for the recursive part)
								2^n*1=O(2^n)
			Space Complexity: Max size of Stack at any point of execution
							    n=O(n).
 */

public class GenerateAllParentheses {

	public static void main(String[] args) {
		ArrayList<String> arr=new ArrayList<>();
		int input=4;
		generateparentheses(input,0,0,"",arr);
        String[] ansArr=new String[arr.size()];
		for(int i=0;i<arr.size();i++){
            ansArr[i]=arr.get(i);
        }
        System.out.println(Arrays.toString(ansArr));
		
	}
		public static  void generateparentheses(int A,int openingParenthesis,int closingParenthesis,String str,ArrayList<String> arr) {
	    	if(str.length()==A*2) {
	    		arr.add(str);
	    		return;
	        }
	    	//opening parenthesis always should be less then A, if we write ==A, then if n==3,"(((" at this point again it enter into if condition and makes current string as "((((" it is wrong because open always==input;
	        if(openingParenthesis<A) {
	        	generateparentheses(A, openingParenthesis+1, closingParenthesis, str+"(",arr);
	        	
	        }
	        //closing parenthesis always should be less then opening, if we write ==opening parenthesis,")" at this point again it enter into if condition and makes current string as ")(" it is wrong because open always closing parentheses less then opening parentheses;
	        if(closingParenthesis<openingParenthesis) {
	        	generateparentheses(A, openingParenthesis, closingParenthesis+1, str+")",arr);
	        }
	      
	        
	        
	    	
	    }
	    
	


}
