package GraphFinalProj;

import java.util.Stack;
import java.util.LinkedList;

// ALGORITHM ADAPTED FROM SEDGEWICK & WAYNE, ALGORITHMS, 4TH EDITION

public class DepthFirstOrder {
	private boolean[] marked;          // marked[v] = has v been marked in dfs?
    private int[] post;                // post[v]   = postorder number of v
    private LinkedList<Integer> postorder = new LinkedList<Integer>();  // vertices in postorder
    private int postCounter;           // counter for postorder numbering

    
    /**
     * Determines a depth-first order for the digraph <tt>G</tt>.
     * @param G the digraph
     */
    public DepthFirstOrder(Digraph G) {
        post   = new int[G.V()];
        marked    = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v]) dfs(G, v);
    }

    // run DFS in digraph G from vertex v and compute preorder/postorder
    private void dfs(Digraph G, int v) {
        marked[v] = true;
        for (int w : G.getAdjList(v)) {
            if (!marked[w]) {
                dfs(G, w);
            }
        }
        postorder.add(v);
        post[v] = postCounter++;
    }

    /**
     * Returns the postorder number of vertex <tt>v</tt>.
     * @param v the vertex
     * @return the postorder number of vertex <tt>v</tt>
     */
    public int post(int v) {
        return post[v];
    }

    /**
     * Returns the vertices in postorder.
     * @return the vertices in postorder, as an iterable of vertices
     */
    public Iterable<Integer> post() {
        return postorder;
    }

    /**
     * Returns the vertices in reverse postorder.
     * @return the vertices in reverse postorder, as an stack of vertices
     */
    public Stack<Integer> reversePost() {
        Stack<Integer> reverse = new Stack<Integer>();
        for (int v : postorder)
            reverse.push(v);
        return reverse;
    }
}