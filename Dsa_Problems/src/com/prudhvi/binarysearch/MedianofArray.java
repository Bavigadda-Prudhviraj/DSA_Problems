package com.prudhvi.binarysearch;

import java.lang.reflect.Method;
import java.util.*;

public class MedianofArray {
	/*
	There are two sorted arrays A and B of sizes N and M respectively.
	Find the median of the two sorted arrays ( The median of the array formed by merging both the arrays ).
	NOTE:
		1.The overall run time complexity should be O(log(m+n)).	
		2.IF the number of elements in the merged array is even, then the median is the average of (n/2)th and (n/2+1)th element. For example, if the array is [1 2 3 4], the median is (2 + 3) / 2.0 = 2.5.
	
	 Hint 1:
	 	The expected time complexity gives away binary search in this case.
		We are going to do binary search for the answer in this case.
		Given a sorted array A of length m, we can split it into two parts:
			{ A[0], A[1], … , A[i - 1] }	{ A[i], A[i + 1], … , A[m - 1] }
			All elements in the right part are greater than elements in the left part.
		The left part has i elements, and the right part has m - i elements.
	 There are m + 1 kinds of splits.
	 (i = 0 ~ m)

		When i = 0, the left part has “0” elements, the right part has “m” elements.
		When i = m, the left part has “m” elements, right part has “0” elements.

		For the array B, we can split it in the same way:

		{ B[0], B[1], … , B[j - 1] }	{ B[j], B[j + 1], … , B[n - 1] }
		The left part has “j” elements, and the right part has “n - j” elements.

		Put A’s left part and B’s left part into one set. (Let’s name this set “LeftPart”)

		Put A’s right part and B’s right part into one set. (Let’s name this set”RightPart”)

        		LeftPart           |            RightPart
		{ A[0], A[1], … , A[i - 1] }	{ A[i], A[i + 1], … , A[m - 1] }
		{ B[0], B[1], … , B[j - 1] }	{ B[j], B[j + 1], … , B[n - 1] }
		If we can ensure the following:
			LeftPart’s length == RightPart’s length (or RightPart’s length + 1)
			All elements in RightPart is greater than elements in LeftPart,
		Then we can split all elements in {A, B} into two parts with equal length, and one part is always greater than the other part.

		Then the median can thus be easily found.
			i.Based on condition 1, can you derive the value of j if the value of i is known?
			ii.Can you binary search on i ?
		
		
		Approach:
			Given a sorted array A of length m, we can split it into two parts:
			{ A[0], A[1], … , A[i - 1] }	{ A[i], A[i + 1], … , A[m - 1] }
			All the elements in the right part are greater than those in the left part.
			The left part has “i” elements, and the right has “m - i” elements.
			There are “m + 1” kinds of splits. (i = 0 ~ m)
			When i = 0, the left part has “0” elements, right part has “m” elements.
			When i = m, the left part has “m” elements, right part has “0” elements.
			For array B, we can split it with the same way:
				{ B[0], B[1], … , B[j - 1] }	{ B[j], B[j + 1], … , B[n - 1] }
				The left part has “j” elements, and the right has “n - j” elements.
				Put A’s left part and B’s left part into one set. (Let us name this set “LeftPart”)
				Put A’s right part and B’s right part into one set. (Let us name this set”RightPart”)
        				LeftPart           |            RightPart 
				{ A[0], A[1], … , A[i - 1] }	{ A[i], A[i + 1], … , A[m - 1] }
				{ B[0], B[1], … , B[j - 1] }	{ B[j], B[j + 1], … , B[n - 1] }
			If we can ensure the following:
				1) LeftPart’s length == RightPart’s length (or RightPart’s length + 1)
				2) All elements in RightPart is greater than elements in LeftPart,
			Then we split all elements in {A, B} into two parts with equal length, and one part is always greater than the other part.
			Then the median can be easily found.
			The expected time complexity gives away binary search in this case.
			We will do binary search for the answer in this case.
			Given a sorted array A of length m, we can split it into two parts:

{ A[0], A[1], … , A[i - 1] }	{ A[i], A[i + 1], … , A[m - 1] }
All elements in the right part are greater than elements in the left part.

The left part has i elements, and the right has m - i elements.
There are m + 1 kinds of splits.

(i = 0 ~ m)

When i = 0, the left part has “0” elements, the right part has “m” elements.
When i = m, the left part has “m” elements, right part has “0” elements.

For the array B, we can split it in the same way:

{ B[0], B[1], … , B[j - 1] }	{ B[j], B[j + 1], … , B[n - 1] }
The left part has “j” elements, and the right part has “n - j” elements.

Put A’s left part and B’s left part into one set. (Let’s name this set “LeftPart”)

Put A’s right part and B’s right part into one set. (Let’s name this set”RightPart”)

        LeftPart           |            RightPart
{ A[0], A[1], … , A[i - 1] }	{ A[i], A[i + 1], … , A[m - 1] }
{ B[0], B[1], … , B[j - 1] }	{ B[j], B[j + 1], … , B[n - 1] }
If we can ensure the following:

LeftPart’s length == RightPart’s length (or RightPart’s length + 1)
All elements in RightPart is greater than elements in LeftPart,
Then we can split all elements in {A, B} into two parts with equal length, and one part is always greater than the other part.

Then the median can thus be easily found.

To ensure these two conditions, we need to ensure:

Condition 1 :
 i + j == (m - i) + (n - j)
 OR i + j == (m - i) + (n - j) + 1
This means if n >= m,

i = 0 to m
j = (m + n + 1) / 2 - i
Condition 2
 B[j - 1] <= A[i] and A[i - 1] <= B[j]
Considering edge values, we need to ensure:

   (j == 0 or i == m or B[j - 1] <= A[i]) and 

   (i == 0 or j == n or A[i - 1] <= B[j])
So, all we need to do is:

Search i from 0 to m to find an object i to meet conditions (1) and (2) above.
And we can do this search by binary search.

How?

If B[j0 - 1] > A[i0], than the object ix can’t be in [0, i0].

Why?

Because if

  ix < i0, 
  => jx = (m + n + 1) / 2 - ix > j0 
  => B[jx - 1] >= B[j0 - 1] > A[i0] >= A[ix]. 
This violates the condition (2). So ix can’t be less than i0.

And if A[i0 - 1] > B[j0], than the object ix can’t be in [i0, m].
So we can do the binary search following the steps described below:

set imin, imax = 0, m, then start searching in [imin, imax]
Search in [imin, imax]:
    i = (imin + imax) / 2
    j = ((m + n + 1) / 2) - i
    if B[j - 1] > A[i]: 
        search in [i + 1, imax]
    else if A[i - 1] > B[j]: 
        search in [imin, i - 1]
    else:
        if m + n is odd:
            answer is max(A[i - 1], B[j - 1])
        else:
            answer is (max(A[i - 1], B[j - 1]) + min(A[i], B[j])) / 2
	 */

	public static void main(String[] args) {
		ArrayList<Integer> arr1 =new ArrayList<>(Arrays.asList(-35 ,5 ,11, 34 ,35));
		ArrayList<Integer> arr2=new ArrayList<>(Arrays.asList()); //expected 10.0
		double ans=medianOfTwoSortedArrays(arr1,arr2);
		System.out.println(ans);
		double ans1=findMedianSortedArrays(arr1,arr2);
		System.out.println(ans1);
	}
	public static double medianOfTwoSortedArrays(ArrayList<Integer> arr1,ArrayList<Integer> arr2) {
		if(arr2.size()<arr1.size()) {
			return medianOfTwoSortedArrays(arr2, arr1);
		}
		int n1=arr1.size();
		int n2=arr2.size();
		int low=0;// we can choose 0 elements from the first array list 
		int high=n1;// we can choose all elements from the array list we have to choose n1+n2/2 elements but we are considering all elements
		//binary search
		
		while (low<=high){
			int mid=low+(high-low)/2;
			int firstCut=mid; // we are selecting first cut elements from the first array list
			int halfElements=((n1+n2+1)/2); //we are considering + 1 for the odd elements when to array are combined
			int secondCut=halfElements-firstCut;//we are selecting the the no of elements from second array list. we have to consider half elements - first cut we have o subtract first cut elements because first cut elements are added so, remaining elements should be add from the second array list
			int l1=firstCut<=0?Integer.MIN_VALUE:arr1.get(firstCut-1); //=arr1.get(firstCut-1) why? when l1 is at 0th index in arr1  then l1 will be -1 in that case we have to take l1 as -infinity it will be less than r2 always else if firstCut!=0 else we can take l1 as first cut -1 from array 1 
			int l2=secondCut<=0?Integer.MIN_VALUE:arr2.get(secondCut-1); //=arr2.get(secondCut-1) why? when l2 is at 0th index in arr2  then l2 will be -1 in that case we have to take l2 as -infinity it will be less than r1 always else if secondCut!=0 else we can take l2 as second cut  from array 1 
			int r1=firstCut>=n1?Integer.MAX_VALUE:arr1.get(firstCut); //=arr1.get(firstCut) why? when r1>=n1 then will will be out of bound exception so we will take r1 as maximum values then l2 will always less then this values else if firstCut>=n1 false then r1 will be the first cut
			int r2=secondCut>=n2?Integer.MAX_VALUE:arr2.get(secondCut); //=arr2.get(secondCut)when r2>=n2 then will will be out of bound exception so we will take r2 as maximum values then l1 will always less then this values else if second>=n2 false then r2 will be the second cut
			if(l1<=r2 && l2<=r1) {
				int evenOrOddElements=(n1+n2)%2;
				if(evenOrOddElements==0) { //if the no of elements in the both array are even then we have to take the average of the two middle elements [1,2,3,4] mid=2+3/2
					int max=Math.max(l1,l2);
					int min=Math.min(r1,r2);
					return (double)(max+min)/2;  
				}
				else{//if the no of elements in the both array are odd then we have to take the middle of the two middle elements [1,2,3,4,5] mid=3
					return (double)Math.max(l1,l2);
				}	
			}
			else if(l1>r2){
				high=mid-1;
					
			}
			else{
				low=mid+1;
			}
			
			
		}
		return 0.0;
	}
	//second method same but with less lines
	public static double findMedianSortedArrays(final List<Integer> A, final List<Integer> B) {
	    int len = A.size() + B.size();
	    if(len % 2 == 1) 
	        return findKth(A, 0, B, 0, len / 2 + 1);
	    else return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2.0;
	}
	//Method Method calling on 209 and 210
	public static int findKth(List<Integer> A, int A_start, List<Integer> B, int B_start, int k){
	    if(A_start >= A.size()) 
	        return B.get(B_start + k - 1);
	    if(B_start >= B.size()) 
	        return A.get(A_start + k - 1);
	    if(k == 1) 
	        return Math.min(A.get(A_start), B.get(B_start));
	    int A_key = A_start + k / 2 - 1 < A.size() ? A.get(A_start + k / 2 - 1) : Integer.MAX_VALUE;
	    int B_key = B_start + k / 2 - 1 < B.size() ? B.get(B_start + k / 2 - 1) : Integer.MAX_VALUE;
	    if(A_key < B_key)
	        return findKth(A, A_start + k / 2, B, B_start, k - k / 2);
	    else
	       return findKth(A, A_start, B, B_start + k / 2, k - k / 2);
	}

}
