package com.prudhvi.Arrays.two_dimentsional_arrays;

import java.util.Arrays;

public class TwoDArrayPrefixSum {
	/*
	Given a matrix of integers A of size N x M and multiple queries Q, for each query, find and return the submatrix sum.
	Inputs to queries are top left (b, c) and bottom right (d, e) indexes of submatrix whose sum is to find out.
	NOTE:
		1.Rows are numbered from top to bottom, and columns are numbered from left to right.
		2.The sum may be large, so return the answer mod 109 + 7.
		3.Also, select the data type carefully, if you want to store the addition of some elements.
		4.Indexing given in B, C, D, and E arrays is 1-based.
		5.Top Left 0-based index = (B[i] - 1, C[i] - 1)
		6.Bottom Right 0-based index = (D[i] - 1, E[i] - 1)

	Problem Constraints
		1 <= N, M <= 1000
		-100000 <= A[i] <= 100000
		1 <= Q <= 100000
		1 <= B[i] <= D[i] <= N
		1 <= C[i] <= E[i] <= M
	 */

	public static void main(String[] args) {
		 int[][] A = {  {1, 2, 3},
		         {4, 5, 6},
		         {7, 8, 9}   };
		 int[] B = {1, 2};
		 int[] C = {1, 2};
		 int[] D = {2, 3};
		 int[] E = {2, 3};
		 
		 int[] ans1=prefisSumQuries(A, B, C, D, E);
		System.out.println(Arrays.toString(ans1));
	}

	public static int[] prefisSumQuries(int[][] A, int[] B, int[] C, int[] D, int[] E) {
        int mod=1000000007;//mod is set to 1000000007, a large prime number used to prevent integer overflow in subsequent calculations.
        long[][] prefixsumArray=new long[A.length][A[0].length];//A new 2D array prefix array is created to store cumulative sums of elements from the input 2D array A
        //The first loop iterates over the rows of A, and the nested loop iterates over the columns.
		for(int i=0;i<prefixsumArray.length;i++) {
			for(int j=0;j<prefixsumArray[0].length;j++) {
				//The if-else block is used to calculate the cumulative sum in A[i][j]. 
				//If j is not 0, it means we are not at the first column, so we add the current element A1[i][j] to the cumulative sum of the previous column (A[i][j - 1]). 
                if(j!=0){
                    prefixsumArray[i][j]=(prefixsumArray[i][j-1]+A[i][j]);	
                }
                //Otherwise, for the first column, we directly set prefixsumArray[i][j] to the value of A[i][j].
                else{
                    prefixsumArray[i][j]=A[i][j];
                }
				
			}
		}
		//After calculating the cumulative sums for each row, another loop is used to calculate the cumulative sums for each column.
		for(int i=0;i<prefixsumArray.length;i++) {
			for(int j=0;j<prefixsumArray[i].length;j++) {
				//The if block is used to calculate the cumulative sum in A[i][j]. If i is not 0, 
				//it means we are not at the first row, so we add the current element A[i][j] to the cumulative sum of the previous row (A[i - 1][j]).
                if(i!=0){
                    prefixsumArray[i][j]=(prefixsumArray[i-1][j]+prefixsumArray[i][j]);
                }	
			}
		}
   
    int[] finalArr=new int[B.length];//An integer array finalArr is created to store the final results.
    //The loop iterates over the elements of the input arrays B, C, D, and E simultaneously using the index i.
    for(int i=0;i<B.length;i++){
    	//For each iteration, x1, y1, x2, and y2 are calculated by subtracting 1 from the corresponding values in B, C, D, and E, respectively. 
    	//These indices are adjusted to be zero-based to access the elements of the A array properly.
        int x1=B[i]-1;
        int y1=C[i]-1;
        int x2=D[i]-1;
        int y2=E[i]-1;
        long sum=prefixsumArray[x2][y2];//sum is initialized with the cumulative sum at the position A[x2][y2].
        //The next three blocks of if statements adjust the value of sum based on the values of x1, y1, x2, and y2 to calculate the sum of elements within the specified sub rectangle in prefix array
        if(y1>0){
            sum=sum-prefixsumArray[x2][y1-1];
        }
        if(x1>0){
            sum=sum-prefixsumArray[x1-1][y2];
        }
        if(x1>0 && y1>0){
            sum=( sum + prefixsumArray[x1-1][y1-1] );
        }
        //The while loop ensures that sum remains non-negative by adding mod to it repeatedly until it becomes positive. 
        //This step is done to handle negative values of sum that may arise during the calculations.
        while(sum<0){
            sum+=mod;
        }
        //Finally, the integer value of sum modulo mod is stored in the corresponding position of finalArr
        finalArr[i]=(int)(sum%mod);
    }
    //After processing all elements, finalArr is returned as the result of the solve method. The array finalArr contains the required final results for each sub rectangle specified by the input arrays B, C, D, and E.
    return finalArr;
    }

}
/*
 The relevant part of the code containing the while loop is as follows:


while (sum < 0) {
    sum += mod;
}
finalArr[i] = (int) (sum % mod);

The purpose of the while loop is to ensure that the variable sum remains non-negative. Before we analyze the loop, let's consider an example scenario where sum becomes negative:

Example Scenario:
Suppose we have the following input values:

mod = 10: The modulo value (a large prime number).
A = [[5, 8], [10, 3]]: A 2D array with cumulative sums.
B = [1, 2], C = [1, 1], D = [2, 2], E = [2, 2]: Arrays representing subrectangles within A.
We'll focus on the first subrectangle specified by B[0], C[0], D[0], and E[0]. The values will be:

x1 = B[0] - 1 = 1 - 1 = 0
y1 = C[0] - 1 = 1 - 1 = 0
x2 = D[0] - 1 = 2 - 1 = 1
y2 = E[0] - 1 = 2 - 1 = 1
The initial value of sum is calculated as follows:


long sum = A[x2][y2];
// A[1][1] = 3
sum = 3;

Next, we adjust sum based on the values of x1, y1, x2, and y2:


if (y1 > 0) {
    sum = sum - A[x2][y1 - 1];
    // A[1][0] = 10
    sum = 3 - 10 = -7;
}
if (x1 > 0) {
    sum = sum - A[x1 - 1][y2];
    // A[0][1] = 8
    sum = -7 - 8 = -15;
}
if (x1 > 0 && y1 > 0) {
    sum = (sum + A[x1 - 1][y1 - 1]);
    // A[0][0] = 5
    sum = -15 + 5 = -10;
}

After this adjustment, sum becomes negative (-10). This is where the while loop comes into play.

Explanation of the while loop:
The while loop ensures that sum remains non-negative by repeatedly adding mod to it until it becomes positive or zero. The loop continues until sum is greater than or equal to zero.

In our example scenario, the while loop will execute as follows:


// Initially, sum = -10
while (sum < 0) {
    // sum = -10 + 10 = 0
    sum += mod;
}

Now, sum has been adjusted to be non-negative (0). After the while loop, the value of sum is as follows:


sum = 0;

Finally, the value of sum is stored in the finalArr array at the corresponding position finalArr[i], after applying the modulo operation:


finalArr[i] = (int) (sum % mod);
// finalArr[i] = (int) (0 % 10) = 0

In this specific example, the value of finalArr[0] will be 0. This process will be repeated for all the subrectangles specified in the input arrays B, C, D, and E, and the corresponding results will be stored in finalArr.

The primary reason for using the while loop in this context is to ensure that the result is always a non-negative integer within the range of int. The loop guarantees that sum remains non-negative even when some intermediate calculations result in negative values
 */

