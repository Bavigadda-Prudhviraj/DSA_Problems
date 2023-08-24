package com.prudhvi.backtracking;

public class AllPathFrom1To2 {

	public static void main(String[] args) {
		int[][] arr= {{1, 0},
			          {0, 0},
			          {0, 2}};
		int paths=allPaths(arr);
		System.out.println(paths);

	}
	static int allpathsCount=0;
	public static int allPaths(int[][] arr) {
		int[] indexes=start_emptySquares(arr);
		int emptySquares=indexes[2];
		int row=indexes[0];
		int col=indexes[1];
		ways(arr,row,col,emptySquares+1);
		return allpathsCount;
	}
	private static int[] start_emptySquares(int[][] arr) {
		int[]  indexes=new int[3];
		int zerosCount=0;
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				if(arr[i][j]==0) {
					zerosCount++;
				}
				else if(arr[i][j]==1) {
					 indexes[0]=i;
					 indexes[1]=j;
				}
			}
		}
		indexes[2]=zerosCount;
		return indexes;
	}
	private static void ways(int[][] arr,int row,int col,int emptySquares){
		if(row<0 || col<0 || row>=arr.length || col>=arr[0].length || arr[row][col]==-1){
			return ;
		}
		if(arr[row][col]==2) {
			allpathsCount++;
			return;
		}
		int temp=arr[row][col];
		arr[row][col]=-1;
	    ways(arr, row+1, col,  emptySquares-1);
		ways(arr, row-1, col,  emptySquares-1);
		ways(arr, row,   col+1,emptySquares-1);
		ways(arr, row,   col-1,emptySquares-1);
		arr[row][col]=temp;
	
	}

}
