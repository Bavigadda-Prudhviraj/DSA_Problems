package com.prudhvi.recursion;

public class Combinations {

	public static void main(String[] args) {
		int A=4;
		int B=2;
		combine(A, B);

	}
	public static void combine(int A, int B) {
		//It checks if the value of A is equal to 1. If A is 1, it returns, which serves as the base case of the recursion.
		if(A==1 ) {
			return;
		}
		//If A is not equal to 1, it makes a recursive call to combine with A-1 and B+1 as the new parameters. This effectively decrements A by 1 and increments B by 1 in each recursive call.
		combine(A-1,B+1);
		//After the recursive call, it prints the values of A and B using System.out.println.
		System.out.println("A: "+A+" b: "+B);	
    }

}
