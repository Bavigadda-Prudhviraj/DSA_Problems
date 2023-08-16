package com.prudhvi.linked_list;

public class PartitionListLeetCode86 {
	/*
	Given the head of a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
	You should preserve the original relative order of the nodes in each of the two partitions.
	
	Constraints:
		The number of nodes in the list is in the range [0, 200].
		-100 <= Node.val <= 100
		-200 <= x <= 200
	
	Input: head = [1,4,3,2,5,2], x = 3
	Output: [1,2,2,4,3,5]
	 */
	public static void main(String[] args) {
		int[] arr= {1,4,3,2,5,2};
		ListNode head=ArrToLL.createLinkedListFromArray(arr);
		int x=3;
		ListNode headNode=partitionLinkedList(head,x);
		ListNode.printLinkedList(headNode);

	}
	/*
	The code is an implementation of partitioning a linked list such that all nodes with values less than a given value 'x' appear before nodes with values greater than or equal to 'x'. 
	The code creates two separate linked lists: one for nodes with values less than 'x', and another for nodes with values greater than or equal to 'x'. 
	It then combines these two lists and returns the resulting partitioned linked list.
	
	Time Complexity:
			The code iterates through each node of the input linked list once. Each node is inserted into either the minTemp partition or the maxTemp partition.
			Therefore, the time complexity of this function is O(N), where N is the number of nodes in the linked list.

	Space Complexity:
			The space complexity is determined by the additional nodes created to represent the two partitions (minHead and maxHead), as well as the pointers used in the process.
			Overall, the space complexity is O(1), as the additional space used is constant regardless of the size of the input linked list.
	 */
	private static ListNode partitionLinkedList(ListNode head, int x) {
		// Create two new linked list heads to hold smaller and greater elements
		ListNode minHead=new ListNode();
		ListNode maxHead=new ListNode();
		ListNode minTemp=minHead;
		ListNode maxTemp=maxHead;
		// Traverse the original linked list
		while(head!=null){
			if(head.value<x) {
				minTemp.next=head; // Add elements smaller than x to the "min" list
				minTemp=minTemp.next;
			}else {
				maxTemp.next=head;//If the value of the current node is greater than or equal to x, it is added to the "max" list.
				maxTemp=maxTemp.next;
			}
			head=head.next;
		}
		//After processing all nodes, the last node of the "min" list (minTemp) is connected to the first node of the "max" list (maxHead.next), effectively merging the two lists.
		minTemp.next=maxHead.next;
		maxTemp.next=null;//The next pointer of the last node in the "max" list (maxTemp) is set to null to mark the end of the list.
		return minHead.next;//The function returns the next node of the minHead, which is the head of the partitioned linked list.
	}

}
