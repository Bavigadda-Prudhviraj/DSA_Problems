package com.prudhvi.tries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ContactFinder {
	/*
	Problem Description
		We want to make a custom contacts finder applications as part of our college project. The application must perform two types of operations:
		Type 1: Add name B[i] ,denoted by 0 in Array A where B[i] is a string in array B denoting a contact name. This must store B[i] as a new contact in the application.
		Type 2: Find partial for B[i] ,denoted by 1 in vector A where B[i] is a string in vector B denoting a partial name to search the application for. It must count the number of contacts starting with B[i].
		You have been given sequential add and find operations. You need to perform each operation in order.
		And return as an array of integers, answers for each query of type 2(denoted by 1 in A) .
	Problem Constraints
		1 <= |A| <= 10000
		1 <= |length of strings in B| <= 10
	 */
	public static void main(String[] args) {
		ArrayList<Integer> operation=new ArrayList<>(List.of(0,1));//0, 0, 1, 1
		ArrayList<String> contacts=new ArrayList<>(List.of("abcde", "abc"));//"hack", "hacker", "hac", "hak"
		ArrayList<Integer> noOfContacts=contactFinder(operation,contacts);
		System.out.println(noOfContacts);
	}
	/*
	This implementation provides an efficient way to manage contacts and quickly retrieve contact counts with a given prefix. 
	The Trie structure allows for fast auto completion and searching in contact lists. 
	The time complexity for inserting and counting operations is proportional to the length of the contact name or prefix.
	
	Time Complexity:
				Inserting a contact into the trie takes O(K) time, where K is the length of the contact name.
				Counting the number of contacts with a given prefix also takes O(K) time.
				Processing each operation in the contactFinder function takes linear time proportional to the number of operations.
			Therefore, the overall time complexity of the contact finder function is O(N * K), where N is the number of operations and K is the average length of contact names.
					
	Space Complexity:
				The space complexity is determined by the storage used for the ContactTrie structure and the additional variables.
				The ContactTrie structure stores each character as a child node, which can take up space proportional to the total number of characters in all contact names.
			Therefore, the overall space complexity is O(N * K), where N is the total number of characters and K is the average length of contact names.
	 */
	private static ArrayList<Integer> contactFinder(ArrayList<Integer> operation, ArrayList<String> contacts) {
		ArrayList<Integer> answer=new ArrayList<>();// to store the results.
		ContactTrie contactTrie=new ContactTrie();
		// Loop through the operations and perform the requested actions
		for(int i=0;i<operation.size();i++) {
			if(operation.get(i)==0) {// Insert contact
				insertContactIntoTrie(contactTrie,contacts.get(i));
			}
			else if (operation.get(i)==1) {// Count contacts with prefix
				int count=countOfContacts(contactTrie,contacts.get(i));
				answer.add(count);// Store the count in the answer list
			}
		}
		
		return answer;
	}
	private static void insertContactIntoTrie(ContactTrie contactTrie, String name) {
		// Traverse the Trie while updating letter frequencies
		for(int i=0;i<name.length();i++) {
			char ch=name.charAt(i);
			if(!contactTrie.child.containsKey(ch)) {
				contactTrie.child.put(ch,new ContactTrie());// Create a new Trie node if not exists
			}
			contactTrie=contactTrie.child.get(ch);// Move to the next Trie node
			contactTrie.letterFrequency++;// Update the letter frequency
		}
		contactTrie.isEnd=true;// Mark the end of the contact is true
	}
	private static int countOfContacts(ContactTrie contactTrie, String name) {
		// Traverse the Trie to find the prefix and return the letter frequency at the last Trie node
		for(int i=0;i<name.length();i++) {
			char ch=name.charAt(i);
			if(!contactTrie.child.containsKey(ch)) {
				return 0;// Prefix not found, return 0
			}
			contactTrie=contactTrie.child.get(ch);// Move to the next Trie node
		}
		return contactTrie.letterFrequency;// Return the count of contacts with the given prefix
	}

}
//Definition of the ContactTrie class
class ContactTrie{
	HashMap<Character, ContactTrie> child;
	boolean isEnd;
	int letterFrequency;
	public ContactTrie() {
		child=new HashMap<>();
		isEnd=false;
		letterFrequency=0;
	}
	
}
