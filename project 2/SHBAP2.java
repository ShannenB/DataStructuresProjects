

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
    private TreeNode root;
    
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
            
            while((index < current.getKey() && key > current.keys[index])){
                index++;
            }
            
            if((index < current.getKey() && key == current.keys[index]))
                return true;
            
            
        }
        
        return false;
    }
    @Override
    public boolean insert(int key){
        if(root == null){
            root.setKey(key);
            return true;
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    private Node next;
	private Node prev;
   
    Node(){
        key = -1;  //essentially a null key
    }
    
    public void setKey(int key){
        this.key = key;
    }
    
    public int getKey(){
        return key;
    }
    
	public void setPrev(Node prev){
		this.prev = prev;
	}
	
	public Node getPrev(){
		return prev;
	}
	
    public Node getNext(){
        return next;
    }
    
    public void setNext(Node next){
        this.next = next;
    }
}

class LeafNode extends Node{
//    private int key;
    
    LeafNode(){
        super();
    }

    boolean isLeaf(TreeNode node){
        if(node.children.length == 0)
            return true;
        else
            return false;
    }
    
   
}
class SHBAP2{
    public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("P2data.txt"));
		String s = sc.nextLine();
		Two3Tree tree = new Two3Tree();
		s = sc.nextLine();
		
		
		while(!s.contains("E")){
		s = sc.nextLine();
		String [] tokens = s.split(" ");
		String command = tokens[0];
		
		

                    switch(command) { 
                           case "I": // insert
                                   String key = tokens[1];
								   
                                   if(tree.insert(Integer.parseInt(key)))
                                           System.out.println("Key " + key + " inserted");
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
                                   System.out.print("Height " + tree.height());
                                   break;
                           case "M":
                                   System.out.print("Size " + tree.numberOfNodes());
                                   break;
                           case "E":
                                   break;
                   } // end of switch
			
		}// end of for
	} // end of main
 } // end of class SHBAP2 


