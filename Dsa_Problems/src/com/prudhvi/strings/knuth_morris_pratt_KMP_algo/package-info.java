package com.prudhvi.strings.knuth_morris_pratt_KMP_algo;
/*
	Introduction
		KMP is a linear string-matching algorithm with low time complexity. 
		It is the solution to the drawbacks of previous text-matching algorithms. 
		You probably have used the advantages of the string-matching algorithm in your life. 
		For example, while working on Microsoft applications like Microsoft Word, you might use the “Find” option to identify similar words. 
		A pattern-matching algorithm supports the functionality of this Microsoft Word feature. 
		In this article, we will understand the working of the KMP string-matching algorithm. 
		We will also solve an example using the KMP algorithm.

		To begin with, we should first understand why the KMP algorithm was needed and what the naive string-matching algorithms had problems with.
 
	What is the Need for the KMP Algorithm?	
		To match a pattern and a string an application use string-matching algorithms. 
		The simplest algorithm is the basic or naïve string-matching algorithm.
		
		The basic algorithms have some drawbacks and are not effective enough to be used for string-matching applications.
			1.The basic string-matching algorithms consume more time.
			2.They use back-tracking whenever there is a mismatch in the string character and a matching pattern character.
			3.With the increasing length of the sample pattern and original string, it is not feasible to repeat the matching process whenever there is a mismatch.
			4.The repeated process consumes more memory and time.
			5.Their time complexity is O(n*m), where n is the length of the text or string and m is the length of the pattern.
		To overcome the problems with the naïve algorithm. 
		There was a need for a fruitful and effective string-matching algorithm. 
		This requirement gives birth to the KMP algorithm.

	What is KMP Algorithm?
		KMP is the fastest string-matching algorithm. 
		Its full form is Knuth Morris Pratt Algorithm. 
		The KMP algorithm got this name from the name of its inventors. 
		In 1970, James H Morris, Vaughan Pratt, and Donald Knuth invented this linear string-matching algorithm.

		The KMP is the only string-matching algorithm with a time complexity of O(n+m), where n is the string length and m is the pattern length.

		In string-matching algorithms, there are two terminologies: sting or text and pattern.
			1.String or text is the original string that is used for matching.
			2.The pattern is the sample text which is to be matched.
			
	
	Below is the illustration of the above algorithm:
			Consider txt[] = “AAAAABAAABA“, pat[] = “AAAA“
			If we follow the above LPS building process then lps[] = {0, 1, 2, 3} 

-> i = 0, j = 0: txt[i] and pat[j] match, do i++, j++

-> i = 1, j = 1: txt[i] and pat[j] match, do i++, j++

-> i = 2, j = 2: txt[i] and pat[j] match, do i++, j++

-> i = 3, j = 3: txt[i] and pat[j] match, do i++, j++

-> i = 4, j = 4: Since j = M, print pattern found and reset j, j = lps[j-1] = lps[3] = 3

Here unlike Naive algorithm, we do not match first three 
characters of this window. Value of lps[j-1] (in above step) gave us index of next character to match.

-> i = 4, j = 3: txt[i] and pat[j] match, do i++, j++

-> i = 5, j = 4: Since j == M, print pattern found and reset j, j = lps[j-1] = lps[3] = 3
Again unlike Naive algorithm, we do not match first three characters of this window. Value of lps[j-1] (in above step) gave us index of next character to match.

-> i = 5, j = 3: txt[i] and pat[j] do NOT match and j > 0, change only j. j = lps[j-1] = lps[2] = 2

-> i = 5, j = 2: txt[i] and pat[j] do NOT match and j > 0, change only j. j = lps[j-1] = lps[1] = 1

-> i = 5, j = 1: txt[i] and pat[j] do NOT match and j > 0, change only j. j = lps[j-1] = lps[0] = 0

-> i = 5, j = 0: txt[i] and pat[j] do NOT match and j is 0, we do i++. 

-> i = 6, j = 0: txt[i] and pat[j] match, do i++ and j++

-> i = 7, j = 1: txt[i] and pat[j] match, do i++ and j++

We continue this way till there are sufficient characters in the text to be compared with the characters in the pattern…
	
 */
