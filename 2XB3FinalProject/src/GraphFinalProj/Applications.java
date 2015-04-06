package GraphFinalProj;

import java.util.ArrayList;
import java.util.Stack;

public class Applications {
    private ArrayList<Integer> sources;
    
    /**
     * Find all the sources by doing reverse postorder on the input graph
     * @param G
     * @return the sources in an arraylist
     */
    public ArrayList<Integer> getSource(Digraph G) {
    	DepthFirstOrder dfs = new DepthFirstOrder(G);		// run dfs on the input graph G
    	sources = new ArrayList();							// initializes an arraylist for storing the sources
    	Stack<Integer> revPost = dfs.reversePost();			// run reverse postorder on dfs
    	while (!revPost.empty()) {							// while the stack is not empty
    		if (G.getToList(revPost.peek()).isEmpty()) {	// peek at the next element on the stack, check its ToList
    														// if it's empty, i.e. there isn't a node pointing to it
    			sources.add(revPost.peek());				// it has to be a source, therefore, add it to the arraylist
    			revPost.pop();								// pop it off the stack, and recur through
    		}
    	}
    	return sources;
    }
    
    /**
     * 
     * @param G
     * @param i
     * @return the adjacency list for input i
     */
    public Bag<Integer> told(Digraph G, int i) {
    	return G.getAdjList(i);
    }
    
    /**
     * 
     * @param G
     * @param i
     * @return the to list for input i
     */
    public Bag<Integer> heard(Digraph G, int i) {
    	return G.getToList(i);
    }
}