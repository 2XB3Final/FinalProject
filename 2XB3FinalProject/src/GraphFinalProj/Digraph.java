package GraphFinalProj;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;

import org.apache.commons.lang3.time.StopWatch;

// ALGORITHM ADAPTED FROM SEDGEWICK & WAYNE, ALGORITHMS, 4TH EDITION

public class Digraph{
	// total number of vertices
    private final int V;
    // total number of edges
    private int E;
    // list of all vertices that a given vertex points to, indexed by vertex
    private Bag<Integer>[] adj;
    // list of all vertices that point to a given vertex, indexed by vertex
    private ArrayList<Integer>[] to;
    
    // Constructs empty digraph with V vertices
    public Digraph(int V){
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        
        adj = (Bag<Integer>[]) new Bag[V];
		to = (ArrayList<Integer>[]) new ArrayList[V];
        buildDigraph();
        
        DirectedCycle checkCycles = new DirectedCycle(this);
        if (checkCycles.hasCycle()) {
        	throw new IllegalArgumentException("This data does not represent a directed acyclic graph");
        }
    }
    
    // Constructs digraph from an input file
    // format is number of vertices (newline) number of edges (newline) and then pairs of vertices, 1 per line
    public Digraph (String fileName) {
    	File file = new File(fileName);
    	if(!file.exists()) throw new IllegalArgumentException("No such file");

    	Scanner fileInput = null;
    	try{
    		fileInput = new Scanner(file);
    	}
    	catch(IOException ex)
    	{
    		System.err.println(ex);
    		throw new IllegalArgumentException(ex);
    	}

    	try{
    		this.V = fileInput.nextInt();
//    		System.out.println(V);													// TESTING
    		if(V < 0) {
    			fileInput.close();
    			throw new IllegalArgumentException("Incorrect number of vertices (cannot be negative)");
    		}

    		adj = (Bag<Integer>[]) new Bag[V];
    		to = (ArrayList<Integer>[]) new ArrayList[V];

    		buildDigraph();
    		int E = fileInput.nextInt();
//   		System.out.println(E);													// TESTING
    		if(E < 0) {
    			fileInput.close();
    			throw new IllegalArgumentException("Incorrect number of edges (cannot be negative)");
    		}

    		for(int i = 0; i < E; i++) {
    			int v = fileInput.nextInt();
    			int w = fileInput.nextInt();
//    			System.out.println(i);												// TESTING
//    			System.out.println(v + " -> " + w);									// TESTING
    			addEdge(v,w);
    		}
    		
    		DirectedCycle checkCycles = new DirectedCycle(this);
            if (checkCycles.hasCycle()) {
            	fileInput.close();
            	throw new IllegalArgumentException("This data does not represent a directed acyclic graph");
            }

    		fileInput.close();
    	}
    	catch(NoSuchElementException e){
    		throw new InputMismatchException("Invalid input format in Digraph constructor");
    	}
    }
	
    /**
     * initializes the digraph
     */
	private void buildDigraph() {
		adj=(Bag<Integer>[])new Bag[V];
		for(int v = 0; v < V; v++) {
			adj[v]= new Bag<Integer>();
			to[v] = new ArrayList<Integer>();
		}

	}
    
	// Clones a digraph
    public Digraph(Digraph G){
        this(G.V());
        this.E = G.E();
        for(int v = 0; v < G.V(); v++){
            // reverse so that adjacency list is in same order as original
            Stack<Integer> reverse = new Stack<Integer>();
            for(int w : G.adj[v]){
                reverse.push(w);
            }
            for(int w : reverse){
                adj[v].add(w);
            }
        }
    } 
  
    // Gets the number of vertices
    public int V(){ return V; }

    // Gets the number of edges
    public int E(){ return E; }

    // Gets the adjacency list (for all vertices)
    public Bag<Integer>[] getAdjList() {
    	return adj;
    }
    
    // Gets the adjacency list for a specific vertex
    public Bag<Integer> getAdjList(int i) {
		return adj[i];
	}
    
    // Gets the to list (for all vertices)
    public ArrayList<Integer>[] getToList() {
    	return to;
    }
	
    // Gets the to list for a specific vertex
	public ArrayList<Integer> getToList(int i) {
		return to[i];
	}

	// Adds a directed edge v-> to the digraph
    public void addEdge(int v, int w){
        adj[v].add(w);
        to[w].add(v);
        E++;
    }

    // Returns the number of edges going out of a given vertex
    public int outdegree(int v){
        return adj[v].size();
    }

    // Reverses digraph
    public Digraph reverse(){
        Digraph R = new Digraph(V);
        for (int v = 0; v < V; v++){
            for (int w : getAdjList(v)){
                R.addEdge(w, v);
            }
        }
        return R;        
    }

    // Returns a string representation of the graph (for testing purposes)
    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        String NEWLINE = System.getProperty("line.separator");
        s.append(V + " vertices, " + E + " edges " + NEWLINE);
        for(int v = 0; v < V; v++){
            s.append(String.format("%d: points to: ", v));
            for(int w : adj[v]){
                s.append(String.format("%d ", w));
            }
            s.append(" // comes from: ");
            for(int x : to[v]){
                s.append(String.format("%d ", x));
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }

    public static void main(String[] args){
    	StopWatch watch = new StopWatch();
    	watch.start();
    	Digraph gtest = new Digraph("2XB3FinalProject/data/tinyDAG.txt");
    	watch.stop();
    	System.out.println(gtest.toString());
    	System.out.println("Time: " + watch.getTime() + " ms");
    	watch.reset();
    }
}