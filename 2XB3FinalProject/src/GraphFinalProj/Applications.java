package GraphFinalProj;

import java.util.ArrayList;
import java.util.Stack;

public class Applications {    
	/**
	 * Find all the sources by doing reverse postorder on the input digraph
	 * @param G
	 * @return the sources in an arraylist
	 */
	public static ArrayList<Integer> getSource(Digraph G) {
		ArrayList<Integer> sources = new ArrayList();
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

	public static ArrayList<Integer> getSource(RandomIntDigraph SymbolG) {
		ArrayList<Integer> sources = getSource(SymbolG.G());
		for (int i = 0; i < sources.size(); i++) {
			sources.set(i, SymbolG.name(sources.get(i)));
		}
		return sources;  	
	}

	/**
	 * @param G
	 * @param i
	 * @return the adjacency list for input i
	 */
	public static Bag<Integer> told(Digraph G, int i) {
		return G.getAdjList(i);
	}

	public static Bag<Integer> told(RandomIntDigraph SymbolG, int i) {
		int sequentialVertex = SymbolG.index(i);
		Bag<Integer> sequentialAdj = SymbolG.G().getAdjList(sequentialVertex);
		Bag<Integer> adj = new Bag<Integer>();
		for (int iSeq : sequentialAdj) {
			adj.add(SymbolG.name(iSeq));
		}
		return adj;
	}

	/**
	 * @param G
	 * @return the ordered list of all the people that have told the rumour
	 */
	public static ArrayList<Integer> told(Digraph G) {
		ArrayList<Integer> told = new ArrayList();
		for (int i = 0; i < G.getAdjList().length; i++) {		// iterate through the adjacency list
			if (!G.getAdjList(i).isEmpty()) {					// if an element's adjacency list is not
				told.add(i);									// empty, it has told the rumour, add it
			}													// to the told list
		}
		return told;
	}

	public static ArrayList<Integer> told(RandomIntDigraph SymbolG) {
		ArrayList<Integer> told = told(SymbolG.G());
		for (int i = 0; i < told.size(); i++) {
			told.set(i, SymbolG.name(told.get(i)));
		}
		return InsertionX.sort(told);  	
	}

	/**
	 * @param G
	 * @param i
	 * @return the to list for input i
	 */
	public static ArrayList<Integer> heard(Digraph G, int i) {
		return G.getToList(i);
	}

	public static ArrayList<Integer> heard(RandomIntDigraph SymbolG, int i) {
		int sequentialVertex = SymbolG.index(i);
		ArrayList<Integer> sequentialTo = SymbolG.G().getToList(sequentialVertex);
		ArrayList<Integer> to = new ArrayList<Integer>();
		for (int iSeq : sequentialTo) {
			to.add(SymbolG.name(iSeq));
		}
		return to;
	}

	/**
	 * @param G
	 * @return the ordered list of all the people that have heard the rumour
	 */
	public static ArrayList<Integer> heard(Digraph G) {
		ArrayList<Integer> heard = new ArrayList();
		for (int i = 0; i < G.getToList().length; i++) {		// iterate through the to list
			if (!G.getToList(i).isEmpty()) {					// if an element's to list is not
				heard.add(i);									// empty, it has heard the rumour, add it
			}													// to the heard list
		}
		return heard;
	}

	public static ArrayList<Integer> heard(RandomIntDigraph SymbolG) {
		ArrayList<Integer> heard = heard(SymbolG.G());
		for (int i = 0; i < heard.size(); i++) {
			heard.set(i, SymbolG.name(heard.get(i)));
		}
		return InsertionX.sort(heard);  	
	}

	/**
	 * @param G
	 * @return the ordered list of all the people that have heard the rumour but did not spread it
	 */
	public static ArrayList<Integer> deadEnds(Digraph G) {
		ArrayList<Integer> deadEnds = new ArrayList();
		ArrayList<Integer> heard = heard(G);
		ArrayList<Integer> told = told(G);
		for (int i = 0; i < heard.size(); i++) {				// binary searches every element in the heard
			int hearer = heard.get(i);							// list in told, if it is in heard but not
			int searchResult = BinarySearch.rank(heard.get(i), told);	// told, that means it is a dead
			if (searchResult == -1) {							// end, i.e. if it's a search miss
				deadEnds.add(hearer);
			}
		} 
		return deadEnds;
	}

	public static ArrayList<Integer> deadEnds(RandomIntDigraph SymbolG) {
		ArrayList<Integer> deadEnds = deadEnds(SymbolG.G());
		for (int i = 0; i < deadEnds.size(); i++) {
			deadEnds.set(i, SymbolG.name(deadEnds.get(i)));
		}
		return deadEnds; 
	}

	/**
	 * @param G
	 * @param i
	 * @return whether a given person is a spreader
	 */
	public static boolean isSpreader(Digraph G, int i) {
		if (told(G,i).isEmpty()) {								// if the adjacency list of i is
			return false;										// empty, then it hasn't told the
		}														// rumour, it's not a spreader
		else {
			return true;
		}
	}

	public static boolean isSpreader(RandomIntDigraph SymbolG, int i) {
		int sequentialVertex = SymbolG.index(i);
		if (told(SymbolG.G(),sequentialVertex).isEmpty()) {
			return false;
		} else return true;

	}

	public static boolean isHearer(Digraph G, int i) {
		if (heard(G,i).isEmpty()) {
			return false;
		} else return true;
	}

	public static boolean isHearer(RandomIntDigraph SymbolG, int i) {
		int sequentialVertex = SymbolG.index(i);
		if (heard(SymbolG.G(),sequentialVertex).isEmpty()) {
			return false;
		} else return true;

	}

	public static String roleOf(Digraph G, int i) {
		boolean spreader = isSpreader(G,i);
		boolean hearer = isHearer(G,i);
		if (spreader && hearer) return (i + " both heard and spread the information.");
		else if (spreader && !hearer) return (i + " spread the information.");
		else if (!spreader && hearer) return (i + " heard the information");
		else return (i + " was not involved.");
	}

	public static String roleOf(RandomIntDigraph SymbolG, int i) {
		boolean spreader = isSpreader(SymbolG,i);
		boolean hearer = isHearer(SymbolG,i);
		if (spreader && hearer) return (i + " both heard and spread the information.");
		else if (spreader && !hearer) return (i + " spread the information.");
		else if (!spreader && hearer) return (i + " heard the information");
		else return (i + " was not involved.");
	}

	/**
	 * @param G the digraph
	 * @param vertex the vertex that is being inquired about
	 * @return Returns a list of the people from whom a given person heard the information first
	 */
	public static ArrayList<Integer> heardFromFirst(Digraph G, int vertex) {
		ArrayList<Integer> origSrc = new ArrayList();
		// find the shortest paths using BST
		ArrayList<Integer> srcs = getSource(G);

		BreadthFirstDirectedPaths paths = new BreadthFirstDirectedPaths(G,srcs);
		// get a list of all the vertices that point to vertex
		ArrayList<Integer> toList = G.getToList(vertex);
		// if no vertices point to vertex, then it never received the information at all
		if (toList.isEmpty()) return null;

		// initialising variables
		int currentVertex = toList.get(0);
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

	public static ArrayList<Integer> heardFromFirst(RandomIntDigraph SymbolG, int vertex) {
		int sequentialVertex = SymbolG.index(vertex);
		ArrayList<Integer> origSrc = heardFromFirst(SymbolG.G(),sequentialVertex);

		if (origSrc == null) return null;

		for (int i = 0; i < origSrc.size(); i++) {
			origSrc.set(i, SymbolG.name(origSrc.get(i)));
		}
		return origSrc; 
	}
}