package com.prudhvi.Bit_manuplation;
/*
	You have an array A with N elements. We have two types of operation available on this array :
	We can split an element B into two elements, C and D, such that B = C + D.
	We can merge two elements, P and Q, to one element, R, such that R = P ^ Q i.e., XOR of P and Q.
	You have to determine whether it is possible to convert array A to size 1, containing a single element equal to 0 after several splits and/or merge?
 */
public class InterestingArray {

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,6,7};
		System.out.println(splicttingArray(arr));

	}
	/*
	The logic is based on the observation that XOR of two odd numbers is always even. 
	If the array has an even count of odd numbers, it implies that you can pair these odd numbers during the XOR operation, resulting in even numbers. 
	The even numbers can then be split into two even numbers, and so on, until you eventually get a single element equal to 0.

	If any element in the array is odd, 
	follow the strategy below to split it into two parts: 
	(1,num-1). An odd element can be reduced to size 1 using this method.
	
	For two odd elements, the following approach can be employed: 
	split each element using the aforementioned strategy and then XOR the results. 
	For example, if we take the number 12, it can be split into 6 and 6. The XOR of 6 and 6 (6^6 = 0) demonstrates that XORing the same number yields 0.
	
	If the number of odd elements in the array is even, then a valid answer is possible. 
	Consider the example of the array [3, 5]: 
		we can split 3 into 2 and 1 (where 2 can further split into 1 and 1, resulting in a XOR value of 0), and 
		5 into 4 and 1 (where 4 can further split into 2 and 2, also resulting in a XOR value of 0). 
		We are left with two 1s, and the XOR of (1^1 = 0), satisfying the condition. 
		
		However, if there is an odd count of odd numbers,the condition is not met. 
		For instance, in the array [3, 5, 7], the remaining 1 from 7 disrupts the even count of odd elements, rendering it impossible to satisfy the given condition. 
		The final XOR result in this case is 0.
	
	So, if the count of odd numbers is even, the function returns "yes." Otherwise, it returns "false."
	 */
	public static String splicttingArray(int[] arr) {
		int n=arr.length;
		int oddNumCount=0;
		for(int i=0;i<n;i++) {
			if(arr[i]%2!=0) {
				oddNumCount++;
			}
		}
		if(oddNumCount%2==0) {
			return "yes";
		}
		return "flase";
	}

}
