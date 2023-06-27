package com.prudhvi.binarysearch;

public class SquareRootofInteger {
	/*
	 Given an integer A. Compute and return the square root of A.
	If A is not a perfect square, return floor(sqrt(A)).
	The value of A can cross the range of Integer.
	NOTE: 
   		Do not use the sqrt function from the standard library. 
   		Users are expected to solve this in O(log(A)) time.
   	
   	Hint 1:
   		Think about the answer of this "Is a particular number r less than or equal to floor(sqrt(x))?" 
		The answer to the above problem as a function of r will look like [1,1,......1,0,0......0]. 
	Approach:
		Think in terms of binary search.
		Let us say S is the answer. We know that 0 <= S <= A.
		Consider any random number r.
			1.If r*r <= A, S >= r , i.e. S would lie towards right of r
			2.If r*r > A, S < r, i.e. S would lie towards left of r
		Run a Binary Search Algorithm, by using the above Fact.
		1.Set the lower bound low as 0 and the upper bound high as A.
		2.While low <= high, perform the following steps:
			a. Calculate the midpoint mid as the average of low and high.
			b. Compare mid squared with A:
				i.If mid * mid is equal to A, return mid as the square root.
				ii.If mid * mid is less than A, update low to mid + 1.
				iii.If mid * mid is greater than A, update high to mid - 1.

		After the binary search ends, return the value of high as the floor value of the square root of A.
		Time Complexity : O(log(A))
	 */

	public static void main(String[] args) {
		int num=2147483647;
		int ans=sqrt(num);
		System.out.println(ans);

	}
	public static int sqrt(int A) {
        int low=1;
        int high=A;
        long ans=-1; // Use long to prevent integer overflow
        if((A==0)||(A==1)){
             return A;
         }
        while(low<=high){
            int mid=low+((high-low)/2);
            //return mid;
            System.out.println("mid: "+mid);
            if(mid==A/mid){///  mid*mid==A
                return mid;
            }
            else if(mid>A/mid){//   mid*mid>A
                high=mid-1;
            }
            else if(mid<A/mid){//   mid*mid<A
                ans=mid;// Keep track of the potential result
                low=mid+1;
            }
           
        }
        return (int)ans;// Return the floor value of the square root
    }
	//another
	public static int sqrt2(int A) {
	      int low = 1, high = A, root = 0;
	      while (low <= high) {
	         int mid = (low + high) / 2;
	         if (mid == A / mid && (A % mid == 0))
	            return mid;
	         if (mid <= A / mid) {
	            root = mid;
	            low = mid + 1;
	         } else {
	            high = mid - 1;
	         }
	      }
	      return root;
	   }
	
	

}
