package com.prudhvi.Arrays.one_dimentsional_arrays;



/*
Note:- space complexity is 0(l)
	   Time complexity is O(n)
*/
public class TrappingRainWaterWithLessSC {

	public static void main(String[] args) {
		int[] arr= {0, 1, 0, 2};
		int totalVolumeOfWater=volumeWithLessSc(arr);
		System.out.println(totalVolumeOfWater);
		int[] arr1= {4, 2, 0, 3,2,5};
		int totalVolumeOfWater1=volumeWithLessSc(arr1);
		System.out.println(totalVolumeOfWater1);

	}

	private static int volumeWithLessSc(int[] arr) {
		int l=0; //left pointer
		int r=arr.length-1; //Right pointer
		int lbh=arr[0];// left highest length( element )
		int rbh=arr[arr.length-1]; //right highest length ( element)
		int ans=0; // Initially answer will be zero
		while(l<=r) { //condition to stop the loop with out crossing l and r pointers
			if(lbh<=rbh) {//it will take care of left side,note:-here water will stored when 
				if(arr[l]>=lbh) {// water will be stored when the present element should be lesser then the lbh 
					lbh=arr[l]; // if not make this element as lbh
				}
				else {
					ans=ans+lbh-arr[l]; //if present element is lesser then the lbh then water can be stored
				}
				l++;
			}
			else { //it will take care of the right side,
				if(arr[r]>=rbh) {// water will be stored when the present element should be lesser then the rhb
					rbh=arr[r]; // if not make this element as lbh
				}
				else {
					ans=ans+rbh-arr[r]; //if present element is lesser then the lbh then water can be stored
				}
				r--;
			}
			
		}
		return ans;
	}
	

}
