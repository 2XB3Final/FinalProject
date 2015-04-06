package GraphFinalProj;

import java.util.ArrayList;
import java.util.Stack;

public class Applications {
    private ArrayList<Integer> sources = new ArrayList();
    private ArrayList<Integer> told = new ArrayList();
    private ArrayList<Integer> heard = new ArrayList();
    private ArrayList<Integer> deadEnds = new ArrayList();
    
    /**
     * Find all the sources by doing reverse postorder on the input graph
     * @param G
     * @return the sources in an arraylist
     */
    public ArrayList<Integer> getSource(Digraph G) {
    	DepthFirstOrder dfs = new DepthFirstOrder(G);		// run dfs on the input graph G
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
    	for (int i = 0; i < G.getAdjList().length(); i++) {
    		if (!G.getAdjList(i).isEmpty()) {
    			told.add(i);
    		}
    	}
    	return told;
    }
    
    /**
     * @param G
     * @param i
     * @return the to list for input i
     */
    public Bag<Integer> heard(Digraph G, int i) {
    	return G.getToList(i);
    }
    
    /**
     * @param G
     * @return the ordered list of all the people that have heard the rumour
     */
    public ArrayList<Integer> heard(Digraph G) {
    	for (int i = 0; i < G.getToList().length(); i++) {
    		if (!G.getToList(i).isEmpty()) {
    			heard.add(i);
    		}
    	}
    	return heard;
    }
    
    /**
     * @param G
     * @return the ordered list of all the people that have heard the rumour but did not spread it
     */
    public ArrayList<Integer> deadEnd(Digraph G) {
    	for (int i = 0; i < heard.size(); i++) {
    		deadEnds.add(BinarySearch.rank(heard.get(i), told));
    	}
    	while (deadEnds.contains(-1)) {
    		deadEnds.remove(-1);
    	}
    	return deadEnds;
    }
}