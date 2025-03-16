import java.util.*;

public class DFS {
    int diameter = 0;
    int maxSum = Integer.MIN_VALUE;

    // Calculate the diameter of a binary tree
    public int diameterOfBinaryTree(TreeNode root) {
        height(root);
        return diameter;
    }

    private int height(TreeNode root) {
        if (root == null) return 0;

        int left = height(root.left);
        int right = height(root.right);

        diameter = Math.max(diameter, left + right);

        return Math.max(left, right) + 1;
    }

    // Invert the binary tree
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    // Flatten the binary tree into a linked list
    public void flatten(TreeNode root) {
        if (root == null) return;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode current = stack.pop();
            if (current.right != null) stack.push(current.right);
            if (current.left != null) stack.push(current.left);
            if (!stack.isEmpty()) current.right = stack.peek();
            current.left = null;
        }
    }

    // Validate if it's a Binary Search Tree
    public boolean isValidBST(TreeNode root) {
        return isBST(root, null, null);
    }

    private boolean isBST(TreeNode root, Integer low, Integer high) {
        if (root == null) return true;
        if (low != null && root.val <= low || high != null && root.val >= high) return false;
        return isBST(root.left, low, root.val) && isBST(root.right, root.val, high);
    }

    // Find the Lowest Common Ancestor in a binary tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return (left != null && right != null) ? root : (left != null ? left : right);
    }

    // Sum of Root to Leaf binary numbers
    public int sumRootToLeaf(TreeNode root) {
        return sumHelper(root, 0);
    }

    private int sumHelper(TreeNode node, int sum) {
        if (node == null) return 0;
        sum = (sum << 1) | node.val;
        if (node.left == null && node.right == null) return sum;
        return sumHelper(node.left, sum) + sumHelper(node.right, sum);
    }

    // Check if a path exists with given sequence
    public boolean checkPath(TreeNode root, int[] arr) {
        return checkPathHelper(root, arr, 0);
    }

    private boolean checkPathHelper(TreeNode node, int[] arr, int index) {
        if (node == null || index >= arr.length || node.val != arr[index]) return false;
        if (node.left == null && node.right == null && index == arr.length - 1) return true;
        return checkPathHelper(node.left, arr, index + 1) || checkPathHelper(node.right, arr, index + 1);
    }

    // Print tree in level order
    public void printTree(TreeNode root) {
        if (root == null) {
            System.out.println("Tree is empty.");
            return;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DFS tree = new DFS();

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2, new TreeNode(4), new TreeNode(5));
        root.right = new TreeNode(3);

        System.out.print("Original Tree (Level Order): ");
        tree.printTree(root);

        System.out.println("Diameter: " + tree.diameterOfBinaryTree(root));

        root = tree.invertTree(root);
        System.out.print("Inverted Tree (Level Order): ");
        tree.printTree(root);

        tree.flatten(root);
        System.out.print("Flattened Tree (Right Skewed): ");
        tree.printTree(root);

        System.out.println("Is Valid BST: " + tree.isValidBST(root));

        TreeNode lca = tree.lowestCommonAncestor(root, root.left, root.right);
        System.out.println("Lowest Common Ancestor: " + (lca != null ? lca.val : "None"));

        System.out.println("Sum of Root to Leaf Paths (Binary): " + tree.sumRootToLeaf(root));

        int[] path = {1, 3};
        System.out.println("Path " + Arrays.toString(path) + " exists: " + tree.checkPath(root, path));
    }

    private static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
