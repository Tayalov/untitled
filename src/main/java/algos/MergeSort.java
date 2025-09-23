package algos;

public class MergeSort {
    private static final int CUTOFF = 16;

    // public entry (provides Metrics)
    public static void sort(int[] a, Metrics m) {
        if (a == null || a.length < 2) return;
        int[] buffer = new int[a.length];
        m.incAllocations(a.length); // count buffer allocation (approx)
        sort(a, buffer, 0, a.length - 1, m);
    }

    private static void sort(int[] a, int[] buffer, int lo, int hi, Metrics m) {
        if (hi - lo <= CUTOFF) {
            Utils.insertionSort(a, lo, hi, m);
            return;
        }
        int mid = lo + (hi - lo) / 2;

        m.enter(); sort(a, buffer, lo, mid, m); m.exit();
        m.enter(); sort(a, buffer, mid + 1, hi, m); m.exit();

        m.incComparisons();
        if (a[mid] <= a[mid + 1]) return; // already ordered

        merge(a, buffer, lo, mid, hi, m);
    }

    private static void merge(int[] a, int[] buffer, int lo, int mid, int hi, Metrics m) {
        for (int i = lo; i <= hi; i++) buffer[i] = a[i];

        int i = lo, j = mid + 1, k = lo;
        while (i <= mid && j <= hi) {
            m.incComparisons();
            if (buffer[i] <= buffer[j]) a[k++] = buffer[i++];
            else a[k++] = buffer[j++];
        }
        while (i <= mid) a[k++] = buffer[i++];
        // right side already in place
    }
}
