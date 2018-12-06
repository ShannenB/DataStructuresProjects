package shbap4;
import java.util.*; //to use LinkedList and Queue classes

public class SHBAP4 {
    public static void main(String[] args){
        DWGraph graph = new DWGraph(8);
        
        graph.addE(1, 2, 2);
        graph.addE(1, 4, 1);
        graph.addE(2, 5, 10);
        graph.addE(2, 4, 3);
        graph.addE(5, 7, 6);
        graph.addE(3, 1, 4);
        graph.addE(6, 3, 5);
        graph.addE(4, 3, 2);
        graph.addE(7, 6, 1);
        graph.addE(4, 5, 2);
        graph.addE(4, 7, 4);
        graph.addE(4, 6, 8);
        
        graph.print();
        
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
        adjList = new LinkedList[v];
        
        /* initialize each LinkedList */
        
        for(int i = 1; i < v; i++){
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
        for(int i = 1; i < v; i++){
            for(int j = 0; j < adjList[i].size(); j++){
                System.out.println(i + " is connected to " + adjList[i].get(j).d + " with weight " + adjList[i].get(j).weight);
            }
        }
    }
    
    void findShortestPaths(){
        
    }
          
}
