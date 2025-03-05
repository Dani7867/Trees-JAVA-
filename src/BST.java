public class BST {

    public class Node {
        private int value;
        private Node left;
        private Node right;
        private int height;

        public Node(int value) {
            this.value = value;
            this.height = 0; // Ensure height is properly initialized
        }

        public int getValue() {
            return value;
        }
    }

    private Node root;

    public BST() {
    }

    public int height(Node node) {
        return (node == null) ? -1 : node.height;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(int value) {
        root = insert(value, root);
    }

    private Node insert(int value, Node node) {
        if (node == null) {
            return new Node(value);
        }

        if (value < node.value) {
            node.left = insert(value, node.left);
        } else if (value > node.value) {
            node.right = insert(value, node.right);
        } else {
            // Duplicate values are ignored
            return node;
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    public void populate(int[] nums) {
        for (int value : nums) {
            insert(value);
        }
    }

    public void populateSorted(int[] nums) {
        populateSorted(nums, 0, nums.length - 1);
    }

    private void populateSorted(int[] nums, int start, int end) {
        if (start > end) {  // Fix: start >= end changed to start > end
            return;
        }

        int mid = start + (end - start) / 2;

        insert(nums[mid]);
        populateSorted(nums, start, mid - 1);
        populateSorted(nums, mid + 1, end);
    }

    public boolean balanced() {
        return balanced(root);
    }

    private boolean balanced(Node node) {
        if (node == null) {
            return true;
        }
        int leftHeight = height(node.left);
        int rightHeight = height(node.right);

        return Math.abs(leftHeight - rightHeight) <= 1 && balanced(node.left) && balanced(node.right);
    }

    public void display() {
        display(root, "Root Node: ");
    }

    private void display(Node node, String details) {
        if (node == null) {
            return;
        }
        System.out.println(details + node.value);
        display(node.left, "Left child of " + node.value + " : ");
        display(node.right, "Right child of " + node.value + " : ");
    }

    public static void main(String[] args) {
        BST tree = new BST();

        int[] values = {10, 5, 15, 3, 7, 18};
        tree.populate(values);

        System.out.println("BST after inserting values:");
        tree.display();

        System.out.println("\nBalanced? " + tree.balanced());

        int[] sortedValues = {1, 2, 3, 4, 5, 6, 7};
        BST balancedTree = new BST();
        balancedTree.populateSorted(sortedValues);

        System.out.println("\nBST from sorted array:");
        balancedTree.display();

        System.out.println("\nBalanced? " + balancedTree.balanced());
    }
}
