package SHBAP3;
import java.util.*;
import java.io.*;

/*
UnionFind(int N); create a union find class for integer elements 0 ... N-1
boolean union(int x, int y); Form the union of sets containing values x and y using
the “union by size” strategy. If the two sets are in the same
UnionFind tree, return false. Return true otherwise. If the two
sets are the same size, the root of the set containing y must
become a child of the root of the set containing x. Otherwise
implement “union by size”.
int find(int y); Search for element y and return the root index of the
tree containing y. Implement path compression on each find,
including the two calls made by union()
void clear() Make all sets disjoint.
void printArray() Print the contents of the UnionFind array, 20 space-separated
integers per line
*/

class UnionFind{
    int[] array; 
    int[] id;
    UnionFind(int N){
        array = new int[N];
        id = new int[N];
        for(int i = 0; i < N; i++){
            array[i] = i;
            id[i] = -1;
        }
        
    }
    
    boolean union(int x, int y){
        if(id[x] == y)
            return false;
        
        id[x] = y;
        return true;
    }
    
    int find(int y){
        return y;
    }
    
    void clear(){
        
    }
    
    void printArray(){
        for(int i : id){
            System.out.print(i + " ");
        }
    }
}
class SHBAP3 {
    public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(new File("P1data.txt"));
//		String s = sc.nextLine();
//		
//		s = sc.nextLine();
//		int q = Integer.parseInt(s); //number of command lines
//		
//		for(int i=0;i<q;i++) {
//		s = sc.nextLine();
//		String [] tokens = s.split(" ");
//		String command = tokens[0];
//		
//                if(command == "n"){
//                    int X = Integer.parseInt(tokens[1]);
//                    UnionFind uf = new UnionFind(X);
//                }
//                else{
//                    
//                }
                  UnionFind uf = new UnionFind(12);
                  System.out.print(uf.union(1,2));
                  System.out.print(uf.union(3,4));
                  System.out.print(uf.union(5,6));
                  System.out.print(uf.union(7,8));
                  System.out.print(uf.union(9,10));
                  System.out.print(uf.union(2,4));
                  System.out.print(uf.union(6,8));
                  System.out.print(uf.union(11,6));
                  System.out.print(uf.union(11,6));
                  uf.printArray();
//                        int X,Y;
//			 switch(command) { 
//				
//				case "u": // delete
//					X = Integer.parseInt(tokens[1]);
//                                        Y = Integer.parseInt(tokens[2]);
//                                        
//                                        System.out.println("Union(" + X + "," + Y + ")");
//					break;
//				case "S": // search
//					X = Integer.parseInt(tokens[1]);
//                                        
//					
//					break;
//                                case "f":
//                                        System.out.println("Found " + X + ", root at " + R);
//                                        break;
//				case "p": // print stats
//					uf.printArray();
//					break;
//			} // end of switch
//			
//		} // end of for
	} // end of main
} // end of class SHBAP3
    
