package com.prudhvi.dynamic_programming.matrix_chain_multiplication;
/*
 	returnType functioneName(i,j,arr,dp){
 		//base cases:
 		 1.think of smallest input
 		 2.think of invalid input think more about this
 		 if(i>j){
 		 	return 0;
 		 }
 		 if(dp[i][[j] preCalculate) return dp[i] [j];
 		 int ans=
 		 for(int k=ik<j;k++){ range of k varies depends on question
 		 	int left=slove(i,k,arr,dp)
 		 	int right=slove(k+1,j,arr,dp)
 		 	int tempAns=left(+ or - or * or / or min or max)right (operation depends in question.
 		 	update ans;
 		 	
 		 }
 		 dp[[i][j]=ans;
 		 return dp[i][j];
 		 
 	}
 	
	Here are some problems based on matrix chain multiplication (MCM) that you can practice:

		1.Matrix Chain Multiplication: 
				Given a sequence of matrices, find the most efficient way to multiply these matrices together to minimize the number of scalar multiplications.
		2.Parenthesization of Matrices: 
				Given a sequence of matrices, find the optimal placement of parentheses to minimize the number of scalar multiplications needed to compute the product.
		3.Scrambled Strings: 
				Given two strings s1 and s2, determine if s2 is a scrambled string of s1. (This can be solved using MCM with a slight modification.)		
		4.Balloon Burst: 
				Given a sequence of balloons, each with a number on it, you can burst the balloons. Determine the maximum coins you can collect by bursting the balloons in an optimal order.		
		5.Egg Dropping Problem: 
				Given a certain number of eggs and a certain number of floors, find the minimum number of attempts needed to determine the highest floor from which an egg can be dropped without breaking.	
		6.Partition Array into Palindromic Substrings: 
				Given a string, partition it into as many palindromic substrings as possible to minimize the number of partitions.		
		7.Maximum Value Arithmetic Expression: 
				Given a sequence of numbers and arithmetic operators (+, -, *), find the maximum value that can be obtained by adding parentheses to the expression.

*/