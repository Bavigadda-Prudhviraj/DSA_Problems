package com.prudhvi.Bit_manuplation;

import java.util.Arrays;

public class CountingBits {

	public static void main(String[] args) {
		int num=5;
		int[] bitsArr=countBits(num);
		System.out.println(Arrays.toString(bitsArr));

	}
	public static int[] countBits(int n) {
        int[] bits=new int[n+1];
        for(int i=0;i<=n;i++){
            int setBitsCount=countSetBits(i);
            bits[i]=setBitsCount;
        }
        return bits;
    }
	private static int countSetBits(int num) {
		int setBits=0;
		for(int i=31;i>=0;i--) {
			int bitMask=(1<<i);
			if((num&bitMask)>=1){
                setBits++;
            }
		}
		return setBits;
	}

}
