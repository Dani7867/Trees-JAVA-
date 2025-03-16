public class BinaryTreeTilt {
    // TreeNode class definition
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    int tilt = 0;

    public int findTilt(TreeNode root) {
        calSum(root);
        return tilt;
    }

    private int calSum(TreeNode root) {
        if (root == null) return 0;

        int left = calSum(root.left);
        int right = calSum(root.right);

        tilt += Math.abs(left - right);

        return left + right + root.val;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        if(root.val == targetSum && root.left == null && root.right == null) return true;


        return hasPathSum(root.left , targetSum - root.val) || hasPathSum(root.right , targetSum - root.val);
    }
    

    public static void main(String[] args) {
        // Creating a binary tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        BinaryTreeTilt tree = new BinaryTreeTilt();

        System.out.println("The tilt of the tree is: " + tree.findTilt(root));
    }
}

