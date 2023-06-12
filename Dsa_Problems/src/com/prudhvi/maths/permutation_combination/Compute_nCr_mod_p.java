package com.prudhvi.maths.permutation_combination;

/*
 	Given three integers A, B, and C, where A represents n, B represents r, and C represents m, find and return the value of nCr % m where nCr % m = (n!/((n-r)!*r!))% m.
		x! means factorial of x i.e. x! = 1 * 2 * 3... * x.
 		Note:same problem with different constants
 			Problem Constraints
 				1 <= A * B <= 106
				1 <= B <= A
				1 <= C <= 106
 
 	Algo:Here's an explanation of how to solve the problem:
			1.First, create an empty list to store the Pascal's triangle.
			2.Iterate from 0 to numRows (inclusive) to generate each row of the triangle.
			3.Inside the loop, create an empty list to represent the current row.
			4.Iterate from 0 to the current row index (inclusive) to calculate the values for each element in the row.
			5.For the first and last elements of each row (indices 0 and row index), set the value to 1.
			6.For the other elements, calculate the value by summing the corresponding elements from the previous row (i.e., the previous row's current index and current index - 1).
			7.Append the current row to the triangle.
			8.After the loop, nCr index.
			
			
			Main formula nCr= (n-1)C(r))! + (n-1)C(r-1)!
 */
public class Compute_nCr_mod_p {

	public static void main(String[] args) {
		int A = 7269;
		int B = 4002;
		int C = 331997;
		int ans=permutaion_combination(A,B,C);
		System.out.println(ans);

	}

	private static int permutaion_combination(int n, int r, int mod) {
		//here are we are making 1 based index so we can return the nCr value at nCr[n][r] directly
        int[][] nCr=new int[n+1][r+1];
        for(int i=0;i<nCr.length;i++) {
        	for(int j=0;j<nCr[i].length;j++) {
        		if(j==0) {
        			//nCo=1 (i,0)
        			nCr[i][j]=1;
        		}
        		else if( r==0 && j>0) {
        			//oC1=0(r==0,j>0)
        			nCr[i][j]=0;
        		}
        		// nCn=1(i==j)
        	}
        }
        for(int i=1;i<nCr.length;i++) {
        	for(int j=1;j<nCr[i].length;j++) {
        		//nCr= (n-1)C(r))! + (n-1)C(r-1)!
        		nCr[i][j]=(nCr[i-1][j]+nCr[i-1][j-1])%mod;
        	}
        }
		return nCr[n][r]%mod;
	}

}
