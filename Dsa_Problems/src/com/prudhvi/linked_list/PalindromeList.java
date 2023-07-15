package com.prudhvi.linked_list;

public class PalindromeList {
	/*
	Problem Description
		Given a singly linked list A, determine if it's a palindrome.
		Return 1 or 0, denoting if it's a palindrome or not, respectively.
	Problem Constraints
		1 <= |A| <= 105
	 */

	public static void main(String[] args) {
		int[] arr= {1,2,3,4,5,4,3,2,1};
		ListNode head=ArrToLL.createLinkedListFromArray(arr);
		int answer=checkPalindrome(head);
		System.out.println(answer);

	}
	public static int checkPalindrome(ListNode head) {
		int len=ListNode.size(head);
		ListNode tempNode=head;
		for(int i=1;i<=(len-1)/2;i++) {
			tempNode=tempNode.next;
		}
		ListNode halfListNode=ListNode.reverseLinkedList(tempNode);
		for(int i=0;i<=(len-1)/2;i++) {
			if(head.value!=halfListNode.value) {
				return 0;
			}
			head=head.next;
			halfListNode=halfListNode.next;
		}
		return 1;
	}
	
	//other way
	public int lPalin(ListNode A) {
        ListNode slow_ptr = A, fast_ptr = A;
        ListNode second_half, prev_of_slow_ptr = A;
        ListNode midnode = null; // To handle odd size list
        int res = 1; // initialize result
        if (A != null && A.next != null) {
            /* Get the middle of the list. Move slow_ptr by 1
               and fast_ptrr by 2, slow_ptr will have the middle
               ListNode */
            while (fast_ptr != null && fast_ptr.next != null) {
                fast_ptr = fast_ptr.next.next;

                /*We need previous of the slow_ptr for
                  linked lists  with odd elements */
                prev_of_slow_ptr = slow_ptr;
                slow_ptr = slow_ptr.next;
            }
            /* fast_ptr would become NULL when there are even elements in list. 
               And not NULL for odd elements. We need to skip the middle ListNode 
               for odd case and store it somewhere so that we can restore the
               original list*/
            if (fast_ptr != null) {
                midnode = slow_ptr;
                slow_ptr = slow_ptr.next;
            }
            // Now reverse the second half and compare it with first half
            second_half = slow_ptr;
            prev_of_slow_ptr.next = null; // NULL terminate first half
            second_half = reverse(second_half); // Reverse the second half
            res = compareLists(A, second_half); // compare
        }
        return res;
    }
    public ListNode reverse(ListNode head_ref) {
        ListNode prev = null;
        ListNode current = head_ref;
        ListNode next;
        while (current != null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    /* Function to check if two input lists have same val*/
    int compareLists(ListNode head1, ListNode head2) {
        ListNode temp1 = head1;
        ListNode temp2 = head2;
        while (temp1 != null && temp2 != null) {
            if (temp1.value == temp2.value) {
                temp1 = temp1.next;
                temp2 = temp2.next;
            } else return 0;
        }
        /* Both are empty reurn 1*/
        if (temp1 == null && temp2 == null)
            return 1;
        /* Will reach here when one is NULL
           and other is not */
        return 0;
    }

}
