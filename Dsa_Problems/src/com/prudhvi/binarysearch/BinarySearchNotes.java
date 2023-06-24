package com.prudhvi.binarysearch;
/*
 	Def:divide search space into two parts and repeatedly keep on neglecting one half of the search space,until find your target
 	
 	To apply Binary search we need three conditions
 		1.Search space: were to search element.
 		2.Target: what to search.
 		3.some condition to discard(ignore) one half of search space
 			Note: sorted is not corrected word for condition because we can use binary search on unsorted array also.
 			
 		Standard way of calculating mid in Binary Search:
 			In general=(low+mid)/2;
 			Best way:mid= low+((mid-high)/2)
 			why?
 				let us consider integer max is 100;
 				low=99, high=98 then, (99+98)/2=197/2= 197 is out of range for integer
 				
 				now: 99-((high-low)/2))=98+((1)/2)=98-0.5=98 in range
 				
 			
 		Time Complexity: log n 
 		space complexity:basen on question in general O(1)
 			
 */
