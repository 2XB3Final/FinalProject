package GraphFinalProj;

import java.io.File;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

//ALGORITHM ADAPTED FROM SEDGEWICK & WAYNE, ALGORITHMS, 4TH EDITION

public class RandomIntDigraph {
	private ST<Integer, Integer> st;  // name-integer -> index
	private Integer[] keys;           // index  -> name-integer
	private Digraph G;
	private int V;

	/**
	 * constructs a symbol table based on a file of input that have either strings or large numbers
	 * @param filename
	 */
	public RandomIntDigraph(String filename) {
		st = new ST<Integer, Integer>();

		File file = new File(filename);
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
			if(V < 0) {
				fileInput.close();
				throw new IllegalArgumentException("Incorrect number of vertices (cannot be negative)");
			}

			int E = fileInput.nextInt();
			if(E < 0) {
				fileInput.close();
				throw new IllegalArgumentException("Incorrect number of edges (cannot be negative)");
			}

			// First pass builds the index by reading the name-integer to associate distinct name-integer with an index
			for(int i = 0; i < 2*E; i++) {
				int v = fileInput.nextInt();
				if (!st.contains(v)) {
					st.put(v, st.size());
				}
			}

			// inverted index to get string keys in an array
			keys = new Integer[st.size()];   // st.size() SHOULD BE == V
			for (int nameInt : st.keys()) {
				keys[st.get(nameInt)] = nameInt;
			}


			fileInput.close();

			G = new Digraph(st.size());
			try{
				fileInput = new Scanner(file);
			}
			catch(IOException ex)
			{
				System.err.println(ex);
				throw new IllegalArgumentException(ex);
			}

			// second pass builds the digraph by connecting first vertex on each line to all others
			fileInput.nextInt();
			fileInput.nextInt();
			for(int i = 0; i < E; i++) {
				int temp = fileInput.nextInt();
				int v = st.get(temp);
				temp = fileInput.nextInt();
				int w = st.get(temp);
				G.addEdge(v,w);
			}
			fileInput.close();
		}
		catch(NoSuchElementException e){
			throw new InputMismatchException("Invalid input format in Digraph constructor");
		}


	}

	/**
	 * returns the keys of the digraph
	 * @return array keys
	 */
	public Integer[] getKeys() {
		return keys;
	}

	/**
	 * Returns the integer associated with the vertex named s.
	 * @param s the name of a vertex
	 * @return the integer (between 0 and V - 1) associated with the vertex named s
	 */
	public int index(int s) {
		return st.get(s);
	}

	/**
	 * Returns the name of the vertex associated with the integer v.
	 * @param v the integer corresponding to a vertex (between 0 and V - 1) 
	 * @return the name of the vertex associated with the integer v
	 */
	public int name(int v) {
		return keys[v];
	}

	/**
	 * Returns the digraph associated with the symbol graph. It is the client's responsibility
	 * not to mutate the digraph.
	 * @return the digraph associated with the symbol digraph
	 */
	public Digraph G() {
		return G;
	}
}