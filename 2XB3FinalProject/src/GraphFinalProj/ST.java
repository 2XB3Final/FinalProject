package GraphFinalProj;

import java.util.Iterator;
import java.util.TreeMap;

//ALGORITHM ADAPTED FROM SEDGEWICK & WAYNE, ALGORITHMS, 4TH EDITION

public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {

	private TreeMap<Key, Value> st;

	/**
	 * Initializes an empty symbol table.
	 */
	public ST() {
		st = new TreeMap<Key, Value>();
	}

	/**
	 * Returns the value associated with the given key.
	 * @param key the key
	 * @return the value associated with the given key if the key is in the symbol table
	 *     and null if the key is not in the symbol table
	 * @throws NullPointerException if key is null
	 */
	public Value get(Key key) {
		if (key == null) throw new NullPointerException("called get() with null key");
		return st.get(key);
	}

	/**
	 * Inserts the key-value pair into the symbol table, overwriting the old value
	 * with the new value if the key is already in the symbol table.
	 * If the value is null, this effectively deletes the key from the symbol table.
	 * @param key the key
	 * @param val the value
	 * @throws NullPointerException if key is null
	 */
	public void put(Key key, Value val) {
		if (key == null) throw new NullPointerException("called put() with null key");
		if (val == null) st.remove(key);
		else             st.put(key, val);
	}

	/**
	 * Does this symbol table contain the given key?
	 * @param key the key
	 * @return true if this symbol table contains key and
	 *     false otherwise
	 * @throws NullPointerException if key is null
	 */
	public boolean contains(Key key) {
		if (key == null) throw new NullPointerException("called contains() with null key");
		return st.containsKey(key);
	}

	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * @return the number of key-value pairs in this symbol table
	 */
	public int size() {
		return st.size();
	}

	/**
	 * Returns all keys in the symbol table as an Iterable.
	 * To iterate over all of the keys in the symbol table named st,
	 * use the foreach notation: for (Key key : st.keys()).
	 * @return all keys in the sybol table as an Iterable
	 */
	public Iterable<Key> keys() {
		return st.keySet();
	}

	public Iterator<Key> iterator() {
		return st.keySet().iterator();
	}
}