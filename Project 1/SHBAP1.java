//Shannen Barrameda sib170130
//CS3345.HON
//Data Structures and Algorithms Project 1
import java.util.*;
import java.io.*;
import java.lang.Math.*;



class Node {
	Node next; // the linkage in the singly linked list
	String key; // the ID of the element
	long value; // the data stored
	
	Node(String ky, long val, Node nxt) { // constructor
		key = ky;
		value = val;
		next = nxt;
	}
}
class LinkedList {
	Node head;
	
	LinkedList() { // constructor
		head = null;
	}
	
	boolean insert(String key, long value) {
		if(find(key) != 0)	//do nothing, and return false if key is already present
			return false;
		
		Node newNode = new Node(key, value, null);
		
		newNode.next = head;
		head = newNode;
		return true;
		

	}
	
	boolean delete(String ky) {
		Node current = head;
		Node prev = null;
		
		//if the head of linkedlist is the node that needs to be deleted
		if(current != null && current.key.equals(ky)){
			head = current.next;
			return true;
		}
		
		//parses through linkedlist until key is found
		while(current != null && !current.key.equals(ky)){
			prev = current;
			current = current.next;
		}
		
		//if key does not exist in the linkedlist
		if(current == null){
			return false;
		}
		
		//unlinks the prev node and next node from node that is deleted
		prev.next = current.next;
		return true;
	}
	
	//linear search of linkedlist
	long find(String ky) {
		Node current = head;
		while(current != null){
			if(current.key.equals(ky))
				return current.value;
			else
				current = current.next;
		}
		return 0; //returns 0 if key does not exist in linkedlist since values in key-value pairs are [1, 2^63-1]
	} 
	
	
	public String toString(){
		Node current = head;
		String key_value = "";
		
		while(current != null){
			key_value += current.key + " " + current.value + "\n";
			current = current.next;
		}
		return key_value;
	}
}
class HashTable {  
	LinkedList [] L; // uses an array of (your) LinkedLists
	int tableSize;
	int entries;
	
	HashTable(int size) { // constructor
		tableSize = size;
		entries = 0;
		L = new LinkedList[size];
		for(int i=0;i<size;i++)
		L[i] = new LinkedList();
	}	

	int remainderUnsigned(int dividend, int divisor){
		return (dividend & (divisor - 1)); //bitwise division
	}
	
	int hash(String ky, int tableSize) {
		int dividend = 0;
		
		for(int i = 0; i < ky.length(); i++){
			dividend += (int)ky.charAt(i) * (int)Math.pow(31, ky.length() - i);
		}
		
		return remainderUnsigned((int)dividend, (int)Math.pow(2,32)) % tableSize;
	}
	
/* 	boolean insert(String key, long val) {
	// attempt to insert a record. Return false if
	// the key is already present in the table
	// . . .
	}
	boolean delete(String key) {
	// attempt to delete a record. Return false if
	// the key isn’t present in the table
	// . . .
	}
	long search(String key) {
	// attempt to find a record. Return the value
	// or -1 if the key is not found
	// . . .
	}
	void clearTable() {
	// empty the hash table
	// . . .
	}
	int size() {
	// return the number of records in the table
	// . . .
	}
	void printStats() {
	// . . .
	} */
} // end of class HashTable

class SHBAP1 { // use a class name according to your name. See Rules for Programming . . .
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("P1data.txt"));
		String s = sc.nextLine();
		int M = Integer.parseInt(s); // hash table size
		HashTable HT = new HashTable(M);
		s = sc.nextLine();
		int q = Integer.parseInt(s); //number of command lines
		for(int i=0;i<q;i++) {
		s = sc.nextLine();
		String [] tokens = s.split(" ");
		String command = tokens[0];
		
		LinkedList ll = new LinkedList();
		ll.insert("see ya", 40);
		ll.insert("hi", 12347);
		ll.insert("bye", 12345);
		ll.insert("bye", 4);
		System.out.println(ll.delete("hi"));
		System.out.println(HT.hash("ABCD", 16));
		

			 switch(command) { 
				case "I": // insert
					String key = tokens[1];
					long val = Long.parseLong(tokens[2]);
					break;
				
				case "D": // delete
					
					key = tokens[1];
					
					break;
				case "S": // search
					key = tokens[1];
				
					break;
				case "P": // print stats
					//HT.printStats();
					break;
			} // end of switch
		} // end of for
	} // end of main
} // end of class SHBAP1 
