package com.prudhvi.trees;

import java.util.HashMap;

class Solution {

    // HashMap to store the depth of each node (Key: node value, Value: depth)
    private HashMap<Integer, Integer> nodeDepthMap = new HashMap<>();

    // Variable to track the maximum depth of the tree
    private int maxDepth = Integer.MIN_VALUE;

    /**
     * Problem: Find the Lowest Common Ancestor (LCA) of the deepest leaves.
     * 
     * Approach:
     * 1. Compute the depth of each node and store it in a HashMap.
     * 2. Identify the maximum depth present in the tree.
     * 3. Find the LCA of the nodes that are at the maximum depth.
     *
     * Time Complexity: O(N)  
     * - We traverse the entire tree twice:
     *   1. First traversal to compute depths (O(N)).
     *   2. Second traversal to find the LCA (O(N)).
     * - So, overall complexity is **O(N)**.
     *
     * Space Complexity: O(N)  
     * - We store the depth of each node in a HashMap (O(N)).
     * - The recursion stack in the worst case (for a skewed tree) could be O(N).
     * - So, the overall space complexity is **O(N)**.
     *
     * @param root The root node of the binary tree
     * @return The LCA of the deepest leaves
     */
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        // Step 1: Compute depth of each node and store in a HashMap
        computeNodeDepths(root, 0);

        // Step 2: Find and return the LCA of the deepest leaves
        return findDeepestLeavesLCA(root);
    }

    /**
     * Step 1: Computes the depth of each node and stores it in nodeDepthMap.
     * Also updates maxDepth with the deepest level found in the tree.
     *
     * @param node         Current node being processed
     * @param currentDepth Depth of the current node in the tree
     */
    private void computeNodeDepths(TreeNode node, int currentDepth) {
        // Base case: If the node is null, stop recursion
        if (node == null) return;

        // Store the depth of the current node in the HashMap
        nodeDepthMap.put(node.val, currentDepth);

        // Update maxDepth if a deeper node is found
        maxDepth = Math.max(maxDepth, currentDepth);

        // Recursively compute depth for left subtree
        computeNodeDepths(node.left, currentDepth + 1);

        // Recursively compute depth for right subtree
        computeNodeDepths(node.right, currentDepth + 1);
    }

    /**
     * Step 2: Finds the Lowest Common Ancestor (LCA) of the deepest leaves.
     *
     * Logic:
     * - If the current node itself is a deepest leaf, return it.
     * - Recursively check the left and right subtrees:
     *   - If both left and right subtrees contain deepest leaves, current node is the LCA.
     *   - If only one subtree contains the deepest leaves, return that subtree's LCA.
     *
     * @param node Current node being processed
     * @return The LCA node of the deepest leaves
     */
    private TreeNode findDeepestLeavesLCA(TreeNode node) {
        // Base case: If the node is null, return null
        if (node == null) return null;

        // If the current node is one of the deepest leaves, return it
        if (nodeDepthMap.get(node.val) == maxDepth) return node;

        // Recursively find LCA in the left subtree
        TreeNode leftSubtreeLCA = findDeepestLeavesLCA(node.left);

        // Recursively find LCA in the right subtree
        TreeNode rightSubtreeLCA = findDeepestLeavesLCA(node.right);

        // If both left and right children contain deepest leaves, return the current node as LCA
        if (leftSubtreeLCA != null && rightSubtreeLCA != null) {
            return node;
        }

        // If only one subtree contains the deepest leaves, return the non-null LCA
        return (leftSubtreeLCA != null) ? leftSubtreeLCA : rightSubtreeLCA;
    }
    
    public static void main(String[] args) {
		
	}
    public TreeNode lcaDeepestLeavesOptimized(TreeNode root) {
    	Pair lcaPair = deepestLeave(0,root);
    	return lcaPair.root;
        
    }
	private Pair deepestLeave(int i, TreeNode root) {
		if(root == null) {
			return new Pair(0, root);
		}
		Pair leftPair = deepestLeave(i + 1, root.left);
		Pair rightPair = deepestLeave(i + 1, root.right);
		if(leftPair.currentDepth == rightPair.currentDepth)
			return new Pair(leftPair.currentDepth + 1, root);
		return leftPair.currentDepth > rightPair.currentDepth ? 
				new Pair(leftPair.currentDepth + 1, leftPair.root) :  
					new Pair(rightPair.currentDepth + 1, rightPair.root);
	}
	class Pair{
        int currentDepth;
        TreeNode root;
        public Pair(int currentDepth,TreeNode root){
            this.currentDepth = currentDepth;
            this.root = root;
        }
    }
    
}
class Solution2 {
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        // Find the Lowest Common Ancestor (LCA) of the deepest leaves
        DepthNodePair result = findDeepestLCA(0, root);
        return result.node;
    }

    /**
     * Recursive function to determine the LCA of the deepest leaves.
     * 
     * @param depth - Current depth in the tree.
     * @param node - The current node being processed.
     * @return DepthNodePair - A pair containing the depth and the potential LCA.
     * 
     * Time Complexity: O(N), where N is the number of nodes in the tree.
     * - Each node is visited once.
     * - We perform constant-time operations for each node.
     * 
     * Space Complexity: O(H), where H is the height of the tree.
     * - Recursion stack uses O(H) space.
     * - In the worst case (skewed tree), H = O(N).
     * - In a balanced tree, H = O(log N).
     */
    private DepthNodePair findDeepestLCA(int depth, TreeNode node) {
        // Base case: If node is null, return depth 0 and null as LCA
        if (node == null) 
            return new DepthNodePair(0, null);
        
        // Recursively compute depth for left and right subtrees
        DepthNodePair leftResult = findDeepestLCA(depth + 1, node.left);
        DepthNodePair rightResult = findDeepestLCA(depth + 1, node.right);

        /**
         * If left and right subtrees have the same depth, the current node is the LCA
         * Explanation:
         * - Both left and right reach the same maximum depth.
         * - This means the current node is the lowest common ancestor.
         */
        if (leftResult.depth == rightResult.depth) 
            return new DepthNodePair(leftResult.depth + 1, node);
        
        /**
         * If one subtree is deeper, return its deeper node as LCA.
         * - The subtree with the greater depth is the correct LCA.
         */
        return leftResult.depth > rightResult.depth 
                ? new DepthNodePair(leftResult.depth + 1, leftResult.node) 
                : new DepthNodePair(rightResult.depth + 1, rightResult.node);
    }

    /**
     * Helper class to store both depth and node reference.
     * 
     * - `depth`: The maximum depth encountered in the subtree.
     * - `node`: The lowest common ancestor (LCA) at this depth.
     */
    class DepthNodePair {
        int depth;  // Maximum depth found in the subtree
        TreeNode node;  // The node that is the LCA of deepest leaves

        public DepthNodePair(int depth, TreeNode node) {
            this.depth = depth;
            this.node = node;
        }
    }
}

