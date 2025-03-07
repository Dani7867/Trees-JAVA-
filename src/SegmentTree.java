public class SegmentTree {
    private static  class Node{
        int data;
        int startintv;
        int endintv;
        Node left;
        Node right;

        public Node(int startintv, int endintv) {
            this.startintv = startintv;
            this.endintv = endintv;
        }
    }

    public SegmentTree() {
    }

    Node root;

    public SegmentTree(int[] arr){
        this.root = constructTree(arr,0,arr.length - 1);
    }

    private Node constructTree(int[] arr, int start, int end) {
        if(start == end){
            Node leaf = new Node(start,end);
            leaf.data = arr[start];
            return leaf;
        }

        Node node = new Node(start,end);

        int mid = start + (end - start) / 2;
        node.left = constructTree(arr,start,mid);
        node.right =constructTree(arr,mid + 1,end);

        node.data = node.left.data + node.right.data;
        return node;
    }

    public void display(){
        display(root);
    }
    private void display(Node node){
        String str = "";

        if(node.left != null){
            str = str + "Interval=[" + node.left.startintv + "-" + node.left.endintv + "] and data : " + node.left.data + " => ";
        }else{
            str += "No left child";
        }

        str = str + "Interval=[" + node.startintv + "-" + node.endintv + "] and data : " + node.data + " <= ";

        if(node.right != null){
            str = str + "Interval=[" + node.right.startintv + "-" + node.right.endintv + "] and data : " + node.right.data ;
        }else{
            str += "No right child";
        }

        System.out.println(str + "\n");
        //recursion call
        if(node.left!= null){
            display(node.left);
        }

        if(node.right!=null){
            display(node.right);
        }
    }


    public int query(int qsi ,int qei){
        return query(root,qsi,qei);
    }

    private int query(Node node , int qsi , int qei){
        if(node.startintv >= qsi && node.endintv <= qei){
            // node in range
            return node.data;
        } else if (node.startintv > qei || node.endintv < qsi) {
            return 0; // node outside
        }else{
            return query(node.left,qsi,qei) + query(node.right,qsi,qei);
        }
    }

    public void update(int index , int value){
        root.data = update(root,index,value);
    }

    private  int update(Node node,int index , int value){
        if(index >= node.startintv && index <= node.endintv){
            if(index == node.startintv && index == node.endintv ){
                node.data = value;
                return node.data;
            }else{
                int leftAns = update(node.left,index,value);
                int rightAns = update(node.right,index,value);
                node.data = leftAns + rightAns;
                return node.data;
            }
        }
        return node.data;
    }
    public static void main(String[] args) {

        int[] nums = {3,8,6,7,2,5,-2,1,9,4};
        SegmentTree tree = new SegmentTree(nums);
        //tree.display();
        System.out.println(tree.query(2,6));


    }


}
