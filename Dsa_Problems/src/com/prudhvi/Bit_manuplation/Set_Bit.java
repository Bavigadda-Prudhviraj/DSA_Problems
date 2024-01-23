package com.prudhvi.Bit_manuplation;

/*
	You are given two integers A and B.
	Set the A-th bit and B-th bit in 0, and return output in decimal Number System.
		Note:The bit positions are 0-indexed, which means that the least significant bit (LSB) has index 0.
 */
public class Set_Bit {
	public static void main(String[] args) {
		int num=3;
		int bit=5;
		System.out.println(setBit(num, bit));

	}
	
	public static int setBit(int num,int bit) {
		return (1<<num)|(1<<bit);
	}

}
