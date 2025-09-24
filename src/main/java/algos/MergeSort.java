package algos;

import util.Metrics;

public class MergeSort {
    private static final int CUTOFF = 16;

    public static void sort(int[] a, Metrics m) {
        if (a == null || a.length < 2) return;
        int[] buffer = new int[a.length];
        if (m != null) m.incAllocations(a.length);
        mergeSort(a, buffer, 0, a.length - 1, m);
    }

    private static void mergeSort(int[] a, int[] buf, int lo, int hi, Metrics m) {
        if (hi - lo <= CUTOFF) {
            Utils.insertionSort(a, lo, hi, m);
            return;
        }
        int mid = lo + (hi - lo) / 2;
        if (m != null) m.enter();
        mergeSort(a, buf, lo, mid, m);
        if (m != null) m.exit();

        if (m != null) m.enter();
        mergeSort(a, buf, mid + 1, hi, m);
        if (m != null) m.exit();

        // optimization: if already ordered, skip merge
        if (a[mid] <= a[mid + 1]) return;
        merge(a, buf, lo, mid, hi, m);
    }

    private static void merge(int[] a, int[] buf, int lo, int mid, int hi, Metrics m) {
        System.arraycopy(a, lo, buf, lo, hi - lo + 1);
        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) {
            if (m != null) m.incComparisons();
            if (buf[i] <= buf[j]) a[k++] = buf[i++];
            else a[k++] = buf[j++];
        }
        while (i <= mid) a[k++] = buf[i++];
    }
}
