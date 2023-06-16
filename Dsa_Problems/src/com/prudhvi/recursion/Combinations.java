package com.prudhvi.recursion;

public class Combinations {

	public static void main(String[] args) {
		int A=4;
		int B=2;
		combine(A, B);

	}
	public static void combine(int A, int B) {
		
		
		if(A==1 ) {
			return;
		}
		combine(A-1,B+1);
		System.out.println("A: "+A+" b: "+B);	
		
		
		
    }

}
