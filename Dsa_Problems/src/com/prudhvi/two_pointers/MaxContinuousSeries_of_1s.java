package com.prudhvi.two_pointers;

import java.util.Arrays;

public class MaxContinuousSeries_of_1s {

	public static void main(String[] args) {
		int[] arr= {1, 1, 0, 1, 1, 0, 0, 1, 1, 1};
		int noOfZserosCanFlip=1;
		//Longest sub Array indexs when n 0 are flipped into 1;
		int[] indexOfLongestSubArrayWhenNzerosAreFlipped=maxone(arr, noOfZserosCanFlip);
		System.out.println(Arrays.toString(indexOfLongestSubArrayWhenNzerosAreFlipped));

	}
	public static int[] maxone(int[] A, int B) {
	    int n = A.length;
	    int s = 0, e = 0;
	    int ans_s = 0, ans_e = 0;
	    int flip = 0;
	    while(e < n){
	        // caterpillar stretch
	        while(e < n && flip <= B){ // end not reach & food still avail
	        if(A[e] == 0) flip++; // eat food or acquire
	        if( flip <= B && (e-s) > (ans_e - ans_s) ){
	            // store max stretch
	            ans_e = e;
	            ans_s = s;
	            }
	        e++; // head stretching
	        }
	        // caterpillar shrink
	        while(s < e && flip > B){ // tail not got stick to head & over eaten
	            if(A[s] == 0) flip--; // poop or release
	            s++; // tail shrinking
	        }
	    }
	    int[] ans = new int[ans_e - ans_s + 1];
	    for(int i = 0; i < ans.length; i++){
	        ans[i] = ans_s + i;
	    }
	    return ans;
	    }
}
