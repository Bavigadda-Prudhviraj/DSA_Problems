package com.prudhvi.trees;
/*
	Trees Notes:-
	
		Tree data structures are hierarchical data structures that consist of nodes connected by edges. 
		They are widely used in computer science, data analysis, and many other fields due to their ability to efficiently represent relationships and hierarchies. 
		Here are some important points about tree data structures:
				1.Hierarchy and Parent-Child Relationships: 
						Trees represent hierarchical relationships, where each node (except the root) has a parent node, and nodes directly connected to a parent are called its children.
				2.Root Node: 
						The topmost node in a tree is called the root node. 
						It is the starting point for traversing the tree, and there is only one root in a tree.
				3.Leaf Nodes: 
						Nodes that do not have any children are called leaf nodes or terminal nodes. 
						They are at the bottom of the hierarchy.
				4.Internal Nodes: 
						Nodes that have one or more children are called internal nodes. 
						These nodes are neither the root nor the leaf nodes.
				5.Depth and Height: 
						The depth of a node is the number of edges from the root to that node. 
						The height of a node is the number of edges on the longest path from that node to a leaf. 
						The height of the tree is the height of the root node.
				6.Binary Trees: 
						A binary tree is a type of tree in which each node can have at most two children, often referred to as the left child and the right child.
				7.Binary Search Trees (BST): 
						A binary search tree is a binary tree with the additional property that for each node, all nodes in its left subtree have values less than the node's value, and all nodes in its right subtree have values greater than the node's value. 
						BSTs allow for efficient searching, insertion, and deletion operations.
				8.Balanced Trees: 
						A balanced tree is a tree in which the difference in height between the left and right subtrees of any node is limited, ensuring that the tree remains relatively balanced. 
						Balanced trees, like AVL trees and Red-Black trees, provide faster access, insertion, and deletion operations.
				9.Traversal Algorithms: 
						There are several ways to traverse a tree, including In-order, Pre-order, Post-order, Level-order, Breadth-First Search (BFS), and Depth-First Search (DFS) algorithms.
				10.Common Applications: 
						Trees find applications in various areas, such as file systems, hierarchical data representation, expression evaluation, parsing, network routing, decision-making algorithms, and organizing data in databases.
				11.Tree Operations: 
						Common operations on trees include searching for a specific node, inserting new nodes, deleting nodes, finding the minimum or maximum value, and balancing the tree to ensure optimal performance.
				12.Tree Representations: 
						Trees can be represented using linked structures (nodes with references to their children) or array-based representations (e.g., heap). 
						Each representation has its advantages and use cases.
				13.Trie (Prefix Tree): A trie is a tree-like data structure often used for storing a dynamic set of strings, where each node represents a common prefix of multiple strings.

		Understanding trees and their properties is essential for designing efficient algorithms, data organization, and solving problems in various domains. 
		They provide a fundamental building block for many advanced data structures and algorithms used in computer science.
		
		Height of a Node:
				The height of a node in a tree is the length of the longest path from that node to a leaf node (a node with no children). 
				In other words, it represents the number of edges in the longest downward path from the node to a leaf. 
				Here are some key points about the height of a node:
						1.Root Node: 
							The height of the root node is the height of the entire tree since it is the starting point for the longest path to any leaf. 
						  	The height of the root node defines the overall height of the tree.
						2.Leaf Nodes: 
							Leaf nodes have a height of 0 because there are no edges to traverse to reach a leaf from itself.
						3.Internal Nodes: 
							The height of an internal node is one more than the maximum height of its children. 
							If a node has only one child (either left or right child), its height is one more than the height of that child.
						4.Height of the Tree: 
							The height of the tree is equal to the height of the root node. 
							It represents the maximum distance from the root node to any leaf in the tree. 
							A tree with just one node (the root node) has a height of 0.

		Depth of a Node:
				The depth of a node in a tree is the length of the path from the root node to that node. 
				In other words, it represents the number of edges in the path from the root to the node. 
				Here are some key points about the depth of a node:
					1.Root Node: 
						The depth of the root node is always 0 since it is the topmost node and there are no edges to traverse from the root to itself.
					2.Leaf Nodes: 
						The depth of a leaf node is equal to the height of the tree since it represents the length of the longest path from the root to any leaf.
					3.Internal Nodes: 
						The depth of an internal node is determined by the number of edges needed to traverse from the root to that node.
	
		Relationship between Depth and Height: 
				For a binary tree, the depth of a node can be calculated as the height of the root node minus the height of the node itself. 
				This relationship holds because the depth represents the upward traversal from the node to the root.
				
		
		Tree traversals
				Tree traversal algorithms are methods used to visit all nodes in a tree data structure in a specific order. 
				There are several different traversal algorithms, each serving unique purposes. 
				Here, I'll explain in more detail about three common tree traversal algorithms:
					1.Pre-order Traversal(DLR):
							i.In this traversal, we visit the current node first, then the nodes in the left subtree, and finally the nodes in the right subtree.
							ii.Preorder traversal is useful for creating a copy of a tree and making prefix expressions from an expression tree.
							iii.Preorder traversal is denoted as (root, left, right).
					2.In-order Traversal(LDR):
							i.In this traversal, we visit the nodes in the left subtree first, then the current node, and finally the nodes in the right subtree.
							ii.For Binary Search Trees (BST), this traversal visits nodes in ascending order, making it useful for getting elements in sorted order.
							iii.Inorder traversal is denoted as (left, root, right).
					
					3.Post-order Traversal(LDR):
							i.In this traversal, we visit the nodes in the left subtree first, then the nodes in the right subtree, and finally the current node.
							ii.Postorder traversal is often used to delete a tree from the memory.
							iii.Postorder traversal is denoted as (left, right, root).
					Let's illustrate these traversals with an example of a binary tree:
										       1
										      / \
										     2   3
										    / \ / \
										   4  5 6  7
					1.Pre-order Traversal (1 2 4 5 3 6 7):
							Visit root: 1
							Traverse left subtree: (2 4 5)
					  		Traverse right subtree: (3 6 7)
					2.In-order Traversal (1 2 4 5 3 6 7):
							Traverse left subtree: (4 2 5)
							Visit root: 1
							Traverse right subtree: (6 3 7)
					3.Post-order Traversal (4 5 2 6 7 3 1):
							Traverse left subtree: (4 5 2)
							Traverse right subtree: (6 7 3)
							Visit root: 1
			
			It's important to note that different traversal algorithms have different applications and uses based on the specific problem or task at hand. 
			In addition to these classic traversals, there are other more specialized traversals, such as level-order traversal (also known as Breadth-First Search), Morris Traversal (in-order without recursion or stack), Zigzag Traversal, etc. 
			Each traversal serves a unique purpose and can be leveraged to solve various tree-related problems efficiently.
			
	print the border of a binary tree without explicitly using tree traversal algorithms:
	
			To print the border of a binary tree without explicitly using tree traversal algorithms, you can traverse the border of the tree by moving along its boundary using various techniques. 
			Here are the explanations for printing the tree border without traversal:
				1.Pre-order Approach:
						The pre-order approach involves printing the root and then moving along the left boundary, printing the leftmost nodes. 
						Next, you print the leaf nodes and finally move along the right boundary, printing the rightmost nodes.
					Steps:
						1.Print the root node.
						2.Move leftwards, printing all the leftmost nodes until you reach a leaf node (or null).
						3.Print all the leaf nodes from left to right.
						4.Move rightwards, printing all the rightmost nodes until you reach a leaf node (or null).
				2.In-order Approach:
						The in-order approach involves first moving along the left boundary and printing the leftmost nodes. 
						Then print all the leaf nodes from left to right. Finally, move along the right boundary, printing the rightmost nodes.
					Steps:
						1.Move leftwards, printing all the leftmost nodes until you reach a leaf node (or null).
						2.Print all the leaf nodes from left to right.
						3.Move rightwards, printing all the rightmost nodes until you reach a leaf node (or null).
				3.Post-order Approach:
						The post-order approach involves first moving along the left boundary, printing the leftmost nodes. 
						Then print all the leaf nodes from left to right. Finally, move along the right boundary, printing the rightmost nodes.
					Steps:
						1.Move leftwards, printing all the leftmost nodes until you reach a leaf node (or null).
						2.Print all the leaf nodes from left to right.
						3.Move rightwards, printing all the rightmost nodes until you reach a leaf node (or null).
						4.Print the root node.
		By following these steps, you can effectively print the border of a binary tree without using explicit tree traversal algorithms like Preorder, Inorder, or Postorder. 
		The key is to traverse the boundary of the tree and print the nodes in the correct order. 
		Note that this approach requires careful handling of the boundary conditions to avoid duplication of nodes in the output.
		
		
	
	
	
	
	
	
	
*/