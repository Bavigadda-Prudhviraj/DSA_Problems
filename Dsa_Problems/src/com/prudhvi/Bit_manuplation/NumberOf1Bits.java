package com.prudhvi.Bit_manuplation;
/*
Write a function that takes an integer and returns the number of 1 bits present in its binary representation.
 */
public class NumberOf1Bits {

	public static void main(String[] args) {
		int num=11;
		int noOfSetbits=countSetBits(num);
		System.out.println(noOfSetbits);
	}
	public static int countSetBits(int num) {
		int setBitsCount=0;
		for(int i=0;i<32;i++) {
			int bitMask=(1<<i);
			if((num&bitMask)>0) {
				setBitsCount++;
			}
		}
		return setBitsCount;
	}

}
