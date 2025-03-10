import java.util.*;

public class BFS {
    public static class Node {
      int val;
      Node left;
      Node right;
      Node() {}
      Node(int val) { this.val = val; }
      Node(int val, Node left, Node right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
       }
    public static void main(String[] args) {

        Node c3 = new Node(2);
        Node c4 = new Node(1);
        Node c5 = new Node(2, c3, c4);
        Node c6 = new Node(5);
        Node c1 = new Node(22, c5, c6);
        Node c2 = new Node(3);
        Node root = new Node(1, c1, c2);

        System.out.println(levelOrder(root));
        levelO(root);
        System.out.println(averageOfLevels(root));
        System.out.println(levelS(root,51));
        System.out.println(zigZagTraversal(root));
        System.out.println(levelOrderBottom(root));
        System.out.println(isCousins(root, 2, 5));
        System.out.println(isSymmetric(root));

    }
    public static List<List<Integer>> levelOrder(Node root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                assert currentNode != null;
                currentLevel.add(currentNode.val);
                if(currentNode.left!=null){ queue.offer(currentNode.left);}
                if(currentNode.right!=null){ queue.offer(currentNode.right);}
            }
            result.add(currentLevel);
        }
        return result;
    }

    public static void levelO(Node root){
        if(root == null){return;}
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int lvlSize = q.size();
            for(int i = 0 ; i<lvlSize ; i++){
                Node curr = q.poll();
                System.out.print(curr.val + " -> ");
                if(curr.left!=null){q.offer(curr.left);}
                if(curr.right!=null){q.offer(curr.right);}
            }
        }
        System.out.print(" null\n");
    }

    public static List<Double> averageOfLevels(Node root){
       List<Double> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<Node> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()){
            int lvlSize = q.size();
            double lvlavg = 0;
            for(int i = 0 ; i<lvlSize ; i++){
                Node curr = q.poll();
                lvlavg += curr.val;
                if(curr.left!=null){q.offer(curr.left);}
                if(curr.right!=null){q.offer(curr.right);}
            }
            lvlavg = lvlavg / lvlSize;
            result.add(lvlavg);
        }
        return result;
    }

    public static int levelS(Node root, int t) {
        if (root == null) {
            return -1;
        }

        Queue<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (curr.left != null) {
                q.offer(curr.left);
            }
            if (curr.right != null) {
                q.offer(curr.right);
            }
            if (curr.val == t) {
                break;
            }
        }
        if (q.isEmpty()) {
            return -1;
        }
        Node successor = q.peek();
        return successor.val;
    }

    public static List<List<Integer>> zigZagTraversal(Node root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) {
            return list;
        }

        Deque<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean reverse = false;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                if (!reverse) {
                    Node currentNode = queue.pollFirst();
                    assert currentNode != null;
                    currentLevel.add(currentNode.val);
                    if (currentNode.left != null) {
                        queue.addLast(currentNode.left);
                    }
                    if (currentNode.right != null) {
                        queue.addLast(currentNode.right);
                    }
                } else {
                    Node currentNode = queue.pollLast();
                    assert currentNode != null;
                    currentLevel.add(currentNode.val);
                    if (currentNode.right != null) {
                        queue.addFirst(currentNode.right);
                    }
                    if (currentNode.left != null) {
                        queue.addFirst(currentNode.left);
                    }
                }

            }
            reverse = !reverse;
            list.add(currentLevel);
        }
        return list;
    }
    public static List<List<Integer>> levelOrderBottom(Node root){
        List<List<Integer>> result = new ArrayList<>();
        if(root == null){
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>(levelSize);
            for (int i = 0; i < levelSize; i++) {
                Node currentNode = queue.poll();
                assert currentNode != null;
                currentLevel.add(currentNode.val);
                if(currentNode.left!=null){ queue.offer(currentNode.left);}
                if(currentNode.right!=null){ queue.offer(currentNode.right);}
            }
            result.add(0,currentLevel);
        }
        return result;

    }

    public static boolean isCousins(Node root, int x, int y) {
        Node xx = findNode(root,x);
        Node yy = findNode(root,y);
        return (level(root,xx,0) == level(root,yy,0)) && (!isSiblings(root,xx,yy));
    }

    private static Node findNode(Node root , int v){
        if(root == null) return null;
        if(root.val == v) return root;
        Node n = findNode(root.left,v);
        if(n!=null) return n;
        return findNode(root.right,v);
    }

    private static int level(Node root , Node node , int l){
        if(root == null) return 0;
        if(root == node) return l;

        int l1 = level(root.left,node,l+1);
        if(l1!=0){
            return l1;
        }


        return level(root.right,node,l+1);
    }

    private static boolean isSiblings(Node root , Node xx , Node yy){

        if(root == null) return false;

        return (root.left == xx && root.right == yy) || (root.left == yy && root.right == xx)
                || isSiblings(root.left,xx,yy) || isSiblings(root.right,xx,yy);
    }

    public static boolean isSymmetric(Node root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(Node p, Node q) {
        if (p == null && q == null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        if (p.val != q.val) {
            return false;
        }
        return isMirror(p.left, q.right) && isMirror(p.right, q.left);
    }

//    public Node connect(Node root){
//        if(root == null){
//            return root;
//        }
//        Node leftMost = root;
//        while(leftMost.left!=null){
//            Node current = leftMost;
//            while(current != null){
//                current.left.next = current.right;
//                if(current.next!=null){
//                    current.right.next = current.next.left;
//                }
//                current = current.next;
//            }
//            leftMost = leftMost.left;
//        }
//        return root;
//    }

//    public Node connect(Node root) {
//        if (root == null) {
//            return root;
//        }
//
//        Node leftMost = root;
//
//        while (leftMost != null) {
//            Node current = leftMost;
//            Node temp = new Node(0);
//            Node dummy = temp;
//
//            while (current != null) {
//                if (current.left != null) {
//                    temp.next = current.left;
//                    temp = temp.next;
//                }
//                if (current.right != null) {
//                    temp.next = current.right;
//                    temp = temp.next;
//                }
//                current = current.next;
//            }
//
//            leftMost = dummy.next;
//        }
//
//        return root;
//    }

}

