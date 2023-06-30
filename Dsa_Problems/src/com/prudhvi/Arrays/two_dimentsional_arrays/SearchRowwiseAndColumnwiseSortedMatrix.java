package com.prudhvi.Arrays.two_dimentsional_arrays;



public class SearchRowwiseAndColumnwiseSortedMatrix {

	public static void main(String[] args) {
		int[][] A = {{1, 2},
			         {3, 3}};
		int B = 3;
		/*Explanation 2:-
		A[2][1] = 3
			2 * 1009 + 1 = 2019
		A[2][2] = 3
			2 * 1009 + 2 = 2020
			The minimum value is 2019
		*/
		int ans=searchIn2dMatrixArray(A, B);
		System.out.println(ans);

	}
	public static int searchIn2dMatrixArray(int[][] arr, int target) {
		//int i = 0; and int j = A[0].length - 1;: These two lines initialize two variables, i and j, that will be used to traverse the 2D array A.
		//i is set to 0, representing the starting row index, and j is set to the last column index, ensuring that we start from the top-right corner of the 2D array.
        int i=0;
        int j=arr[0].length-1;
        //int ans = Integer.MAX_VALUE;: This variable ans is used to store the result, initialized with the maximum possible value of an integer. 
        //It will be used to keep track of the minimum value found during the traversal
        int ans=Integer.MAX_VALUE;
        int value=0;//int value = 0;: This variable value will be used to temporarily store an index calculation based on the current position in the 2D array.
        //The while loop iterates as long as i is within the valid row index range (0 to A.length - 1) and j is within the valid column index range (0 to A[0].length - 1). 
        //This ensures that we stay within the bounds of the 2D array during the traversal.
        while(i<arr.length && j>=0){
        	//if (A[i][j] < target): If the value at the current position is less than the target value target, 
        	//it means that the current row does not contain the target value. So, we move to the next row by incrementing i.
            if(arr[i][j]<target){
                i++;
            }
            //else if (A[i][j] > target): If the value at the current position is greater than the target value target, it means that the current column does not contain the target value. 
            //So, we move to the previous column by decrementing j.
            else if(arr[i][j]>target){
                j--;
            }
            //else: If the value at the current position is equal to the target value B, it means we have found a match. 
            //In this case, we calculate a unique value based on the current row index i and column index j, and store it in the variable value.
            //The value is calculated as (i + 1) * 1009 + (j + 1).
            else{
            	//The addition of 1 in the indices i and j is to convert the zero-based indices used in programming to one-based indices, as mentioned in the problem's context.
                //The value 1009 is used as a multiplier to ensure uniqueness in the value calculation. This multiplier is an arbitrary prime number.
            	value= (i + 1) * 1009 + (j+1);
            	//ans = Integer.min(ans, value);: We update the ans variable by taking the minimum between the current ans and the calculated value. 
            	//This ensures that ans holds the smallest unique value found during the traversal, representing the first occurrence of the target value B in the 2D array.
                ans=Integer.min(ans,value);
                j--;//j--;: After finding a match, we move to the previous column to look for other occurrences of the target value, if any.
            }
        }
        //After the while loop, we check if the ans value has been updated during the traversal. If ans still holds its initial value (Integer.MAX_VALUE), 
        //it means that no occurrence of the target value B was found in the 2D array. In this case, the function returns -1, indicating that the target value is not present.
        if(ans==Integer.MAX_VALUE){
            return  -1;
        }
        //If the ans value has been updated, it means that the target value B was found in the 2D array. 
        //The function returns ans, which represents the unique index value of the first occurrence of the target value B.
        return ans;
       
    }

}
