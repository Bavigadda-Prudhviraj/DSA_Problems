package com.prudhvi.hashing;

import java.util.ArrayList;
import java.util.HashSet;

public class ColorfulNumber {
	/*
	Given a number A, find if it is COLORFUL number or not.
	If number A is a COLORFUL number return 1 else, return 0.
	What is a COLORFUL Number:
		A number can be broken into different consecutive sequence of digits. 
		The number 3245 can be broken into sequences like 3, 2, 4, 5, 32, 24, 45, 324, 245 and 3245. 
		This number is a COLORFUL number, since the product of every consecutive sequence of digits is different
	Problem Constraints
		1 <= A <= 2 * 109
	Example:
		A = 123
			1 2 3 12 23 123
			1 -> 1
			2 -> 2
			3 -> 3
			12 -> 2  we have already encountered 2 before. Return 0
	 */

	public static void main(String[] args) {
		int num=23;
		int ans=checkProductIsRepeatingOrNot(num);
		System.out.println(ans);

	}
	/*
	the checkProductIsRepeatingOrNot function takes an integer as input, extracts its digits, and stores them in same order in an ArrayList. 
	Then, it calculates the product of all possible subsequences of digits and checks if any product is repeating. 
	If it finds a repeating product, it returns 0; otherwise, it returns 1.
	*/
	public static int checkProductIsRepeatingOrNot(int num) {
		//numbersArrayList to store the digits of the number in reverse order. 
		//This ArrayList will help us calculate the products of all possible subsequences of digits later.
		ArrayList<Integer> numbersArrayList=new ArrayList<>();
		int number=num;
		//The while loop extracts the digits from the number one by one from the last digit to the first digit. 
		//It iterates until the number becomes zero.
		while (number!=0) {
			int lastDigit=number%10;// the lastDigit variable is used to store the last digit of the current number.
			//numbersArrayList already contains the lastDigit. If it does, it means the digit is repeating, and the function returns 0.
			if(numbersArrayList.contains(lastDigit)) {
				return 0;
			}
			//If the digit is not already in the numbersArrayList, it is added to the front of the list to reverse the order of digits.
			else {
				numbersArrayList.add(0,lastDigit);//why o means to store the elements same as number if we just use add it will add at the end again we have to reverse the array so it is best to add at 0th index to maintain the number digits order
			}
			number=number/10;
		} 
		//This set will be used to keep track of the products of all possible subsequences of digits.
		HashSet<Long> hashSet=new HashSet<>();
		// iterates through all possible starting points of the subsequence (from the first digit to the last digit
		for(int i=0;i<numbersArrayList.size();i++) {
			//product is initialized to 1, which will be used to calculate the product of the digits in the current subsequence.
			long product=1;
			//iterates through all possible ending points of the subsequence, starting from the current starting point.
			for(int j=i;j<numbersArrayList.size();j++) {
				//the product is multiplied by the current digit in the numbersArrayList to include it in the subsequence product.
				product=product*numbersArrayList.get(j);
				//condition checks if the hashSet does not contain the product. If it doesn't, 
				//it means this is a new product and is added to the set
				if(!hashSet.contains(product)) {
					hashSet.add(product);
				}
				// condition checks if the hashSet already contains the product. If it does, it means the product is repeating, and the function returns 0.
				else if(hashSet.contains(product)) {
					return 0;
				}
			}
		}
		//If the function reaches this point without returning 0, 
		//it means no repeating product was found, and the function returns 1.
		return 1;
	}

}
