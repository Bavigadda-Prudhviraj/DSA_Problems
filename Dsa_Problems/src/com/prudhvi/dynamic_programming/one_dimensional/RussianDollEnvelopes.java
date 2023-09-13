package com.prudhvi.dynamic_programming.one_dimensional;

import java.util.Arrays;
/*
	Problem Description
		Given a matrix of integers A of size N x 2 describing dimensions of N envelopes, where A[i][0] denotes the height of the ith envelope and A[i][1] denotes the width of the ith envelope.
		One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
		Find the maximum number of envelopes you can put one inside other.
	Problem Constraints
		1 <= N <= 1000
		1 <= A[i][0], A[i][1] <= 109
 */

public class RussianDollEnvelopes {

	public static void main(String[] args) {
		int[][] arr = {{6,18},{2,14},{5,6},{4,15},{8,11},{3,11},{11,10},{5,11}};
		int maxEnvelopes=russianDollEnvelopes(arr);
		System.out.println(maxEnvelopes);
	}

	private static int russianDollEnvelopes(int[][] arr) {
		int n=arr.length;
		//It first checks if there is only one envelope (n == 1) and returns 1.This is because there is only one envelope, and it can be considered the outermost envelope.
		if(n==1){
            return 1;
        }
		//It creates an array of Envelopes objects (envelopes), where each Envelopes object stores the width and height of an envelope from the input array.
		Envelopes[] envelopes=new Envelopes[arr.length];
		for(int i=0;i<n;i++) {
			envelopes[i]=new Envelopes(arr[i][0], arr[i][1]);
		}
		//It sorts the envelopes array based on the width and height of the envelopes. Sorting is necessary to ensure that envelopes with smaller dimensions come before envelopes with larger dimensions. This sorting step is essential for the dynamic programming approach that follows.
		Arrays.sort(envelopes);
		//It initializes an array dp of the same length as the number of envelopes, initially filled with zeros. The dp[i] value represents the maximum number of nested envelopes that can be achieved up to the i-th envelope.
		int[] dp=new int[n];
		dp[0]=1;
		//It initializes maxEnvelopes to -1. This variable will keep track of the maximum number of nested envelopes found.
		int maxEnvelopes=-1;
		//It iterates through the sorted envelopes array, considering each envelope one by one (from left to right).
		for(int i=0;i<n;i++) {
			//For each envelope at index i, it initializes envelopesCount to 0. This variable will keep track of the number of envelopes that can be nested within the current envelope (envelopes[i]).
			int envelopesCount=0;
			//It then iterates through the envelopes before the current envelope (from j = i-1 to j = 0).
			for (int j = i-1; j>=0 ; j--) {
				//For each previous envelope at index j, it checks if the current envelope (envelopes[i]) can be nested within the previous envelope (envelopes[j]) based on the width and height conditions. If it can be nested, 
				if(envelopes[i].height>envelopes[j].height && envelopes[i].width>envelopes[j].width) {
					//it updates envelopesCount to the maximum of its current value and dp[j], which represents the maximum number of nested envelopes achievable up to envelope j.
					envelopesCount=Math.max(envelopesCount, dp[j]);
				}
			}
			//It sets dp[i] to envelopesCount + 1, representing the maximum number of nested envelopes achievable up to the current envelope (envelopes[i]).
			dp[i]=envelopesCount+1;
			//It updates maxEnvelopes to the maximum of its current value and dp[i], ensuring that it always contains the maximum number of nested envelopes found so far.
			maxEnvelopes=Math.max(maxEnvelopes,dp[i]);
			
		}
		//After processing all envelopes, it returns maxEnvelopes as the maximum number of Russian Doll Envelopes that can be nested within each other.
		return maxEnvelopes;
	}
	

}

class Envelopes implements Comparable<Envelopes> {
    int height;
    int width;

    public Envelopes(int height, int width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public int compareTo(Envelopes other) {
    	if(this.height==other.height) {
    		return this.width-other.width;
    	}else {
    		return this.height - other.height;
    	}
        
    }

	@Override
	public String toString() {
		return "Envelopes [height=" + height + ", width=" + width + "]";
	}
    
}
