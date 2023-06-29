package com.prudhvi.binarysearch;

public class PaintersPartition {
	/*
	Given 2 integers A and B and an array of integers C of size N. Element C[i] represents the length of ith board.
	You have to paint all N boards [C0, C1, C2, C3 … CN-1]. There are A painters available and each of them takes B units of time to paint 1 unit of the board.

	Calculate and return the minimum time required to paint all boards under the constraints that any painter will only paint contiguous sections of the board.
	NOTE:
		1. 2 painters cannot share a board to paint. That is to say, a board cannot be painted partially by one painter, and partially by another.
		2. A painter will only paint contiguous boards. This means a configuration where painter 1 paints boards 1 and 3 but not 2 is invalid.

	Return the ans % 10000003.

	Problem Constraints
		1 <= A <= 1000
		1 <= B <= 106
		1 <= N <= 105
		1 <= C[i] <= 106
	 */

	public static void main(String[] args) {
		int A=4;
		int B=10;
		int[] C= {884,228,442,889}; //8890
		int ans=paint(A, B, C);
		System.out.println(ans);

	}
	/*
	 the paint method implements the painter's partition problem using binary search to find the minimum time required to paint all the boards, considering the given number of painters. 
	 It uses the minTimeToPaint function to check the feasibility of a particular time allocation (mid) and adjusts the search range (low and high) accordingly.
	 The result is returned as (int) (ans % mod), providing the minimum time required with modulo mod.
	 */
	public static int paint(int painters, int time, int[] C) {
		/*
		1.The paint method takes in the number of painters (painters), the time taken by each painter (time), and an array of board lengths (C).
		2.mod is set as 10000003, likely to be used as a modulo value in the end of the code.
		3.low is initialized as the minimum possible value for a long variable.
		4.high is initialized as 0.
		5.ans is initialized as 0.
		*/
        int mod=10000003;
        /*
          The  aims to identify the maximum element in an array when multiple painters are involved. 
          In this scenario, the maximum element represents the length of the longest sub array, and the minimum time required corresponds to the duration it takes to paint that particular sub array. 
          By determining this maximum element, 
          we can efficiently allocate the necessary time for painting and optimize the overall process for multiple painters
         */
        long low=Integer.MIN_VALUE; 
         /* The objective is to calculate the total time taken by a single remaining painter, which is equivalent to the sum of all the elements in the given array. 
            In this scenario, with only one painter remaining, their task is to paint the entire array, and the total time required is determined by adding up the lengths of all the elements. 
            By computing this sum, we can accurately determine the time needed for the sole painter to complete the painting job, taking into account the combined length of all the array elements.
        */
        long high=0;
        long ans=0;
		//The for loop iterates over the elements of the C array to calculate the values for low and high. It multiplies each element by the time and checks if it's greater than the current low value. If so, it updates low to this new maximum value. Additionally, it adds the product to high to keep a running sum of the multiplied values.
        for(int i=0;i<C.length;i++){
            if(C[i]*(long)time>low){
                low=(long)C[i]*time;
            }
            high+=(long)C[i]*time;
        }
        /*
        1.The code enters a while loop as long as low is less than or equal to high. This signifies a binary search iteration.
        2.mid is calculated as the average of low and high using low + ((high - low) / 2).
        3.The if statement calls the minTimeToPaint function, passing mid, painters, C, and time as arguments. If the function returns true, it means that it is possible to paint all the boards within the allocated time mid, so it updates ans to the current mid value and adjusts high to continue searching for a lower mid value. Otherwise, it adjusts low to search for a higher mid value.
        4.The while loop continues until low becomes greater than high, indicating the binary search has found the desired ans value.
        5.Finally, it returns (int) (ans % mod) as the result, which is the modulo of ans with mod.
        
        */while(low<=high){
            long mid=low+((high-low)/2);
            if(minTimeToPaint(mid,painters,C,time)){
                ans=mid;
                high=mid-1;
            }
            else{
                low=mid+1;
            }
        }
        return (int)(ans%mod);
    }
	/*
	  the goal is to divide a given array of boards into a minimum number of sub arrays, such that each sub array can be painted by a single painter. 
	  The function minTimeToPaint employs a binary search algorithm to efficiently find the minimum time required to paint all the boards while considering the specified number of painters.
	 
	1.The minTimeToPaint method takes in mid (the current time to check), painter (the number of painters), arr (the array of board lengths), and time (the time taken by each painter).
	2.painterscnt is initialized as 1, representing the current number of painters being considered.
	3.timeCnt is initialized as 0, representing the current total time taken by.
	4.The for loop iterates over the elements of arr to calculate the timeCnt by multiplying each element by time and adding it to the running sum.
	5.If timeCnt exceeds mid, it means that the current painter cannot handle the additional board length without exceeding the allocated time. 
	  In this case, timeCnt is reset to the length of the current board multiplied by time, painterscnt is incremented, and if the incremented painterscnt is greater than painter (the maximum number of painters), the function returns false.
	6.If the for loop completes without encountering a condition where false is returned, it means that the given mid value is feasible for completing the painting task with the specified number of painters, so the function returns true.
	 */
	public static boolean minTimeToPaint(long mid,int painter,int[] arr,long time){
        int painterscnt=1;
        long timeCnt=0;
        for(int i=0;i<arr.length;i++){
            timeCnt+=arr[i]*time;
            
            if(timeCnt>mid){
                timeCnt=arr[i]*time;
                painterscnt++;
                if(painterscnt>painter){
                    return false;
                }
            }
        }
        return true;
    }


}
