package com.prudhvi.two_pointers;

public class ContainerWithMostWater {
	/*
	Problem Description
		Given n non-negative integers A[0], A[1], ..., A[n-1] , where each represents a point at coordinate (i, A[i]).
		N vertical lines are drawn such that the two endpoints of line i is at (i, A[i]) and (i, 0).
		Find two lines, which together with x-axis forms a container, such that the container contains the most water.
	Note: You may not slant the container.
	Problem Constraints
		0 <= N <= 105
		1 <= A[i] <= 105
	 */

	public static void main(String[] args) {
		int[] A = {5544,9700,1272,6717,7622,4120,9344,5261,572,5166,7257,1042,2445,390,5147,5772,6654,6194,4376,7100,9343,5577,4311,2210,636,8371,3320,4945,9327,231,7592,6204,5098,4531,2730,5371,454,3682,1297,1620,8199,9972,363,6404,9953,938,5949,4861,2035,2933,4899,8498,1764,6207,6081,8728,8492,3518,4393,1297,6979,9801,9346,6152,9474,1531,2929,4572,203,9180,7416,1935,916,3774,7547,5926,7798,4183,7765,4296,9420,5431,1446,9278,4782,7187,1646,5018,4826,6164,8242,549,6929,4118,6443,9191,1915,8701,8962,4168,9000,3684,6780,1560,9863,8635,8561,9187,6229,4556,2573,9649,1780,5722,2756,7758,849,9530,6342,2605,862,8058,8038,1811,6973,413,670,7734,9275,3758,926,2297,2501,4244,2304,1892,9905,6197,6256,2571,4738,613,7649,2210,7127,9996,8212,3406,7184,4431,8341,4818,3958,215,5717,4969,6637,7204,4920,3088,8280,410,8434,2199,6396,2840,9972,1044,4078,1098,2659,1513,2196,7737,5555,7264,1254,3014,8942,7406,3009,2482,7221,7715,3290,3312,9396,5161,9562,6329,3746,426,9277,1616,8223,3149,5961,6259,6925,5503,7112,5014,9258,53,4369,5953,7763,3496,8983,797,8742,7597,6160,9622,8046,7923,6637,7606,7601,2844,9080,3645,134,9316,3687,7581,587,4538,7625,2803,5650,8967,1285,4576,1384,5853,3947,4950,1803,4405,6263,413,8082,6997,6302,2291,8950,3283,5333,6599,9414,2333,3924,8117,5352,1848,7140,7756,7483,9862,5957,6691,9337,4550,5707,3613,996,7976,285,4967,8460,5502,5209,1441,9182,9379,9577,9910,7292,43,1401,4643,1808,3716,793,6081,1885,3936,1317,6724,2938,3418,7258,2100,8112,5838,5338,154,5270,365,6378,8976,6004,4557,5463,7652,1313,4115,9329,3223,1063,3330,9474,8480,2923,6706,7440,3832,9123,7992,3677,4767,6717,432,1529,1279,4301,5787,7749,3608,9615,8276,8353,8194,9681,6979,5672,1076,9901,4562,3919,9891,9953,5115,9058,763,9473,3422,854,8163,6864,4823,5850,8514,8092,503,375,165,4,242,4245,5901,7951,5772,1361,7885,8169,3116,4203,280,7535,7173,2019,4529,9095,6419,7567,9560,6603,2549,9424,5065,4876,7443,2292,2756,1375,6719,7875,3298,934,6938,9898,253,9899,8972,2540,580,1624,1208,7799,5847,5175,845,4585,6297,4209,6953,2259,587,7073,8646,9503,1347,3996,7441,1512,7025,6829,4001,7218,6887,4876,6390,958,2244,4831,7122,3490,4367};
		int answer=containerWithMostWater(A);
		System.out.println(answer);//3905733
	}
	/*
	the code uses the two-pointer approach to find the container with the most water by maximizing the water volume trapped between two bars at different heights. 
	The pointers start from the two ends of the array and move towards each other, always updating the answer with the maximum water volume found.
	
	Time Complexity: 
			The time complexity of this code is O(n), where n is the number of elements in the input array arr. 
			The while loop iterates at most n times, and each iteration performs constant time operations.

	Space Complexity: 
			The space complexity of this code is O(1), as it uses a constant amount of extra space, independent of the input size. 
			The additional space is used for variables like leftPointer, rightPointer, answer, length, width, and waterVolume, all of which require a constant amount of memory.
	 */
	private static int containerWithMostWater(int[] arr) {
		//The function initializes two pointers leftPointer and rightPointer, pointing to the start and end of the array, respectively. 
		int leftPointer=0;
		int rightPointer=arr.length-1;
		int answer=0;//The variable answer will store the maximum water volume.
		while (leftPointer<rightPointer){
			//calculates the length as the minimum value between the heights of the bars at leftPointer and rightPointer.
			int length=Math.min(arr[leftPointer],arr[rightPointer]);
			//The width is calculated as the difference between rightPointer and leftPointer.
			int width=rightPointer-leftPointer;
			//The waterVolume is calculated as the product of length and width, which represents the amount of water that can be trapped between the two bars at leftPointer and rightPointer
			int waterVolume=length*width;
			//The answer is updated to store the maximum water volume found so far using Math.max()
			answer=Math.max(answer, waterVolume);
			//checks whether the bar height at leftPointer is less than, greater than, or equal to the bar height at rightPointer.
			if(arr[leftPointer]<arr[rightPointer]) {
				//If the bar height at leftPointer is less than the bar height at rightPointer, 
				//it means moving the leftPointer towards the right may result in a higher water volume, so leftPointer is incremented.
				leftPointer++;
			}
			//If the bar height at leftPointer is greater than the bar height at rightPointer,
			//it means moving the rightPointer towards the left may result in a higher water volume, so rightPointer is decremented.
			else if(arr[leftPointer]>arr[rightPointer]) {
				rightPointer--;
			}
			//If the bar height at leftPointer is equal to the bar height at rightPointer, either moving leftPointer or rightPointer will result in the same water volume. 
			//Thus, leftPointer is incremented.
			else {
				leftPointer++;
			}
			
			
		}
		return answer;
	}

}
