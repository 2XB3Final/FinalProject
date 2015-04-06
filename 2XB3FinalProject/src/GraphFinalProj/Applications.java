package GraphFinalProj;

import java.util.Stack;

public class Applications {
    private int[] sources;
    
    public int[] getSource(Digraph G) {
    	DepthFirstOrder dfs = new DepthFirstOrder(G);
    	sources = new int[G.V()];
    	Stack<Integer> revPost = dfs.reversePost();
    	while (!revPost.empty()) {
    		if (G.getToList(revPost.peek()).isEmpty()) {
    			sources[revPost.peek()] = revPost.peek();
    			revPost.pop();
    		}
    	}
    	return sources;
    }
    
    public Bag<Integer> told(Digraph G, int i) {
    	return G.getAdjList(i);
    }
    
    public Bag<Integer> heard(Digraph G, int i) {
    	return G.getToList(i);
    }
}