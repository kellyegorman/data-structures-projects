import java.util.LinkedList;
import java.util.Queue;

/**
 * @author Kelly Gorman
 * class that represents a Binary Search Tree
 */
public class BinarySearchTree {

    //Field that represents a root in an instance of a BST
    private Node root;

    //Private class representing a Node in a BST plus private getter/setters
    private class Node{

        //field that represents element stored in Node
        private int value;

        //field that represents left child
        private Node left;

        //field that represents right child 
        private Node right;

        /**
         * Constructor to instantiate an instance of a Node
         * @param value  element initialized to value stored in Node 
         */
        private Node(int value){
            this.value = value;
            this.left = null;
            this.right = null;
        }


        /**
         * @return value stored in Node
         */
        private int getData() {
            return value;
        }


        /**
         * @return Node that is left child of current node
         */
        private Node getLeftChild() {
            return left;
        }

        /**
         * @return Node that is right child of current Node
         */
        private Node getRightChild() {
            return right;
        }

        /**
         * @param n  Node to be added as left child of current node
         */
        private void setLeftChild(Node n) {
            this.left = n;
        }

        /**
         * @param n Node to be added as right child of current node
         */
        private void setRightChild(Node n) {
            this.right = n;
        }

        /**
         * @param value element to be added as value stored in Node
         */
        private void setData(int value) {
            this.value = value;
        }
    }


    /**
     * @return root node of BST
     */
    private Node getRootNode() {
        return this.root;
    }

    /**
     * @param n Node to be set to root Node of BST
     */
    private void setRootNode(Node n) {
        this.root = n;
    }

    /**
     * Private helper method to traverse thru BST 
     * @param node given Node to search thru
     * @param value element that the method is attempting to find
     * @return value of entry if found, null if not
     */
    private Integer findEntry(Node node, int value){
        Integer result = null;
        //If node is parameter is not null, continue searching
        if(node != null) {
            int nodeEntry = node.getData();
            //found element
            if(value == nodeEntry) {
                result = nodeEntry;
            }
            //value we are searching for is smaller than value in node, so 
            //search left side of root, treating root's left child as new root 
            else if(value < nodeEntry) {
                result = findEntry(node.getLeftChild(), value);
            }
            //value we are searching for it larger than value in node, so
            //search right side of root, treating root's right child as new root
            else {
                result = findEntry(node.getRightChild(), value);
            }
        }
        return result;
    }

    /**
     * @param value value we are searching for
     * @return Integer represeting element if value is found, null if not
     */
    private Integer getEntry(int value) {
        return findEntry(getRootNode(), value);
    }

    /**
     * CONTAINS METHOD
     * @param value Element we are searching for 
     * @return true if element is in BST, false is element is not 
     */
    public boolean contains(int value){
        return getEntry(value) != null;
    }


    /**
     * Finds a place to put a new Node in BST
     * @param node Node to be added once a space is found
     * @param value element to be set as data in new Node
     * @return new Node with value in parameter
     */
    private Node addEntry(Node node, int value){
        //unoccupied space, base case
        if(node == null) {
            return new Node(value);
        }
        //value is less than node's value, got to left side of node
        if(value < node.getData()) {
            node.setLeftChild(addEntry(node.getLeftChild(),value));
        }
        //value is more than or equal to node's value, go to right side of node
        else if (value >= node.getData()) {
            node.setRightChild(addEntry(node.getRightChild(),value));
        }
        return node;
    }

    /**
     * ADD METHOD
     * @param value value to be added to Node designated at location found by addEntry method
     */
    public void add(int value){
        this.setRootNode(addEntry(this.getRootNode(), value));
    }
    
    @Override
    /**
     * TOSTRING METHOD
     * @return String representation of pre-order representation of elements in tree
     */
    public String toString() {
        return depthFirst(root).trim();
    }

    /**
     * Traverses tree in depth first order (preorder) in order to create toString method
     * @param n Node to start traversal from
     * @return String representation of tree in preorder from n to last element
     */
    private String depthFirst(Node n) {
        StringBuilder s = new StringBuilder();
        //base case, no more nonnull elements to traverse
        if(n != null) {
            s.append(" " + n.getData());
            s.append(depthFirst(n.getLeftChild()));
            s.append(depthFirst(n.getRightChild()));
        }
        return s.toString();
    }

    /**
     * BFS METHOD
     * @return String representation of tree in breadfirst order 
     */
    public String bfs() {
        StringBuilder s = new StringBuilder();
        //no elements in tree
        if(getRootNode() == null) {
            return s.toString().trim();
        }
        Queue<Node> nodeQ = new LinkedList<Node>();
        nodeQ.add(root);
        //traverses until the Q is empty, appends each element in Q to SB 
        while(!nodeQ.isEmpty()) {
            Node n = nodeQ.remove();
            s.append(" " + n.getData());
            //appends element in left child
            if(n.getLeftChild() != null) {
                nodeQ.add(n.getLeftChild());
            }
            //appends element in right child
            if(n.getRightChild() != null) {
                nodeQ.add(n.getRightChild());
            }
        }
        return s.toString().trim();
    }

    /**
     * @param n Node to treat as root and start calling at 
     * @return value found at root with no left subtree 
     */
    private int compareChildren(Node n) {
        //return root node if there are no elements in left child
        if (getRootNode().getLeftChild() == null) {
            return getRootNode().getData();
        } 
        //continues calling down left child recursively
        else {
            return compareChildren(getRootNode().getLeftChild());
        }
    }

    /**
     * Searches BST for node w/ value, removes if found, returns null if not
     * @param node node to start searching from 
     * @param value value searching for in Nodes to be removed
     * @return new node if found, null if not found
     */
    private Node removeEntry(Node node, int value) {
        //value isn't in BST
        if(node == null) {
            return null;
        }
        //Remove node
        if(value == node.getData()) {
            //No left or right children
            if(node.getLeftChild() == null && node.getRightChild() == null) {
                return null;
            }
            //has left child but not right child, return left child 
            if(node.getRightChild() == null) {
                return node.getLeftChild();
            }
            //has right child but not left child, return right child
            if(node.getLeftChild() == null) {
                return node.getRightChild();
            }
            //has both left and right child, replace with smallest value in right child, remove
            else {
                int smallerChildData = compareChildren(node.getRightChild());
                node.setData(smallerChildData);
                node.setRightChild(removeEntry(node.getRightChild(), smallerChildData));
                return node;
            }
        }
        //value to remove is less than element in node, repeat remove process in left child
        if (value < node.getData()) {
            node.setLeftChild(removeEntry(node.getLeftChild(), value));
        }
        //value to remove is greater than or equal to element in node, repeat remove process in right child
        else {
            node.setRightChild(removeEntry(node.getRightChild(), value));
        }
        return node;
    }

    /**
     * DELETE METHOD
     * @param value element to be removed if it exists in BST
     * @return true if value was removed, false otherwise
     */
    public boolean delete(int value) {
        //checks if element is in BST
        if(this.contains(value)) {
            //recursively removes/replaces value
            this.setRootNode(removeEntry(getRootNode(), value));
            return true;
        }
        //element is not contained in BST
        else {
            return false;
        }
    }

    /**
     * @param n Node to start counting height from
     * @return int representing height from node
     */
    private int heightRecursive(Node n) {
        //node is empty, height is 0
        if(n == null) {
            return 0;
        }
        //count height going down left child and right child, compare heights 
        //return height that is greater plus one to account from the occupied root
        else {
            int left = heightRecursive(n.getLeftChild());
            int right = heightRecursive(n.getLeftChild());
            if(right >= left) {
                return right+1;
            }
            else {
                return left+1;
            }
        }
    }

    /**
     * HEIGHT METHOD
     * @return int representing the height of the BST -- 0 if root is empty, 1 if root occupied, 2 if children, etc.
     */
    public int height() {
        //starts counting and comparing heights going down left and right subtrees starting at the root
        return this.heightRecursive(getRootNode());
    }

    /**
     * @param myNode node in BST checking if contributes to given sum
     * @param sum value we are trying to reach by adding consecutive elements in BST
     * @return true if sum can be found, false if not
     */
    private boolean hasSumRecursive(Node myNode, int sum) {
        //empty tree can only be true if the sum is 0
        if (myNode==null){
            return sum==0;
        }
        //BST is not empty
        else {
            int smallSum = sum - myNode.getData();
            //if there is a path that generates sum in either left or right subtree returns true
            //smallSum equals zero when sum is reached (subtracted elements exactly equal to sum param)
            return (hasSumRecursive(myNode.getLeftChild(), smallSum) || hasSumRecursive(myNode.getRightChild(), smallSum));
        }
    }

    /**
     * HASSUM METHOD
     * @param sum value we are trying to reach by adding consecutive values in tree
     * @return true if sum can be reached, false if not
     */
    public boolean hasSum(int sum) {
        //recursively calls starting at the root to see if sum can be reached in either right or left subtrees
        return this.hasSumRecursive(getRootNode(), sum);
    }

    /**
     * @param leftChild Node representing left child connected to a root 
     * @param rightChild Node representing right child connected to the same root as leftChild
     * @return true if there is symmetry from these nodes down
     */
    public static boolean isSymmetricalRecursive(Node leftChild, Node rightChild) {
        //empty tree is symmetrical
        if(leftChild == null && rightChild == null) {
            return true;
        }
        //left is empty but right is not, not symmetrical
        if(leftChild == null) {
            return false;
        }
        //right is empty but left is not, not symmetrical
        if(rightChild == null) {
            return false;
        }
        //both left and right are not symmetrical, check if children of these nodes are symmetrical with each other recursively
        else {
            return (rightChild.getData() == leftChild.getData() && isSymmetricalRecursive(leftChild.getLeftChild(), rightChild.getRightChild()) && isSymmetricalRecursive(leftChild.getRightChild(), rightChild.getLeftChild()));
        }
    }

    /**
     * ISSYMMETRICAL METHOD
     * @param tree int array representing elements in a BST in level order
     * @return true if tree is symmetrical, false if not
     */
    public static boolean isSymmetrical(int[] tree){
        //empty tree is symmetrical 
        if(tree == null) {
            return true;
        }
        //ADD ELEMENTS IN ARRAY TO BST, must complete using a Q because level order traversal
        BinarySearchTree b = new BinarySearchTree();
        Queue<Node> qN = new LinkedList<Node>();
        Node rootNode = b.new Node(tree[0]);
        b.setRootNode(rootNode);
        qN.add(rootNode);
        int index = 1;
        //iterates thru input array in order to add elements to BST in intended order
        while(!qN.isEmpty() && index < tree.length) {
            //note: use poll instead of remove in case it is null
            Node nptr = qN.poll();
            //check if there are still elements in the array to iterate through, add element as left child
            if(index < tree.length) {
                Node left = b.new Node(tree[index]);
                nptr.setLeftChild(left);
                qN.add(left);
                index++;
            }
            //check if there are still elements in the array to iterate through, add element as right child
            if (index < tree.length) {
                Node right = b.new Node(tree[index]);
                nptr.setRightChild(right);
                qN.add(right);
                index++;
            }
        }
        //uses recursive symmetrical method on BST that we made from array using the first left and right children from the root
        return isSymmetricalRecursive(b.getRootNode().getLeftChild(), b.getRootNode().getRightChild());
    }
    public static void main(String[] args) throws Exception{
    }

}