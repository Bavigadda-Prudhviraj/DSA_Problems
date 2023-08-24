package com.prudhvi.greedy;

import java.util.ArrayList;

public class AnotherCoinProblem {

	public static void main(String[] args) {
		int totoalAmount=47;
		int answer=minCoins(totoalAmount);
		System.out.println(answer);
		System.out.println(mincoins(totoalAmount));

	}
	/*
	
	Time Complexity:
			The first while loop runs in O(log₅ A) time.
			The second while loop runs a maximum of O(log₅ A) times.
		Overall, the time complexity of the function is O(log₅ A).
	
	Space Complexity:
		The space complexity is constant as the function only uses a few integer variables.
	 */
	public static int mincoins(int A) {
		int k = 0;//The variable k is used to keep track of the power of 5. It starts at 0.
	    int product = 5;
	    //The while loop calculates the maximum power of 5 (product) that can be used without exceeding the given amount A. 
	    //Each iteration increases product by a factor of 5 and increments k.
	    while (product <= A) {
	    	product *= 5;
	        k++;
	    }
	    //After calculating k, the loop calculates the count of coins required for each power of 5 from the highest power of 5 to the lowest.
	    int count = 0;
	    while (A > 0) {
	    	//coins is calculated by dividing A by 5^k, which represents how many 5-coin denominations are needed for the current power of 5.
	    	int coins = A / (int)Math.pow(5, k);
	        count += coins;//count keeps track of the total number of coins required.
	        A = A - coins * (int)Math.pow(5, k);//A is updated by subtracting the value of coins times 5^k, which represents the value of coins used for the current power of 5.
	        k--;//he value of k is decremented to move to the next lower power of 5 for the next iteration.
	    }
	    return count;
	}
	//take more TC
	private static int minCoins(int totoalAmount) {
		int coinsCount=0;
		ArrayList<Integer> coins=new ArrayList<>();
		coins.add(1);
		while(coins.get(coins.size()-1)<totoalAmount){
			int amount=coins.get(coins.size()-1);
			coins.add(amount*5);
		}
		for(int i=coins.size()-1;i>=0;i--) {
			if(coins.get(i)>totoalAmount) {
				continue;
			}else {
				if(totoalAmount<5) {
					coinsCount+=totoalAmount;
					break;
				}else {
					int currentCoins=(totoalAmount/coins.get(i));
					coinsCount+=currentCoins;
					totoalAmount=totoalAmount-(currentCoins*coins.get(i));
				}
				
				
			}
		}
		return coinsCount;
	}

}
