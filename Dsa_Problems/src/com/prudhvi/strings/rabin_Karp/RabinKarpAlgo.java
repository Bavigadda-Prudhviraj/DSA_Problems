package com.prudhvi.strings.rabin_Karp;

public class RabinKarpAlgo {
	/*
	Problem Description
		Given two string A and B of length N and M respectively consisting of lower case letters.
		 Find the number of occurrences of B in A.
		 
	Problem Constraints
		1 <= M <= N <= 105
	
	Approach:
		We will use the Rabin Karp algorithm. We compare the hash values of the pattern and the 
		corresponding substring of the string A of length equal to that of B. 
		If the hash value matches, then we increment our count.
		As the Rabin Karp algorithm is depending on the hash values, we need to 
		calculate the hash value very carefully so that we can minimize the collision.
			Time Complexity : O(N + M)
			Space Complexity : O(N) if we use array to store the hash values else SC O(1)
	 */

	public static void main(String[] args) {
		String message = "acbac";
		String text = "ac";
		int answer=rabinKarp(message,text);
		System.out.println(answer);
		
	}
	/*
	 * 
	Time Complexity:
			The time complexity of the Rabin-Karp algorithm is O(N+M) in the best case, where N is the length of the message and M is the length of the text when no text is message. 
			This occurs when there are no matches between the text and the message. In the worst case, where all characters of the message match the text, the time complexity is O(N*M).
	
	Space Complexity:
			The space complexity of the algorithm is O(1) as it uses a constant amount of additional space to store variables and calculate the hash values.
	 */
	public static int rabinKarp(String message,String text) {
		int textCount=0; //keep track of the count of occurrences.
		int msgLen=message.length();
		int textLen=text.length();
		int pow=textLen-1;//the power value for calculating the hash.
		int base=29;//Choose a base value for the hash function. In this case, it is set to 29.why 29 reason written at the end
		long mod=1000000007;//Choose a large prime number as the modulo to avoid overflow in hash calculations.
		int textHashSum=0;
		int pointerI=0;
		// Calculate the hash value of the text pattern
		while (pointerI<textLen){	
			//Calculate the hash sum using the Rabin-Karp formula
			textHashSum+=(1L*(text.charAt(pointerI)-'a')* fastPower(base, pow,mod) )%mod;	
			//Increment the pointer and decrement the power value.
			pointerI++;
			pow--;
		}
		pointerI=0;
		pow=textLen-1;
		long windowSum=0;
		// Calculate the hash value of the first sliding window in the message
		while (pointerI<textLen){
			windowSum=(long)((windowSum+((message.charAt(pointerI)-'a')*fastPower(base, pow,mod))%mod));	
			pointerI++;
			pow--;
		}
		// Check if the hash sums of the text and the first sliding window are equal:
		if( windowSum==textHashSum) {
			//If equal, call the compareString function to check if the substring matches.
			if(compareString(message, text,0)) {
				//If true, increment the textCount variable.
				textCount++;
			}
		}
		//Set up variables for sliding the window:
		pointerI=1;
		long baseMaxPow=fastPower(base,textLen-1,mod);//this is to calculate the removing character weightage
		int pointerJ=textLen;
		long windowHashSum=windowSum;
		// Slide the window over the message and update the hash value
		while (pointerJ<msgLen){
			long removingChar=(message.charAt(pointerI-1)-'a')*baseMaxPow;
			int addingChar=message.charAt(pointerJ)-'a';
			//Update the windowHashSum by removing the first character of the previous window and adding the next character:
			windowHashSum=( ((( windowHashSum-(removingChar))*base)%mod  )+addingChar )%mod ;
			//Handle negative values of windowHashSum: if (windowHashSum < 0) windowHashSum += mod;
			if(windowHashSum<0) {
				windowHashSum+=mod;
			}
			// Check if the updated windowHashSum matches the textHashSum and the substring matches using the compareString function.
			if(windowHashSum==textHashSum) {
				//If true, increment the textCount variable.
				if(compareString(message, text, pointerI)) {
					textCount++;
				}
			}
			//Increment the pointers to move the sliding window.
			pointerJ++;
			pointerI++;
		}
		return textCount;
	}
	public static boolean compareString(String message,String text,int pointer) {
		int pointerI=pointer;
		int textLen=text.length();
		for(int i=0;i<textLen;i++) {
			if(text.charAt(i)!=message.charAt(pointerI)) {
				return false;
			}
			pointerI++;
		}
		return true;
	}
	private static long fastPower(long base, long exponent, long mod) { 
        long res = 1;        
        base = base % mod;       
        while (exponent > 0) { 
            if ((exponent & 1) == (long)1) 
                res = (res*base) % mod; 
            exponent = exponent >> 1; 
            base = (base * base) % mod; 
        }
        return res; 
    }
/*
 	why 29?
		The choice of the base value in the Rabin-Karp algorithm is crucial for the effectiveness of the hashing mechanism. 
		It should be a prime number that is large enough to minimize collisions and distribute the hash values evenly.
		
		In this scenario, we are working with lowercase characters. 
		To ensure that the modulo values cover the range of letters (0-27), 
		we need to select a prime number greater than the sum of these characters. 
		Therefore, we have chosen the prime number 29, which is the closest prime number greater than 26 (the number of letters in the alphabet). 
		By selecting a prime number, we reduce the likelihood of collisions and ensure that the modulo values are evenly distributed. 
		Choosing a larger prime number further decreases the collision rate and improves the accuracy and effectiveness of the hashing process.
		
		In the given code, the base value is set to 29. 
		The number 29 is a prime number, and it is commonly used as a base value in hashing algorithms due to its properties. 
		Here are a few reasons why 29 is often chosen as the base:

			1.Prime Number: 
				29 is a prime number, which means it is only divisible by 1 and itself. 
				Using a prime base helps reduce the chance of collisions and ensures a good distribution of hash values.

			2.Avoiding Power of 2: 
				Choosing a base that is a power of 2 (e.g., 2, 4, 8, 16, etc.) can lead to collisions when using modular arithmetic. 
				By selecting a prime base like 29, the algorithm avoids this issue and improves the effectiveness of the hashing.

			3.Balance of Factors: 
				The number 29 has a good balance of factors, which contributes to a more evenly distributed set of hash values.
				 This helps reduce the likelihood of collisions and improves the efficiency of the Rabin-Karp algorithm.

			It's important to note that the choice of the base value depends on the specific requirements and characteristics of the input data. 
			Different prime numbers or even non-prime numbers can be chosen as the base value in different scenarios. 
			Experimentation and analysis of the specific problem domain can help determine the most suitable base value for optimal performance.
			
	why mod(10^9+7)?
		In the Rabin-Karp algorithm, the modulo value is chosen as a large prime number to prevent the hash values from overflowing and to ensure the correctness of the calculations. Here's why a large prime number is used as the modulo value:
			1.Avoiding Integer Overflow:
			 		As the algorithm involves multiplying and adding large numbers during the hash calculations, there is a risk of integer overflow, 
			 		especially if the numbers involved are too large. 
			 		By choosing a large prime number as the modulo value, the intermediate results remain within a manageable range, reducing the chance of overflow.

			2.Handling Collisions: 
					Collisions occur when two different inputs produce the same hash value. 
					By selecting a large prime number as the modulo, it helps minimize collisions and improve the uniqueness of the resulting hash values. 
					This is particularly important in algorithms like Rabin-Karp, where collisions can lead to false positive matches.

			3.Preservation of Precision: 
					When performing calculations involving division or modulo, the use of a large prime modulo value helps preserve the precision of the results. 
					This is especially crucial in algorithms that rely on the equality of hash values for matching purposes.

			4.Ensuring Hash Uniformity: 
					A large prime number modulo value helps distribute the hash values more uniformly across the range of possible values. 
					This enhances the performance of the algorithm by reducing the likelihood of clustering and producing a more even distribution of hash values.

			By choosing a large prime number as the modulo value, the Rabin-Karp algorithm can effectively handle large inputs, avoid integer overflow, minimize collisions, preserve precision, and ensure uniformity in hash values.

		It's worth noting that the specific choice of the modulo value depends on the requirements of the problem, and different prime numbers may be used based on the expected size of the input and desired properties of the hash function.
 */

}
