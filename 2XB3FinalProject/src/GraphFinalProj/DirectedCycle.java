package GraphFinalProj;

import GraphFinalProj.Digraph;
import java.util.Stack;

//ALGORITHM ADAPTED FROM SEDGEWICK & WAYNE, ALGORITHMS, 4TH EDITION

public class DirectedCycle {
	private boolean[] marked;        // marked[v] = has vertex v been marked?
	private int[] edgeTo;            // edgeTo[v] = previous vertex on path to v
	private boolean[] onStack;       // onStack[v] = is vertex on the stack?
	private Stack<Integer> cycle;    // directed cycle (or null if no such cycle)

	/**
	 * Determines whether the digraph G has a directed cycle and, if so,
	 * finds such a cycle.
	 * @param G the digraph
	 */
	public DirectedCycle(Digraph G) {
		marked  = new boolean[G.V()];
		onStack = new boolean[G.V()];
		edgeTo  = new int[G.V()];
		for (int v = 0; v < G.V(); v++)
			if (!marked[v]) dfs(G, v);
	}

	// check that algorithm computes either the topological order or finds a directed cycle
	private void dfs(Digraph G, int v) {
		onStack[v] = true;
		marked[v] = true;
		for (int w : G.getAdjList(v)) {

			// short circuit if directed cycle found
			if (cycle != null) return;

			//found new vertex, so recur
			else if (!marked[w]) {
				edgeTo[w] = v;
				dfs(G, w);
			}

			// trace back directed cycle
			else if (onStack[w]) {
				cycle = new Stack<Integer>();
				for (int x = v; x != w; x = edgeTo[x]) {
					cycle.push(x);
				}
				cycle.push(w);
				cycle.push(v);
			}
		}
		onStack[v] = false;
	}

	/**
	 * Does the digraph have a directed cycle?
	 * @return true if the digraph has a directed cycle, false otherwise
	 */
	public boolean hasCycle() {
		return cycle != null;
	}
}