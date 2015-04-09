package GraphFinalProj;

import java.util.LinkedList;
import java.util.Stack;

//ALGORITHM ADAPTED FROM SEDGEWICK & WAYNE, ALGORITHMS, 4TH EDITION


public class BreadthFirstDirectedPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // marked[v] = is there an s->v path?
    private int[] edgeTo;      // edgeTo[v] = last edge on shortest s->v path
    private int[] distTo;      // distTo[v] = length of shortest s->v path

    /**
     * Computes the shortest path from any one of the source vertices in sources
     * to every other vertex in graph G.
     * @param G the digraph
     * @param sources the source vertices
     */
    public BreadthFirstDirectedPaths(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++) distTo[v] = INFINITY;
        bfs(G, sources);
    }

    // BFS from single source
    private void bfs(Digraph G, int s) {
        LinkedList<Integer> q = new LinkedList<Integer>();
        marked[s] = true;
        distTo[s] = 0;
        q.add(s);
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : G.getAdjList(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.add(w);
                }
            }
        }
    }

    // BFS from multiple sources
    private void bfs(Digraph G, Iterable<Integer> sources) {
        LinkedList<Integer> q = new LinkedList<Integer>();
        for (int s : sources) {
            marked[s] = true;
            distTo[s] = 0;
            q.add(s);
        }
        while (!q.isEmpty()) {
            int v = q.poll();
            for (int w : G.getAdjList(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.add(w);
                }
            }
        }
    }

    /**
     * Returns the number of edges in a shortest path from the source s
     * (or sources) to vertex v?
     * @param v the vertex
     * @return the number of edges in a shortest path
     */
    public int distTo(int v) {
        return distTo[v];
    }
}