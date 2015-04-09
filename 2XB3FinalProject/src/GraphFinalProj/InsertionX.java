package GraphFinalProj;

import java.util.ArrayList;

public class InsertionX {

	// This class should not be instantiated.
	private InsertionX() { }

	/**
	 * Rearranges the ArrayList in ascending order, using the natural order.
	 * @param a the ArrayList to be sorted
	 * @return 
	 */
	public static ArrayList<Integer> sort(ArrayList<Integer> a) {
		int N = a.size();

		// put smallest element in position to serve as sentinel
		for (int i = N-1; i > 0; i--)
			if (less(a.get(i), a.get(i-1))) exch(a, i, i-1);

		// insertion sort with half-exchanges
		for (int i = 2; i < N; i++) {
			int v = a.get(i);
			int j = i;
			while (less(v, a.get(j-1))) {
				a.set(j,a.get(j-1));
				j--;
			}
			a.set(j,v);
		}
		return a;
	}

	/***********************************************************************
	 *  Helper sorting functions
	 ***********************************************************************/

	// is v < w ?
	private static boolean less(int v, int w) {
		return v < w;
	}

	// exchange the element at index i in a and the element at index j in a
	private static void exch(ArrayList<Integer> a, int i, int j) {
		int swap = a.get(i);
		a.set(i, a.get(j));
		a.set(j,swap);
	}
}