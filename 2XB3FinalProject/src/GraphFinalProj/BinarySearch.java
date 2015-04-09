package GraphFinalProj;

import java.util.ArrayList;

//ALGORITHM ADAPTED FROM SEDGEWICK & WAYNE, ALGORITHMS, 4TH EDITION

public class BinarySearch {

    /**
     * This class should not be instantiated.
     */
    private BinarySearch() { }

    /**
     * Searches for the integer key in the sorted ArrayList a.
     * @param key the search key
     * @param a the ArrayList of integers, must be sorted in ascending order
     * @return index of key in ArrayList a if present; -1 if not present
     */
    public static int rank(int key, ArrayList<Integer> a) {
        int lo = 0;
        int hi = a.size() - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key < a.get(mid)) hi = mid - 1;
            else if (key > a.get(mid)) lo = mid + 1;
            else return mid;
        }
        return -1;
    }
}