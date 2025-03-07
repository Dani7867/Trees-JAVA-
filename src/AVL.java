import java.util.Random;

public class AVL {

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

    public AVL() {}

    public int height() {
        return height(root);
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
            return node; // Ignore duplicates
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;

        return rotate(node);
    }

    private Node rotate(Node node) {
        int balanceFactor = height(node.left) - height(node.right);

        if (balanceFactor > 1) { // Left heavy
            if (height(node.left.left) >= height(node.left.right)) {
                return rightRotate(node);
            } else {
                node.left = leftRotate(node.left);
                return rightRotate(node);
            }
        }

        if (balanceFactor < -1) { // Right heavy
            if (height(node.right.right) >= height(node.right.left)) {
                return leftRotate(node);
            } else {
                node.right = rightRotate(node.right);
                return leftRotate(node);
            }
        }

        return node; // Already balanced
    }

    private Node rightRotate(Node node) {
        Node c = node.left;

        node.left = c.right;
        c.right = node;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        c.height = Math.max(height(c.left), height(c.right)) + 1;
        return c;
    }

    private Node leftRotate(Node node) {
        Node p = node.right;
        Node t = p.left;

        p.left = node;
        node.right = t;

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        p.height = Math.max(height(p.left), height(p.right)) + 1;
        return p;
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
        display(root, "");
    }

    private void display(Node node, String indent) {
        if (node == null) {
            return;
        }
        System.out.println(indent + node.value);
        display(node.left, indent + "   L--> ");
        display(node.right, indent + "   R--> ");
    }

    public static void main(String[] args) {
        AVL tree = new AVL();
        Random rand = new Random();

        for (int i = 0; i < 1000; i++) {
            tree.insert(i); // Insert random values
        }

        System.out.println("Tree Balanced: " + tree.balanced());
        System.out.println("Tree Height: " + tree.height());
       // tree.display();
    }
}
