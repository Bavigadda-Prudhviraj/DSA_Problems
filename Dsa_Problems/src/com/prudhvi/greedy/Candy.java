package com.prudhvi.greedy;

import java.util.Arrays;

public class Candy {

	public static void main(String[] args) {
		int[] rating = {1,3,4,5,2}; //11
		System.out.println(candy(rating));
		System.out.println(candyOptimized(rating));

	}    
	public static int candy(int[] ratings) {
		int noOfChildrens = ratings.length;
		int[] noOfcandiesToEachChild = new int[noOfChildrens];
		Arrays.fill(noOfcandiesToEachChild, 1);
		for (int i = 0; i < noOfcandiesToEachChild.length; i++) {
			if( i > 0) {
				if(ratings[i] > ratings[i - 1]) {
					noOfcandiesToEachChild[i] = noOfcandiesToEachChild[i - 1] + 1;
					
				}
			}
		}
		
		for(int i = noOfChildrens - 1; i >=0 ; i--) {
			if(i != noOfChildrens - 1 ) {
				if(ratings[i] > ratings[i+1] && noOfcandiesToEachChild[i] <= noOfcandiesToEachChild[i + 1]) {
					noOfcandiesToEachChild[i] = noOfcandiesToEachChild[i+1] + 1;
				}
			}
		}
		
		int totalCadnies = 0;
		for (int i = 0; i < noOfcandiesToEachChild.length; i++) {
			totalCadnies += noOfcandiesToEachChild[i];
		}
        return totalCadnies;
    }
	public static int candyOptimized(int[] ratings) {
		
		int i = 1;
		int len = ratings.length;
		int candies = len;
		while(i < len){
			// when both are equal
			if(ratings[i] == ratings[i-1]) {
				i++;
				continue;
			}

			int peak = 0;
	        while (i < len && ratings[i] > ratings[i - 1]) {
	            peak++;
	            candies += peak;
	            i++;
	        }

			int dip = 0;
			while(i < len && ratings[i] < ratings[i - 1]){
				dip++;
				candies +=  dip;
				i++;
				
			}
			candies -= Math.min(peak, dip);
				
			
		}
		return candies;
    }
	

}
