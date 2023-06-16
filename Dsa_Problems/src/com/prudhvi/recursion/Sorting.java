package com.prudhvi.recursion;

public class Sorting {

	public static void main(String[] args) {
		int[] A= {8,16,80,55,32,8,38,40,65,18,15,45,50,38,54,52,23,74,81,42,28,16,66,35,91,36,44,9,85,58,59,49,75,20,87,60,17,11,39,62,20,17,46,26,81,92};
		int B=9;
		int[] ans=new int[A.length];
    for(int i=0;i<A.length;i++){
        ans[i]=A[i];
    }
    int[] sort=SelectionSorting(ans,B);
    System.out.println(sort[B-1]);
		
	}
	public static int[] SelectionSorting(int[] arr,int b){
        for(int i=0;i<b;i++){
            int minValues=arr[i];
            int minIndex=i;
            for(int j=i+1;j<arr.length;j++){
                if(arr[j]<minValues){
                    minValues=arr[j];
                    minIndex=j; 
                }
            }
            int temp=arr[i];
            arr[i]=arr[minIndex];
            arr[minIndex]=temp;
        }
        return arr;
    }

}
