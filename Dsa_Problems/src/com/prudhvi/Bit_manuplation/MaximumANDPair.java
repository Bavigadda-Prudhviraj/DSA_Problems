package com.prudhvi.Bit_manuplation;

public class MaximumANDPair {

	public static void main(String[] args) {
		int[] arr= {53, 39, 88};
		int answer=maximumAndPair(arr);
		System.out.println(answer);

	}
	public static int maximumAndPair(int[] arr) {
		int answer=0;
		for(int j=31;j>=0;j--) {
			int setBitCount=0;
			for(int i=0;i<arr.length;i++) {
				if(checkBit(arr[i],j)) {
					setBitCount++;
				}
			}
			if(setBitCount>=2) {
				answer=answer+(1<<j);
				for(int i=0;i<arr.length;i++) {
					if(!checkBit(arr[i],j)) {
						arr[i]=0;
					}
				}
			}
		}
		return answer;
	}
	
	public static boolean checkBit(int num,int bit){	
		int maskBit=(1<<bit);
		if((maskBit&num)==0) {
			return false;
		}
		return true;
		
	}

}
