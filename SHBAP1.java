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
		next = nxt;
		key = ky;
		value = val;
	}
}
class LinkedList {
	Node head;
	boolean insert(String key, long value) {
	// do nothing, and return false if key is already present
	// . . .
	}
	boolean delete(String ky) {
	// return false if key doesn’t exist
	// . . .
	}
	long find(String ky) {
	// return result of the search
	// . . .
	}
	LinkedList() { // constructor
		head = null;
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

	int hash(String ky, int tableSize) {
	// . . .
	}
	boolean insert(String key, long val) {
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
	}
} // end of class HashTable

class SHBAP1 { // use a class name according to your name. See Rules for Programming . . .
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new File("P1data.txt"));
		String s = sc.nextLine();
		int M = Integer.parseInt(s); // hash table size
		HashTable HT = new HashTable(M);
		s = sc.nextLine();
		int q = Integer.parseInt(s); // number of command lines
		for(int i=0;i<q;i++) {
		s = sc.nextLine();
		String [] tokens = s.split(" ");
		String command = tokens[0];
			switch(command) {
				case "I": // insert
				String key = tokens[1];
				long val = Long.parseLong(tokens[2]);
				// . . .
				break;
				case "D": // delete
				4
				key = tokens[1];
				// . . .
				break;
				case "S": // search
				key = tokens[1];
				// . . .
				break;
				case "P": // print stats
				HT.printStats();
				break;
			} // end of switch
		} // end of for
	} // end of main
} // end of class SHBAP1
