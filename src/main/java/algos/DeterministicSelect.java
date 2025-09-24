package algos;

import util.Metrics;

/**
 * Deterministic selection (Median of Medians), group size 5.
 * Returns k-th smallest (0-based) in array a.
 */
public class DeterministicSelect {

    public static int select(int[] a, int k, Metrics m) {
        if (a == null || k < 0 || k >= a.length) throw new IllegalArgumentException("Invalid args");
        return selectInplace(a, 0, a.length - 1, k, m);
    }

    private static int selectInplace(int[] a, int left, int right, int k, Metrics m) {
        while (left <= right) {
            if (left == right) return a[left];
            int pivot = medianOfMedians(a, left, right, m);
            int pivotPos = partitionByValue(a, left, right, pivot, m);

            if (k == pivotPos) return a[k];
            else if (k < pivotPos) right = pivotPos - 1;
            else left = pivotPos + 1;
        }
        throw new RuntimeException("Select failed");
    }

    private static int partitionByValue(int[] a, int left, int right, int pivotValue, Metrics m) {
        int pivotPos = left;
        for (int i = left; i <= right; i++) {
            if (m != null) m.incComparisons();
            if (a[i] == pivotValue) { pivotPos = i; break; }
        }
        Utils.swap(a, pivotPos, right);
        int store = left;
        for (int i = left; i < right; i++) {
            if (m != null) m.incComparisons();
            if (a[i] < pivotValue) { Utils.swap(a, store, i); store++; }
        }
        Utils.swap(a, store, right);
        return store;
    }

    private static int medianOfMedians(int[] a, int left, int right, Metrics m) {
        int n = right - left + 1;
        if (n <= 5) {
            Utils.insertionSort(a, left, right, m);
            return a[left + n / 2];
        }
        int write = left;
        for (int i = left; i <= right; i += 5) {
            int subR = Math.min(i + 4, right);
            Utils.insertionSort(a, i, subR, m);
            int medianIdx = i + (subR - i) / 2;
            Utils.swap(a, write, medianIdx);
            write++;
        }
        // medians in a[left..write-1]
        return medianOfMedians(a, left, write - 1, m);
    }
}
