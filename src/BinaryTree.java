import java.util.Scanner;

public class BinaryTree {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BinaryTree tree = new BinaryTree();
        tree.populate(scanner);
        tree.display();
        tree.preorder();
        tree.postorder();
        tree.inorder();

    }
    private static class Node{
        int val;
        Node left;
        Node right;

        Node(int val ){
            this.val = val;
        }
    }

    Node root;

    public void populate(Scanner scanner){
        System.out.println("Enter root node");
        root = new Node(scanner.nextInt());
        populate(scanner,root);
    }

    private void populate(Scanner scanner , Node node){
        System.out.println("Do you want to enter left of " + node.val);
        boolean left = scanner.nextBoolean();
        if(left){
            System.out.println("Enter left val of "+ node.val);
            node.left = new Node(scanner.nextInt());
            populate(scanner,node.left);
        }

        System.out.println("Do you want to enter right of " + node.val);
        boolean right = scanner.nextBoolean();
        if(right){
            System.out.println("Enter right val of "+ node.val);
            node.right = new Node(scanner.nextInt());
            populate(scanner,node.right);
        }
    }

    public void display() {
        System.out.println("Binary Tree (Preorder Traversal):");
        display(root);
        System.out.println();
        treeDisplay(root,0);
    }

    private void treeDisplay(Node node , int level) {
        if(node == null) return;

        treeDisplay(node.right,level+1);
        if(level!=0){
            for (int i = 0; i < level - 1; i++) {
                System.out.print("|\t\t");
            }
            System.out.println("|---------> " + node.val);
        }else{
            System.out.println(node.val);
        }
        treeDisplay(node.left,level+1);
    }

    private void display(Node node) {
        if (node == null) return;
        System.out.print(node.val + " ");
        display(node.left);
        display(node.right);
    }

    public void preorder(){
        preorder(root);
        System.out.println();
    }

    private void preorder(Node node){
        if(node == null){
            return;
        }
        System.out.print(node.val + " ");
        preorder(node.left);
        preorder(node.right);
    }

    public void postorder(){
        postorder(root);
    }

    private void postorder(Node node){
        if(node == null){
            return;
        }
        postorder(node.left);
        postorder(node.right);
        System.out.print(node.val + " ");
    }

    public void inorder(){
        inorder(root);
    }

    private void inorder(Node node){
        if(node == null){
            return;
        }
        inorder(node.left);
        System.out.print(node.val + " ");
        inorder(node.right);
    }

}


