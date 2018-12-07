/* CS 3345.HON Shannen Barrameda sib170130 */
/* citations: Algorithms from CS @Princeton */
package SHBAP4;
import java.util.*; //to use LinkedList and Queue classes
import java.io.*;

public class SHBAP4 {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("P4d1in.txt"));
        String s = sc.nextLine();
        
        DWGraph graph = new DWGraph(Integer.parseInt(s));
        
        while(sc.hasNextLine()){
            s = sc.nextLine();
            
            String[] tokens = s.split(" ");
            
            graph.addE(Integer.parseInt(tokens[0]), Integer.parseInt(tokens[1]),
                    Integer.parseInt(tokens[2]));
        }
       
        
        graph.print();
        
    }
}
class ShortestPath{
    private double[] distanceTo;
    private Edge[] edgeTo;
    private boolean[] onQueue;
    private Queue<Integer> que;
    private Iterable<Edge> cycle;
    
    ShortestPath(DWGraph graph, int s){
        int u;
        distanceTo = new double[graph.v + 1];
        for(int v = 1; v < graph.v + 1; v++){
            distanceTo[v] = Double.POSITIVE_INFINITY;
        }
        distanceTo[s] = 0;
        
        que.add(s);
        
        while(que.peek() != null){
            u = que.poll();
            
            for(int i = 0; i < graph.adjList.length; i++){
                for(Edge edge: graph.adjList[i]){
                    
                }
            }
        }
        
    }
    
    
}
    
   
class Edge{
    int s; //source vertex
    int d; //destination vertex
    int weight; //weight of edge
    
    Edge(int s, int d, int weight){
        this.s = s;
        this.d = d;
        this.weight = weight;
    }
}

class DWGraph{ //directed, weighted Graph
    int v; //number of vertices

    LinkedList<Edge> adjList[]; //adjacency list - an array of LinkedLists
    
    //parameter for constructor: number of vertices in graph
    //constructor creates a LinkedList array of size v 
    DWGraph(int v){ 
        this.v = v;
        adjList = new LinkedList[v+1];
        
        /* initialize each LinkedList */
        
        for(int i = 1; i < v+1; i++){
            adjList[i] = new LinkedList<Edge>();
        }
    }
    
    void addE(int s, int d, int w){
        adjList[s].add(new Edge(s,d,w));
    }
    
    int getVertexNum(){
        return v;
    }
    
    void print(){
        for(int i = 1; i < v+1; i++){
            for(int j = 0; j < adjList[i].size(); j++){
                System.out.println(i + " is connected to " + adjList[i].get(j).d + " with weight " + adjList[i].get(j).weight);
            }
        }
    }
    

    void findShortestPath(int source){
        LinkedList<Integer> queue = new LinkedList(); //LinkedList used to act as queue of vertices
        int[] distance = new int[v];
        
        
        
            
        queue.addFirst(source);

//        while(queue.peek() != null){
//            int u = queue.poll();
//            queue.pop();
//            
//            for(each )
//            if(!queue.contains(destination))
//                queue.add(destination);
//        }
    }
          
}
