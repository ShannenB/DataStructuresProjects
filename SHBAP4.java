/* CS 3345.HON Shannen Barrameda sib170130 */
/* citations: Algorithms from CS @Princeton + Dr. Ivor Page */
package datastructures;
import java.util.*; //to use LinkedList and Queue classes
import java.io.*;

public class SHBAP4 {
    public static void main(String[] args) throws FileNotFoundException{
        Scanner sc = new Scanner(new File("P4d3in.txt"));
        String s = sc.nextLine();
        
        DWGraph graph = new DWGraph(Integer.parseInt(s));
        
        while(sc.hasNextLine()){
            s = sc.nextLine();
            
            String[] tokens = s.split(" ");
            //subtract 1 to offset off by one
            graph.addE(Integer.parseInt(tokens[0]) - 1, Integer.parseInt(tokens[1]) - 1,
                    Integer.parseInt(tokens[2]));
        }
        
        
        ShortestPathFaster spfa = new ShortestPathFaster(graph, 0);
        Iterable<Edge> path;
  
        if(spfa.hasNegativeCycle()){
            System.out.println("The graph contains a negative edge cycle");                
        }
        else{
            double weight = 0;
           
            for(int v = 0; v < graph.v ; v++){
                if(spfa.hasPath(v)){
                    System.out.print(1 + " ");
                    path = spfa.pathTo(v);
                    for(Edge e : path){
                        weight += e.weight;
                        
                     
                            System.out.print(e.d + " ");
                            
                    }
                    System.out.print((int) weight + 1);
                    weight = 0;
                    System.out.println();
                    
                }
                else{
                    System.out.println("No path from 1 to v");
                }
                
            }
           
        }
        FloydWarshall fw = new FloydWarshall(graph);
        
        System.out.println();
        
        fw.getDistance();
        fw.FloydWarshallMod(graph);
        
        
        
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
    @Override
    public String toString(){
        return  d + " ";
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
        
        for(int i = 0; i < v; i++){
            adjList[i] = new LinkedList<Edge>();
        }
    }
    
    void addE(int s, int d, int w){
        adjList[s].add(new Edge(s,d,w));
    }
    
    void addE(Edge e){
        adjList[e.s].add(e);
    }
    int getVertexNum(){
        return v;
    }
    
    void print(){
        for(int i = 0; i < v; i++){
            for(int j = 0; j < adjList[i].size(); j++){
                System.out.println( (i+1) + " is connected to " + (adjList[i].get(j).d + 1) + " with weight " + adjList[i].get(j).weight);
            }
        }
    }
    
    
   
    
}

class FloydWarshall{
    boolean hasNegativeCycle;
    double[][] distTo;
    Edge[][] edgeTo;
    double[][] arrayD;
    FloydWarshall(DWGraph G){
        int V = G.v;
        distTo = new double[V][V];
        edgeTo = new Edge[V][V];

        //initialize each element to infinity
        for (int v = 0; v < V; v++) {
            for (int w = 0; w < V; w++) {
                distTo[v][w] = Double.POSITIVE_INFINITY;
            }
        }


        for (int v = 0; v < G.v; v++) {
            for (Edge e : G.adjList[v]) {
                distTo[e.s][e.d] = e.weight;
                edgeTo[e.s][e.d] = e;
            }
            if (distTo[v][v] >= 0.0) {
                distTo[v][v] = 0.0;
                edgeTo[v][v] = null;
            }
        }

      
        for (int i = 0; i < V; i++) {
            for (int v = 0; v < V; v++) {
                if (edgeTo[v][i] == null) continue;  
                for (int w = 0; w < V; w++) {
                    if (distTo[v][w] > distTo[v][i] + distTo[i][w]) {
                        distTo[v][w] = distTo[v][i] + distTo[i][w];
                        edgeTo[v][w] = edgeTo[i][w];
                    }
                }
            }
        }
        
        
    }
    
    void FloydWarshallMod(DWGraph G){
        int V = G.v;
        distTo = new double[V][V];
        edgeTo = new Edge[V][V];
        arrayD = new double[V][V];
        // initialize distances to infinity
        for (int v = 0; v < V; v++) {
            for (int w = 0; w < V; w++) {
                distTo[v][w] = Double.POSITIVE_INFINITY;
            }
        }

        for (int v = 0; v < G.v; v++) {
            for (Edge e : G.adjList[v]) {
                distTo[e.s][e.d] = e.weight;
                edgeTo[e.s][e.d] = e;
            }
            if (distTo[v][v] >= 0.0) {
                distTo[v][v] = 0.0;
                edgeTo[v][v] = null;
            }
        }

        /* using Dr. Page's notes on minimax with Floyd Warshall */
        for (int i = 0; i < V; i++) {
            for (int v = 0; v < V; v++) {
                if (edgeTo[v][i] == null) continue;  
                for (int w = 0; w < V; w++) {
                    if (distTo[v][w] > distTo[v][i] + distTo[i][w]) {
                        distTo[v][w] = Double.min(distTo[v][w], Double.max(distTo[v][i],distTo[i][w]));
                        edgeTo[v][w] = edgeTo[i][w];
                    }
                }
            }
        }
        System.out.println("The minimax distance from 1 to " + G.v + " is " + (int)distTo[0][G.v-1]);
        
    }
    
    
    void print(){
        for(int i = 0; i < distTo.length; i++){
            for(int j = 0; j < distTo[i].length; j++){
                System.out.print(distTo[i][j] + " ");
            }
            System.out.println();
        }
    }
 
    double getDistance() {
        double distance = distTo[0][0];
        int p = 0, q = 0;
        for (int j = 0; j < distTo.length; j++) {
            for (int i = 0; i < distTo[j].length; i++) {
                if (distTo[j][i] > distance) {
                    distance = distTo[j][i];
                    p = j;
                    q = i;
                }
            }
        }
        System.out.println("The diameter is " + (int) distance + " between " + (p+1) + " and " + (q+1));
        return distance;
    }
}
class ShortestPathFaster{
    double[] distanceTo;
    Edge[] edgeTo;
    boolean[] onQueue;
    Queue<Integer> que;
    LinkedList<Edge> cycle;
    int cost;
    
    ShortestPathFaster(DWGraph graph, int s){
        int u;
        onQueue = new boolean[graph.v ];
        distanceTo = new double[graph.v];
        edgeTo = new Edge[graph.v];
        
        for(int v = 0; v < graph.v; v++){
            distanceTo[v] = Double.POSITIVE_INFINITY;
        }
        distanceTo[s] = 0;
       
       
        que = new LinkedList<Integer>();
        que.add(s);
        onQueue[s] = true;
        while(!que.isEmpty() && !hasNegativeCycle()){
            u = que.poll();
            onQueue[u] = false;
            
            for(int i = 0; i < graph.adjList.length; i++){
                for(Edge edge: graph.adjList[i]){
                    if(distanceTo[i] > distanceTo[u] + edge.weight){
                        distanceTo[i] = distanceTo[u] + edge.weight;
                        edgeTo[i] = edge;
                        
                        if(!onQueue[i]){
                            que.add(i);
                            onQueue[i] = true;
                        }
                    }   
                    if(cost++ % graph.v == 0){
                        findNegCycle();
                        if(hasNegativeCycle()) return;
                    }
                    
                }
            }
        }
        
       
        
    }
    
    
    
    
    
    boolean hasPath(int v){
        
        return distanceTo[v] < Double.POSITIVE_INFINITY;
    }
    
    LinkedList<Edge> pathTo(int v){
        if(hasNegativeCycle()){
            System.out.println("The graph contains a negative edge cycle");
        }
        if(!hasPath(v)) return null;
        
        LinkedList<Edge> path = new LinkedList<Edge>();
        
        for(Edge e = edgeTo[v]; e != null; e = edgeTo[e.s - 1]){
            
            path.push(e);
        }
        
        return path;
    }
    boolean hasNegativeCycle(){
       
        return cycle != null;
    }
    
    public Iterable<Edge> negativeCycle(){
        return cycle;
    }
    
    void findNegCycle(){
        int v = edgeTo.length;
        
        DWGraph spt = new DWGraph(v);
        
        for(int i = 0; i < v; i++){
            if(edgeTo[i] != null){
                spt.addE(edgeTo[i].s, edgeTo[i].d, edgeTo[i].weight);
            }
        }
        
        DWCycle finder = new DWCycle(spt);
        
        cycle = finder.cycle;
    }
    boolean check(DWGraph graph, int s){
        if(hasNegativeCycle()){
            double weight = 0.0;

            for(Edge e : negativeCycle()){
                weight += e.weight;
            }
            if(weight >= 0.0){
                System.out.println("This path contains a negative edge cycle");
                return false;
            }
            else {
                if (distanceTo[s] != 0.0 || edgeTo[s] != null) {
                    System.err.println("distanceTo[s] and edgeTo[s] inconsistent");
                    return false;
                }
                for (int v = 1; v < graph.v + 1; v++) {
                    if (v == s) continue;
                    if (edgeTo[v] == null && distanceTo[v] != Double.POSITIVE_INFINITY) {
                        System.err.println("distTo[] and edgeTo[] inconsistent");
                        return false;
                    }
                }


                for (int v = 1; v < graph.v + 1; v++) {
                    for (Edge e : graph.adjList[v]) {
                        int w = e.s;
                        if (distanceTo[v] + e.weight < distanceTo[w]) {
                            System.err.println("edge " + e + " not relaxed");
                            return false;
                        }
                    }
                }

                // check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
                for (int w = 0; w < graph.v ; w++) {
                    if (edgeTo[w] == null) continue;
                    Edge e = edgeTo[w];
                    int v = e.s;
                    if (w != e.d) return false;
                    if (distanceTo[v] + e.weight != distanceTo[w]) {
                        System.err.println("edge " + e + " on shortest path not tight");
                        return false;
                    }
                }
            }
        }
        return true;
        
    }
}

class DWCycle{
    boolean[] marked;
    Edge[] edgeTo;
    boolean[] onStack;
    LinkedList<Edge> cycle;
    
    DWCycle(DWGraph graph){
        marked = new boolean[graph.v];
        edgeTo = new Edge[graph.v];
        onStack = new boolean[graph.v];
           
        for(int v = 0; v < graph.v; v++){
            if(!marked[v]) dfs(graph, v);
        }
        checkCycle();
    }
    
    void dfs(DWGraph graph, int v){
        onStack[v] = true;
        marked[v] = true;
        
        for(Edge edge : graph.adjList[v]){
            int u = edge.d;
            
            if(cycle != null) return;
            
            else if(!marked[u]){
                edgeTo[u] = edge;
                dfs(graph, u);
            }
            else if(onStack[u]){
                cycle = new LinkedList<Edge>();
                
                Edge edge1 = edge;
                
                while(edge1.s != u){
                    cycle.push(edge1);
                    edge1 = edgeTo[edge1.s ];
                }
                
                cycle.push(edge1);
                
                return;
            }
            
        }
        onStack[v] = false;
    }
 
    boolean hasCycle(){
        return cycle != null;
    }
    
    boolean checkCycle(){
        if(hasCycle()){
            Edge f = null, l = null;
            
            for(Edge e : cycle){
                if(f == null) f = e;
                if(l != null){
                    if(l.d != e.s){
                        System.out.println("The graph contains a negative edge cycle");
                    }
                   
                }
                l = e;
            }
            
            if(l.d != f.s){
                System.out.println("cycle edges not incident ");
                return false;
            }
        }
        return true;
    }
}
