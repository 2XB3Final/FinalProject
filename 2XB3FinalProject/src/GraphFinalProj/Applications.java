package GraphFinalProj;

import java.util.ArrayList;
import java.util.Stack;

public class Applications {
    private static ArrayList<Integer> sources = new ArrayList();
    private ArrayList<Integer> told = new ArrayList();
    private ArrayList<Integer> heard = new ArrayList();
    private ArrayList<Integer> deadEnds = new ArrayList();
    
    /**
     * Find all the sources by doing reverse postorder on the input graph
     * @param G
     * @return the sources in an arraylist
     */
    public static ArrayList<Integer> getSource(Digraph G) {
    	DepthFirstOrder dfs = new DepthFirstOrder(G);		// run dfs on the input graph G
    	Stack<Integer> revPost = dfs.reversePost();			// run reverse postorder on dfs
    	while (!revPost.empty()) {							// while the stack is not empty
    		if (G.getToList(revPost.peek()).isEmpty()) {	// peek at the next element on the stack, check its ToList
    			sources.add(revPost.peek());				// if it's empty, i.e. there isn't a node pointing to it
    		}												// it has to be a source, therefore, add it to the arraylist
    		revPost.pop();									// pop it off the stack, and recur through
    	}
    	return sources;
    }
    
    /**
     * @param G
     * @param i
     * @return the adjacency list for input i
     */
    public Bag<Integer> told(Digraph G, int i) {
    	return G.getAdjList(i);
    }
    
    /**
     * @param G
     * @return the ordered list of all the people that have told the rumour
     */
    public ArrayList<Integer> told(Digraph G) {
    	for (int i = 0; i < G.getAdjList().length; i++) {		// iterate through the adjacency list
    		if (!G.getAdjList(i).isEmpty()) {					// if an element's adjacency list is not
    			told.add(i);									// empty, it has told the rumour, add it
    		}													// to the told list
    	}
    	return told;
    }
    
    /**
     * @param G
     * @param i
     * @return the to list for input i
     */
    public ArrayList<Integer> heard(Digraph G, int i) {
    	return G.getToList(i);
    }
    
    /**
     * @param G
     * @return the ordered list of all the people that have heard the rumour
     */
    public ArrayList<Integer> heard(Digraph G) {
    	for (int i = 0; i < G.getToList().length; i++) {		// iterate through the to list
    		if (!G.getToList(i).isEmpty()) {					// if an element's to list is not
    			heard.add(i);									// empty, it has heard the rumour, add it
    		}													// to the heard list
    	}
    	return heard;
    }
    
    /**
     * @param G
     * @return the ordered list of all the people that have heard the rumour but did not spread it
     */
    public ArrayList<Integer> deadEnds(Digraph G) {
    	for (int i = 0; i < heard.size(); i++) {				// iterate every element in the heard
    		deadEnds.add(BinarySearch.rank(heard.get(i), told));// list through the told list, if it's
    	}														// in heard but not told, that means 
    	while (deadEnds.contains(-1)) {							// it's a dead end
    		deadEnds.remove(-1);								// BinarySearch makes returns -1 to a
    	}														// search miss, need to remove them
    	return deadEnds;
    }
    
    /**
     * @param G
     * @param i
     * @return whether a given person is a spreader
     */
    public boolean isSpreader(Digraph G, int i) {
    	if (told(G,i).isEmpty()) {								// if the adjacency list of i is
    		return false;										// empty, then it hasn't told the
    	}														// rumour, it's not a spreader
    	else {
    		return true;
    	}
    }

    /**
     * @param G the digraph
     * @param vertex the vertex that is being inquired about
     * @return Returns a list of the people from whom a given person heard the information first
     */
    public static ArrayList<Integer> heardFromFirst(Digraph G, int vertex) {
    	ArrayList<Integer> origSrc = new ArrayList();
    	// find the shortest paths using BST
    	ArrayList<Integer> srcs;
    	if (sources == null) srcs = getSource(G);
    	else srcs = sources;
    	System.out.println(srcs.toString());														// TESTING

    	BreadthFirstDirectedPaths paths = new BreadthFirstDirectedPaths(G,srcs);
    	// get a list of all the vertices that point to vertex
    	ArrayList<Integer> toList = G.getToList(vertex);
    	System.out.println("the toList: " + toList.toString());										// TESTING
    	// if no vertices point to vertex, then it never received the information at all
    	if (toList.isEmpty()) return null;

    	// initialising variables
    	int currentVertex = toList.get(0);
    	System.out.println("current vertex: " + currentVertex);										// TESTING
    	int shortestPathVertex = currentVertex;
    	int length = paths.distTo(shortestPathVertex);
    	int shortestLength = length;
    	int shortestPathVertexIndex = 0;
    	// for each vertex that points to the given vertex, check its path length from the source(s)
    	// find the shortest path length and record the vertex that corresponds to it
    	for (int i = 1; i < toList.size(); i++) {
    		currentVertex = toList.get(i);
    		length = paths.distTo(currentVertex);
    		if (length < shortestLength) {
    			shortestLength = length;
    			shortestPathVertex = currentVertex;
    			shortestPathVertexIndex = i;
    		}
    	}
    	// add the vertex that has the shortest path length to the source(s) to the output list
    	origSrc.add(shortestPathVertex);
    	// go back through, starting from the index of the first vertex with the shortest path length
    	// check if there are any other vertices that have the same shortest path length and add to the output list
    	for (int j = shortestPathVertexIndex + 1; j < toList.size(); j++) {
    		currentVertex = toList.get(j);
    		length = paths.distTo(currentVertex);
    		if (length == shortestLength) {
    			origSrc.add(currentVertex);
    		}
    	}
    	return origSrc;
    }
    
    public static void main (String[] args) {
    	Digraph gtest = new Digraph("2XB3FinalProject/data/tinyDAG.txt");
    	
    	ArrayList<Integer> sources = getSource(gtest);
    	System.out.println(sources.toString());
//    	sources = new ArrayList();																	// TESTING
//    	sources.add(2);																				// TESTING
//    	sources.add(8);																				// TESTING
//    	ArrayList tester = heardFromFirst(gtest,12);
//    	System.out.println(tester.toString());
    }
}