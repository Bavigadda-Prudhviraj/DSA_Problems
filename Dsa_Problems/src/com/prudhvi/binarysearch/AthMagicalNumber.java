package com.prudhvi.binarysearch;

import com.prudhvi.Arrays.gcd_hcf.GCD;

public class AthMagicalNumber {
	/*
	You are given three positive integers, A, B, and C.
	Any positive integer is magical if divisible by either B or C.
	Return the Ath smallest magical number. Since the answer may be very large, return modulo 109 + 7.
	Note: Ensure to prevent integer overflow while calculating
	
	Hint 1:
		Try to think by reading the below property:
		Numbers divisible by B and C in a range of [1,M] is given by M/B + M/C - M/lcm(B,C)
		The number of magical numbers less than or equal to x is a monotone increasing function in x
		
	Approach:
		 L = lcm(B, C), the least common multiple of B and C, and let f(x) be the number of magical numbers less than or equal to x.

		A well known result says that L = (B*C)/gcd(B,C), and that we can calculate the function gcd.
		Then f(x) = x/B + x/C - x/L (floor of all the divisions)

		Why?
			i.There are x/B numbers B, 2B, 3B.... that are divisible by B,
			ii.There are x/C numbers C, 2C, 3C.... that are divisible by C,
			iii.We need to subtract the x/L numbers divisible by B and C that we double-counted.
		Finally,the answer must be between 0 and A * min(B,C).
	If x increases f(x) increases, we can use Binary Search on x to find the Ath number.

	Algorithm:
		1.low=1 and high = A * min(B,C)
		2.while low <= high
			i.Find mid = (low + high)/2
			ii.Find f(mid) let it be count
			iii.If count>=A then mark it as a answer and try to find smaller number which implies high = mid-1
			iv.Else low = mid+1

	Time Complexity: O(log (A * min(B, C))) Space Complexity: O(1).
	 */

	public static void main(String[] args) {
		int A = 807414236;
		int B = 3788;
		int C = 38141;
		//for brute force give low input or else it will take more time 
		int a = 3;
		int b = 3;
		int c = 5;
		int ans=magicalNumber(A,B,C);
		System.out.println(ans);
		int ans1= bruteForceMagicalNum(a,b,c);
		System.out.println("brute Forece "+ans1);

	}
	public static int magicalNumber(int a,int b,int c) {
		long ans=0;
		//why we are taking low as minimum(b,c) because below minimum number we can't find the divisible of b or c 
		long low=Math.min(b,c);
		/*
		why high is minimum of (b,c)* a
		for suppose there is no c divisible are there even up to minimum* a divisible we can take
		example a=3,b=5,c=3 minimum(5,3)*3=9
			b=3,6,9,12,15,18,21,24,27 suppose c number no consider even though we got ath magical number
			c=5,10,15,20,25,30,35,40,45, 
		*/
		long high=(long)Math.min(b,c)*a;
		long gcdBC=gcd(b,c);
	    long lcm=(long)b*c/gcdBC;
		while (low<=high){
			long mid=low+((high-low)/2);
			//no.of multiples of b up to mid+ no.of multiples of c up to mid - common multiples of b,c
	        long multiples=(mid/b)+(mid/c)-(mid/lcm);
	        //if multiples are reaches to target make it answer
			if(multiples==a) {
				ans=mid;
				/*
				there is a chance to that answer can't be fist a multiples which have a multiples
				take example of 5,7 a=3
				numbers           [ 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 ]
				multiples 5 or 7 :  0 0 0 0 1 1 2 2 2  3  3  3  3  4  5 ]
				In above case if we are at 13 answer will be 13 that is not first number 10 is the actual and accurate answer we we have to move left for accurate answer 
				 */
				high=mid-1;
			}
			//if multiples are less then a move right
			else if( multiples<a ){
				low=mid+1;
			}
			//if factors are greater then move left
			else{ // (divisibles>a) 
				high=mid-1;
			}		
		}
			return (int)(ans%1000000007);
	}
	public static int gcd(int a,int b) {
		if(b==0) {
			return a;
		}
		return gcd(b, a%b);
	}
	
	// brute force
	public static int bruteForceMagicalNum(int a,int b,int c) {
		long ans=0;
		long cnt=0;
		long min=Math.min(b,c);
		for(long i=min;i<=min*a;i++) {
			if(i%b==0 || i%c==0) {
				cnt++;
			}
			if(cnt==a) {
				return (int)i;
			}
		}
		return (int)(ans%1000000007);
		
	}

}
