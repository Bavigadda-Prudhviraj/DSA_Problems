package com.prudhvi.strings.knuth_morris_pratt_KMP_algo;

import java.util.Arrays;

public class Kmp_Algo_NPS_Array {

	public static void main(String[] args) {
		String str="ababdabacdababcabab";//ABABDABACDABABCABAB
		int[] lpsArray=lPS_array_construction(str);
		System.out.println(Arrays.toString(lpsArray));

	}

	private static int[] lPS_array_construction(String str) {
		int[] LPS_arr=new int[str.length()];
		for(int i=1;i<str.length();i++) {
			int index=LPS_arr[i-1]; // i-1 it doesn't work
			while (str.charAt(i)!=str.charAt(index)){
				if(index<=0) {
					index=-1;
					break;
				}
				 index=LPS_arr[index-1];
			}
			LPS_arr[i]=index+1;
		}
		return LPS_arr;
	}

}
  