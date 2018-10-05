

import java.util.*;
import java.io.*;
import java.lang.Math.*;

interface TwoThreeTree {
    boolean insert(int key); // returns false if the key already exists
    boolean search(int key); // returns true if the key exists
    boolean remove(int key); // returns true if the key found and deleted
    void keyOrderList(); // prints all keys stored in order of increasing key values
    void bfsList(); // prints all the keys in breadth first order
    int numberOfNodes(); // returns the number of keys currently stored
    int height(); // returns the number of links on any path from
    // the root to a leaf
}

class Two3Tree implements TwoThreeTree{
    private int height;
    private int numNodes;
    private TreeNode root = new TreeNode();
    
    Two3Tree(){
        root = null;
        height = 0;
        numNodes = 0;
    }
	
    
	
    @Override
    public boolean search(int key){
        TreeNode current = root;
        
        while(current != null){
            int index = 0;
            
            while((index < current.getDegree()) && (key > current.keys[index])){
                index++;
            }
            if((index < current.getDegree() && key == current.keys[index]))
                return true;    
            else if(current.getChildren()[0] == null)
                return false; //if no children exist
            else if(index < current.getDegree() && key > current.keys[index])
                current = current.getChildren()[current.getDegree()]; //traverse tree to next child 
            else
                current = current.getChildren()[index];
        }
        
        return false;
    }
    
    @Override
    public void bfsList(){
        printInOrder(root);
    }
    
    void printInOrder(Node node){
        if(node == null)
            return;
        printInOrder(node);
    }
    
    @Override
    public int numberOfNodes(){
        return numNodes;
    }
    
    @Override
    public int height(){
        return height;
    }
    
    public boolean isEmpty(){
        return numberOfNodes() == 0;
    }

    @Override
    public boolean remove(int key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyOrderList() {
        keyOrderList(root);
    }
    void keyOrderList(TreeNode n){
        if(n != null){
            for(int ky : n.keys)
                System.out.print(ky);
            for(Node child: n.children)
                keyOrderList((TreeNode)child);
        }
    }
    @Override
    public boolean insert(int key){
		return true;
        // return insert(root, key);
        
    }        
    
    // boolean insert(Node node, int key){
        // if(node instanceof LeafNode){
            // Node leaf = new LeafNode();
            
            // leaf.setKey(key);
            // this.numNodes++;
            // return node.add(leaf);
        // }
        // Node treeNode;
        // TreeNode current = (TreeNode)node;
        
        // if (current.getLeft().getKey() > 0) {  
            // treeNode = insert(current.getLeftChild(), key);
            // if (treeNode == current.getLeftChild()) {
                // return current;
            // }
            // else {
                // return current.add(treeNode);
            // }
        // }
        // else if (current.getRight() == null ||
                // current.getRight().getKey() > 0) { 
            // treeNode = insert(current.getMiddleChild(), key);
            // if (treeNode == current.getMiddleChild()) {
                // return current;
            // }
            // else {     
                // return current.add(treeNode);
            // }
        // }
        // else {    
            // treeNode = insert(current.getRightChild(), pair);
            // if (treeNode == current.getRightChild()) {      
                // return current;
            // }
            // else {         
                // return current.add(treeNode);
            // }
        // }
    // }
    int getMin(Node node){
        if(node == null)
            return -1;
        if(node instanceof TreeNode){
            TreeNode n = (TreeNode) node;
            while (n.getChildren()[0] != null) {
                    n = n.getChildren()[0]; //traverses down subtrees for minimum
            }
            return n.getKeys()[0];
        }
        else{
            LeafNode n = (LeafNode)node;
            return n.getKey();
        }
        
    }
    void split(TreeNode n, int i){
        TreeNode newN = new TreeNode();
        
        newN.getKeys()[0] = n.getChildren()[i].getKeys()[2];
        newN.getChildren()[0] = n.getChildren()[i].getChildren()[2];
       
        n.getChildren()[i].getChildren()[2] = null;
        
        
        newN.setKey(1);
        n.getChildren()[i].setKey(1);
        
        for(int k = n.getDegree() - 1; k >= i; k--){
            n.getKeys()[k+1] = n.getKeys()[k];
        }
        
        for(int k = n.getDegree() - 1; k > i; k--){
            n.getChildren()[k+1] = n.getChildren()[k];
        }
        
        n.getChildren()[i+1] = newN;
        n.getKeys()[i] = n.getChildren()[i].getKeys()[1];
        n.setKey(n.getDegree() + 1);
       
        
    }
}

class TreeNode extends Node{
    int keys[]; // keys for searching
    Node children[]; // references to the 2 or 3 children
    int degree = 0;

    TreeNode() {
            keys = new int[2]; // keys[0] = min key in middle subtree
            children = new Node[3]; // references to children of leaves
            degree = 0; // for printing, overflow, and split operations
    }
	
	int[] getKeys(){
		return keys;
	}
	
	TreeNode[] getChildren(){
		return (TreeNode[])children;
	}
	
	int getDegree(){
		return degree;
	}
	
	void setDegree(int degree){
		this.degree = degree;
	}
	
	boolean isEmpty(){
		return keys == null;
	}
        
        
	
    void print() {
            if(degree == 1)
                    System.out.print("(-,-)");
            else if(degree==2)
                    System.out.print("(" + keys[1] + ",-) ");
            else
                    System.out.print("(" + keys[1] + "," + keys[2] + ") ");
    }
}

class Node{
    private int key;
   
    Node(){
          
    }
	
    Node(int key){ 
        this.key = key;
            
    }
    
    void setKey(int key){
        this.key = key;
    }
    
    int getKey(){
        return key;
    }
    
	// void setVal(int val){
		// this.val = val;
	// }
	
	// int getVal(){
		// return val;
	// }
	
	void setLeft(Node n){
		
	}
}

class LeafNode extends Node{

	private LeafNode leftChild, rightChild;
	
	LeafNode(){
		leftChild = null;
		rightChild = null;
    }
	
	void setLeftChild(Node node){
		leftChild = (LeafNode)node;
	}
	
	void setRightChild(Node node){
		rightChild = (LeafNode) node;
	}
	
	LeafNode getLeftChild(){
		return leftChild;
	}
	
	LeafNode getRightChild(){
		return rightChild;
	}
	
	
    
    
   
}
class SHBAP2{
    public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("P2data.txt"));
		String s = sc.nextLine();
		Two3Tree tree = new Two3Tree();
		
		
		
		while(!s.contains("E")){

		String [] tokens = s.split(" ");
		String command = tokens[0];
		
		

                    switch(command) { 
                           case "I": // insert
                                   String key = tokens[1];
								 
								 
                                   if(tree.insert(Integer.parseInt(key)))
                                           System.out.println("Key " + key + " inserted" + command);
                                   else
                                           System.out.println("Key " + key + " already exists");
                                   break;

                           case "D": // delete
                                   key = tokens[1];
                                   if(tree.remove(Integer.parseInt(key)))
                                           System.out.println("Key " + key + " deleted");
                                   else
                                           System.out.println("Key " + key + " doesn't exist");
                                   break;
                           case "S": // search
                                   key = tokens[1];
                                   if(tree.search(Integer.parseInt(key)))
                                           System.out.println("Key " + key + " found");
                                   else
                                           System.out.println("Key " + key + " doesn't exist");
                                   break;
                           case "K": // print keys in order
                                   tree.keyOrderList();
                                   break;
                           case "B": 
                                   tree.bfsList();
                                   break;
                           case "H":
                                   System.out.println("Height " + tree.height());
                                   break;
                           case "M":
                                   System.out.println("Size " + tree.numberOfNodes());
                                   break;
                           case "E":
                                   break;
                   } // end of switch
			s = sc.nextLine();
		}// end of while
	} // end of main
 } // end of class SHBAP2 


