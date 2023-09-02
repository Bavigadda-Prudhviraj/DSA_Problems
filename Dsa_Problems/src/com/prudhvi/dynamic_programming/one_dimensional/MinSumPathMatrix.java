package com.prudhvi.dynamic_programming.one_dimensional;



public class MinSumPathMatrix {

	public static void main(String[] args) {
		int[][] arr= {{1, -3, 2},
		              {2, 5, 10},
		              {5, -5, 1}};
		int minSum=minSumPath(arr);
		System.out.println(minSum);

	}

	private static int minSumPath(int[][] arr) {
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[0].length;j++) {
				if(i==0&& j==0) {
					continue;
				}
				else if(i==0 && j>0) {
					arr[i][j]=arr[i][j-1]+arr[i][j];
				}
				else if(j==0 && i>0) {
					arr[i][j]=arr[i-1][j]+arr[i][j];
				}else {
					arr[i][j]=Math.min(arr[i-1][j],arr[i][j-1] )+arr[i][j];
				}
				
			}
		}
		return arr[arr.length-1][arr[0].length-1];
	}

}
