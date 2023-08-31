package com.prudhvi.dynamic_programming.one_dimensional;

public class DiceSum {

	public static void main(String[] args) {
		int sum=7;
		int noOfways=diceSumDpEquation(sum);
		System.out.println(noOfways);
		int noOfwaysTogetSum=diceSum(sum);
		System.out.println(noOfwaysTogetSum);

	}

	private static int diceSumDpEquation(int sum) {
		if(sum==0) {
			return 1;
		}
		int[] dp=new int[sum+1];
		dp[0]=1;
		for(int i=1;i<=sum;i++) {
			int currentSum=0;
			for(int j=1;j<=sum && j<=i;j++) {
				currentSum+=dp[i-j];
			}
			dp[i]=currentSum;
		}
		return dp[sum];
	}

	private static int diceSum(int sum) {
		if(sum<=5) {
			int[] dp=new int[6];
			dp[0]=1;dp[1]=1;dp[2]=2;dp[3]=4;dp[4]=8;dp[5]=16;
			return dp[sum];
		}
		int[] dp=new int[sum+1];
		dp[0]=1;dp[1]=1;dp[2]=2;dp[3]=4;dp[4]=8;dp[5]=16;
		for(int i=6;i<=sum;i++) {
			dp[i]=dp[i-1]+dp[i-2]+dp[i-3]+dp[i-4]+dp[i-5]+dp[i-6];
		}
		return dp[sum];
	}

}
