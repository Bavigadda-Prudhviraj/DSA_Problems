package com.prudhvi.two_pointers;
//https://leetcode.com/problems/trapping-rain-water/description/
public class Trapping_Rain_Water_TotalVolumeOfWater {

	public static void main(String[] args) {
		int[] heights= {0,1,0,2,1,0,1,3,2,1,2,1};
		int totalValum1=traps(heights);
		int totalVolume2=trap(heights);
		System.out.print(totalValum1+" "+totalVolume2);

	}
	public static int traps(int[] height) {
        int l=0; //left pointer
		int r=height.length-1; //Right pointer
		int lbh=height[0];// left highest length( element )
		int rbh=height[height.length-1]; //right highest length ( element)
		int ans=0; // Initially answer will be zero
		while(l<=r) { //condition to stop the loop with out crossing l and r pointers
			if(lbh<=rbh) {//it will take care of left side,note:-here water will stored when 
				if(height[l]>=lbh) {// water will be stored when the present element should be lesser then the lbh 
					lbh=height[l]; // if not make this element as lbh
				}
				else {
					ans=ans+lbh-height[l]; //if present element is lesser then the lbh then water can be stored
				}
				l++;
			}
			else { //it will take care of the right side,
				if(height[r]>=rbh) {// water will be stored when the present element should be lesser then the rhb
					rbh=height[r]; // if not make this element as lbh
				}
				else {
					ans=ans+rbh-height[r]; //if present element is lesser then the lbh then water can be stored
				}
				r--;
			}
			
		}
		return ans;
        
    }
	public static int trap(int[] height) {
        //first we need to construct the prefix Array for left height calculation
		int[] lb=new int[height.length];
		//for left highest 0th element is the left highest element
		lb[0]=height[0];
		for(int i=1;i<height.length;i++) {
			lb[i]=Math.max(lb[i-1], height[i]);
		}
		//second we need to construct the right highest of current element prefix Array
		int[] rb=new int[height.length];
		//last element is the right highest element 
		rb[rb.length-1]=height[height.length-1];
		for(int i=height.length-2;i>=0;i--) {
			rb[i]=Math.max(rb[i+1],height[i]);
		}
		int totalVolume=0;
		for(int i=1;i<height.length-1;i++) {
			int len=Math.min(lb[i], rb[i]);
			totalVolume=totalVolume+(len-height[i]);
		}
		return totalVolume;
        
    }

}
